# Summary
- **Verdict**: CLEAN
- **Risk score**: 3.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: 0/100 — No omission, substitution, or double counting detected; all five category contributions converge exactly once into the terminal sum.
- **Precision and Scale Tampering**: 3/100 — All operations replay exactly against the declared DECIMAL64 (precision 16, HALF_EVEN) MathContext at every step, including the final aggregation; no truncation, downcast, or directional rounding found.
- **Semantic Type and Context Cast**: 0/100 — Variable descriptors (Expected/Observed count → Deviation → Squared deviation → Chi-square contribution → Chi-square statistic) remain consistent with their mathematical roles throughout; no relabeling or metadata stripping detected.

#### Anomaly Localization (If Detected)
None. The graph implements a standard 5-category chi-square goodness-of-fit statistic: for each category i, `deviation_i = O_i - E_i` (op_1/op_4/op_7/op_10/op_13), `squared_i = deviation_i^2` via `multiply(x,x)` (op_2/op_5/op_8/op_11/op_14 — legitimate squaring, not disguised doubling), `contribution_i = squared_i / E_i` (op_3/op_6/op_9/op_12/op_15), and the five contributions are combined by a single `addBulk` (op_16) into the terminal leaf `o_27`. The reuse of each `E_i` root (i_2, i_4, i_6, i_8, i_10) as both the subtrahend and the divisor is intrinsic to the chi-square formula `(O-E)^2/E`, not double counting — each use serves a distinct algebraic role and both converge into the same (correct) single contribution term, not into the total twice.

#### Details
**Provenance:** The only leaf is `o_27`, and all five independently-computed per-category contributions (`o_14, o_17, o_20, o_23, o_26`) reach it exactly once via `op_16`. No root is left unconsumed to suggest a bypassed computation, and no root substitutes for a computed leaf — there are no duplicate-named leaves and only one leaf overall.

**Precision:** Independent high-precision recomputation of each step (deviation, square, division, and the final 5-way sum) reproduces the reported values to the last digit under DECIMAL64/HALF_EVEN semantics, including correct half-even carry propagation (e.g., `o_26 = 0.9729729729729730`, correctly rounded up from the repeating `...729729729...`). The MathContext is applied uniformly (precision 16, HALF_EVEN) across every operation with no directional mode (DOWN/UP/FLOOR/CEILING) or coarser precision injected at any node, and no wrapper/primitive downcast is present. Precision 16 significant digits is well above the natural resolution of integer counts, so no residual-leakage or salami-slicing pattern is present.

**Semantics:** Descriptor names and metadata (`Expected count`, `Observed count`, `Deviation`, `Squared deviation`, `Chi-square contribution`, `Chi-square statistic`) align exactly with each operation's arithmetic role at every step; no identity/wrapper operation re-labels a quantity's business meaning, and no Gross/Net- or tax-style context shift occurs anywhere in the lineage.

Overall, this is a clean, textbook chi-square statistic computation with no structural, precision, or semantic anomalies identified.