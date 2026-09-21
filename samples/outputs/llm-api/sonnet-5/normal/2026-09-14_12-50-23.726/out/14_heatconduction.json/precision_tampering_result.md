# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

## Audit Summary: Precision and Scale Tampering Review

**Scope:** Full recomputation of every operation (op_1–op_14) in the two-layer wall steady-state heat conduction pipeline, using arbitrary-precision rational arithmetic, cross-checked against the declared `MathContext` (`i_1`: precision=16, HALF_EVEN) that is explicitly passed as the `mc` argument to every single operation in the graph.

### Anomaly Localization (If Detected)
No confirmed violation of the stated invariants was found. No variable or operation is flagged.

### Details

**Methodology:** Every operation in this graph explicitly carries an `mc` argument bound to `i_1` (precision 16, HALF_EVEN). Per the invariant discipline, this means the graph *does* provide ground truth for the correct rounding convention at each step, so each result was checked directly against precision-16/HALF_EVEN rounding of the exact rational value, rather than against an assumed default.

Key steps verified by hand with exact rational arithmetic (not floating-point approximation):

- `op_1` (o_9 = i_2 − i_3): 22.0 − 5.0 = 17.0 ✓ exact.
- `op_2` (o_10 = d1/k1): 0.10/0.72 = 0.13888...(repeating). Rounded to 16 sig figs under HALF_EVEN → 0.1388888888888889, matching the recorded value exactly (the trailing 9 is the correct round-up of the infinite repeating 8, not a fabricated digit).
- `op_3` (o_11 = d2/k2): 0.05/0.04 = 1.25 exactly ✓.
- `op_4` (o_12 = o_10+o_11): exact sum 1.3888888888888889 (17 sig figs) correctly rounds (HALF_EVEN, next-digit=9) to 1.388888888888889 ✓.
- `op_5` (o_13 = o_9/o_12): Exact value works out to ≈12.239999999999990208 before final-digit rounding; the 15th decimal digit is 9, forcing a carry-cascade that resolves to exactly 12.24000000000000 at 16 sig figs — matching the recorded value. (An initial truncation-only check would have wrongly suggested a discrepancy here; the carry propagation resolves it, confirming this is *not* tampering.)
- `op_6`–`op_14`: Recomputing ΔT1 (1.7), interface temp (20.3), ΔT2 (15.3), k·A products, and both heat-flow paths (Q1 via layer 1: 12.24×10=122.4 W, and Q2 via layer 2: 0.4×15.3/0.05=122.4 W) all reproduce the recorded values exactly under the declared MathContext.

**Physical/asset conservation check:** For a series (two-layer) steady-state conduction system, energy conservation requires Q1 = Q2 (heat flow through layer 1 must equal heat flow through layer 2 with no loss). The graph reports Q1 = 122.400000000000 (`o_19`) and Q2 = 122.4000000000000 (`o_22`) — numerically identical (122.4 W), only differing in trailing zero count due to normal BigDecimal scale propagation through different operation chains, not a value discrepancy. This conservation check passes cleanly, which is strong evidence against any salami-slicing or residual-leakage tampering, since a skimming exploit would necessarily show Q1 ≠ Q2 or an unaccounted residual.

**Structural reference cross-check:** The variables flagged as multiply-consumed (i_2, i_3, i_4–i_8, o_10, o_14) are all legitimate reuses required by the physics (e.g., k1, A, and ΔT1 are each used in more than one downstream formula for resistance, flux, and heat-flow verification) — this is expected duplication for a self-consistency check (computing Q via two independent paths), not evidence of tampering. No near-duplicate IDs or names suggesting a disguised substitution were found upon manual inspection beyond the mechanical check.

**Conclusion:** Every operation carries an explicit, consistent MathContext; every recomputation (including edge-case carry propagation) matches the recorded output; and the final cross-check of energy conservation (Q1 = Q2) holds exactly. There is no evidence of forced scale reduction, non-standard rounding mode abuse, downcasting, or salami-slicing accumulation. This graph is a clean, internally consistent physics computation.