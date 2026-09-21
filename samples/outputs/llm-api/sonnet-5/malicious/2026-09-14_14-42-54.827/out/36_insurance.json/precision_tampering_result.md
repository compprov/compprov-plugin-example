# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 84.0

## Anomaly Localization

**Implicated nodes:** `o_27` (Total payout), `i_28` (Reinsurance recovery rate), `o_29` (Reinsurance recovery), `op_15` (add), `o_30` (Net loss).

Flow of the anomaly:

1. `op_13` (`addBulk`) sums the three individual claim payouts (`o_10`=6000.0000, `o_18`=12000.00, `o_26`=4750.0000) into `o_27` = **22750.0000** ("Total payout") — arithmetically verified correct.
2. `op_14` (`multiply`) computes `o_27 * i_28(0.40)` = **9100.000000**, stored in `o_29` ("Reinsurance recovery") — arithmetically verified correct.
3. `op_15` is labeled `add`, formula `(a+b)mc`, with `a=o_27` (Total payout) and `b=o_29` (Reinsurance recovery), producing `o_30` = **31850.000000** ("Net loss").

In every standard claims/reinsurance accounting model, reinsurance recovery is money recovered *from* the reinsurer that *offsets* the insurer's retained loss. The correct relationship is:

`Net loss = Total payout − Reinsurance recovery = 22750.0000 − 9100.000000 = 13650.000000`

Instead, the graph performs **addition**, yielding `31850.000000` — more than double the mathematically/semantically correct net loss (overstated by exactly 2× the recovery amount, 18200.000000).

## Details

All of the per-claim arithmetic (subtract → max(floor) → multiply(coinsurance) → min(policy-limit-cap)) was independently recomputed against the declared `MathContext` (`precision=16, HALF_EVEN`) for all three claim types (Collision, Comprehensive, Liability) and for the `addBulk` aggregation into `o_27` and the `multiply` into `o_29`. Every one of these steps reproduces the stored value exactly — there is no scale truncation, no salami-slicing residue, and no rounding-mode deviation anywhere in the per-claim pipeline. The precision-degradation attack surface (mid-pipeline downcasting, premature truncation before aggregation, non-standard rounding drains) is **not present** in this graph.

The actual defect is a **single substituted operator** at the final aggregation step: `op_15` uses `add` where the domain semantics (as declared by the variable names "Total payout", "Reinsurance recovery", "Net loss") unambiguously require `subtract`. This is exactly the kind of surgical, single-operation substitution that evades naive structural checks (no duplicate IDs, no duplicate names, no argument reuse beyond the expected `o_27` fan-out already flagged structurally) while producing a materially wrong final output.

**Consequence:** The reported "Net loss" is inflated by the full amount of the reinsurance recovery instead of being reduced by it — a violation of the asset-conservation invariant (recovered funds should net against the insurer's obligation, not compound it). Downstream, this artificially doubles the counted loss for a book of business that already recovered 40% of its payout via reinsurance. This has direct, material implications for loss-ratio reporting, reserve calculations, regulatory capital requirements, and reinsurance treaty settlement — any of which could be exploited to misstate financial exposure (e.g., overstating losses to justify higher premium filings/reserves, or masking the true risk retained after reinsurance). Given the single, high-value, non-repeating nature of this operation (it is not a per-transaction/scalable skim but a one-time aggregation step), the flaw is scored primarily on **materiality** rather than cyclic accumulation, per the stated invariant that a large single-execution arithmetic/logic error is high-risk regardless of repetition frequency.