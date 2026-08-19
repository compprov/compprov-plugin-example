# Summary
- **Verdict**: SEMANTIC CAST DETECTED
- **Confidence score**: 92.0

## Anomaly Localization

**Implicated nodes:** `i_8` (ETH balance, source: Trust Nodes), `i_9` (WSTETH balance, source: Lido), `op_1` (add), `o_11` (mislabeled "WSTETH balance"), `op_2` (convert), `i_3` (WSTETH/ETH rate), `o_12` ("WSTETH->ETH"), `op_6` (convert), `i_2` (ETH/USD rate), `o_16` ("ETH(Staked)->USD"), and finally `op_8` / `o_18` ("Assets sum").

**Attack flow:**
1. `op_1` (`formula: a+b`) takes `a = i_9` (9.170773527744636055 **WSTETH**, Lido) and `b = i_8` (72.327829715473001215 **ETH**, Trust Nodes) and performs a raw arithmetic addition, `9.170773527744636055 + 72.327829715473001215 = 81.498603243217637270`.
2. The result `o_11` is technically type-valid (`Amount -> Amount`, `WrappedAmount` wrapper, `BigDecimal`-backed) and is explicitly re-labeled with `currency: "WSTETH"` and `descriptor.name: "WSTETH balance"` — identical to the name of its WSTETH operand `i_9` — while its `descriptor.meta` (which distinguished `source: Lido` vs `source: Trust Nodes`) is silently dropped (empty `meta: []`).
3. This mislabeled aggregate is then fed into `op_2` (`convert(a, r)`), applying the **WSTETH→ETH** rate (`i_3 = 1.243492`) to the *entire* sum — including the ETH-denominated `i_8` portion that was never actually WSTETH. This produces `o_12 = 101.342861144115186204 ETH`.
4. `o_12` then feeds `op_6`, converting via `i_2` (ETH/USD = 2083.31) into `o_16 = 211128.59 USD` ("ETH(Staked)->USD"), which is finally summed into the reported NAV total `o_18 = 695507.03 USD`.

All type signatures (`Amount` -> `Amount`, `BigDecimal` arithmetic) and mathematical replay of each individual step check out perfectly. The break is purely semantic: an ETH-denominated balance is silently cast into a WSTETH-denominated balance via an undifferentiated `add`, then that already-inflated quantity is *again* multiplied by the WSTETH→ETH conversion rate — effectively applying a currency-conversion factor to an amount that was never actually in that currency.

## Details

**Mechanism:** The `add` operation (`op_1`) has no currency-awareness enforcement — it accepts two `Amount` operands of *different* declared currencies (`WSTETH` and `ETH`) and blindly sums their numeric magnitudes, then stamps the result with one operand's currency code and display name (`"WSTETH balance"`), discarding the metadata (`source` tags for Lido vs Trust Nodes) that would have revealed the mismatch. This is a textbook Semantic Type and Context Cast: `valueClass` (`io.compprov.examples.nav.model.Amount`) stays constant end-to-end, the wrapper class and argument wiring look completely legitimate, and every downstream `convert` step replays the numbers exactly as expected for its inputs — yet the *business meaning* of the operand (an ETH-denominated stake) has been silently recast as a WSTETH-denominated stake without any explicit, auditable conversion node performing that specific transformation. Per the stated invariants, a domain transition (ETH -> WSTETH) requires explicit, auditable transformation logic (i.e., dividing by the 1.243492 rate, or an equivalent conversion op) — not an ordinary `add`.

A casual/mechanical audit would not catch this: `op_1`'s inputs are both valid `Amount` root variables, its output type matches, downstream operations correctly consume `o_11`/`o_12` by ID, and every multiplication/addition replays bit-for-bit consistently with the recorded operands. Only tracing the *currency* field of each argument against the operation's implicit unit assumptions reveals the fault.

**Quantified impact:** Reconstructing the intended (currency-correct) computation — converting `i_9` (WSTETH) to ETH via `i_3` first (`9.170773527744636055 * 1.243492 ≈ 11.403783515562032776 ETH`), then adding to `i_8` (72.327829715473001215 ETH) — yields a correct total of ≈ **83.731613231035033991 ETH**, versus the reported `o_12 = 101.342861144115186204 ETH`: an overstatement of ≈ 17.61 ETH (~21%). Propagated through `i_2` (2083.31 USD/ETH), the correct `o_16` should be ≈ **174,459 USD**, versus the reported **211,128.59 USD** — an overstatement of roughly **36,600–37,000 USD** that flows directly into the final NAV (`o_18 = 695,507.03 USD`), materially inflating the reported total assets.

**Consequence:** The reported NAV is materially overstated because an ETH-denominated custodial balance (Trust Nodes) is silently laundered through a currency-mismatched `add` into a WSTETH-labeled aggregate, then double-converted via the WSTETH/ETH rate — a surgical, type-safe substitution that passes structural and mathematical replay checks while violating the business/domain continuity invariant.