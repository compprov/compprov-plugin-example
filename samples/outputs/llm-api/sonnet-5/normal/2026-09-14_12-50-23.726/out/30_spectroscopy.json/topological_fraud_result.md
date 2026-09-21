# Summary
- **Verdict**: CLEAN
- **Risk score**: 6.0

## Audit Summary
This CPG models a Beer-Lambert calibration series: three calibration points (ε·c·l → Absorbance) followed by two independent cross-check ratios (A2/A1, A3/A1). The graph was audited against the three Topological & Provenance Fraud sub-patterns (Calculation Omission, Double-Counting, Lineage Disconnection/Substitution) using the provided structural reference data as a starting point, then verified manually.

### Terminal Outputs
The graph's true terminal (unconsumed) outputs are `o_13` ("Absorbance ratio A2/A1") and `o_14` ("Absorbance ratio A3/A1"). Both are clearly labeled cross-check ratios by their own descriptor metadata — there is no single scalar "final result" (e.g., a regression slope or aggregate) that these should be rolled up into. The pipeline's own declared purpose (calibration verification) is fully satisfied by these two independent ratio outputs.

### Root Input Coverage (M=0 check)
All six root inputs (`i_1` MathContext, `i_2` ε, `i_3` l, `i_4`/`i_7`/`i_10` concentrations for points 1–3) are consumed by at least one operation and their computed descendants (`o_5/o_6`, `o_8/o_9`, `o_11/o_12`) all propagate forward into the terminal ratios `o_13`/`o_14`. No root or intermediate result is silently dropped. Point-1 absorbance (`o_6`) is not itself a leaf — it is consumed as the denominator in both ratio computations, which is its intended role as the calibration reference point, not an omission.

### Reused Variables (M>1 check)
- `i_2` (ε) and `i_3` (l) are each consumed by three separate multiply operations (one per calibration point). This is legitimate shared-parameter reuse across independent, non-aggregating branches — ε and l are physical constants applied identically to each point, and each branch terminates in a distinct output (there is no summation of these branches into one aggregate where double-counting would occur).
- `o_6` (Absorbance A1) is consumed by both `op_7` and `op_8` as the denominator for `o_13` and `o_14` respectively. This is not double counting: the two consumptions feed two **distinct, independent terminal outputs** (A2/A1 and A3/A1), not a single terminal aggregation that would be inflated/deflated by reusing A1's value twice. This matches the explicitly documented role of A1 as the calibration reference, consistent with the ratio names in the descriptors.

### Lineage / Substitution check (M=1, wrong-source)
No leaf shares an exact or near-duplicate name with another variable (confirmed empty by structural scan, and no plausible semantic near-duplicates found on manual inspection — the three "Concentration (mol/L)" inputs are differentiated unambiguously by `point` metadata 1/2/3 and each feeds only its own branch). No root `INPUT` mirrors a computed sibling's name/units to suggest a hardcoded override. Each divide operation (`op_7`, `op_8`) consumes exactly the `resultId` produced by the immediately preceding multiply step (`o_9`→from `op_4`; `o_12`→from `op_6`; `o_6`→from `op_2`), with no stand-in or foreign constant injected at any junction.

### Arithmetic Verification
All operations replay correctly end-to-end: ε·c for each point (0.15, 0.30, 0.60), then ×l (unchanged, l=1.00) to yield A1=0.15, A2=0.30, A3=0.60, and finally A2/A1=2, A3/A1=4 — all exactly matching stored values and expected physical relationships (ratios equal concentration ratios, as annotated).

## Conclusion
No Calculation Omission, Double-Counting, or Lineage Disconnection pattern was found. Reuse of `i_2`, `i_3`, and `o_6` is structurally necessary and benign (shared physical constants and calibration reference point across independent, non-aggregating output branches). The terminal outputs derive from full forward propagation of the graph's own root inputs through its own computation chain with no substitution. This graph presents as clean.