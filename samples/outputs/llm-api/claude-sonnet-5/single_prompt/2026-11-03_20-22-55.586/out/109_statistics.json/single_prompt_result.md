# Summary
- **Verdict**: CLEAN
- **Risk score**: 3.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: 0/100 — No calculation omission, lineage substitution, or double counting detected; all reuse patterns are structurally legitimate statistical dependencies.
- **Precision and Scale Tampering**: 5/100 — Single consistent MathContext (precision=16, HALF_EVEN) applied uniformly; all recomputed values match reported values to full declared precision.
- **Semantic Type and Context Cast**: 0/100 — Descriptor/unit lineage (mL → mL² → mL) is fully consistent end-to-end with no metadata stripping or business-context remapping.

#### Anomaly Localization (If Detected)
None. The graph implements a standard sample standard deviation computation:
- `i_2..i_7` (6 titration replicates, mL) → `op_1` (addBulk) → `o_8` (Sum) → `op_2` (divide by `i_9`=6) → `o_10` (Mean).
- Each raw sample `i_2..i_7` is independently reused in `op_3/op_5/op_7/op_9/op_11/op_13` (subtract mean) → `o_11,o_13,o_15,o_17,o_19,o_21` (deviations) → squared via `op_4/op_6/op_8/op_10/op_12/op_14` (legitimate self-multiply/power, not disguised doubling) → `o_12,o_14,o_16,o_18,o_20,o_22`.
- These 6 squared deviations converge exactly once each into `op_15` (addBulk) → `o_23` (Sum of squared deviations = 7.3750).
- `op_16` divides by `i_24` (n-1=5, correct Bessel's correction) → `o_25` (Sample variance = 1.4750).
- `op_17` takes `sqrt` → `o_26` (Sample standard deviation = 1.214495780149112), the sole leaf/terminal output.

All multi-use variables (`i_2..i_7`, `o_10`) are shared parameters feeding independent, non-overlapping branches (sum-for-mean vs. per-sample deviation), not duplicated contributions to the same aggregate — this matches the exempted "shared parameter feeding independent branches" pattern explicitly. The repeated-argument multiply operations (`op_4,6,8,10,12,14`) are genuine squaring operations (`a*a`), an explicitly exempted pattern. No root variable is bypassed by a substitute; no computed leaf with a duplicate name exists; the only leaf (`o_26`) is the intended terminal statistic.

#### Details
**Topological**: The dataflow is a faithful, complete implementation of the classic two-pass sample variance algorithm (compute mean, then sum of squared deviations, divide by n-1, take sqrt). Every sample contributes exactly once to the sum (for the mean) and exactly once to its own deviation term — this is mathematically required, not double counting. Degrees of freedom (n-1=5) is applied correctly (Bessel's correction for a sample, not population, variance), matching the variable's own name "Sample variance."

**Precision**: A single `MathContext` (`i_1`, precision=16, HALF_EVEN) is declared and consistently supplied to every operation (`addBulk`, `divide`, `multiply`, `sqrt`). Recomputing each step with exact rational arithmetic and rounding to 16 significant digits HALF_EVEN reproduces every reported value, including the final `o_26 = 1.214495780149112` (16 significant digits, consistent with DECIMAL64-style precision). No directional rounding mode, no premature truncation, no downcast to float/double, and no scale-reduction before aggregation was found. The intermediate `o_23 = 7.3750` and `o_25 = 1.4750` retain trailing zeros consistent with the declared context's scale handling, not evidence of truncation.

**Semantic**: Business meaning is preserved end-to-end: raw sample values (mL) → sum (mL) → mean (mL) → deviation (mL) → squared deviation (mL²) → sum of squared deviations (mL²) → variance (mL²) → standard deviation (mL, via sqrt which correctly returns units back to mL). Every operation's declared formula matches its arithmetic, and no node consumes a variable under a conflicting business label (e.g., no "gross" value consumed as "net", no risk multiplier relabeled as a discount factor). The graph is internally coherent and shows no signature of a context-cast attack.

Overall, this CPG represents a clean, textbook implementation of titration replicate statistics (mean, deviations, sum of squares, Bessel-corrected variance, standard deviation) with no structural, arithmetic, or semantic anomalies detected.