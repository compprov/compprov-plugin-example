# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\calculation_omission\insurance.json

Generated: 2026-08-13T04:46:33.449868300Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 2 please make sure the CPG has no gaps and substitutions
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 97.0 | `calculation_omission_result.md` |
| Lineage disconnection | LINEAGE BREAK DETECTED | 93.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 94.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 88.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | TOPOLOGICAL ANOMALY | 88.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

