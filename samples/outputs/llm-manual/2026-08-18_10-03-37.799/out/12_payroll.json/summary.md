# Compprov Analytics Report: C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\malicious\double_counting\payroll.json

Generated: 2026-08-18T05:03:38.208097900Z[UTC]

**Calculation validity**: ✅ Valid

**Chronology validity**: ✅ Valid

## Structural Highlights

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

