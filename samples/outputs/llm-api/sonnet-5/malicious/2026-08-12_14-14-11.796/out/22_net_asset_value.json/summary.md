# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\lineage_disconnection\net_asset_value.json

Generated: 2026-08-12T11:52:29.985529400Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 2 please make sure the CPG has no gaps and substitutions
- Unused roots detected
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 92.0 | `calculation_omission_result.md` |
| Lineage disconnection | LINEAGE BREAK DETECTED | 91.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 88.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 86.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 78.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

