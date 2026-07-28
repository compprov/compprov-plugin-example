# Summary
- **Verdict**: CLEAN
- **Confidence score**: 78.0

## Audit Summary

The CPG was audited specifically for **Lineage Disconnection and Context Substitution** — i.e., a computed intermediate `OUTPUT` being quietly orphaned while a hardcoded or foreign-sourced `INPUT` masquerading as its equivalent is routed into the final calculation instead.

### Structural Reference Cross-Check
- **Root inputs**: i_1–i_19 (19 nodes) — all confirmed to be genuine leaf-less roots (rates, yield amounts, gas amounts, the 3% fee constant).
- **Leaf variable**: only `o_34` ("Net profit in USDC") is unconsumed — this is the expected single terminal output of the pipeline, not an orphaned intermediate.
- **Multiply-consumed variables**: `i_2`, `i_4`, `o_24`. All three were manually traced:
  - `i_2` (ETH/USDC rate, 2026-06-30) is legitimately reused by `op_2` and `op_4` to value two different ETH-denominated yield positions (AAVE and Lido+EtherFi) at the same current market rate — same role, same date, no substitution.
  - `i_4` (ETH/USDC rate, 2026-06-01) is legitimately reused by `op_6`, `op_10`, `op_11` to convert three separate gas expenditures that were all incurred on 2026-06-01 — dates match transaction metadata exactly.
  - `o_24` (Gross yield in USDC) is correctly consumed by **both** `op_13` (fee scale) and `op_14` (gas subtraction) via its actual `resultId` — not a stand-in.
- **Leaf name-collision set**: empty — no exact-match evidence of the classic hijack signature.

### Full Forward-Propagation Replay
Every arithmetic step was independently recomputed from root inputs forward:
- `o_20`…`o_23` (per-asset yields in USDC): all match declared values from their stated `convert(a,r)` operations, using rate inputs whose `date` metadata aligns with the correct valuation context (current market rate for yield valuation, tx-date-specific rate for gas conversion).
- `o_24` (Gross yield) = sum of `o_20,o_21,i_10,o_22,o_23` = 1738.305562 — matches exactly, and this is the value actually consumed downstream by both `op_13` and `op_14` (verified via `resultId`/`arguments` linkage, not merely by name).
- `o_25`…`o_30` (gas legs) and `o_31` (total gas) all replay correctly, with each `Gas (ETH)` amount converted using the ETH/USDC rate whose `date` metadata matches the transaction date recorded in that gas variable's own `meta.tx`/`meta.date` fields — a coherent, date-consistent design rather than a rate swap.
- `o_32` (Platform fee) = `o_24 * i_19` — computed value is off by 1 in the 6th decimal (52.149166 vs. an exact half-up rounding of 52.149167), consistent with a truncation/round-down `MathContext` policy rather than any substitution; it still derives from `o_24`, the real computed gross yield, not a foreign constant.
- `o_33` = `o_24 - o_31` = 1626.871962 — matches, derived from the genuine `o_24`/`o_31`.
- `o_34` (Net profit) = `o_33 - o_32` = 1574.722796 — matches, and both operands trace back through the verified chain to the original root inputs.

### Semantic Role Scan Beyond Exact-Match Sets
Beyond the mechanical leaf/name-collision sets, each output's role (currency, date, protocol) was checked against every other variable in the graph for a plausible semantically-equivalent stand-in (same currency pair, same date, same protocol tag) that could have been substituted in its place. No second variable — INPUT or OUTPUT — was found occupying the same causal slot as any consumed computed output. Every `arguments` entry in every operation points to the actual `resultId` of its logical predecessor step, not to a parallel/orphaned duplicate.

## Anomaly Localization (If Detected)
No variable or operation exhibiting the target attack signature (a computed OUTPUT left unconsumed while a same-named or same-role hardcoded INPUT is substituted downstream) was found. The only unconsumed variable, `o_34`, is the graph's legitimate final result, and it is reachable through an unbroken, correctly-linked computation chain back to all relevant root inputs.

## Details
The pipeline is dense (34 variables, 15 operations) with several same-named rate inputs (multiple "ETH/USDC rate" entries) that could superficially resemble a substitution setup — this is exactly the kind of pattern the attack vector definition warns about. However, on forward-propagation and metadata cross-referencing, each of these near-duplicate-named rate inputs serves a distinct, correctly-dated purpose (either current-date valuation of yield positions or transaction-date valuation of gas costs), and none of them displaces a computed sibling output. All `resultId`-to-`arguments` linkages were verified literally, not just numerically, and every multiply-consumed or terminal variable was checked against the strict definition of the attack. A single minor arithmetic rounding discrepancy (`o_32`, 6th decimal place) was observed, attributable to a truncating rounding mode rather than a data-substitution event, and does not alter the lineage integrity of the graph. No violation of the stated `EXPECTED_INVARIANTS` was substantiated.