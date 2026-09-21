# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 96.0

## Anomaly Localization

**Implicated nodes:** `o_11` (Tier 2 usage portion), `i_7` (Tier 2 rate), `op_7` (multiply → Tier 2 cost), `o_15` (Tier 2 cost, leaf), `op_9` (addBulk → Total energy cost), `o_14` (Tier 1 cost), `o_16` (Tier 3 cost), `o_17` (Total energy cost), `op_10` (add → Total bill), `i_18` (service charge), `o_19` (Total bill, terminal output).

**Flow of the attack:**
1. The tiered-usage logic is computed correctly end-to-end: `o_9`=500 (tier1 usage), `o_10`=1000 (cumulative tier2 usage), `o_11`=500 (tier2 portion, via `op_3`: 1000-500), `o_13`=180 (tier3 floored usage).
2. All three tier costs are correctly computed: `op_6` → `o_14` = 500×0.10 = 50.00 (Tier 1 cost); `op_7` → `o_15` = 500×0.14 = 70.00 (Tier 2 cost); `op_8` → `o_16` = 180×0.18 = 32.40 (Tier 3 cost).
3. The terminal energy-cost aggregation, `op_9` (`addBulk`, formula `(a+b0+...+bn)mc`), only takes arguments `a=o_14` and `b0=o_16` — **`o_15` (Tier 2 cost, $70.00) is never passed into this operation**, despite `addBulk` being explicitly designed to sum an arbitrary list of cost components and despite Tier 2 cost being a mandatory contributor implied by the pipeline's own tiered-billing formula.
4. `o_17` ("Total energy cost") is reported as 82.40 = 50.00 + 32.40, silently skipping the 70.00 Tier 2 cost.
5. `op_10` then adds the service charge `i_18` (12.50) to this incomplete subtotal, producing the terminal output `o_19` ("Total bill") = 94.90, when the correct, fully-propagated result should be 50.00 + 70.00 + 32.40 + 12.50 = **164.90**.
6. `o_15` is left as an unconsumed leaf variable (confirmed by the structural reference data: leaf set = `[o_15, o_19]`), with no downstream consumer anywhere in the graph — the textbook signature of $M=0$ Calculation Omission.

## Details

This is a Calculation Omission ($M=0$) attack. Every individual tier calculation replays correctly in isolation — `op_1` through `op_8` are all arithmetically sound, and a naive per-node replay check would find nothing wrong. The fraud is purely topological: it lives in the *argument list* of the aggregation step `op_9`, which quietly drops one of the three cost components (`o_15`) that its own `addBulk` formula and the domain semantics ("Tier 2 cost" feeding into "Total energy cost") say it must include. Because `o_15` is fully and correctly computed upstream (rate × usage, matching the pattern of `o_14` and `o_16` exactly), the omission cannot be dismissed as a diagnostic-only or reference-only output — its name, units, and position in the tier-cost sequence identify it as a mandatory contributor to `o_17`/`o_19`.

The practical impact: the customer's tiered electricity bill under-reports the true cost by exactly $70.00 (94.90 reported vs. 164.90 correct), a ~74% understatement of the final bill, produced via a single surgical omission in one aggregation step's argument list rather than any incorrect formula or bad input. This is precisely the disguised-attack shape the audit is designed to catch: locally consistent, structurally invisible to a shallow arithmetic check, but a material and unambiguous violation of the $M=1$ invariant for a mandatory cost contributor.