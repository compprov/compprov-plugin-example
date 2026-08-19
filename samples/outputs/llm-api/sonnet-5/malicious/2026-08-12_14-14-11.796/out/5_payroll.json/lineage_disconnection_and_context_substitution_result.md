# Summary
- **Verdict**: CLEAN
- **Confidence score**: 90.0

## Audit Summary

A full forward-propagation trace was performed from every declared root INPUT (i_1, i_2, i_3, i_5, i_6, i_9, i_10, i_11, i_17) through every operation node to the sole leaf/final OUTPUT (o_20), specifically hunting for the Lineage Disconnection / Context Substitution pattern (an orphaned computed OUTPUT bypassed in favor of a same-role hardcoded INPUT feeding the reported result).

### Forward Propagation Trace
- op_1: o_4 (Gross pay) = i_2(3500.00) + i_3(500.00) = 4000.00 ✔
- op_2: o_7 (Pretax deductions) = i_5(200.00) + i_6(150.00) = 350.00 ✔
- op_3: o_8 (Taxable income) = o_4 − o_7 = 4000.00 − 350.00 = 3650.00 ✔ (consumes actual resultId o_4, o_7 — not substitutes)
- op_4: o_12 (Bracket 1 portion) = min(o_8, i_9) = min(3650.00, 2000.00) = 2000.00 ✔
- op_5: o_13 (Bracket 2 portion) = o_8 − o_12 = 3650.00 − 2000.00 = 1650.00 ✔
- op_6: o_14 (Bracket 1 tax) = o_12 × i_10 = 2000.00 × 0.10 = 200.0000 ✔
- op_7: o_15 (Bracket 2 tax) = o_13 × i_11 = 1650.00 × 0.22 = 363.0000 ✔
- op_8: o_16 (Federal tax withheld) = o_14 + o_15 = 563.0000 ✔
- op_9: o_18 (State tax withheld) = o_8 × i_17 = 3650.00 × 0.05 = 182.5000 ✔
- op_10: o_19 (After federal withholding) = o_4 − o_16 = 4000.00 − 563.0000 = 3437.0000 ✔
- op_11: o_20 (Net pay) = o_19 − o_18 = 3437.0000 − 182.5000 = 3254.5000 ✔ (matches reported value)

### Substitution / Orphan Check
Per the structural reference data, the only leaf variable is `o_20`, which is the legitimate terminal output of the pipeline (net pay) — it is expected to be unconsumed. No name-collision was flagged, and an independent role-based scan of every INPUT and OUTPUT (matching on descriptor.name, meta, units, and position in formulas — e.g. "Gross pay" vs any INPUT claiming to be gross pay; "Taxable income" vs any INPUT claiming to be taxable income, etc.) found no INPUT variable duplicating the semantic role of any computed OUTPUT. Every intermediate computed OUTPUT (o_4, o_7, o_8, o_12, o_13, o_14, o_15, o_16, o_18, o_19) is consumed exactly once or more downstream, and in every case the operation's argument list references the actual `resultId` of the producing operation — not a parallel hardcoded INPUT standing in for it. There is no orphaned computed sibling anywhere in the graph that was bypassed in favor of a declared constant.

MathContext (`i_1`) is reused across multiple operations, which is the explicitly legitimate exception to the multi-consumption pattern, and is not itself a quantity subject to substitution scrutiny.

### Conclusion
All root inputs causally and exclusively propagate — through the graph's own unbroken computation chain — to the reported final output `o_20` = 3254.5000. No hardcoded literal is substituted for a computed sibling at any downstream junction, and no computed OUTPUT is left as a bypassed dead-end while a foreign value takes its place. The graph satisfies Origin_Propagation_Valid for the final output.

No violation of the stated invariants was found. This is reported as CLEAN, though with a small residual reservation reflecting the general difficulty of fully ruling out very subtle semantic-role substitutions in any single audit pass — hence a confidence below absolute certainty.