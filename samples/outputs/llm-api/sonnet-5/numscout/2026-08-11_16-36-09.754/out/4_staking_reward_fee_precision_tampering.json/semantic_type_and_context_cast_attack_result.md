# Summary
- **Verdict**: CONTEXT MISMATCH
- **Confidence score**: 70.0

## Anomaly Localization

**Implicated nodes:** `i_1` (Accumulated staking reward = 199), `i_2` (Developer fee percent = 10), `i_3` (Percent denominator = 100), `op_1` (divide, formula `a/b`), `o_4` ("Reward / denominator" = 1), `op_2` (multiply, formula `a*b`), `o_5` ("Developer reward (fee)" = 10).

**Flow of the attack:**
1. `op_1` computes `i_1 / i_3` = `199 / 100`. Because the operand types are `java.math.BigInteger` (integer division, no fractional remainder retained), this truncates to `1`, discarding the `.99` fractional remainder.
2. This truncated intermediate `o_4` — labeled generically as "Reward / denominator" — is then fed into `op_2` as the `a` operand and multiplied by `i_2` (`Developer fee percent = 10`), yielding `o_5 = 10`.
3. The mathematically equivalent (and standard, precision-preserving) formulation for a percentage fee is `(reward * percent) / denominator` = `(199 * 10) / 100 = 1990 / 100 = 19` (still truncated once, but only after preserving full precision through the multiply). The graph instead truncates *before* multiplying, compounding the rounding loss and producing `10` instead of the correct `19` — an understatement of the developer fee by roughly 47%.

## Details

The technical type-safety check passes trivially: every variable is a `BigInteger`, every operation's `formula` metadata (`a/b`, `a/b`... `a*b`) exactly matches the operation actually performed, and mathematical replay of each individual node succeeds — `199/100` really does equal `1` in integer arithmetic, and `1*10` really does equal `10`. A naive or purely mechanical validator that only checks node-local formula correctness and type continuity will find nothing wrong.

What is missing is any domain-level justification for the *order* in which these two arithmetic steps are composed. The variable `o_4` is described only as "Reward / denominator" — a label that is technically accurate but conceals the fact that, at this point in the graph, the value has already been silently recast from "a precise proportional share of the reward" (the business quantity actually needed to compute a percentage fee) into "a floor-truncated integer count" (a materially different quantity, since 1.99 rounds/truncates to 1). `op_2` then consumes this truncated integer exactly as if it were still the precise fractional share the fee formula requires — i.e., it consumes a variable under a business definition ("1% share of reward, suitable for scaling into a fee") that conflicts with what the value actually is after `op_1` ("floor(reward/100)", an integer with the fractional remainder permanently destroyed). No domain-justified transformation node, rounding-mode annotation, or `descriptor.meta` entry documents or discloses this truncation; the `meta` arrays for both `o_4` and the surrounding operations are empty.

This is precisely the pattern flagged by the attack vector under audit: two type-safe, formula-consistent, individually-verifiable operations are composed in an order that silently changes the business meaning and magnitude of the final "Developer reward (fee)" output, without any explicit transformation logic or metadata disclosing the domain shift. The consequence is directly material to the pipeline's stated purpose (developer fee deduction): for any reward value not an exact multiple of the denominator, the developer fee is systematically and silently understated relative to the standard multiply-then-divide fee formula, benefiting whichever party is not the developer. The isolation of this single reorder in an otherwise clean, minimal two-operation graph is consistent with a surgical, targeted substitution rather than incidental variance.