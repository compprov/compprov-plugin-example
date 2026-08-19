# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

## Summary
This CPG models a 6-year annuity/compound-interest projection: each year computes interest on the current balance (`multiply`), adds it to get post-interest balance (`add`), then adds the annual contribution (`add`). A final pair of operations computes total contributions (`i_2 * i_23`) and total growth (`o_22 - o_24`).

## Recomputation (arbitrary-precision, cross-checked against declared MathContext i_1: precision=16, HALF_EVEN)

All 20 operations were re-derived by hand using exact rational arithmetic and BigDecimal scale-propagation rules (multiply scale = scaleA+scaleB; add scale = max(scaleA,scaleB); MathContext precision only kicks in when the unscaled significant-digit count exceeds 16):

- Years 1–5 (`op_1`…`op_15`, producing o_5–o_19): every scale and numeric value matches the exact rational result of chained `0*0.07`, `6000*0.07` progressions — o_5=0.00 … o_19=34504.4340600000. No deviation, no digit count ever exceeds the 16-significant-digit MathContext bound, so no rounding is expected or observed.
- `op_16` (o_20 = o_19*i_3): exact product is 2415.310384200000, which lands at exactly 16 significant digits — no rounding required. Matches reported value exactly.
- `op_17` (o_21 = o_19+o_20): the exact scale-12 sum is 36919.744444200000, which has 17 significant digits — one more than the MathContext's precision=16. Applying HALF_EVEN rounding to 16 significant digits correctly drops the trailing zero, yielding 36919.74444420000 (scale 11) — which is exactly what is reported. This initially looked like a scale/off-by-one anomaly but is fully explained by the declared MathContext being applied as designed.
- `op_18` (o_22 = o_21+i_2 = 42919.74444420000): sum has exactly 16 significant digits, no rounding needed, matches reported "Ending balance".
- `op_19` (o_24 = i_2*i_23 = 6000.00*6 = 36000.00): matches exactly.
- `op_20` (o_25 = o_22-o_24 = 6919.74444420000): matches exactly, and independently equals the sum of all six yearly interest amounts (o_5+o_8+o_11+o_14+o_17+o_20 = 6919.7444442), confirming conservation between the per-year interest ledger and the aggregate "Total growth" output.

## Structural Reference Cross-Check
- Reused variables (i_2, i_3, i_4, o_7, o_10, o_13, o_16, o_19) are all legitimate running-balance/contribution/rate nodes consumed by both the interest-multiply and the subsequent add in the standard compounding chain — expected topology for this computation, not evidence of duplication/substitution abuse.
- No leaf-name collisions were found, and the single leaf (o_25) is the natural terminal aggregate.
- No MathContext substitution, no alternate rounding mode, no unit/scale mixing (single currency, single precision context throughout) was found anywhere in the 20 operations.

## Details
No Precision and Scale Tampering pattern is present:
- No forced premature truncation prior to aggregation — the only scale reduction observed (o_21) is a mathematically correct, MathContext-driven, precision-bound rounding event, not an arbitrary truncation, and it does not recur as a directional, accumulating bias (there is exactly one 1-digit rounding event in the entire trace, and it is HALF_EVEN, the declared and expected mode).
- No evidence of Salami Slicing: there is no repeated, direction-biased skim across a scalable operation population, and the one rounding event that did occur does not divert value to any side channel — it is simply absorbed by the MathContext precision limit exactly as BigDecimal semantics dictate.
- No mixed-unit or type-downcast issues: all values remain `java.math.BigDecimal` with a single MathContext used consistently across all 20 operations; no float/double/integer downcast appears anywhere.
- Asset conservation holds exactly: Ending balance (o_22) − Total contributions (o_24) = Total growth (o_25) = sum of the six discrete yearly interest figures, with zero residual leakage.

Given that every numeric value across all 25 variables reconciles exactly against arbitrary-precision recomputation, and the single apparent scale anomaly is fully and precisely explained by the graph's own declared MathContext (precision 16, HALF_EVEN) rather than by an undisclosed or inconsistent rounding convention, this graph shows no signs of the targeted Precision and Scale Tampering vector.