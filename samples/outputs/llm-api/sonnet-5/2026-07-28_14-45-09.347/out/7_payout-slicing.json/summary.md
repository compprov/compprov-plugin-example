# Compprov Analytics Report: src\test\resources\snapshots\malicious\payout-slicing.json

Generated: 2026-07-28T10:39:55.089584500Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting
- Multiple math contexts detected, please check CPG for rounding fraud
- Scaling operation is used, please check CPG for rounding fraud

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 82.0 | `calculation_omission_result.md` |
| Lineage disconnection | SUSPICIOUS SUBSTITUTION | 76.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 87.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 86.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | TOPOLOGICAL ANOMALY | 68.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

