# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:**
- `i_1` (Accumulated staking reward = 199)
- `i_2` (Developer fee percent = 10)
- `i_3` (Percent denominator = 100)
- `op_1` (divide: `i_1 / i_3` → `o_4`)
- `o_4` (Reward / denominator = 1)
- `op_2` (multiply: `o_4 * i_2` → `o_5`)
- `o_5` (Developer reward/fee = 10)

**Attack flow:**
The pipeline is supposed to compute a percentage fee: `fee = reward * percent / denominator`. Because all operands are `java.math.BigInteger` (integer, truncating division with no `MathContext`), the *order* of the multiply and divide operations is not mathematically interchangeable — it materially changes the result.

The graph performs the division **first** (`op_1`: `i_1 / i_3` = `199 / 100` = `1`, truncating away the `.99` fractional remainder), and only **then** multiplies by the percent (`op_2`: `1 * 10` = `10`).

The mathematically correct order — multiply first, then divide — gives `199 * 10 / 100 = 1990 / 100 = 19` (still truncated, but truncated only at the very last step, losing at most a sub-unit fraction).

Reported `o_5` = **10**. Correct order-preserving result = **19**. Δ = 9, a **47% relative error** on the fee itself, and roughly 4.5% of the entire principal (`i_1` = 199) — several orders of magnitude beyond the ≤1-unit ceiling that a legitimate single rounding-convention choice could ever produce.

## Details

**Mechanism:** This is a textbook "divide-before-multiply" precision-tampering pattern. By routing the computation through an intermediate node (`o_4`, deceptively labeled "Reward / denominator") that forcibly collapses `199/100` down to the integer `1` *before* the percent multiplier is ever applied, nearly all of the reward's magnitude information is destroyed prior to the fee calculation. A casual review of the formula annotations (`a/b` then `a*b`) looks locally correct — each operation is internally consistent with BigInteger semantics and no MathContext is present to contradict it — which is precisely why this survives naive replay/local verification. Only recomputing the *intended* formula end-to-end (multiply-then-divide) exposes the discrepancy.

**Why it clears local checks:** Each operation independently reproduces its own `resultId` value exactly (1 and then 10), so bit-for-bit / value replay of each step passes. There is no duplicate ID, no reused variable, and no explicit MathContext being violated — the tampering lives entirely in the *choice and order* of the two arguments passed into the two operations, not in any single miscomputed step.

**Consequences / beneficiary analysis:** The result is that the developer fee is under-reported (10 instead of the correct 19), meaning ~9 units per this reward event are silently retained by whatever party keeps the non-fee remainder (the staker/pool), rather than being routed to the developer fee sink. This is not a one-off, structurally bounded computation (like a fixed per-asset NAV conversion) — it is the generic fee-deduction formula for a staking pipeline, which by its nature is invoked once per reward-distribution event and scales directly with the number of stakers/transactions processed. Repeated at scale, this specific order-of-operations flaw would systematically and directionally skim value away from the developer-fee stream on every single distribution — satisfying the scalable, accumulating-toward-a-beneficiary criterion for genuine salami-slicing, not mere rounding noise.

**Materiality:** The observed Δ (9 units, 47% relative to the correct fee) far exceeds the "single rounding-mode difference ≤ 1 unit" ceiling the invariants describe as ordinary/expected variance. This is a structural, repeatable, directional value-leakage pattern consistent with deliberate precision/scale tampering rather than benign truncation.