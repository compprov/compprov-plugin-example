# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Summary
A full forward-propagation replay of this titration-statistics CPG was performed, from the nine true root INPUTs (`i_1` MathContext, `i_2`..`i_7` sample values, `i_9` sample count, `i_24` degrees of freedom) through every intermediate operation to the sole terminal OUTPUT `o_26` (sample standard deviation).

## Anomaly Localization (If Detected)
No lineage disconnection or context-substitution pattern was found.

- **Leaf set** = `[o_26]`. This is the expected single terminal node (final standard deviation) — it is *supposed* to be unconsumed, since it is the reported final result of the pipeline, not an orphaned intermediate that was bypassed in favor of a substitute.
- **Name-collision set** = `[]` (empty) — independently confirmed by manual inspection: no `INPUT` variable anywhere in the graph shares an exact or near-duplicate name, unit, or domain role with `o_26` or with any other computed `OUTPUT` that could serve as a disguised substitute.
- Every computed `OUTPUT` (`o_8, o_10, o_11..o_23, o_25`) is consumed **exactly** by the argument slot of the next logical operation, and in every case the consumed `track.id` matches the `resultId` of the operation that produced it (verified operation-by-operation: `op_1→o_8` consumed by `op_2`'s `a`; `op_2→o_10` consumed by `op_3,5,7,9,11,13`'s `b`; each deviation `o_11/o_13/o_15/o_17/o_19/o_21` consumed by its own squaring op; each squared deviation consumed by `op_15`'s addBulk; `op_15→o_23` consumed by `op_16`'s `a`; `op_16→o_25` consumed by `op_17`'s `a`). No computed variable is left as an unconsumed dead-end while a same-named or same-role foreign/hardcoded value is routed into the next step instead.
- Numerical replay confirms correctness at every step: sum=80.1, mean=13.35, deviations (-0.85, 1.85, -1.55, 0.75, 0.25, -0.45), squared deviations (0.7225, 3.4225, 2.4025, 0.5625, 0.0625, 0.2025), sum-of-squares=7.375, variance=1.475 (7.375/5), and sqrt(1.475)=1.21449578… matching the reported `o_26` to full precision under DECIMAL64/HALF_EVEN.

## Details
The only structurally "unusual" root input is `i_24` ("Degrees of freedom, n − 1" = 5), which is supplied directly as a root `INPUT` rather than being derived via a `subtract(i_9, 1)` operation from the sample count `i_9` = 6. This was scrutinized against the attack definition specifically because a hardcoded root INPUT masquerading as a derived quantity is the exact disguise the attack vector describes. However, the invariant's carve-out for genuine constants applies here: there is **no computed sibling variable anywhere in the graph** representing 