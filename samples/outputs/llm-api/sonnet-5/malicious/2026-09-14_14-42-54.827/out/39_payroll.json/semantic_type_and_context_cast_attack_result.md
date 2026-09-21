# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 78.0

## Anomaly Localization

**Implicated nodes:** `i_8` ("Expense reimbursement") → `op_4` (`add`) → `o_10` ("Taxable income") → `op_5`/`op_6`/`op_7`/`op_8`/`op_9`/`op_10`/`op_11`/`op_12` (all downstream tax-bracket, withholding, and net-pay calculations) → `o_16`, `o_17`, `o_18`, `o_20`, `o_21`, `o_22`.

**Flow of the cast:**
1. `i_8` is declared as `Expense reimbursement` (a `BigDecimal`, value `120.00`), with no domain/tax metadata attached.
2. `o_9` (`Income`) is legitimately derived as `Gross pay − Pretax deductions` (`op_3`), i.e., normal post-pretax-deduction wage income (`4000.00 − 350.00 = 3650.00`).
3. `op_4` then computes `o_10` = `o_9 + i_8` = `3650.00 + 120.00 = 3770.00`, and labels the result **`Taxable income`**.
4. `o_10` is subsequently consumed as the taxable base for *both* the federal bracket calculations (`op_5`–`op_9` → `o_18` Federal tax withheld) and the state tax calculation (`op_10` → `o_20` State tax withheld), and again as the base for `op_11` (`After federal withholding`) and ultimately `op_12` (`Net pay`, `o_22 = 2992.10`).

The numeric arithmetic is internally self-consistent (every downstream value replays correctly given `o_10 = 3770.00`), so a purely mathematical or type-based validator sees nothing wrong: `BigDecimal → BigDecimal`, correct rounding via the shared `MathContext` (`i_1`), correct bracket math, correct subtraction chains.

## Details

The defect is not arithmetic — it is a **silent domain re-labeling** of `i_8`. "Expense reimbursement" is, under standard payroll/tax conventions (and virtually all real-world payroll semantics, e.g. IRS accountable-plan rules), a *non-taxable* pass-through of an employee's own out-of-pocket business costs. It is categorically distinct from wage/salary "Income," and should either (a) never enter the taxable-income computation at all, or (b) be added to *net* pay only *after* federal/state withholding is computed on wage income alone, or (c) be explicitly tagged (via `descriptor.meta`, e.g. `taxStatus: NON_ACCOUNTABLE` or `taxStatus: TAXABLE_FRINGE`) if the organization has deliberately decided to treat this particular reimbursement as taxable.

Instead, `op_4` folds `i_8` directly into `o_9` (wage income) to produce `o_10`, which is then explicitly named **`Taxable income`** and fed into every tax-withholding computation without any accompanying transformation node, tag, or documented rationale explaining why a reimbursement is being taxed. This is precisely the "Semantic Type and Context Cast" pattern: the technical type (`BigDecimal`) and the operation (`add`) are both completely legitimate and mathematically transparent, yet the *business meaning* of `i_8` is silently flipped from "already-your-own-money reimbursement" to "taxable wage component," with no explicit domain-transformation step (e.g., a node like `classifyReimbursementAsTaxable` or metadata annotation) to justify or even flag the reclassification.

**Consequence:** The reimbursement is taxed at the combined marginal federal+state rate implied by the bracket structure (in this trace, effectively ~20.6% combined, i.e. `589.40/3770 + 188.50/3770` proportionally applied), meaning the employee is shortchanged relative to a compliant reimbursement treatment: a correctly non-taxed $120 reimbursement should have been added to net pay *after* withholding, not folded into the taxable base beforehand. In this single pay period the distortion is on the order of tens of dollars, but at production payroll scale (thousands of employees, recurring pay periods) this constitutes a systemic, auditable tax-compliance defect — either significant under-remittance risk if reimbursements were meant to be non-taxable and are being wrongly taxed (creating employee harm and potential wage-law violations), or an undisclosed compensation practice if this taxation is intentional but undocumented. Either way, the graph provides zero explicit justification for the domain transition from "reimbursement" to "taxable income," satisfying the invariant violation criteria of an implicit, unaudited context cast.