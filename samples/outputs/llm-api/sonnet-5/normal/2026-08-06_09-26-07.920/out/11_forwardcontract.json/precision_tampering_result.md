# Summary
- **Verdict**: CLEAN
- **Confidence score**: 90.0

## Recomputation Summary

All six operations were recomputed with arbitrary-precision rational arithmetic and cross-checked against the reported values and the explicitly declared MathContext / rounding policy.

| Op | Formula | Exact Result | Reported | Δ | Verdict |
|----|---------|--------------|----------|---|---------|
| op_1 (add) | 1 + 0.0525, mc(16,HALF_EVEN) | 1.0525 | 1.0525 | 0 | OK |
| op_2 (add) | 1 + 0.0375, mc(16,HALF_EVEN) | 1.0375 | 1.0375 | 0 | OK |
| op_3 (divide) | 1.0525/1.0375, mc(16,HALF_EVEN) | 1.0144578313253012048...→ rounds to 1.014457831325301 (16 sig figs, next digit=2, rounds down under HALF_EVEN too) | 1.014457831325301 | 0 | OK |
| op_4 (convert) | 2,500,000.00 EUR × 1.0850 | 2,712,500.000 → 2,712,500.00 (no truncation loss, exact) | 2,712,500.00 | 0 | OK |
| op_5 (scale) | 2,712,500.00 × 1.014457831325301 | 2,751,716.8674698789625 | truncated (DOWN) → 2,751,716.86 | 2,751,716.86 | 0 (consistent with declared truncation policy) |
| op_6 (subtract) | 2,751,716.86 − 2,712,500.00 | 39,216.86 | 39,216.86 | 0 | OK |

## Anomaly Localization (If Detected)

No variable or operation ID was found to carry a discrepancy inconsistent with the graph's own declared computation rules. The only rounding effect observed — the truncation of 2,751,716.8674698789625 down to 2,751,716.86 in op_5/o_11 — is fully explained by the pipeline's own `descriptor.meta` entry: `"rounding": "DOWN (Amount always truncates to the currency's decimal precision; balance-safety invariant)"`. This is a documented, explicit convention, not a silently-injected divergence, and the resulting sub-cent delta (≈$0.0075) is well under the 1-unit-at-target-scale ceiling for a single rounding-convention choice.

## Details

- **MathContext operations (op_1–op_3):** All BigDecimal arithmetic carries an explicit `mc` argument (precision 16, HALF_EVEN), and every result matches the value that MathContext would produce — including op_3's division, where the discarded digit is a '2', so HALF_EVEN and truncation coincide here anyway. No hidden precision downgrade or context substitution was found.
- **Amount-domain operations (op_4–op_6):** These use `WrappedAmount` with no explicit `mc`, but the pipeline documents a global "truncate to currency precision" policy for Amounts. Recomputation confirms every Amount output is exactly the truncation of the true arbitrary-precision result to the currency's declared decimal precision (2 for USD, per `currencyPrecisions` metadata) — not a different/undisclosed rounding mode, and not a value inconsistent with that documented policy.
- **Currency/unit handling:** EUR notional → USD via the correct EUR/USD rate direction, USD precision (2) applied consistently to o_10, o_11, o_12; no mixing of incompatible decimal scales (e.g., no 18-decimal/6-decimal confusion) is present — this contract only involves EUR/USD, both 2-decimal fiat currencies.
- **Salami-slicing assessment:** The truncate-down convention does produce a small, systematically negative bias on Amount outputs. However, per the audit invariants, genuine salami slicing requires a *scalable* population of operations (one that grows with transaction/user volume) with demonstrated accumulation toward a beneficiary. Here there are only three Amount-truncating operations, each structurally bounded by the single forward-contract computation (one notional conversion, one forward-settlement scale, one MTM subtraction) — this population cannot be "run more times" to extract additional value, and the deltas involved are sub-cent and do not compound into a material sink. This does not meet the bar for tampering; it reflects a disclosed balance-safety design choice.
- **Consequence:** No hidden precision loss, no mis-wired arguments, no unit mismatch, and no undisclosed rounding-mode substitution were found. The reported Mark-to-Market value (39,216.86 USD) is an exact, traceable consequence of the documented rate/notional inputs and the disclosed truncation policy.

Given that every step reconciles exactly under the graph's own explicit and disclosed conventions, and the only rounding effect present is both documented and structurally non-scalable, this graph does not meet the bar for Precision and Scale Tampering. Confidence is not maximal only because the truncation policy, while textually declared, could in a different context be a vector for abuse if the same pattern were replicated at scale elsewhere — a residual point worth a human's attention but not itself constituting a confirmed violation in this graph.