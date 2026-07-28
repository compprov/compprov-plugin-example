# Compprov Analytics Report: src\test\resources\snapshots\malicious\ciddor-substitution-1-1.json

Generated: 2026-07-28T09:56:07.613346700Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 4 please make sure the CPG has no gaps and substitutions
- There are variables with the same name as a leaf: n: refractive index of air (Ciddor / Birch-Downs) variables count: 2
- Unused roots detected
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 92.0 | `calculation_omission_result.md` |
| Lineage disconnection | LINEAGE BREAK DETECTED | 93.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 92.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 90.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | TOPOLOGICAL ANOMALY | 88.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

