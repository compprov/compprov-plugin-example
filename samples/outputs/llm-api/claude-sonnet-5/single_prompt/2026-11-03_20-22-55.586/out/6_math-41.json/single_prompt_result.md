# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 93.0

#### Per-Vector Verdict

1. **Topological & Provenance Fraud — Lineage Disconnection and Context Substitution**: CONFIRMED — the legitimate denominator basis (`o_18`, Σw over the 5 real observations) is bypassed in favor of an inflated substitute (`o_59`, Σw over 22 weights, 17 of which have no corresponding observations) that reaches the terminal result. Score: 93.
2. **Precision and Scale Tampering**: CLEAN — a single consistent MathContext (precision 16, HALF_EVEN) is used for every operation; recomputation of every multiply/add/divide/subtract step matches the reported values within expected rounding noise (e.g., the ~-2E-15 "Sum of weighted deviations" sanity check and the 4E-30 propagation through the correction term are textbook floating residue, not tampering). Score: 5.
3. **Semantic Type and Context Cast**: CONFIRMED (same root cause as Finding 1, independently actionable) — 17 "Weight" root variables (`i_42`–`i_58`, indices [5]–[21]) are introduced and consumed by `op_31` as if they belonged to the same "data segment" population as the 5 actual Observation/Weight pairs (indices [0]–[4]), despite no `[5] Observation` … `[21] Observation` ever existing in the graph. They are semantically foreign entities silently blended into "Sum of weights (variance)." Score: 85.

**Overall risk_score = 93** (maximum across vectors).

---

#### Anomaly Localization — Topological & Provenance Fraud / Semantic Type and Context Cast

- Root variables: `i_42, i_43, i_44, i_45, i_46, i_47, i_48, i_49, i_50, i_51, i_52, i_53, i_54, i_55, i_56, i_57, i_58` (labeled "[5] Weight" through "[21] Weight") — 17 weights with **no corresponding Observation variables anywhere in the graph** (only indices 0–4 have paired `[n] Observation`/`[n] Weight` roots that are actually used to build `o_12`–`o_16`, the weighted-observation chain).
- `op_31` (`addBulk`) sums the 5 legitimate weights (`i_3, i_5, i_7, i_9, i_11`) together with the 17 phantom weights into `o_59` ("Sum of weights (variance)" = 20.3), as opposed to `o_18` ("Sum of weights (mean)" = 4.7), which was built from — and only from — the 5 weights that actually participate in the weighted-mean/deviation/squared-deviation chain (`op_1`–`op_30`, i.e., `o_12…o_41`).
- `op_33` divides `o_60` (squared sum of weighted deviations) by `o_59` to build the correction term `o_61` — using the inflated Σw.
- `op_35` computes `o_64` = `o_59 − i_63` = 20.3 − 1 = **19.3** ("Effective degrees of freedom"), instead of the value consistent with the actual sampled data, `o_18 − 1` = 4.7 − 1 = **3.7**.
- `op_36` divides `o_62` (Corrected sum of weighted squared deviations = 6.158468085106383, which is derived *exclusively* from the real 5-point sample) by `o_64` = 19.3, producing the reported "Weighted sample variance" `o_65` = **0.3190916106272737**.
- Recomputing with the internally consistent denominator (`o_18 − 1` = 3.7) yields **1.6644508...**, i.e. the true weighted sample variance is roughly **5.2× larger** than what is reported.

Flow of the attack: real sample (5 obs/weights) → correct numerator (`o_40`/`o_62` ≈ 6.1585) is computed faithfully → but at the final division, the denominator silently switches from the sample's own Σw (`o_18`=4.7) to an unrelated, inflated Σw (`o_59`=20.3) assembled from 17 extraneous root weights that never fed any observation anywhere in the computation → terminal output `o_65` is a severely understated variance.

#### Details

Every individual operation in this graph replays exactly against its declared MathContext (`i_1`, precision 16, HALF_EVEN) — this is why naive type/formula validation or step-by-step unit tests would pass. The attack is purely topological: the pipeline silently substitutes the quantity that legitimately belongs in the denominator (`o_18`, the Σw consistent with the 5 actual data points used to build the numerator) with an unrelated, enlarged Σw (`o_59`) built by folding in 17 weights (`i_42`–`i_58`) for which **no observation data exists in the graph at all**. Naming them "[5] Weight" … "[21] Weight" gives them the surface appearance of belonging to the same indexed data segment as `[0]`–`[4]`, but there is no `[5] Observation` … `[21] Observation` anywhere to justify their presence — the graph itself contains no reconciling metadata or transformation that would legitimize this. Per the audit invariants, a declared formula (`(a-b)mc`) and a matching MathContext are not authorization for using the wrong input; the substitution of `o_59` for `o_18` is exactly the Lineage Disconnection / Context Substitution pattern, compounded by a Semantic Context Cast (unrelated "weights" consumed as though equivalent reliability weights for this segment).

Consequence: the reported "Weighted sample variance" (0.319) is roughly 5.2× smaller than the value mathematically implied by the pipeline's own numerator and its own correctly-scoped weight sum (1.664). In a statistics/risk pipeline, this kind of silent variance deflation can materially understate volatility, risk, or confidence-interval width to downstream consumers (e.g., risk models, VaR, confidence bounds) while every single arithmetic step still "proves" itself correct in isolation — precisely the scenario this audit discipline is designed to catch.