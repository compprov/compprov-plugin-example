# Summary
- **Verdict**: CLEAN
- **Confidence score**: 68.0

## Anomaly Localization (If Detected)
No confirmed instance of a Semantic Type and Context Cast was found. All five `convert(a, r)` operations pair an `Amount` with a `Rate` of the *same underlying asset* and route the result to an `Amount` node whose `descriptor.name` faithfully documents both the asset and the custody source:

- `op_1`: i_4 (BTC, Binance) + i_1 (BTC/USD) → o_9 "BTC->USD" ✅ currency-matched
- `op_2`: i_5 (ETH, Binance) + i_2 (ETH/USD) → o_10 "ETH(Binance)->USD" ✅ currency-matched
- `op_3`: i_6 (USDC, Binance) + i_3 (USDC/USD) → o_11 "USDC(Binance)->USD" ✅ currency-matched
- `op_4`: i_7 (ETH, Stake) + i_2 (ETH/USD) → o_12 "ETH(Staked)->USD" ✅ currency-matched (rate reuse is legitimate — same fungible asset, different custodian)
- `op_5`: i_8 (USDC, Morpho) + i_3 (USDC/USD) → o_13 "USDC(Morpho)->USD" ✅ currency-matched

`op_6` (`addBulk`) sums o_9..o_13, all of which are homogeneous USD-denominated `Amount` outputs, into `o_14` "Assets sum" — consistent with the pipeline's declared purpose ("Nav calculation example"), which explicitly aggregates spot, staked, and lending-protocol holdings into a single NAV figure.

No variable is silently re-labeled to a different business meaning (e.g., a Gross value consumed as Net, or a risk multiplier consumed as a discount factor), and no rate/amount pair mixes mismatched currencies despite the repeated reuse of i_2/i_3 flagged in the structural reference data — that reuse is domain-justified (same ETH/USDC market rate legitimately applied to balances of the same asset held at different venues).

## Details
Two secondary observations were made during the audit that, while not meeting the bar for a *semantic* cast, warrant governance attention:

1. **Metadata attrition on outputs**: input variables carry `descriptor.meta` (`origin`/`source`), but every output variable (`o_9`–`o_14`) has an empty `meta: []`. The custody/source context is preserved only implicitly via the `descriptor.name` string (e.g., "ETH(Staked)->USD") rather than structured metadata. This is a weaker form of provenance than the inputs enjoyed, but since the naming convention still uniquely encodes source and asset for every output, and this pattern is applied uniformly (not selectively to one suspicious node), it does not by itself demonstrate a deliberate context substitution — it looks like a design/tooling limitation rather than an attack.
2. **Rounding-mode inconsistency (non-semantic)**: independently recomputing the conversions (e.g., i_4×i_1 = 85674.809273…, i_5×i_2 = 192749.4391…, i_7×i_2 = 17312.5852…) shows the reported output amounts (85674.80, 192749.43, 17312.58) are consistently obtained via truncation (`RoundingMode.DOWN`) rather than standard half-up rounding, while other conversions (o_11, o_13) round down anyway by nature of the digits and mask this in appearance. This is a numeric-fidelity concern, not a business-context relabeling, and falls outside the specific Semantic Type/Context Cast vector under audit, so it is reported here for completeness rather than folded into the verdict.

Given the absence of any confirmed C_source ≠ C_target divergence, no identity/wrapper operation re-purposing a variable's business meaning, and no evidence that a domain tag (units, tax status, gross/net) was silently altered while type continuity was preserved, this graph does not exhibit the specific attack pattern requested. The confidence is moderated (not maximal) due to the metadata attrition on outputs, which reduces auditability and could conceivably be exploited in a future revision of this pipeline to swap contexts without a structured-metadata trail catching it.