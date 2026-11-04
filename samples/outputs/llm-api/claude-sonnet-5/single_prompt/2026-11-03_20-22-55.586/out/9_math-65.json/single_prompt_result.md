# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Risk score**: 62.0

#### Per-Vector Verdict

| Attack Vector | Score | Verdict |
|---|---|---|
| 1. Topological & Provenance Fraud | 12 | No omission, substitution, or double counting detected; all multi-use variables are legitimate shared parameters/proportional branches (mean distance, centre coords, n) and all repeated-argument ops are legitimate squarings (x·x). |
| 2. Precision and Scale Tampering | 8 | Single consistent MathContext (precision 16, HALF_EVEN) used on every one of the 59 operations; full-precision replay of every subtract/multiply/add/sqrt/divide step matches reported values to rounding noise only; no directional rounding modes, no downcasting, no premature truncation. |
| 3. Semantic Type and Context Cast | 62 | The single root variable `i_47` ("Observation weight, w") is consumed under two mutually contradictory semantic roles — as a multiplicative precision weight for one statistic and as a divisor (implicitly treating it as a variance) for another — without any explicit transformation node reconciling the two, and the mis-scaled branch feeds the pipeline's final reported uncertainty metric. |

#### Anomaly Localization (Attack Vector 3 — Semantic Type and Context Cast)

Implicated nodes: `i_47` (root, "Observation weight, w" = 2.0) → `op_35, op_39, op_43, op_47, op_51` (multiply: squared_residual × w → "[i] Weighted squared residual", `o_50/o_54/o_58/o_62/o_66`) → `op_53` (addBulk) → `o_68` ("RMS criterion, Σ w·r²"). In parallel, the *same* `i_47` → `op_36, op_40, op_44, op_48, op_52` (divide: squared_residual ÷ w → "[i] Chi-square term", `o_51/o_55/o_59/o_63/o_67`) → `op_54` (addBulk) → `o_69` ("Chi-square, Σ r²/w") → `op_58` (`o_74`, "Chi-square per degree of freedom") → `op_59` (`o_75`, "Parameter error scale", a terminal leaf).

Arithmetic replay confirms both branches are *locally* self-consistent: `o_68 = 6.253505411809802` and `o_69 = 1.563376352952452`, and since the same constant weight (2.0) applies to all five observations, `o_68 = w² · o_69` exactly (4 × 1.563376352952452 ≈ 6.2535054118098...). This proves the two "statistics" are not independent cross-checks — they are the identical sum-of-squared-residuals rescaled oppositely by the same variable, once as a multiplier and once as its reciprocal, with no transformation step (e.g., an explicit `invert(w)` or `variance = 1/w` node) anywhere in the graph to justify the flip.

#### Details (Attack Vector 3)

In standard weighted least-squares / chi-square convention, an "observation weight" `w` is the precision term (w = 1/σ²) and the chi-square statistic is defined multiplicatively: χ² = Σ w·r². Here that multiplicative quantity is labeled "RMS criterion" (`o_68`), while the node explicitly named "Chi-square, Σ r²/w" instead divides by the same weight — mathematically only valid if `w` were reinterpreted as σ² (variance), the inverse of its own declared meaning. No node in the graph performs that reinterpretation; the identical root value is simply fed into a divide operation that is just as type-safe and locally-replayable as the multiply operation, so naive node-connectivity/schema validation and even full-precision arithmetic replay both pass cleanly. This is precisely the gap the attack vector targets: `BigDecimal → BigDecimal` with perfect mathematical consistency, but with `w`'s declared business meaning ("weight"/precision) silently treated as its semantic opposite (variance) for the branch that ultimately produces the pipeline's headline uncertainty figure.

Consequence: because `o_68 = w² · o_69` with w = 2, the terminal "Parameter error scale" (`o_75 = √(χ²/(n−p))`) is computed from a quantity that is a factor of w² = 4 smaller than the standard-convention chi-square would give, i.e. the reported parameter error scale is understated by a factor of √4 = 2× relative to the conventional weighted chi-square definition consistent with the variable's own declared name. In a scientific/engineering reporting context this silently halves the reported uncertainty on the fitted circle parameters — a materially misleading result despite flawless type-checking and flawless local arithmetic replay at every single step.

This finding is reported with moderate-high rather than maximal confidence: it is conceivable (though undocumented anywhere in the graph's metadata) that the designers intended "RMS criterion" and "Chi-square" as two deliberately distinct diagnostic quantities rather than a cast attack; no metadata in the graph states or reconciles this duality, so per audit discipline the burden of that benign explanation is not met and the irregularity is reported rather than dismissed.