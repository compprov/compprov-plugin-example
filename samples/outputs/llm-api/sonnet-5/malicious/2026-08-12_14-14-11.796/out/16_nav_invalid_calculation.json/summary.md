# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\legacy\nav_invalid_calculation.json

Generated: 2026-08-12T11:10:00.057685600Z[UTC]

**Calculation validity**: ❌ Invalid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Invalid variable value detected. Expected: Amount{currency=USD, amount=441749.17}. Computed: Amount{currency=USD, amount=431749.17}. Variable id: o_14
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 89.0 | `calculation_omission_result.md` |
| Lineage disconnection | LINEAGE BREAK DETECTED | 95.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 97.0 | `precision_tampering_result.md` |
| Semantic violation | CONTEXT MISMATCH | 78.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | DOUBLE COUNTING DETECTED | 91.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

