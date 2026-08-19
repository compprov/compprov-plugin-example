# Summary
- **Verdict**: DOUBLE COUNTING DETECTED
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:** `o_27` (Total payout), `i_28` (Reinsurance recovery rate), `op_14` (multiply), `o_29` (Reinsurance recovery), `op_15` (add), `o_30` (Net loss — terminal output).

**Attack flow:**
1. `op_13` (addBulk) correctly aggregates the three independent claim-type payout streams (`o_10` Collision=6000.0000, `o_18` Comprehensive=12000.00, `o_26` Liability=4750.0000) into `o_27` = 22750.0000 ("Total payout"). This step is clean — each root claim (i_3..i_6, i_11..i_14, i_19..i_22) contributes exactly once to `o_27`.
2. `op_14` derives `o_29` ("Reinsurance recovery") = `o_27` * `i_28` (0.40) = 9100.000000. This is a legitimate derived quantity — a 40% share of the already-computed Total payout.
3. `op_15` then computes the terminal output `o_30` ("Net loss") = `o_27` **+** `o_29` = 22750.0000 + 9100.000000 = 31850.000000.

The terminal output `o_30` therefore receives the value of `o_27` (Total payout) through **two concurrent paths that reinforce rather than net against each other**:
- Path A (direct): `o_27` → `op_15` (argument `a`).
- Path B (indirect): `o_27` → `op_14` → `o_29` → `op_15` (argument `b`).

Both paths terminate at the same aggregation node (`op_15`) and both paths are combined via **addition**, giving `o_30` = `o_27` * 1.4 rather than a proper netting. This is a path-multiplicity violation on `o_27` with respect to the terminal output `o_30`: M(o_27, op_15) = 2, contributing positively both times, instead of the expected M = 1 (with the reinsurance share properly *subtracted* as an offset, not added as a reinforcement).

## Details

**Why this passes local/casual replay:** Every individual operation in the graph is internally arithmetically consistent under the stated `MathContext` (precision 16, HALF_EVEN) — `op_14`'s multiply and `op_15`'s add both replay correctly given their stored operands and produce the exact stored results (9100.000000 and 31850.000000 respectively). A checker that verifies each operation node in isolation ("does result = f(inputs)?") will find zero discrepancies. The fraud is not in any single node's arithmetic — it is in the **topology**: the same underlying entity (Total payout, `o_27`, itself already a rollup of every root claim in the file) is fed into the final aggregation both directly and via a derived multiple of itself, and the combining operator chosen (`add`) causes that entity to be double-counted into the reported "Net loss" rather than netted against it.

**Why this is economically/semantically wrong, not a benign design choice:** Reinsurance recovery is, by definition, cash received by the ceding insurer from its reinsurer that *reduces* the insurer's retained loss. The standard, universally-documented relationship is `Net loss = Total payout − Reinsurance recovery`. The graph instead computes `Net loss = Total payout + Reinsurance recovery`, which is the exact algebraic inverse of the correct netting operation, and produces a result (31850.0000) that is *larger* than the gross Total payout (22750.0000) it's supposed to be a net-of-recovery figure for — an internally contradictory result no legitimate reinsurance accounting convention would produce. There is no `meta` annotation, override flag, or documented rationale anywhere in the graph (on `o_29`, `o_30`, `op_14`, or `op_15`) explaining this non-standard treatment; the operation's own `formula` metadata (`(a+b)mc`) confirms `add` was deliberately used, not a labeling artifact.

**Consequence:** The reported "Net loss" of 31850.000000 overstates the insurer's true retained loss (which should be 22750.0000 − 9100.000000 = 13650.000000) by exactly the doubled recovery amount (18200.000000, i.e., 2× the 9100 recovery). This is a textbook instance of the deduction/inflation-mirrored double-counting pattern described in the attack vector: a derived subtotal (`o_27`) that already flows into the terminal aggregate is re-introduced a second time through a secondary calculation path (`op_14`→`o_29`) that should have functioned as an *offset* but was instead wired as a *reinforcement*, silently inflating the headline financial metric while surviving node-level replay.
