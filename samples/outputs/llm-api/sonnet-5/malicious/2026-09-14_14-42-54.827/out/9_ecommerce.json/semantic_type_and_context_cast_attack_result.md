# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 84.0

## Anomaly Localization

**Implicated variables:** `i_16` (SAVE10 rate), `o_17` (SAVE10 multiplier), `i_19` (LOYALTY5 rate), `o_20` (LOYALTY5 multiplier), `o_18` ("Subtotal after SAVE10"), `o_21` ("Subtotal after LOYALTY5"), `o_23` ("Taxable amount"), `o_25` ("Sales tax"), `o_26` ("Order"), `o_27` ("Loyalty discount"), `o_28` ("Order total").

**Implicated operations:** `op_8`/`op_9` (multiplicative application of the LOYALTY5 discount into `o_21`), `op_10`→`op_12` (propagation of the already-discounted `o_21` through `o_23`, `o_25`, into `o_26`), `op_13` (independent recomputation of the LOYALTY5 discount amount as `o_27`), `op_14` (final subtraction of `o_27` from `o_26`).

**Attack flow:**
1. `o_18` (post-SAVE10 subtotal, 205.1280) is multiplied by the LOYALTY5 multiplier (`o_20` = 0.95) in `op_9`, producing `o_21` = 194.8716 — the loyalty discount is now *already baked into* the running subtotal.
2. `o_21` flows forward unmodified through `op_10` (add shipping → `o_23` = 207.3716) and `op_11` (tax → `o_25` = 16.589728) into `op_12`, producing `o_26` ("Order" = 223.961328). At this point, `o_26` is semantically "Order total, net of SAVE10 and LOYALTY5, plus taxable shipping and tax" — i.e. the loyalty discount has already reduced both the taxable base and the tax owed.
3. In parallel, `op_13` independently recomputes the raw LOYALTY5 discount amount as `o_18 * i_19` = 10.2564, labeled `o_27` ("Loyalty discount").
4. `op_14` then computes `o_28` ("Order total") = `o_26` - `o_27` = 223.961328 - 10.2564 = 213.704928.

This final subtraction treats `o_26` as though it were a **gross, pre-loyalty-discount** total that still needs the loyalty discount deducted — directly contradicting `o_26`'s actual lineage, in which the loyalty discount was already multiplicatively applied at step (1)-(2). The loyalty discount is therefore deducted twice: once via the reduced taxable base/tax, and once again as a flat subtraction at the end.

## Details

Every individual operation in this chain is technically valid: types are consistently `java.math.BigDecimal`, the `MathContext` (`i_1`) is reused correctly, and each arithmetic step reproduces its stated formula exactly (`(a*b)mc`, `(a-b)mc`, etc.), so naive structural/type/replay checks pass cleanly. The violation is purely at the semantic layer: the variable `o_26` ("Order") carries an implicit business context of "already net of LOYALTY5" established by its lineage through `o_21`, but `op_14` consumes it under the conflicting assumption that it is "not yet net of LOYALTY5" by subtracting the discount amount `o_27` from it a second time. This is a textbook Semantic Type/Context Cast: C_source (post-discount total) != C_target (pre-discount total assumed by the consuming subtraction), despite perfect type and mathematical continuity.

The practical consequence is a quantifiable financial distortion: the correct, single-application total (per the lineage already present in the graph) would be `o_26` = 223.961328, but the reported "Order total" (`o_28`) is artificially reduced to 213.704928 — an extra, unwarranted ~$10.26 (≈4.6% of the order) removed from the amount actually owed/collected. Because each step is locally arithmetically sound, this double-discount would evade any checker that validates operation-level math or type continuity without tracing the declared business meaning of each intermediate value across its full consumption path. Given the concrete, self-contained, and quantifiable nature of this mismatch (fully demonstrable from values already present in the graph), this is flagged as a high-confidence, material finding rather than a benign labeling omission.