# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

#### Anomaly Localization (If Detected)
None. Full recomputation of every operation (op_1 through op_9) against exact rational arithmetic produced zero discrepancy (Δ = 0) with the reported values at every node:

- op_1: 100 × 42.50 = 4250.00 → matches o_4
- op_2: 150 × 38.25 = 5737.50 → matches o_7
- op_3: 75 × 51.00 = 3825.00 → matches o_10
- op_4: 100+150+75 = 325 → matches o_11
- op_5: 4250.00+5737.50+3825.00 = 13812.50 → matches o_12
- op_6: 13812.50 / 325 = 42.50 (exact, no remainder) → matches o_13
- op_7: 120 × 47.75 = 5730.00 → matches o_16
- op_8: 120 × 42.50 = 5100.00 → matches o_17
- op_9: 5730.00 − 5100.00 = 630.00 → matches o_18

#### Details
Every arithmetic operation in this graph carries an explicit `MathContext` (`i_1`, precision 16, HALF_EVEN) that is consistently propagated to all nine operations — there is no divergence in rounding-mode application, no silent default substitution, and no operation that omits `mc` while another of the same formula includes it. All underlying quantities (share counts, prices, proceeds) are decimal values whose exact products, sums, and the single division (13812.50 / 325 = 42.5 exactly) resolve with no remainder or fractional residue at all, meaning the 16-digit precision context is never actually stressed — there is no boundary case where a rounding-mode choice could have produced a different result, let alone one that leaks value.

The reused variables flagged by structural traversal (`i_2`, `i_5`, `i_8` feeding both per-lot multiply and the total-shares addBulk; `i_14` feeding both the sale-proceeds multiply and the cost-basis-sold multiply) are legitimate, expected reuse patterns for a weighted-average cost-basis calculation — each value is consumed once for a per-lot cost/total computation and once for a sale-side computation, with no unit mismatch, no unexplained rescaling, and no downcast to float/double/int anywhere in the wrapper chain (`WrappedBigDecimal` throughout).

No evidence of salami slicing, truncation-based skimming, non-standard rounding modes (ROUND_DOWN/FLOOR), or unit/scale mixing (e.g., differing decimal-place conventions) was found. The single leaf output (`o_18`, Realized gain/loss) is arithmetically consistent with proceeds minus cost basis, and cost basis is consistent with the weighted-average calculation across all three lots. Asset conservation holds exactly at every intermediate step (lot costs sum to total cost; total cost divided by total shares yields the weighted average; shares sold times weighted average yields cost basis sold). No boundary-sized discrepancies, isolated 1-unit deviations, or directional bias toward any sink were detected.

Conclusion: the graph is internally consistent, precision-context usage is uniform and correctly bounded, and no Precision/Scale Tampering pattern as defined is present.