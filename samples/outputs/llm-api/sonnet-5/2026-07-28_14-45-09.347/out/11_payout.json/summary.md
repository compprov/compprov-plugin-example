# Compprov Analytics Report: src\test\resources\snapshots\normal\payout.json

Generated: 2026-07-28T11:11:53.031455200Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 3 please make sure the CPG has no gaps and substitutions
- Unused roots detected
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CLEAN | 88.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 88.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | CLEAN | 92.0 | `precision_tampering_result.md` |
| Semantic violation | CLEAN | 76.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 88.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

