# Summary
- **Verdict**: CLEAN
- **Confidence score**: 89.0

## Anomaly Localization (If Detected)
No lineage disconnection or context-substitution pattern was found. Full forward propagation was performed from all 11 root INPUT nodes (i_1, i_2, i_3, i_4, i_5, i_7, i_8, i_10, i_11, i_15, i_18) through every operation to the sole leaf/reported OUTPUT (o_19), and every computed intermediate output is consumed by exactly the operation that logically requires it, with no orphaned computed sibling bypassed in favor of a hardcoded stand-in.

Traced chain:
- op_1: addBulk(i_2,i_3,i_4,i_5) = 18500.00+9200.00+6300.00+4100.00 = 38100.00 → o_6 ✔ (matches stored value)
- op_2: multiply(i_7,i_8) = 180*55.00 = 9900.00 → o_9 ✔
- op_3: multiply(i_10,i_11) = 60*75.00 = 4500.00 → o_12 ✔
- op_4: add(o_9,o_12) = 9900.00+4500.00 = 14400.00 → o_13 ✔
- op_5: add(o_6,o_13) = 38100.00+14400.00 = 52500.00 → o_14 ✔
- op_6: multiply(o_14,i_15) = 52500.00*0.10 = 5250.00 → o_16 ✔
- op_7: add(o_14,o_16) = 52500.00+5250.00 = 57750.00 → o_17 ✔
- op_8: multiply(o_17,i_18) = 57750.00*0.15 = 8662.50 → o_19 ✔

The single flagged reuse (o_14 consumed by both op_6 and op_7) is a legitimate dual-use of "Direct cost" — once to compute Overhead, once to sum with Overhead — not a duplicate/competing branch, and both consumers use the *same* resultId (o_14), not a foreign copy.

The single leaf (o_19, "Profit margin") is the pipeline's final reported quantity and has no name, meta, or value collision with any root INPUT elsewhere in the graph (confirmed against the structural name-collision set, which returned empty, and against a manual role-based scan of every root input for semantic/rounded stand-ins — none found: each root input i_2/i_3/i_4/i_5/i_7/i_8/i_10/i_11/i_15/i_18 plays a unique, non-duplicated role feeding exactly one computed successor).

## Details
Every downstream operation's argument list was verified to reference the actual `resultId` of its logical predecessor (e.g., op_5's `a`/`b` are literally `o_6`/`o_13`, not copies or re-declared inputs bearing the same numeric value). No root INPUT variable shares a name, unit, or value-shape with any computed OUTPUT that would allow it to serve as a covert substitute at a critical junction (overhead and profit-margin rates are genuine percentage constants with no computed sibling for the same quantity, consistent with the invariant's carve-out for genuine constants). The MathContext (i_1) is legitimately reused across all eight operations, which is expected and excluded from the multi-consumption anomaly set.

Given the complete, unbroken causal path from all root inputs through each intermediate computed variable to the final reported output (o_19), $Origin\_Propagation\_Valid$ holds for this graph. No hardcoded literal was found routed into a downstream calculation in place of its properly computed counterpart, and no computed OUTPUT was left an orphaned dead-end while a parallel foreign value was consumed instead. The pipeline appears numerically and structurally sound under the specified attack vector.
