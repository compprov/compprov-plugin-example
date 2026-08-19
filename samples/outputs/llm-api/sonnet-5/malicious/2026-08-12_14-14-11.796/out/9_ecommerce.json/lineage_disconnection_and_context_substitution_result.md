# Summary
- **Verdict**: SUSPICIOUS SUBSTITUTION
- **Confidence score**: 85.0

## Anomaly Localization

**Implicated variables:** `i_16` (SAVE10 rate), `o_17` (SAVE10 multiplier), `o_18` ("Subtotal after SAVE10" — the true, correctly-computed *pre-loyalty* subtotal), `i_19` (LOYALTY5 rate), `o_20` (LOYALTY5 multiplier), `o_21` ("Subtotal after LOYALTY5" — subtotal that **already has the loyalty discount applied**), `i_22` (shipping), `o_23` ("Taxable amount"), `i_24` (tax rate), `o_25` ("Sales tax"), `o_26` ("Order total (before loyalty discount)"), `o_27` ("Loyalty discount"), `o_28` ("Order total", final reported output).

**Implicated operations:** `op_9` (`o_18 * o_20 -> o_21`), `op_10` (`o_21 + i_22 -> o_23`), `op_11` (`o_23 * i_24 -> o_25`), `op_12` (`o_23 + o_25 -> o_26`), `op_13` (`o_18 * i_19 -> o_27`), `op_14` (`o_26 - o_27 -> o_28`).

**Attack flow:** The graph correctly produces `o_18`, the subtotal *after SAVE10 but before LOYALTY5*. That variable is legitimately reused twice (flagged in the multi-consumer structural set): once to multiplicatively fold LOYALTY5 into `o_21` (`op_9`), and once to independently compute the flat "Loyalty discount" amount `o_27` (`op_13`, = `o_18 * i_19` = 205.128 * 0.05 = 10.2564).

The critical juncture is `op_10`. Given the downstream labels ("Order total (**before** loyalty discount)" for `o_26`, followed by subtracting a "Loyalty discount" `o_27` to reach the final `o_28`), the taxable-amount/pre-loyalty chain (`o_23 -> o_25 -> o_26`) should have been built on `o_18` (the genuinely pre-loyalty subtotal). Instead, `op_10` consumes `o_21` — the subtotal that *already* has LOYALTY5 baked in via `o_20`. This silently reroutes an already-discounted value into a chain whose own metadata claims it precedes that discount.

As a result, `o_26` (223.961328) is in fact the fully-correct, single-application order total (SAVE10 + LOYALTY5 + shipping + tax), mislabeled as "before loyalty discount." `op_14` then subtracts `o_27` (10.2564) from it a second time, yielding the reported final `o_28` = 213.704928. This is an exact algebraic fingerprint of double-discounting: 223.961328 − 10.2564 = 213.704928 to the last digit — not numerical noise, but a deterministic, reproducible second application of the same discount.

## Details

Every individual operation in the graph is locally arithmetically correct — `op_1` through `op_14` all replay cleanly against their declared arguments and `MathContext` (DECIMAL64/HALF_EVEN, precision 16). This is precisely why casual or purely local replay-based auditing would certify this graph as clean: no single node's math is wrong.

The rupture is at the *lineage/role* level, not the *node-local math* level. The graph maintains two parallel representations of "subtotal state": `o_18` (pre-loyalty) and `o_21` (post-loyalty, computed via `o_20`'s multiplier). The correctly-computed pre-loyalty variable `o_18` is not orphaned in the strict topological sense — it is consumed — but it is diverted away from the role its own name and adjacent SAVE10-multiplier pattern establish (base for the "before loyalty" total chain) and instead only feeds a side calculation (`o_27`) whose result is then subtracted a second time from a total that already absorbed the same discount through the substituted `o_21` path. The properly-computed "before loyalty" total that should have flowed into `o_26` (built from `o_18`, not `o_21`) never appears in the graph as a materialized node — it was silently replaced end-to-end by the post-loyalty `o_21` chain while the labels were left unchanged, masking the substitution.

**Consequence:** the reported final "Order total" (`o_28` = 213.70492800) systematically understates the true, correctly-derived order total (which is `o_26` = 223.96132800, itself already properly reflecting both SAVE10 and LOYALTY5) by exactly one redundant loyalty-discount instance (10.256400). This is a material, exactly-quantifiable financial discrepancy hidden behind a deterministically-replayable operation chain — the textbook signature of lineage disconnection: full local mathematical validity, broken semantic/causal provenance for the specific quantity ("pre-loyalty total") that feeds the compliance-critical final result.