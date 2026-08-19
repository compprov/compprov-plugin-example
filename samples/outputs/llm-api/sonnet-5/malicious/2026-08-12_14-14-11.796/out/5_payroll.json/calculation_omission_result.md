# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 93.0

## Anomaly Localization

**Implicated variables:** `i_5` (401k contribution, 200.00), `i_6` (Health premium, 150.00), `o_7` (Pretax deductions, 350.00), `o_4` (Gross pay, 4000.00), `o_8` (Taxable income, 3650.00), `o_16` (Federal tax withheld, 563.0000), `o_18` (State tax withheld, 182.5000), `o_19` (After federal withholding, 3437.0000), `o_20` (Net pay, 3254.5000).

**Implicated operations:** `op_2` (add i_5+i_6 → o_7), `op_3` (subtract o_4-o_7 → o_8), `op_10` (subtract o_4-o_16 → o_19), `op_11` (subtract o_19-o_18 → o_20).

**Attack flow:** `o_7` ("Pretax deductions" = 401k + health premium = 350.00) is computed correctly and is genuinely consumed — but only as the subtrahend used to derive `o_8` ("Taxable income" = Gross − Pretax deductions = 3650.00), which is used exclusively to *size the tax brackets* (op_4/op_5/op_6/op_7/op_8/op_9) and thereby correctly reduce the federal and state tax liabilities. However, the **net pay aggregation itself** (`op_10`: `o_4 − o_16` → `o_19`, then `op_11`: `o_19 − o_18` → `o_20`) starts from `o_4` (Gross pay, **before** pretax deductions), not from `o_8` (Taxable income, **after** pretax deductions), and at no point does the chain leading to `o_20` re-subtract `o_7`. The employee's actual $350 of withheld pretax contributions (money that leaves the paycheck for 401k/health insurance) is therefore never deducted from the reported "Net pay."

## Details

This is a textbook instance of the second failure mode named in the attack definition: "a final aggregation/result operation whose arguments quietly omit a component that its own name, role, and metadata indicate it should include." `o_7` ("Pretax deductions") is not a dead leaf — it is consumed by `op_3` — so a naive unconsumed-variable/leaf check (and the structural reference data provided, which lists no anomalous leaves here) would not flag it. This is precisely why a sophisticated adversary would choose this construction: the deduction variable is *used*, just used for the wrong purpose (tax-bracket sizing only) while being silently excluded from the take-home aggregation that its own descriptor name ("Pretax deductions") and domain semantics (401k + health premium, both amounts an employee never receives in cash) require it to reduce.

The numeric evidence is unambiguous: a correctly complete payroll computation would yield Net pay = Gross − Pretax deductions − Federal tax − State tax = 4000.00 − 350.00 − 563.00 − 182.50 = **2904.50**. The graph instead reports `o_20` = 3254.5000 = Gross − Federal tax − State tax (4000.00 − 563.00 − 182.50), omitting the pretax-deduction term entirely. The discrepancy (3254.50 − 2904.50 = 350.00) is *exactly* equal to `o_7`'s value, which is a strong, mechanically verifiable fingerprint of an intentional omission rather than a rounding or design artifact — there is no metadata, annotation, or documented rationale anywhere in the graph (e.g., indicating deductions are "already netted elsewhere" or paid via a separate disbursement not modeled here) that would justify excluding a computed, named deduction from the pipeline's own reported "Net pay" output.

**Consequence:** The reported Net pay (`o_20` = 3254.50) overstates the employee's true take-home pay by $350.00 per pay period — the exact amount of the 401k and health-premium withholdings that were correctly computed but never subtracted from the final aggregation.