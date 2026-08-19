# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 95.0

## Anomaly Localization

**Implicated nodes:** `i_1`, `i_2`, `o_8`, `o_9`, `o_10`, `o_11`, `o_12`, `o_13`, `i_3`, `i_4`, `o_14`, `o_15`, `op_1`–`op_8`.

The attack flow:

1. `op_1` (divide, L/g) correctly consumes `i_3`, `i_4` with the declared computation context `i_1` (`MathContext(precision=16, HALF_EVEN)`), producing `o_8 = 0.2040816326530612`.
2. `op_2` (sqrt) is supposed to continue that chain at the same fidelity, but its `mc` argument is silently switched to `i_2` — a variable explicitly self-labeled in its own metadata as **"Computation precision (tampered)"** (`precision=3, roundingMode=DOWN`). Every other arithmetic step in the graph (`op_1`, `op_3`, `op_4`, `op_5`, `op_6`, `op_7`, `op_8`) uses `i_1`; only `op_2` uses `i_2`.
3. The true mathematically correct value of `sqrt(0.2040816326530612)` at 16-digit precision is ≈`0.4517543887…`. Because `op_2` was executed under the truncated 3-digit `DOWN` context instead, it emits a heavily degraded `o_9 = "0.451"`.
4. This corrupted `o_9` is then the sole precision-losing input consumed by `op_4` (multiply `o_10 * o_9` → `o_11`, the reported Period `T`). Verifying: `6.28318530717958 * 0.451 = 2.833716573538…`, which exactly matches the reported `o_11 = 2.833716573537991`. This confirms `o_11` was built from the truncated `0.451`, not from a correctly-propagated 16-digit sqrt result.
5. `o_11` (Period) then propagates the corruption into `o_12` (Frequency, `op_5`) and `o_13` (Angular frequency ω = 2π/T, `op_6`) — the graph's headline derived outputs.
6. Meanwhile, an entirely independent, correctly-context-consistent branch exists: `i_4/i_3 → o_14 (g/L) → o_15 (sqrt(g/L), "Angular frequency cross-check")`, computed end-to-end using only `i_1` (16-digit, HALF_EVEN). This yields the mathematically sound angular frequency `o_15 = 2.213594362117866`.
7. `o_13` (the reported/consumed angular frequency, derived through the tampered-context branch) = `2.217294900221729` — a ~0.17% divergence from the correct cross-check value `o_15 = 2.213594362117866`. `o_15` is left as an unconsumed leaf, never reconciled against or substituted back into the reported chain, despite existing specifically as a "cross-check" for exactly this quantity.

## Details

This is a textbook **Context Substitution** rather than a straightforward whole-variable swap: instead of replacing a `resultId` argument with a foreign value, the adversary replaced the *precision context* (`mc`) feeding one critical operation (`op_2`) with a second, explicitly-named-as-tampered `MathContext` object (`i_2`). Because `MathContext` is a legitimate, reusable, declarable root `INPUT` type in this schema, swapping it in as an argument passes every local/casual check: the operation is still deterministic, still replays cleanly, still produces a value consistent with its own (corrupted) inputs, and the resulting `o_9` is internally self-consistent with `precision=3, DOWN` semantics. A naive validator that only checks "does replay reproduce the stored value" will pass this outright.

What breaks is end-to-end origin propagation: the graph's own root inputs (`L`, `g`, 16-digit context `i_1`) do **not** forward-propagate to the reported Period/Frequency/Angular-frequency outputs at the fidelity the rest of the pipeline uses — a foreign, lower-fidelity context is quietly substituted at exactly the one step (`op_2`) that feeds the final headline results. The presence of a parallel, fully-consistent cross-check branch (`o_14`→`o_15`) that computes the same physical quantity correctly, and is left orphaned/unconsumed while the corrupted branch's `o_13` is what actually reaches the leaf outputs, is precisely the structural signature the audit brief warns about: the correctly-computed twin is bypassed, not merely uninvolved. The explicit self-labeling of `i_2` as "(tampered)" removes any reasonable doubt that this is an intentional, injected precision downgrade at a critical calculation juncture (the sqrt directly feeding the Period, Frequency, and Angular Frequency outputs), not benign precision variance.

**Impact:** The reported Period (`o_11`), Frequency (`o_12`), and Angular Frequency (`o_13`) — the pipeline's primary physical results — are all derived from an artificially truncated intermediate value rather than from the graph's own declared 16-digit computation standard, while a correctly-computed independent verification value (`o_15`) that would have exposed the discrepancy is left unused and unreconciled.