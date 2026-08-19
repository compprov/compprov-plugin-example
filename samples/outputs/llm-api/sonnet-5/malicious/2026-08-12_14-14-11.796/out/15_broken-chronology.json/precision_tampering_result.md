# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

## Summary
This CPG models a 10-leg (5 CALL / 5 PUT) daily options payout calculation on ETH/USDC, followed by a bulk aggregation into a single 'Total payout in USDC' output. Each leg follows the same three-step pattern: (1) directional strike/spot subtraction appropriate to option type, (2) `max(x,0)` intrinsic-value floor, (3) multiply by position size, and finally an `addBulk` summation across all ten leg payouts.

## Recomputation (independent, arbitrary-precision)

| Leg | Type | K | Spot-side subtract | Intrinsic | Size | Payout (recomputed) | Reported |
|---|---|---|---|---|---|---|---|
|0|CALL|4630|4650-4630=20|20|6.8639|137.2780|137.2780|
|1|PUT|4710|4710-4650=60|60|2.8431|170.5860|170.5860|
|2|CALL|4670|4650-4670=-20|0|9.0434|0.0000|0.0000|
|3|PUT|4550|4550-4650=-100|0|2.8299|0.0000|0.0000|
|4|PUT|4730|4730-4650=80|80|7.8507|628.0560|628.0560|
|5|CALL|4630|4650-4630=20|20|4.4213|88.4260|88.4260|
|6|CALL|4710|4650-4710=-60|0|3.9270|0.0000|0.0000|
|7|PUT|4690|4690-4650=40|40|5.9841|239.3640|239.3640|
|8|PUT|4590|4590-4650=-60|0|8.2771|0.0000|0.0000|
|9|PUT|4550|4550-4650=-100|0|5.9155|0.0000|0.0000|

Sum of the ten leg payouts = 137.2780+170.5860+0+0+628.0560+88.4260+0+239.3640+0+0 = **1263.7100**, which exactly matches the reported `o_54` value of `1263.7100`.

## Directionality / Wiring Check
For every leg, the `subtract` operation argument order was verified against the option type:
- CALL legs (op_1, op_7, op_16, op_19) correctly compute `spot - strike` (i_3 as minuend where CALL).
- PUT legs (op_4, op_10, op_13, op_22, op_25, op_28) correctly compute `strike - spot` (i_3 as subtrahend where PUT).

All `max(x, i_2)` floor operations correctly reference the shared zero-floor variable `i_2`, and all `multiply` operations correctly pair each leg's intrinsic-value output with its own size input (i_5, i_10, i_15, i_20, i_25, i_30, i_35, i_40, i_45, i_50 respectively) — no cross-leg substitution or off-by-one wiring was found.

## Precision/Scale Analysis
- A single `MathContext` (`i_1`: precision=16, HALF_EVEN) is declared once and reused (legitimately) across all `subtract`/`multiply` operations that carry an explicit `mc` argument, satisfying the invariant that reuse of a declared MathContext is not itself a violation.
- All operands and intermediate results have at most 4-5 significant digits; the 16-digit precision context is never exercised near its rounding boundary, so no truncation, downcasting, or precision loss occurs anywhere in the pipeline.
- No unit mixing is present: strikes, spot, and intrinsic values are all denominated in USDC per ETH, and sizes are denominated in ETH; the multiplication intrinsic × size correctly yields USDC payout with no scale mismatch (e.g., no 18-decimal/6-decimal token confusion).
- The final `addBulk` operation includes all ten leg payout variables (`a=o_8`, `b0..b8 = o_13,o_18,o_23,o_28,o_33,o_38,o_43,o_48,o_53`) with none omitted, substituted, or duplicated, and the reported aggregate matches the exact rational sum to the last digit.

## Conclusion
Independent high-precision recomputation of every subtract, max, multiply, and the final addBulk operation reproduces the reported values exactly, with zero discrepancy (Δ=0) at every step, including the final aggregate. No evidence of salami-slicing, rounding-mode substitution, scale/unit mixing, or precision downcasting was found. The graph's structural reference data (reused i_2/i_3, single leaf o_54) reflects ordinary NAV/payout aggregation design, not tampering indicators.
