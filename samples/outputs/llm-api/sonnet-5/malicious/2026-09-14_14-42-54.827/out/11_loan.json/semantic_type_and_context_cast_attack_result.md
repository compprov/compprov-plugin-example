# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 88.0

## Anomaly Localization

**Implicated nodes:** `o_6` (Interest accrued [Month 1]), `o_10`, `o_14`, `o_18`, `o_23`, `o_27` (Interest accrued [Month 2-6]), `op_20` (addBulk), `o_31` (Total interest accrued).

`op_20` is the `addBulk` operation that is supposed to sum the six independent monthly interest figures into the single reported output `o_31` ("Total interest accrued"). Its argument map is:

```
a  -> o_6   (Interest accrued [Month 1])
b0 -> o_6   (Interest accrued [Month 1])  <-- duplicate
b1 -> o_10  (Interest accrued [Month 2])
b2 -> o_14  (Interest accrued [Month 3])
b3 -> o_18  (Interest accrued [Month 4])
b4 -> o_23  (Interest accrued [Month 5])
b5 -> o_27  (Interest accrued [Month 6])
```

The amortization schedule has exactly six distinct monthly interest values (`o_6, o_10, o_14, o_18, o_23, o_27`). A correct "Total interest accrued" aggregation should therefore use exactly six argument slots (as correctly done in the analogous `op_21` escrow-total and `op_22` payment-total operations, which each use six slots for six months, `a, b0..b4`). Instead, `op_20` uses **seven** slots (`a, b0..b5`), with the value bound to `o_6` (Month 1 interest, $960.00) fed into *both* the `a` slot and the `b0` slot.

Numeric confirmation:
- Correct sum of the six distinct monthly interest values = 960.00000 + 955.84 + 951.66336 + 947.4700134400000 + 923.2598934937600 + 918.9529330677352 = **5657.186200001495**
- Reported `o_31` value = **6617.186200001495**
- Delta = **960.0000000000** — exactly equal to `o_6`, the duplicated argument.

## Details

This is a textbook context-cast slipped past naive validation: the operation is 100% type-safe (`BigDecimal` in, `BigDecimal` out via `WrappedBigDecimal`), the `mc` argument is correctly the shared `MathContext`, and every individual argument ID (`o_6`) legitimately resolves to a real, correctly-computed variable in the graph. A schema/connectivity validator sees nothing wrong: all IDs exist, all types match, the arithmetic function (`addBulk`) executed without error, and `o_6` is *already* known to be consumed by multiple operations elsewhere in the graph (it legitimately feeds `op_2` for the Month-1 principal split), so a naive "variable reused across operations" heuristic provides no signal either — the tampering is hidden *inside a single operation's argument list*, not across operations, which is exactly the kind of surgical substitution that evades exact-match/duplicate-ID heuristics.

Semantically, however, `o_6`'s originating business definition is unambiguous: "Interest accrued [Month 1]" — one specific month's interest, already consumed once for that purpose. By binding it a second time into the `b0` slot of the totalizing operation, the pipeline implicitly treats a single real economic event (Month 1's interest charge) as though it were *two independent monthly interest charges*, fabricating a phantom seventh data point in what is supposed to be a strict 6-month aggregation. This is a covert re-labeling of `o_6`'s context — from "the Month 1 interest amount" to "an additional, distinct interest contribution equal in value to Month 1's" — with no explicit transformation node, no metadata justification, and no domain annotation explaining the duplication. It directly violates the invariant that "no operation may consume a variable under a business definition that conflicts with its originating metadata."

**Impact:** The reported "Total interest accrued" (`o_31`) is overstated by exactly $960.00 (Month 1's interest charged twice), a material and directly quantifiable misstatement in a regulated financial disclosure (loan amortization interest total). Any downstream consumer, auditor, or borrower reconciliation relying on `o_31` as the true lifetime/period interest cost would be given a falsified figure, despite the computation graph appearing fully valid under type and connectivity checks. Note that `o_31` is a terminal/leaf output (never consumed further), so the corruption is not self-correcting downstream and is only detectable by recomputing the aggregation from its constituent monthly values, exactly as done above.