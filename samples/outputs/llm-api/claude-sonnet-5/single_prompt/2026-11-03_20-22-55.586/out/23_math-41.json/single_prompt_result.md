# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud** — Score: 5/100. No calculation omission, lineage disconnection, or harmful double counting detected. A benign redundant computation of Σw (o_18 vs o_42) exists but each copy feeds a distinct, non-overlapping role in the final formula.
2. **Precision and Scale Tampering** — Score: 5/100. Full manual re-derivation of every operation (multiplications, subtractions, addBulk sums, and divisions) under the declared MathContext (precision 16, HALF_EVEN) reproduces every reported value to the represented digit, including the sub-10^-30 correction term. No truncation, salami-slicing, or context substitution detected.
3. **Semantic Type and Context Cast** — Score: 5/100. All `descriptor.name` labels are consistent with their arithmetic role (Observation → Weighted observation → Deviation → Squared/Weighted-squared deviation → Sum/Correction → Variance), and the `i_46` 'Bias correction, 1' constant is used exactly as labeled (Σw − 1). No silent re-mapping of business meaning is present.

#### Anomaly Localization (If Detected)
No material anomaly was found. The only noteworthy structural feature is the parallel, independent computation of the sum of weights twice:
- `op_7` (i_3,i_5,i_7,i_9,i_11 → o_18, "Sum of weights (mean)"), consumed solely by `op_8` (weighted-mean divisor).
- `op_31` (same inputs → o_42, "Sum of weights (variance)"), consumed by `op_33` (correction-term divisor) and `op_35` (degrees-of-freedom subtraction).

Both o_18 and o_42 evaluate to the identical value (4.7) and each is consumed exactly once per its distinct downstream purpose (mean normalization vs. variance correction/denominator), so this is parallel re-derivation, not a double-counted contribution to the terminal result o_48.

#### Details
A full re-computation of the DAG under rational arithmetic, matching the declared `MathContext` (precision 16, HALF_EVEN) at every step, reproduces every intermediate and terminal value exactly as reported:
- Weighted mean `o_19` = 60.11/4.7 = 12.78936170212766 ✔
- Per-observation deviations (`o_20,o_24,o_28,o_32,o_36`) ✔
- Squared and weighted-squared deviations (`o_21..o_39`) ✔ (verified e.g. o_21=0.08373019465821663, o_22=0.1255952919873249)
- Sum of weighted squared deviations `o_40` = 6.158468085106383 ✔ (hand-summed from o_22,o_26,o_30,o_34,o_38)
- Sum of weighted deviations `o_41` = -2E-15 (expected ~0 residual from finite precision) ✔
- Correction term chain `o_43→o_44` (squared residual / Σw) ✔, negligible magnitude (~8.5E-31), correctly leaves `o_45` numerically unchanged from `o_40`
- Degrees of freedom `o_47` = 4.7-1 = 3.7 ✔
- Final weighted variance `o_48` = 6.158468085106383/3.7 = 1.664450833812536 ✔ (confirmed via manual long division to 17 digits, matching HALF_EVEN rounding at position 16)

The correction-term sub-graph (`o_41→o_43→o_44→o_45`) is a legitimate numerical-stability technique (compensating the residual Σw(x−x̄) that is not exactly zero due to finite-precision mean subtraction), not an injected bias or hidden deduction — its magnitude is consistent with rounding noise, not with any material skim, and it does not benefit any identifiable sink.

The use of `Σw − 1` (rather than an effective-sample-size correction) as the denominator is a documented, explicitly labeled statistical convention ("Effective degrees of freedom, Σw − 1") rather than a covert semantic substitution — the arithmetic performing this exact subtraction is present and matches the label.

No root variable is substituted for a computed counterpart, no mandatory contributor is excluded from the terminal leaf `o_48`, no variable's business meaning is silently remapped while retaining its type, and no rounding-mode or precision deviation diverges from the single, consistently-applied DECIMAL64-style context. The graph is assessed as a faithfully and carefully implemented weighted-sample-variance pipeline with no confirmed attack-vector violation; residual risk score reflects only the benign structural redundancy noted above.