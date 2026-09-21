# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 72.0

## Anomaly Localization

**Implicated nodes:** `op_1`,`op_2`,`op_3` (option [0] CALL K=4630 chain: `i_3`,`i_5`,`i_6` → `o_7` → `o_8` → `o_9`), and the terminal `op_40` (`addBulk` → `o_64`).

**Comparator (control) chain for every other leg**, e.g. option [1]: `op_4`,`op_5`,`op_6`,`op_7` producing `o_12`→`o_13`→`o_14`→**`o_15`** (the last step, `op_7`, is a `setScale` truncation using `mc=i_4`, precision=2, rounding=DOWN). The identical four‑step pattern (subtract → max → multiply → **setScale**) repeats for every one of the other nine legs: `op_8‑11`(→`o_21`), `op_12‑15`(→`o_27`), `op_16‑19`(→`o_33`), `op_20‑23`(→`o_39`), `op_24‑27`(→`o_45`), `op_28‑31`(→`o_51`), `op_32‑35`(→`o_57`), `op_36‑39`(→`o_63`).

Option [0] is the sole exception: its chain is only **three** steps — `op_1`(subtract)→`op_2`(max)→`op_3`(multiply, result `o_9`) — with **no corresponding `setScale` node**. No operation anywhere in the graph consumes `o_9` except `op_40` (`addBulk`), which takes it directly as argument `a`. Every other leg's *truncated* variable (`o_15,o_21,o_27,o_33,o_39,o_45,o_51,o_57,o_63`) is what actually feeds `op_40` (arguments `b0..b8`), never the corresponding raw multiply output (`o_14,o_20,o_26,o_32,o_38,o_44,o_50,o_56,o_62` are all dead-ended into their own `setScale` op and go no further).

**Attack flow:** raw, full-precision payout for leg 0 (`o_9 = 137.2780000000000`) bypasses the mandatory truncate-to-currency-precision step that the descriptor itself mandates (`rounding: DOWN ... balance-safety invariant`) and that every sibling leg is forced through, then flows straight into the final consolidation `op_40 → o_64`. If leg 0 had been truncated like every other leg (`DOWN`, 2 decimals), it would contribute `137.27` instead of `137.278`, making the reported total `1263.680000000000` instead of the actual `1263.688000000000` — a silent +0.008 inflation on this single settlement.

## Details

This is a **Calculation Omission / Lineage Disconnection hybrid**: the mandatory truncation node (`setScale` with `mc=i_4`) that the pipeline's own repeated structural pattern — and its own descriptor-level policy ("Amount always truncates to the currency's decimal precision; balance-safety invariant") — requires for *every* option leg before it can validly enter the terminal aggregation, was never instantiated for leg [0]. Instead, the terminal `addBulk` operation (`op_40`) consumes the raw pre-truncation multiply output (`o_9`) directly as its base term, in violation of $Origin\_Propagation\_Valid$: the terminal result does not derive uniformly from the graph's own repeated computation chain, because one leg's causal path is missing a required correction step that all nine analogous siblings possess.

Local replay of `op_3` and `op_40` passes cleanly in isolation — each operation's own math is internally consistent — which is exactly why a naive per-node audit would miss this. Only a structural, cross-leg comparison (leg [0] vs. legs [1]-[9]) exposes that the pattern subtract→max→multiply→setScale is truncated to three steps for exactly one leg, and that leg happens to be the one whose raw value is largest in absolute terms among the in-the-money legs contributing to the sum.

**Consequence:** the reported "Total payout in USDC" (`o_64 = 1263.688000000000`) is systematically inflated relative to a fully-consistent truncation policy, because one of ten legs escapes the currency-precision floor enforced everywhere else. In a real payout/settlement system this is a textbook salami-slicing vector: individually small (sub-cent-to-cent) leakage per settlement, reproducible deterministically and invisibly across every batch that includes an ITM leg in the `a` (first) slot of `addBulk`, since the structural gap — not a random rounding artifact — is what enables it.
