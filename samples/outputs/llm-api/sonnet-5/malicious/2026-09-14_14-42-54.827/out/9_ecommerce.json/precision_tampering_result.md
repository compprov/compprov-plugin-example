# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 90.0

## Anomaly Localization

**Implicated nodes:** `o_16→o_20` (LOYALTY5 discount multiplier chain), `o_18` (Subtotal after SAVE10), `o_20` (Discount multiplier LOYALTY5), `op_9`→`o_21` (Subtotal after LOYALTY5), `op_10`→`o_23` (Taxable amount), `op_11`→`o_25` (Sales tax), `op_12`→`o_26` ("Order"), `op_13`→`o_27` (Loyalty discount, standalone), `op_14`→`o_28` ("Order total", final reported output).

**Flow of the flaw:**

1. `op_9` computes `o_21 = o_18 * o_20 = 205.1280 * 0.95 = 194.8716`. This is the subtotal **after both SAVE10 and LOYALTY5 discounts have already been multiplicatively applied** (o_18 already reflects SAVE10; o_20=0.95 applies LOYALTY5 on top).
2. `op_10` builds the taxable amount directly from this doubly-discounted subtotal: `o_23 = o_21 + shipping(i_22) = 194.8716 + 12.50 = 207.3716`.
3. `op_11`/`op_12` compute tax and the running "Order" total (`o_26 = o_23 + o_25 = 223.961328`) entirely from the chain that already incorporates the LOYALTY5 discount.
4. In parallel, `op_13` independently computes `o_27 = o_18 * i_19 = 205.1280 * 0.05 = 10.2564` — the monetary value of the *same* LOYALTY5 discount, purely for line-item display. Its correctness is confirmed by `o_18 - o_27 = 194.8716 = o_21`, i.e., it is mathematically the identical discount already baked into `o_21`.
5. `op_14` then computes the final reported output: `o_28 = o_26 - o_27 = 223.961328 - 10.2564 = 213.704928`.

This final subtraction removes the LOYALTY5 discount amount a **second time** from a total that already reflects it, because `o_26` is downstream of `o_21` (which already divided by/multiplied the 0.95 factor).

## Details

**Mechanism:** The graph computes the LOYALTY5 discount twice through two independent paths that both feed the final result:
- Path A (multiplicative): `i_19 → o_20 (0.95) → op_9 → o_21 → op_10 → o_23 → op_12 → o_26`
- Path B (subtractive): `i_19, o_18 → op_13 → o_27 → op_14 → o_28`

Both paths derive from the *same* discount rate (`i_19 = 0.05`) applied to the *same* base (`o_18`), and both are algebraically consistent with each other (`o_18 - o_27 == o_21`, verified exactly: 205.1280 - 10.2564 = 194.8716). This consistency is precisely what makes the flaw dangerous — each individual multiplication/subtraction step is arithmetically perfect against its declared `MathContext` (precision 16, HALF_EVEN), so a naive per-operation reconciliation check (recomputing each op in isolation) reports zero discrepancy. The corruption only becomes visible when tracing the *lineage* of `o_26` back through `o_23`/`o_21` and recognizing it already contains the LOYALTY5 discount, making the second subtraction at `op_14` a double-application.

**Why it evades naive checks:** Every single operation (`op_1`–`op_14`) reconciles exactly against its `mc` context — there is no per-step rounding error, truncation, or scale-degradation anywhere in the graph. The structural duplicate/leaf-name detectors also find nothing, because `o_27` and `o_21` have distinct names and IDs and are never argument-identical. The vulnerability is a **lineage-level double-count**, not a local precision bug — exactly the class of defect an isolated, mechanical per-operation audit is structurally blind to.

**Consequence / materiality:** The reported "Order total" (`o_28 = 213.704928`, displayed as `213.70492800`) is **$10.2564 lower** than the correct total of `223.961328` (`o_26`), a ~4.8% deviation on a ~\$224 order. This is far beyond any single-ULP or rounding-mode-ambiguity tolerance (which caps at 1 unit at the target scale) — it is a full double-discount, a direct violation of the asset-conservation invariant. Whether this represents a coding defect or deliberately engineered under-billing, it is a material, reproducible skim/leak on every order that includes a LOYALTY5 discount — and because the discount-stacking logic is templated per order (not a one-off), it is trivially scalable across the full transaction volume of the pipeline, satisfying the "scalable population" criterion for a systemic (not merely cosmetic) financial defect.

**Recommendation:** Treat `op_14` (and the introduction of `o_27` as a subtrahend against `o_26`) as the point of compromise. The final total should be computed either as `o_26` alone (if LOYALTY5 is meant to be captured only via the multiplicative chain) or by subtracting the loyalty discount from a subtotal that has *not* already had it multiplicatively applied — never both.