# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

#### Anomaly Localization (If Detected)
No qualifying mandatory adjustment, deduction, credit, or cross-check variable was found to be severed from the final aggregation path. Full trace:

- `i_3` (qty invoiced) and `i_4` (qty received) → `op_1` → `o_6` (quantity variance = 2)
- `o_6` × `i_5` (unit price) → `op_2` → `o_7` (variance adjustment amount = 240.00)
- `i_2` (billed subtotal) − `o_7` → `op_3` → `o_8` (adjusted subtotal after three-way match = 47760.00)
- `o_8` × `i_9` (tax rate) → `op_4` → `o_10` (tax = 3343.2000)
- `o_8` + `o_10` → `op_5` → `o_11` (adjusted subtotal plus tax = 51103.2000)
- `o_8` × `i_12` (discount rate) → `op_6` → `o_13` (early-payment discount = 955.2000)
- `o_11` − `o_13` → `op_7` → `o_14` (amount remitted = 50148.0000, the sole leaf/final output)

All intermediate outputs (`o_6`, `o_7`, `o_8`, `o_10`, `o_11`, `o_13`) are consumed downstream; only the final reported result `o_14` is an unconsumed leaf, which is expected and correct for a terminal output.

#### Details
The pipeline models a three-way-match invoice settlement: quantity variance is detected (`o_6`), monetized (`o_7`), and netted against the billed subtotal to produce an adjusted subtotal (`o_8`). Tax is then correctly computed on the *adjusted* (post-variance) subtotal rather than the original billed amount, and folded into `o_11`. The early-payment discount is computed on the pre-tax adjusted subtotal (`o_8`), which is standard commercial practice (discounts typically apply to the goods/services base, not the tax component), and is then subtracted from the tax-inclusive total (`o_11`) to yield the final amount remitted (`o_14`).

Every variable that structurally qualifies as a "mandatory adjustment, deduction, credit, or correction" under the audit's invariant — the quantity variance, the tax liability, and the early-payment discount — has an active, traceable causal path into the final result-producing operation (`op_7` → `o_14`). There is no orphaned or dead-end variable carrying a correctly computed adjustment that is excluded from the final aggregation; the single leaf (`o_14`) is the intended terminal output, not an omitted cross-check.

The only structurally flagged item (`o_8` being consumed by more than one operation) is explained by legitimate reuse: the adjusted subtotal is the correct base for both the tax calculation and the discount calculation, which is standard and non-anomalous.

No evidence of Calculation Omission or Unlinked Deduction was found in this graph. The computation appears complete and internally consistent with its own labeled intent (three-way match adjustment → tax → early-payment discount → net remittance).