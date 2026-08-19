# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\liquidity_pool_join_precision_tampering.json

Generated: 2026-08-11T11:47:07.443398100Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CLEAN | 85.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 88.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | SUSPICIOUS LOGIC | 63.0 | `precision_tampering_result.md` |
| Semantic violation | CLEAN | 78.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 80.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

