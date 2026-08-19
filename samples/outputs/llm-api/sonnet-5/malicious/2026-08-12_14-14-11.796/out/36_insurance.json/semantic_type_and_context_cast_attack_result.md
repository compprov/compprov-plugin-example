# Summary
- **Verdict**: SEMANTIC CAST DETECTED
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:** `o_27` (Total payout), `i_28` (Reinsurance recovery rate), `o_29` (Reinsurance recovery), `op_14` (multiply), `op_15` (add), `o_30` (Net loss)

**Attack flow:**
1. `op_13` (`addBulk`) sums the three claim-type payouts (`o_10`, `o_18`, `o_26`) into `o_27` = **22750.0000**, correctly labeled "Total payout" (a gross, pre-recovery liability figure).
2. `op_14` (`multiply`) computes `o_27 * i_28` = 22750 * 0.40 = **9100.0000**, correctly labeled "Reinsurance recovery" — the amount the insurer expects to recover from its reinsurer, which by definition *offsets* (reduces) the insurer's retained loss.
3. `op_15` (`add`), with an explicit formula annotation `(a+b)mc`, takes `a = o_27` (Total payout) and `b = o_29` (Reinsurance recovery) and **adds** them, producing `o_30` = **31850.0000**, labeled "Net loss."

The technical type chain is flawless: `BigDecimal -> BigDecimal -> BigDecimal`, all operations replay mathematically consistent with their declared formulas, and the `MathContext` (`i_1`) is applied uniformly. A naive replay/type-checking audit would pass this node without complaint.

## Details

The defect is not in the arithmetic — `22750 + 9100 = 31850` is computed exactly as the `add` operation and its formula metadata dictate. The defect is in the **business semantics**: in actuarial/insurance accounting, "Net loss" (the insurer's retained loss after ceding a portion to a reinsurer) must be *Total payout − Reinsurance recovery*, not *Total payout + Reinsurance recovery*. Reinsurance recovery is, by its own declared name and role in `op_14`, a reduction of the insurer's exposure — it cannot legitimately be added to gross payout and still be called "net." Adding it instead inflates the reported liability by exactly double the recovery amount (9100 too high: true net loss should be 22750 − 9100 = 13650, but the graph reports 31850).

This is a textbook Semantic Type and Context Cast: the `add` operation is technically valid, type-safe, and internally consistent with its own formula tag, yet it silently re-maps the business context of the output variable. The value flowing into `o_30` is *not* what its `descriptor.name` (