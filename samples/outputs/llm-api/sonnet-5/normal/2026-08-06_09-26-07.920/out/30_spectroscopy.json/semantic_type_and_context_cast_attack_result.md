# Summary
- **Verdict**: CLEAN
- **Confidence score**: 83.0

## Summary
This CPG encodes a three-point Beer-Lambert calibration series: molar absorptivity (ε, constant), path length (l, constant), and three concentration measurements (c1, c2, c3), from which εc products, absorbances (A1, A2, A3), and calibration ratios (A2/A1, A3/A1) are derived.

## Semantic / Context Trace
- **i_2 (ε, L/(mol·cm))**: consumed identically as the multiplicand in op_1, op_3, op_5 — always in the role of molar absorptivity against a concentration operand. No re-labeling detected.
- **i_3 (l, cm)**: consumed identically in op_2, op_4, op_6 — always as the path-length multiplier applied to an εc intermediate to yield absorbance. Dimensionally consistent: (L·mol⁻¹·cm⁻¹) × (mol·L⁻¹) = cm⁻¹, then × cm = dimensionless absorbance. This matches physical Beer-Lambert semantics throughout.
- **i_4, i_7, i_10 (concentrations, point 1/2/3)**: each tagged with a `point` meta key, consumed exactly once each, feeding only their corresponding εc/absorbance chain. No cross-point contamination.
- **o_6 (A1)**: reused as the denominator in both op_7 (A2/A1) and op_8 (A3/A1) — a legitimate, single, well-defined reference role for ratio calculations, not a silent re-definition.
- **o_13, o_14**: labeled explicitly as ratios "expect ≈ c2/c1" and "expect ≈ c3/c1"; the declared expectation matches the actual concentration ratios (2 and 4 respectively) and the actual computed absorbance ratios. No mismatch between declared and realized business meaning.

## Numeric Cross-Check
- εc1 = 15000×0.00001 = 0.15 ✓; A1 = 0.15×1.00 = 0.15 ✓
- εc2 = 15000×0.00002 = 0.30 ✓; A2 = 0.30×1.00 = 0.30 ✓
- εc3 = 15000×0.00004 = 0.60 ✓; A3 = 0.60×1.00 = 0.60 ✓
- A2/A1 = 2 (matches c2/c1 = 2) ✓; A3/A1 = 4 (matches c3/c1 = 4) ✓
- MathContext i_1 ("DECIMAL64", precision 16, HALF_EVEN) correctly matches the real `MathContext.DECIMAL64` constant definition — the display name is not a mismatched or misleading label.

## Minor Observation (non-violating)
The intermediate εc-product variables (o_5, o_8, o_11) do not carry the `point` meta tag that their sibling concentration inputs and absorbance outputs carry (they rely solely on the free-text `descriptor.name` for point disambiguation). This is a metadata-completeness inconsistency worth flagging for hygiene purposes, but it does not constitute a re-mapping of business context: each intermediate is consumed exactly once, by the operation whose name and argument wiring unambiguously correspond to the same calibration point, and no downstream operation treats it under a conflicting domain definition.

## Conclusion
No node was found where a variable's declared business context (ε, path length, concentration-at-a-point, absorbance, or ratio) diverges from the context assumed by its consuming operation. Argument orderings, reused variables (ε, l, A1), and the mathematical substance of every step are consistent with their declared domain roles and with standard Beer-Lambert semantics. No identity/wrapper re-casting, unit stripping, or gross→net-style silent redefinition was identified. This does not preclude a very subtle issue outside the checked dimensions, hence confidence is not maximal, but no EXPECTED_INVARIANTS violation for the semantic-cast vector was substantiated.