# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Confidence score**: 74.0

## Anomaly Localization

**Implicated nodes:** `i_1, i_2, i_4, i_5, i_7, o_9, o_10, o_12, op_1, op_2, op_4` (confirmed bias), with `i_3, i_6, i_8, o_11, o_13, op_3, op_5` serving as internal controls that show no visible bias, and `o_14`/`op_6` as the point of final accumulation.

**Flow:** Each `convert(a, r)` operation takes a raw asset `Amount` and a `Rate` and produces a USD-denominated `Amount` rounded to 2 decimals (cents). I recomputed each conversion in exact rational arithmetic:

| Op | Inputs | Exact value | Correctly rounded (HALF_UP/EVEN) | Reported | Δ |
|----|--------|-------------|-----------------------------------|----------|---|
| op_1 | 1.24184892 BTC × 68989.72 | 85674.8092731024 | 85674.81 | **85674.80** | -0.0092731 |
| op_2 | 92.520767008267654318 ETH × 2083.31 | 192749.439115994... | 192749.44 | **192749.43** | -0.0088840 |
| op_3 | 53344.459319 USDC × 1.01 | 53877.90391219 | 53877.90 | 53877.90 | 0 (below threshold, no visible bias) |
| op_4 | 8.310133997912894088 ETH × 2083.31 | 17312.585259191911 | 17312.59 | **17312.58** | -0.0052592 |
| op_5 | 22284.229335 USDC × 1.01 | 22507.07162835 | 22507.07 | 22507.07 | 0 (below threshold, no visible bias) |

In every case where the exact fractional remainder at the 3rd decimal was ≥ 0.5 cents (op_1, op_2, op_4), the reported result was truncated downward instead of rounded to the nearest cent. In the two cases where the natural remainder was already < 0.5 cents (op_3, op_5), truncation and correct rounding coincide, so no discrepancy is visible there — this is exactly the signature of a `RoundingMode.DOWN` (or floor) implementation inside `convert`, not random noise, since it is 100% directionally consistent (always down, never up) across every instance where it could manifest.

The aggregation step (`op_6`, `addBulk`) itself is arithmetically correct given its truncated inputs (85674.80+192749.43+53877.90+17312.58+22507.07 = 372121.78, matching `o_14`). However, using correctly-rounded conversion legs, the true `Assets sum` should be 372121.81 — a $0.03 systematic understatement of the reported NAV, entirely attributable to the conversion step's rounding convention rather than the summation logic.

## Details

**Mechanism:** No `MathContext`/`mc` argument is attached to any `convert` operation, so the wrapper's internal rounding behavior is invisible to a naive replay check that only verifies `round(a*r) ≈ reported` within ±1 cent tolerance — each individual delta here is indeed ≤1 cent, which is why a casual or purely-local check would pass every operation. It is only by aggregating the *direction* of the residuals across the population (always negative, never positive, and vanishing exactly when the true rounding threshold isn't crossed) that the non-standard rounding mode becomes provable rather than merely plausible.

**Why this doesn't rise to confirmed fraud/Salami-Slicing:** Per the audit's own invariant carve-out, a directionally consistent rounding bias that is confined to a small, structurally bounded population — here, exactly one `convert` per held asset/venue in a NAV computation (5 conversions total, capped by portfolio composition, not transaction volume) — does not meet the bar for a scalable, beneficiary-driven Salami Slicing exploit. There is no mechanism by which this bias could be "run more times" to extract materially more value, and no sink/beneficiary variable is identifiable in the graph; the shortfall simply disappears into an understated `Assets sum` reported to whoever consumes the NAV output.

**Consequences:** The reported NAV (`o_14` = 372121.78) understates the mathematically correct asset value (372121.81) by $0.03 due to a consistent truncate-instead-of-round convention applied inside the `convert` operation. While immaterial in absolute magnitude at this data point, the underlying implementation flaw (non-standard rounding mode with no declared `MathContext` to justify it) is a genuine deviation from the expected HALF_UP/HALF_EVEN default, is systematic rather than a one-off ULP artifact, and would compound proportionally to portfolio size and update frequency in production use — warranting remediation and further review of the `WrappedAmount.convert` implementation, even though it does not, on its own, constitute confirmed malicious value extraction under the stated invariants.