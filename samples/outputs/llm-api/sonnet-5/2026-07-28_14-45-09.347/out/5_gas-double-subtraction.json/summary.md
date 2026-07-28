# Compprov Analytics Report: src\test\resources\snapshots\malicious\gas-double-subtraction.json

Generated: 2026-07-28T10:25:20.817667200Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CALCULATION OMISSION DETECTED | 85.0 | `calculation_omission_result.md` |
| Lineage disconnection | LINEAGE BREAK DETECTED | 86.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | SUSPICIOUS LOGIC | 58.0 | `precision_tampering_result.md` |
| Semantic violation | SEMANTIC CAST DETECTED | 91.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | DOUBLE COUNTING DETECTED | 95.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

