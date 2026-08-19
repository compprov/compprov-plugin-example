# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Audit Scope
This audit traced every variable's declared business/domain context (`descriptor.name`, `descriptor.meta`, implied units) against its consumption by every downstream operation, specifically hunting for the Semantic Type and Context Cast pattern: technically valid `BigDecimal -> BigDecimal` flows that silently swap business meaning (e.g., gross vs net, local vs base unit, pre- vs post-adjustment) while preserving numeric/type validity.

## Methodology
The graph implements a complete, internally coherent physical/metrological pipeline for laser-interferometric gauge-block calibration:

1. **Wexler/Sonntag saturation vapor pressure** (i_7→o_12 T_K, o_13..o_24 svp) — Celsius correctly converted to Kelvin before Kelvin-domain polynomial; Celsius-domain B&D/CIPM terms (o_51, o_56, o_65) correctly continue to consume `i_7` directly in Celsius, never mixing with `o_12` (Kelvin). No cross-unit substitution found.
2. **Sellmeier/Ciddor standard-air refractivity** (i_29-i_33, o_26-o_39) — wavelength nm→µm conversion (o_26) is correctly isolated to the sigma/Sellmeier branch, while the *original* nm-valued `i_6` is correctly reused later (op_48) for the nm-domain wavelength division — both consumers use the unit appropriate to their formula, not a mismatched one.
3. **CO2 correction** (i_10, i_40, i_41, o_42-o_45) — reference (450 ppm) and measured (450.0 ppm) concentrations are subtracted in the correct order (measured − reference), producing a legitimate (here, zero) correction; no swap of reference/measured roles.
4. **Birch & Downs dry-air density correction** (i_46-i_50, o_51-o_61) — coefficients `a`, `b`, thermal coefficient, and denominator constant are consumed in the argument slots matching their declared roles (`a - b*T`, `1+1e-8*P*(a-b*T)`, `96095.43*(1+0.003661*T)`); replay of the full Birch–Downs 1994 formula is exact and role-consistent.
5. **CIPM water-vapor enhancement factor** (i_62-i_64, o_65-o_69) and **Edlen water-vapor refractivity correction** (i_71-i_73, o_74-o_78) — `alpha`, `beta`, `gamma` and `W1`, `W2` are each consumed exactly once, in the slot matching their declared coefficient identity; the two distinctly-named 