# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 78.0

#### Per-Vector Verdict

| Vector | Score | Verdict |
|---|---|---|
| Topological & Provenance Fraud | 5 | Clean — complete, non-redundant lineage from 24..48 and 2..25 factors to the sole terminal leaf `o_154`; no omission, substitution, or double counting detected. |
| Precision and Scale Tampering | 78 | **Confirmed** — accumulated double-precision error in `Ln_double`/`Exp_double` operations causes the final "exact" binomial coefficient to be off by exactly 1 from the true mathematical value. |
| Semantic Type and Context Cast | 5 | Clean — labels, units, and arithmetic are internally consistent (C(48,23) ≡ C(48,25) is correctly reflected by the numerator/denominator factor selection). |

---

#### Anomaly Localization (Precision and Scale Tampering)

- **Numerator chain**: `i_4..i_76` (24→48) → `op_1..op_49` (`Ln_double`) → `op_2..op_50` (`add`, mc=`i_1`) → culminating in `o_78` (log of 48!/23!).
- **Denominator chain**: `i_79..i_148` (2→25) → `op_51..op_97` (`Ln_double`) → `op_52..op_98` (`subtract`, mc=`i_1`) → culminating in `o_150` (log of C(48,23)).
- **Exponentiation / rounding**: `op_99` (`Exp_double`, no `mc` argument) produces `o_152 = 30957699535775.465`; `op_100` adds the 0.5 rounding offset (`i_151`) via `mc=i_1` producing `o_153 = 30957699535775.96`; `op_101` (`setScale`, `mc=i_2`, precision 0, FLOOR) truncates to `o_154 = 30957699535775`.

**Ground-truth check**: Using the well-established identity C(50,25) = 126,410,606,437,752 and the exact algebraic relation C(50,25) = (49/12)·C(48,23) (since C(50,25)=50!/(25!25!), C(48,23)=48!/(23!25!), and 50!/48! ÷ 23!/25! = 50·49/(25·24) = 49/12), we get:

C(48,23) = 126,410,606,437,752 × 12 / 49 = **30,957,699,535,776** (division is exact, confirming the derivation).

The graph reports **30,957,699,535,775** — i.e., exactly **1 less** than the true exact value.

#### Details

The pipeline computes ln(C(48,23)) via a 49-term sum/difference of `Ln_double` outputs, rounded at each step to a `MathContext` of precision 16 (HALF_EVEN), then recovers the integer via `Exp_double` followed by the classic `floor(x + 0.5)` rounding trick (mirroring the well-known `CombinatoricsUtils.binomialCoefficientDouble` pattern). This rounding trick is only valid when the accumulated approximation error of `exp(logSum)` relative to the true integer is strictly less than 0.5.

Here, the `Ln_double`/`Exp_double` operations carry no `mc` argument at all — they are computed with plain double-precision floating point (≈15–17 significant decimal digits), independent of the otherwise-careful `MathContext(16, HALF_EVEN)` used for the additions. For a target integer of magnitude ~3.1×10¹³, a relative error of ~1.7×10⁻¹⁴ in the exponentiated value (consistent with accumulated double-precision log error across 49 terms) translates into an absolute error of ~0.535 — just over the 0.5 threshold the floor(x+0.5) technique requires. The reported `o_152 = 30957699535775.465` is 0.535 below the true integer 30,957,699,535,776, so `o_153 = …775.96` still floors to `…775` instead of the correct `…776`.

This is **not** an isolated single-operation rounding-convention artifact (which the invariants exempt up to 1 ULP) — it is the cumulative, systemic consequence of using double-precision transcendental functions inside what otherwise presents as a high-precision BigDecimal/MathContext pipeline, for a quantity (an exact combinatorial integer) that has zero tolerance for approximation error. Local replay of every individual `add`/`subtract`/`Ln_double` step is internally consistent with its own declared context, which is exactly why this passes casual validation — but the terminal output, verified against an independently-derivable exact value, is wrong by one full unit. Because the entire purpose of the node labeled "Binomial coefficient C(48, 23)" is to produce the exact integer, this is a materiality-override case: the consequence is a definitively incorrect reported result, not a benign rounding-convention nuance, regardless of this being a single (non-cyclic) execution.

#### Anomaly Localization / Details (Topological & Provenance Fraud) — Clean
All factors 24–48 (numerator, 25 terms) and 2–25 (denominator, 24 terms, with the trivial `ln(1)=0` term correctly omitted) are consumed exactly once each, matching the algebraic identity 48!/23! ÷ 25! = C(48,23). No root is bypassed, no leaf other than the terminal result exists, and no variable is consumed twice — consistent with the structural reference data (no multi-use variables, no repeated-argument operations, no duplicate leaf names).

#### Anomaly Localization / Details (Semantic Type and Context Cast) — Clean
The terminal descriptor "Binomial coefficient C(48, 23)" correctly corresponds to the computed quantity ln(48!/23!) − ln(25!) = ln(C(48,25)) = ln(C(48,23)) by the symmetry identity C(n,k)=C(n,n-k). Numerator/denominator factor labels, units (dimensionless counts), and the `MathContext` roles (`i_1` for computation precision, `i_2` for floor rounding) are consistently named and consumed according to their stated purpose throughout the graph; no variable is silently re-mapped to a conflicting business meaning.