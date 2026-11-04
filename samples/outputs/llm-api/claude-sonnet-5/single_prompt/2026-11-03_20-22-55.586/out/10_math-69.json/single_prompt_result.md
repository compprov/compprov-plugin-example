# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Risk score**: 32.0

#### Per-Vector Verdict

| Attack Vector | Score | Verdict |
|---|---|---|
| Topological & Provenance Fraud | 5 | No calculation omission, lineage disconnection, or double counting found — sole leaf `o_194` is correctly fed by the full computation chain; all repeated-argument ops are legitimate squarings; all multi-use variables are legitimate shared parameters/branches. |
| Precision and Scale Tampering | 32 | A genuine catastrophic-cancellation precision flaw exists at the tail end of the p-value computation (`op_132`→`op_133`), zeroing out a materially non-zero (but numerically negligible) upper-tail probability. Not malicious/beneficiary-driven, but a real arithmetic design defect. |
| Semantic Type and Context Cast | 5 | Business meaning (`descriptor.name`) is consistent end-to-end; every formula step matches its declared name (Sxy, Sxx, Syy, r, df, t, I_x, CDF, p-value); no relabeling or metadata stripping detected. |

---

#### Anomaly Localization (Precision and Scale Tampering)

- `o_187` (`I_x(ν/2,1/2)`) = 4.895802275184153E-18
- `o_190` (`0.5·I_x`) = 2.447901137592076E-18 — this is the mathematically correct value of the two-sided tail mass before doubling.
- `o_191` (`1 − 0.5·I_x`, the CDF) = `op_132`: `i_189 (1) − o_190` → computed under MathContext `i_1` (precision 16, HALF_EVEN) as **1.000000000000000** (the ~2.4e-18 subtrahend is far below the 16-significant-digit resolution of a value near 1, so it vanishes entirely).
- `o_192` (`Upper-tail probability`) = `op_133`: `i_189 (1) − o_191` = **0E-15** (since `o_191` was already rounded to exactly 1, this second subtraction has nothing left to recover).
- `o_194` (`Correlation p-value`) = `op_134`: `i_193 (2) × o_192` = **0E-15**.

Flow: the only precision-preserving representation of the true tail mass (`o_190`, correct to 16 significant digits of a ~1e-18 quantity) is discarded by routing it through two successive `(1 − x)` subtractions against the constant `i_189` (value `1`) instead of being used, or its complement algebraically simplified, directly. The round-trip `1 − (1 − 0.5·I_x)` is mathematically equal to `0.5·I_x`, but under finite-precision decimal arithmetic at 16 significant digits it is not numerically equal — it collapses to zero.

#### Details (Precision and Scale Tampering)

**Mechanism:** Each individual operation (`op_132`, `op_133`) replays perfectly against its own declared `MathContext` (precision 16, HALF_EVEN) and in isolation looks unremarkable — a plain `subtract`. This is exactly why local/casual replay checks pass: no single step violates its own declared context, and HALF_EVEN is a legitimate rounding mode consistent with the rest of the graph. The flaw only becomes visible when tracing the *value*, not just the *operation*: the already-correctly-computed quantity `o_190` (0.5·I_x, accurate to ~16 significant digits of a 1e-18-scale number) is never propagated forward; instead the pipeline recomputes an equivalent quantity via `1 − (1 − x)`, which is catastrophic cancellation by construction whenever `x` is smaller than the ULP of 1 at the chosen precision. A precision/scale check that only validates "does this operation's result match its own MathContext" will never catch this, because it does.

**Consequence:** The reported final `Correlation p-value` (`o_194` = `0E-15`) understates the true value (~9.79E-18) by effectively discarding all significant digits of an already-known, correctly computed quantity. In this specific dataset the practical statistical conclusion (reject null hypothesis of zero correlation; p is astronomically small) is unaffected regardless of whether the reported value is `0` or `9.79E-18` — hence this is not scored as a critical/materiality-override finding. However, it is a structural defect: in any pipeline reusing this subtraction pattern for less extreme (but still small) `t`/`I_x` values, or in contexts where the exact magnitude of a tiny p-value matters (e.g., multiple-testing correction, ranking many near-zero p-values), this pattern would silently flatten distinct results to indistinguishable zeros. The fix (using `o_190` directly, or an algebraic identity that avoids subtracting from 1) is straightforward and its absence is a genuine, if non-malicious, precision-tampering-class flaw rather than ordinary rounding noise — it exceeds the "single rounding-mode, ≤1-ULP" ceiling because the entire signal (18 orders of magnitude of it) is lost, not just the last digit.

---

#### Anomaly Localization (Topological & Provenance Fraud) — none found
All 60 root variables (data points, series constants, context) are consumed in their structurally expected roles; the lone leaf `o_194` is reached through an unbroken, non-duplicated computation chain (sums → means → deviations → cross/square products → Sxy/Sxx/Syy → r → t → incomplete-beta series → CDF → p-value). Every flagged "repeated argument" operation (e.g., `op_8`, `op_82`, `op_88`) is a legitimate squaring (`x²`, `r²`, `t²`), and every flagged "multi-use variable" (e.g., `o_32`, `o_110`, `o_113`, `o_122`) is a shared parameter feeding independent, non-overlapping downstream branches (e.g., `r` feeding both `r²` and the signed-`t` product; `ν` feeding both the ratio and the `ν+t²` sum) — their contributions do not converge twice into the same total.

#### Anomaly Localization (Semantic Type and Context Cast) — none found
Names and metadata track their mathematical role consistently end-to-end: deviations are always consumed as deviations, sums-of-squares as sums-of-squares, `r` is never consumed as if it were `r²` or vice versa, and the degrees-of-freedom / t-statistic / incomplete-beta chain is internally consistent with the standard Pearson correlation significance-test derivation. No variable is silently re-interpreted under a conflicting business/domain meaning.