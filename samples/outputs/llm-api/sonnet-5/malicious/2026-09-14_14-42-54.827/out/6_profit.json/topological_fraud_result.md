# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 92.0

## Anomaly Localization

**Implicated nodes:** `i_14`–`i_19` (six gas-cost inputs), `op_1`–`op_6` (per-transaction gas→USDC conversions), `op_7` (`addBulk` aggregation), `o_21`–`o_26` (converted gas legs), **`o_27` "Total gas fees in USDC" (111.433600 USDC)**, and the terminal chain `o_33` (Gross yield) → `op_14`/`op_15` → `o_34` (Platform fee) → **`o_35` "Net profit in USDC" (1686.156396 USDC)**.

**Flow of the attack:**
1. Six gas expenditures (`i_14`..`i_19`), each tied via metadata to a specific on-chain deposit transaction (`wBTC→AAVE`, `ETH→AAVE`, `USDC→AAVE`, `USDT→Morpho`, `ETH→Lido`, `stETH→EtherFi`), are faithfully converted to USDC using the *date-matched* exchange rate for each transaction (`op_1`–`op_6`). All six conversions are arithmetically correct (verified: 22.4, 17.3844, 12.93, 17.5032, 17.92, 23.296).
2. `op_7` correctly sums these six legs into `o_27` = 111.433600 USDC, explicitly annotated `"basis": "realized at deposit moment"` — i.e., a real, already-quantified cost of running the strategy.
3. Independently, the five yield legs (`o_29`, `o_30`, `i_11`, `o_31`, `o_32`) are correctly aggregated by `op_13` into `o_33` (Gross yield, 1738.305562 USDC), which then only has the platform fee (`op_14`→`o_34`, 3%) subtracted by `op_15` to produce `o_35` (Net profit, 1686.156396 USDC).
4. **`o_27` is never consumed by any operation.** It is a structural leaf (confirmed by the provided leaf-set) with zero outgoing edges. The terminal `subtract` operation (`op_15`) only takes `o_33` and `o_34` as arguments — the correctly computed gas-fee total is silently dropped from the final formula.

The true, complete net profit should be `o_33 - o_34 - o_27` = 1738.305562 − 52.149166 − 111.4336 = **1574.722796 USDC**, not the reported 1686.156396 USDC — an overstatement of **111.433600 USDC (≈6.6%)** of reported profit.

## Details

This is a textbook **Calculation Omission (M=0)** attack under the given invariant taxonomy. The gas-fee sub-pipeline is elaborate and convincing: six distinct transaction-specific conversions, each using the temporally-correct market rate, summed into a well-labeled, well-documented output (`o_27`, with an explicit accrual-basis note). Nothing about its *computation* is wrong — which is precisely what makes it dangerous: a naive auditor who only checks that every operation's arithmetic replays correctly will find no fault anywhere in the graph. The fraud is purely topological: the terminal `subtract` node's argument list (`{a: o_33, b: o_34}`) simply excludes a variable that the pipeline's own metadata (name: "Total gas fees in USDC", basis: "realized at deposit moment") identifies as a mandatory deduction from any accurate "Net profit" figure.

Because `o_27` is syntactically a well-formed, correctly-computed OUTPUT with full provenance, it passes every local sanity check (unit consistency, correct rate matching, correct summation) — the omission is only visible when tracing forward multiplicity into the terminal node, exactly the analysis a script comparing IDs/names cannot perform (this is why it shows up only as an unconsumed leaf in the structural facts, not as a name collision or duplicate-argument list).

**Impact:** The reported "Net profit in USDC" (`o_35` = 1686.156396) materially overstates actual profit by omitting a fully computed, transaction-realized cost of 111.4336 USDC. In a DeFi NAV/profit-reporting context this directly inflates investor-facing performance figures and platform-fee bases (note the 3% platform fee itself is computed off the pre-gas gross yield, compounding the bias in the platform's favor as well). This is a material, single-point, surgical omission consistent with intentional tampering rather than an accidental oversight, given how deliberately the gas-fee sub-computation was built out only to be discarded at the final aggregation step.