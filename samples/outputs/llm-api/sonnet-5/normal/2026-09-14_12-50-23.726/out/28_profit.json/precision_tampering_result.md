# Summary
- **Verdict**: CLEAN
- **Risk score**: 8.0

## Precision & Scale Tampering Audit

### Methodology
Every `convert`, `addBulk`, `scale`, and `subtract` operation in the graph was re-derived using exact rational arithmetic (numerator/denominator, no floating point) and compared against the reported `value` at each node. The graph's own descriptor explicitly declares a global rounding convention: **DOWN / truncate to the currency's native decimal precision**, described as a "balance-safety invariant." This was used as the ground truth for evaluating whether any step deviates from policy.

### Recomputation Results
- `op_1` (peg convert WBTC→BTC, rate=1): 0.00369452 → 0.00369452 BTC. Exact, 8-decimal BTC precision preserved.
- `op_2` (o_21→USDC @109800): 0.00369452 × 109800 = 405.658296 exactly. Matches `o_22`.
- `op_3` (i_10→USDC @4650): 0.02735342 × 4650 = 127.193403 exactly. Matches `o_23`.
- `op_4` (i_12→USDC @0.9998): exact product = 299.5291231918 → truncated DOWN to 6 decimals = 299.529123. Matches `o_24`. Truncated remainder (0.0000001918) is far below one unit (0.000001) at USDC's declared precision.
- `op_5` (i_13→USDC @4650): exact product = 786.3411795 → truncated DOWN = 786.341179. Matches `o_25`. Truncated remainder is exactly 0.0000005, still under the one-unit ceiling for a single truncation.
- `op_6` (addBulk of already-truncated o_22, o_23, i_11, o_24, o_25) = 1738.305562, exactly reproducing the sum of the (already-rounded) inputs — no additional rounding error introduced at the addition step itself.
- `op_7`–`op_12` (gas conversions using date-matched historical rates i_5/i_6/i_7/i_8, consistent with each gas transaction's stated date): all exact, no truncation loss (22.4, 17.3844, 12.93, 17.5032, 17.92, 23.296).
- `op_13` (addBulk of gas legs) = 111.4336 → matches `o_33` exactly.
- `op_14` (3% platform fee, scale on o_26): exact = 52.14916686 → truncated DOWN to 52.149166. Matches `o_34`, consistent with declared truncation policy (a HALF_UP convention would have yielded 52.149167 — a 1-unit difference, exactly the ceiling permitted for a rounding-mode discrepancy, not evidence of tampering).
- `op_15`/`op_16` (gas deduction, fee deduction): 1738.305562 − 111.433600 = 1626.871962; 1626.871962 − 52.149166 = 1574.722796. Both match reported outputs exactly.

### Cumulative Drift Analysis
Summing the *exact* (untruncated) values for `o_24` and `o_25` instead of their truncated reported values yields a gross yield of 1738.3055626918 versus the reported 1738.305562 — a cumulative drift of only ~0.0000007 USDC across the entire aggregation. This is the classic "truncate-before-aggregate" pattern that can indicate salami-slicing, but here:
1. The magnitude is far under a single unit (0.000001) at the target currency scale — within the invariant's explicit tolerance for rounding-convention noise.
2. The truncation direction (DOWN) is explicitly documented in the graph's top-level descriptor as an intentional balance-safety convention, not a silent/undisclosed default.
3. The operation population (5 yield legs, 6 gas legs) is small and structurally bounded to this single portfolio snapshot on a single valuation date — it is not a scalable, per-transaction/per-user operation whose skims could compound with volume, which the invariants require for a genuine Salami Slicing finding.
4. No evidence of an identifiable beneficiary/sink absorbing the truncated dust; the residual is simply unrealized (money not created, just floored away), consistent with a conservative accounting convention.

### Business-logic note (non-precision)
The platform fee (`op_14`) is levied on gross yield (`o_26`) rather than on the post-gas or post-fee net amount, and gas costs are valued at historical (deposit-date) rates via `i_5`–`i_8`, while yields are valued at current (valuation-date) rates via `i_1`–`i_3`. Both choices are internally consistent, documented via variable names/metadata ("realized at deposit moment", "Gross yield"), and reflect a coherent accounting design rather than a precision exploit.

### Conclusion
No MathContext misuse, no downcasting to float/double, no premature/undisclosed truncation exceeding the documented DOWN policy, and no evidence of scalable, beneficiary-directed skimming was found. The only anomalies detected are sub-unit truncation remainders fully explained by the graph's own stated rounding invariant and structurally incapable of compounding (single, non-recurring valuation snapshot). This falls within expected/benign behavior rather than tampering.