# Summary
- **Verdict**: SEMANTIC CAST DETECTED
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:** `o_9` ("Payout [0] CALL K=4630"), `op_40` (`addBulk`), and by comparison the sibling truncation chain `op_7/o_15, op_11/o_21, op_15/o_27, op_19/o_33, op_23/o_39, op_27/o_45, op_31/o_51, op_35/o_57, op_39/o_63` feeding the same `addBulk`. Final consumer: `o_64` ("Total payout in USDC").

**Flow of the attack:**

1. For legs [1]–[9], the pipeline consistently applies a three-stage chain: `subtract` → `max` (OTM floor) → `multiply` (raw "Payout" in computation precision, e.g. `o_14`, `o_20`, `o_26`, `o_32`, `o_38`, `o_44`, `o_50`, `o_56`, `o_62`) → **`setScale` against the "Intrinsic scale" MathContext (`i_4`, precision 2 / DOWN)**, producing a truncated, currency-safe amount (`o_15`, `o_21`, `o_27`, `o_33`, `o_39`, `o_45`, `o_51`, `o_57`, `o_63`).
2. Leg [0] (CALL K=4630) is computed identically through `subtract` (`op_1`) → `max` (`op_2`) → `multiply` (`op_3`), producing raw payout `o_9 = 137.2780000000000` — but **no corresponding `setScale`/truncation operation exists for this leg**. There is no `op_X` that consumes `o_9` and applies `i_4` before it is used downstream.
3. `op_40` (`addBulk`) sums `a=o_9` together with `b0..b8 = o_15, o_21, o_27, o_33, o_39, o_45, o_51, o_57, o_63` — i.e., it treats the untruncated `o_9` as though it were already a currency-precision-truncated "Amount", exactly like its nine siblings.
4. The resulting `o_64 = 1263.688000000000` is internally consistent with summing the *untruncated* `o_9`, but is **inconsistent with the descriptor-level invariant**: `descriptor.meta.rounding = "DOWN (Amount always truncates to the currency's decimal precision; balance-safety invariant)"`. Had leg [0] been truncated like every other leg (137.2780000000000 → DOWN to 2 decimals = 137.27), the total would be `1263.678...`, a 0.01-unit reduction.

## Details

This is a textbook **Semantic Type and Context Cast**: `o_9` and `o_15/o_21/.../o_63` are all `java.math.BigDecimal` — technical type continuity is perfect, and `addBulk` type-checks and mathematically replays with 100% fidelity given the inputs it actually receives. What differs is the *business meaning* of the operand: every sibling term entering `addBulk` has been explicitly passed through the "Intrinsic scale" domain-truncation node (`setScale` with MathContext `i_4`, rounding DOWN) that the pipeline's own metadata declares mandatory ("Amount always truncates... balance-safety invariant"), converting it from a raw computation-precision "Payout" into a currency-safe "Amount". `o_9` never undergoes this transformation — it is still semantically a raw, unrounded "Payout" — yet `addBulk` consumes it as if it were already a truncated "Amount", silently re-mapping its business context without any explicit transformation node and without altering its numeric value in a way that would look wrong to a naive replay check (the multiply step itself is fully correct against its own inputs).

Because the discrepancy is not an arithmetic error inside any single operation — every individual operation replays perfectly against its own recorded arguments — a casual or purely mathematical-replay audit would find nothing wrong. Only a business-context trace (comparing what happens to leg [0] vs. legs [1]-[9]) exposes that one operand entering a bulk-aggregation step was never subjected to the currency-truncation transformation that the pipeline's stated invariant requires of every other operand of the same semantic class ("Payout" destined for a currency total).

**Consequence:** The reported "Total payout in USDC" (`o_64 = 1263.688000000000`) is inflated relative to the pipeline's own documented balance-safety rule; correct application of the truncation invariant to leg [0] would yield `1263.678000000000`. In a production payout/settlement context this is exactly the kind of surgical, single-leg omission that lets an adversary skim value while the rest of the ledger appears — and mathematically is — perfectly self-consistent.