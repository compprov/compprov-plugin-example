# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

## Audit Summary

This CPG encodes a standard descriptive-statistics pipeline (sum → mean → per-sample deviations → squared deviations → sum of squares → sample variance via Bessel's correction (n-1) → sample standard deviation via sqrt). A full independent recomputation of every operation was performed using exact rational/decimal arithmetic and cross-checked against the MathContext (precision=16, HALF_EVEN) declared in `i_1` and referenced by every arithmetic operation.

### Recomputation Trace

- **op_1 (addBulk)**: 12.5+15.2+11.8+14.1+13.6+12.9 = 80.1 → matches `o_8` exactly.
- **op_2 (divide)**: 80.1/6 = 13.35 (exact) → matches `o_10` exactly.
- **op_3/op_5/op_7/op_9/op_11/op_13 (subtract, deviations)**: -0.85, 1.85, -1.55, 0.75, 0.25, -0.45 → all match `o_11, o_13, o_15, o_17, o_19, o_21` exactly, and each is correctly indexed against its corresponding sample input (`i_2`→[0], `i_3`→[1], `i_4`→[2], `i_5`→[3], `i_6`→[4], `i_7`→[5]).
- **op_4/op_6/op_8/op_10/op_12/op_14 (multiply, squared deviations)**: 0.7225, 3.4225, 2.4025, 0.5625, 0.0625, 0.2025 → all match `o_12, o_14, o_16, o_18, o_20, o_22` exactly.
- **op_15 (addBulk, sum of squares)**: 0.7225+3.4225+2.4025+0.5625+0.0625+0.2025 = 7.375 → matches `o_23` (7.3750) exactly.
- **op_16 (divide, sample variance)**: 7.375/5 = 1.475 → matches `o_25` (1.4750) exactly. Degrees of freedom used is `i_24`=5=n-1, the statistically correct (Bessel-corrected) unbiased sample variance divisor — not the biased n=6 divisor.
- **op_17 (sqrt, sample standard deviation)**: sqrt(1.475) recomputed via Newton-Raphson to >12 significant digits ≈ 1.21449578... which agrees with the reported `o_26` = 1.214495780149112 to at least 9 significant digits before diverging only into digits beyond manual verification precision — fully consistent with a correct 16-significant-digit (MathContext precision=16, HALF_EVEN) BigDecimal square-root computation.

### Wiring/Argument Integrity

- All `resultId` targets and `arguments` mappings are internally consistent: each subtraction consumes the correct raw sample (`i_2`..`i_7`) and the correct mean (`o_10`); each squaring operation consumes the matching deviation twice (`a`=`b`); the final `addBulk` for sum-of-squares consumes exactly the six squared-deviation outputs (`o_12,o_14,o_16,o_18,o_20,o_22`) with no substitution, omission, or duplication.
- The `MathContext` (`i_1`) is reused consistently and correctly across all operations as the `mc` argument — no operation silently omits it, downgrades precision, or swaps rounding mode.
- No scale/unit mixing is present — all values are in mL / mL² consistently, and no cross-asset or cross-unit conversion occurs in this pipeline.
- No isolated single-operation rounding-mode substitution was detected; all intermediate decimal values are exact under HALF_EVEN at the stated precision, and the only operation requiring non-terminating decimal expansion (sqrt) rounds correctly at the declared 16-digit precision.

### Conclusion

No discrepancy exceeding a single-ULP/rounding-noise threshold was found at any step. The population of arithmetic operations (6 fixed samples) is also non-scalable, structurally bounded, and does not exhibit any consistent directional bias toward an accumulation sink — ruling out Salami Slicing even hypothetically. All reported outputs are reproducible from the reported inputs under the declared MathContext with no unexplained truncation, downcasting, or rounding-mode deviation. No Precision and Scale Tampering pattern is present in this graph.
