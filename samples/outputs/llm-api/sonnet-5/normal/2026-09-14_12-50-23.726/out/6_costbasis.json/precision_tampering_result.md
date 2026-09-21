# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

#### Anomaly Localization (If Detected)
No precision or scale tampering was detected. All nine operations (op_1 through op_9) were recomputed using exact rational arithmetic and cross-checked against the reported output values:

- op_1: 100 × 42.50 = 4250.00 ✅ (matches o_4)
- op_2: 150 × 38.25 = 5737.50 ✅ (matches o_7)
- op_3: 75 × 51.00 = 3825.00 ✅ (matches o_10)
- op_4: 100 + 150 + 75 = 325 ✅ (matches o_11)
- op_5: 4250.00 + 5737.50 + 3825.00 = 13812.50 ✅ (matches o_12)
- op_6: 13812.50 / 325 = 42.5 exactly ✅ (matches o_13)
- op_7: 120 × 47.75 = 5730.00 ✅ (matches o_16)
- op_8: 120 × 42.50 = 5100.00 ✅ (matches o_17)
- op_9: 5730.00 − 5100.00 = 630.00 ✅ (matches o_18)

Every arithmetic step resolves to an exact decimal value with no repeating fraction or rounding boundary encountered, so the declared MathContext (precision=16, HALF_EVEN) never actually needs to engage its rounding behavior anywhere in this trace.

#### Details
All multiply, addBulk, divide, and subtract operations explicitly carry the `mc` (MathContext) argument bound to `i_1` (precision 16, HALF_EVEN), satisfying the invariant that rounding convention be explicit and consistent. Because every input value in this lot-based weighted-average cost basis calculation is a "clean" decimal (2 decimal places on prices/costs, integer share counts) and the total share count (325) divides the total cost (13812.50) evenly, no operation in the graph produces a repeating or truncated fractional result. There is therefore no rounding-mode ambiguity to exploit, no residual value leakage from premature scale reduction, and no evidence of salami-slicing (no operation population that could scale with transaction volume shows a directional skim, and no fractional residue is dropped or redirected anywhere).

The apparent coincidence that the weighted-average cost per share (42.50) numerically equals Lot 1's price per share is a mathematical artifact of the specific input quantities/prices chosen (100×42.50 + 150×38.25 + 75×51.00 = 13812.50, and 13812.50/325 = 42.50 exactly) — it is verifiable via independent recomputation and does not indicate manipulation of the graph structure or argument wiring. All argument bindings (a, b, b0, b1, mc) correctly reference their expected source variables, and result IDs are consistently and uniquely assigned to plausible successor variables. No downcasting to lower-precision types, no unit conversions, and no MathContext substitution across operations were observed.

Conclusion: this CPG shows a clean, internally consistent, and mathematically exact provenance trace with no material or subtle precision/scale tampering indicators.