# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

#### Anomaly Localization (If Detected)
No violation of the EXPECTED_INVARIANTS was found. All eight operations (op_1–op_8) were recomputed exactly against exact rational arithmetic and cross-checked against the declared `MathContext` (`i_1`: precision=16, HALF_EVEN).

| Op | Formula | Inputs | Exact Result | Reported Result | Δ |
|----|---------|--------|--------------|------------------|---|
| op_1 | i_2 * i_4 | 15000 × 0.00001 | 0.15000 | 0.15000 | 0 |
| op_2 | o_5 * i_3 | 0.15000 × 1.00 | 0.1500000 | 0.1500000 | 0 |
| op_3 | i_2 * i_7 | 15000 × 0.00002 | 0.30000 | 0.30000 | 0 |
| op_4 | o_8 * i_3 | 0.30000 × 1.00 | 0.3000000 | 0.3000000 | 0 |
| op_5 | i_2 * i_10 | 15000 × 0.00004 | 0.60000 | 0.60000 | 0 |
| op_6 | o_11 * i_3 | 0.60000 × 1.00 | 0.6000000 | 0.6000000 | 0 |
| op_7 | o_9 / o_6 | 0.3000000 / 0.1500000 | 2 | 2 | 0 |
| op_8 | o_12 / o_6 | 0.6000000 / 0.1500000 | 4 | 4 | 0 |

All deltas are zero — every result is an exact BigDecimal computation with no rounding required at all (multiplication scales follow the standard `scale(a)+scale(b)` rule, and the two divisions resolve to exact integers well within the declared 16-digit precision). There is no residual leakage, no truncation, and no need to even invoke the HALF_EVEN rounding mode in practice, since none of these results required rounding.

The variables flagged in the structural reference data as "consumed by more than one operation" (`i_2`, `i_3`, `o_6`) are legitimate, semantically necessary reuses in a Beer–Lambert calibration series: `i_2` (molar absorptivity ε) and `i_3` (path length l) are constants shared across all three calibration points by the physics of the model, and `o_6` (Absorbance A1) is the correct reference denominator for both ratio calculations (A2/A1, A3/A1) that the pipeline is explicitly designed to compute. This is expected design, not disguised tampering — there is no scale mixing, no unit inconsistency (ε in L/(mol·cm), l in cm, c in mol/L are dimensionally consistent throughout), and no downcasting to lower-precision types anywhere in the graph.

#### Details
No mechanism for precision/scale tampering is present. The `MathContext` (`i_1`) is threaded consistently and explicitly into every multiply/divide operation, the rounding mode (HALF_EVEN) is the sanctioned default, and no operation actually required rounding at all given the round, exact test values chosen for this calibration series (15000, 1.00, 1e-5, 2e-5, 4e-5). Because no rounding was invoked, there is no possible salami-slicing vector, no sub-cent skimming, and no accumulation toward any sink — the ratios (A2/A1 = 2, A3/A1 = 4) reproduce the expected Beer–Lambert linearity (c2/c1 = 2, c3/c1 = 4) exactly, confirming both mathematical correctness and internal consistency of the pipeline. The graph shows a clean, physically well-formed calibration computation with no evidence of adversarial manipulation of scale, precision, or rounding convention.