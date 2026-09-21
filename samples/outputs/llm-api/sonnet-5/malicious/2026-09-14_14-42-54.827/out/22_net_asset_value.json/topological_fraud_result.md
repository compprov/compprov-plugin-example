# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 93.0

## Anomaly Localization

**Implicated nodes:** `i_1`, `i_4`, `op_1`, `o_9` (correct BTC→USD computation chain); `i_10` (substituted root input); `op_6`, `o_15` (terminal aggregation and reported NAV).

**Attack flow:**
1. `op_1` (`convert`) correctly computes the USD value of the BTC balance: `i_4` (9.91350497 BTC) × `i_1` (68989.72 USD/BTC) → `o_9` = **683,929.93 USD**, labeled `"BTC->USD"`. This computation is arithmetically correct and fully traceable to root inputs.
2. `o_9` is never consumed by any downstream operation — confirmed by the structural reference data listing `o_9` as a leaf. A properly computed, mandatory contributor (the BTC leg of the NAV) is thus a dead-end.
3. In its place, a root `INPUT` variable `i_10`, named `"BTC amount in USD"` — a name that is semantically identical in role to `o_9` ("BTC->USD") but is *not* the exact same string, evading the naive exact-name-collision check — is injected directly into the terminal aggregation `op_6` (`addBulk`) as argument `a`, alongside `o_11`, `o_12`, `o_13`, `o_14`.
4. `i_10` carries a hardcoded value of **752,322.92 USD**, with no producing operation and no traceable lineage to `i_4`/`i_1`.
5. The terminal output `o_15` ("Assets sum") = `i_10 + o_11 + o_12 + o_13 + o_14` = 752,322.92 + 202,100.78 + 83,482.41 + 159,606.12 + 57,989.55 = **1,255,501.78 USD**, exactly matching the reported value.

Recomputing with the correctly-derived `o_9` in place of `i_10` yields:
683,929.93 + 202,100.78 + 83,482.41 + 159,606.12 + 57,989.55 = **1,187,108.79 USD**.

The discrepancy (**+68,392.99 USD**, ~5.76% inflation of reported NAV) is exactly the difference between the hardcoded `i_10` and the legitimately computed `o_9`.

## Details

This is a textbook **Lineage Disconnection and Context Substitution** ($M=1$, wrong source):

- Cardinality into the terminal sum looks correct — exactly one "BTC leg" value reaches `op_6` — so naive replay of `op_6` passes locally, and `op_1`'s local replay also passes (it correctly derives `o_9`). Neither single-step check catches the fraud.
- The actual defect is that the value consumed by the terminal aggregation (`i_10`) does not derive from the graph's own root-to-terminal computation chain; it is an unmonitored, hardcoded stand-in declared as a plain `INPUT` with no producing operation, sitting in the exact structural position (`addBulk` argument `a`) that the correctly-computed `o_9` should occupy.
- The correctly-computed sibling `o_9` is left orphaned as a leaf — this is the necessary structural signature of this attack, not evidence of an unrelated, non-competing branch.
- The near-but-not-exact name match ("BTC->USD" vs. "BTC amount in USD") and the numerically plausible (same order of magnitude) substituted value are precisely the disguise techniques that defeat exact-string and gross-magnitude sanity checks, while still being wrong by a material, non-rounding-scale amount (68,392.99 USD, far beyond any DOWN-truncation tolerance).
- Consequence: the reported "Assets sum" (`o_15` = 1,255,501.78 USD) overstates the true NAV (1,187,108.79 USD) by roughly 5.76%, directly inflating the fund's/portfolio's reported net asset value based on a fabricated BTC valuation leg rather than the live-rate-derived one the pipeline itself computed.

This satisfies the $Origin\_Propagation\_Valid$ failure condition explicitly: the terminal output does not derive from full forward propagation of the true root inputs (`i_1`, `i_4`) through the graph's own computation chain — it substitutes a foreign, hardcoded context at the critical aggregation step.