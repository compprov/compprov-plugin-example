# Summary
- **Verdict**: CLEAN
- **Confidence score**: 90.0

## Audit Summary
The CPG was audited specifically for **Lineage Disconnection and Context Substitution** — the pattern where a correctly-computed intermediate variable is orphaned while a hardcoded or foreign value with the same name/role is routed into a downstream calculation instead.

### Structural Reference Cross-Check
- **Leaf variables**: only `o_30` ("Net loss") is unconsumed. This is expected and benign — it is the final reported output of the pipeline (Total payout minus Reinsurance recovery), and nothing downstream exists for it to feed into.
- **Name-collision set**: empty. No leaf shares an exact display name with another variable, so the textbook signature (computed OUTPUT orphaned while an identically-named root INPUT is substituted downstream) does not fire.
- **Multi-consumer variables**: `i_2` ("Zero (claim floor)") and `o_27` ("Total payout"). Both are legitimate reuse patterns: `i_2` is a genuine floor constant reused across three independent claim-type branches (Collision, Comprehensive, Liability), and `o_27` is correctly consumed twice downstream — once to compute reinsurance recovery (`op_14`) and once to compute net loss (`op_15`) — both directly from its own `resultId`, not from a substitute.

### Full Forward-Propagation Trace (per attack vector requirement)
Every operation's arguments were checked against the literal `resultId` of the operation that should have produced them, not merely replayed for arithmetic correctness:

- Collision: `i_3,i_4` → `op_1`→`o_7` → `op_2`(with `i_2`)→`o_8` → `op_3`(with `i_5`)→`o_9` → `op_4`(with `i_6`)→`o_10`. Each step consumes the prior step's actual `resultId`; final `o_10=6000.0000` matches recompute exactly.
- Comprehensive: `i_11,i_12`→`op_5`→`o_15`→`op_6`(`i_2`)→`o_16`→`op_7`(`i_13`)→`o_17`→`op_8`(`i_14`)→`o_18=12000.00`. Chain intact, matches recompute.
- Liability: `i_19,i_20`→`op_9`→`o_23`→`op_10`(`i_2`)→`o_24`→`op_11`(`i_21`)→`o_25`→`op_12`(`i_22`)→`o_26=4750.0000`. Chain intact, matches recompute.
- Aggregation: `op_13` (`addBulk`) consumes exactly `o_10, o_18, o_26` (the true computed payout outputs, not any substitute) → `o_27=22750.0000` — matches 6000+12000+4750 precisely.
- `op_14`: `o_27 * i_28(0.40)` → `o_29=9100.000000` — matches recompute exactly.
- `op_15`: `o_27 - o_29` → `o_30=13650.000000` — matches recompute exactly.

No operation was found consuming a variable other than the legitimate `resultId` of its logical predecessor. No computed OUTPUT variable was found to have zero downstream consumers while a same-named, same-role, or metadata-overlapping root INPUT was substituted in its place at a critical junction. The repeated INPUT names ("Claim amount", "Deductible", "Coinsurance rate", "Policy limit") are differentiated by `claimType` metadata and each feeds only its own correct branch — no cross-branch substitution or hijacking was found.

### Conclusion
After full forward propagation from root inputs to the reported final outputs (`o_27`, `o_29`, `o_30`), $O_{derived}$ matches $O_{reported}$ at every step, and every downstream consumer references the actual `resultId` of its logical predecessor. No orphaned computed sibling exists anywhere in the graph that was bypassed in favor of a hardcoded or foreign stand-in. This graph does not exhibit the Lineage Disconnection / Context Substitution pattern.
