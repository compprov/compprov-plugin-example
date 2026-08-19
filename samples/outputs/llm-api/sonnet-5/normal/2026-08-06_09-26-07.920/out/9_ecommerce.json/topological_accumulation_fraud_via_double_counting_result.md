# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Anomaly Localization (If Detected)
No genuine double-counting was found. The two variables flagged by the structural reference data (`i_2` and `o_23`) were traced end-to-end and both resolve to legitimate, single-contribution patterns:

- **`i_2` (constant "1.0")** feeds `op_6` (`1 - i_16` → `o_17`, SAVE10 complement) and `op_8` (`1 - i_19` → `o_20`, LOYALTY5 complement). It is a bare numeric constant, not a financial entity (revenue, cost, or fee) — it carries no monetary value of its own. Each of its two consumers produces an independent multiplier (`o_17`, `o_20`) that is itself used exactly once further downstream (`o_17`→`op_7`, `o_20`→`op_9`). There is no rollup node where the value `1` is summed twice; the reuse is purely as a shared arithmetic constant for two unrelated sequential discount computations.
- **`o_23` ("Taxable amount")** is consumed by `op_11` (`o_23 * i_24` → `o_25`, Sales tax) and by `op_12` (`o_23 + o_25` → `o_26`, Order total). This is the canonical `Total = Principal + Tax(Principal)` pattern. The principal (`o_23`) contributes exactly once as the additive base of the total; the tax figure added alongside it is a *derived, distinct* quantity (8% of the principal), not a second copy of the principal itself. Mathematically this is equivalent to `Total = P × (1 + r)`, a single use of `P` — not a duplication.

## Details
Full forward trace from roots to the terminal output `o_26`:

1. Four independent SKU lines (`i_3×i_4`→`o_5`, `i_6×i_7`→`o_8`, `i_9×i_10`→`o_11`, `i_12×i_13`→`o_14`) are each computed once and combined exactly once via `addBulk` (`op_5`) into `o_15` (Subtotal = 227.92). No SKU value appears in more than one multiply or in the bulk-add more than once.
2. Subtotal is discounted sequentially: SAVE10 (`op_7`: `o_15 × o_17` → `o_18` = 205.1280), then LOYALTY5 (`op_9`: `o_18 × o_20` → `o_21` = 194.871600). Each discount rate (`i_16`, `i_19`) is used exactly once; compounding two distinct, sequential discounts on the same base is standard commercial practice, not duplication of a single discount.
3. Shipping (`i_22`) is added once (`op_10`) to produce `o_23` = 207.371600. Shipping is never referenced again elsewhere in the graph.
4. Tax is computed on `o_23` (`op_11`) giving `o_25` = 16.58972800, and the final total (`op_12`) adds `o_23 + o_25` = 223.961328, matching the reported `o_26` = 223.96132800.

All intermediate values recompute exactly under the stated `MathContext` (precision 16, HALF_EVEN) from their declared operands — no silent rounding drift, no rewrapped/aliased root entities with matching numeric fingerprints under different IDs (unit prices/quantities 29.99/3, 14.50/2, 59.00/1, 9.99/5 are all distinct), and no cost or fee that is netted into an intermediate subtotal and then subtracted again downstream. The only two multi-consumed variables identified by structural scanning correspond to (a) a non-monetary shared constant driving two independent, non-overlapping discount branches, and (b) the expected 