# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 82.0

## Anomaly Localization

**Implicated nodes:** `i_14`–`i_19` (gas cost inputs), `op_1`–`op_6` (per-leg gas conversions), `op_7` (gas aggregation), **`o_27`** ("Total gas fees in USDC" = 111.433600 USDC), `op_13` (gross-yield aggregation → `o_33`), `op_14` (platform fee), `op_15` (final subtraction → `o_35`, "Net profit in USDC").

**Flow of the defect:**
1. Six separate gas-cost legs (`i_14…i_19`, all denominated in ETH) are individually and *correctly* converted to USDC via `convert()` at the historical (deposit-date) rate for each leg (`op_1`–`op_6` → `o_21…o_26`). Every one of these conversions is arithmetically exact.
2. These six converted gas costs are then correctly summed by `addBulk` (`op_7`) into `o_27` — "Total gas fees in USDC" = **111.433600 USDC**, explicitly tagged with meta `basis: realized at deposit moment`.
3. Separately, the five yield legs (`o_29, o_30, i_11, o_31, o_32`) are summed by `addBulk` (`op_13`) into `o_33` — "Gross yield in USDC". **`o_27` is not among the arguments of `op_13`.**
4. `op_14` computes a 3% platform fee directly off `o_33` (gross yield, not net of gas).
5. `op_15` computes "Net profit" as `o_33 - o_34` (gross yield minus platform fee only).
6. Per the structural reference data, `o_27` is a **leaf node** — it is never consumed by any downstream operation. The 111.433600 USDC of computed, attributed gas expenditure is silently discarded and never reduces the reported "Net profit."

Net effect: the reported `o_35` = 1686.156396 USDC overstates the true economic net profit by the full 111.433600 USDC of gas costs that were computed but never netted out. The mathematically consistent net profit (gross yield − platform fee − gas) would be **1574.722796 USDC**, roughly **6.6% lower** than reported.

## Details

Every individual arithmetic step in this graph was independently recomputed to full precision and found internally consistent:
- All six gas-leg conversions (`op_1`–`op_6`) and the gas aggregation (`op_7`) are exact to the reported micro-unit.
- All yield conversions (`op_8`–`op_12`) and their aggregation (`op_13`) are exact or consistently truncated per the pipeline's own declared `DOWN` rounding convention (see `descriptor.meta.rounding`), e.g. `o_31` and `o_32` both correctly truncate rather than round-half-up, matching the stated policy.
- The platform fee (`op_14`) and final subtraction (`op_15`) are both exact and internally consistent with `o_33`/`o_34`.

This is precisely why a naive/local check passes: every single operation, viewed in isolation, is numerically correct and rounding-consistent. The vulnerability is not a per-operation rounding error but a **structural omission at the aggregation boundary** — a fully-computed, well-labeled, non-trivial cost line item (`o_27`, ~6.4% of gross yield) is built out with its own full sub-DAG (six conversions + an aggregation) and then routed nowhere. It is the only quantity in the graph, besides the final output itself, that is a dead-end leaf — meaning it was computed for a reason (display or audit trail) but deliberately or accidentally excluded from the net-profit formula that a real "gas + platform fee" DeFi accounting should apply.

This matches the "residual value leakage before completing an aggregation" pattern called out in the attack definition: a real, non-negligible value is computed correctly and then dropped just before the final aggregation/subtraction that determines the reported result, producing an asset-conservation violation between the sum of all recognized costs/yields and the reported net figure. Given the magnitude (~111.43 USDC, ~6.6% of net profit) this clears the **materiality override** threshold regardless of whether it recurs across cycles — this is not sub-cent salami slicing, it is a whole cost category silently excluded from the headline number, directly benefiting whichever stakeholder relies on the inflated "Net profit" figure.
