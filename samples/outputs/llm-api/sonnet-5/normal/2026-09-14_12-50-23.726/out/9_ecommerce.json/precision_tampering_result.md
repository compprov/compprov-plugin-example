# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

## Precision and Scale Tampering Audit

### Anomaly Localization (If Detected)
None identified. No variable or operation exhibits scale truncation, non-conforming rounding, unit downcasting, or residual-value leakage.

### Details
A full re-derivation of every arithmetic step in the pipeline was performed using exact rational arithmetic and cross-checked against BigDecimal scale-propagation rules (scale(a·b) = scale(a)+scale(b); scale(a±b) = max(scale(a), scale(b))), then compared to the declared MathContext (`precision=16, HALF_EVEN`, `i_1`).

- op_1–op_4 (line-item multiplications): all products (89.97, 29.00, 59.00, 49.95) are exact and match reported outputs with correctly propagated scale.
- op_5 (addBulk subtotal): 89.97+29.00+59.00+49.95 = 227.92, exact match.
- op_6/op_8 (discount multiplier derivation via `1 - rate`): 1‑0.10=0.90 and 1‑0.05=0.95, both exact; `i_2` (constant 1.0) is legitimately reused across both subtractions, which explains its appearance in the multi-consumption structural list — this is benign shared-constant reuse, not tampering.
- op_7 (SAVE10 application): 227.92 × 0.90 = 205.1280, exact, scale 4 as expected from BigDecimal multiplication semantics.
- op_9 (LOYALTY5 application): 205.1280 × 0.95 = 194.871600, exact, scale 6.
- op_10 (add shipping): 194.871600 + 12.50 = 207.371600, exact, scale 6 (max of 6 and 2).
- op_11 (tax on taxable amount, which includes shipping per the `taxable=true, jurisdiction=California` metadata on `i_22`): 207.371600 × 0.08 = 16.58972800, exact, scale 8.
- op_12 (final total): 207.371600 + 16.58972800 = 223.96132800, exact, scale 8 (max of 6 and 8).

All magnitudes involved stay well under the 16-significant-digit precision ceiling of the declared MathContext, so HALF_EVEN rounding is never actually invoked anywhere in the graph — every result is the exact mathematical product/sum with correctly propagated decimal scale. There is no premature truncation, no scale-forcing operation, no downcast to float/double/int, and no divergence between the applied MathContext and the computed values.

`o_23` (Taxable amount) is legitimately consumed twice (feeding both the tax calculation `op_11` and the final summation `op_12`), which is expected fan-out for a value used in two downstream formulas, not a leakage or double-counting pattern — the final total correctly incorporates the pre-tax amount plus tax exactly once each.

No Salami Slicing pattern is present: there is no repeated directional bias across a scalable population of operations, no fractional skim, and no divergence between exact and reported values at any step (Δ = 0 for every operation checked). Asset conservation holds exactly through the full discount → shipping → tax → total pipeline.

**Conclusion:** This is a clean, arithmetically exact computation graph with respect to the Precision and Scale Tampering vector. No material or structural evidence of tampering was found.