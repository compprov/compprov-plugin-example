# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 90.0

## Anomaly Localization

**Implicated nodes:** `i_7` ("Seconds per hour" = 3600), `o_14` ("Orbital period, T (s) (computed, unused)" = 86166.72260766155), `i_15` ("Orbital period, T (hours)" = 23.935), and the operation chain `op_1`\14` (`op_1`→`op_6`).

The graph faithfully executes Kepler's Third Law end-to-end:

- `op_1`: G × M → `o_8` (398571280000000.0) ✔ matches 6.674E-11 × 5.972E24
- `op_2`: r³ → `o_10` ✔
- `op_3`: r³/(GM) → `o_11` (188069951.5201998) ✔
- `op_4`: sqrt → `o_12` (13713.85983303752) ✔
- `op_5`: 2π → `o_13` (6.28318530717958) ✔
- `op_6`: 2π·sqrt(r³/GM) → `o_14` = 86166.72260766155 s ✔ (numerically consistent with all upstream values)

This chain correctly and transparently produces the orbital period **in seconds** (`o_14`). Converting that value to hours (86166.7226 / 3600 = 23.9352006…) is exactly what `i_15` (23.935) represents, and `i_7` ("Seconds per hour" = 3600) is precisely the conversion constant that *should* be consumed by a `divide(o_14, i_7)` operation to produce that hour-denominated figure.

However, **no such operation exists in the `operations` array.** `i_7` is a root INPUT that is never consumed by any operation (it is listed as a leaf). `o_14` is likewise a leaf — its own descriptor explicitly labels it "(computed, unused)", an unusually candid admission that the correctly-computed seconds-based period is discarded. Meanwhile `i_15` — the value that should be the *output* of dividing `o_14` by `i_7` — is instead injected as a **root INPUT with no producing operation at all**, hard-coded to the (truncated) correct answer.

## Details

This is a textbook Calculation Omission / severed-provenance pattern:

1. The pipeline computes the verifiable, auditable result (`o_14`, seconds) through a fully traceable operation chain from raw physical inputs (r, G, M, π).
2. The mandatory final step — unit conversion of that verified result into the reported unit (hours), using the very constant (`i_7`) provided for that purpose — is never performed as an operation.
3. Instead, the number that conversion *would* produce is smuggled in as a disconnected `INPUT` variable (`i_15`), bearing no causal edge back to `o_14`, `i_7`, or any operation whatsoever.

Because `i_15` is untethered from the computation graph, it can be silently substituted with any value — favorable or otherwise — without invalidating any operation-level replay check: every operation in the graph will still validate correctly in isolation, since none of them touch `i_15`. An auditor who only checks that each operation's output is mathematically consistent with its own inputs would see a perfectly clean trace (`op_1`–`op_6`) and never notice that the number actually being reported downstream (`i_15`, presumably consumed outside this trace as the "final" period in hours) has zero causal linkage to the physics computed inside this graph. This is precisely the "correctly computed in an isolated subgraph, then silently excluded/replaced at final aggregation" pattern the omission-detection invariant targets — with the added aggravation that the replacement value is presented as an `INPUT` rather than an `OUTPUT`, actively obscuring the fact that it should have been derived rather than asserted.

**Impact:** The pipeline's reported orbital period in hours (`i_15` = 23.935) is not provably derived from the verified orbital-mechanics computation. Any tampering with `i_2`–`i_5` (r, G, M, π) upstream would change `o_14` but leave the reported `i_15` completely unaffected, since it is a hard-coded constant with no dependency edges — defeating the entire purpose of the provenance trace.