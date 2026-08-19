# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 92.0

## Anomaly Localization

**Implicated nodes:**
- Raw (pre-gas) yield conversions: `o_29` (wBTC yield in USDC), `o_31` (ETH/AAVE yield in USDC), `i_11` (USDC yield, no conversion needed), `o_34` (USDT/Morpho yield in USDC), `o_36` (ETH Lido+EtherFi yield in USDC)
- Individual gas legs: `o_21`, `o_22`, `o_23`, `o_24`, `o_25`, `o_26`
- Total gas aggregate: `o_27` (`op_7`, addBulk of `o_21..o_26`)
- Per-position "net of gas" values: `o_30` (`op_10`: `o_29`-`o_21`), `o_32` (`op_12`: `o_31`-`o_22`), `o_33` (`op_13`: `i_11`-`o_23`), `o_35` (`op_15`: `o_34`-`o_24`), `o_37`/`o_38` (`op_17`/`op_18`: `o_36`-`o_25`-`o_26`)
- Mislabeled aggregate: `o_39` (`op_19`, addBulk of `o_30,o_32,o_33,o_35,o_38`) — labeled **"Gross yield in USDC"**
- Second gas subtraction: `op_21` (`o_39` - `o_27` → `o_41`, labeled **"After gas deduction"**)
- Final tainted output: `o_42` (`op_22`: `o_41` - `o_40` → "Net profit in USDC")

**Attack flow:**
1. Each yield stream is converted to USDC and then has its *own* transaction gas subtracted per position (`op_10`, `op_12`, `op_13`, `op_15`, `op_17`+`op_18`), producing `o_30, o_32, o_33, o_35, o_38`. Algebraically, `o_30+o_32+o_33+o_35+o_38 = (o_29+o_31+i_11+o_34+o_36) − (o_21+o_22+o_23+o_24+o_25+o_26)` = RawGrossYield − TotalGas.
2. `op_19` sums exactly these already-gas-net figures into `o_39`, and confirmed by exact recomputation: `o_39 = 1738.305562 − 111.433600 = 1626.871962`. Yet `o_39` is labeled **"Gross yield in USDC"**, concealing that gas has *already* been deducted.
3. `op_21` then computes `o_41 = o_39 − o_27`, i.e. subtracts the **same total gas figure (`o_27` = 111.433600) a second time**, and is labeled "After gas deduction" — reinforcing the illusion that this is the first (and only) gas deduction step.
4. `op_22` subtracts the platform fee (correctly computed off the true net-of-gas base `o_39` via `op_20`) from this already double-deducted figure, propagating the error unchanged into the final reported "Net profit" `o_42 = 1466.632204`.

Recomputing the pipeline correctly (single gas deduction): true net profit = `o_39 − o_40` = `1626.871962 − 48.806158 = 1578.065804`. The reported final output (`1466.632204`) is understated by exactly `111.433600` — precisely equal to `o_27`, the total gas figure — proving a mechanical double subtraction rather than coincidental rounding drift.

## Details

This is not a rounding-mode artifact: the discrepancy (111.4336 USDC, ~7.6% of gross yield and of the final reported profit) is many orders of magnitude larger than the ≤1-unit-at-target-scale ceiling that a single truncate-vs-round difference could produce. It was confirmed via exact rational re-derivation of every operation in the graph, all of which independently checked out to their reported values with the documented `DOWN` truncation policy applied consistently — except for this one aggregation-level double count.

The mechanism that lets this slip past casual/local review: `op_19`'s addBulk operation and its output `o_39` are locally self-consistent (the sum of its five listed arguments does equal `o_39` to the last decimal), so any check that only validates "does this operation's output equal the arithmetic combination of its declared inputs" passes cleanly at every single node. The vulnerability is purely in the *aggregate-level semantics*: `o_39` is deceptively named "Gross yield" when it is structurally already net-of-gas, and it is then fed into a *second* subtraction of the identical gas total (`o_27`) at `op_21`, which is itself deceptively labeled "After gas deduction" as though this were the sole/first such deduction. The five intermediate "net of individual gas" variables (`o_30, o_32, o_33, o_35, o_37, o_38`) all carry blank `descriptor.name` fields (`""`), removing the semantic breadcrumb (e.g. "yield net of gas") that would otherwise tip off a reviewer that gas was already embedded in them before they were rolled into the "Gross yield" total.

**Consequence:** The reported "Net profit in USDC" (`o_42` = 1466.632204) understates the true, correctly-computed net profit (1578.065804) by exactly the total transaction gas cost (111.433600 USDC). This is a full, material double-deduction disguised as two separate legitimate-looking accounting steps (gross-yield aggregation, then "gas deduction", then "fee deduction") — a textbook subtle arithmetic/aggregation logic flaw that silently drains a fixed, non-trivial amount from the reported result on every run of this pipeline, and would misstate distributable profit for any investor, LP, or fee-share calculation relying on this DAG's final output.