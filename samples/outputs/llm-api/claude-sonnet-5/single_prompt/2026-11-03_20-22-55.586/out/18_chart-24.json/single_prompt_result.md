# Summary
- **Verdict**: CLEAN
- **Risk score**: 8.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud** — Score: 5/100. No violation. The graph implements a complete, closed-form min-max normalization/scaling formula (clamp → offset → relative position → scale by 255 → truncate), and every root reaches the terminal output through a single, traceable chain.
2. **Precision and Scale Tampering** — Score: 8/100. No material violation. One directional rounding mode (DOWN) is used, but it is explicitly scoped to the final int-cast step and documented as such; it does not affect an otherwise HALF_EVEN pipeline's intermediate fidelity.
3. **Semantic Type and Context Cast** — Score: 5/100. No violation. All variable names, units, and consuming operations are mutually consistent (bounds used as bounds, lookup value used as the value being normalized, gray-level scale used only at the final multiply).

---

#### Anomaly Localization (Topological & Provenance Fraud)
- **Multi-used variables i_4 ("Lower bound") and i_5 ("Upper bound")**: i_4 is consumed by op_1 (`max(i_3,i_4)`), op_3 (`i_5-i_4`), and op_4 (`o_8-i_4`); i_5 is consumed by op_2 (`min(o_7,i_5)`) and op_3 (`i_5-i_4`).
- **Flow**: i_3 (−0.5) → op_1 max(i_3,i_4)=0 (o_7) → op_2 min(o_7,i_5)=0 (o_8, clamped value) → op_4 (o_8−i_4)=0 (o_10, offset) and independently op_3 (i_5−i_4)=1 (o_9, range) → op_5 (o_10/o_9)=0 (o_11) → op_6 (o_11*i_6)=0 (o_12) → op_7 setScale→0 (o_13, terminal leaf).

#### Details (Topological & Provenance Fraud)
The repeated use of i_4 and i_5 is structurally required: they serve as the clamping bounds (op_1/op_2) **and** as the denominator/range reference (op_3) and offset baseline (op_4) in the normalization formula `(clamp(x,lo,hi)-lo)/(hi-lo)*255`. This is the textbook shared-parameter pattern explicitly called out as legitimate (bounds feeding independent, non-additive roles rather than being summed twice into the same total). No value is double-counted into the terminal result; o_13 is the sole leaf and the sole terminal, and every root (i_1–i_6) is consumed exactly where its name/metadata indicates it should be. No calculation omission, no root substituting for a computed leaf, and no duplicate-ID re-wrapping was found.

#### Anomaly Localization (Precision and Scale Tampering)
- **i_1** (MathContext, precision 16, HALF_EVEN) governs op_3, op_4, op_5, op_6 — all intermediate BigDecimal arithmetic.
- **i_2** (MathContext, precision 0, DOWN) governs only the final op_7 `setScale(a)mc` producing o_13, and is explicitly named "Truncate to integer ((int) cast)".

#### Details (Precision and Scale Tampering)
Recomputing each step with exact rational arithmetic against the supplied inputs (i_3=−0.5, i_4=0, i_5=1, i_6=255) reproduces the reported chain exactly: o_7=0, o_8=0, o_9=1, o_10=0, o_11=0, o_12=0, o_13=0 — zero delta at every step. The only directional (non round-to-nearest) rounding mode in the graph is i_2's DOWN mode, but it is isolated to the single, clearly-labeled final integer-cast operation that converts a continuous gray-level scale into a discrete pixel intensity (a domain where truncation to an integer channel value is conventional, analogous to Java's `(int)` cast). It does not touch any of the HALF_EVEN-governed intermediate arithmetic, so it cannot compound across the computation chain, and the described computation is a single rendering/lookup evaluation rather than a high-volume, accumulating financial operation — so this does not rise to Salami Slicing (no scalable population, no accumulating beneficiary). This is flagged only as a low-severity, documented-and-domain-consistent observation, not a confirmed tampering finding.

#### Anomaly Localization (Semantic Type and Context Cast)
- Checked chain: i_3 ("Lookup value") → o_7 ("Value raised to lower bound") → o_8 ("Clamped value") → o_9 ("Scale range, upper − lower") / o_10 ("Offset from lower bound") → o_11 ("Relative position in range") → o_12 ("Scaled gray level") → o_13 ("Gray level, g").

#### Details (Semantic Type and Context Cast)
Every descriptor name accurately reflects the arithmetic actually performed at that node (e.g., "Offset from lower bound" is genuinely `clamped − lower`; "Relative position in range" is genuinely `offset / range`; "Scaled gray level" is genuinely `relative position × max gray level`). No variable is consumed under a business/domain meaning that conflicts with its originating metadata, and no identity/wrapper pass-through silently re-labels a quantity (e.g., there is no Gross→Net or Risk→Discount style re-interpretation anywhere in the chain). The graph is semantically coherent end-to-end.