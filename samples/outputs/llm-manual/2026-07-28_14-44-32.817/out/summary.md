# Compprov Analytics Summary

Generated: 2026-07-28T09:44:33.286917100Z[UTC]

## Overview

| File | Calculation | Chronology | Highlights | Calculation omission | Lineage disconnection | Precision tampering | Semantic violation | Double counting |
|---|---|---|---|---|---|---|---|---|
| src\test\resources\snapshots\normal\profit.json | ✅ Valid | ✅ Valid | 1 | — | — | — | — | — |
| src\test\resources\snapshots\malicious\nav_invalid_calculation.json | ❌ Invalid | ✅ Valid | 2 | — | — | — | — | — |
| src\test\resources\snapshots\malicious\ciddor-substitution-1-1.json | ✅ Valid | ✅ Valid | 4 | — | — | — | — | — |
| src\test\resources\snapshots\malicious\gas-costs-omission.json | ✅ Valid | ✅ Valid | 2 | — | — | — | — | — |
| src\test\resources\snapshots\malicious\payout-slicing.json | ✅ Valid | ✅ Valid | 3 | — | — | — | — | — |
| src\test\resources\snapshots\normal\payout.json | ✅ Valid | ✅ Valid | 3 | — | — | — | — | — |
| src\test\resources\snapshots\malicious\semantic-tamper.json | ✅ Valid | ✅ Valid | 1 | — | — | — | — | — |
| src\test\resources\snapshots\normal\metrology.json | ✅ Valid | ✅ Valid | 1 | — | — | — | — | — |
| src\test\resources\snapshots\malicious\ciddor-substitution-similar.json | ✅ Valid | ✅ Valid | 3 | — | — | — | — | — |
| src\test\resources\snapshots\malicious\gas-double-subtraction.json | ✅ Valid | ✅ Valid | 1 | — | — | — | — | — |
| src\test\resources\snapshots\malicious\broken-chronology.json | ✅ Valid | ❌ Invalid | 11 | — | — | — | — | — |
| src\test\resources\snapshots\normal\net_asset_value.json | ✅ Valid | ✅ Valid | 1 | — | — | — | — | — |

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


## Fraud-Pattern Analysis

No chat model is configured, so the fraud-pattern prompts below were generated but not sent to an LLM. You can either:

- copy each prompt file listed below into the user interface of an LLM of your choice and review the response manually, or
- re-run this tool with `--plugin=<path-to-plugin-jar>` pointing to a plugin that supplies a `dev.langchain4j.model.chat.ChatModel` implementation, so the prompts are sent automatically and verdicts appear in this report.

| Prompt | Prompt file |
|---|---|
| Calculation omission | `calculation_omission_prompt.md` |
| Lineage disconnection | `lineage_disconnection_and_context_substitution_prompt.md` |
| Precision tampering | `precision_tampering_prompt.md` |
| Semantic violation | `semantic_type_and_context_cast_attack_prompt.md` |
| Double counting | `topological_accumulation_fraud_via_double_counting_prompt.md` |

