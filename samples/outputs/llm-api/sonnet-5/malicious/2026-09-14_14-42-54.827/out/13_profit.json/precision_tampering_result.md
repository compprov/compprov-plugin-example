# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 85.0

## Anomaly Localization

**Implicated nodes:** `o_21`–`o_26` (per-leg gas costs), `o_27` ("Total gas fees in USDC"), `o_29`, `o_31`, `i_11`, `o_34`, `o_36` (raw yields), `o_30`, `o_32`, `o_33`, `o_35`, `o_37`, `o_38` (per-leg yields already net of their own gas leg via `op_10`, `op_12`, `op_13`, `op_15`, `op_17`, `op_18`), `o_39` ("Gross yield in USDC"), `op_21` (`o_39 - o_27 → o_41`, labeled "After gas deduction"), `o_41`, `op_22`, `o_42` ("Net profit in USDC").

**Flow of the defect:**
1. Each yield leg has its *own* transaction gas cost subtracted individually: `o_30 = o_29 - o_21`, `o_32 = o_31 - o_22`, `o_33 = i_11 - o_23`, `o_35 = o_34 - o_24`, `o_38 = (o_36 - o_25) - o_26`.
2. `op_19` (`addBulk`) sums these already-gas-net legs into `o_39`. Algebraically: `o_39 = (Σ raw yields) - (o_21+o_22+o_23+o_24+o_25+o_26) = (Σ raw yields) - o_27`. Verified numerically: Σ raw yields = 1738.305562, minus `o_27` = 111.4336 → 1626.871962, exactly matching reported `o_39`.
3. Despite already having gas fully netted out, `o_39` is labeled **"Gross yield in USDC"**, and `op_21` (labeled **"After gas deduction"**) subtracts `o_27` (total gas) from `o_39` a **second time**, producing `o_41 = 1515.438362`.
4. `op_22` then subtracts the platform fee (`o_40`, computed off the already-gas-net `o_39`) to yield the final `o_42 = 1466.632204` ("Net profit in USDC").

**Consequence:** The true economically correct net profit is `(Σ raw yields) − gas(once) − fee = 1738.305562 − 111.4336 − 48.806158 = 1578.065804`. The reported `o_42 = 1466.632204` is short by exactly `111.4336` — i.e., precisely one extra copy of `o_27`, the total gas figure. This is not rounding noise; it is an exact, reproducible duplication of a real cost.

## Details

All individual arithmetic steps (`convert`, `subtract`, `addBulk`, `scale`) were independently recomputed with exact rational arithmetic and match the reported values to the stated DOWN-truncation convention at each currency's native precision (e.g., `o_36 = 786.3411795` correctly truncates to `786.341179`; `o_34 = 299.5291231918...` correctly truncates to `299.529123`). There is no salami-slicing or rounding-mode inconsistency at the individual-operation level — every single node is internally self-consistent with the declared DOWN rounding rule.

The vulnerability instead lives in the **aggregation topology**: gas is deducted twice — once distributed per-leg before the `addBulk` into `o_39`, and again in bulk via `op_21` against the same `o_27` total. Because `o_39` is *mislabeled* as "Gross yield" (when it is actually already net-of-gas), a casual reviewer sees `op_21` performing a plausible "After gas deduction" step and does not notice that the gas has already been silently absorbed one layer earlier. This mislabeling is the exact kind of disguise a naive/exact-match audit (or even a human skimming descriptor names) would miss, since no duplicate IDs or duplicate names are involved — only a duplicate econometric effect achieved through distinct nodes/operations.

This directly violates the **asset conservation invariant**: the sum of (real gas paid + platform fee + reported net profit) does not reconcile to the total realized gross yield; ~$111.43 USDC (≈7% of the true net profit of $1578.07) evaporates from the ledger with no corresponding output variable capturing where it went — it is simply lost to a phantom double deduction. Given the materiality (single-digit-percent of total profit, in absolute USDC terms non-trivial) and the fact that this is a full periodic NAV/profit computation (implying the same double-subtraction bug would recur at every future settlement/reporting cycle for this portfolio), this qualifies as a high-severity, high-confidence finding under both the Materiality Override and (secondarily) the cyclic-recurrence considerations in the invariant specification.