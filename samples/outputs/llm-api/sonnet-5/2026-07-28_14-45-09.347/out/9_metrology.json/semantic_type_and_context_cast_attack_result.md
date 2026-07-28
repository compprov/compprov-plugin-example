# Summary
- **Verdict**: CLEAN
- **Confidence score**: 78.0

## Scope of This Audit
This audit specifically targets **Semantic Type and Context Cast** attacks: cases where a variable's technical type (`java.math.BigDecimal`, etc.) and mathematical replay both check out, but its declared business/domain meaning is silently altered, stripped, or reinterpreted by a downstream consumer (e.g., Gross→Net, Local→Base Currency, 'Standard Risk Multiplier'→'Corporate Discount Factor').

## Methodology
I traced every variable's `descriptor.name` (which in this metrology CPG functionally encodes both domain meaning and units, since there is no separate `meta.domainType`/`units`/`taxStatus` field populated anywhere in the graph) from its root INPUT origin through every consuming operation to its final OUTPUT destination, verifying:
1. Unit/dimension consistency at every arithmetic step (°C vs K, nm vs µm, Pa, um⁻¹, um⁻², dimensionless ×10⁻⁸ refractivity units).
2. Whether any variable is consumed downstream under an assumption (e.g., 'already corrected', 'already converted') that contradicts its origin.
3. Whether any identity/wrapper operation is used to silently re-label a variable's business meaning while preserving its numeric value.
4. Cross-checked all multiply-consumed variables (i_2, i_6, i_7, i_8, o_12, o_27, o_28, i_49) for consistent semantic usage across all consumers.

## Findings
- **T_air (i_7, °C)** is consistently consumed as Celsius in all four downstream formulas (B&D pressure/thermal correction, enhancement-factor T², and the T_K conversion via explicit `+273.15` op_1) — no silent Celsius/Kelvin conflation.
- **T_K (o_12, K)** is consistently used only in the Wexler/Hyland svp formula (T², B·T, D/T) — correct Kelvin-only usage, never mixed with Celsius paths.
- **lambda_vac3 (i_6, nm)** is correctly unit-converted (÷1000) before entering the Sellmeier/sigma computation, and separately correctly divided by refractive index `n` (dimensionless) to yield `lambda_air3` in nm — both preserve declared units without cast.
- **sigma² (o_28, µm⁻²)** is legitimately reused in three physically distinct formulas (Ciddor-type dry-air dispersion, and Edlen water-vapor dispersion) — this is standard metrological practice (all reference the same laser vacuum wavenumber), not a context cast.
- **N_s → N_s_corrected_for_CO2 (o_39→o_45)**: the CO2 correction is fully explicit and auditable (op_19–op_22), but numerically inert in this run because `xCO2` (i_10=450.0) exactly equals the reference concentration (i_40=450). This produces a labeled 'CO2-corrected' value that is numerically identical to the uncorrected one. This is a *suspicious coincidence* worth flagging for human review, but the transformation logic itself is transparent and present in the graph (satisfying the invariant that domain transitions be backed by explicit, auditable logic), so it does not meet the bar of a concealed semantic cast.
- **Final chain (svp≈2339 Pa at 20°C, n≈1.000272, deltaL≈2.31 nm on a 7 mm block)** all match physically expected real-world magnitudes, corroborating that units/domain meanings were not silently swapped anywhere in the pipeline.

### Secondary (out-of-scope) observation
The water-vapor enhancement-factor computation (op_38, `o_69 = o_67 - o_68`, i.e., `(alpha+beta*P) - gamma*T_air^2`) uses subtraction where the standard Davis/CIPM formula for this quantity uses addition of all three terms. This is an *arithmetic operator* anomaly, not a metadata/domain relabeling, so it falls outside the Semantic Type and Context Cast vector this audit targets — but it is flagged here for a separate operator-integrity review, since the numeric consequence is a small (~5e-5) downward bias in `f_enh`, propagating into `pv`, `N_v`, and ultimately `deltaL`.

## Conclusion
No variable was found where a downstream operation consumed it under a business/domain interpretation inconsistent with its declared origin, and no metadata suppression, identity-relabeling, or unjustified domain transition (e.g., raw treated as adjusted, gross treated as net) was identified. All unit conversions (°C↔K, nm↔µm, wavelength↔wavenumber, ×10⁻⁸ refractivity scaling) are backed by explicit, correctly-directioned arithmetic operations matching their descriptor names throughout the entire lineage from root inputs to `o_96`.
