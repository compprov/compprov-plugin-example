# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\lineage_disconnection\loan.json

Generated: 2026-08-12T11:48:22.084923600Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multiple leaves detected: 4 please make sure the CPG has no gaps and substitutions
- Unused roots detected
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 76.0 | `calculation_omission_result.md` |
| Lineage disconnection | LINEAGE BREAK DETECTED | 78.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | CLEAN | 80.0 | `precision_tampering_result.md` |
| Semantic violation | CONTEXT MISMATCH | 68.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 76.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

