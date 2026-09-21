# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 96.0

## Anomaly Localization

**Primary Finding — Materially Broken Asset Conservation in `op_6` (addBulk → `o_14`)**

Implicated nodes: `o_9`, `o_10`, `o_11`, `o_12`, `o_13` (inputs to op_6), `op_6` (addBulk), `o_14` ("Assets sum", final OUTPUT/leaf).

Recomputing the aggregation exactly (in cents to avoid floating error):

| Operand | Value (USD) |
|---|---|
| o_9 (BTC→USD) | 146,948.10 |
| o_10 (ETH Binance→USD) | 48,624.45 |
| o_11 (USDC Binance→USD) | 538.22 |
| o_12 (ETH Staked→USD) | 12,312.36 |
| o_13 (USDC Morpho→USD) | 223,326.04 |
| **Exact sum** | **431,749.17** |
| **Reported `o_14`** | **441,749.17** |

Δ = **$10,000.00** — a flat, exact $10,000 overstatement injected at the final aggregation step. This is not a sub-cent or single-ULP rounding artifact; it is two orders of magnitude larger than any plausible rounding-mode disagreement and represents an outright violation of the asset-conservation invariant (`o_14` must equal the sum of its declared operands, `o_9..o_13`). The flow is: five legitimately-converted USD-denominated asset legs (`o_9`–`o_13`) are fed into `op_6` (`addBulk`), whose declared formula is `a+b0+...+bn`, yet the emitted `resultId` (`o_14`) does not correspond to that formula's output — it is inflated by exactly $10,000, and `o_14` is a terminal leaf node (never consumed downstream), meaning nothing in the graph re-validates or corrects this figure before it becomes the reported NAV.

**Secondary Finding — Systematic Downward Truncation in `convert` Operations (`op_2`, `op_3`, `op_5`)**

Implicated nodes: `i_2`,`i_3`,`i_5`,`i_6`,`i_8` → `op_2`,`op_3`,`op_5` → `o_10`,`o_11`,`o_13`.

- `op_2`: 23.34 × 2083.31 = 48,624.4554 exactly → correct round-to-nearest cent = 48,624.46; reported `o_10` = 48,624.45 (Δ = -0.01, truncated down despite thousandths digit = 5, unambiguously above the halfway point).
- `op_3`: 532.9 × 1.01 = 538.229 exactly → correct rounding = 538.23; reported `o_11` = 538.22 (Δ = -0.01, truncated down despite thousandths digit = 9).
- `op_5`: 221,114.9 × 1.01 = 223,326.049 exactly → correct rounding = 223,326.05; reported `o_13` = 223,326.04 (Δ = -0.01, truncated down despite thousandths digit = 9).

By contrast, `op_1` and `op_4` land on exact rounding boundaries where truncation and round-to-nearest coincide (thousandths digits 3 and 2 respectively), producing no visible discrepancy — consistent with a floor/DOWN rounding convention being applied uniformly across all five `convert` calls, but only becoming detectable in the three cases where the true value crosses the half-cent threshold.

## Details

**Op_6 (addBulk) — why it matters most:** This is a clean, unambiguous arithmetic failure at the aggregation layer, not a rounding-convention artifact. The invariant that `resultId` must equal the declared `formula` applied to `arguments` is broken by a flat $10,000, i.e. ~2.3% of the correctly-computed $431,749.17 NAV. Because `o_14` is the terminal leaf of the graph (the reported NAV figure consumers see), this single operation is sufficient to materially misstate the fund's reported net asset value regardless of any other findings. A casual check of individual `convert` steps would look clean; only recomputing the final `addBulk` against its own declared inputs surfaces the injected discrepancy. Per the Materiality Override in the invariants, this scale of error mandates a high risk score independent of cyclicity — this appears to be a one-shot NAV calculation, but a $10,000 injected value at the aggregation boundary is a severe, standalone exploit vector (e.g., a classic "phantom asset" or plug figure inserted post-aggregation).

**Op_2/3/5 truncation pattern — why it's flagged but scored lower:** Each individual delta (-$0.01) is within the "1 unit at target scale" ceiling the invariants describe as the mathematical maximum divergence between truncate and round-to-nearest conventions, and no `MathContext` argument is present on these `convert` calls to establish authoritative rounding-mode ground truth — so no single instance is a confirmed violation in isolation. However, the *directionality* is perfectly one-sided (down, never up) across all cases where it's distinguishable, which is the fingerprint of a DOWN/FLOOR rounding mode substituted for HALF_UP/HALF_EVEN. Per the invariants, genuine Salami Slicing requires a scalable operation population; here there are only 5 structurally-bounded `convert` calls (one per balance line), not a transaction-volume-scaled set, so this alone does not rise to a confirmed exploit — it is reported as a corroborating, lower-confidence irregularity rather than the primary finding. Notably, these three truncations net -$0.03, which does not offset or explain the +$10,000 aggregation error; the two anomalies do not cancel and should be treated as independent issues, with the addBulk discrepancy being decisively the dominant, high-severity concern.