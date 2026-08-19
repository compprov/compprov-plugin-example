# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 90.0

## Anomaly Localization

**Implicated nodes:** `o_4` (Gross pay = 4000.00), `i_5`/`i_6` (401k = 200.00, Health premium = 150.00), `o_7` (Pretax deductions = 350.00), `o_8` (Taxable income = 3650.00), `o_16` (Federal tax withheld = 563.0000), `o_18` (State tax withheld = 182.5000), `op_10` (subtract: `a=o_4`, `b=o_16` → `o_19`), `op_11` (subtract: `a=o_19`, `b=o_18` → `o_20` "Net pay").

**Flow of the flaw:**
1. `op_2` correctly aggregates the employee's pretax deductions (401k + health premium) into `o_7` = 350.00.
2. `op_3` correctly subtracts `o_7` from `o_4` to compute `o_8` (Taxable income = 3650.00) — this is the *only* place `o_7`/`o_8` are used for tax-bracket calculations (`op_4`–`op_9`).
3. Critically, at the point where **net (take-home) pay** is finally assembled, `op_10` subtracts the federal tax **directly from the raw Gross pay (`o_4`)**, not from Taxable income (`o_8`) or from any node that has had the 350.00 pretax deduction actually removed from the cash flow.
4. `op_11` then subtracts state tax from that result to yield `o_20` = 3254.5000, the reported "Net pay."

The 350.00 in pretax deductions is used to *reduce the tax base* (correctly lowering the tax liability computed via `op_3`→`op_9`), but is **never actually withheld from the employee's paycheck** in the final aggregation chain (`op_10`/`op_11`). The employee benefits from the tax break on the 401k/health premium contributions without those amounts ever leaving `o_4` on the way to `o_20`.

## Details

**Mechanism:** This is a classic "forked lineage" residual-leakage pattern — a value (`o_7`/pretax deductions) is correctly computed and correctly consumed by one downstream branch (tax bracket calculation), giving every appearance of a fully-wired, auditable pipeline. But the *other* branch that should also consume it (the net-pay aggregation) instead reaches back upstream to the raw `o_4` (Gross pay) rather than a deduction-adjusted figure. Every individual operation replays perfectly under its own arguments — `op_10 = o_4 - o_16` is arithmetically exact given its stated inputs — so no single-step rational-arithmetic check flags a discrepancy. The defect is only visible when tracing *which* node was fed as the base value at the final subtraction step, i.e. a lineage/data-flow check rather than a per-operation numeric check.

**Quantified impact:** Correct net pay, by standard payroll arithmetic, is:
Gross − 401k − Health − Federal Tax − State Tax = 4000.00 − 200.00 − 150.00 − 563.0000 − 182.5000 = **2904.5000**.
Reported net pay (`o_20`) = **3254.5000**.
Δ = 350.00 exactly — matching `o_7` (Pretax deductions) to the cent. This is not a rounding-mode artifact (which the invariants cap at ≤1 unit at target scale); it is a full, deterministic omission of an entire deduction category from the final aggregation, reproducible on every payroll run using this template.

**Materiality:** This is not an isolated sub-cent salami-slice; it is an 8.75%-of-gross overstatement of net pay that would recur identically on every paycheck for every employee run through this pipeline — a scalable, systematic leak (either overpaying employees relative to what the 401k/health provider actually receives, or masking an accounting shortfall in employer books), not statistical noise. This violates the "asset conservation across intermediate steps" invariant: the sum of tax withheld + pretax deductions + net pay does not reconcile to gross pay (563.00 + 182.50 + 350.00 + 3254.50 = 4350.00 ≠ 4000.00 gross), confirming a structural (not rounding-level) conservation break.

**Why casual/mechanical checks miss it:** The structural reference data shows `o_7` is consumed once (by `op_3`), which looks like a clean, non-suspicious single-use variable — no duplicate ID, no duplicate name, no multi-consumption flag. The exact-match heuristics have nothing to catch here because the anomaly is not a duplicated or renamed identifier; it is a missing edge — `o_7`/`o_8` never feed into `op_10`/`op_11` where they should. This is precisely the kind of surgical, single-edge substitution a sophisticated adversary would use to survive automated provenance diffing while still altering the reported bottom-line figure.