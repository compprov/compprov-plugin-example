# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Anomaly Localization (If Detected)
No lineage disconnection or context-substitution signature was found. Full trace below for transparency:

- Roots: i_1 (MathContext), i_2 (Asset cost=85000.00), i_3 (Salvage=10000.00), i_4 (Useful life=5), i_7 (Accumulated depreciation start=0)
- op_1: subtract(i_2,i_3,mc=i_1) -> o_5 = 75000.00 (Depreciable base)
- op_2: divide(o_5,i_4,mc=i_1) -> o_6 = 15000.00 (Annual depreciation)
- op_3: add(i_7,o_6) -> o_8 = 15000.00 (Accum Y1)
- op_4: subtract(i_2,o_6) -> o_9 = 70000.00 (Book value Y1)
- op_5: add(o_8,o_6) -> o_10 = 30000.00 (Accum Y2)
- op_6: subtract(o_9,o_6) -> o_11 = 55000.00 (Book value Y2)
- op_7: add(o_10,o_6) -> o_12 = 45000.00 (Accum Y3)
- op_8: subtract(o_11,o_6) -> o_13 = 40000.00 (Book value Y3)
- op_9: add(o_12,o_6) -> o_14 = 60000.00 (Accum Y4)
- op_10: subtract(o_13,o_6) -> o_15 = 25000.00 (Book value Y4)
- op_11: add(o_14,o_6) -> o_16 = 75000.00 (Accum Y5, leaf)
- op_12: subtract(o_15,o_6) -> o_17 = 10000.00 (Book value Y5, leaf)

Every operation's arguments map exactly to the `resultId` of the immediately preceding logical step (or to a genuine root input). The two leaf variables, o_16 and o_17, are the terminal Year-5 accumulated depreciation and book value figures — the natural, expected end of a 5-year straight-line schedule, not orphaned computed twins bypassed in favor of a substitute. No variable in the graph shares a name, role, unit, or near-identical value with either leaf while also being consumed downstream in its place; the name-collision set is empty and manual semantic review of `descriptor.meta`/names for every intermediate (depreciable base, annual depreciation, per-year accumulated depreciation/book value) found no second candidate for the same quantity anywhere else in the graph.

## Details
This is a single, linear (non-branching) depreciation-schedule chain: each accumulated-depreciation/book-value pair for year N is built strictly from the year N-1 result plus the single reused `o_6` (Annual depreciation) and `i_1` (MathContext), which are legitimately reused across multiple operations (as reflected in the multi-consumer list) rather than substituted. Numeric replay confirms internal consistency: depreciable base (75000.00) = cost - salvage; annual depreciation (15000.00) = base/life; the accumulated-depreciation sequence terminates at 75000.00 (== depreciable base) and book value terminates at 10000.00 (== salvage value), exactly as expected for a fully-depreciated asset at the end of its useful life. No hardcoded constant masquerading as a computed intermediate was found feeding any operation; no computed output was found abandoned while a same-named or same-role root INPUT was routed into a downstream calculation instead. The `i_7` root input (Accumulated depreciation start = 0) is a legitimate baseline for year-0, with no computed sibling anywhere in the graph that it could be said to bypass.

Given the small, fully-auditable size of this graph and the absence of any structural or semantic signature matching the Lineage Disconnection / Context Substitution pattern, no violation of the EXPECTED_INVARIANTS was identified. Confidence is not maximal only because dense/adversarial graphs of this style are explicitly designed to resist casual review, and a small residual uncertainty about undisclosed external context is retained.