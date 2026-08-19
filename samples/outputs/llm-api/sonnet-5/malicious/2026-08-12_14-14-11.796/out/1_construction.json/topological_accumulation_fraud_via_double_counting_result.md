# Summary
- **Verdict**: CLEAN
- **Confidence score**: 82.0

## Anomaly Localization (If Detected)
No genuine double-counting fraud was found. The only variable flagged by the structural reference data as consumed by more than one operation is **o_14** ("Direct cost (materials + labor)"), which feeds:
- `op_6`: `o_16 = o_14 * i_15` (Overhead = Direct Cost × 10%)
- `op_7`: `o_17 = o_14 + o_16` (Cost including overhead = Direct Cost + Overhead)

Tracing forward: `o_14 → op_6 → o_16 → op_7 → o_17` and `o_14 → op_7 → o_17` converge at the same node (`op_7`), giving `o_14` a raw path-multiplicity of 2 into `o_17`, and by extension into the terminal leaf `o_19` (Profit margin = `o_17 * i_18`).

## Details
At face value this matches the mechanical signature described in the attack vector: a single entity (`o_14`) reaching a downstream aggregation (`op_7`) via two distinct paths. However, tracing the *semantics*, not just the topology, shows this is a standard, transparent cost-plus-markup calculation rather than duplicated cash-flow accounting:

- `o_17 = o_14 + (o_14 * 0.10) = o_14 * 1.10`

The second path does not re-inject an independent copy of the Direct Cost value into the sum — it injects a *derived* 10% markup (`o_16`, explicitly labeled "Overhead") that is mathematically dependent on `o_14`, not a duplicate representation of the same underlying transaction. This is the conventional construction-bid pattern: Direct Cost → + Overhead (% of Direct Cost) → + Profit Margin (% of Cost-incl-Overhead). Every intermediate node's `descriptor.name` ("Overhead rate (10%)", "Overhead", "Cost including overhead", "Profit margin rate (15%)", "Profit margin") documents this markup logic explicitly and auditable, satisfying the invariant's carve-out for "explicit, auditable proportional splitting/markup logic."

All root financial entities were checked individually:
- Materials (`i_2..i_5`) → summed exactly once into `o_6`.
- Labor components (`i_7,i_8,i_10,i_11`) → each used exactly once into `o_9`/`o_12`, summed once into `o_13`.
- `o_6` and `o_13` are each consumed exactly once, into `o_14`.
- No variable value reappears under a different `track.id` (no aliasing/passthrough duplication detected), and no near-duplicate names or hidden re-wrapped identities were found among leaves or intermediates.
- All arithmetic (38100, 9900, 4500, 14400, 52500, 5250, 57750, 8662.5) recomputes correctly under the stated `MathContext` (precision 16, HALF_EVEN), with no discrepancy between locally-verified operations and the reported outputs.

The one open observation worth flagging for human review: the graph terminates at "Profit margin" (`o_19`) rather than a final "Total bid price" (Cost incl. overhead + Profit), so the ultimate consolidated bid figure is not explicitly computed in this trace. This is a completeness/design question, not evidence of duplicated entity accounting, and does not itself indicate double counting.

**Conclusion:** The flagged reuse of `o_14` reflects a legitimate multiplicative markup structure (overhead and profit margin layered on direct cost), not a topological double-counting fraud. No entity's raw value is added or subtracted more than once into any rollup.