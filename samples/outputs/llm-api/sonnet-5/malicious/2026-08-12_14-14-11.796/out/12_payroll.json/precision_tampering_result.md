# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 93.0

## Anomaly Localization

**Implicated nodes:** `i_3` (Bonus, value 500.00), `o_4` (Gross pay), `o_20` (After state withholding), `op_1` (add), `op_12` (add), `o_21` (Net pay, final output)

**Attack flow:**

1. `op_1`: `add(i_2=3500.00, i_3=500.00) → o_4 = 4000.00` — Bonus is correctly folded into Gross Pay here.
2. Gross pay flows legitimately through the entire pipeline: `o_4 → o_8` (taxable income = 3650.00, via subtracting pretax deductions), through bracket splitting (`o_12`, `o_13`), tax computation (`o_14`, `o_15`, `o_16`), and withholding subtraction (`o_19`, `o_20 = 2904.50`). At every one of these steps the bonus's dollar-value is already embedded in the running balance — this is verified by exact recomputation: 4000.00 − 350.00 = 3650.00 (taxable income), 3650.00 − 563.00 (federal) − 182.50 (state) = 2904.50, which is the fully-taxed, fully-accounted net balance after all legitimate deductions.
3. `op_12`: `add(o_20 = 2904.50, i_3 = 500.00) → o_21 = 3404.50` — the raw `i_3` (Bonus) input variable is pulled a **second time**, directly from the original input node, and added on top of the already bonus-inclusive `o_20`, producing the value reported as "Net pay".

This exactly matches the structurally-flagged fact that `i_3` is consumed by more than one operation (`op_1` and `op_12`) — a legitimate reuse pattern for a `MathContext` node, but here it is a raw monetary INPUT variable being re-injected into a downstream aggregation after its value has already been fully absorbed into the running total.

## Details

**Mechanism:** The bonus amount is deducted from no other line item, appears nowhere as an offsetting subtraction, and is not part of any legitimate "add back non-taxed portion" business rule documented anywhere in the graph's descriptor metadata (no such reconciliation semantics exist in the `descriptor.meta` of `i_3`, `o_20`, or `op_12`). It is simply re-summed into the terminal output. Because each individual operation (`op_1` through `op_12`) is locally correct under HALF_EVEN with the supplied `MathContext` (`i_1`, precision 16), a naive replay-checker that recomputes each operation node-by-node against its own declared arguments will find **zero discrepancy** — every single op's `Δ = 0`. The tampering is not inside any operation's arithmetic; it is in the **choice of which variable is wired as an argument** to the final `add` (`op_12`), silently smuggling a second, unauthorized instance of the bonus value into the terminal sum. This is precisely the kind of surgical, locally-consistent substitution that evades scalar/precision-focused validation while still corrupting the aggregate.

**Consequence — Asset Conservation Violation:** Reported Net Pay = 3404.50, but exact reconciliation of Gross Pay minus all deductions and both tax withholdings yields 2904.50. The discrepancy is exactly $500.00 — not a sub-cent salami-slice, not a single-ULP rounding artifact, but a full, material duplication of the Bonus line item (14.7% of the reported net pay figure). This fails the "Asset conservation must hold across all intermediate steps, at a materiality threshold appropriate to the values involved" invariant outright: $500 in value appears in the final output with no corresponding source or authorization elsewhere in the graph, and it does not cancel out — it accumulates directly into the leaf output (`o_21`), which is the ultimate reported result of the entire computation. This is a textbook example of over-payment injection via double-counting an input variable at the final aggregation step, dressed up as an innocuous `add` operation identical in form to every other correct `add` in the graph.