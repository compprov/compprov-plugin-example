# Compprov Analytics Report: src\test\resources\snapshots\normal\net_asset_value.json

Generated: 2026-07-28T11:08:25.762334700Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CLEAN | 88.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 87.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | SUSPICIOUS LOGIC | 74.0 | `precision_tampering_result.md` |
| Semantic violation | CLEAN | 68.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 90.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

