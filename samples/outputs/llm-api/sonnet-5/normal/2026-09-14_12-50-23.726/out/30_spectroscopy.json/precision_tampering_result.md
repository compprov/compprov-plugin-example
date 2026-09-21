# Summary
- **Verdict**: CLEAN
- **Risk score**: 3.0

## Precision and Scale Tampering Audit

### Anomaly Localization (If Detected)
No violation of the EXPECTED_INVARIANTS was found. Every operation (`op_1`–`op_8`) was independently recomputed using exact rational/BigDecimal arithmetic and compared against the reported result:

| Op | Formula | Inputs | Exact Result | Reported | Δ |
|----|---------|--------|---------------|----------|---|
| op_1 | i_2 * i_4 | 15000 × 0.00001 | 0.15000 | 0.15000 | 0 |
| op_2 | o_5 * i_3 | 0.15000 × 1.00 | 0.1500000 | 0.1500000 | 0 |
| op_3 | i_2 * i_7 | 15000 × 0.00002 | 0.30000 | 0.30000 | 0 |
| op_4 | o_8 * i_3 | 0.30000 × 1.00 | 0.3000000 | 0.3000000 | 0 |
| op_5 | i_2 * i_10 | 15000 × 0.00004 | 0.60000 | 0.60000 | 0 |
| op_6 | o_11 * i_3 | 0.60000 × 1.00 | 0.6000000 | 0.6000000 | 0 |
| op_7 | o_9 / o_6 | 0.3000000 / 0.1500000 | 2 | 2 | 0 |
| op_8 | o_12 / o_6 | 0.6000000 / 0.1500000 | 4 | 4 | 0 |

All scale transitions follow the standard BigDecimal multiplication rule (scale(a)+scale(b)) and division under the declared MathContext (`i_1`, precision 16, HALF_EVEN). No result required rounding at all, since every operand fits well within the 16-digit working precision, so the choice of HALF_EVEN vs. any alternative rounding mode is immaterial here — there is no boundary case being masked.

### Details
- **Reused variables (`i_2`, `i_3`, `o_6`)**: This is a calibration series reusing molar absorptivity (ε) and path length (l) across three data points, and reusing the first absorbance (A1) as the denominator for the two ratio calculations (A2/A1, A3/A1). This is the expected, benign structure of a Beer-Lambert calibration curve — not evidence of tampering, since the reused values are constants/reference points by design, and the meta annotations (`point: 1/2/3`) confirm the intended domain semantics.
- **MathContext usage**: Every multiply/divide operation explicitly carries the `mc` argument (`i_1`), so the rounding convention is unambiguous and directly verifiable — and it is honored exactly at every step.
- **Scale preservation**: Each multiplication correctly accumulates scale rather than truncating it prematurely (e.g., o_5 at scale 5, o_6 at scale 7), preserving the full precision needed for the downstream ratio divisions. No intermediate truncation, downcasting to float/double, or arbitrary rescaling was observed anywhere in the lineage.
- **Ratio outputs (`o_13`, `o_14`)**: Both leaf outputs are exact integers (2 and 4), consistent with the underlying concentrations (c2/c1 = 2, c3/c1 = 4), confirming the arithmetic chain from raw concentration inputs through to the final ratios is internally consistent and unmodified.

**Conclusion**: No salami-slicing pattern, no premature scale reduction, no rounding-mode substitution, and no residual leakage are present. The graph is a clean, exact execution trace of a Beer-Lambert calibration computation. Risk is assessed as minimal, reflecting only the inherent, non-material uncertainty of auditing a single, non-cyclic, fully-reconciled calculation.