# Summary
- **Verdict**: UNLINKED DEDUCTION
- **Confidence score**: 85.0

## Anomaly Localization

**Implicated nodes:** `i_28` (Reinsurance recovery rate), `o_27` (Total payout), `o_29` (Reinsurance recovery), `o_30` (Net loss), `op_14` (multiply), `op_15` (add).

Flow of the attack:

1. `op_13` (`addBulk`) correctly sums the three line-of-business payouts (`o_10` Collision 6000.0000, `o_18` Comprehensive 12000.00, `o_26` Liability 4750.0000) into `o_27` = **22750.0000** (Total payout). This is fully correct.
2. `op_14` (`multiply`) correctly computes the reinsurance recovery: `o_27 * i_28` = 22750 × 0.40 = `o_29` = **9100.000000**. The recovery amount is computed transparently and correctly — it is *not* a dead-end (it is consumed exactly once, by `op_15`), so it passes a naive "is this variable ever used?" check.
3. `op_15` is declared `"name": "add"` with `formula: "(a+b)mc"`, consuming `a = o_27` and `b = o_29`, producing `o_30` = **31850.000000**, labeled "Net loss".

The defect is in step 3: standard insurance/reinsurance accounting defines **Net loss = Gross claims paid − Reinsurance recovery** (the recovery is a credit that *offsets* the insurer's retained loss; that is the entire economic purpose of a variable named "Reinsurance recovery" feeding into a variable named "Net loss"). The correct value should be `o_27 - o_29` = 22750 − 9100 = **13650.000000**. Instead, the graph adds the recovery to the gross payout, yielding 31850.000000 — a result that is 18,200 higher than the mathematically-required net figure (double the recovery amount, since the subtraction was replaced with an addition).

## Details

This is a disguised variant of Calculation Omission: rather than leaving `o_29` unconsumed (which the structural leaf-check would immediately flag — and indeed the provided leaf list confirms `o_29` is *not* a leaf), the tampering keeps `o_29` causally wired into the final aggregation operation `op_15`, satisfying any naive "was this mandatory adjustment consumed downstream?" check. However, the operator used to combine it (`add` instead of `subtract`) means the deduction the recovery is supposed to perform on the reported "Net loss" never actually occurs — it is functionally severed from its intended deducting role even though it remains topologically connected. The `formula` metadata (`"(a+b)mc"`) confirms this is not a display/formatting artifact; the wrapper genuinely executed an addition.

Why this passes casual/local replay: every individual operation node (op_1 through op_14) is internally consistent and numerically correct when checked in isolation (subtract, max, multiply, min chains for each claim type; the addBulk total; the recovery multiplication). Only the final composition (`op_15`) is wrong, and because `o_29` is still referenced as an argument (not omitted from the argument list), automated dead-end/unconsumed-variable heuristics do not catch it — the omission is in the *sign/operator* of the aggregation, not in the argument wiring.

Consequence: the reported "Net loss" of 31850.000000 overstates the insurer's true net retained loss by exactly 2× the reinsurance recovery amount (9100 × 2 = 18200) relative to the correct value of 13650.000000. Whether this benefits the reporting entity (e.g., inflated loss ratios used to justify premium increases, reduced taxable income, or overstated reserve/capital requirements) or another party, it represents a materially incorrect final figure produced from otherwise correctly computed intermediate values — precisely the "calculated correctly in an isolated subgraph, then ignored/severed at final aggregation" pattern this audit targets.