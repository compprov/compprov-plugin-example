# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\gptscan\vault_first_deposit.json

Generated: 2026-08-13T07:42:07.128199500Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 2 please make sure the CPG has no gaps and substitutions
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 88.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 88.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | SUSPICIOUS LOGIC | 63.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 66.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 66.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

