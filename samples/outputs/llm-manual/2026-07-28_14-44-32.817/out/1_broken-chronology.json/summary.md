# Compprov Analytics Report: src\test\resources\snapshots\malicious\broken-chronology.json

Generated: 2026-07-28T09:44:33.012221500Z[UTC]

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

