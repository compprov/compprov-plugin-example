# Summary
- **Verdict**: TOPOLOGICAL ANOMALY
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:** `o_9`, `o_10`, `o_11`, `o_12`, `o_13` (the five per-source USD conversions), `op_6` (`addBulk`), and `o_14` ("Assets sum", the terminal output).

**Flow of the discrepancy:**
1. `op_1`–`op_5` convert each balance (`i_4` BTC-Binance, `i_5` ETH-Binance, `i_6` USDC-Binance, `i_7` ETH-Stake, `i_8` USDC-Morpho) into USD, producing `o_9`…`o_13`.
2. `op_6` (`addBulk`) declares exactly five arguments — `a=o_9, b0=o_10, b1=o_11, b2=o_12, b3=o_13` — with no duplicate argument keys and no repeated `track.id` among them. This passes a naive replay/argument-uniqueness check cleanly.
3. Recomputing the arithmetic: `146948.10 + 48624.45 + 538.22 + 12312.36 + 223326.04 = 431749.17` (verified with both the stored, truncated per-branch outputs and with full, un-truncated precision arithmetic on the raw rates/amounts — both give ≈431,749.17–431,749.19).
4. The stored terminal value at `o_14` is **441749.17** — exactly **$10,000.00** higher than the value actually produced by summing the five declared, traced branches.

## Details

Each individual conversion (`op_1`–`op_5`) was independently verified against its source rate and amount and is internally consistent (all use a consistent truncation convention, e.g. `23.34 × 2083.31 = 48624.4554 → 48624.45`, `221114.9 × 1.01 = 223326.049 → 223326.04`, etc.). The rate variables `i_2` (ETH/USD) and `i_3` (USDC/USD) are each consumed by two operations, but this is legitimate: they are applied to two *distinct* balance entities (Binance vs. Stake ETH; Binance vs. Morpho USDC), not to the same underlying entity twice — there is no path multiplicity > 1 for any single root asset feeding the aggregation.

The actual anomaly is at the final aggregation step itself: the `addBulk` operation's declared inputs (`o_9`..`o_13`) sum correctly to ~431,749.17, but the `resultId` variable `o_14` stores 441,749.17 — a value the recorded operation and its recorded arguments cannot produce. This is precisely the failure mode the invariant "Deduplicated sum S_dedup must match reported consolidation S_reported" is designed to catch: the terminal, reported consolidated NAV figure does not equal the deduplicated, traceable sum of the five legitimate branch entities. The extra $10,000 has no corresponding root entity, rate, or path in the graph — it does not match any visible duplicate-path pattern (no argument is repeated in `op_6`), which is exactly why a naive script checking only for duplicate argument IDs or duplicate variable names (as the structural reference data shows: no exact-duplicate leaf names, no duplicate `addBulk` arguments) would miss it. A competent adversary inflated the terminal aggregate node directly rather than duplicating a traceable path, evading argument-level and name-level duplicate checks while still producing the double-counting-style effect of an untraceable extra $10,000 being folded into the reported total assets.

**Consequence:** The reported "Assets sum" (NAV component) is overstated by exactly $10,000.00 relative to what the graph's own recorded computation supports, with no auditable justification (no meta/documentation for an allocation, adjustment, or additional untracked asset). This is a material, unexplained discrepancy between the recorded lineage and the reported consolidated output.