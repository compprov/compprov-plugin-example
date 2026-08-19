# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 93.0

## Anomaly Localization

**Implicated nodes:**
- `i_14`–`i_19` (six gas-cost INPUT amounts, one per protocol transaction)
- `i_5`–`i_8` (ETH/USDC rates used to convert each gas cost to USDC)
- `op_1`–`op_6` (six `convert` operations producing `o_21`…`o_26`, the per-transaction gas cost in USDC)
- `op_7` (`addBulk`) producing **`o_27` — "Total gas fees in USDC" = 111.433600 USDC**
- `op_13` (`addBulk`) producing `o_33` — "Gross yield in USDC", consuming only `o_29, o_30, i_11, o_31, o_32`
- `op_14` (`scale`) producing `o_34` — "Platform fee in USDC" = 3% of `o_33`
- `op_15` (`subtract`) producing **`o_35` — "Net profit in USDC"**, computed as `o_33 - o_34` only

**Attack flow:**
1. Every individual on-chain gas expenditure (`i_14`…`i_19`) is faithfully converted to USDC (`op_1`–`op_6`) using date-matched, transaction-matched exchange rates — each conversion is arithmetically correct and internally consistent (e.g. `0.005 ETH * 4480 = 22.400000`, `0.00396 * 4390 = 17.384400`, etc.).
2. These six converted costs are then correctly summed via `op_7` into `o_27`, "Total gas fees in USDC" = **111.433600 USDC**. This sum is verified: 22.4 + 17.3844 + 12.93 + 17.5032 + 17.92 + 23.296 = 111.4336. The computation is transparent and exact.
3. However, **`o_27` is never consumed by any downstream operation.** Per the structural leaf-set, `o_27` is a leaf (unconsumed dead end) — confirmed by full traversal: no operation argument list (`op_8`–`op_15`) references `o_27`.
4. The final aggregation chain instead runs: `op_13` sums only the five yield legs (`o_29, o_30, i_11, o_31, o_32`) into `o_33` (Gross yield); `op_14` computes the 3% platform fee from `o_33`; `op_15` computes Net profit as `o_33 - o_34` — **completely bypassing the gas-fee total that the pipeline itself computed one step earlier.**
5. Net result: the reported "Net profit in USDC" (`o_35` = 1686.156396) silently excludes the 111.433600 USDC of transaction/gas costs that were incurred to generate that yield. A complete computation would report `o_33 - o_34 - o_27` = 1738.305562 − 52.149166 − 111.433600 = **1574.722796 USDC**, roughly 6.6% lower than the reported figure.

## Details

The pipeline's own descriptor identifies this as a "DeFi portfolio profit calculation," and the gas-fee subgraph (six matched conversions + a dedicated `addBulk` roll-up named explicitly "Total gas fees in USDC") demonstrates the designers clearly intended gas costs to be tracked as a first-class cost component of the strategy — on par with the platform fee, which *is* wired into the final subtraction. There is no metadata, comment, or alternate consumer anywhere in the graph indicating that `o_27` is meant to be informational-only or reported out-of-band; it has no other resultId dependents, and it is not marked as a separate OUTPUT report distinct from the profit calculation (it carries the exact same `Amount`/`OUTPUT` shape as every other consumed intermediate).

This is precisely the profile of a **Calculation Omission**: the deduction is computed with full correctness and precision (surviving any per-operation replay check, since `op_1`–`op_7` are individually valid), but it is severed from the aggregation that produces the headline number. A casual auditor who only re-verifies each operation's local arithmetic (as any naive replay tool would) will find every single operation 