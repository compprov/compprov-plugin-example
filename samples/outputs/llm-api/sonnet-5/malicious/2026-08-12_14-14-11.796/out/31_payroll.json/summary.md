# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\precision_tampering\payroll.json

Generated: 2026-08-12T12:50:19.793459500Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting
- Multiple math contexts detected, please check CPG for rounding fraud

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CLEAN | 72.0 | `calculation_omission_result.md` |
| Lineage disconnection | SUSPICIOUS SUBSTITUTION | 97.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 93.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 93.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 78.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

