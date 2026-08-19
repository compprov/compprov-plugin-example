# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Anomaly Localization (If Detected)
No double-counting or topological accumulation fraud was found. The two flagged multi-consumed variables — `i_4` (planet radius, r) and `o_8` (2·G·M) — were traced end-to-end and do not converge on a shared aggregation node.

**Path trace for `i_4`:**
- `i_4` → `op_3` (divide, `o_8/i_4` → `o_9`) → `op_4` (sqrt → `o_10`, *Escape velocity at surface*)
- `i_4` → `op_5` (add, `i_4+i_5` → `o_11`, *Radius at altitude*) → `op_6` (divide, `o_8/o_11` → `o_12`) → `op_7` (sqrt → `o_13`, *Escape velocity at altitude*)

**Path trace for `o_8`:**
- `o_8` → `op_3` → `o_9` → `op_4` → `o_10`
- `o_8` → `op_6` → `o_12` → `op_7` → `o_13`

Both reuse chains terminate at **two distinct, independent leaf/terminal outputs** (`o_10` and `o_13`), per the structural reference data's own leaf-variable list. There is no downstream `add`, `sum`, or rollup operation that consumes both `o_10` and `o_13` (or `o_9` and `o_12`) together. The graph has no single consolidated terminal metric into which these two branches are re-merged.

## Details
The attack vector under audit requires a single root/intermediate financial (here, physical) quantity to be counted more than once *into the same terminal aggregate* — e.g., two paths both feeding one final sum. In this graph:

- `i_4` (surface radius) is legitimately a shared physical constant needed for two independent, parallel calculations: (1) escape velocity at the planet's surface, and (2) escape velocity at a given altitude (which requires `r + altitude` as the effective radius). Reusing `r` in both is physically necessary and analogous to the already-excluded `MathContext` reuse pattern — it is a shared parameter, not a duplicated entity flowing into one rollup.
- `o_8` (2GM, the doubled standard gravitational parameter) is likewise a shared intermediate constant consumed by both the surface-escape-velocity branch (`op_3`) and the altitude-escape-velocity branch (`op_6`). Each consumption produces a *different* physical quantity (`o_9` vs `o_12`), which are never summed or blended — they diverge into two separate sqrt operations producing two separate, independently reported final outputs (`o_10`, `o_13`).
- No `add`/`sum`/aggregation operation exists anywhere in the operation list that takes both `o_9`/`o_12` or `o_10`/`o_13` as joint inputs. The only `add` operation (`op_5`) computes `r + altitude`, a legitimate physical sum with no duplicated entity (its operands `i_4` and `i_5` are distinct root inputs, each consumed exactly once by that operation).
- Full arithmetic replay confirms every output value (`o_7` through `o_13`) is numerically consistent with its stated formula and inputs (verified `G×M`, `2GM`, `2GM/r`, `sqrt`, `r+altitude`, `2GM/(r+alt)`, `sqrt`), so there is no discrepancy between recomputed and reported values that would corroborate a hidden duplication.
- No aliasing/origin-ID masking was found: no leaf or intermediate variable shares a display name or an equivalent value with another node under a different ID (per the structural reference and manual value inspection), so there is no evidence of a re-wrapped/duplicated entity being smuggled in under a fresh `track.id`.

**Consequence assessment:** Since the two branches terminate in two separate, independently reported physical results (surface vs. altitude escape velocity) rather than a single consolidated total, the reuse of `i_4` and `o_8` does not inflate or deflate any single reported aggregate. This is structurally analogous to using the same tax rate or shared constant in two unrelated line-item calculations — expected and auditable, not fraudulent.

Given the domain (orbital mechanics, not a financial consolidation with a single rollup target) and the absence of any joint aggregation node, the reuse patterns flagged by the structural reference data represent legitimate shared-parameter use rather than Topological Accumulation Fraud. Confidence is not maximal only because the audit cannot fully rule out that this trace is a fragment of a larger pipeline where `o_10`/`o_13` are later aggregated elsewhere outside the provided graph.