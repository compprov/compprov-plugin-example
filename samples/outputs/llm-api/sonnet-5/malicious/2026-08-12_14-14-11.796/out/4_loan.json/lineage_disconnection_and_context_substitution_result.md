# Summary
- **Verdict**: CLEAN
- **Confidence score**: 74.0

## Summary

A full forward-propagation replay of this 6-month amortization schedule was performed, tracing every operation from the true root inputs (`i_1`–`i_5`, `i_9`, `i_13`, `i_17`, `i_22`, `i_26`, `i_30`) through to all four terminal leaf outputs (`o_29`, `o_31`, `o_32`, `o_34`), specifically hunting for the signature of Lineage Disconnection / Context Substitution: an orphaned, correctly-computed variable whose role is quietly filled by a hardcoded or foreign stand-in at a critical downstream junction.

### Chain verification performed
- Principal/interest/balance recursion: `i_5 →(op_1..op_19)→ o_6...o_29` was recomputed by hand month-by-month, including the mid-schedule prepayment fork (`o_20 → op_13 → o_21`, then `o_21` correctly reused by both `op_14` and `op_16` for month 5).
- All intermediate values, including non-trivial MathContext(16, HALF_EVEN) rounding events (e.g. `o_25 = 229738.23326693376` rounding to `229738.2332669338`), were independently re-derived and match the recorded values exactly. This indicates the arithmetic chain itself is authentic and untampered — a sloppy substitution attack would not reproduce HALF_EVEN rounding this precisely.
- `o_31` (Total interest paid) = addBulk of the six correctly-chained interest nodes (`o_6,o_10,o_14,o_18,o_23,o_27`) — matches exactly.
- `o_32` (Total escrow collected) = addBulk of the six root escrow inputs (`i_9,i_13,i_17,i_22,i_26,i_30`) — matches exactly, uses genuine root inputs, no substitute escrow constant found.
- `o_33`/`o_34`: `o_33` = 6×`i_2` (correct), `o_34 = o_33 + i_4` (correct arithmetic for the terms it uses).

### Hijack-pattern check
Per the structural reference data, the name-collision leaf set is empty, and a manual review of every variable name/role (interest, principal, balance, escrow, totals) found no near-duplicate, reworded, or semantically equivalent INPUT masquerading as a computed sibling. Each of the four leaves (`o_29` Ending balance, `o_31` Total interest paid, `o_32` Total escrow collected, `o_34` Total amount paid) is unconsumed, but in each case nothing else in the graph is used **in its place** for the same purpose — the specific signature required by the attack definition (an injected value quietly routed in to replace the orphaned, properly-derived value) is absent for all three of `o_29`, `o_31`, and `o_32`.

### Notable but inconclusive observation
`o_34` ("Total amount paid by borrower") = `o_33 (12000) + i_4 (5000) = 17000`, but never incorporates `o_32` (escrow, 2400). A full reconciliation (`starting principal − ending balance + total interest + escrow = 19400`) shows the true total cash outlay is $19,400, $2,400 higher than the reported headline figure — exactly the escrow amount. This is worth flagging to a human reviewer: it silently omits a fully-computed, valid quantity from the final "total paid" KPI. However, this is an **omission** (nothing is substituted for `o_32` in `op_23`, which is a simple two-argument `add`, not a doctored `addBulk` missing a term) rather than a hardcoded/foreign value being routed in as a lookalike replacement — the specific mechanism defined for this attack vector. It may simply reflect a legitimate scope choice (escrow held in trust vs. loan debt-service total).

## Anomaly Localization (If Detected)
No confirmed instance of Lineage Disconnection / Context Substitution (orphaned computed variable + hardcoded/foreign stand-in consumed in its place) was found. The only irregularity identified — `o_34` excluding `o_32` from the "Total amount paid by borrower" aggregation — does not satisfy the structural signature of the targeted attack (no substitute value fills escrow's role at that junction; it is simply left out of the sum).

## Details
The entire principal/interest amortization chain (`i_5` → `o_6...o_29`) was independently re-derived by hand, including exact MathContext(16, HALF_EVEN) rounding boundary cases, and matches the recorded graph values to the last digit. Every operation consumes the literal `resultId` of its logical predecessor — no operation was found consuming a hardcoded or unrelated variable where the properly-computed one should have been used. The four leaf/terminal outputs are consistent with a legitimate multi-KPI loan summary report (ending balance, total interest, total escrow, total paid), and none of them show a competing hardcoded input assuming their role downstream. The one aggregation gap (`o_34` omitting escrow) is flagged for human review as a potential reporting-completeness issue, but it does not meet the evidentiary bar of a lineage-disconnection/substitution attack as defined, since no foreign value was routed in to replace `o_32`.