# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\double_counting\ecommerce.json

Generated: 2026-08-12T10:13:40.994021400Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 76.0 | `calculation_omission_result.md` |
| Lineage disconnection | SUSPICIOUS SUBSTITUTION | 85.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 90.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 85.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | DOUBLE COUNTING DETECTED | 93.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

