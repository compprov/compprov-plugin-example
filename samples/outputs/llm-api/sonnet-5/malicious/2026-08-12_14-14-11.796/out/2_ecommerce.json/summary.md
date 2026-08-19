# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\calculation_omission\ecommerce.json

Generated: 2026-08-12T09:24:41.377340400Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 2 please make sure the CPG has no gaps and substitutions
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 93.0 | `calculation_omission_result.md` |
| Lineage disconnection | LINEAGE BREAK DETECTED | 58.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | ANOMALY DETECTED | 78.0 | `precision_tampering_result.md` |
| Semantic violation | CONTEXT MISMATCH | 68.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 78.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

