# Summary
- **Verdict**: CLEAN
- **Confidence score**: 85.0

## Summary
This CPG models a series-parallel resistor network (R1+R2 in series, combined with R3∥R4 in parallel) driving a total current and two independent power calculations (direct P=I·V and cross-check P=I²·Rtotal). A full forward-propagation replay was performed from every root input through every operation to every reported output, explicitly checking for the Lineage Disconnection / Context Substitution signature: a computed sibling variable being orphaned while a foreign/hardcoded stand-in with the same name, role, or metadata is routed into a downstream calculation instead.

## Forward Propagation Verification
- op_1: add(i_2=100, i_3=150) → o_7 = 250 ✅ (R1+R2)
- op_2: multiply(i_4=300, i_5=600) → o_8 = 180000 ✅ (R3·R4)
- op_3: add(i_4=300, i_5=600) → o_9 = 900 ✅ (R3+R4)
- op_4: divide(o_8=180000, o_9=900) → o_10 = 200 ✅ (Rparallel)
- op_5: add(o_7=250, o_10=200) → o_11 = 450 ✅ (Rtotal)
- op_6: divide(i_6=120, o_11=450) → o_12 = 0.2666666666666667 ✅ (4/15 rounded HALF_EVEN, precision 16)
- op_7: multiply(o_12, i_6=120) → o_13 = 32.00000000000000 ✅ (verified exact digit-level rounding)
- op_8: pow(o_12, i_14=2) → o_15 = 0.07111111111111113 ✅ (verified via exact big-integer expansion and HALF_EVEN rounding at the 17th significant digit)
- op_9: multiply(o_15, o_11=450) → o_16 = 32.00000000000001 ✅ (verified via exact expansion: true product 32.0000000000000085 → rounds up at 16 sig figs)

Every arithmetic step reproduces exactly, down to the last rounding digit under MathContext(precision=16, HALF_EVEN) — including the two values (o_15, o_16) whose last-digit behavior could plausibly have masked a substitution but instead check out precisely against manual big-integer recomputation.

## Lineage / Substitution Check
- **Root inputs** (i_1 MathContext, i_2 R1, i_3 R2, i_4 R3, i_5 R4, i_6 V, i_14 exponent) are all genuine, non-duplicated primary parameters with no computed sibling elsewhere in the graph representing the same quantity under a different id.
- **Name-collision set is empty** — no leaf shares an exact name with another node.
- Manually scanning for *semantic* stand-ins (same units/role/value pattern) for every leaf and every intermediate output: no root INPUT exists with a value matching or approximating Rtotal (450), I (0.2666...), or Power (32), nor any duplicate-named or re-worded variable playing the role of Rtotal, I, or P. No hardcoded literal is substituted at any consumption point.
- **Multi-consumer variables** (i_4, i_5, i_6, o_11, o_12) are all consumed by *their own* correctly-produced downstream operations (verified by argument-to-resultId matching for every operation) — no operation consumes a foreign variable in place of the expected resultId.
- **Leaves o_13 and o_16** are not orphaned computed siblings bypassed in favor of a substitute — they are two independently and correctly derived final answers computing the *same physical quantity* (total power) via two different legitimate formulas (P=I·V and P=I²·Rtotal), a standard engineering cross-check pattern. Neither is superseded by a hardcoded twin; both trace cleanly back to true root inputs (i_2..i_6, i_14) through the graph's own actual computation chain, with every argument being the literal resultId of its producing operation.

## Conclusion
No instance of Intermediate Context Hijacking, orphaned-computed-vs-hardcoded-twin substitution, or severed causal lineage was found. Every reported OUTPUT (o_7 through o_16) is fully and exclusively derived from the graph's own operation chain rooted in i_1–i_6/i_14, with exact resultId-to-argument consumption at each step and correct numeric replay (including precision-context rounding edge cases that would be the most likely place to hide a disguised substitution). Given the depth of this audit's specific mandate, I flag this with high — but not absolute — confidence as clean, since a small graph of this size leaves less room for a subtle hidden substitution to escape detection, and none was found.