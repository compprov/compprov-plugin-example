# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: 0 — No omission, substitution, or double counting detected; all root parameters are legitimately reused across independent, correctly-labeled branches, and both leaves (o_22, o_25) are genuine, non-duplicated aggregates.
- **Precision and Scale Tampering**: 3 — Single consistent MathContext (precision 16, HALF_EVEN) used uniformly across every operation; all recomputed values match exactly with no residual leakage, directional rounding, or scale degradation.
- **Semantic Type and Context Cast**: 0 — Every variable's descriptor name and role (mass, velocity, before/after state) is preserved consistently from root through to the terminal momentum checks; no relabeling or implicit context shift detected.

#### Anomaly Localization (If Detected)
None. Full trace of the DAG:
- Roots i_2 (m1=3.0), i_3 (v1=4.0), i_4 (m2=5.0), i_5 (v2=-2.0), i_6 (constant 2), and i_1 (MathContext, precision 16 HALF_EVEN) feed two symmetric branches computing the post-collision velocities via the standard 1-D elastic collision formulas:
  - v1' = ((m1-m2)v1 + 2·m2·v2) / (m1+m2) → o_8→o_9, o_10→o_11, o_12, o_7, o_13 = -3.5
  - v2' = ((m2-m1)v2 + 2·m1·v1) / (m1+m2) → o_14→o_15, o_16→o_17, o_18, o_7, o_19 = 2.5
- These are both verified exactly against rational arithmetic (e.g., -28.00/8.0 = -3.5, 20.00/8.0 = 2.5) with no rounding discrepancy.
- Momentum-before (o_20, o_21 → o_22 = 2.00) and momentum-after (o_23, o_24 → o_25 = 2.00) are computed independently from the same mass/velocity roots and the newly derived v1'/v2', converging to identical values, correctly demonstrating momentum conservation.
- The reuse of i_2, i_3, i_4, i_5, i_6, and o_7 across multiple operations is structurally necessary (each mass/velocity feeds both the v1' and v2' branch, and o_7 is the shared denominator for both final velocities) and does not constitute double counting, since each use contributes to a distinct, non-overlapping component of the final momentum totals.

#### Details
This is a well-formed, internally consistent physics provenance graph for a 1-D elastic collision. All formulas match the canonical physical equations, the single declared MathContext (precision 16, HALF_EVEN — appropriate for a DECIMAL64-equivalent exact-decimal computation) is applied uniformly to every operation with no directional-rounding or coarser-precision steps, and every recomputed intermediate and terminal value matches the reported value exactly (zero delta). The two leaf variables (total momentum before and after) are independent, legitimate cross-check aggregates that both correctly incorporate all contributing root values exactly once per branch, and descriptor metadata (mass, velocity, before/after collision state) remains coherent and non-contradictory throughout the lineage. No evidence of calculation omission, lineage substitution, double counting, precision/scale tampering, or semantic context-cast was found. Residual risk score is kept non-zero only to account for the narrow scope of the trace (e.g., no explicit kinetic-energy conservation cross-check is present, though none is implied as mandatory by any variable name or metadata) and the general impossibility of absolute certainty from a static review of a single execution instance.