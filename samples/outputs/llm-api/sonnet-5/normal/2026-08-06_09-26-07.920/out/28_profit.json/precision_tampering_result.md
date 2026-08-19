# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Audit Summary

A full re-derivation of every operation in the DAG was performed using exact rational arithmetic and cross-checked against the documented global rounding policy (`rounding: DOWN`, truncation to the currency's native decimal precision — explicitly declared in `descriptor.meta`).

### Recomputation Results (all operations)

| Op | Formula | Exact Result | Reported | Δ | Note |
|---|---|---|---|---|---|
| op_1 | 0.00369452 WBTC × 1 (peg) | 0.00369452 | 0.00369452 | 0 | exact |
| op_2 | 0.00369452 × 109800 | 405.658296 | 405.658296 | 0 | exact |
| op_3 | 0.02735342 × 4650 | 127.193403 | 127.193403 | 0 | exact |
| op_4 | 299.589041 × 0.9998 | 299.5291231918 | 299.529123 | truncated (DOWN) | consistent w/ policy |
| op_5 | 0.16910563 × 4650 | 786.3411795 | 786.341179 | truncated at the .5 boundary (DOWN) | consistent w/ policy |
| op_6 | addBulk(o_22,o_23,i_11,o_24,o_25) | 1738.305562 | 1738.305562 | 0 | exact |
| op_7–op_12 | gas conversions | all exact | all exact | 0 | rate/date pairing verified correct for every leg |
| op_13 | addBulk(gas legs) | 111.433600 | 111.433600 | 0 | exact |
| op_14 | 1738.305562 × 0.03 | 52.14916686 | 52.149166 | truncated (DOWN) | consistent w/ policy |
| op_15 | 1738.305562 − 111.433600 | 1626.871962 | 1626.871962 | 0 | exact |
| op_16 | 1626.871962 − 52.149166 | 1574.722796 | 1574.722796 | 0 | exact |

### Rate/Date Pairing Verification
Each gas-fee conversion (`i_14…i_19`) was checked against its transaction date metadata and matched to the ETH/USDC rate variable (`i_5,i_6,i_7,i_8`) carrying the identical date:
- i_14 (2026-06-01) → i_5 (2026-06-01, 4480) ✓
- i_15 (2026-06-04) → i_7 (2026-06-04, 4390) ✓
- i_16 (2026-06-08) → i_8 (2026-06-08, 4310) ✓
- i_17 (2026-06-03) → i_6 (2026-06-03, 4420) ✓
- i_18, i_19 (2026-06-01) → i_5 (2026-06-01, 4480) ✓

All yield conversions (`i_9,i_10,i_12,i_13`) consistently use the period-end (2026-06-30) rate variables (`i_1,i_2,i_3`), a valuation convention that is applied uniformly across all yield legs, and is distinct from (but not inconsistent with) the historical-cost convention used for gas — a coherent design pattern, not a mixed/arbitrary application.

### Currency/Unit Consistency
- Source-asset native precision is preserved in every input Amount (WBTC/BTC 8dp, ETH 18dp, USDC/USDT 6dp) per the declared `currencyPrecisions` table.
- Every `convert(a,r)` call has `r.from` matching `a.currency`, and every result lands in the correct target currency (USDC) at 6-decimal precision.
- No float/double downcast, no mixed-scale addition (all `addBulk` operands are already in USDC at 6dp before summation).

### Rounding-Boundary Cases
Two truncations (op_5, op_14) drop a residual right at or just past the half-unit boundary (786.3411795→786.341179; 52.14916686→52.149166). Under the graph's explicitly declared DOWN/truncate policy these are the mathematically correct outputs — not silent switches to a favorable rounding mode. The maximum single-operation deviation from a HALF_UP/HALF_EVEN alternative is ≤1 unit at the 6th decimal (USDC), matching the ceiling described in EXPECTED_INVARIANTS for a legitimate documented rounding-convention choice, not tampering.

### Salami-Slicing Assessment
The truncated residuals (≤ ~$0.000001–0.0000009 per instance) are simply discarded, not redirected to any traceable sink or beneficiary variable — no operation collects these fractional remainders. The affected operation population (5 yield conversions, 6 gas conversions, 1 fee calc) is structurally bounded by portfolio composition, not by transaction/user volume, so it cannot scale to material aggregate leakage. This fails the EXPECTED_INVARIANTS bar for genuine salami slicing.

## Anomaly Localization (If Detected)
No confirmed precision/scale tampering was found. All operation outputs reconcile exactly against arbitrary-precision recomputation, subject only to the graph's own declared DOWN truncation policy, applied uniformly and non-preferentially.

## Details
Every `convert`, `addBulk`, `scale`, and `subtract` operation was independently recomputed from raw input values with full-precision rational arithmetic. All results match the recorded output values exactly, including at truncation boundaries, and all truncations are consistent with the pipeline's explicitly documented DOWN/truncate rounding invariant (a "balance-safety" design choice stated in the top-level descriptor metadata, not an undisclosed deviation). Rate-to-transaction date pairings and currency-to-rate `from`/`to` pairings were all verified correct with no cross-asset mismatches, no unit-scale confusion (e.g., no WEI/USDC mixing without conversion), and no evidence of type downcasting. The only debatable design choice — computing the 3% platform fee on gross yield (o_26) rather than post-gas net (o_35) — is a business-logic/ordering question explicit in the graph's operation wiring, not a precision or scale defect, and is applied transparently (both `o_34` and `o_35` are derived from the same `o_26`, and `o_36` correctly nets both deductions). No hidden beneficiary or accumulation sink for truncated residuals was identified. Overall the graph presents as an internally consistent, correctly computed pipeline under its own stated conventions.