# Compprov Analytics Summary

Generated: 2026-07-28T11:23:42.474902200Z[UTC]

## Overview

| File | Calculation | Chronology | Highlights | Calculation omission | Lineage disconnection | Precision tampering | Semantic violation | Double counting |
|---|---|---|---|---|---|---|---|---|
| src\test\resources\snapshots\normal\profit.json | ✅ Valid | ✅ Valid | 1 | CLEAN (78.0) | CLEAN (78.0) | SUSPICIOUS LOGIC (58.0) | CONTEXT MISMATCH (63.0) | CLEAN (70.0) |
| src\test\resources\snapshots\malicious\nav_invalid_calculation.json | ❌ Invalid | ✅ Valid | 2 | CALCULATION OMISSION DETECTED (88.0) | LINEAGE BREAK DETECTED (93.0) | ANOMALY DETECTED (97.0) | SEMANTIC CAST DETECTED (86.0) | TOPOLOGICAL ANOMALY (88.0) |
| src\test\resources\snapshots\malicious\ciddor-substitution-1-1.json | ✅ Valid | ✅ Valid | 4 | CALCULATION OMISSION DETECTED (92.0) | LINEAGE BREAK DETECTED (93.0) | ANOMALY DETECTED (92.0) | SEMANTIC CAST DETECTED (90.0) | TOPOLOGICAL ANOMALY (88.0) |
| src\test\resources\snapshots\malicious\gas-costs-omission.json | ✅ Valid | ✅ Valid | 2 | CALCULATION OMISSION DETECTED (90.0) | LINEAGE BREAK DETECTED (89.0) | SUSPICIOUS LOGIC (66.0) | SEMANTIC CAST DETECTED (88.0) | TOPOLOGICAL ANOMALY (68.0) |
| src\test\resources\snapshots\malicious\payout-slicing.json | ✅ Valid | ✅ Valid | 3 | CALCULATION OMISSION DETECTED (82.0) | SUSPICIOUS SUBSTITUTION (76.0) | ANOMALY DETECTED (87.0) | SEMANTIC CAST DETECTED (86.0) | TOPOLOGICAL ANOMALY (68.0) |
| src\test\resources\snapshots\normal\payout.json | ✅ Valid | ✅ Valid | 3 | CLEAN (88.0) | CLEAN (88.0) | CLEAN (92.0) | CLEAN (76.0) | CLEAN (88.0) |
| src\test\resources\snapshots\malicious\semantic-tamper.json | ✅ Valid | ✅ Valid | 1 | CALCULATION OMISSION DETECTED (68.0) | CLEAN (78.0) | SUSPICIOUS LOGIC (78.0) | SEMANTIC CAST DETECTED (93.0) | TOPOLOGICAL ANOMALY (88.0) |
| src\test\resources\snapshots\normal\metrology.json | ✅ Valid | ✅ Valid | 1 | CLEAN (88.0) | CLEAN (85.0) | SUSPICIOUS LOGIC (72.0) | CLEAN (78.0) | CLEAN (83.0) |
| src\test\resources\snapshots\malicious\ciddor-substitution-similar.json | ✅ Valid | ✅ Valid | 3 | CALCULATION OMISSION DETECTED (90.0) | LINEAGE BREAK DETECTED (93.0) | ANOMALY DETECTED (90.0) | SEMANTIC CAST DETECTED (88.0) | TOPOLOGICAL ANOMALY (81.0) |
| src\test\resources\snapshots\malicious\gas-double-subtraction.json | ✅ Valid | ✅ Valid | 1 | CALCULATION OMISSION DETECTED (85.0) | LINEAGE BREAK DETECTED (86.0) | SUSPICIOUS LOGIC (58.0) | SEMANTIC CAST DETECTED (91.0) | DOUBLE COUNTING DETECTED (95.0) |
| src\test\resources\snapshots\malicious\broken-chronology.json | ✅ Valid | ❌ Invalid | 11 | CLEAN (88.0) | CLEAN (92.0) | CLEAN (93.0) | CLEAN (76.0) | CLEAN (90.0) |
| src\test\resources\snapshots\normal\net_asset_value.json | ✅ Valid | ✅ Valid | 1 | CLEAN (88.0) | CLEAN (87.0) | SUSPICIOUS LOGIC (74.0) | CLEAN (68.0) | CLEAN (90.0) |

## Highlights

### src\test\resources\snapshots\normal\profit.json

- Multi-used variables detected, please check CPG for double-counting

### src\test\resources\snapshots\malicious\nav_invalid_calculation.json

- Invalid variable value detected. Expected: Amount{currency=USD, amount=441749.17}. Computed: Amount{currency=USD, amount=431749.17}. Variable id: o_14
- Multi-used variables detected, please check CPG for double-counting

### src\test\resources\snapshots\malicious\ciddor-substitution-1-1.json

- Multiple leaves detected: 4 please make sure the CPG has no gaps and substitutions
- There are variables with the same name as a leaf: n: refractive index of air (Ciddor / Birch-Downs) variables count: 2
- Unused roots detected
- Multi-used variables detected, please check CPG for double-counting

### src\test\resources\snapshots\malicious\gas-costs-omission.json

- Multiple leaves detected: 2 please make sure the CPG has no gaps and substitutions
- Multi-used variables detected, please check CPG for double-counting

### src\test\resources\snapshots\malicious\payout-slicing.json

- Multi-used variables detected, please check CPG for double-counting
- Multiple math contexts detected, please check CPG for rounding fraud
- Scaling operation is used, please check CPG for rounding fraud

### src\test\resources\snapshots\normal\payout.json

- Multiple leaves detected: 3 please make sure the CPG has no gaps and substitutions
- Unused roots detected
- Multi-used variables detected, please check CPG for double-counting

### src\test\resources\snapshots\malicious\semantic-tamper.json

- Multi-used variables detected, please check CPG for double-counting

### src\test\resources\snapshots\normal\metrology.json

- Multi-used variables detected, please check CPG for double-counting

### src\test\resources\snapshots\malicious\ciddor-substitution-similar.json

- Multiple leaves detected: 4 please make sure the CPG has no gaps and substitutions
- Unused roots detected
- Multi-used variables detected, please check CPG for double-counting

### src\test\resources\snapshots\malicious\gas-double-subtraction.json

- Multi-used variables detected, please check CPG for double-counting

### src\test\resources\snapshots\malicious\broken-chronology.json

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

### src\test\resources\snapshots\normal\net_asset_value.json

- Multi-used variables detected, please check CPG for double-counting

