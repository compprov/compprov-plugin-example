# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Risk score**: 68.0

## Anomaly Localization

- **Variables involved:** `i_1` (Total game interest pool = 1000), `i_2` (Winner count = 3), `o_3` (Per-winner payout = 333)
- **Operation involved:** `op_1` (`divide`, BigInteger division, formula `a/b`, `a=i_1`, `b=i_2`, `resultId=o_3`)

**Flow:** The entire pool (`i_1` = 1000) is divided by the winner count (`i_2` = 3) using integer (`BigInteger`) division, which truncates rather than rounds. The reported per-winner payout (`o_3` = 333) is stored directly as the terminal OUTPUT/leaf node — it is never multiplied back out, reconciled against `i_1`, or accompanied by any remainder-tracking variable.

## Details

### Arithmetic recomputation
Exact rational division: 1000 / 3 = 333.333...
BigInteger truncating division yields 333, which is what is reported — so the single division operation itself is internally consistent with standard `BigInteger` truncation semantics (no MathContext exists on this wrapper type, so there is no declared rounding convention to violate).

### The real issue: conservation, not rounding mode
The invariant violation is not "333 vs. 333.33" — it is that the **total distributed** amount, 3 × 333 = 999, does not equal the **total pool**, 1000. One full unit of the asset (`i_1`'s currency/interest unit) evaporates from the graph with no lineage: it is not returned to the pool, not assigned to any winner, not recorded as a remainder/dust variable, and does not appear in any OUTPUT node. The CPG contains no `mod`/`remainder` operation and no reconciliation step — the leaf set confirms `o_3` is the only sink, meaning the missing unit has no destination in the trace at all.

### Why casual/structural checks miss it
- The duplicate-argument and duplicate-leaf-name heuristics are irrelevant here — this is a single, syntactically "clean" division with a plausible-looking formula annotation (`a/b`).
- Because `BigInteger` division truncation is the *expected* behavior of that type, a naive check that only compares an operation's output to "what BigInteger would produce" passes trivially. The actual defect is a **missing conservation check** across the operation, not a wrong-rounding-mode defect on the operation itself.

### Materiality and cyclicity
- This is a per-game payout computation ("Game: winner interest payout"), which is inherently a **scalable, repeating operation** — it will be executed once per game, across an arbitrarily large number of games/rounds. Each execution can leak up to `winner_count − 1` units of pool value whenever the pool is not evenly divisible by the winner count (a near-certainty in real play).
- Because the leaked remainder is never redistributed, refunded, or tracked as a liability, it structurally accumulates wherever the undistributed pool balance resides (commonly a house/operator-controlled account) — an unaccounted sink that beneficiary-side evidence in the graph cannot rule out, since the graph never surfaces where the pool's residual balance goes after payout.
- This satisfies the profile of true salami-slicing risk (scalable population + consistent directional leakage toward an unmodeled sink) rather than a bounded, self-cancelling rounding artifact — the deltas here do not cancel out; they compound in one direction (pool retains the remainder) every time the game is played with a non-exact division.

### Consequence
Any consumer of `o_3` believes the pool has been fully and fairly distributed among winners, when in fact up to `winner_count − 1` units silently remain unaccounted per execution — a systemic precision-and-conservation gap that compounds materially across repeated plays of this pipeline.