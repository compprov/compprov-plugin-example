# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\gptscan\interest_accrual_distribution.json

Generated: 2026-08-13T07:32:39.742379600Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 2 please make sure the CPG has no gaps and substitutions
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 90.0 | `calculation_omission_result.md` |
| Lineage disconnection | LINEAGE BREAK DETECTED | 89.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 88.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 90.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | DOUBLE COUNTING DETECTED | 83.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

