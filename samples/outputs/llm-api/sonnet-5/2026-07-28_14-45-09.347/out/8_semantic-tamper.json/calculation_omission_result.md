# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 68.0

## Anomaly Localization

**Implicated nodes:** `i_8` (ETH balance, source: Trust Nodes), `i_9` (WSTETH balance, source: Lido), `i_3` (WSTETH/ETH rate), `i_2` (ETH/USD rate), `op_1` (add), `o_11` (mislabeled "WSTETH balance"), `op_2` (convert), `o_12` ("WSTETH->ETH"), `op_6` (convert), `o_16` ("ETH(Staked)->USD"), `op_8` (addBulk), `o_18` ("Assets sum", final reported NAV).

**Flow of the defect:**
1. `op_1` computes `o_11 = i_9 + i_8`, i.e. it adds a **WSTETH token quantity** (26.515133966086203543) directly to a **native ETH token quantity** (95.602701057800416606) as if the two units were interchangeable, and stamps the result with `currency: "WSTETH"` even though roughly 78% of the summed magnitude is actually ETH, not WSTETH.
2. `op_2` then converts this entire mixed sum (`o_11`) using the WSTETH→ETH exchange rate (`i_3` = 1.243492), producing `o_12 = 151.852550909522821062` ETH. This step is arithmetically self-consistent (122.117835023886620149 × 1.243492 ≈ 151.8526), but it applies the WSTETH conversion multiplier to the *entire* combined balance — including the `i_8` portion, which is already denominated in ETH and should be included at a 1:1 (identity) rate, not scaled by 1.243492.
3. `op_6` converts `o_12` to USD via `i_2`, yielding `o_16 = 316,355.93` USD, which flows unmodified into the final aggregation `op_8` (`addBulk`) alongside `o_13`, `o_14`, `o_15`, `o_17`, producing the reported NAV `o_18 = 1,112,940.13` USD.

The correct, complete computation that the pipeline's own component naming implies (mirroring the pattern used for every other balance, e.g. `o_14 = convert(i_6, i_2)`) would be:

`correct_staked_ETH_ETH = i_8 + convert(i_9, i_3)` → `95.602701057800416606 + (26.515133966086203543 × 1.243492)` ≈ `95.602701 + 32.973` ≈ `128.575701` ETH

`correct_staked_ETH_USD = 128.575701 × 2083.31` ≈ **$267,863** USD

versus the actual reported `o_16 = $316,355.93` — an overstatement of roughly **$48,500** flowing straight into the final `Assets sum`, inflating the reported NAV (`o_18`) by ~4.4%.

## Details

The mechanism that lets this pass casual and even automated review is that every individual operation is *locally* correct: `op_1`'s addition is arithmetically exact, `op_2` and `op_6`'s multiplications are exact given their inputs, and `op_8`'s bulk sum exactly equals the sum of its five listed arguments. No variable is left as an unconsumed leaf (the only leaf is the expected final output `o_18`), and every raw balance (`i_5`–`i_10`) does have *some* causal path into the final result, so naive dead-end/argument-coverage checks report a clean, fully-connected graph.

The defect is instead embedded in *how* two legitimately distinct balances — native ETH held via Trust Nodes (`i_8`) and wrapped-staked-ETH held via Lido (`i_9`) — are merged. By adding the two raw token quantities before applying the WSTETH/ETH exchange rate, the pipeline silently applies the WSTETH conversion multiplier to `i_8`'s already-correct ETH value, which should have been carried through at parity (rate = 1). This means the correct, straightforward inclusion of the Trust Nodes ETH balance at its true USD value (`i_8 × ETH/USD rate`, exactly the pattern used for `o_14 = convert(i_6, i_2)`) is never computed anywhere in the graph — it is quietly displaced by a corrupted composite figure before the final aggregation is even reached. The mislabeling of `o_11` as pure `"WSTETH balance" / currency: WSTETH` (despite containing a majority-ETH-derived quantity) further obscures the unit conflation from a descriptor-level review.

**Consequence:** the reported "Assets sum" (`o_18 = $1,112,940.13`) materially overstates the true asset value by roughly $48,500 (~4.4%), biasing the NAV upward — a favorable/inflated outcome consistent with the credit-side variant of the calculation-omission pattern (the correct, lower-value identity-rate contribution of `i_8` is effectively omitted from the result and replaced by an inflated substitute), even though the topological argument-coverage of the final aggregation operation appears complete.