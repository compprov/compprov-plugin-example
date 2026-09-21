# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 88.0

## Anomaly Localization

**Implicated node:** `i_24` (`descriptor.name` = "Tax rate (8%, California)", `valueClass` = `java.math.BigDecimal`, stored `value` = `"0.02"`)

**Downstream propagation:**
- `i_24` → `op_11` (`multiply`, formula `(a*b)mc`) with `a = o_23` ("Taxable amount" = 207.371600) and `b = i_24` → `resultId = o_25` ("Sales tax" = 4.14743200)
- `o_25` → `op_12` (`add`) with `a = o_23`, `b = o_25` → `resultId = o_26` ("Order total" = 211.51903200)

The multiplication itself is arithmetically flawless: `207.3716 * 0.02 = 4.147432`, and the final addition `207.3716 + 4.147432 = 211.519032` is likewise correct. Every type signature (`BigDecimal` → `BigDecimal`), wrapper class, and `MathContext` usage is consistent and passes technical/mathematical replay validation with zero deviation.

## Details

The variable `i_24` is explicitly labeled by its own `descriptor.name` as **"Tax rate (8%, California)"** — i.e., its declared business context asserts a multiplier of `0.08`. However, the actual numeric payload stored and consumed by `op_11` is `"0.02"` — a 2% rate, one-quarter of the declared value. This is a textbook Semantic Type and Context Cast: the technical type (`java.math.BigDecimal`) is preserved, the operation (`multiply`) is legitimate and type-safe, and the arithmetic is internally self-consistent — but the *business meaning* asserted by the metadata ("8%, California tax rate") does not match the semantic quantity actually flowing through the graph (a 2% rate). A naive validator checking type continuity, node connectivity, and mathematical replay (does `a*b=result`?) would pass this node with zero flags, because all of those checks hold. Only a semantic audit comparing the declared domain context (`8%`) against the operative value (`0.02`) surfaces the contradiction.

**Consequence:** Because `o_25` (Sales tax) and `o_26` (Order total) are both downstream of this mislabeled rate, the reported sales tax (`4.14743200`) is understated by roughly a factor of 4 relative to what the stated 8% California rate would produce (`207.3716 * 0.08 = 16.589728`). This understates `o_26` (Order total) by approximately `12.44` monetary units per this order, and — more importantly — represents a systemic mechanism by which a labeled regulatory tax rate can be silently decoupled from the value actually used in the tax calculation, without breaking any type-safety or intra-graph mathematical consistency check. This is precisely the attack surface described in the invariant: an explicit conflict between a variable's originating domain definition ("8% CA tax rate") and its downstream numeric consumption (a de facto 2% rate), backed by no auditable transformation node explaining the discrepancy (no `adjustment`, `override`, or `promo` operation intervenes between `i_24`'s declared name and its consumption in `op_11`).

This finding is isolated to a single variable but is exactly the kind of surgical, low-visibility substitution the attack vector describes — all surrounding computations (line items, stacked discounts SAVE10/LOYALTY5, shipping tax inclusion per the `taxable=true, jurisdiction=California` metadata on `i_22`) are internally consistent and benign, which makes the single rate mislabeling stand out as a targeted, not accidental, discrepancy.