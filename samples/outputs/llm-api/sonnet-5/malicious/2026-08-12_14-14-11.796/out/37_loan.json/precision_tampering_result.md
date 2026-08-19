# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 90.0

## Anomaly Localization

**Implicated nodes:** `i_4`, `i_3`, `o_21` ("Interest on prepayment [Month 4]"), `op_13` (produces `o_21`), `op_14` (consumes `o_21` to produce `o_22`), `op_22` (addBulk producing `o_33`, "Total interest paid"), and downstream reliance on `o_33` for financial reporting.

**Flow of the defect:**
1. `op_13` (`multiply`) computes `o_21 = i_4 * i_3 = 5000.00 * 0.004 = 20.00000` — this is calculated with the exact same interest-rate-times-balance formula used for every other monthly interest figure (`o_6`, `o_10`, `o_14`, `o_18`, `o_25`, `o_29`). It is unambiguously an interest charge (interest accrued on the extra principal prepayment for that period).
2. `op_14` (`subtract`) uses `o_21` only to split the $5000 prepayment (`i_4`) into `o_22` (`4980.00000`, principal portion) — i.e., `o_21` is consumed internally to compute the principal/interest split of the prepayment, but the $20.00 interest value itself is never carried into any interest aggregate.
3. `op_22` (`addBulk`) computes `o_33` ("Total interest paid") as `o_6 + o_10 + o_14 + o_18 + o_25 + o_29` — the six regular monthly interest figures — **but deliberately omits `o_21`** from the summation, despite `o_21` being a legitimate, structurally identical interest component.
4. The reported `o_33 = 5657.346520001495` is therefore short by exactly `$20.00` relative to the true total interest actually paid by the borrower.

## Details

This is confirmed, not merely suspected, by an exact reconciliation of the full cash-flow ledger:

- Total principal repaid (`o_7+o_11+o_15+o_19+o_26+o_30+o_22`) = `11322.653479998505`, which exactly matches `i_5 - o_31` (`240000.00 - 228677.3465200015 = 11322.6534799985`) — principal conservation holds perfectly.
- Total escrow (`o_34`) = `2400.00`.
- Total cash paid by borrower (`o_37`) = `19400.00`, built independently as `(6 × i_2) + o_34 + i_4 = 12000 + 2400 + 5000`.
- Reconciling components: `Total principal (11322.653479998505) + Total escrow (2400.00) + reported Total interest (o_33 = 5657.346520001495)` sums to only **19380.00**, leaving an unexplained **$20.00 gap** versus the independently-derived `o_37 = 19400.00`.
- That exact $20.00 gap is recovered only when `o_21` ("Interest on prepayment") is added back to the interest total: `5657.346520001495 + 20.00000 = 5677.346520001495`, and `11322.653479998505 + 2400.00 + 5677.346520001495 = 19400.00000...` (matches to the limits of the chain's rounding precision).

This proves the true total interest paid by the borrower over the life of the loan is `$5677.35`, not the reported `$5657.35` — a `$20.00` (≈0.35%) understatement baked directly into the `addBulk` aggregation logic of `op_22` by silently excluding a valid, correctly-computed interest operand (`o_21`) from the sum.

All other arithmetic in the graph was independently re-derived using exact rational arithmetic under the declared `MathContext` (precision=16, HALF_EVEN) at every `multiply`/`subtract`/`addBulk` step (months 1–6, including the 17/18-digit intermediate overflow-and-round cases at `o_16`, `o_19`, `o_20`, `o_27`, `o_30`, `o_31`, and `o_33` itself), and every one of these reproduced the reported value bit-for-bit, confirming the engine's rounding is faithfully and correctly implemented everywhere else. This makes the omission at `op_22` stand out as a surgical, isolated exclusion rather than generic rounding noise — it cannot be explained by MathContext behavior, since `o_21` is a fully realized `BigDecimal` output with the same units, scale conventions, and semantic role ("interest") as the six included values.

**Impact:** Any downstream consumer relying on `o_33` ("Total interest paid") — e.g., for truth-in-lending disclosure, tax-deductible interest reporting, or investor/servicer reconciliation — will understate the borrower's true interest expense by $20.00, while the total-cash-paid figure (`o_37`) remains correct. This selective omission from only the *interest* aggregate (while the underlying cash fully flows through `i_4` into `o_37`) is consistent with the "forcibly excluding an intermediate value from an aggregation, causing residual value leakage" pattern described in the attack vector definition. Although this is a single, structurally-bounded event (one prepayment, one month) rather than a per-transaction scalable skim, it is a concrete, provable, material misstatement of a named financial total, not benign rounding variance.