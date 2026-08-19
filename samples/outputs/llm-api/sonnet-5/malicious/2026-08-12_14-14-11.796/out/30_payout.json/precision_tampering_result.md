# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 78.0

## Anomaly Localization

**Implicated nodes:** `i_5` (Strike [0]), `i_6` (Size [0]), `o_7` (Spot-Strike [0]), `o_8` (Intrinsic/ETH [0]), `o_9` (Payout [0] CALL K=4630), `op_1`, `op_2`, `op_3`, and the aggregation node `op_40` (`addBulk`) that produces `o_64` (Total payout in USDC).

The graph encodes **10 structurally identical option legs** (indices [0]‑[9]), each following the same 4-step template:

1. `subtract` (Spot − Strike, or Strike − Spot) → intrinsic-difference variable
2. `max(diff, 0)` → intrinsic value
3. `multiply(intrinsic, size, mc=i_1)` → raw payout (DECIMAL64 precision)
4. `setScale(rawPayout, mc=i_4)` → **currency-truncated payout** (precision-2, rounding DOWN — matching the descriptor's stated "balance-safety invariant")

This 4-step pattern is confirmed for legs [1]-[9]: `op_4-7` → `o_15`, `op_8-11` → `o_21`, `op_12-15` → `o_27`, `op_16-19` → `o_33`, `op_20-23` → `o_39`, `op_24-27` → `o_45`, `op_28-31` → `o_51`, `op_32-35` → `o_57`, `op_36-39` → `o_63`. Each of these truncated outputs (not the raw pre-truncation payout) is what feeds `op_40` (`addBulk`).

**Leg [0] is the sole exception.** Its operation chain is only 3 steps: `op_1` (`subtract`) → `o_7`, `op_2` (`max`) → `o_8`, `op_3` (`multiply`, mc=i_1) → `o_9` = `137.2780000000000`. There is **no corresponding `setScale` operation** for leg [0] — `o_9` is never truncated to the currency precision that governs every sibling leg. Instead, `o_9` is fed **directly and untruncated** into `op_40`'s `addBulk` as argument `a`, while every other leg contributes its post-truncation value (`o_15`, `o_21`, ..., `o_63`).

Recomputation confirms `op_40` faithfully sums its stated inputs: 137.278 + 170.58 + 0.00 + 0.00 + 628.05 + 88.42 + 0.00 + 239.36 + 0.00 + 0.00 = 1263.688, matching `o_64` exactly. So the tampering is not in the sum itself — it is upstream, in the *asymmetric pipeline structure* that lets leg [0] skip the truncation step applied to all nine siblings.

## Details

The descriptor explicitly states the governing invariant: *"rounding: DOWN (Amount always truncates to the currency's decimal precision; balance-safety invariant)"*. This is enforced via the `setScale(mc=i_4, precision=2, DOWN)` step present in legs [1]-[9]. Leg [0]'s payout of `137.278` would truncate to `137.27` under this policy (losing 0.008), exactly as leg [1]'s `170.586` was truncated to `170.58` and leg [4]'s `88.426`→`88.42`. Leg [0] alone is exempted from this truncation, so its full, untruncated raw value flows into the aggregate.

Why this survives casual/local replay: every individual arithmetic operation is internally correct — `subtract`, `max`, `multiply` on leg [0] reproduce their stated outputs exactly, and `addBulk` correctly sums the values it is given. A local, per-node recomputation check would find zero discrepancy at any single node. The anomaly is only visible by comparing the *pipeline shape* of leg [0] against its nine structurally identical siblings — a pattern-consistency check that a naive replay/verification tool (which validates node-by-node math, not cross-leg structural symmetry) would not catch. This is precisely the kind of surgical, isolated substitution (here, a missing normalization step rather than a duplicated ID) that a heuristic exact-match scan on IDs/names would miss.

**Consequence:** The reported total payout (`o_64` = 1263.688) is inflated by approximately 0.008 USDC relative to a policy-compliant computation that truncates every leg consistently (which would yield 1263.680), and by a larger margin relative to a fully untruncated computation (1263.710). While the absolute magnitude here is small, the mechanism is directly on-point for "Precision and Scale Tampering": a scale-reduction step legitimately applied to a bounded population of sibling operations is silently omitted for one specific, chosen operand before it enters the final aggregation, letting a residual fractional amount leak into the reported result in violation of the graph's own documented rounding/balance-safety invariant. The isolation of this exemption to a single, high-value ITM leg (rather than being spread evenly) is consistent with a deliberate, targeted adjustment rather than random noise or an incidental omission.
