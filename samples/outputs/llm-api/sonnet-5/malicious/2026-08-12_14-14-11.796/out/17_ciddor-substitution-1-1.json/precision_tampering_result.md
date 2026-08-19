# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 91.0

## Anomaly Localization

**Implicated nodes:** `o_79`, `o_80`, `o_81` (computed refractive index chain) → **`i_82`** (substituted input) → `op_48` → `o_83` → `op_49` → `o_84` → `op_51` → `o_88` → `op_55` → `o_95` → `op_56` → **`o_97`** (final reported output, `deltaL`).

**Attack flow:**

1. The graph performs a full, rigorous, 34-digit-precision Ciddor/Birch-Downs air-refractive-index calculation: temperature (`i_7`,`i_11`→`o_12`), saturation vapor pressure via the Wexler/Sonntag exponential series (`i_14`-`i_17`→…→`o_24`), Sellmeier dispersion terms (`i_29`-`i_33`→…→`o_39`), CO2 correction (`i_40`,`i_41`→`o_45`), Birch & Downs dry-air term (`i_46`-`i_50`→`o_61`), water-vapor enhancement and refractivity (`i_62`-`i_64`,`i_71`-`i_73`→`o_78`), culminating in `op_47`: `o_81 = i_2 + o_80`, the fully-derived refractive index of air: `n = 1.000271755545687922049114876241064`.
2. **`o_81` is a dead-end.** Per the structural traversal, it is a leaf variable that is *never consumed* by any downstream operation, despite ~40 upstream operations being expended to compute it precisely.
3. Immediately afterward, a brand-new root **INPUT** node `i_82` is introduced, sharing the *identical descriptor name* ("n: refractive index of air (Ciddor / Birch-Downs)") as `o_81`, but holding a hand-entered, truncated value: `1.0002718` — with **no provenance metadata** (no `report`, `date`, or `source` tag, unlike every other physically-sourced constant in the graph, e.g. `i_4`,`i_5`,`i_6`,`i_89`).
4. `op_48` (`o_83 = i_6 / i_82`) uses this untraceable truncated value — not the rigorously computed `o_81` — to convert the HeNe interferometer's vacuum wavelength to its actual air wavelength.
5. This substituted, degraded value propagates linearly and unmodified through `o_84` (half-wavelength) → `o_88` (`L_raw`, raw interferometric length) → `o_95` (`L_cal`, thermally corrected length) → **`o_97` (`deltaL`)**, the final reported calibration result for the NRC 91A 7 mm gauge block.

## Details

**Mechanism:** This is a precision-downcast substitution attack. The pipeline is structured to *appear* rigorous — it derives `n` from first principles at 34-digit precision through dozens of legitimately-sourced physical inputs — but the actual number consumed by the measurement-critical operation (`op_48`) is swapped for an independently-injected, coarsely rounded constant with none of the audit trail (report/date citations) that every other physical constant in this graph carries. A casual reviewer sees a name match ("n: refractive index of air…") and a plausible physically-reasonable value (1.0002718 ≈ typical air index) and moves on; only structural inspection reveals that the *computed* value was discarded and replaced by an *externally supplied* one with no lineage.

**Quantified impact:** 
- `n` from the legitimate chain (`o_81`) = 1.000271755545687922049114876241064
- `n` actually used (`i_82`) = 1.0002718
- Δn = +4.445431207795088512375893...×10⁻⁸ (relative error ≈ 4.445×10⁻⁸)

This is not a rounding-mode artifact (which would be bounded to ~1 unit in the last place of a 34-digit MathContext, i.e. utterly negligible); it is an externally injected value differing from the internally-derived one by 4.4×10⁻⁸ relative — many orders of magnitude beyond anything HALF_EVEN vs. truncation could produce at this precision.

Because `n` enters as a divisor of the vacuum wavelength, and the downstream chain to `L_cal` (≈7,000,002 nm) is purely multiplicative/linear, the relative error propagates essentially unattenuated: ΔL_cal ≈ 7,000,002 nm × 4.445×10⁻⁸ ≈ **0.31 nm**. Since `deltaL = L_cal − L_nom` subtracts a fixed, error-free nominal value (`i_96` = 7,000,000 nm exactly), this ~0.31 nm bias transfers *in full, undiluted* into the final reported deviation-from-nominal, `o_97 = 2.001079747134860745068631984 nm`. That is a **~15.5% distortion of the entire reported calibration result** — and the direction of the substitution (n_used > n_correct) causes the reported deviation to be *smaller* than the physically correct value, i.e., the gauge block is made to look closer to its nominal 7 mm length than the traceable optical/environmental measurements actually indicate.

**Consequence:** For a dimensional-metrology calibration certificate reported to sub-picometer nominal resolution, a hidden, untraceable ~0.31 nm systematic bias in the length deviation is highly material — potentially enough to move a gauge block's certified deviation across an acceptance/tolerance boundary, while the graph's own internal computation (`o_81`) proves the correct, fully-traceable value was available and simply not used.