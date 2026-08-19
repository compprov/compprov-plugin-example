# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\calculation_omission\loan.json

Generated: 2026-08-12T09:43:16.248534300Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 4 please make sure the CPG has no gaps and substitutions
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 85.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 74.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 83.0 | `precision_tampering_result.md` |
| Semantic violation | CONTEXT MISMATCH | 78.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 82.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

