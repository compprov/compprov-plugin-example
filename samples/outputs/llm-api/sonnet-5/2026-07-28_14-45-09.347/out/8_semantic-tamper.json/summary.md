# Compprov Analytics Report: src\test\resources\snapshots\malicious\semantic-tamper.json

Generated: 2026-07-28T10:50:59.769087700Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 68.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 78.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | SUSPICIOUS LOGIC | 78.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 93.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | TOPOLOGICAL ANOMALY | 88.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

