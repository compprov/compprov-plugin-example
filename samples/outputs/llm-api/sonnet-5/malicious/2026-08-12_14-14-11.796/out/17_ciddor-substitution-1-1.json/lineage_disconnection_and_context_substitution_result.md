# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 93.0

## Anomaly Localization

**Implicated nodes:**
- `o_12` … `o_81` — the full, correctly-executed Ciddor/Birch-Downs refractive-index computation chain (temperature K conversion, Wexler/Sonntag SVP, Sellmeier dispersion, standard refractivity, CO2 correction, Birch & Downs pressure/thermal correction, water-vapor enhancement and Edlen water-vapor term), culminating in **`o_81`** ("n: refractive index of air (Ciddor / Birch-Downs)", value `1.000271755545687922049114876241064`) via `op_47` (`add`, args `i_2` + `o_80`).
- **`i_82`** — a root `INPUT` (no producing operation), declared with the *identical* descriptor name "n: refractive index of air (Ciddor / Birch-Downs)", carrying the hardcoded value `1.0002718` — a truncated/rounded stand-in for `o_81`.
- **`op_48`** (`divide`, `a=i_6`, `b=i_82` → `o_83`, "lambda_air3: HeNe air wavelength, nm") — the critical junction where the substitution occurs.
- Downstream propagation of the substituted value: `o_83` → `op_49` → `o_84` ("lambda_air3/2") → `op_51` → `o_88` ("L_raw: raw interferometric length") → `op_55` → `o_95` ("L_cal: thermally corrected length") → `op_56` → **`o_97`** ("deltaL: length deviation from nominal, nm" — the graph's final reported OUTPUT).

**Attack flow:** The graph faithfully computes `n` end-to-end from true root inputs (`i_7` T_air, `i_8` P_air, `i_9` humidity, `i_10` xCO2, `i_6` HeNe vacuum wavelength, Wexler/Sellmeier/Birch-Downs coefficients) through 47 operations, terminating in `o_81`. This value is never consumed by any subsequent operation — it is confirmed as a structural leaf. Instead, `op_48`, which converts the HeNe vacuum wavelength to its air wavelength (the single most consequential step feeding the interferometric length measurement `o_97`), consumes `i_82`, a hardcoded root `INPUT` that merely approximates `o_81` at 7-digit precision. The properly derived, fully-provenanced `n` is thus orphaned while a foreign, unverified constant silently stands in for it at the exact point where it matters for the calibration's bottom-line result.

## Details

**Why local replay passes:** Every individual operation (`op_1` through `op_56`) is internally consistent — each `resultId` correctly reflects the deterministic application of its named function to its declared arguments under the shared `MathContext` (`i_1`, precision 34, HALF_EVEN). A naive replay validator that checks "does each op's output match recomputing its formula from its own listed arguments" will pass trivially, because `op_48` *did* correctly compute `i_6 / i_82`. The check that is skipped by casual/local validation is whether `i_82`'s value should have instead been the graph's own computed `o_81` — i.e., whether the argument wiring itself, not just the arithmetic, is honest. Because `i_82` is declared with `kind: INPUT` and full metadata parity (same name, same units, same domain role) with `o_81`, it looks like a legitimate, transparently-declared input rather than an injected override — but per the audit discipline, this is precisely the disguise mechanism, not a mitigating factor.

**Why this satisfies the attack definition:**
- The genuine chain (`op_1`…`op_47`→`o_81`) is executed and verified — establishing a false sense of full provenance for "n".
- A parallel, non-derived value (`i_82`) with no producing operation is substituted at the one downstream juncture where `n` is actually consumed.
- `o_81` is left as a dead-end (0 downstream consumers — confirmed by the leaf-variable structural data).
- The substitute value (`1.0002718`) is numerically close to the computed value (`1.000271755...`), consistent with a deliberately disguised, non-obvious override rather than an error that would fail casual inspection.
- This exactly matches the name-collision signal flagged in the structural reference data: `o_81` (computed OUTPUT, leaf) shares its exact name with `i_82` (root INPUT, consumed downstream, reaching the final reported output `o_97`).

**Consequence:** The final calibration result `o_97` (deltaL, the reported deviation of the 7 mm gauge block from nominal length — the entire purpose of this pipeline) is derived using an unverified, hardcoded refractive-index constant instead of the environmentally-corrected value the graph itself computed from live sensor/report inputs (temperature, pressure, humidity, CO2). Any discrepancy between the true environmental conditions and the hardcoded `1.0002718` — whether from stale calibration, transcription, or intentional manipulation — propagates directly and invisibly into the reported length deviation, while the audit trail superficially shows a complete, correctly-computed refractive-index derivation that was never actually used.

**Secondary observation (lower severity, noted for completeness):** `i_4` and `i_5` (vacuum wavelengths for the TESA SG-L and SG-O gauges) are never consumed anywhere in the graph — two calibration-report-sourced measurements are recorded but structurally inert. This does not itself constitute the substitution attack but is consistent with a broader pattern of asserting multi-instrument provenance while only a single, more easily overridden channel (`i_6`/`i_82`) actually drives the reported result.