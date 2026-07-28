# Compprov Analytics Report: src\test\resources\snapshots\malicious\broken-chronology.json

Generated: 2026-07-28T09:49:19.715118700Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ❌ Invalid

## Structural Highlights

- Multi-used variables detected, please check CPG for double-counting
- Broken chronology detected. Variable i_3 created after operation op_1 started
- Broken chronology detected. Variable i_3 created after operation op_4 started
- Broken chronology detected. Variable i_3 created after operation op_7 started
- Broken chronology detected. Variable i_3 created after operation op_10 started
- Broken chronology detected. Variable i_3 created after operation op_13 started
- Broken chronology detected. Variable i_3 created after operation op_16 started
- Broken chronology detected. Variable i_3 created after operation op_19 started
- Broken chronology detected. Variable i_3 created after operation op_22 started
- Broken chronology detected. Variable i_3 created after operation op_25 started
- Broken chronology detected. Variable i_3 created after operation op_28 started

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Calculation omission | CLEAN | 88.0 | `calculation_omission_result.md` |
| Lineage disconnection | CLEAN | 92.0 | `lineage_disconnection_and_context_substitution_result.md` |
| Precision tampering | CLEAN | 93.0 | `precision_tampering_result.md` |
| Semantic violation | CLEAN | 76.0 | `semantic_type_and_context_cast_attack_result.md` |
| Double counting | CLEAN | 90.0 | `topological_accumulation_fraud_via_double_counting_result.md` |

