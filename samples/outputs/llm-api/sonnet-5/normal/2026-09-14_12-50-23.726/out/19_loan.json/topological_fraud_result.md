# Summary
- **Verdict**: CLEAN
- **Risk score**: 8.0

## Audit Scope
Attack vector under review: Topological & Provenance Fraud (Calculation Omission M=0, Double-Counting M>1, Lineage Disconnection/Context Substitution M=1-wrong-source) across the 6-month loan amortization CPG, focused on the three terminal (leaf) outputs: `o_29` (Ending balance), `o_31` (Total interest accrued), `o_34` (Total amount paid by borrower).

## Methodology
1. Fully replayed every arithmetic operation from the true root inputs (`i_1`\u2013`i_5`, `i_9`, `i_13`, `i_17`, `i_22`, `i_26`, `i_30`) forward through the entire balance/interest/principal recursion chain (op_1\u2013op_19), reproducing every reported value to the stated `MathContext(precision=16, HALF_EVEN)`.
2. Verified each of the three `addBulk` consolidations (`op_20`\u2192`o_31`, `op_21`\u2192`o_32`, `op_22`\u2192`o_33`, `op_23`\u2192`o_34`) against the full set of monthly components they are supposed to aggregate.
3. Cross-checked every entry in the structural reference sets (roots, leaves, multi-consumed variables, name-collision leaves) against the reconstructed lineage graph to look for orphaned mandatory contributors, re-entrant duplication into the same terminal, or root/computed name substitutions.

## Findings

### Calculation Omission (M=0) check
All six monthly interest values (`o_6, o_10, o_14, o_18, o_23, o_27`) are present as arguments to `op_20` \u2192 `o_31`. All six escrow inputs (`i_9, i_13, i_17, i_22, i_26, i_30`) are present as arguments to `op_21` \u2192 `o_32`. `o_34` = `o_33` (scheduled payments) + `o_32` (escrow) + `i_4` (prepayment) \u2014 a complete cash-flow total; `o_31` (interest) is correctly *not* re-added since it is already embedded inside `o_33`\u2019s monthly payments, so its exclusion from `o_34` is not an omission. No leaf or root input was found unconsumed where its name/role required inclusion.

### Double Counting (M>1) check
The reused-variable list (`i_2, i_3, i_4, i_5, o_6, o_8, o_10, o_12, o_14, o_16, o_18, o_21, o_23, o_25, o_27`) was traced individually. Each instance corresponds to the expected two-step amortization pattern (a balance/interest value used once to derive the next month\u2019s figure and once to derive the current month\u2019s output), or a genuine constant (rate, monthly payment amount) applied identically across independent periods. `i_4` (prepayment) is consumed once in the balance chain (`op_13`) and once directly in the total-paid aggregate (`op_23`) \u2014 these are two different terminal metrics (ending balance vs. total cash paid), not a re-entry into the same rollup, so this is the expected dual role of a prepayment, not double counting. `i_2` appearing 6x in `op_22` reflects 6 distinct monthly payment periods of identical value (verified independently: each month\u2019s principal+interest sums to exactly `i_2`), not an artificial inflation.

### Lineage Disconnection / Context Substitution (M=1, wrong source) check
No leaf shares an exact or near-duplicate name with another variable (confirmed both mechanically and via manual semantic scan of all INPUT roots vs. computed OUTPUTs). Every downstream operation consumes the literal `resultId` of its true predecessor step \u2014 verified line-by-line for the entire balance/interest/principal recursion (`op_1`\u2013`op_19`) and the four consolidation nodes (`op_20`\u2013`op_23`). No root INPUT was found masquerading under a computed sibling's name/units/role, and no orphaned computed twin exists anywhere in the graph.

### Arithmetic Verification
All 34 variable values were independently recomputed and match the reported values under the declared `MathContext(16, HALF_EVEN)`, including the compounding effects of the month-4 prepayment on months 5\u20136 interest, and the final rollups (`o_31=5657.186200001495`, `o_32=2400.00`, `o_33=12000.00`, `o_34=19400.00`). No silent value substitution or precision-based skimming was detected.

## Conclusion
This CPG exhibits a well-formed, single-path amortization pipeline: every mandatory contributor (monthly interest, monthly principal, escrow, prepayment) reaches its correct terminal aggregate exactly once, with no orphaned computed variables, no re-entrant duplication into the same rollup, and no root/computed lineage substitution. The reused-variable list is fully explained by legitimate recursive amortization structure and constant reuse across independent time periods, not by the double-counting or substitution mechanisms defined in the attack vector. No violation of the stated invariants was found; residual risk is limited to the generic architectural note that repeated literal reuse of `i_2` (rather than deriving `o_33` from the sum of each month's own principal+interest outputs) is a slightly less auditable but mathematically verified-equivalent shortcut, worth monitoring in future revisions but not itself an exploit.