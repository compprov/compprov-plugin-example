# Summary
- **Verdict**: CLEAN
- **Confidence score**: 86.0

## Anomaly Localization (If Detected)
No variable/operation pair was found where the declared business meaning (`descriptor.name`, implied units) of a source variable conflicts with the semantic assumption of the consuming operation. Every downstream consumption traced below preserves domain continuity:

- `i_2` (G, N·m²/kg²) & `i_3` (M, kg) → `op_1` (multiply) → `o_7` "G × M, standard gravitational parameter (m³/s²)" — dimensionally and semantically correct (G·M → m³/s²).
- `i_6` ("Constant 2") & `o_7` (μ) → `op_2` (multiply) → `o_8` "2 × G × M" — correct scalar application, no relabeling.
- `o_8` (2GM) & `i_4` ("Planet radius, r") → `op_3` (divide) → `o_9` "2GM / r at surface (m²/s²)" — surface radius correctly paired with the *surface* result label.
- `o_9` → `op_4` (sqrt) → `o_10` "Escape velocity at surface (m/s)" — units resolve m²/s² → m/s correctly, and the "surface" qualifier is preserved from its true surface-radius lineage.
- `i_4` (r) & `i_5` (altitude) → `op_5` (add) → `o_11` "Radius at altitude, r + altitude (m)" — explicit, auditable domain transformation (surface radius → orbital radius), correctly labeled as a distinct derived quantity rather than silently overwriting `i_4`.
- `o_8` (2GM, reused) & `o_11` (radius at altitude) → `op_6` (divide) → `o_12` "2GM / r at altitude (m²/s²)" — the *altitude* denominator is correctly paired with the *altitude* label; `o_8` is reused unchanged (legitimate re-use of the same physical constant μ·2, not a re-cast).
- `o_12` → `op_7` (sqrt) → `o_13` "Escape velocity at altitude (m/s)" — consistent terminal label matching its lineage.

The two variables flagged in the structural reference data as multi-consumed (`i_4`, `o_8`) were both traced explicitly: `i_4` is legitimately consumed both as the raw surface radius (for the surface-escape-velocity branch) and as an addend forming a new, distinctly-named derived variable (`o_11`, radius-at-altitude) for the altitude branch — it is never silently reinterpreted as "radius at altitude" itself. `o_8` (2GM) is reused verbatim in both the surface and altitude branches, which is dimensionally and semantically identical in both consumptions (it is the same physical quantity, not re-cast into a different domain entity).

## Details
This graph implements a two-branch escape-velocity computation (surface vs. orbital-altitude) from first principles: μ = GM, 2μ, then 2μ/r and sqrt for each radius variant. Every operation's declared formula (`(a*b)mc`, `(a/b)mc`, `sqrt(a)mc`) matches its numeric behavior, and every result's declared name/unit is dimensionally consistent with the operation and its operand's declared meaning (verified unit propagation: N·m²/kg²·kg → m³/s²; m³/s² ÷ m → m²/s²; sqrt(m²/s²) → m/s). The one true domain transformation present — surface radius `i_4` being combined with altitude `i_5` to produce a new "radius at altitude" entity `o_11` — is performed via an explicit, auditable `add` operation and assigned a new, distinctly-described variable ID rather than overwriting or silently relabeling the original radius variable, which is exactly the auditable pattern the invariants require for legitimate domain transitions.

All `descriptor.meta` arrays for variables are empty (no `domainType`/`unit`/`taxStatus` tags are populated anywhere in the graph), which limits deep metadata-level verification; however, the `descriptor.name` fields function as the sole carriers of business semantics here, and cross-referencing them against every argument/result binding in all seven operations reveals full semantic continuity with no implicit re-mapping, no identity-passthrough relabeling, and no case of a variable being consumed under an assumption inconsistent with its origin. No isolated or surgical substitution consistent with a Semantic Type and Context Cast attack was identified.

Given the sparse metadata (only names, no explicit unit/domainType tags), full certainty is capped slightly below maximum, but the exhaustive name/unit/lineage trace found no violation of the stated invariants.