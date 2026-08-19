# Summary
- **Verdict**: DOUBLE COUNTING DETECTED
- **Confidence score**: 97.0

## Anomaly Localization

**Implicated nodes:**
- `i_2` — INPUT, "[0] Sample value (mL)", value `12.5`
- `op_1` — `addBulk`, formula `(a+b0+...+bn)mc`, arguments: `a=i_2, b0=i_3, b1=i_4, b2=i_5, b3=i_6, b4=i_7, b5=i_2, mc=i_1`
- `o_8` — OUTPUT, "Sum of samples (mL)", value `92.6`
- `i_9` — INPUT, "Sample count, n", value `6`
- `o_10` — OUTPUT, "Mean (mL)", value `15.43333333333333` (via `op_2`, `o_8 / i_9`)
- Downstream propagation: `o_11..o_22` (deviations and squared deviations for all 6 samples), `o_23` (Sum of squared deviations), `o_25` (Sample variance), `o_26` (Sample standard deviation)

**Attack flow:**

The `addBulk` operation `op_1`, which is supposed to sum the six independent titration replicates (`i_2` through `i_7`), is wired with **seven** operand slots (`a, b0, b1, b2, b3, b4, b5`) instead of six. Slot `a` and slot `b5` both point to the *same* root variable `i_2` ("[0] Sample value (mL)", 12.5 mL). This means sample [0] is fed into the aggregation node via two parallel argument paths while every other sample (`i_3`–`i_7`) is fed in exactly once.

Arithmetic proof of the injection:
- True sum of the six unique replicates: 12.5 + 15.2 + 11.8 + 14.1 + 13.6 + 12.9 = **80.1**
- Reported `o_8` ("Sum of samples"): **92.6**
- Difference: 92.6 − 80.1 = **12.5**, exactly equal to the value of `i_2` — an exact, non-coincidental match confirming `i_2` was counted twice in the rollup.

This inflated sum then flows directly into `op_2` (`o_8 / i_9`), where the *denominator* `i_9` ("Sample count, n") is still declared as `6` — the correct count of unique physical replicates. This mismatch (7 terms summed, but divided by 6) is itself a structural giveaway that the aggregation and the declared population size have diverged, and it inflates the reported mean (`o_10 = 15.4333...` instead of the true mean of `80.1/6 = 13.35`).

Critically, all downstream deviation operations (`op_3, op_5, op_7, op_9, op_11, op_13`) correctly reference each of the six *unique* sample inputs (`i_2, i_3, i_4, i_5, i_6, i_7`) exactly once against the (already-corrupted) mean `o_10`. This is precisely the disguise mechanism described in the attack vector: the duplication is confined to a single aggregation node (`op_1`) buried among 17 operations, while every other stage of the pipeline replays perfectly and locally consistently — a casual or purely local review of each operation's own formula would find no fault, because each downstream step is individually correct given its (poisoned) inputs.

## Details

**Mechanism:** A single root entity (`i_2`) is mapped into two distinct named argument slots (`a` and `b5`) of the *same* `addBulk` aggregation call. Because `addBulk`'s formula `(a+b0+...+bn)mc` treats each slot as an independent addend, this silently double-counts `i_2`'s value in the total, without altering the surface-level list of root inputs, without reusing an obviously duplicate variable ID, and without touching the `i_9` "sample count" input that a naive consistency check might rely on. The structural reference data flags `i_2` as consumed by multiple operations, but that list alone cannot distinguish legitimate reuse (e.g., `i_2` feeding both the sum and its own deviation calculation in `op_3`) from illegitimate reuse (two argument slots of the *same* aggregation node) — exactly the kind of reuse this audit was tasked to distinguish.

**Why local replay passes:** Every operation's arithmetic is internally correct relative to its own inputs — `op_1` genuinely computes `a+b0+b1+b2+b3+b4+b5` correctly per BigDecimal MathContext rules, `op_2` genuinely computes `o_8/i_9` correctly, and all deviation/variance operations replay correctly given their stated inputs. No single node's math is wrong; the fraud is purely topological — an extra causal edge from `i_2` into `op_1` that has no business being there given only six physical replicates exist.

**Consequences:** The reported "Sum of samples," "Mean," and by extension every downstream statistic that depends on the mean (all six deviations, squared deviations, sum of squared deviations, sample variance, and sample standard deviation) are computed against a population mean skewed toward sample [0]'s value (12.5 mL, the lowest of the six replicates), rather than the true unweighted mean of the six independent titration measurements. This is a textbook instance of Topological Accumulation Fraud via Double Counting: a legitimate root entity (`i_2`) is routed through two parallel argument paths into the same aggregation node, inflating the consolidated total (`M(i_2, op_1) = 2`) in violation of the required invariant `M(V_in, Op_agg) = 1`, with no explicit split/allocation rationale documented anywhere in the graph's metadata.