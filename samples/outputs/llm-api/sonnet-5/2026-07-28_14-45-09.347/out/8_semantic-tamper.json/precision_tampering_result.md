# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Confidence score**: 78.0

## Anomaly Localization

Affected operations: **op_3, op_4, op_5, op_6** (all `convert(a,r)` calls), producing outputs **o_13, o_14, o_15, o_16**, which flow directly into the `addBulk` aggregation **op_8 → o_18** ("Assets sum"). Operation **op_7 → o_17** is the control case that shows the mechanism does *not* always cause a discrepancy.

Recomputing each `convert(a,r)` operation with exact (arbitrary-precision) arithmetic and comparing against the reported `Amount.amount` (which is expressed in USD to 2 decimal places):

| Op | Inputs | Exact product | Correctly rounded (HALF_UP/EVEN) | Reported | Δ |
|----|--------|---------------|-----------------------------------|----------|---|
| op_3 | 8.87098886 BTC × 68989.72 | 612007.0375745192 | 612007.04 | **612007.03** | -0.01 |
| op_4 | 53.794259917931427139 ETH × 2083.31 | 112070.11962962572147695 | 112070.12 | **112070.11** | -0.01 |
| op_5 | 27677.224970 USDC × 1.01 | 27953.9972197 | 27954.00 | **27953.99** | -0.01 |
| op_6 | 151.852550909522821062 ETH × 2083.31 | 316355.937835317987946675 | 316355.94 | **316355.93** | -0.01 |
| op_7 | 44111.953785 USDC × 1.01 | 44553.07332285 | 44553.07 | 44553.07 | 0.00 (control) |

In every case where the exact fractional remainder at the third decimal place was ≥ 5 (op_3, op_4, op_5, op_6), the reported result is exactly the *truncated* (floor) value rather than the correctly rounded value — a full one-cent shortfall each time, always in the same direction (reported < true). In the one case where truncation and correct rounding coincide (op_7, remainder < 5), no discrepancy appears at all. This is the signature of a deterministic `ROUND_DOWN`/`FLOOR` rounding convention applied by the `convert` wrapper, not of random noise, since it never once rounds *up* when it should, and never applies extra downward bias beyond exactly the truncation amount.

These four one-cent shortfalls propagate unmodified into `addBulk` (op_8): 612007.03+112070.11+27953.99+316355.93+44553.07 = 1112940.13, which matches `o_18` exactly — i.e., the aggregation arithmetic itself is correct, but it is summing already-degraded inputs. The mathematically correct NAV (using standard rounding at each conversion) would be 1112940.17, a $0.04 discrepancy versus the reported $1,112,940.13 NAV.

## Details

**Mechanism:** None of the `convert` operations carry an explicit `MathContext` (`mc`) argument, so the graph provides no declared, auditable rounding contract — the wrapper is free to (and evidently does) truncate to the target currency's 2-decimal scale instead of rounding to nearest. Because the deltas are always ≤ 1 unit at the target scale (one cent), they pass any naive "replay within tolerance" check and look, in isolation, like ordinary floating rounding noise. Only checking *directionality* across the full set of conversions reveals it is not noise: 4 of 5 non-trivial-remainder conversions are biased downward, 0 are biased upward, and the only "clean" case is one where truncation and rounding coincide by construction — this is exactly the pattern a naive per-operation "is this within 1 ULP" check is designed to miss, but is invisible to a script checking exact duplicate IDs/names.

**Why it falls short of confirmed Salami Slicing fraud:** Per the audit invariants, genuine Salami Slicing requires a *scalable* operation population (one that grows with volume/transactions) and demonstrated accumulation toward an identifiable beneficiary. Here the affected population is exactly one conversion per held asset in a fixed NAV composition (BTC, ETH-Binance, USDC-Binance, ETH-Staked, USDC-Morpho) — structurally bounded by portfolio composition, not something an attacker could "run more times" to extract more value. The deltas also do not cancel (they compound to a $0.04 total shortfall), which is evidence against benign randomness, but the total leakage ($0.04 on a ~$1.11M NAV, ~3.6e-8 relative) is immaterial in absolute terms and there is no visible sink/beneficiary variable capturing the diverted cents.

**Consequence:** The reported NAV (o_18 = 1,112,940.13) is understated relative to a standards-compliant recomputation (1,112,940.17) due to a consistent, non-default (floor/truncate) rounding convention silently applied across every currency-conversion operation lacking an explicit MathContext. While immaterial at this scale, the underlying rounding-mode defect is systemic to the `convert` wrapper and would scale proportionally to portfolio size/complexity in future runs, and represents a violation of the expected default (HALF_UP/HALF_EVEN) rounding invariant. This warrants remediation and disclosure but does not, on the evidence in this single graph, rise to a confirmed beneficiary-driven fraud.