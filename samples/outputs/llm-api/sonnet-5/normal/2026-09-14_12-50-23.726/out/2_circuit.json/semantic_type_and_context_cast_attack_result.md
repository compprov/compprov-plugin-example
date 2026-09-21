# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

## Semantic Type and Context Cast Audit — Series-Parallel Resistor Network

### Anomaly Localization (If Detected)
No variable or operation was found where the declared business/domain context (C_source) diverges from the context under which a downstream operation consumes it (C_target), despite identical technical types (`java.math.BigDecimal`).

Full lineage trace performed:

- `i_2` (R1, series) + `i_3` (R2, series) --op_1(add)--> `o_7` ("Series resistance, R1+R2") — label matches operation and inputs exactly.
- `i_4` (R3, parallel) × `i_5` (R4, parallel) --op_2(multiply)--> `o_8` ("R3 × R4") — matches.
- `i_4` + `i_5` --op_3(add)--> `o_9` ("R3+R4") — matches; reuse of `i_4`/`i_5` in both op_2 and op_3 is the expected numerator/denominator pattern for a parallel-resistance formula, not a context switch.
- `o_8` / `o_9` --op_4(divide)--> `o_10` ("Parallel resistance, Rparallel") = (R3·R4)/(R3+R4) — canonical parallel formula, label matches.
- `o_7` + `o_10` --op_5(add)--> `o_11` ("Total resistance, Rtotal") — correctly combines series segment with parallel segment per the stated series-parallel topology.
- `i_6` (Supply voltage) / `o_11` --op_6(divide)--> `o_12` ("Total current, I") — Ohm's law, label matches.
- `o_12` × `i_6` --op_7(multiply)--> `o_13` ("Total power, P = I×V") — matches formula in name.
- `o_12` ^ `i_14`(=2) --op_8(pow)--> `o_15` ("I²") — matches.
- `o_15` × `o_11` --op_9(multiply)--> `o_16` ("Total power cross-check, P = I²×Rtotal") — matches formula in name; used only as an independent cross-check, consistent with its own label and not silently relabeled as a different metric.

Every reused variable (`i_4`, `i_5`, `i_6`, `o_11`, `o_12` — per the structural reference list) is consumed twice, but in both cases under the *same* declared domain meaning (e.g., `Rtotal` is used once as a divisor for current and once as a multiplicand for power — both are legitimate uses of "total resistance," not a re-definition of the quantity). `i_1` (MathContext) is correctly excluded as a non-domain technical parameter.

### Details
All numeric outputs are internally consistent with the formulas implied by their own labels (series sum, product-over-sum parallel formula, Ohm's law, and both P=IV and P=I²R power formulas), and the small discrepancy between `o_13` (32.00000000000000) and `o_16` (32.00000000000001) is attributable to normal floating-point rounding under a 16-digit `MathContext` with HALF_EVEN rounding — not a semantic or numeric tampering signal.

No instance was found where a variable's originating `descriptor.name`/domain tag (e.g., "series," "parallel," "Total resistance," "Total current") conflicts with the assumption of the operation that consumes it downstream. There is no evidence of metadata stripping, unit reinterpretation (e.g., Ω silently treated as a different unit), or a value being laundered through an identity/wrapper op to acquire a new business meaning while keeping the same numeric payload. The empty `descriptor.meta` arrays on several outputs are consistent with the stated convention that self-explanatory intermediate aggregates need not carry redundant explicit tags, and in every such case the variable `name` field still unambiguously documents its business meaning and that meaning is honored by all consuming operations.

**Conclusion:** No Semantic Type and Context Cast violation is present in this graph. The pipeline is a clean, mathematically and semantically coherent series-parallel resistor-network computation. Residual risk score reflects only the inherent limits of static provenance auditing (e.g., inability to verify metadata against an external ground-truth schema), not any concrete finding.