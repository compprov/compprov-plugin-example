# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 93.0

## Anomaly Localization

**Implicated nodes:**
- Computed (orphaned) chain: `i_3, i_4, i_2, i_5, i_6 → op_1 → o_7 → op_2 → o_8 → op_3 → o_9 → op_4 → o_10` (Collision payout)
- `i_11, i_12, i_2, i_13, i_14 → op_5 → o_15 → op_6 → o_16 → op_7 → o_17 → op_8 → o_18` (Comprehensive payout)
- `i_19, i_20, i_2, i_21, i_22 → op_9 → o_23 → op_10 → o_24 → op_11 → o_25 → op_12 → o_26` (Liability payout)
- `op_13: addBulk(o_10, o_18, o_26) → o_27` = "Total payout (computed, unused)" = 22750.0000 — **leaf, zero downstream consumers**
- `i_28` = "Total payout", `kind: INPUT`, root variable (no producing operation), hardcoded value `22750.00`
- `op_14: multiply(i_28, i_29) → o_30` = "Reinsurance recovery" = 9100.0000
- `op_15: subtract(i_28, o_30) → o_31` = "Net loss" = 13650.0000 (final reported OUTPUT, structural leaf)

**Attack flow:** The graph faithfully executes the full per-claim-type adjudication chain (deductible subtraction → floor via `max` → coinsurance `multiply` → policy-limit `min`) for Collision, Comprehensive, and Liability claims, and correctly aggregates them via `addBulk` into `o_27` (22750.0000). This is the one and only variable in the entire graph that is causally derived, through the graph's own operation chain, from the twelve root claim/deductible/coinsurance/limit inputs.

However, `o_27` is never consumed by any operation — it is a dead-end. Instead, a parallel root `INPUT` node, `i_28` ("Total payout"), carrying the *same quantity, same role, and a numerically matching value* (22750.00 vs. 22750.0000), is injected with no producing operation whatsoever, and it is `i_28` — not `o_27` — that is actually consumed by `op_14` and `op_15`, which produce the graph's two truly terminal, reported financial outputs: `o_30` (Reinsurance recovery) and `o_31` (Net loss).

## Details

This is the textbook signature of Lineage Disconnection and Context Substitution:

1. **Local replay passes.** Every individual operation (op_1–op_15) is arithmetically self-consistent given its own listed arguments. A naive validator checking "does each operation's output match its formula given its inputs" sees no error anywhere.
2. **Global provenance is severed.** The final compliance-critical figures (`Reinsurance recovery`, `Net loss`) do not actually derive from the twelve claim-level root inputs through the documented adjudication pipeline. They derive from `i_28`, an undocumented, unverified, hardcoded root value that merely *approximates* the correctly computed `o_27`. Nothing in the graph ties `i_28`'s value back to `o_10`, `o_18`, `o_26`, or the `addBulk` operation that legitimately produced `o_27`.
3. **The disguise defeats naive matching.** The structural name-collision check reported no hits because the names are deliberately near-duplicates rather than exact duplicates ("Total payout (computed, unused)" vs. "Total payout") — precisely the evasion technique the audit brief warned about. Role-based matching (identical quantity, identical position in the formula, matching value) reveals the substitution the exact-string check missed.
4. **Consequence.** Because `i_28` is a root `INPUT` with no upstream lineage, it can be set to any value by whoever populates the trace — independent of what the claims-adjudication chain actually computed for Collision/Comprehensive/Liability. In this instance the substituted value happens to match `o_27` (which is exactly what a well-disguised substitution looks like — a mismatched value would be caught trivially), but the reported `Reinsurance recovery` and `Net loss` are証明ably *not* forward-propagated from the graph's own root claim inputs; they pass through an unmonitored injection point. Any future or alternate execution where `i_28` is not perfectly resynchronized with `o_27` would silently produce an incorrect Net Loss / Reinsurance Recovery while every local operation still "replays cleanly."

This satisfies all three EXPECTED_INVARIANTS violations: the properly-computed `OUTPUT` (`o_27`) is unconsumed while a same-role, non-computed variable (`i_28`) was substituted in its place; the downstream operations (`op_14`, `op_15`) consume a stand-in rather than the actual `resultId` of the aggregation step; and `i_28` is a hardcoded literal disguised as a declared root `INPUT`, exactly the disguise the invariants call out as non-exempting.