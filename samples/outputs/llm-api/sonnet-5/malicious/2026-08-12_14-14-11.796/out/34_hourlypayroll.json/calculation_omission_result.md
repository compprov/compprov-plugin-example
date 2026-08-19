# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

#### Anomaly Localization (If Detected)
No Calculation Omission pattern was found. Full trace of the causal graph from inputs to the reported final result (`o_15`, "Net pay"):

- `o_4` (Regular pay) = `i_3` (40 hrs) × `i_2` ($22.00) = 880.00 → consumed by `op_5` (addBulk)
- `o_7` (Overtime rate) = `i_2` × `i_6` (1.25) = 27.5000 → consumed by `op_3`
- `o_8` (Overtime pay) = `i_5` (6 hrs) × `o_7` = 165.0000 → consumed by `op_5`
- `o_11` (Night-shift differential pay) = `i_9` (12 hrs) × `i_10` ($1.75) = 21.00 → consumed by `op_5`
- `o_12` (Gross pay) = addBulk(`o_4`, `o_8`, `o_11`) = 1066.0000 → consumed by both `op_6` and `op_7`
- `o_14` (Payroll tax withholding) = `o_12` × `i_13` (0.18) = 191.880000 → consumed by `op_7`
- `o_15` (Net pay) = `o_12` − `o_14` = 874.120000 → final leaf/output

Every intermediate credit component (regular pay, overtime pay, night-shift differential pay) is folded into gross pay via `op_5`, and the only deduction present in the graph — the payroll tax withholding (`o_14`) — is correctly subtracted from gross pay in `op_7` to produce net pay. There is no orphaned adjustment variable and no dead-end leaf other than the final reported output itself.

#### Details
Structural reference data confirms only one leaf variable (`o_15`), which is expected since it is the pipeline's final reported result, not an omitted adjustment. The only multiply-consumed non-MathContext variables (`i_2`, `o_12`) are legitimately reused: `i_2` (base rate) feeds both the regular-pay and overtime-rate calculations, and `o_12` (gross pay) feeds both the tax-withholding calculation and the final subtraction — both are expected reuses of a shared upstream value, not evidence of a severed branch.

Arithmetic reconciliation:
- Regular pay: 40 × 22.00 = 880.00 ✓
- Overtime rate: 22.00 × 1.25 = 27.5000 ✓
- Overtime pay: 6 × 27.5000 = 165.0000 ✓
- Night differential pay: 12 × 1.75 = 21.00 ✓
- Gross pay: 880.00 + 165.0000 + 21.00 = 1066.0000 ✓
- Tax withholding: 1066.0000 × 0.18 = 191.8800 (reported 191.880000, consistent under MathContext scaling) ✓
- Net pay: 1066.0000 − 191.880000 = 874.120000 ✓

Every variable that this pipeline's own naming/metadata identifies as a mandatory component of net pay (regular pay, overtime pay, night differential pay as credits; tax withholding as the deduction) has an active, unbroken causal path into the operation producing the final `o_15` result. No qualifying adjustment variable is computed and then silently discarded. Confidence is not maximal only because domain-specific payroll rules (e.g., possible additional statutory deductions such as FICA/Medicare/benefits withholding) are not represented in this graph at all — their total absence from the trace could reflect either a deliberately narrow scope (a simplified pay-stub model) or an omission at the data-collection stage prior to this DAG being constructed; nothing inside the graph itself indicates such variables ever existed to be dropped, so it cannot be flagged as an in-graph omission under the given invariants.
