# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud** — Score: 5/100. No Calculation Omission, Lineage Disconnection/Context Substitution, or Double Counting detected; all multi-use variables and repeated-argument operations trace to legitimate Welford's-algorithm reuse patterns (shared scalar update factor applied to three independent accumulators; power-squaring of deviations; deviation values reused once for the accumulator branch and once for the running-mean branch).
2. **Precision and Scale Tampering** — Score: 5/100. A single consistent MathContext (precision=16, HALF_EVEN) is applied to every operation; recomputation of every step matches the reported value to the declared precision. The -3E-14 'Unclamped SSE' artifact is expected rounding noise from chained 16-sig-fig arithmetic on a perfectly collinear dataset (true SSE=0), and is explicitly, correctly clamped to 0 via max(SSE,0) — a deliberate, sound numerical safeguard, not drainage/truncation.
3. **Semantic Type and Context Cast** — Score: 3/100. Every variable's descriptor name is consistent with its arithmetic role across its full lineage (Sxx/Syy/Sxy accumulators, deviations, mean shifts, regression sum of squares, SSE); the final SSE formula (Syy − Sxy²/Sxx) matches both the naming and the actual subtraction performed. No relabeling, metadata stripping, or domain-context substitution found.

**Overall risk_score: 5** (reflects only the inherent, correctly-handled floating-point noise; no confirmed structural, arithmetic, or semantic violation).

#### Anomaly Localization (If Detected)
No material anomalies were localized. The only numerically notable artifact is:
- `o_39` (Syy, 77.07107858000000) vs `o_48` (Sxy²/Sxx, 77.07107858000003) → `o_49` (Unclamped SSE) = -3E-14 → clamped by `op_36` (`max(o_49, i_50)`) to `o_51` = 0.
This chain was fully hand-verified against exact rational arithmetic (e.g., `o_47` = Sxy² was independently recomputed to 17 significant digits and rounds correctly to the reported 16-sig-fig value under HALF_EVEN), confirming the discrepancy is accumulated rounding noise from the declared 16-digit MathContext across ~10 chained operations, not an injected or directional bias.

#### Details
**Topological check:** The graph implements a textbook Welford-style online computation of Sxx, Syy, Sxy across 3 observations, culminating in SSE = Syy − Sxy²/Sxx, clamped at 0. Every root (`i_2…i_10`, `i_27…i_30`, `i_50`) is consumed, every intermediate accumulator (`o_16`→`o_36` for Sxx, `o_19`→`o_39` for Syy, `o_22`→`o_42` for Sxy) is carried forward correctly into the final computation, and the two leaf variables not reaching the terminal result (`o_44`, `o_46`, the final running means of x and y) are standard auxiliary statistics that are not components of the SSE formula — their absence from the terminal result is not an omission.

**Precision check:** A single MathContext (`i_1`, precision 16, HALF_EVEN) is used uniformly by all 36 operations — no directional rounding modes, no mid-pipeline downcast to float/double/int, and no premature scale truncation were found. HALF_EVEN at 16 significant digits is an appropriate, non-coarsened precision for a statistical computation of this magnitude. The tiny negative unclamped SSE is the expected consequence of computing a near-zero residual (true SSE ≈ 0 for near-perfectly-collinear data) via several chained multiplications/divisions at finite precision; the pipeline explicitly anticipates and corrects this via the `max(SSE, 0)` floor, which is sound engineering practice rather than tampering, and the discrepancy does not compound across a scalable population (this is a single, bounded 3-point regression, not a repeatable salami-slicing opportunity).

**Semantic check:** Descriptor names and arithmetic are mutually consistent end-to-end: 'Initial Sxx/Syy/Sxy' roots feed additive accumulation into correspondingly named outputs, the regression sum of squares is computed exactly as Sxy²/Sxx (matching its name), and SSE is computed exactly as Syy − (Sxy²/Sxx) (matching its name). No variable is silently re-mapped to a different business meaning, and no identity/wrapper pass-through changes domain interpretation.

**Conclusion:** This is a clean, correctly engineered computation graph. The risk score reflects only the inherent and properly-mitigated floating-point noise common to finite-precision decimal arithmetic, not a confirmed exploit.