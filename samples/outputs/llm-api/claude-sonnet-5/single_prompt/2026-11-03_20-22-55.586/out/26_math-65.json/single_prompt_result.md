# Summary
- **Verdict**: CLEAN
- **Risk score**: 6.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud** — Score: 5/100. No Calculation Omission, Lineage Disconnection/Context Substitution, or Double Counting detected; all roots and intermediates trace cleanly to the two terminal leaves (o_65, o_69).
2. **Precision and Scale Tampering** — Score: 6/100. A single uniform MathContext (precision=16, HALF_EVEN) is applied to every operation; recomputation matches reported values within single-ULP rounding noise only.
3. **Semantic Type and Context Cast** — Score: 3/100. Descriptor names/units are consistent end-to-end (point coordinates → offsets → squared distance → distance → residual → weighted squared residual → chi-square → RMS/parameter error scale); no re-labeling or metadata stripping detected.

Overall verdict: Clean graph. No confirmed attack-vector violation found; only ordinary, bounded rounding variance consistent with a 16-digit HALF_EVEN MathContext applied uniformly throughout.

#### Anomaly Localization (If Detected)
None material. For completeness, the following was checked and found benign:
- **Multi-use variables** (i_2, i_3, i_44, i_47, o_11/o_19/o_27/o_35/o_43, o_46, o_63): each reuse corresponds to a shared parameter (fitted centre, n, weight) feeding independent per-point branches, or a value (distance, mean radius, chi-square) consumed once for the running sum/mean and once for an independent downstream statistic (residual, RMS vs. parameter-error-scale branch). None of these converge the same contribution into the same total twice.
- **Repeated-argument operations** (op_3, op_4, op_9, op_10, op_15, op_16, op_21, op_22, op_27, op_28, op_34, op_37, op_40, op_43, op_46): all are `multiply(x, x)` squaring operations (dx², dy², residual²), which is the explicitly permitted "power" pattern, not a disguised double count.
- **Terminal leaves** o_65 (RMS) and o_69 (parameter error scale): both are legitimate independent statistics derived from the same chi-square value (o_63) via two different divisors (n vs. n−p); this is standard statistical practice, not duplication.
- **Roots** i_2/i_3 (fitted centre) are supplied externally with no computed counterpart elsewhere in the graph — exempt as genuine inputs, not substitutions.

#### Details
**Provenance:** A full forward trace from every root (i_1…i_66) shows each is consumed as expected (coordinates feed their own point's offset calculation; centre, n, weight, and p are shared across the appropriate independent branches), and every intermediate output is consumed exactly where the pipeline's naming implies, terminating cleanly at o_65 and o_69. No root is silently substituted for a computed sibling, and no computed contributor is dropped before reaching a terminal output.

**Precision:** Recomputing each step with exact rational arithmetic and comparing to the reported BigDecimal values shows agreement to the full 16-significant-digit precision declared in i_1, with only a 1-unit-in-the-last-place drift visible at o_63 (computed running total 6.253505411809803 vs. reported 6.253505411809802) — this is attributable to intermediate rounding at each addBulk step under a HALF_EVEN context and is within the mathematical ceiling for rounding-convention noise, not a scale-tampering or salami-slicing pattern (no directional bias toward a beneficiary is evident, and the discrepancy does not recur in a scalable, high-frequency operation population). All MathContext arguments are identical and appropriate (HALF_EVEN, precision 16) across every one of the 53 operations — no coarser or directional-mode outlier contexts were found.

**Semantics:** Business labels (Point x/y → offset → dx²/dy² → squared distance → distance → residual → squared residual → weighted squared residual → chi-square → mean weighted squared residual/RMS, and chi-square → dof-normalized chi-square → parameter error scale) are mutually consistent at every step; the weight variable (i_47, value 2.0) is applied uniformly and only once per observation, consistent with a weighted least-squares/chi-square convention. No identity-cast or relabeling was found that changes a variable's declared business meaning while preserving its numeric value.

**Residual note (not a finding):** Degrees of freedom is computed as n − p with p = 2 (centre-only), while the radius is derived as the mean distance rather than treated as an independently fitted third parameter; this is a defensible modeling convention for this class of circle-fit algorithm (geometric fit with radius as a post-hoc statistic) and is not contradicted by any metadata in the graph, so it is noted only for completeness and not scored as a violation.