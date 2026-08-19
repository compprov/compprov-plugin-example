# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

## Audit Summary

This CPG models a standard two-layer (brick + insulation) steady-state heat-conduction calculation under a single, globally-shared `MathContext` (`i_1`: precision=16, HALF_EVEN). Every operation (`op_1`…`op_14`) explicitly carries this `mc` argument, so the declared rounding convention is directly checkable against the EXPECTED_INVARIANTS ground truth (no ambiguous 'no-MathContext' cases exist here).

### Verification methodology
I recomputed every operation using exact rational/decimal arithmetic and then re-applied HALF_EVEN rounding at precision 16 exactly as Java's `BigDecimal` MathContext semantics dictate, tracking scale propagation (multiply → sum of scales; divide → preferred-scale = dividend.scale − divisor.scale, subject to precision-driven rounding when the exact quotient doesn't terminate within the allotted digits).

Key chain re-derivations performed by hand:
- `op_2`/`op_3` (R1 = d1/k1 = 0.1̄38→0.1388888888888889, R2 = d2/k2 = 1.25): both exact HALF_EVEN roundings match reported `o_10`, `o_11`.
- `op_4` (R_total = R1+R2): summing the two 16-sig-fig operands yields a 17-digit intermediate (1.3888888888888889…9); HALF_EVEN rounding of the 17th digit (9) correctly rounds up to `1.388888888888889`, matching `o_12` exactly.
- `op_5` (q = ΔT/R_total = 17/1.388888888888889): performed exact big-integer long division (17×10^15 / 1388888888888889). Derived the true quotient as 12.2399999999999990208… (offset from the ideal 12.24 caused entirely by the earlier legitimate rounding of R_total, not by tampering). At the 15th decimal digit the value is a 9, forcing a HALF_EVEN carry cascade back through twelve consecutive 9-digits, landing exactly on the reported `12.24000000000000`. This is a textbook example of *correct* rounding-mode application that a naive 'looks suspiciously round' heuristic might flag — but it is mathematically forced by HALF_EVEN, not evidence of tampering.
- `op_6`–`op_8` (ΔT1, T_interface, ΔT2): exact BigDecimal scale arithmetic reproduces `1.700000000000000`, `20.30000000000000`, `15.30000000000000` precisely.
- `op_9`–`op_11` (Q1 = k1·A·ΔT1/d1) and `op_12`–`op_14` (Q2 = k2·A·ΔT2/d2): both independent physical paths converge on the same value, 122.4 W (`o_19` = 122.400000000000, `o_22` = 122.4000000000000), differing only in trailing-zero scale (12 vs. 13 decimal places) as dictated by each divide's distinct dividend/divisor scale combination — not by divergent rounding logic. This cross-check is the critical energy-conservation invariant (Q1 must equal Q2 in steady state across two layers in series), and it holds to full precision.

### Formula/argument-order sanity checks
All `arguments` mappings were checked against each operation's declared `formula` metadata and against physical correctness:
- ΔT_total = T_int − T_ext (`op_1`, correct operand order)
- R = d/k for both layers (`op_2`, `op_3`, correct operand order — not accidentally inverted to k/d)
- q = ΔT_total/R_total (`op_5`)
- ΔT1 = q·R1 (`op_6`), interface temp and ΔT2 correctly chained (`op_7`, `op_8`)
- Independent Q1 and Q2 derivations use k·A·ΔT/d consistently, with no cross-wiring of layer-1 inputs into the layer-2 branch or vice versa.

No instance was found of: a non-default rounding mode substituted for HALF_EVEN, a precision downgrade/truncation applied mid-pipeline, a unit/scale mismatch between operands, an argument swap that would silently invert a ratio, or a leaf/duplicate-ID substitution consistent with the structural reference data (root/leaf sets, multi-consumed variables) all correspond to legitimate, physically-meaningful reuse (e.g., `o_10`/`o_14` are reused because R1 and ΔT1 legitimately feed two downstream formulas each).

### Conclusion
Every operation's result is consistent with exact application of the declared MathContext(16, HALF_EVEN), scale propagation follows standard BigDecimal semantics, and the two independently-computed heat-flow values (Q1, Q2) agree exactly, satisfying the energy-conservation invariant. No evidence of salami-slicing, precision downgrade, rounding-mode substitution, or unit-mixing was found.

Given the depth of manual re-derivation (including the non-trivial rounding-cascade check on `op_5`) and full agreement at every step, this graph presents as clean with respect to the Precision and Scale Tampering vector.