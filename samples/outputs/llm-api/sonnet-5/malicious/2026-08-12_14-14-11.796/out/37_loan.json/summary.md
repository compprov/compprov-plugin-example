# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\semantic_tamper\loan.json

Generated: 2026-08-12T13:25:06.722754600Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 3 please make sure the CPG has no gaps and substitutions
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 91.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 68.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 90.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 91.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | TOPOLOGICAL ANOMALY | 85.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

