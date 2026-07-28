# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 85.0

## Anomaly Localization

**Implicated variables:** `o_20, o_21, o_22, o_23, o_24, o_25` (six per-transaction gas costs), `o_26` ("Total gas fees in USDC"), `o_27, o_29, i_10, o_32, o_34` (raw, pre-gas position yields), `o_28, o_30, o_31, o_33, o_36` (per-position yields net of their own gas leg), `o_37` ("Gross yield in USDC"), `o_38` ("Platform fee in USDC"), `o_39` ("After gas deduction"), `o_40` ("Net profit in USDC").

**Implicated operations:** `op_9, op_11, op_12, op_14, op_16, op_17` (per-position gas subtractions), `op_18` (addBulk producing `o_37`), `op_19` (fee = `o_37 * 3%`), `op_20` (`o_37 - o_26` → `o_39`), `op_21` (`o_39 - o_38` → `o_40`).

**Flow of the defect:**
1. Each of the five yield positions is converted to USDC (`o_27, o_29, i_10, o_32, o_34` — these are the true, unadjusted "gross" yields).
2. Each position then has its *own* transaction gas cost subtracted individually (`op_9, op_11, op_12, op_14, op_16, op_17`), producing `o_28, o_30, o_31, o_33, o_36`. These individual subtractions are numerically correct in isolation.
3. `op_18` ("addBulk") — the operation whose result is explicitly labeled **"Gross yield in USDC" (`o_37`)** — does *not* sum the raw gross components (`o_27, o_29, i_10, o_32, o_34`). It instead sums the *already gas-adjusted* components (`o_28, o_30, o_31, o_33, o_36`). Verification: raw sum = 405.658296+127.193403+119.583561+299.529123+786.341179 = 1738.305562; subtracting total gas `o_26` (111.4336) gives exactly 1626.871962 — the value stored in `o_37`. So `o_37`, despite its name, is **already net of all gas**, not gross.
4. `op_19` then computes the 3% platform fee (`o_38`) on this artificially deflated "gross yield" base, understating the fee that should be charged on the true gross figure.
5. `op_20` ("After gas deduction", `o_39`) subtracts the *same* total gas figure `o_26` from `o_37` a **second time**: 1626.871962 − 111.4336 = 1515.438362. Algebraically, `o_39` = raw yield − 2×gas (1738.305562 − 222.8672 = 1515.438362, an exact match), i.e. gas is deducted twice on the path to the reported result.
6. `op_21` finally computes `o_40` ("Net profit") = `o_39 − o_38` = 1466.632204.

## Details

The true, un-substituted gross yield (the sum of `o_27, o_29, i_10, o_32, o_34` = 1738.305562) is never itself consumed by any downstream operation — it is silently replaced, in the very aggregation whose name and role ("Gross yield in USDC") demand the raw figure, by five look-alike but functionally different values that already embed a full gas deduction. This is a textbook instance of a final aggregation "quietly omitting" the component (true gross yield) that its own name indicates it should include, substituting an already-adjusted proxy instead.

Because the substitution is value-preserving in form (still a five-term sum labeled "Gross yield"), it passes casual review and even local replay of each individual subtraction (each `op_9…op_17` step is arithmetically correct in isolation, and `o_26` is a correctly computed sum of the same six gas legs). The deception only becomes visible when reconstructing the intended end-to-end formula: gas should be deducted exactly once between raw yield and net profit, and the 3% fee should be assessed on the true gross figure. Instead, gas is deducted once inside `o_37`'s hidden components and a second time via `op_20`, while the fee is assessed on the already-reduced base.

**Consequence:** Using a single-deduction, correct-base reconstruction, expected net profit ≈ 1738.305562 − 111.4336 (gas) − 52.149167 (3% of true gross) ≈ 1574.72 USDC. The graph instead reports `o_40` = 1466.632204 USDC — an understatement of roughly 108 USDC (~6.9%) versus the value a complete, non-duplicated computation would produce. This directly biases the reported "Net profit" figure downward relative to what the pipeline's own labeled formula ("Gross yield" → fee → gas deduction → net profit) claims to compute, which is material for any profit-sharing, tax, or performance-reporting use of this DAG's output.