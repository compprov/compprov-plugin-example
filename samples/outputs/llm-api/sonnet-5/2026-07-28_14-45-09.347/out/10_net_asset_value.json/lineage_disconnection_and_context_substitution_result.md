# Summary
- **Verdict**: CLEAN
- **Confidence score**: 87.0

## Anomaly Localization (If Detected)
No instance of Lineage Disconnection or Context Substitution was found in this graph after full manual forward propagation.

## Details

**Structural check:** The only leaf variable (never consumed downstream) is `o_14` ("Assets sum"), which is the graph's intended terminal output — the final NAV total. All intermediate computed OUTPUTs (`o_9`, `o_10`, `o_11`, `o_12`, `o_13`) are each consumed exactly once, as arguments `a`, `b0`, `b1`, `b2`, `b3` respectively of `op_6` (`addBulk`), which produces `o_14`. There is no orphaned computed variable sitting unused while a same-named or same-role substitute is routed into the final aggregation instead. The name-collision set is empty, and a manual role-based scan (matching by currency, source venue, and rate) found no near-duplicate or semantically-equivalent stand-in variables anywhere in the graph that could have been swapped in for any of `o_9`–`o_13`.

**Pairing/lineage check:** Each conversion operation consumes the correct root inputs for its stated purpose:
- `op_1`: `i_4` (BTC balance) × `i_1` (BTC/USD rate) → `o_9`
- `op_2`: `i_5` (ETH balance, Binance) × `i_2` (ETH/USD rate) → `o_10`
- `op_3`: `i_6` (USDC balance, Binance) × `i_3` (USDC/USD rate) → `o_11`
- `op_4`: `i_7` (ETH balance, Stake) × `i_2` (ETH/USD rate, legitimately reused) → `o_12`
- `op_5`: `i_8` (USDC balance, Morpho) × `i_3` (USDC/USD rate, legitimately reused) → `o_13`

Reuse of `i_2` and `i_3` across two operations each is expected and benign here: the same market rate is correctly applied to two separate balances of the *same* underlying asset held at different venues (ETH at Binance vs. Stake; USDC at Binance vs. Morpho). This is a legitimate multi-use rate pattern, not a disguised substitution, since in both cases the rate is applied to a genuinely distinct root balance producing a distinct, individually-consumed output.

**Full numeric forward-propagation (independent replay, not just local check):**
- `o_9` = 1.24184892 × 68989.72 ≈ 85674.80 ✓ (matches reported)
- `o_10` = 92.520767008267654318 × 2083.31 ≈ 192749.44 (reported 192749.43 — consistent with a truncating/floor rounding context)
- `o_11` = 53344.459319 × 1.01 = 53877.903912… → 53877.90 ✓
- `o_12` = 8.310133997912894088 × 2083.31 ≈ 17312.5853 (reported 17312.58 — consistent with truncation, not standard half-up rounding)
- `o_13` = 22284.229335 × 1.01 = 22507.07163 → 22507.07 ✓
- `o_14` = 85674.80+192749.43+53877.90+17312.58+22507.07 = 372121.78 ✓ exactly matches the reported final output, and is an exact sum of the five properly-derived conversion outputs — no hardcoded or substituted term is present in the aggregation.

All five terms feeding the final `addBulk` operation are literally the `resultId`s of the immediately preceding conversion operations, each of which traces cleanly back to a genuine root `INPUT` (rate and balance pair). No computed variable is bypassed in favor of a parallel hardcoded or unmonitored value; no leaf variable is left as a dead-end substitute for a foreign value; no root INPUT masquerades under the name or role of one of the computed conversion outputs.

**Conclusion:** Origin_Propagation_Valid holds for the reported final output `o_14`. The full chain from all 8 root inputs through the 5 conversions and the final bulk-add is intact, consistent, and mathematically reproducible under a plausible (truncating) rounding context. No lineage disconnection, hijacked intermediate, or context substitution is evidenced in this graph.