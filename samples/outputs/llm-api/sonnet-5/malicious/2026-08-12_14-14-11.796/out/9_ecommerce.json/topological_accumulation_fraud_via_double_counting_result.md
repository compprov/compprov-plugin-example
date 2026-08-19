# Summary
- **Verdict**: DOUBLE COUNTING DETECTED
- **Confidence score**: 93.0

## Anomaly Localization

**Implicated variables:** `i_16` (SAVE10 rate), `o_17` (SAVE10 multiplier), `i_19` (LOYALTY5 rate), `o_20` (LOYALTY5 multiplier), `o_15` (Subtotal), `o_18` (Subtotal after SAVE10), `o_21` (Subtotal after LOYALTY5), `i_22` (Shipping), `o_23` (Taxable amount), `i_24` (Tax rate), `o_25` (Sales tax), `o_26` ("Order total (before loyalty discount)"), `o_27` ("Loyalty discount"), `o_28` (Order total, terminal output).

**Implicated operations:** `op_7` (o_18*o_20 → o_21), `op_10` (o_21+i_22 → o_23), `op_11` (o_23*i_24 → o_25), `op_12` (o_23+o_25 → o_26), `op_13` (o_18*i_19 → o_27), `op_14` (o_26-o_27 → o_28).

**Attack flow:**

1. `o_18` (Subtotal after SAVE10 = 205.128) is multiplied by `o_20` (0.95, the LOYALTY5 discount multiplier, itself derived from `i_19`=0.05 via `op_8`) in `op_7`, producing `o_21` = "Subtotal after LOYALTY5" = 194.8716. **The LOYALTY5 discount is fully baked into `o_21` at this point.**
2. `o_21` flows forward unmodified in substance through `op_10` (+shipping → `o_23`), `op_11` (tax → `o_25`), and `op_12` (`o_23`+`o_25` → `o_26`). `o_26` is labeled "Order total (before loyalty discount)" — but this label is false: the LOYALTY5 discount has *already* been applied upstream via `o_21`. The name masks the fact that the loyalty reduction is already embedded in `o_26`.
3. Independently, `op_13` re-reads the *pre-LOYALTY5* subtotal `o_18` and the *same* rate `i_19` to compute `o_27` = "Loyalty discount" = 10.2564 (5% of 205.128) — a value computed from an entity (`i_19`, `o_18`) that has already had its effect fully absorbed into the `o_21`→`o_23`→`o_25`→`o_26` chain.
4. `op_14` then subtracts `o_27` from `o_26` a second time to produce the terminal output `o_28` = 213.704928.

Both `i_19` and `o_18` are consumed by two separate operations (`op_8`/`op_13` and `op_7`/`op_13` respectively) whose results both converge, through disjoint paths, on the same terminal aggregation `op_14`. Path multiplicity M(i_19, o_28) = 2, violating the M=1 invariant.

## Details

This is a textbook instance of the deduction-side mirror of accumulation fraud described in the attack vector: "netting a cost out of one branch's intermediate subtotal, then subtracting that same cost again from a later aggregate that already incorporates that subtotal." The LOYALTY5 discount rate (`i_19`) legitimately reduces the order total once, via the multiplicative path `o_18 → o_20 → o_21`, which then correctly propagates through shipping and tax calculations into `o_26`. However, the graph *also* routes the same rate and the same pre-discount subtotal through a second, parallel arithmetic path (`op_13`) that recomputes the discount amount and subtracts it again from `o_26` in `op_14`.

Local replay of each operation passes: `op_7`, `op_13`, and `op_14` are each individually correct arithmetic given their stated inputs. The fraud is only visible globally, by tracing that `o_21` (which already contains the LOYALTY5 effect) and `o_27` (an independent LOYALTY5 computation) both terminate at the same rollup node `op_14`. The deceptive variable name on `o_26` — "Order total (before loyalty discount)" — is precisely the kind of look-alike/mislabeling mechanism the attack vector describes: it signals to a casual reviewer that loyalty discount has *not yet* been applied at that point, when in fact it structurally has (via `o_21`), making the second, explicit subtraction appear legitimate and necessary.

**Impact:** The reported `Order total` (`o_28` = 213.704928) is artificially deflated by exactly the double-counted loyalty discount amount (10.2564) relative to the value that would result from applying the LOYALTY5 discount only once (`o_26` = 223.961328). In a revenue-recognition context this understates collected/receivable revenue by ~4.6%; in a customer-facing billing context it means the customer is (or should be) charged more than what the ledger records, creating a reconciliation gap between recognized revenue and actual collectible amount. This satisfies the deduplicated-sum invariant violation: S_dedup (single application of LOYALTY5) ≠ S_reported (double application).