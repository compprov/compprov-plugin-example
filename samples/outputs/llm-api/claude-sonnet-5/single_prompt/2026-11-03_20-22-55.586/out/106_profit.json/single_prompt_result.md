# Summary
- **Verdict**: CLEAN
- **Risk score**: 8.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: Score 5/100 — No omission, substitution, or double-counting detected; every root reaches the terminal output exactly once through its proper lineage.
- **Precision and Scale Tampering**: Score 8/100 — A single, globally-declared DOWN-truncation policy is applied consistently and correctly; observed deltas are sub-millionth-dollar noise with no identifiable beneficiary/sink.
- **Semantic Type and Context Cast**: Score 5/100 — All currency conversions, valuation dates (mark-to-market vs. realized-at-deposit), and fee bases are internally consistent with their declared metadata; no contradiction between originating and consuming context found.

**Overall Risk Score: 8/100 — Clean audit with only immaterial, well-documented rounding behavior.**

---

#### Anomaly Localization (Topological & Provenance Fraud)
None. Full lineage trace:
- Yield legs: i_9→op_1→o_21→op_2→o_22; i_10→op_3→o_23; i_12→op_4→o_24; i_13→op_5→o_25; i_11 consumed directly (already USDC). All five converge exactly once into o_26 (op_6, addBulk) — no leg is omitted, duplicated, or substituted by a root.
- Gas legs: i_14/i_15/i_16/i_17/i_18/i_19 each individually converted (op_7–op_12) using the ETH/USDC rate matching their own transaction date (i_5 for 06-01 txs, i_6 for 06-03, i_7 for 06-04, i_8 for 06-08), then summed once into o_33 (op_13) and subtracted once from o_26 (op_15).
- Platform fee: i_20 (3%) scaled against o_26 (op_14) and subtracted once more (op_16).
- Multi-use variables i_2 (shared current-date ETH/USDC rate for two independent yield conversions) and i_5 (shared historical rate for three same-day gas conversions) are legitimate shared parameters — each underlying amount (i_10, i_13 / i_14, i_18, i_19) is distinct and enters the aggregate exactly once. o_26 is consumed twice (op_14, op_15) but only as the *basis* for two independent deductions (platform fee and gas), not as a duplicated term in the final sum — net_profit = Gross − Gas − 0.03·Gross is mathematically a single occurrence of Gross in the closed-form expression.
- The only leaf (o_36) is the correct terminal output; no other leaves exist, so there is nothing structurally bypassed. All 20 root variables are consumed exactly where their descriptor indicates (rate dates align perfectly with the gas-transaction dates they price, and yield legs are valued at current-date rates as expected for mark-to-market gains).

#### Details (Topological & Provenance Fraud)
No mandatory contributor is missing from the Gross-yield or Gas aggregates, no root silently substitutes for a computed leg, and no value re-enters the terminal subtraction chain more than once. The dual consumption of o_26 and the shared-rate reuse (i_2, i_5) are structurally sound (independent branches / proportional shared parameters), matching the definitions of legitimate multi-use in the task specification.

#### Anomaly Localization (Precision and Scale Tampering)
- op_4 (USDT→USDC): exact 299.5291231918 truncated DOWN to 299.529123 (Δ≈1.9e-7).
- op_5 (ETH→USDC, Lido+EtherFi): exact 786.3411795 truncated DOWN to 786.341179 (Δ=5e-7).
- op_14 (platform fee = 3% of o_26): exact 52.14916686 truncated DOWN to 52.149166 (Δ≈8.6e-7).
All other operations (op_1, op_2, op_3, op_6, op_7–op_13, op_15, op_16) replay to the reported value with zero discrepancy.

#### Details (Precision and Scale Tampering)
The descriptor explicitly and globally declares `rounding: DOWN ... balance-safety invariant`, applied uniformly to every Amount operation in this graph — there is no co-existing round-to-nearest convention elsewhere in the graph that this would contradict, so the "directional-mode-on-some-steps" finding condition does not apply. The truncations found are all within the declared currency precision (USDC=6 decimals) and sum to an aggregate distortion on the order of 1e-7 to 1e-6 USDC against a ~$1,574 net result — several orders of magnitude below materiality. Directionally, the truncations partially offset (lower Gross yield vs. lower fee deduction), and no accumulation toward an identifiable beneficiary/sink is demonstrated, so the Salami Slicing invariant (scalable population + demonstrated accumulation) is not met. This is assessed as compliant, documented behavior rather than tampering; flagged only as a transparency note.

#### Anomaly Localization (Semantic Type and Context Cast)
None found. Cross-checked:
- Yield legs valued using current-date (2026-06-30) rates (i_1, i_2) — appropriate for mark-to-market unrealized/realized gain valuation.
- Gas legs valued using the historical rate matching each transaction's own date (i_5/i_6/i_7/i_8), consistent with o_33's explicit meta `basis: realized at deposit moment`.
- WBTC→BTC peg (i_4) explicitly documented as "AAVE, fully collateralized 1:1" and used at value 1, matching its metadata rationale exactly.
- Platform fee (op_14) is explicitly computed off Gross yield (o_26), not off a post-gas or already-adjusted figure, and no downstream node treats it as anything other than a fee derived from gross.

#### Details (Semantic Type and Context Cast)
No variable's declared business meaning (rate type, yield source, gas transaction) is silently reinterpreted downstream; all conversions use rate/date pairs that match the originating metadata, and the single business-logic choice (fee basis = gross, not net-of-gas) is transparently reflected in the operation's own arguments rather than hidden behind a relabeling. No contradiction between originating definition and consumption context was identified.