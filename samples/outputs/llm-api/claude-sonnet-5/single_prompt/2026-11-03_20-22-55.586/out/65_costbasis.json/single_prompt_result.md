# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Risk score**: 68.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: Score 5/100 — No omission, substitution, or double counting detected; all roots and intermediates trace cleanly to the single terminal leaf `o_22`, and the two multi-use variables (`o_4`,`o_8`,`o_12`,`i_18`) feed legitimately independent branches (cost vs. share-count aggregation; proceeds vs. cost-basis).
- **Precision and Scale Tampering**: Score 68/100 — Confirmed finding: a directional (DOWN) rounding mode at a coarser precision (`i_2`, precision=2) is used to truncate fractional share quantities before they are multiplied into cost figures, while the rest of the graph uses a round-to-nearest context (`i_1`, precision=16, HALF_EVEN). This causes residual share-value leakage that measurably changes the terminal reported result.
- **Semantic Type and Context Cast**: Score 5/100 — Variable naming/metadata (Lot shares → Lot cost → Total shares/cost → Weighted-average cost → Cost basis → Realized gain) is internally consistent; no relabeling, unit swap, or business-context substitution is evident.

---

#### Anomaly Localization (Precision and Scale Tampering)
- **Context node**: `i_2` (MathContext, precision=2, roundingMode=DOWN) — contrasted with `i_1` (MathContext, precision=16, roundingMode=HALF_EVEN) used everywhere else in the graph.
- **Operations using `i_2`**: `op_1` (`i_3`→`o_4`), `op_3` (`i_7`→`o_8`), `op_5` (`i_11`→`o_12`) — each truncates a 6-decimal-place share quantity down to 2 decimal places using a strictly directional (toward-zero) rounding mode.
- **Downstream propagation**: `o_4`,`o_8`,`o_12` feed `op_2/op_4/op_6` (lot cost = truncated shares × price), `op_7` (`o_15`, total shares), and `op_8` via the lot-cost chain (`o_16`, total cost). These feed `op_9` (`o_17`, weighted-average cost/share), `op_11` (`o_21`, cost basis of shares sold), and finally `op_12` (`o_22`, realized gain/loss — the graph's sole terminal output).

**Reconstructed exact-precision arithmetic (using the untruncated, 6-decimal share quantities actually fed in as `i_3`, `i_7`, `i_11`):**

| Lot | Exact cost (shares×price) | Reported cost | Δ |
|---|---|---|---|
| 1 | 9615.410976 | 9613.5350 (`o_6`) | 1.875976 |
| 2 | 22910.038657 | 22908.6225 (`o_10`) | 1.416157 |
| 3 | 42348.991950 | 42346.5000 (`o_14`) | 2.491950 |
| **Total** | **74874.441583** | **74868.6575 (`o_16`)** | **5.784083** |

- Exact total shares = 135.804415 vs. reported `o_15` = 135.79 (Δ = 0.014415 shares silently discarded, never re-surfaced anywhere in the graph).
- Exact weighted-average cost/share = 74874.441583 / 135.804415 ≈ 551.340257 vs. reported `o_17` = 551.3561933868473 (reported value is ~0.0159 **higher** than the economically correct figure).
- Exact cost basis of 60 shares sold = 60 × 551.340257 ≈ 33080.4154 vs. reported `o_21` = 33081.37160321084 (overstated by ≈ 0.9562).
- Exact realized gain = 39000.00 − 33080.4154 ≈ 5919.5846 vs. reported terminal `o_22` = 5918.62839678916 — **understated by ≈ $0.96**.

#### Details (Precision and Scale Tampering)
The graph truncates each lot's true fractional share count (recorded to 6 decimal places, e.g. `i_3 = 45.678912`) down to 2 decimal places using `MathContext(precision=2, DOWN)` before that truncated figure — not the true share count — is multiplied by price to build lot cost, summed into total shares/cost, and carried into the weighted-average cost basis used for the sale. Locally, every operation replays exactly against its own declared `mc`, so naive validation (type checks + per-step replay) passes cleanly. However:
- `DOWN` is a directional rounding mode, inconsistent with the `HALF_EVEN` convention used everywhere else in the graph (`i_1`), and a precision of 2 is markedly coarser than the 16-digit context otherwise applied — exactly the pattern the precision invariant calls out as a finding when it changes a value reaching a reported result (verified above: it does, by ≈$0.96 on the terminal output).
- Because rounding is always toward zero, the share fraction lost (0.008912, 0.004156, 0.001347 shares per lot) is *never credited back* anywhere in the graph — a textbook 