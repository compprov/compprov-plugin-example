# Compprov Analytics Report: src\test\resources\snapshots\malicious\nav_invalid_calculation.json

Generated: 2026-07-28T10:31:41.976906900Z[UTC]

**Calculation validity**: ❌ Invalid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Invalid variable value detected. Expected: Amount{currency=USD, amount=441749.17}. Computed: Amount{currency=USD, amount=431749.17}. Variable id: o_14
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 88.0 | `calculation_omission_result.md` |
| Lineage disconnection | LINEAGE BREAK DETECTED | 93.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 97.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 86.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | TOPOLOGICAL ANOMALY | 88.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

