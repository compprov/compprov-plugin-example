# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 90.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud — Double Counting**: Score 90/100 — CONFIRMED. The LOYALTY5 discount is embedded into the subtotal chain via op_9 and then subtracted a second time via op_14, double-applying the same discount to the final total.
- **Precision and Scale Tampering**: Score 8/100 — No violation found. All operations replay exactly against the declared MathContext (DECIMAL64, HALF_EVEN, precision 16), which is suitable and consistently applied to every arithmetic step; scale padding (e.g., 205.1280) is benign BigDecimal scale-tracking behavior, not truncation or salami-slicing.
- **Semantic Type and Context Cast**: Score 70/100 — CONFIRMED (same root cause viewed semantically). A variable explicitly labeled "Subtotal after LOYALTY5" (already net of the loyalty discount) is propagated into "Order" and then that already-net figure is consumed in op_14 as though it were still gross, by subtracting the "Loyalty discount" amount from it a second time.

---

#### Anomaly Localization (Attack Vector 1 — Double Counting)
Implicated nodes: `i_19` (Discount rate LOYALTY5), `o_18` (Subtotal after SAVE10), `op_8` (computes `o_20` = 1 - i_19 = 0.95), `op_9` (computes `o_21` = o_18 * o_20 — "Subtotal after LOYALTY5"), `op_13` (computes `o_27` = o_18 * i_19 = 10.2564 — "Loyalty discount"), `op_10`/`op_11`/`op_12` (propagate o_21 through `o_23` → `o_25` → `o_26` "Order"), and finally `op_14` (`o_28` = o_26 - o_27 — "Order total").

Flow of the attack:
1. `o_18` (205.128, post-SAVE10 subtotal) is multiplied by `o_20` (0.95) in `op_9`, producing `o_21` = 194.8716, explicitly named "Subtotal after LOYALTY5" — i.e., the loyalty discount of 10.2564 is already baked into this figure (205.128 − 194.8716 = 10.2564).
2. `o_21` flows forward unmodified through shipping addition (`op_10` → `o_23`), tax computation (`op_11` → `o_25`), and summation (`op_12` → `o_26`, "Order" = 223.961328). The loyalty discount is now fully reflected once in `o_26`.
3. Independently, `op_13` recomputes the same loyalty discount amount directly from the *same* inputs (`o_18`, `i_19`) as `o_27` = 10.2564.
4. `op_14` then computes "Order total" = `o_26` − `o_27` = 223.961328 − 10.2564 = 213.704928, subtracting the loyalty discount a **second time** from a total that already reflects it.

The variable `o_18` and `i_19` (both flagged in the structural reference data as multi-consumed) are precisely the shared values that converge into the terminal result `o_28` twice through two different arithmetic paths — the textbook double-counting pattern.

#### Details (Attack Vector 1)
Local replay of every individual operation passes: `op_9` is a correct multiplication, `op_13` is a correct multiplication, and `op_14` is a correct subtraction. No single step is locally wrong, which is exactly why naive validation (type-checking and per-node replay) misses the fraud — the flaw only becomes visible when tracing the loyalty-discount value's convergence into the terminal node. The practical impact: the reported "Order total" (213.70492800) is understated by the full loyalty-discount amount (10.2564, ~4.6% of the correct total of 223.961328), a material revenue leakage if this pipeline is used for billing/collections, or an improper double-rebate to the customer if used for refund/credit purposes. This is a mandatory-adjustment-applied-twice case, not a benign independent branch, because the discount's effect already reached the terminal path once via `o_21`/`o_23`/`o_26` before being subtracted again.

---

#### Anomaly Localization (Attack Vector 3 — Semantic Context Cast)
Implicated nodes: `o_21` ("Subtotal after LOYALTY5"), `o_26` ("Order"), `o_27` ("Loyalty discount"), `op_14`.

`o_21`'s own descriptor name declares it as already net of the LOYALTY5 discount. That business state ("loyalty-discount-applied") propagates unchanged in substance through `o_23` and `o_26` — no operation reverses or strips the discount. Yet `op_14` consumes `o_26` ("Order") as if it were still a pre-loyalty-discount, gross figure, applying `o_27` ("Loyalty discount") against it as a fresh deduction to produce "Order total".

#### Details (Attack Vector 3)
The technical type (`BigDecimal` → `BigDecimal`) and the arithmetic (`subtract`) are both completely valid, and `o_27`'s name ("Loyalty discount") makes the final subtraction look like a legitimate, expected step in an order-total pipeline — this is precisely the semantic gap the attack exploits: structural/type validation and even a cursory lineage read suggest "Order minus loyalty discount = Order total" is sound, while the graph's own earlier metadata ("Subtotal after LOYALTY5") proves the discount was already consumed into the lineage feeding "Order". No explicit transformation node reconciles this (e.g., nothing re-grosses `o_26` before the subtraction), so the originating metadata and the downstream consumption are in direct, unreconciled conflict. The consequence mirrors the topological finding: the terminal "Order total" silently understates the true payable amount by the loyalty-discount value, and an auditor relying only on name plausibility ("of course you subtract the loyalty discount from the order") would be misled exactly as intended.