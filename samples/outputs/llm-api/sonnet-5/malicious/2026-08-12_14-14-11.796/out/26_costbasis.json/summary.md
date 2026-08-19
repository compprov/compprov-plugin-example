# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\precision_tampering\costbasis.json

Generated: 2026-08-12T12:13:59.136543600Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting
- Multiple math contexts detected, please check CPG for rounding fraud

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 66.0 | `calculation_omission_result.md` |
| Lineage disconnection | SUSPICIOUS SUBSTITUTION | 83.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 92.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 89.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 83.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

