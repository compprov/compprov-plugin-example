# Summary
- **Verdict**: CLEAN
- **Risk score**: 3.0

## Audit Summary

This CPG models a standard 1-D elastic collision calculation (conservation of momentum applied via the standard `v1' = ((m1-m2)v1 + 2m2v2)/(m1+m2)` and `v2' = ((m2-m1)v2 + 2m1v1)/(m1+m2)` formulas). All arithmetic was independently recomputed using exact rational arithmetic and cross-checked against the reported `BigDecimal` values and the declared `MathContext` (precision=16, HALF_EVEN) carried on every operation via the `mc` argument.

### Recomputation Results (Exact vs Reported)
- o_7 (m1+m2): 8.0 == 8.0
- o_8 (m1-m2): -2.0 == -2.0
- o_9 ((m1-m2)*v1): -8.00 == -8.00
- o_10 (2*m2): 10.0 == 10.0
- o_11 (2m2*v2): -20.00 == -20.00
- o_12 (numerator v1'): -28.00 == -28.00
- o_13 (v1' = o_12/o_7): -3.5 == -3.5 (exact division, no remainder, no rounding invoked)
- o_14 (m2-m1): 2.0 == 2.0
- o_15 ((m2-m1)*v2): -4.00 == -4.00
- o_16 (2*m1): 6.0 == 6.0
- o_17 (2m1*v1): 24.00 == 24.00
- o_18 (numerator v2'): 20.00 == 20.00
- o_19 (v2' = o_18/o_7): 2.5 == 2.5 (exact division)
- o_20 (m1*v1 before): 12.00 == 12.00
- o_21 (m2*v2 before): -10.00 == -10.00
- o_22 (momentum before): 2.00 == 2.00
- o_23 (m1*v1' after): -10.50 == -10.50
- o_24 (m2*v2' after): 12.50 == 12.50
- o_25 (momentum after): 2.00 == 2.00

Every arithmetic step matches the exact rational result with zero delta — no truncation, no scale reduction, and no divergence from the declared `MathContext` (precision 16, HALF_EVEN) was observed. Both divide operations (`op_7`, `op_13`) resolve to terminating decimals (-28.00/8.0 = -3.5, 20.00/8.0 = 2.5), so no rounding behavior is even exercised, eliminating any ambiguity about rounding-mode selection.

### Conservation Check (Domain Invariant)
Total momentum before (o_22 = 2.00) equals total momentum after (o_25 = 2.00) exactly — the physical invariant the pipeline is designed to preserve holds with zero residual leakage. There is no asset/quantity conservation violation at any intermediate node.

### Structural Findings Review
- Root inputs (i_1..i_6) are all legitimate INPUT nodes; `i_1` (MathContext) is correctly reused across all 19 operations, which is expected/benign per the spec.
- Variables reused across multiple operations (i_2, i_3, i_4, i_5, i_6, o_7) are all legitimate re-use of masses, velocities, the constant 2, and total mass — not evidence of tampering, and each downstream consumption was verified arithmetically above.
- Leaf variables (o_22, o_25) are the two final momentum conservation checkpoints, appropriately terminal.
- No duplicate/near-duplicate naming collisions were found among leaves.

### Conclusion
No evidence of precision degradation, premature truncation, non-standard/inconsistent rounding, unit-scale mishandling, or salami-slicing-style skimming was found. Every operation's declared `MathContext` was honored, all divisions were exact, and the core physical invariant (momentum conservation) holds exactly at both the pre- and post-collision checkpoints. This graph shows no material or structural indication of Precision and Scale Tampering.