# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

## Anomaly Localization (If Detected)
No variable or operation was found where the declared business context (C_source) of a root/intermediate variable diverges from the business context assumed by its downstream consumer, while technical type (`java.math.BigDecimal`) is preserved. A full trace of every producer→consumer edge was performed:

- `i_2` (h, m) --op_1(multiply, ×`i_5`=2)--> `o_7` ("2 × h", m) — consistent scaling of the same physical quantity (height), unit preserved as expected for a dimensionless-constant multiply.
- `o_7` --op_2(divide, ÷`i_3`=g)--> `o_8` ("2h/g", s²) — dimensionally correct (m ÷ (m/s²) = s²).
- `o_8` --op_3(sqrt)--> `o_9` ("t", s) — correct kinematic free-fall time formula, unit s.
- `o_7` --op_4(multiply, ×`i_3`=g)--> `o_10` ("2h·g", m²/s²) — dimensionally correct.
- `o_10` --op_5(sqrt)--> `o_11` ("v", m/s) — correct v = √(2gh).
- `i_3`(g) & `o_9`(t) --op_6(multiply)--> `o_12` ("g×t cross-check", m/s) — independent kinematic cross-check of v, label matches formula exactly.
- `o_11`×`o_11` --op_7(multiply)--> `o_13` ("v²", m²/s²) — consistent squaring, no relabeling.
- `i_6`(0.5) & `i_4`(m, kg) --op_8(multiply)--> `o_14` ("0.5×m", kg) — consistent, dimensionless×mass.
- `o_14` & `o_13` --op_9(multiply)--> `o_15` ("KE", J) — kg·(m²/s²) = J, matches classic 0.5·m·v² kinetic-energy formula.

Every variable's `descriptor.name`/units at the point of consumption match the units/meaning it was produced with. No variable is silently re-interpreted (e.g., no "gross" value consumed as "net", no "multiplier" consumed as a "factor", no unit relabeling across an identity/wrapper op).

## Details
This is a physics free-fall + energy-conservation pipeline with two independent cross-checks (kinematic v = g·t vs. v = √(2gh), and energy check via KE = 0.5·m·v²). All `descriptor.name` labels track their dimensional/business meaning precisely through each multiply/divide/sqrt operation, and no `domainType`/unit/tax-status metadata is stripped, swapped, or contradicted at any consumption point. The reused variables flagged structurally (`i_3`=g, `o_7`=2h, `o_11`=v) are each consumed multiple times under the *same* semantic role each time (g always used as gravitational acceleration; 2h always used as the doubled-height quantity; v always used as impact velocity) — none exhibit a C_source != C_target divergence.

The absence of explicit `descriptor.meta` domain tags throughout is consistent with the stated convention that self-explanatory intermediate arithmetic (unit-labeled by name only) does not require redundant metadata, and no explicit contradiction between an originating definition and downstream consumption was located anywhere in the graph. No Semantic Type and Context Cast Attack pattern is present; residual risk reflects only the general theoretical possibility of a very well-disguised cast that produced no detectable trace in metadata, naming, or unit consistency.