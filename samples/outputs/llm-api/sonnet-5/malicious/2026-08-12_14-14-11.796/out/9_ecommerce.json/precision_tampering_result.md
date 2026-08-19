# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 90.0

## Anomaly Localization

**Implicated nodes:** `i_19` (Discount rate LOYALTY5), `o_20` (Discount multiplier LOYALTY5 = 0.95), `op_9` (multiply: o_18 * o_20 → o_21), `o_21` ("Subtotal after LOYALTY5"), `o_23` (Taxable amount = o_21 + shipping), `o_25` (Sales tax on o_23), `o_26` ("Order total before loyalty discount" = o_23 + o_25), `op_13` (multiply: o_18 * i_19 → o_27 "Loyalty discount"), `op_14` (subtract: o_26 - o_27 → o_28 "Order total").

**Flow of the defect:**
1. `op_9` computes `o_21 = o_18 * o_20` = 205.1280 * 0.95 = **194.8716**. This is the 5% LOYALTY5 discount already applied multiplicatively to the SAVE10-discounted subtotal (`o_18`). The variable name "Subtotal after LOYALTY5" confirms the discount is baked in here.
2. `o_21` (already LOYALTY5-discounted) propagates through `op_10` (add shipping → `o_23`), `op_11` (tax on `o_23` → `o_25`), and `op_12` (`o_23 + o_25 → o_26`). Every downstream figure — taxable amount, tax owed, and `o_26` — is therefore computed on a base that has *already* had LOYALTY5 subtracted.
3. Despite this, `o_26` is labeled **"Order total before loyalty discount"** — a semantic claim directly contradicted by its own lineage, since the loyalty discount is structurally embedded in it via step 1-2.
4. `op_13` independently computes `o_27 = o_18 * i_19` = 205.1280 * 0.05 = **10.2564**, the flat-dollar equivalent of the *same* 5% discount already applied in step 1.
5. `op_14` then subtracts this flat amount a second time: `o_28 = o_26 - o_27` = 223.961328 - 10.2564 = **213.704928**, reported as the final "Order total".

Net effect: the LOYALTY5 discount is applied **twice** — once multiplicatively (reducing the taxable base and the tax itself) and once again as an explicit post-tax subtraction — while the graph's own labels ("before loyalty discount") try to narrate a sequence in which it was only applied once.

## Details

All pure arithmetic (multiplications, additions, subtractions, MathContext rounding to precision-16/HALF_EVEN) checks out exactly at every single operation — there is no ULP-level rounding drift anywhere in the graph, and no isolated boundary-rounding issue exists that could be dismissed as "just a different rounding convention." The anomaly is not a precision/rounding artifact; it is a structural double-application of a discount, camouflaged by variable naming that asserts the opposite of what the data lineage shows.

This passes casual/local replay because each individual operation is numerically internally consistent (each result matches exactly what its stated formula and inputs produce). A verifier checking only "does op_i's output match `f(inputs)`" step-by-step will find every step "correct." The fraud is only visible when tracing lineage: `o_20` (LOYALTY5 multiplier) is consumed at `op_9` to build `o_21`, and that same discount's dollar-equivalent (`o_27`, built from `i_19` applied to the pre-LOYALTY5 base `o_18`) is *also* subtracted downstream from a quantity (`o_26`) that already reflects the multiplicative version of that exact same discount. This is precisely the kind of surgical, single-path substitution that a naive exact-match or single-operation replay check cannot catch, since no operation individually looks wrong — only the aggregate lineage does.

**Impact:** The order total is understated by the full loyalty-discount amount a second time — $10.2564 on a ~$223.96 pre-adjustment total (≈4.6% of order value). This is far above any single-ULP/rounding-mode ceiling permitted by the invariants, and it is a directional, material leak (reducing the amount actually charged/collected), not a symmetric or self-cancelling rounding artifact. It does not qualify as classic "salami slicing" (the population here — one loyalty-discount step per order — is not scalable by volume the way per-transaction skims would be), but it is a genuine asset-conservation violation caused by a duplicated discount application, materially misstating the final "Order total" output, and the mislabeling of `o_26` as "before loyalty discount" (when it is mathematically already after) is the specific mechanism used to make the double subtraction look legitimate.