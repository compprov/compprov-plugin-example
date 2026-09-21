# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 85.0

## Anomaly Localization

**Implicated nodes:** `o_4` (Gross pay), `o_7` (Pretax deductions), `o_8` (Taxable income), `op_3`, `o_16` (Federal tax withheld), `o_18` (State tax withheld), `op_10`, `o_19` ("After federal withholding"), `op_11`, `o_20` (Net pay).

**Attack flow:**

1. `op_1` computes `o_4` = Gross pay = i_2 (Base salary) + i_3 (Bonus) = 4000.00. Semantic definition: gross pay **before any deductions**.
2. `op_2` computes `o_7` = Pretax deductions = i_5 (401k) + i_6 (Health premium) = 350.00.
3. `op_3` correctly computes `o_8` = Taxable income = `o_4` − `o_7` = 3650.00 — i.e., pretax deductions are legitimately removed from gross pay to establish the tax base.
4. `o_8` correctly propagates through the bracket logic (`op_4`–`op_8`, `op_9`) to produce `o_16` (Federal tax withheld = 563.00) and `o_18` (State tax withheld = 182.50), both properly computed against the *deduction-reduced* taxable base.
5. **The cast occurs at `op_10`**: `o_19` ("After federal withholding") = `o_4` − `o_16`. Critically, this reuses the *raw* `o_4` (Gross pay, still containing the 401k/health-premium amounts that were supposed to be pretax-deducted) rather than `o_8` (Taxable income, which already reflects the deduction) or an explicit "Gross minus pretax deductions" node. `o_4` is silently consumed here as though it already represents post-pretax-deduction pay — a business meaning it was never assigned.
6. `op_11` finalizes `o_20` = Net pay = `o_19` − `o_18` = 3437.00 − 182.50 = 3254.50.

**Net effect:** Correct net pay, honoring the pretax deductions actually withheld from the employee, should be Gross − Pretax deductions − Federal tax − State tax = 4000.00 − 350.00 − 563.00 − 182.50 = **2904.50**. The graph instead reports **3254.50** — overstated by exactly **350.00**, which is precisely the value of `o_7` (Pretax deductions). The 401k/health-premium amounts were used to legitimately shrink the *tax basis* (correctly lowering taxes owed) but were never actually removed from the amount disbursed to the employee.

## Details

This is a textbook Semantic Type and Context Cast: `o_4` never changes its `valueClass` (`java.math.BigDecimal`) or even its `descriptor.name` ("Gross pay") — it passes every technical/type-safety and mathematical-replay check, since `op_10` is a perfectly ordinary, type-correct `subtract` operation. Yet its *business meaning* is silently reinterpreted: at `op_3` it is treated as "gross before pretax deductions" (correctly reduced to get taxable income), while at `op_10` — just two hops later in the same graph — it is implicitly consumed as if it already incorporated the pretax deduction, because no further subtraction of `o_7` ever occurs on the path to `o_20`. The structural fact that `o_4` is consumed by *two different operations* (flagged in the reference data) is exactly the mechanism enabling this: one consumer (`op_3`) treats it under its true semantics, the other (`op_10`) treats it under a conflicting, undocumented semantics, and no domain-transformation node reconciles the two.

Because `descriptor.meta` is empty for the intermediate outputs, a naive validator sees only: correct types, correct arithmetic per node, and a fully connected DAG. It has no way to notice that the pretax deductions, having been "spent" once to lower the tax base, are never spent again to actually reduce the employee's take-home pay — they are effectively returned to the employee for free while still being reported to authorities as legitimately pretax-deducted for tax-liability purposes. This is a material, exploitable payroll fraud pattern (or at minimum a severe modeling defect): it understates true deductions in the net-pay output while the audit trail formally shows 401k/health-premium amounts being "deducted," creating a reconciliation gap between what tax computations assume and what money actually moves. The discrepancy is exactly reproducible and quantifiable (350.00), which strongly indicates deliberate/structural mis-wiring rather than random noise.