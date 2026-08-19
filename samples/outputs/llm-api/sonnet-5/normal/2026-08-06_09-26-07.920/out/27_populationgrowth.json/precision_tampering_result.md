# Summary
- **Verdict**: CLEAN
- **Confidence score**: 82.0

## Audit Summary

This CPG models exponential population growth (N(t) = N0 · e^(r·t)) across three checkpoints (t1=5h, t2=10h, t3=20h), using a declared `MathContext` (precision=16, HALF_EVEN) for `multiply` operations, and a separate `Exp_double` operation (op_2, op_5, op_8) for the exponential term that notably does **not** take an `mc` argument.

### Anomaly Localization (Investigated, Not Confirmed as Tampering)
Candidate flow examined: `i_3`(r) × `i_4/i_5/i_6`(t) → `o_7/o_10/o_13` (exponent) → `Exp_double` (op_2/op_5/op_8) → `o_8/o_11/o_14` (e^(rt)) → `i_2`(N0) × exp result → `o_9/o_12/o_15` (final population).

Key observation: `Exp_double` operations (op_2, op_5, op_8) carry **no `mc` argument**, unlike every `multiply` operation in the graph. This means the exponential term is computed outside the declared DECIMAL64 (precision-16) computation context — consistent with a double-precision floating-point implementation of `exp()`, which is common because `java.math.BigDecimal` has no native transcendental function support.

- `o_8` = 1.161834242728283 (16 sig figs) — matches true e^0.15 to available precision.
- `o_11` = 1.3498588075760032 (17 sig figs) — matches the well-known IEEE-754 double result for `Math.exp(0.3)`, differing from the exact mathematical value (1.349858807576003103...) only at the 17th significant digit (2 vs 1) — a single-ULP binary-to-decimal rounding artifact, not a directional truncation.
- `o_14` = 1.8221188003905089 (17 sig figs) — matches true e^0.6 to all shown digits.

Recomputing the downstream `multiply` steps that reapply the declared `mc` (precision 16, HALF_EVEN):
- `o_9` = i_2 × o_8 = 1000000 × 1.161834242728283 = 1161834.242728283 — exactly 16 sig figs, no rounding needed, matches reported value exactly.
- `o_12` = i_2 × o_11 = 1000000 × 1.3498588075760032 = 1349858.8075760032 (17 sig figs) → HALF_EVEN round to 16 sig figs (17th digit '2', rounds down) = 1349858.807576003 — matches reported `o_12` exactly.
- `o_15` = i_2 × o_14 = 1000000 × 1.8221188003905089 = 1822118.8003905089 (17 sig figs) → HALF_EVEN round to 16 sig figs (17th digit '9', rounds 16th digit 8→9) = 1822118.800390509 — matches reported `o_15` exactly.

All three exponent-multiplications (`o_7`, `o_10`, `o_13` = r×t) are exact and match reported values (0.15, 0.30, 0.60).

### Details
The pipeline does exhibit a genuine precision transition — `Exp_double` bypasses the declared DECIMAL64 MathContext and injects double-precision floating point (≈15-17 significant decimal digits, IEEE-754 binary rounding) into an otherwise BigDecimal/MathContext-governed pipeline. This matches the literal definition of "downcasting a precise decimal type to double mid-pipeline" named in the attack vector.

However, several factors argue against classifying this as tampering:
1. **Disclosure**: The operation is explicitly named `Exp_double` in `descriptor.name`, and its `arguments` list conspicuously omits `mc` — this is visible directly in the graph structure to any auditor, not concealed behind a misleading name or hidden argument substitution.
2. **Mathematical correctness**: Every one of the three `Exp_double` outputs matches the true mathematical value of e^x to the full precision available in double arithmetic, with no directional bias (differences are consistent with ordinary binary-to-decimal representation noise, not systematic truncation toward zero or toward any beneficiary).
3. **Correct re-rounding**: The subsequent `multiply` operations correctly reapply the declared MathContext (precision 16, HALF_EVEN) and round exactly as expected — 17-digit double outputs are properly truncated back to 16 significant figures using standard HALF_EVEN behavior (verified digit-by-digit for `o_12` and `o_15`), with no residual leakage or discrepancy versus exact rational recomputation.
4. **No scalable exploitation surface**: Per `EXPECTED_INVARIANTS`, genuine Salami Slicing requires a scalable operation population and a demonstrated accumulation sink. Here there are exactly three checkpoint computations, structurally bounded by the pipeline's design (not by transaction/user volume), and the residual double-precision noise (~1e-16 relative, ~1e-10 absolute against population counts in the millions) is many orders of magnitude below any plausible materiality threshold — and there is no identifiable beneficiary variable accumulating these differences.
5. **No asset conservation violation**: All final outputs (`o_9`, `o_12`, `o_15`) are internally consistent with `N0 · e^(rt)` computed under the graph's own declared rounding rules.

### Conclusion
The graph shows a disclosed, mathematically-consistent use of double-precision arithmetic for the transcendental `exp()` step, which is a common and defensible implementation choice given BigDecimal's lack of native exponential support. Downstream rounding correctly reapplies the declared MathContext. No hidden truncation, rounding-mode substitution, unit mixing, or beneficiary-directed skimming was found. This is a borderline design pattern worth flagging for documentation improvement (e.g., an explicit note on `Exp_double`'s precision boundary), but it does not meet the bar for confirmed Precision/Scale Tampering under the stated invariants.