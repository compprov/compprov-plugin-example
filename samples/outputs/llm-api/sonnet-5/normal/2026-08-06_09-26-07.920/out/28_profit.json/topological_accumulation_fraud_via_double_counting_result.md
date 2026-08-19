# Summary
- **Verdict**: CLEAN
- **Confidence score**: 83.0

## Summary
A full forward-trace from every root INPUT variable to the sole terminal output (`o_36`, "Net profit in USDC") was performed, and every operation's numeric output was independently recomputed from its declared arguments and cross-checked against the stored `value` in the CPG.

### Arithmetic Reconciliation (spot-verified to the currency's DOWN-truncation precision)
- `o_21 = convert(i_9, i_4=1)` = 0.00369452 BTC ✔
- `o_22 = convert(o_21, i_1=109800)` = 405.658296 ✔
- `o_23 = convert(i_10, i_2=4650)` = 127.193403 ✔
- `o_24 = convert(i_12, i_3=0.9998)` = 299.529123 ✔
- `o_25 = convert(i_13, i_2=4650)` = 786.341179 ✔
- `o_26 = addBulk(o_22,o_23,i_11,o_24,o_25)` = 1738.305562 ✔
- `o_27..o_32` (6 gas conversions using i_5/i_6/i_7/i_8, each dated to match the corresponding deposit) all reconcile exactly (22.400000 / 17.384400 / 12.930000 / 17.503200 / 17.920000 / 23.296000) ✔
- `o_33 = addBulk(o_27..o_32)` = 111.433600 ✔
- `o_34 = scale(o_26, i_20=0.03)` = 52.149166 (down-truncated) ✔
- `o_35 = o_26 - o_33` = 1626.871962 ✔
- `o_36 = o_35 - o_34` = 1574.722796 ✔

All reported values match independent recomputation exactly — no silent numeric substitution was found anywhere in the chain.

## Path-Multiplicity / Double-Counting Analysis
Per the structural reference data, the reused variables are `i_2`, `i_5`, and `o_26`.

- **`i_2` / `i_5` (ETH/USDC rate snapshots)**: These are *rate parameters*, not financial entities being accumulated. `i_2` (2026‑06‑30 rate) is applied to both ETH-denominated yield legs (`i_10`, `i_13`) because both are being mark-to-market valued as of the reporting date — a single valuation input legitimately parameterizing two independent conversions of two *distinct* underlying yield entities. `i_5` (2026‑06‑01 rate) is likewise applied to three distinct gas-cost entities (`i_14`, `i_18`, `i_19`), all three transactions dated 2026‑06‑01. No underlying revenue/cost entity is duplicated by this reuse — only the multiplier is shared, exactly the kind of legitimate shared-parameter reuse the audit discipline distinguishes from entity duplication.
- **`o_26` (Gross yield subtotal)**: This intermediate aggregate is consumed twice — once by `op_14` (`scale`, formula `a*f`, computing the 3% platform fee `o_34`) and once by `op_15` (`subtract`, computing `o_35 = o_26 - o_33`, the gas-adjusted balance). Both branches converge at the terminal `op_16` (`o_36 = o_35 - o_34`). Algebraically this collapses to `o_36 = o_26 - o_33 - 0.03*o_26 = 0.97*o_26 - o_33`, i.e., a standard "gross minus incurred costs minus a percentage-of-gross platform fee" formula. Each root yield entity (`i_9`,`i_10`,`i_11`,`i_12`,`i_13`) therefore contributes net `0.97×` its value to the terminal result rather than being additively duplicated (it is never added or subtracted twice at full/undiminished weight through two independent paths). The proportional-fee mechanism is explicitly and auditably documented in the operation metadata (`formula: a*f`, applied against a named variable "Platform fee rate (3%)"), which satisfies the invariant's stated exception for "explicit, auditable proportional splitting logic."

No other candidate duplication was found:
- Each of the five yield entities (`i_9`, `i_10`, `i_11`, `i_12`, `i_13`) feeds `o_26` via exactly one path.
- Each of the six gas-cost entities (`i_14`–`i_19`) feeds `o_33` via exactly one path; the two gas legs for the "Lido+EtherFi" position (`i_18`, `i_19`) correspond to two genuinely distinct on-chain transactions (ETH→Lido mint, then stETH→EtherFi restake) consistent with that protocol's documented two-hop deposit flow, not a re-wrapped duplicate of a single gas payment.
- No near-duplicate variable names or values were found that alias a previously-consumed root entity into a second, disguised path (checked amounts, dates, and tx metadata for i_15/i_17 coincidental gas-amount similarity — these are distinct transactions on different dates/protocols, not the same entity re-entered).
- `o_33` and `o_34` (the two deduction terms) are each subtracted exactly once at the terminal; no cost already netted into a subtotal reappears in a later aggregate.

## Details
The graph exhibits the two reuse patterns the structural scan is designed to surface (`i_2`/`i_5` rate reuse, `o_26` subtotal reuse), but on manual trace both resolve to legitimate, well-documented computational patterns rather than the double-counting fraud shape: rate reuse is parameter sharing across distinct entities, and the gross-yield reuse is a standard, formula-labeled percentage-fee-on-gross calculation that is mathematically equivalent to a single linear combination, not a duplicated addend/deduction. All terminal arithmetic reconciles exactly with the declared operations, and the deduplicated sum of the five yield legs equals the reported gross figure, and the deduplicated sum of the six gas legs equals the reported total-gas figure. No entity contributes with multiplicity >1 in the sense of being fully counted twice toward the terminal output.

Given the explicit formula annotations and full arithmetic reconciliation, I do not find sufficient evidence of Topological Accumulation Fraud via Double Counting in this graph. The `o_26` dual-consumption pattern is the one point warranting continued attention in future audits (fee-on-gross vs. fee-on-net-of-costs is a business-policy choice, not inherently fraudulent, but should be confirmed against the pipeline's documented fee policy outside this graph).