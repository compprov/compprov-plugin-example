# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 92.0

## Anomaly Localization

**Implicated nodes:** `o_23` (Taxable amount), `i_24` (Tax rate 8%), `op_11` (multiply → `o_25`), `o_25` (Sales tax, **leaf/dead-end**), `i_26` (INPUT node named "Tax"), `op_12` (add → `o_27`, Order total).

**Flow of the attack:**
1. `op_11` correctly computes the sales tax as `o_23 * i_24` = `207.371600 * 0.08` = **16.58972800** (`o_25`). This is arithmetically exact for the declared MathContext (precision 16, HALF_EVEN) — a fully correct calculation.
2. However, `o_25` is never consumed by any downstream operation. It is confirmed as a graph **leaf** — a dead-end output that exists only to look like the tax was applied.
3. The final "Order total" (`o_27`) is instead computed by `op_12` as `o_23 (207.371600) + i_26 (14.930755200)` = **222.302355200**.
4. `i_26` is declared as an independent `INPUT` variable named "Tax" with **no producing operation** — it is asserted as raw data rather than derived from the tax rate/taxable amount that the rest of the graph establishes.
5. Critically, `i_26`'s value is not arbitrary: `14.930755200 = 16.58972800 × 0.9` **exactly**, matching scale (8+1=9 decimal digits) and magnitude to the penny. This is not coincidental noise — it is the correctly-computed tax deliberately scaled down by exactly 10% and re-injected as a fabricated "input" to disguise the manipulation as legitimate external data rather than a traceable computation.

## Details

**Mechanism:** This is a classic "compute-then-discard-and-substitute" pattern. The pipeline performs the fully correct, auditable tax calculation (`op_11` → `o_25`), which would pass any cursory review of the tax-rate application logic. But that correct result is silently orphaned, and the value actually flowing into the customer-facing/ledger-facing `Order total` is a separately-declared input (`i_26`) that was engineered offline to equal 90% of the true tax. Because `i_26` carries no `resultId`-producing operation, there is no arithmetic step here for a naive validator to recompute and flag — the substitution happens at the *data provenance* level, not inside a visibly wrong formula. This is precisely the kind of tampering designed to slip past exact-match/formula-recomputation heuristics: every individual operation in the graph reconciles perfectly against its own inputs; the fraud lives in the choice of *which* variable feeds the final aggregation.

**Why this exceeds a rounding-mode ambiguity:** The delta between `o_25` and `i_26` is not a 1-ULP or last-digit rounding artifact — it is a clean, exact 10% reduction (Δ = 1.658972800, i.e., ~10% of the correct tax), reproduced to 9 decimal digits of precision. No MathContext, rounding mode, or legitimate scale-conversion rule can produce a 10% divergence; the EXPECTED_INVARIANTS explicitly cap legitimate rounding-mode disagreement at 1 unit in the target scale. This is asset-conservation failure: the taxable base and statutory rate the graph itself establishes (`o_23`, `i_24`) do not reconcile with the tax actually applied to the total.

**Materiality and scalability:** In this single order the shortfall is ~$1.66 on ~$222 (a mechanically consistent 10% skim on the tax line), but the operation pattern (`compute correct tax via op_11`, `discard it`, `substitute a pre-scaled input`) is embedded in a per-order, per-SKU e-commerce pricing pipeline — a population that scales directly with transaction volume. A systematic 10%-of-tax diversion applied across every order processed by this pipeline would constitute material, ongoing tax underpayment/skimming, which is exactly the scalable, beneficiary-directed pattern the Salami Slicing invariant is meant to catch (unlike a bounded, non-repeating rounding quirk). This is a high-confidence, high-materiality finding warranting escalation, not a benign rounding-convention artifact.