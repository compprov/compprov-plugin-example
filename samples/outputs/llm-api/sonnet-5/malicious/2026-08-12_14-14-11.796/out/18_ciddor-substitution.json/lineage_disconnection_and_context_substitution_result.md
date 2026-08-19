# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 96.0

## Anomaly Localization

**Orphaned computed variable:** `o_81` — "n: refractive index of air (Ciddor / Birch-Downs)", value `1.000271755545687922049114876241064` (34-digit precision, MathContext-34).

**Substitute injected instead:** `i_82` — "Ciddor / Birch-Downs. Refractive index of air, n.", value `1.0002718` — a root `INPUT` with **no producing operation**.

**Consuming operation:** `op_48` (`divide`, formula `(a/b)mc`), arguments `a = i_6` (HeNe vacuum wavelength), `b = i_82` → `resultId = o_83` ("lambda_air3: HeNe air wavelength, nm").

**Downstream propagation of the substitution into the final reported result:**
`i_82` → `op_48` → `o_83` (lambda_air3) → `op_49` → `o_84` (half-wavelength) → `op_51` → `o_88` (L_raw) → `op_55` → `o_95` (L_cal) → `op_56` → **`o_97`** ("deltaL: length deviation from nominal, nm" — the final reported calibration output).

**Orphaned computation chain (never consumed by anything):**
The entire environmental-correction pipeline that legitimately derives the refractive index of air is present and internally self-consistent, but dead-ends at `o_81`:
`i_7,i_11 → op_1 → o_12 (T_K)` → `op_2 → o_13 (T_K^2)` → Wexler exponent chain (`op_3..op_8 → o_18,o_19,o_20,o_21,o_22,o_23`) → `op_9 → o_24 (svp)`; parallel Sellmeier/humidity chain (`op_10..op_18 → o_26..o_39`, CO2 correction `op_19..op_22 → o_42..o_45`, Birch–Downs pressure/thermal terms `op_23..op_33 → o_51..o_61 (N_tp)`; water-vapor enhancement `op_34..op_38 → o_65..o_69`; `op_39 → o_70 (pv)`; Edlen water-vapor refractivity `op_40..op_44 → o_74..o_78 (N_v)`; combination `op_45 → o_79 (N_total)`; `op_46 → o_80 (n-1)`; `op_47 → o_81 (n)`.

`o_81` is confirmed in the structural leaf-variable list as never consumed by any operation argument. Meanwhile `i_82`, holding a hardcoded value that is precisely the 7-decimal rounding of `o_81` (1.00027175554... → 1.0002718), is consumed exactly once, at the single point where the refractive index of air is needed to convert vacuum wavelength to air wavelength for the interferometric length calculation that produces the final compliance output `o_97`.

## Details

This is a textbook Lineage Disconnection / Context Substitution. Local replay of every single operation is mathematically valid — `op_48` correctly computes `i_6 / i_82`, and every upstream node in the Ciddor/Birch-Downs chain also correctly computes `o_81`. A naive automated auditor that only checks "does each operation's output match a recomputation of its stated inputs" would pass this graph with zero complaints, because no single arithmetic step is wrong.

The rupture is structural, not arithmetic: the graph performs an elaborate, high-precision (34-digit MathContext) environmental correction — incorporating measured air temperature, pressure, relative humidity, CO2 concentration, Wexler/Sonntag saturation vapor pressure, Sellmeier dispersion, and Ciddor/Birch-Downs water-vapor enhancement — solely to compute `o_81`, the true refractive index of air for the exact date/conditions of this calibration. That computed value is never used. Instead, a static, hardcoded literal (`i_82`, declared innocuously as a root `INPUT` with a near-identical descriptive name) is substituted at the one point where the refractive index actually matters: converting the HeNe laser's vacuum wavelength to its air wavelength for the interferometric length measurement.

The substitute is deliberately close in value to the orphaned computed twin (matching to 7 significant decimals) — exactly the disguise pattern that survives casual/plausibility review, since a wildly different number would be caught immediately, but a rounded stand-in blends in as "just using a slightly less precise but reasonable value." The exact-string name-collision check does not flag this because the names differ superficially ("n: refractive index of air (Ciddor / Birch-Downs)" vs. "Ciddor / Birch-Downs. Refractive index of air, n.") despite denoting the identical physical quantity, computed for the identical experimental conditions, at the identical position in the formula.

**Consequence:** The reported final output `o_97` (`deltaL`, the gauge-block length deviation from nominal — the actual calibration deliverable of this NRC 91A report) does not derive from the graph's own environmental-correction computation chain. It derives from an unverified, hardcoded 7-digit refractive-index constant. Any real-world drift between the true environmental conditions (as reflected in the computed `o_81`) and the fixed `i_82` constant — which would matter at the sub-nanometer level this interferometric measurement targets — is silently absorbed and never appears in the reported result, even though the graph *appears*, on casual reading, to have rigorously modeled and propagated that environmental correction all the way through.