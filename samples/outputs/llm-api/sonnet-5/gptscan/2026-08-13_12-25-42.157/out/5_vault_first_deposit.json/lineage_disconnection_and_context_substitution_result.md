# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Anomaly Localization (If Detected)
No instance of Lineage Disconnection / Context Substitution was found in this graph.

The graph contains only two operations:
- `op_1`: `min(i_1, i_2) -> o_4` (Minimum liquidity floor)
- `op_2`: `add(i_3, i_1) -> o_5` (First depositor shares)

Both `o_4` and `o_5` are the two designated OUTPUT/leaf variables of the pipeline. Each is produced directly from genuine root INPUTs (`i_1`, `i_2`, `i_3`) via a single, transparent operation, and each is the *only* computed variable representing its respective quantity ("minimum liquidity floor" and "first depositor shares") anywhere in the graph.

## Details
I specifically checked the attack signature described: a computed OUTPUT left as an orphaned dead-end while a same-named or same-role hardcoded/root INPUT is substituted downstream in its place.

1. **Name-collision set is empty** — no leaf shares an exact display name with another variable, so the primary structural trigger does not fire.
2. **Manual role/value scan for near-duplicates**: `i_2` ("Minimum liquidity constant" = 1000) and `o_4` ("Minimum liquidity floor" = 1000) share a numeric value, but they are not competing representations of the same downstream-consumed quantity — `o_4` *is* the final reported output (a leaf), not an intermediate that gets bypassed in favor of `i_2`. `i_2` is consumed only once, as an argument to the very operation that produces `o_4`; it is never separately routed to a final output in place of `o_4`.
3. Similarly, `i_1` (1000000) and `o_5` (1000000) coincide numerically only because `i_3 = 0` in this specific first-deposit scenario (`add(0, 1000000) = 1000000`). `i_1` is legitimately consumed by `op_2` as the actual documented computation step producing `o_5` — this is direct, undisguised reuse (also flagged structurally as consumed-by-multiple-operations, which is expected here since `i_1` feeds both `op_1` and `op_2` legitimately), not a substitution of a hardcoded stand-in for a bypassed computed sibling.
4. Both `o_4` and `o_5` are leaves (never consumed downstream) — but critically, they are the graph's *terminal reported outputs themselves*, not orphaned computed variables whose role was usurped by a parallel hardcoded value elsewhere. There is no third variable in the graph that duplicates either output's role and *is* consumed downstream in its place. The invariant violation pattern ("properly-computed variable shows zero downstream consumers because a substitute was routed instead") requires a competing consumed variable serving the same purpose reaching a final output — no such competing variable exists here.
5. No `MathContext`, precision, or hidden constant nodes are present that could mask a silent override.

Given the graph's small size, full density was inspected (2 operations, 5 variables) — there is no room for a concealed rupture to hide; every edge was traced end-to-end from root to leaf.

**Consequence**: No evidence that either final output diverges from its own full computation chain. `Origin_Propagation_Valid` holds for both `o_4` and `o_5`.

One residual observation (not a lineage/tampering finding, flagged for completeness): the `add` formula for `o_5` does not subtract the locked minimum liquidity (standard Uniswap V2 first-deposit logic would mint `deposit - MINIMUM_LIQUIDITY` shares to avoid the floor being double-counted). This is a potential business-logic/formula-design concern, but it is applied uniformly and transparently via the graph's own declared formula metadata (`a+b`) — it is not a provenance-severing substitution, and is outside the scope of this lineage-disconnection audit.