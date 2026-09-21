# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\legacy\nav_invalid_calculation.json

Generated: 2026-09-14T10:31:42.090407900Z[UTC]

**Calculation validity**: ❌ Invalid

**Chronology validity**: ✅ Valid

## Structural Highlights

- Invalid variable value detected. Expected: Amount{currency=USD, amount=441749.17}. Computed: Amount{currency=USD, amount=431749.17}. Variable id: o_14
- Multi-used variables detected, please check CPG for double-counting

## Fraud-Pattern Analysis

| Prompt | Verdict | Confidence | Report |
|---|---|---|---|
| Topological fraud | ANOMALY DETECTED | 93.0 | `topological_fraud_result.md` |
| Precision tampering | ANOMALY DETECTED | 96.0 | `precision_tampering_result.md` |
| Semantic violation | ANOMALY DETECTED | 80.0 | `semantic_type_and_context_cast_attack_result.md` |

