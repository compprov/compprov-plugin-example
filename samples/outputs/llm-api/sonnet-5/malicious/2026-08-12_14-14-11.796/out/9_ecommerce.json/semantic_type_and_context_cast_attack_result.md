# Summary
- **Verdict**: SEMANTIC CAST DETECTED
- **Confidence score**: 85.0

## Anomaly Localization

**Implicated variables:** `i_19` (Discount rate LOYALTY5), `o_18` (Subtotal after SAVE10), `o_20` (Discount multiplier LOYALTY5), `o_21` (Subtotal after LOYALTY5), `i_22` (Shipping fee), `o_23` (Taxable amount), `i_24` (Tax rate), `o_25` (Sales tax), `o_26` (Order total (before loyalty discount)), `o_27` (Loyalty discount), `o_28` (Order total).

**Implicated operations:** `op_8` (subtract → `o_20`), `op_9` (multiply → `o_21`), `op_10` (add → `o_23`), `op_11` (multiply → `o_25`), `op_12` (add → `o_26`), `op_13` (multiply → `o_27`), `op_14` (subtract → `o_28`).

**Attack flow:**

1. The LOYALTY5 rate (`i_19` = 0.05) is first consumed by `op_8` to build a multiplier (`o_20` = 0.95), which `op_9` applies to the *already SAVE10-discounted* subtotal (`o_18`), producing `o_21` = "Subtotal after LOYALTY5" (194.8716). This value is semantically **already net of both discounts**.
2. `o_21` propagates, untouched in economic meaning, through `op_10` (+shipping → `o_23` "Taxable amount"), `op_11` (×tax rate → `o_25` "Sales tax"), and `op_12` (`o_23`+`o_25` → `o_26`). Consequently `o_26` — labeled **"Order total (before loyalty discount)"** — is numerically and semantically a total that **already has the LOYALTY5 discount baked in**, since its full lineage traces back through `o_21`.
3. Independently, `op_13` re-applies the *same* `i_19` rate directly to `o_18` (the pre-loyalty subtotal) to compute `o_27` = "Loyalty discount" (10.2564) as a stand-alone line item.
4. `op_14` then subtracts `o_27` from `o_26` to produce the final `o_28` "Order total" (213.704928).

Because `o_26` is mislabeled as *pre*-loyalty-discount when its true computational lineage is *post*-loyalty-discount, subtracting `o_27` at `op_14` **double-applies the LOYALTY5 discount** — once multiplicatively inside the tax base calculation, and a second time as an explicit deduction from the (already-discounted) tax-inclusive total.

## Details

Every individual arithmetic step replays correctly (`subtract(a,b)`, `multiply(a,b)`, `add(a,b)` all match their formulas and produce internally consistent decimal results), so mathematical replay and type-checking (`BigDecimal → BigDecimal` throughout) pass with no discrepancy. This is precisely the gap the attack exploits: the **numeric/type continuity is perfect**, but the **declared business context of `o_26`** ("before loyalty discount") is inconsistent with its actual derivation (already reflects the loyalty multiplier via `o_21`/`o_20`). No explicit domain-transformation node documents or justifies this re-labeling — it is an implicit, undocumented context flip that lets `op_14` treat an already-net figure as if it were gross, licensing an illegitimate second discount subtraction.

**Consequence:** The final `Order total` (`o_28` = 213.704928) is understated relative to the mathematically correct, once-discounted total (`o_26` = 223.961328) by the full second loyalty discount amount (`o_27` = 10.2564). This constitutes a systematic under-charge / discount-duplication defect that is invisible to schema validation and arithmetic replay, and would only be caught by auditing the semantic consistency between a variable's declared business meaning (`descriptor.name`/lineage) and its actual computational provenance — exactly the class of Semantic Type and Context Cast attack this audit targets.
