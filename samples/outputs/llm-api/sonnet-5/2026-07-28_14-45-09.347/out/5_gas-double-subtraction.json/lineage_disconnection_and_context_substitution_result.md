# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 86.0

## Anomaly Localization

**Implicated variables:** `o_20, o_21, o_22, o_23, o_24, o_25` (six per-transaction gas costs in USDC), `o_26` ("Total gas fees in USDC"), `o_27, o_29, i_10, o_32, o_34` (raw per-asset yields in USDC), `o_28, o_30, o_31, o_33, o_36` (per-asset yields *already net of their own gas*), `o_37` (labeled "Gross yield in USDC"), `o_38` ("Platform fee in USDC"), `o_39` ("After gas deduction"), `o_40` ("Net profit in USDC").

**Implicated operations:** `op_7` (addBulk → `o_26`), `op_9/op_11/op_12/op_14/op_16/op_17` (per-asset subtract of gas → `o_28,o_30,o_31,o_33,o_36`), `op_18` (addBulk → `o_37`), `op_19` (scale → `o_38`), `op_20` (subtract `o_26` from `o_37` → `o_39`), `op_21` (subtract → `o_40`).

**Flow of the defect:** Each of the six gas-cost variables (`o_20`…`o_25`) is legitimately produced once by `convert()`, but is then consumed by **two independent downstream aggregation paths**:
1. Individually subtracted from its corresponding raw yield (`op_9, op_11, op_12, op_14, op_16, op_17`) to build the per-asset "net-of-gas" figures (`o_28, o_30, o_31, o_33, o_36`), which are summed in `op_18` into `o_37`.
2. Summed independently by `op_7` into `o_26` ("Total gas fees"), which is then subtracted from `o_37` a second time in `op_20` to produce `o_39` ("After gas deduction").

Because `o_37` is algebraically equal to `TotalRawYield − TotalGas` (verified: 1738.305562 − 111.4336 = 1626.871962 = `o_37`), it is **already net of gas** despite being labeled "Gross yield in USDC". Subtracting `o_26` from it again in `op_20` deducts the identical gas expense a second time. The result: `o_39 = TotalRawYield − 2×TotalGas`, and the final reported `o_40` ("Net profit") is short of the value that the graph's own upstream computation chain actually supports by **exactly 111.4336 USDC — precisely the value of `o_26`**. (Recomputing `o_37 − o_38` directly, i.e. treating `o_37` as the already-net figure it actually is, yields 1578.065804 vs. the reported 1466.632204 — a discrepancy of exactly the total-gas amount.)

## Details

Every individual operation in this graph replays correctly in isolation — each `convert`, `subtract`, and `addBulk` step is internally consistent with its declared arguments and `resultId`, which is why a naive local-replay check passes cleanly. The rupture is not a swapped operand or a hardcoded literal masquerading as an input; it is a **semantic/contextual substitution**: the variable `o_37` is genuinely computed, but it is fed into `op_20` under a false context — treated as if it were the *pre-gas* gross yield (consistent with its misleading label "Gross yield in USDC") when it is mathematically the *post-gas* net yield. This mislabeling licenses a duplicate consumption of the same six gas-cost values through two parallel aggregation branches (`op_7`→`o_26` and the `op_9/11/12/14/16/17` chain feeding `o_37`), which converge at `op_20`.

This is exactly the class of defect the audit is designed to catch: a variable that is technically wired to its own correct `resultId` upstream, but is substituted into a downstream role it does not actually fulfill, causing the final reported output to diverge from what true forward-propagation of the graph's stated formula would produce — while remaining fully deterministic and locally self-consistent. The structural reference data's "multiply-consumed variable" list (`o_20…o_25, o_37`) is the direct fingerprint of this pattern: these are not benign reuses (e.g., a shared `MathContext`), they are the same monetary quantity being drained into two competing aggregation paths that both terminate in the final P&L.

**Impact:** The reported "Net profit in USDC" (`o_40` = 1466.632204) understates the profit that the graph's own inputs and formulas actually justify by 111.4336 USDC (~7% of the correctly-derived figure of ~1578.07), because total gas is deducted twice — once implicitly (embedded in `o_37` via six per-asset subtractions) and once explicitly (`op_20`). In a compliance/profit-distribution context, this is precisely the mechanism by which value can be quietly siphoned or reported returns understated while still passing casual mathematical review, since every single arithmetic step checks out.