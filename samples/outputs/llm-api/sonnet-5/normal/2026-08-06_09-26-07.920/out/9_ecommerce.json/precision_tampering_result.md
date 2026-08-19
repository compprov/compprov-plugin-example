# Summary
- **Verdict**: CLEAN
- **Confidence score**: 91.0

## Anomaly Localization (If Detected)
No variable IDs or operation IDs were found to violate the EXPECTED_INVARIANTS for Precision and Scale Tampering. Every arithmetic step was independently recomputed with exact rational arithmetic and cross-checked against both (a) the declared MathContext (precision=16, HALF_EVEN) and (b) BigDecimal's standard scale-propagation rules for `multiply`, `add`, and `subtract`.

## Details

**Recomputation trace (all operations use `mc = i_1`, precision 16, HALF_EVEN):**

- op_1: 29.99 × 3 = 89.97 → o_5 = 89.97 ✅ (scale 2+0=2, matches)
- op_2: 14.50 × 2 = 29.00 → o_8 = 29.00 ✅ (scale 2+0=2, matches)
- op_3: 59.00 × 1 = 59.00 → o_11 = 59.00 ✅ (scale 2+0=2, matches)
- op_4: 9.99 × 5 = 49.95 → o_14 = 49.95 ✅ (scale 2+0=2, matches)
- op_5: 89.97+29.00+59.00+49.95 = 227.92 → o_15 = 227.92 ✅
- op_6: 1 − 0.10 = 0.90 → o_17 = 0.90 ✅
- op_7: 227.92 × 0.90 = 205.1280 → o_18 = 205.1280 ✅ (scale 2+2=4, matches)
- op_8: 1 − 0.05 = 0.95 → o_20 = 0.95 ✅
- op_9: 205.1280 × 0.95 = 194.871600 → o_21 = 194.871600 ✅ (scale 4+2=6, matches)
- op_10: 194.871600 + 12.50 = 207.371600 → o_23 = 207.371600 ✅ (scale max(6,2)=6, matches)
- op_11: 207.371600 × 0.08 = 16.58972800 → o_25 = 16.58972800 ✅ (scale 6+2=8, matches)
- op_12: 207.371600 + 16.58972800 = 223.96132800 → o_26 = 223.96132800 ✅ (scale max(6,8)=8, matches)

Every reported value is an **exact** match to the mathematically correct result — no rounding, truncation, or context-driven adjustment occurred anywhere in the pipeline, because every intermediate product/sum has far fewer significant digits than the declared MathContext precision of 16. Since no rounding event ever actually fires, the choice of rounding mode (HALF_EVEN) is never exercised and cannot be a vector for salami-slicing or directional bias in this trace.

Scale propagation also matches BigDecimal's documented preferred-scale semantics exactly at every step (multiply → sum of scales; add/subtract → max of scales), which rules out silent downcasting, forced scale truncation, or unit-mixing. The two variables flagged by the structural pass as multiply-consumed (`i_2`, the constant `1.0` reused for two independent discount-multiplier subtractions, and `o_23`, the taxable amount reused for both the tax calculation and the final total) are both legitimate, non-destructive fan-outs — the same value is read twice, not silently mutated or re-scaled between uses.

**Business-logic note (not a precision defect):** shipping fee is added to the taxed base (i.e., shipping appears taxable in this jurisdiction model). This is a policy/domain decision reflected consistently in the graph's `addBulk`/`add`/`multiply` sequence, not a scale or rounding manipulation, and no metadata or invariant in scope contradicts it.

**Conclusion:** No boundary-sized discrepancies were found at all (Δ = 0 at every step), so there is no rounding-convention ambiguity to adjudicate, no evidence of residual-value leakage, and no scalable population of skimmed operations. The graph is internally consistent and arithmetically exact end-to-end.