# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 90.0

## Anomaly Localization

**Implicated nodes:** `i_3` (Bonus, value 500.00), `o_4` (Gross pay), `o_8` (Taxable income), `o_16` (Federal tax withheld), `o_18` (State tax withheld), `o_19`/`o_20` (post-withholding intermediates), `op_1` (add: i_2+i_3 -> o_4), `op_12` (add: o_20+i_3 -> o_21).

**Flow of the attack:**

1. `i_3` ("Bonus", $500.00) is legitimately consumed once in `op_1` to compute `o_4` ("Gross pay" = base $3500 + bonus $500 = $4000.00).
2. `o_4` flows forward correctly: `o_8` ("Taxable income") = Gross pay − Pretax deductions = $4000 − $350 = $3650.00. This taxable base **includes the bonus**.
3. The graduated-bracket tax engine (`op_4`–`op_8`) and the state-tax calculation (`op_9`) both operate on `o_8`, meaning **the $500 bonus is fully taxed** as part of `o_16` (Federal tax withheld, $563.00) and `o_18` (State tax withheld, $182.50).
4. `o_19` = Taxable income − Federal tax = $3087.00, and `o_20` = `o_19` − State tax = $2904.50 ("After state withholding"). At this point `o_20` is the mathematically complete, fully-taxed net pay figure derivable from every correctly-computed subgraph in the trace.
5. Instead of reporting `o_20` as the final "Net pay", `op_12` re-consumes `i_3` a **second time**: `o_21 = o_20 + i_3 = 2904.50 + 500.00 = 3404.50`.

The structural reference data independently flags `i_3` as consumed by more than one operation (`op_1` and `op_12`) — unlike `o_8` and `o_12`, whose multi-consumption is legitimate fan-out required by the bracket-splitting logic, `i_3`'s reuse has no such legitimate justification: it re-injects the exact same raw, pre-tax bonus value that was already fully absorbed into the taxable-income → withholding → net-pay chain.

## Details

**Mechanism:** The pipeline correctly builds a complete, internally consistent withholding computation: Gross Pay → Pretax Deductions → Taxable Income → Federal Tax → State Tax → Net Pay (`o_20`). That chain alone constitutes the full, self-consistent "complete formula" for net pay as evidenced by the graph's own operations and satisfies every mandatory adjustment (401k, health premium, federal bracket tax, state tax) with an active causal path to the final aggregation. However, the actual result-producing operation (`op_12`) does not report `o_20`; it splices in a second, un-taxed instance of the bonus (`i_3`) on top of the already-bonus-inclusive, already-taxed `o_20`.

This passes casual/local replay because every individual operation is arithmetically correct in isolation (`op_12`'s `(a+b)mc` formula computes exactly what it claims), and `i_3` is a genuine, pre-existing INPUT variable rather than a fabricated one — there is no invalid value, broken formula, or orphaned node to trip a naive integrity check. The tamper is purely topological: a previously-consumed compensation component is looped back into the final aggregation, bypassing the very tax-withholding subgraph (`o_16`, `o_18`) that the graph itself establishes as mandatory for all components of compensation. In effect, $500 of income is disbursed a second time while completely evading the federal and state withholding calculations that were correctly computed and applied to every other dollar of compensation.

**Consequence:** The reported "Net pay" (`o_21` = $3404.50) overstates the correct, fully-tax-compliant net pay (`o_20` = $2904.50) by exactly $500.00 — precisely the bonus amount — because that amount is paid out a second time with zero tax withheld on the duplicate disbursement. This is a quantifiable, materially favorable bias in the final reported result relative to what the graph's own correctly-computed intermediate values establish as complete.