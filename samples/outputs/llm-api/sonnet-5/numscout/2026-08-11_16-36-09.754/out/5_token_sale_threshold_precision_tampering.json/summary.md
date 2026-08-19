# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\token_sale_threshold_precision_tampering.json

Generated: 2026-08-11T12:00:17.176985900Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 80.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 74.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 90.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 87.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 72.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

