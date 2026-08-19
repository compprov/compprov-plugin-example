# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\double_counting\commission.json

Generated: 2026-08-12T10:03:56.592501800Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CLEAN | 72.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 85.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 87.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 81.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | DOUBLE COUNTING DETECTED | 83.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

