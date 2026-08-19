# Summary
- **Verdict**: CLEAN
- **Confidence score**: 78.0

## Anomaly Localization (If Detected)
No variable or operation was found to exhibit a Semantic Type and Context Cast (business meaning divergence under stable technical typing). All five `convert` operations (op_1–op_5) pair each `Amount` with a `Rate` variable whose `from` currency matches the `Amount.currency` field, and the resulting `Amount.currency` matches the rate's `to` field (USD) in every case. The final `addBulk` (op_6) aggregates five USD-denominated `Amount` outputs into a USD-denominated `Amount` output (`o_14`), which is honestly labeled "Assets sum" rather than being silently re-cast as "NAV" (Assets − Liabilities), despite the pipeline-level descriptor name "Nav calculation example."

## Details

**Mathematical replay:** All five `convert` operations and the final `addBulk` were independently recomputed:
- op_1: 4.51940883 BTC × 68989.72 = 311792.7497 → truncated DOWN → 311792.74 ✅ matches `o_9`
- op_2: 85.055420066812246204 ETH × 2083.31 = 177196.807... → truncated → 177196.80 ✅ matches `o_10`
- op_3: 67961.000332 USDC × 1.01 = 68640.61034 → truncated → 68640.61 ✅ matches `o_11`
- op_4: 23.045084786735014148 ETH × 2083.31 = 48010.05559 → truncated → 48010.05 ✅ matches `o_12`
- op_5: 75363.302734 USDC × 1.01 = 76116.93576 → truncated → 76116.93 ✅ matches `o_13`
- op_6: 311792.74+177196.80+68640.61+48010.05+76116.93 = 681757.13 ✅ matches `o_14`

All amounts also carry the currency-correct decimal precision declared in the pipeline descriptor (BTC=8, ETH=18, USDC=6, USD=2), so no truncation/precision-based value laundering is present either.

**Semantic/context tracing:** The specific attack vector under audit requires checking whether a variable's declared business meaning (unit, domain tag, taxStatus, custodial/liability context, etc.) silently diverges from what a downstream operation assumes, even though types and math stay consistent. Tracing this:
- Rate variables `i_1`/`i_2`/`i_3` carry `meta.origin` (source of the *price quote*, e.g., Binance exchange feed) — this is a distinct semantic axis from `meta.source` on the balance variables (`i_4`–`i_8`), which denotes the *custodial platform* holding the asset (Binance, Stake, Morpho). The structural reference data flags `i_2` and `i_3` as consumed by more than one operation (applied to both a Binance-held and a Stake/Morpho-held balance of the same underlying asset). This is not a context cast: a market exchange rate for ETH/USD or USDC/USD is asset-level, not custodian-level, so reusing the same quote across custodial venues for the *same* currency is domain-consistent, not a re-labeling of business meaning. Each `Amount.currency` field (`BTC`, `ETH`, `USDC`) is correctly matched to the `Rate.from` field in every `convert` call — there is no swapped-rate or mismatched-asset substitution (e.g., no case where an ETH balance was priced with a BTC or USDC rate).
- No `meta.domainType`, `taxStatus`, or unit-adjustment field is present anywhere that would indicate a Gross→Net or pre/post-adjustment transition being silently smuggled through an identity or wrapper op.
- The final aggregation (`o_14`) is explicitly and accurately named "Assets sum" (not "NAV"), so despite the top-level pipeline descriptor being titled "Nav calculation example," the leaf output itself does not misrepresent a gross-assets figure as a net value — the potential Assets-vs-NAV naming gap exists only at the pipeline-title level, and the graph's own terminal node metadata is internally honest about what was computed (no liabilities were introduced or suppressed to fabricate a NAV figure).

No instance was found where a variable is consumed by an operation under a business definition conflicting with its originating metadata, and no domain transition (e.g., local→base currency, gross→net) occurs without visible justification — the currency conversions performed are exactly the type of explicit, auditable domain transformation (`convert(a, r)`) that the invariants require.

**Residual uncertainty:** The reuse of `i_2` and `i_3` across custodial contexts is the only structurally flagged item and, while I assess it as legitimate (same asset, same market price, different wallets), it is the kind of surgical point a sophisticated adversary would target for a masked semantic substitution (e.g., quietly applying a retail/liquid-market rate to an illiquid staked position that should carry a discounted or haircut rate). No metadata field or explicit annotation in the graph asserts or denies such a haircut requirement, so this residual ambiguity is noted rather than dismissed, and is the basis for reserving a small amount of confidence rather than declaring the graph fully clean.