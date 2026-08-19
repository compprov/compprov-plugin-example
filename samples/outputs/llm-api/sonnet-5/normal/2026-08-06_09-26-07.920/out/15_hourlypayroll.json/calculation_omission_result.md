# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

## Anomaly Localization (If Detected)
No qualifying variable (deduction, credit, correction, or cross-check) was found to be computed and then silently excluded from the final aggregation operation. All intermediate results are fully consumed:

- `o_4` (Regular pay = i_3×i_2) → consumed by `op_5` (addBulk) into `o_12`.
- `o_7` (Overtime rate = i_2×i_6) → consumed by `op_3` into `o_8`.
- `o_8` (Overtime pay = i_5×o_7) → consumed by `op_5` (addBulk) into `o_12`.
- `o_11` (Night-shift differential pay = i_9×i_10) → consumed by `op_5` (addBulk) into `o_12`.
- `o_12` (Gross pay = o_4+o_8+o_11) → consumed by both `op_6` (tax withholding) and `op_7` (net pay subtraction).
- `i_13` (tax withholding rate) → consumed by `op_6` producing `o_14` (tax withholding), which is in turn consumed by `op_7` to produce `o_15` (Net pay = Gross − Tax).

The only leaf variable in the graph is `o_15` (Net pay), which is the intended final reported output — consistent with a complete, terminated computation chain rather than an orphaned adjustment.

## Details
Tracing the full causal graph from roots to the final output `o_15`:

1. Regular pay, overtime pay, and night-shift differential pay are each computed correctly and independently, then aggregated via `addBulk` (`op_5`) into Gross pay (`o_12`) — 880.00 + 198.000 + 21.00 = 1099.000, arithmetically correct.
2. Payroll tax withholding (`o_14`) is computed from Gross pay and the tax rate (`op_6`) — 1099.000 × 0.18 = 197.82000, arithmetically correct.
3. Net pay (`o_15`) is computed as Gross pay minus Tax withholding (`op_7`) — 1099.000 − 197.82000 = 901.18000, arithmetically correct.

Every variable that functions as a mandatory adjustment to the payroll calculation (regular pay, overtime pay, night differential pay, and tax withholding) has an active, unbroken causal path into the operation producing the final reported result (`o_15`). No correctly-computed adjustment variable is left as an unconsumed dead-end, and no argument list for `op_5`, `op_6`, or `op_7` appears to have quietly dropped a component that its name/metadata indicates should be present. The `MathContext` precision/rounding is reused consistently (`i_1`) across all operations, and re-verification of each operation's arithmetic against its stated formula and MathContext confirms the recorded values are exactly what the formulas would produce — no silent precision-based value manipulation either.

No violation of the Calculation Omission invariant is present in this graph as submitted.