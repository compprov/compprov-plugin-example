# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Risk score**: 58.0

## Anomaly Localization

**Implicated nodes:** `i_1` (Gross amount), `i_2` (fee rate bps), `i_3` (bps denominator), `op_1` (multiply → `o_4`), `op_2` (divide → `o_5`, the Taker fee), `op_3` (subtract → `o_6`, Net amount).

Flow: `o_4 = i_1 * i_2 = 1000003 * 37 = 37,000,111` (exact, verified). `o_5 = o_4 / i_3` using **`java.math.BigInteger` division**, which has no rounding-mode/`MathContext` parameter and unconditionally truncates toward zero: `37,000,111 / 10,000 = 3700.0111 → 3700`. `o_6 = i_1 - o_5 = 1000003 - 3700 = 996303`.

Conservation holds exactly for this single instance: `o_5 + o_6 = 3700 + 996303 = 1,000,003 = i_1`. In *this* transaction the truncated result (3700) also happens to coincide with the round-to-nearest result (3700.0111 rounds down under HALF_UP/HALF_EVEN too), so the per-operation delta is 0 and no naive drift check would fire.

## Details

The structural concern is not this one snapshot's arithmetic — it is verified correct — but the **mechanism chosen to compute a fractional-rate fee**. Basis-point fee math (`a*rate/denom`) is inherently a decimal/rational operation, yet the pipeline executes it entirely in `BigInteger`, a type that has no `MathContext`, no rounding-mode parameter, and *always* truncates toward zero on division. Per the stated invariant, rounding must default to HALF_EVEN/HALF_UP "unless explicitly bounded" by a declared `mc` — here there is no `mc` argument anywhere in `op_2`, and the wrapper class itself (`WrappedBigInteger`) structurally forecloses any rounding mode other than floor/truncation. That is a deliberate (or at minimum consequential) type choice that guarantees the fee is *never* rounded up and *never* rounded to nearest — only ever down — regardless of how large the discarded remainder is on any given swap (it could be up to `9999/10000` of a unit, not just the `111/10000` seen here).

Why this evades casual review: (1) asset conservation is perfect at every step, since the discarded fraction is absorbed directly into the net-amount leg rather than vanishing into an unaccounted sink; (2) in this specific instance the truncation happens to match round-to-nearest, so a single-transaction delta check reports zero; (3) there is no explicit `MathContext` to compare against, so a naive "does it match its declared mc" check is vacuously satisfied by having no mc at all.

Consequence: this is exactly the shape of a scalable Salami Slicing setup rather than an isolated rounding artifact. The bias (fee always floored, net amount always the beneficiary of the residual) is baked into the operation's *type choice*, not into transaction-specific data — meaning it fires identically on every AMM swap. The descriptor name ("AMM swap **#48213**") indicates this is one execution in a very large, ongoing, naturally scalable population of taker-fee settlements, satisfying the "population can grow with transaction volume" criterion for genuine Salami Slicing. Over tens of thousands of swaps, systematically under-collecting the fee (transferring the truncated remainder from the fee/protocol side to the taker/net side every single time, never the reverse) accumulates into a material, directional value transfer away from the fee-recipient sink — even though no single swap's delta exceeds the 1-unit ceiling that would flag it in isolation.

This is a genuine, structural precision-tampering risk (integer-truncating division used for a fractional-rate calculation, with no rounding-mode control, in a high-frequency financial settlement path) rather than confirmed proof of malicious intent in this one instance — hence an intermediate-high rather than maximal score.