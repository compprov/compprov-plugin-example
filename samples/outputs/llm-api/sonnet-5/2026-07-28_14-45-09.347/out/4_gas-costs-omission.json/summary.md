# Compprov Analytics Report: src\test\resources\snapshots\malicious\gas-costs-omission.json

Generated: 2026-07-28T10:12:09.008324300Z[UTC]

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
| Precision tampering | SUSPICIOUS LOGIC | 66.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 88.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | TOPOLOGICAL ANOMALY | 68.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

