# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 90.0

## Anomaly Localization

**Implicated nodes:** `i_1` (true wei-precise payment), `op_1` (divide), `o_5` (truncated whole-ether payment), `op_4` (multiply, rescale), `o_8` ("payment rescaled to wei"), `op_5`, `o_9` (threshold in wei), `op_6`, `o_10` ("raw excess payment above threshold"), `op_7`, `o_11` ("excess payment above threshold, bonus-eligible").

**Flow of the defect:**

1. `op_1` computes `o_5 = i_1 / i_2` = `3500000000000000000 / 1000000000000000000` using `BigInteger` (integer) division. This truncates 3.5 → **3**, silently discarding the fractional 0.5 ether (500000000000000000 wei) remainder. Using `BigInteger` here means there is no `mc`/rounding-mode argument at all — the wrapper class is `WrappedBigInteger`, and Java's `BigInteger.divide` always truncates toward zero.
2. `o_5` (the truncated value, now representing "whole ether units") is legitimately reused once for the gate comparison (`op_2`/`op_3` → `o_6`/`o_7`, deciding pass/fail against the 1-ether minimum) — this usage is defensible, since a coarse whole-ether gate check is a plausible design choice.
3. However, `op_4` reuses the **same truncated `o_5`** — not the original precise `i_1` — to reconstruct `o_8 = o_5 * i_2 = 3 * 1e18 = 3000000000000000000` wei, labelled "Payment, rescaled to wei". This re-inflation locks in the 0.5-ether loss as if it were the actual payment amount.
4. `op_6` then computes `o_10 = o_8 - o_9 = 3e18 - 1e18 = 2e18`, labelled "Raw excess payment above threshold (wei)". The mathematically correct excess, computed from the true payment `i_1`, is `3.5e18 - 1e18 = 2.5e18`.
5. `op_7` propagates this understated excess unchanged into the final leaf output `o_11 = 2e18` ("Excess payment above threshold, bonus-eligible"), rather than the correct `2.5e18`.

**Δ = |2.5e18 − 2.0e18| = 5 × 10^17 wei (0.5 ether)** — roughly 14% of the total payment, and vastly larger than any single-ULP/1-unit rounding-mode artifact.

## Details

The mechanism is a classic scale/precision tampering pattern: a value is deliberately downscaled (wei → whole-ether units) via truncating integer division for a legitimate purpose (a coarse minimum-purchase gate check), but that same truncated, scale-reduced value is then illegitimately reused as the basis for a *different*, precision-sensitive calculation (reconstructing the wei-denominated payment and computing bonus-eligible excess) instead of referencing the original full-precision input `i_1`. This is precisely why `o_5` shows up in the structural "consumed by more than one operation" list — it is doing double duty as both a coarse gate signal and (incorrectly) a precise payment proxy.

Local replay of each individual operation passes: `op_1`'s division is arithmetically "correct" BigInteger truncating division, `op_4`'s multiplication is arithmetically correct given its inputs, and so on — no single operation is internally inconsistent. This is exactly what makes the attack subtle: it passes step-by-step recomputation while still producing a materially wrong aggregate result, because the error is injected structurally (wrong variable reused) rather than via a bad multiplication/addition.

**Consequence:** For any payment that is not an exact integer multiple of 1 ether (i.e., the overwhelming majority of real-world payments), the fractional ether remainder is silently dropped from the "rescaled to wei" payment and thus from the excess/bonus-eligible calculation, while the buyer's full payment (`i_1` = 3.5 ether) is presumably still collected in full by the underlying contract. The buyer is short-changed on bonus-eligible excess by the truncated fractional amount. This defect recurs on every transaction whose payment is not a whole-ether multiple, so the population of affected operations scales directly with transaction volume/number of users — satisfying the "scalable population" criterion for a genuine, exploitable precision-leakage pattern rather than a bounded, cosmetic rounding difference. The leaked value does not reappear or cancel elsewhere in the graph (the leaf `o_11` is the final, understated figure), which is evidence for, not against, a beneficiary-driven skew (the sale operator retains the un-credited 0.5 ether of bonus-eligible excess).
