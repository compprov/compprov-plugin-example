# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 96.0

## Anomaly Localization

**Implicated nodes:** `i_1` (legitimate MathContext, precision=16, HALF_EVEN), `i_2` (MathContext explicitly named "Computation precision (tampered)", precision=2, DOWN), `o_19` (Subtotal after SAVE10), `o_21` (Discount multiplier LOYALTY5), `op_9` (multiply), `o_22` (Subtotal after LOYALTY5), `o_24` (Taxable amount), `o_26` (Sales tax), `o_27` (Order total, the sole graph leaf/reported final output).

**Flow of the attack:**

1. Every other arithmetic operation in the graph (`op_1`–`op_8`, `op_10`, `op_11`, `op_12`) consistently uses `mc = i_1` — the declared, correctly-specified `MathContext` (precision 16, HALF_EVEN), matching the descriptor "Computation precision (DECIMAL64)".
2. `op_9` — the multiplication that stacks the two discounts (`o_19 * o_21` → `o_22`, "Subtotal after LOYALTY5") — silently swaps in `i_2` as its `mc` argument instead of `i_1`. `i_2` is a root `INPUT` node whose own descriptor name is literally "Computation precision (tampered)", precision=2, rounding=DOWN.
3. Mathematically, `o_19 (205.1280) * o_21 (0.95) = 194.8716`. Under the graph's own governing precision context `i_1` (16 significant digits, HALF_EVEN), the correct result would be `194.8716` (no rounding needed at that precision). Instead, because `op_9` was executed under `i_2`, the result is truncated to 2 significant digits with DOWN rounding, yielding `1.9E+2` = `190` — a silent, undisclosed loss of `4.8716`.
4. This corrupted value (`190`) then legitimately and deterministically propagates forward: `o_24 = 190 + 12.50 = 202.50`, `o_26 = 202.50 * 0.08 = 16.2000`, `o_27 = 202.50 + 16.2000 = 218.7000`. Every one of these downstream steps replays perfectly given its stated inputs — local replay passes cleanly.
5. However, forward-propagating the *correct*, consistently-governed chain (using `i_1` throughout, as every sibling operation does) gives `o_22 = 194.8716`, `o_24 = 207.3716`, `o_26 ≈ 16.5897`, `o_27 ≈ 223.9613`. The reported final "Order total" of `218.7000` therefore does **not** derive from the graph's own uniformly-governed computation chain — it derives from a chain in which the precision/rounding context was covertly substituted at exactly one critical juncture, right before tax and grand-total calculation.

## Details

This is a textbook instance of the "Context Substitution" half of the attack vector: rather than swapping out a data variable for a hardcoded twin, the adversary swapped the *execution context* (`MathContext`) feeding a single downstream multiply. Because `MathContext` objects are legitimately reused across many operations (and are explicitly excluded from the "multi-consumer" structural heuristic), a second, near-identical `MathContext` INPUT node with drastically different precision/rounding semantics is an ideal disguise — it looks like ordinary configuration, passes local replay trivially (the multiply is internally self-consistent given its stated `mc`), and evades naive duplicate-detection because the substituted node is a distinct, declared root `INPUT`, not a copy of an existing variable.

The node's own descriptor name — "Computation precision (tampered)" — is itself effectively an admission that this is not a legitimate second precision profile but an injected override. It is consumed by exactly one operation (`op_9`), at precisely the point where discount-stacking rounding has the largest compounding effect on the taxable base, sales tax, and final order total. All fourteen other arithmetic steps correctly and uniformly reference `i_1`; only this one, high-leverage step diverges.

**Consequence:** The reported "Order total" (`218.7000`) understates the true, internally-consistent result (`≈223.9613`) by roughly `$5.26`, entirely due to an undisclosed precision/rounding downgrade (16-digit HALF_EVEN → 2-digit DOWN) injected at a single multiplication. This satisfies the EXPECTED_INVARIANTS violation directly: a hardcoded/foreign context was substituted at a critical calculation juncture in place of the context that governs the rest of the chain, breaking true origin propagation for the final compliance output while still allowing clean local mathematical replay.