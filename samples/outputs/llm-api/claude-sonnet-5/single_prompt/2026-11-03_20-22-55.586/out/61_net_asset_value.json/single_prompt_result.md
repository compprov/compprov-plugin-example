# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 95.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud — Lineage Disconnection / Context Substitution**: 95/100 — CONFIRMED. The correctly computed BTC→USD conversion (`o_9`) is orphaned; a root variable (`i_10`) claiming to be the same quantity is substituted into the terminal aggregation instead.
- **Precision and Scale Tampering**: 5/100 — Clean. Every `convert` operation (op_1–op_5) replays exactly against the declared DOWN truncation to the currency's native decimal precision (USD = 2 dp). The final `addBulk` sums the inputs exactly with no scale loss.
- **Semantic Type and Context Cast**: 10/100 — No independent finding; the substituted variable (`i_10`) carries the *same* declared business label ("BTC amount in USD") as the bypassed computed value (`o_9`), so the anomaly is a provenance substitution rather than a relabeling/context-cast of a differently-defined quantity.

---

#### Anomaly Localization (Topological & Provenance Fraud)
- **Bypassed computed node:** `o_9` ("BTC->USD", value `683929.93`), produced by `op_1 = convert(i_4, i_1)` where `i_4` = BTC balance (9.91350497 BTC, source: Binance) and `i_1` = BTC/USD rate (68989.72, origin: Binance). Recomputing exactly: 9.91350497 × 68989.72 = 683929.9321... → truncates (DOWN, matching the descriptor's global rounding rule) to **683929.93**, exactly matching the stored value of `o_9`. The computation is internally correct.
- **Substitute node:** `i_10` ("BTC amount in USD", value `752322.92`) — a **root variable** (per structural reference data) with no upstream operation, no origin/source metadata, and no MathContext/provenance trail, despite sharing the same descriptive role as `o_9` (both represent "BTC balance expressed in USD", same position in the formula).
- **Terminal consumption:** `op_6` (`addBulk`, formula `a+b0+...+bn`) consumes `a = i_10` (NOT `o_9`) alongside `b0=o_11`, `b1=o_12`, `b2=o_13`, `b3=o_14`, producing `o_15` ("Assets sum" = `1255501.78`).
- `o_9` is confirmed as a **leaf** per the structural reference data — i.e., no operation ever consumes it. It is computed, displayed, and then silently discarded from the aggregation it should feed.

**Attack flow:** `i_4`,`i_1` → `op_1` → `o_9` (correct BTC-in-USD value, $683,929.93) is computed and stored but never consumed. Simultaneously, an unrelated root variable `i_10` ($752,322.92), bearing an identical display name and business role but with zero computational provenance, is fed directly into `op_6`'s terminal sum in place of `o_9`. The terminal NAV, `o_15 = 1,255,501.78`, is therefore built from four genuinely computed legs (`o_11`,`o_12`,`o_13`,`o_14`) plus one **unverified, substituted** leg (`i_10`) instead of the fifth genuinely computed leg (`o_9`).

#### Details (Topological & Provenance Fraud)
This is the textbook Lineage Disconnection / Context Substitution pattern: a fully-formed, mathematically-verifiable computation chain (`i_4`,`i_1` → `op_1` → `o_9`) exists in the graph and replays perfectly in isolation, which is exactly why naive type/consistency checks pass — `o_9` is a valid `Amount`, correctly rounded, correctly typed. But the terminal aggregation never actually uses it. Instead, a same-named, same-role root value with no computational backing is injected in its place. Local replay of every individual operation (`op_1`–`op_6`) succeeds, masking the fact that the reported NAV does not derive from the graph's own BTC pricing computation.

**Quantified impact:** Using the computed value instead of the substitute, the correct aggregate would be:
683,929.93 + 202,100.78 + 83,482.41 + 159,606.12 + 57,989.55 = **1,187,108.79**
The reported result is **1,255,501.78** — an inflation of **$68,392.99** (≈5.76% of the correct NAV), entirely attributable to swapping in `i_10` for `o_9`. This is a material, single-point, surgical substitution exactly matching the attack vector's definition, and it is not explained or reconciled anywhere else in the graph (no transformation node, no adjustment entry, no metadata bridging the two BTC-in-USD figures).

#### Anomaly Localization (Precision and Scale Tampering)
None material. All five `convert` operations (`op_1`–`op_5`) and the final `addBulk` (`op_6`) were recomputed with exact rational arithmetic and match the reported values exactly under the declared DOWN/truncate-to-currency-precision convention stated in the descriptor. No salami-slicing, no premature truncation, no scale degradation detected.

#### Details (Precision and Scale Tampering)
The descriptor's global rounding rule (`DOWN`, truncate to the currency's native decimal precision) is applied uniformly and correctly across every conversion step and the final summation. There is no evidence of a directional-mode skim, coarser-than-expected MathContext, or mid-pipeline downcast. This vector is clean; the only numerical discrepancy in the graph is the vector-1 substitution, not a rounding artifact.

#### Anomaly Localization (Semantic Type and Context Cast)
None independently identified. `i_10` and `o_9` share the identical declared business label ("BTC amount in USD"), currency (`USD`), and `valueClass` (`io.compprov.examples.nav.model.Amount`); there is no relabeling of a different underlying quantity (e.g., gross vs. net, risk multiplier vs. discount factor) — the fraud here is provenance substitution (wrong source for the same declared quantity), not a semantic relabeling of a differently-defined quantity. This is properly scoped under Vector 1.

#### Details (Semantic Type and Context Cast)
No explicit contradiction between a variable's originating domain definition and its downstream consumption was found beyond the lineage issue already reported. All other conversions (`ETH`, `USDC` balances across Binance/Stake/Morpho) maintain consistent descriptor semantics from source to terminal sum.