# Compprov Analytics Report: src\test\resources\snapshots\normal\profit.json

Generated: 2026-07-28T11:23:42.474011800Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CLEAN | 78.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 78.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | SUSPICIOUS LOGIC | 58.0 | `precision_tampering_result.md` |
| Semantic violation | CONTEXT MISMATCH | 63.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 70.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

