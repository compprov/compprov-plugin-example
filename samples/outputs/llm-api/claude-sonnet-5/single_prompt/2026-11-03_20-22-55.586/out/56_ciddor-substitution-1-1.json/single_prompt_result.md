# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 95.0

#### Per-Vector Verdict

| Vector | Score | Verdict |
|---|---|---|
| Topological & Provenance Fraud (Lineage Disconnection & Context Substitution, with a secondary Calculation Omission signal) | 95 | **CONFIRMED** — the entire Ciddor/Birch-Downs refractive-index computation chain (ops 1–47) terminates in a leaf (`o_81`) that is never consumed; the final length calculation instead consumes a hard-coded, duplicate-named root (`i_82`) of far coarser precision. |
| Precision and Scale Tampering | 55 | Confirmed secondary flaw: `op_9` (`Exp_double`) silently drops from the declared 34-digit `HALF_EVEN` context to double precision, and the substitute `i_82` itself violates the graph's own precision standard (7 sig. figs. vs. 34). Materiality is high because the precision mismatch feeds directly into the reported result via `op_48`. |
| Semantic Type and Context Cast | 15 | No confirmed business-meaning relabeling — `i_82` is declared under the identical name/quantity as `o_81`, so this is a sourcing/lineage fraud rather than a mislabeling; only a minor provenance-metadata inconsistency is noted. |

---

#### Anomaly Localization (Vector 1 — Lineage Disconnection & Context Substitution) [PRIMARY FINDING]

- **Computed (bypassed) value:** `o_81` — "n: refractive index of air (Ciddor / Birch-Downs)" = `1.000271755545687922049114876241064`, produced by `op_47` (`add(i_2, o_80)`), itself the terminus of a 47-operation chain (`op_1`…`op_47`) that legitimately incorporates `T_air` (`i_7`), `P_air` (`i_8`), humidity (`i_9`), `xCO2` (`i_10`), Wexler/Sonntag SVP coefficients (`i_14`–`i_17`), Sellmeier dispersion terms (`i_29`–`i_33`), CO2 correction (`i_40`,`i_41`), Birch & Downs constants (`i_46`–`i_50`), and Edlen water-vapor terms (`i_62`–`i_64`, `i_71`–`i_73`).
- **Substitute (consumed) value:** `i_82` — a **root** variable, name-identical to `o_81` ("n: refractive index of air (Ciddor / Birch-Downs)"), value `1.0002718`, with **empty metadata** (no source, no date, no derivation), unlike every other critical root in the graph (`i_4`, `i_5`, `i_89`, `i_96` all carry report/date/source tags).
- **Point of substitution:** `op_48` — `divide(i_6, i_82) -> o_83` ("lambda_air3: HeNe air wavelength"). This is the single node where the refractive index is actually applied to convert the vacuum HeNe wavelength (`i_6`) to the air wavelength used for the physical length measurement.
- **Downstream propagation to terminal result:** `o_83` → `op_49` (`o_84`, half-wavelength) → `op_51` (`o_88`, L_raw, using fringe order `o_87 = i_85+i_86`) → `op_55` (`o_95`, thermally corrected length) → `op_56` (`o_97 = o_95 - i_96`, **deltaL, the terminal reported output**).
- `o_81` is confirmed as a structural **leaf** (per reference data) — it has zero downstream consumers. The entire elaborate physics computation that produces it is provenance theater: it replays perfectly and looks rigorous to a schema/connectivity check, but is causally disconnected from the reported calibration result.

#### Details (Vector 1)

The attack works because local replay of every step passes: `op_48` is internally consistent (it is a valid `divide(a,b,mc)` with a real `MathContext`), and `i_82` is a syntactically valid `BigDecimal` root with the exact semantic label required ("refractive index of air"). A naive type/connectivity audit sees a fully computed Ciddor/Birch-Downs refractive index feeding the pipeline — it does not notice that the *actual* consumed value is a different, hand-entered root with the same name.

Quantitative impact: `i_82 = 1.0002718` vs. `o_81 = 1.0002717555456879...`, a relative offset of ≈4.44×10⁻⁸ in `n`. Propagated through `lambda_air = lambda_vac/n`, then multiplied by the fringe order (`m+f ≈ 22123.237`) in `op_51`, this produces a shift of **≈0.31 nm** in `L_raw`/`L_cal`, against a final reported `deltaL = 2.001079747...` nm. Had the graph's own computed `o_81` been used instead of the substituted `i_82`, `deltaL` would be **≈2.31 nm** — a ~15–16% change in the reported deviation-from-nominal for a 7 mm gauge block. In dimensional metrology, where calibration-grade tolerances are measured in tens of nanometers, this magnitude of silent distortion can move a block from "in-tolerance" to "out-of-tolerance" or vice versa. This is a textbook Lineage Disconnection / Context Substitution: a computed quantity is built in full view, then quietly bypassed in favor of a coarser, unaudited constant at the one node that matters.

---

#### Anomaly Localization (Vector 1 — Calculation Omission, secondary finding)

- `i_4` ("lambda_vac1: TESA SG-L vacuum wavelength", report OFS-2024-0006) and `i_5` ("lambda_vac2: TESA SG-O vacuum wavelength", report OFS-2024-0005) are structural **leaves**: fully provenanced calibration-certificate inputs that are never consumed by any operation.
- Only `i_6` (Spectra 117A-1 HeNe) is used, and the fringe order components `i_85` (m=22123) and `i_86` (f=0.23675) are injected as unexplained root constants rather than derived in-graph.

#### Details (Vector 2 cross-reference)

Gauge-block interferometry conventionally uses multiple reference wavelengths ("method of exact fractions") specifically to resolve/cross-validate the integer fringe order and to detect refractive-index or wavelength errors. Here, two fully-documented wavelength certificates are carried into the graph (with report numbers and dates, signaling they were deliberately measured for this calibration) yet play no role — the fringe order is simply asserted. This is consistent with Calculation Omission: the cross-check that `i_4`/`i_5` should provide (either to verify `m`/`f`, or to independently verify the assumed `n`) never occurs, leaving the result dependent solely on the single, substituted-index path identified above. Confidence is moderate (it is possible these are retained purely as archival records), but the explicit "report"/"date" metadata, mirroring the one genuinely-used wavelength's metadata, argues against a purely decorative role.

---

#### Anomaly Localization (Vector 2 — Precision and Scale Tampering)

- `op_9` (`Exp_double`, name itself signals floating-point double-precision arithmetic) computes `o_24 = Exp(o_23)` **without a declared `mc` argument**, while `o_23` carries full 34-digit precision. The result `o_24 = 2339.1632301967884` has only ~17 significant digits — a precision collapse from the graph's declared 34-digit `HALF_EVEN` standard to double precision.
- Separately, `i_82` (consumed in `op_48`) carries only 8 significant figures, far coarser than the `MathContext i_1` (precision 34) used in the surrounding `divide` operation and in every other numeric input in the graph.

#### Details (Vector 2)

The `Exp_double` downcast is a genuine violation of the "no downcasting a precise decimal type to float/double mid-pipeline" invariant; however, because `o_24` only feeds the orphaned `o_81` branch (see Vector 1 finding), it currently has **no material effect on the reported terminal result** — its risk is forward-looking/structural rather than presently realized. The `i_82` precision shortfall, by contrast, *does* reach the terminal result (via `op_48`) and independently violates the invariant that a context 