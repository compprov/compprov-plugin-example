# Summary
- **Verdict**: SEMANTIC CAST DETECTED
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated variables:** `i_8` ("Expense reimbursement (non-taxable)", 120.00), `o_9` ("Taxable income (before reimbursement)", 3650.00), `o_10` ("Taxable income", 3770.00), and every downstream consumer of `o_10`: `o_14` (Bracket 1 portion), `o_15` (Bracket 2 portion), `o_16` (Bracket 1 tax), `o_17` (Bracket 2 tax), `o_18` (Federal tax withheld), `o_20` (State tax withheld), `o_21` (After federal withholding), `o_22` (Net pay).

**Implicated operations:** `op_4` (the cast point), and the entire downstream chain `op_5, op_6, op_7, op_8, op_9, op_10, op_11, op_12` that consumes the tainted value.

**Attack flow:**
1. `i_2` + `i_3` → `o_4` (Gross pay, 4000.00) — clean.
2. `i_5` + `i_6` → `o_7` (Pretax deductions, 350.00) — clean.
3. `o_4` − `o_7` → `o_9` ("Taxable income (before reimbursement)", 3650.00) — clean, and the *name itself* documents that the non-taxable reimbursement has not yet been mixed in.
4. **`op_4`: `o_9` + `i_8` → `o_10` ("Taxable income").** Here the variable explicitly tagged in its own descriptor as "Expense reimbursement (non-taxable)" is added directly into the value that is thereafter treated, by name and by downstream consumption, as the authoritative taxable-income figure (3770.00).
5. `o_10` is then fed as the taxable base into every subsequent tax-bracket computation (`op_5`–`op_11`): bracket capping (`min`), bracket splitting (`subtract`), rate multiplication (`multiply`), federal tax aggregation (`add`), and state tax multiplication — all operating on the inflated 3770.00 figure instead of the correctly-scoped 3650.00.
6. The federal/state tax withheld (`o_18`=589.40, `o_20`=188.50) are therefore computed as if the $120 reimbursement were ordinary taxable wages, and `o_10` is reused again in `op_11` (`o_10` − `o_18` → `o_21`) to derive "After federal withholding", compounding the same tainted base into the final `o_22` ("Net pay" = 2992.10).

Reconstructing the pipeline as its own naming implies it should behave — reimbursement excluded from the tax base and simply added back to net pay post-tax, untaxed — yields taxable income of 3650.00, total tax of ~563.00 federal + 182.50 state, after-tax pay of ~3087.00, and a net pay of ~3024.50 once the $120 non-taxable reimbursement is added back untaxed. The delta between that figure and the reported 2992.10 is 32.40 — almost exactly 22% + 5% (the marginal federal + state rates) of the $120 reimbursement. This is not rounding noise; it is the precise fiscal signature of a non-taxable amount being silently taxed.

## Details

The attack works because every individual operation is textbook type-safe: `BigDecimal + BigDecimal → BigDecimal` under a shared `MathContext`, replayed and mathematically verifiable to the reported precision. `op_4`'s formula `(a+b)mc` is indistinguishable, at the type/signature level, from any other legitimate addition in the graph (e.g. `op_1`, `op_2`). No wrapper, cast, or explicit "apply non-taxable exclusion" node exists to mark the semantic transition — the reimbursement is simply added into the running total and the result is renamed from "Taxable income (before reimbursement)" to "Taxable income", implying the operation is a corrective/finalizing step rather than a mislabeling one. A purely structural or type-based validator sees a well-typed add producing a well-typed output with a plausible name, and passes it.

Semantically, however, `i_8`'s own descriptor explicitly declares it non-taxable, and `o_9`'s own descriptor explicitly declares it as taxable income *before* the reimbursement is considered — i.e., the graph's own metadata states the correct business rule (reimbursement should not enter the taxable base) and then `op_4` violates that rule by folding it in anyway, with no domain-transformation node, no tax-status override annotation, and no audit trail justifying the domain change from "non-taxable expense" to "taxable income component." This is a canonical Semantic Type/Context Cast: `C_source` (non-taxable reimbursement) is silently recast as `C_target` (taxable wages) while the technical type (`BigDecimal`) and the arithmetic (mathematically consistent, verifiable to the stated MathContext) remain perfectly intact.

**Consequence:** every downstream tax figure (`o_16`–`o_21`) and the final reported `Net pay` (`o_22` = 2992.10) are computed on an inflated taxable base, resulting in the employee being over-withheld by approximately $32.40 relative to correct treatment of a non-taxable reimbursement — a real, quantifiable, and directionally consistent financial harm that would recur every pay period this logic is used, and one that survives casual review because the sums all "add up" locally.