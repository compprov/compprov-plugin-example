# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Anomaly Localization (If Detected)
No lineage disconnection or context-substitution pattern was found. Full forward propagation from the eight root INPUT nodes (`i_1, i_2, i_3, i_5, i_6, i_9, i_10, i_13`) through every intermediate OUTPUT to the sole leaf `o_15` (Net pay) is unbroken and mathematically exact.

Trace of the computation chain:
- `op_1`: `o_4` = `i_3`(40) * `i_2`(22.00) = 880.00 ✔ (consumed later by `op_5`)
- `op_2`: `o_7` = `i_2`(22.00) * `i_6`(1.5) = 33.000 ✔ (consumed later by `op_3`)
- `op_3`: `o_8` = `i_5`(6) * `o_7`(33.000) = 198.000 ✔ (consumed later by `op_5`)
- `op_4`: `o_11` = `i_9`(12) * `i_10`(1.75) = 21.00 ✔ (consumed later by `op_5`)
- `op_5`: `o_12` = addBulk(`o_4`,`o_8`,`o_11`) = 1099.000 ✔ (consumed by `op_6` and `op_7`)
- `op_6`: `o_14` = `o_12`(1099.000) * `i_13`(0.18) = 197.82000 ✔ (consumed by `op_7`)
- `op_7`: `o_15` = `o_12`(1099.000) - `o_14`(197.82000) = 901.18000 ✔ (final leaf, no downstream consumer — expected for a terminal OUTPUT)

No variable in this chain is orphaned in favor of a parallel hardcoded stand-in. Every `resultId` produced by an operation is the exact argument consumed by the next logical operation in the chain — there is no case where a computed sibling (`o_4, o_7, o_8, o_11, o_12, o_14`) goes unconsumed while a same-named or same-role root INPUT is substituted into a downstream operation instead.

## Details
**Root/Leaf check:** The only leaf variable is `o_15` (Net pay), which is the pipeline's genuine final output — it has zero downstream consumers because it is the terminus of the computation, not because a substitute was routed in its place. No other computed OUTPUT variable in the graph is left unconsumed; each of `o_4, o_7, o_8, o_11, o_12, o_14` is consumed by exactly the operation the formula requires, and cross-checking `resultId` against the `arguments` of the next operation confirms the exact variable (not a same-named or same-valued stand-in) is what is actually passed forward.

**Name-collision / role-substitution scan:** The structural name-collision set returned empty, and an independent semantic scan (matching by role, unit, and value pattern rather than exact string) turned up no additional candidates. `i_2` (Base hourly rate) and `o_12` (Gross pay) are each consumed by two operations, but in both cases this is legitimate reuse dictated by the payroll formula itself (base rate feeds both regular pay and the overtime-rate multiplier; gross pay feeds both the tax-withholding calculation and the final net-pay subtraction) — not a case of one branch being computed-but-discarded while a foreign value is substituted into the other. There is no root INPUT in the variable list that duplicates, approximates, or shadows the name/value/role of any of the six computed intermediate OUTPUTs.

**Arithmetic/scale verification:** All BigDecimal scale arithmetic (multiplication scale = sum of operand scales, addBulk scale = max operand scale, subtraction scale = max operand scale) is internally consistent and exact under the declared `MathContext` (precision 16, HALF_EVEN) — no rounding artifacts or precision mismatches that might mask a hidden substitution were found.

**Conclusion:** Origin_Propagation_Valid holds for `o_15`: the reported Net pay of 901.18000 is fully and exclusively derived from the graph's own forward computation chain rooted in the declared inputs, with no evidence of a parallel injected value bypassing a properly computed sibling at any critical juncture. Confidence is not maximal only because, per audit discipline, a sufficiently well-disguised substitution using semantically identical-but-differently-named variables can never be fully excluded by inspection alone — but no such candidate was located here despite an explicit search.