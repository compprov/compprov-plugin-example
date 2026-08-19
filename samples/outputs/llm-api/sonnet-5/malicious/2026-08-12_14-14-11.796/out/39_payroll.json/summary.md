# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\semantic_tamper\payroll.json

Generated: 2026-08-12T13:42:39.632138600Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 78.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 78.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 88.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 88.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | DOUBLE COUNTING DETECTED | 78.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

