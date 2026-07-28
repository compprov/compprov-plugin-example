# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 90.0

## Anomaly Localization

**Implicated variables:** `i_13, i_14, i_15, i_16, i_17, i_18` (raw gas-fee inputs), `o_20, o_21, o_22, o_23, o_24, o_25` (per-transaction gas costs converted to USDC), `o_26` ("Total gas fees in USDC" = 111.433600 USDC) — **the omitted node**.

**Implicated operations:** `op_1`–`op_6` (per-leg gas conversions), `op_7` (`addBulk` aggregating gas into `o_26`), `op_12` (`addBulk` producing `o_31` Gross yield), `op_13` (`scale` producing `o_32` Platform fee), `op_14` (`subtract` producing `o_33` Net profit).

**Attack flow:**
1. The pipeline meticulously computes gas costs for every single deposit transaction (wBTC→AAVE, ETH→AAVE, USDC→AAVE, USDT→Morpho, ETH→Lido, stETH→EtherFi), converting each into USDC at the transaction-date exchange rate (`op_1`–`op_6`), and correctly sums them into `o_26 = 111.4336 USDC` via `op_7`.
2. In parallel, the pipeline computes each position's yield in USDC (`op_8`–`op_11`) and aggregates them via `op_12` into `o_31 = "Gross yield in USDC" = 1738.305562`.
3. `op_13` computes a 3% platform fee on `o_31`, and `op_14` computes `o_33 = "Net profit in USDC" = o_31 - o_32 = 1686.156396`.
4. **`o_26` (Total gas fees) is never referenced as an argument to `op_12`, `op_13`, `op_14`, or any other operation.** It is a structural leaf (confirmed by the provided leaf-variable set `[o_33, o_26]`) — a fully computed, correctly-derived cost that terminates in a dead end instead of flowing into the final "Net profit" figure.

The formula the pipeline *should* compute is:
`Net profit = Gross yield − Platform fee − Total gas fees`
but what is actually computed is:
`Net profit = Gross yield − Platform fee`

This overstates the reported "Net profit in USDC" by exactly the omitted gas-cost amount (111.4336 USDC, ~6.4% of the reported net profit of 1686.156396).

## Details

This is a textbook Calculation Omission. The gas-fee subgraph (`i_13`…`i_18` → `o_20`…`o_25` → `o_26`) is computed with full rigor: each leg uses the exchange rate matching its own transaction date and target asset, and the bulk-add arithmetic is internally exactly consistent (22.4+17.3844+12.93+17.5032+17.92+23.296 = 111.4336). This precision is exactly what makes the omission hard to spot on casual review — every individual computation checks out mathematically, and a naive replay of each operation's arithmetic in isolation would find no fault. The defect is not in any single operation's math; it is in the *aggregation graph topology*: `o_26`'s only edge is as a `resultId`, with no outgoing edge into `op_12`'s addBulk (gross yield) or into `op_14`'s subtract (net profit).

Semantically, gas fees are unambiguously an operational cost of entering these yield positions — the same category of "mandatory adjustment" as the platform fee, which *is* correctly wired into the final subtraction. There is no metadata, descriptor note, or alternate output variable indicating that gas costs are intentionally reported separately (e.g., no "Net profit before gas" label — the terminal node is unqualified "Net profit in USDC," implying completeness). The selective inclusion of the platform fee alongside the selective exclusion of gas fees is precisely the kind of surgical, single-channel omission a targeted tamper would produce: it preserves a clean, plausible-looking one-term deduction (fee) while quietly dropping a second, comparably-sized deduction (gas) that was nonetheless computed in full view.

**Consequence:** The reported final result, "Net profit in USDC" = 1686.156396, is overstated by the full 111.4336 USDC of unaccounted transaction gas costs. A corrected computation would yield approximately 1686.156396 − 111.433600 = 1574.722796 USDC as the true net profit — a material difference for a DeFi portfolio profit/loss report.