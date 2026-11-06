# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud** — Score: 4/100. No omission, substitution, or double counting confirmed; multi-use variables and dual leaves are legitimate structural features of the physics model.
2. **Precision and Scale Tampering** — Score: 3/100. A single consistent MathContext (precision=16, HALF_EVEN) is applied uniformly to every operation; all recomputed values match reported values within expected rounding/ preferred-scale behavior.
3. **Semantic Type and Context Cast** — Score: 2/100. All variable names/units/business meanings remain consistent across every consuming operation; no re-labeling or metadata stripping detected.

Overall this is a clean, internally-consistent two-layer steady-state heat conduction computation (series thermal resistance network with an independent Fourier-law cross-check). No material anomaly found; a few structural patterns that superficially resemble "multi-use" or "omission" red flags were traced and verified to be legitimate, correct physics.

---

#### Anomaly Localization (Topological & Provenance Fraud) — None confirmed
Candidate leads examined and cleared:
- **Leaves `o_19` (Q1) and `o_22` (Q2)**: These are not an omission/duplication pair but two *independently derived* values of the same physical quantity (heat flow through each layer), computed via different chains:
  - `o_19` path: `i_5,i_6 → o_10(R1) → o_12(R_total) → o_13(q) → o_14(ΔT1) [via o_6]`, then `i_5,i_4 → o_17(k1·A)`, `o_17·o_14 → o_18`, `o_18/i_6 → o_19`.
  - `o_22` path: analogous chain through layer‑2 parameters (`i_7, i_8`) and `o_16(ΔT2)`.
  Algebraically, `o_19 = (k1·A)·(q·R1)/d1 = q·A` (k1 and d1 cancel through R1 = d1/k1), and similarly `o_22 = q·A`. Both leaves converge to the same value (122.4 W), which is the expected steady-state energy-conservation identity (Q1 = Q2), not duplicated contribution to a shared total — there is no downstream aggregation node that sums them.
- **Multi-used variables `i_5, i_6, i_7, i_8, o_10, o_14`**: Each appears in two operations, but tracing forward shows the second use is an independent branch (either a parallel physical quantity — e.g., `i_4` used for both `k1·A` and `k2·A` — or a term that algebraically cancels inside the Fourier-law cross-check, e.g., `i_5`/`i_6` canceling as shown above). None of these converge twice into the same aggregate (`o_9`, `o_12`, or any single terminal sum); each multi-use is a legitimate shared parameter or correct re-derivation, matching the "independent branch" exemption.
- **Roots `i_1`–`i_8`**: All are consumed; no superseded/unconsumed root was found, so no lineage-disconnection substitution is present.

#### Details (Topological & Provenance Fraud)
The graph implements a standard two-layer composite-wall conduction model (`ΔT_total → R1, R2 → R_total → q → ΔT1, ΔT2 → interface temp`) and then re-derives the heat flow independently per layer via Fourier's law (`Q = k·A·ΔT/d`) as a self-consistency check. The two leaf outputs (`Q1`, `Q2`) agreeing numerically is the intended verification outcome, not evidence of duplication or omission. All root inputs feed the computation exactly once in their proper algebraic role.

---

#### Anomaly Localization (Precision and Scale Tampering) — None confirmed
- Single `MathContext` (`i_1`: precision 16, HALF_EVEN) referenced by every operation (`op_1`–`op_14`); no directional rounding modes (DOWN/UP/FLOOR/CEILING), no mixed/coarser contexts.
- Recomputed each step with exact rational arithmetic: `o_9=17.0`, `o_10=5/36≈0.1388888888888889`, `o_11=1.25`, `o_12=25/18≈1.388888888888889`, `o_13≈12.24`, `o_14≈1.7`, `o_15=20.3`, `o_16=15.3`, `o_17=7.2`, `o_18=12.24`, `o_19=122.4`, `o_20=0.4`, `o_21=6.12`, `o_22=122.4` — all match reported values to well within single-ULP rounding noise at precision 16 (sub-1e-14 deviations where any exist, e.g., in `o_13`).
- Variable reported scales (e.g., `o_19` carrying 15 significant digits vs. `o_22` carrying 16) reflect normal BigDecimal preferred-scale propagation through division, not evidence of truncation or forced scale reduction.
- Conservation check: `ΔT1 (o_14) + ΔT2 (o_16) = 1.7 + 15.3 = 17.0 = o_9` exactly — no residual leakage.

#### Details (Precision and Scale Tampering)
Precision=16 with HALF_EVEN is appropriate for an engineering/physics quantity (temperatures, resistances, fluxes) and is applied uniformly across the entire pipeline, satisfying the "suitable and consistent context" invariant. No salami-slicing pattern (no scalable transaction population, no directional bias toward a beneficiary) and no premature truncation degrading the aggregation chain were found.

---

#### Anomaly Localization (Semantic Type and Context Cast) — None confirmed
- `i_2` ("Interior temperature") is consumed identically as interior temperature in both `op_1` (ΔT_total) and `op_7` (interface temperature calc) — consistent semantic role.
- `i_3` ("Exterior temperature") consistent across `op_1` and `op_8`.
- `i_5`/`i_6` (k1, d1) and `i_7`/`i_8` (k2, d2) consistently represent the same physical layer properties across both the resistance-network branch and the Fourier-law branch.
- `o_10` (R1), `o_14` (ΔT1) retain identical declared meaning across all consuming operations; no node relabels a quantity (e.g., no "Net" value consumed as "Gross", no risk multiplier consumed as a discount factor).

#### Details (Semantic Type and Context Cast)
Domain metadata (names, units) propagate consistently through every operation with no silent re-mapping, no stripped-then-reinterpreted quantities, and no identity/wrapper passthrough altering business meaning. All type continuity (`BigDecimal → BigDecimal`) is paired with matching semantic continuity throughout the graph.

---

**Conclusion:** This CPG represents a coherent, physically self-consistent steady-state conduction computation with a deliberate, verifiable cross-check (two independent derivations of heat flow that correctly reduce to the same value). No confirmed violation of any of the three attack vectors was identified; residual risk score reflects only the normal baseline uncertainty inherent to auditing without direct source-code access.