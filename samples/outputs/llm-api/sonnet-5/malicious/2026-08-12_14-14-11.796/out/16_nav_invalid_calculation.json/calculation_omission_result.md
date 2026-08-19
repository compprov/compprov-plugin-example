# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 89.0

## Anomaly Localization

**Implicated nodes:** `op_6` (addBulk), `o_9`, `o_10`, `o_11`, `o_12`, `o_13` (arguments to op_6), `o_14` (final reported result, "Assets sum").

The pipeline computes five USD-converted asset legs via `convert` operations:

- `op_1`: i_4 (BTC 2.13) × i_1 (68989.72) → `o_9` = 146948.10
- `op_2`: i_5 (ETH 23.34, Binance) × i_2 (2083.31) → `o_10` = 48624.45
- `op_3`: i_6 (USDC 532.90, Binance) × i_3 (1.01) → `o_11` = 538.22
- `op_4`: i_7 (ETH 5.91, Stake) × i_2 (2083.31) → `o_12` = 12312.36
- `op_5`: i_8 (USDC 221114.90, Morpho) × i_3 (1.01) → `o_13` = 223326.04

These five outputs are then fed as the *complete* argument set (`a, b0, b1, b2, b3`) into `op_6` (`addBulk`), whose `resultId` is `o_14`, reported as "Assets sum" = **441749.17**.

Recomputing the sum of the operation's own declared arguments:

```
146948.10 + 48624.45 + 538.22 + 12312.36 + 223326.04 = 431749.17
```

The operation's own inputs sum to **431749.17**, but the stored result `o_14` is **441749.17** — a discrepancy of exactly **$10,000.00**. There is no sixth variable, adjustment, correction, or credit anywhere in the `variables` array that accounts for this $10,000 delta; no leaf or dead-end variable of that magnitude exists in the graph (the only leaf is `o_14` itself). This means the value stored as the pipeline's final NAV/asset total is **not actually the output of the `addBulk` operation it claims to be produced by** — a component (or components) contributing exactly $10,000 to the reported total was excluded from the transparent, auditable aggregation path while still being baked into the number the pipeline reports downstream.

## Details

A naive replay check that only re-verifies each individual `convert` step (op_1–op_5) in isolation would pass, since each conversion is locally self-consistent (aside from minor cent-level rounding, discussed below). A check that only verifies "does `o_14` exist and is it consumed nowhere further (a valid leaf)" would also pass. The attack surfaces only when the aggregation operation's declared arguments are actually re-summed and compared against its own declared result — exactly the kind of casual-review-resistant tampering this audit is meant to catch.

This is a textbook Calculation Omission pattern applied to the final aggregation node itself: the `addBulk` step's argument list (`a=o_9, b0=o_10, b1=o_11, b2=o_12, b3=o_13`) is presented as the full and complete formula for "Assets sum," and the descriptor/formula metadata (`a+b0+...+bn`) asserts this is a simple total of all five legs. Yet the number actually persisted for `o_14` silently reflects an additional, untraceable $10,000 of value that never passed through any operation, was never converted from a rate, and has no corresponding INPUT/OUTPUT variable anywhere in the graph. Either (a) a sixth asset/credit component that should have been part of the NAV was computed off-graph and then folded directly into the final figure while being severed from the visible causal chain, or (b) the result was directly overwritten post-hoc — both scenarios are precisely the "quietly excluded component that biases the final result" behavior the Calculation Omission invariant is designed to catch, and in either case the recorded `resultId` value violates the basic invariant that the final aggregation node's stored value must equal a valid evaluation of its own consumed arguments.

Separately, and of lower severity, three of the five `convert` outputs (`o_10`, `o_11`, `o_13`) are rounded down by exactly one cent relative to correct HALF_UP rounding of the exact products (e.g. 23.34 × 2083.31 = 48624.4554 → should round to 48624.46, not 48624.45), while `o_9` and `o_12` round correctly. This 3-of-5 asymmetric truncation is mildly suspicious but individually immaterial (≤$0.03 total) compared to the $10,000 aggregation gap, and could plausibly be an artifact of a truncating `MathContext`; it is noted for completeness but is not the primary finding.

**Impact:** The reported "Assets sum" (NAV total) overstates the value actually justified by the transparent, auditable computation chain by $10,000.00 — a material and exact-round-number distortion of the final reported figure, consistent with a deliberately injected, untraceable credit bypassing the documented aggregation logic.