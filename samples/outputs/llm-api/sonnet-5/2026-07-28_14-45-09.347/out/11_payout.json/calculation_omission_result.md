# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Audit Summary

This CPG models the per-position payout calculation and aggregation for a 10-leg ETH/USDC option book against a single spot fixing (`i_3`, rate=4650).

### Reconstruction of the Independent Calculation

For each of the 10 `OptionPosition` inputs (`i_4`..`i_13`), a `payout` operation (`op_1`..`op_10`) is applied against the shared spot price `i_3`, producing 10 `Amount` outputs (`o_14`..`o_23`). Manually recomputing intrinsic value = max(spot-strike,0)*size for calls, max(strike-spot,0)*size for puts, against each position's strike/size:

- i_4 (CALL 4630, 6.8639): (4650-4630)*6.8639 = 137.278 -> matches o_14
- i_5 (PUT 4710, 2.8431): (4710-4650)*2.8431 = 170.586 -> matches o_15
- i_6 (CALL 4670, 9.0434): OTM -> 0 -> matches o_16
- i_7 (PUT 4550, 2.8299): OTM -> 0 -> matches o_17
- i_8 (PUT 4730, 7.8507): (4730-4650)*7.8507 = 628.056 -> matches o_18
- i_9 (CALL 4630, 4.4213): (4650-4630)*4.4213 = 88.426 -> matches o_19
- i_10 (CALL 4710, 3.927): OTM -> 0 -> matches o_20
- i_11 (PUT 4690, 5.9841): (4690-4650)*5.9841 = 239.364 -> matches o_21
- i_12 (PUT 4590, 8.2771): OTM -> 0 -> matches o_22
- i_13 (PUT 4550, 5.9155): OTM -> 0 -> matches o_23

All 10 per-leg payouts are independently correct.

### Aggregation Check

`op_11` (`addBulk`) consumes arguments `a=o_14, b0=o_15, b1=o_16, ..., b8=o_23` — i.e., all 10 payout outputs, with no gaps in the b0..b8 index sequence, and produces `o_24`. Summing the 10 leg values: 137.278+170.586+0+0+628.056+88.426+0+239.364+0+0 = 1263.710, which matches the reported `Total payout in USDC` (o_24 = 1263.710000) exactly.

### Leaf/Dead-end Review

The structural leaf set flags `i_1` (MathContext, DECIMAL64/precision 16) and `i_2` ("Zero (OTM floor)") as unconsumed. Neither qualifies as a 'mandatory adjustment, deduction, credit, correction, or cross-check' to the final result under the attack definition:
- `i_1` is a rounding-context input that legitimately need not appear as an explicit operation argument (its use is implicit in the wrapper's internal decimal arithmetic), consistent with it being excluded from the 'consumed by multiple operations' list rather than flagged as suspicious.
- `i_2` is a documentation/reference constant for the floor logic embedded inside the opaque `payout()` wrapper call; the correctness of the floor is independently verifiable — every OTM leg (i_6, i_7, i_10, i_12, i_13) correctly reports 0.000000 rather than a negative intrinsic value, confirming the floor was actually applied, not silently skipped. Its non-consumption as an explicit operation argument is a modeling/documentation artifact, not evidence that the floor was omitted from computation.

### Conclusion

All 10 individually computed leg payouts (both CALL 'credit' style and PUT 'credit' style legs — this is a long option book, no leg represents a cost/liability side that was separately computed and dropped) flow into the single `addBulk` aggregation with no missing index, and the arithmetic reproduces the reported total exactly. No variable meeting the definition of a mandatory adjustment (tax, fee, correction, cross-check) exists in the graph as an unconsumed dead-end. No omission pattern is present.
