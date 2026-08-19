# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 90.0

## Anomaly Localization

**Implicated nodes:** `i_8` (ETH balance, Trust Nodes, 72.327829715473001215 ETH), `i_9` (WSTETH balance, Lido, 9.170773527744636055 WSTETH), `op_1` (`add`), `o_11` (labeled "WSTETH balance", value 81.498603243217637270), `i_3` (WSTETH/ETH rate = 1.243492), `op_2` (`convert`), `o_12` ("WSTETH->ETH", value 101.342861144115186204), `i_2` (ETH/USD rate = 2083.31), `op_6` (`convert`), `o_16` ("ETH(Staked)->USD", value 211128.59), `op_8` (`addBulk`), `o_18` ("Assets sum", value 695507.03).

**Flow of the defect:**
1. `op_1` computes `o_11 = i_9 + i_8`, i.e. it adds a **WSTETH** quantity (9.170773527744636055) directly to an **ETH** quantity (72.327829715473001215) as if they were the same unit, and stores the raw sum (81.498603243217637270) under the label "WSTETH balance".
2. `op_2` then applies the WSTETH→ETH exchange rate (`i_3` = 1.243492) to this entire mixed sum, producing `o_12` = 101.342861144115186204 ETH. Because the multiplication itself (`o_11 * rate`) is numerically self-consistent, a naive replay check on `op_2` alone passes.
3. `op_6` converts `o_12` to USD via `i_2` (2083.31), yielding `o_16` = 211,128.59 USD.
4. `op_8` (`addBulk`) sums `o_16` together with the other converted asset lines into the final NAV `o_18` = 695,507.03 USD.

The correct, unit-consistent computation would have been to convert the WSTETH balance into ETH-equivalent terms **first**, then add the already-ETH-denominated balance:
`i_8 + (i_9 * rate_i3) = 72.327829715473001215 + 11.403783515562... ≈ 83.731613231035...` ETH.
Instead, the graph computes `(i_9 + i_8) * rate_i3 ≈ 101.342861144115...` ETH — applying the WSTETH conversion multiplier (1.243492, i.e. >1) to the *entire* sum, including the 72.33 ETH portion that was never denominated in WSTETH and should never have been multiplied by that rate at all.

## Details

**Mechanism:** This is a textbook instance of "mixing scales or token units … without a proper scaling transformation." `op_1`'s `add` operation combines two economically distinct assets (WSTETH and ETH) as raw numeric quantities before any conversion is performed, and the result is deceptively re-labeled as a pure "WSTETH balance" so that the subsequent `convert(a, r)` in `op_2` appears syntactically legitimate (a single Amount + a single Rate — exactly the pattern used by every other `convert` call in the graph). A casual or purely-structural replay check sees: valid add, valid convert, valid convert, valid addBulk — every operation's own arithmetic reconciles internally. Only tracing the *provenance and units* of the operands reveals that `i_8` (ETH) was smuggled into an operation and a rate meant only for WSTETH.

**Quantified impact:**
- Erroneous ETH-equivalent staked balance: 101.342861144115186204 ETH (reported) vs. ≈83.731613231035234191 ETH (correct) → excess of **≈17.61 ETH**.
- At the ETH/USD rate of 2083.31, this inflates `o_16` ("ETH(Staked)->USD") from a correct value of ≈174,441 USD to the reported 211,128.59 USD — an overstatement of **≈36,687 USD** on that single line (≈21% overstatement of that line item).
- This flows straight through the uncontested `addBulk` in `op_8` into the final NAV `o_18`, inflating the reported total NAV of 695,507.03 USD by roughly **5.3%** relative to the correct total (~658,820 USD).

This is not a boundary-sized, single-ULP rounding-mode artifact (which the invariants explicitly exclude) — the discrepancy is many orders of magnitude larger than any plausible truncate-vs-round-to-nearest deviation at the currency's declared precision (2 decimals for USD, 18 for ETH/WSTETH). It is a structural unit-conflation defect that materially and directly inflates the reported Net Asset Value, and it is isolated to exactly one asset bucket ("Staked ETH"), consistent with a surgical, targeted manipulation rather than a diffuse systemic rounding bias.