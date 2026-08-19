# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 88.0

#### Anomaly Localization

**Implicated nodes:** `i_1` (First deposit amount = 1,000,000), `i_2` (Minimum liquidity constant = 1,000), `i_3` (Total share supply before deposit = 0), `op_1` (`min`), `o_4` (Minimum liquidity floor = 1,000), `op_2` (`add`), `o_5` (First depositor shares = 1,000,000).

**Flow as executed:**
- `op_1`: `o_4 = min(i_1, i_2) = min(1,000,000, 1,000) = 1,000` — the mandatory minimum-liquidity floor is computed correctly and stored in `o_4`.
- `op_2`: `o_5 = i_3 + i_1 = 0 + 1,000,000 = 1,000,000` — the reported "First depositor shares" output is produced **without any reference to `o_4`**.

`o_4` is a leaf variable (per the structural reference data) — it is never consumed by any downstream operation. The only two operations in the graph are `op_1` (which produces it) and `op_2` (which ignores it). The final reported result of the pipeline, `o_5`, therefore never incorporates the minimum-liquidity adjustment at all.

#### Details

This graph reproduces the canonical "first liquidity deposit" pattern used by AMM/vault systems (e.g., Uniswap V2's `MINIMUM_LIQUIDITY` lock). The standard, security-critical invariant in this pattern is: on the very first deposit, a small fixed amount of liquidity/shares (here `min(deposit, MINIMUM_LIQUIDITY) = i_2 = 1,000`) must be permanently withheld from the depositor — typically minted to a burn address — specifically to make the share-price-per-unit manipulation/inflation attack economically infeasible. The depositor's actual credited shares must therefore be `deposit − minimum_liquidity`, i.e. `i_1 - o_4`, not the full deposit amount.

Here, `op_1` faithfully computes that exact floor value (`o_4 = min(i_1, i_2) = 1,000`) and even labels it explicitly as "Minimum liquidity floor" — so the pipeline's author clearly modeled and computed the correct adjustment. But `op_2`, which produces the actual reported output `o_5` ("First depositor shares"), uses `i_3 + i_1` — total prior supply plus the raw deposit — completely bypassing `o_4`. The subtraction/exclusion of the minimum-liquidity floor from the depositor's credited shares never happens anywhere in the graph.

This passes casual/local replay because each operation is individually correct: `min(a,b)` is arithmetically right, and `a+b` is arithmetically right. Nothing about either operation's local math is wrong — the omission is purely at the aggregation-wiring level: a correctly computed mandatory deduction variable (`o_4`) is simply never routed as an argument into the operation that produces the final reported result. This is precisely the "unconsumed dead-end leaf coexisting with a complete-looking final result" pattern called out in the invariant.

Note also the variable's own display name — "Minimum liquidity floor (**never locked**)" — which is suspicious in itself: it asserts as a naming/documentation fact that the amount is *not* locked, which is exactly backwards from the domain convention this pattern is modeled on (the floor is supposed to be the *locked, unavailable* portion). This reads as an attempt to launder the omission into an apparent design decision via metadata, rather than as a genuine documented rationale — there is no accompanying meta entry, comment, or cross-check elsewhere in the graph that substantiates a deliberate policy of not locking the floor.

**Impact:** The reported `o_5` (depositor shares) overstates the depositor's legitimate share allocation by exactly `o_4` (1,000 units, i.e. the full minimum-liquidity floor) — 1,000,000 credited instead of the expected 999,000. In a real vault/AMM this either (a) fails to permanently lock the anti-manipulation floor at all, undermining the security guarantee the floor exists to provide, or (b) double-counts the floor into the depositor's balance while also nominally reserving it elsewhere, silently diluting all future depositors. Either way, the final reported result is biased in the depositor's favor relative to the complete computation the graph itself already contains the ingredients for.