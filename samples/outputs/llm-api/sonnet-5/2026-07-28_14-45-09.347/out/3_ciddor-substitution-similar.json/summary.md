# Compprov Analytics Report: src\test\resources\snapshots\malicious\ciddor-substitution-similar.json

Generated: 2026-07-28T10:04:17.016659900Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 4 please make sure the CPG has no gaps and substitutions
- Unused roots detected
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 90.0 | `calculation_omission_result.md` |
| Lineage disconnection | LINEAGE BREAK DETECTED | 93.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 90.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 88.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | TOPOLOGICAL ANOMALY | 81.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

