# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 91.0

## Anomaly Localization

**Implicated nodes:** `i_3` (Bonus, 500.00), `op_1` (add → `o_4` Gross pay), `o_4`, `o_7`, `o_8` (Taxable income), `o_12`, `o_13`, `o_14`, `o_15`, `o_16` (Federal tax), `o_18` (State tax), `o_19`, `o_20` (After state withholding, 2904.5000), `op_12` (add → `o_21` Net pay), `o_21` (3404.5000).

**Flow of the defect:**
1. `op_1`: `i_2` (Base salary, 3500.00) + `i_3` (Bonus, 500.00) → `o_4` = 4000.00 (Gross pay). The bonus is correctly folded into gross pay here.
2. `o_4` flows through `op_3` into `o_8` (Taxable income = 3650.00), which already **includes** the bonus, and is subsequently used to compute federal tax (`o_16` = 563.0000) and state tax (`o_18` = 182.5000) — i.e., the bonus is taxed exactly as ordinary income.
3. `op_10`/`op_11` correctly reduce taxable income by federal and state withholding to yield `o_20` = 2904.5000 — this is the mathematically correct net pay (Gross − Pretax deductions − Federal tax − State tax = 4000.00 − 350.00 − 563.0000 − 182.5000 = 2904.5000).
4. `op_12` then takes `o_20` and **adds `i_3` (the same Bonus input variable, 500.00) a second time**, producing the final reported output `o_21` = 3404.5000.

The structural fact set independently flags `i_3` as consumed by more than one operation (`op_1` and `op_12`) — a legitimate `MathContext`-style reuse pattern would not apply here since `i_3` is a `BigDecimal` monetary amount, not a shared context/config object like `i_1`.

## Details

This is not a subtle rounding-mode discrepancy — it is a full re-injection of a monetary input (`i_3`, Bonus = $500.00) into the pipeline after it was already consumed and taxed as part of Gross Pay / Taxable Income. The net effect is that the reported "Net pay" (`o_21` = 3404.50) overstates the true, internally-consistent net pay (`o_20` = 2904.50, which itself correctly reconciles Gross − Deductions − Federal Tax − State Tax) by exactly $500.00 — precisely the bonus amount, to the last unit of scale.

Why this passes casual review: every individual arithmetic step (`add`, `subtract`, `multiply`, `min`) is internally exact and consistent with the declared `MathContext` (precision 16, HALF_EVEN) — there is no ULP-level rounding error anywhere, and each operation's `resultId` value matches what its formula and arguments produce exactly. The exploit is not in the math of any single operation but in the **data-flow topology**: the same source variable (`i_3`) is legitimately consumed once (to build gross/taxable income) and then illegitimately reused a second time downstream of the tax-withholding chain, with no compensating subtraction anywhere in the graph to cancel this reintroduction. A per-operation checker that only recomputes `a op b = result` locally will find every step "correct" and miss the violation entirely — it only becomes visible when tracing that `i_3` has two consumers with no netting operation between them.

Consequence: this is a material asset-conservation violation (Invariant 1) — the payroll output overstates the employee's net pay by $500.00 (≈14.7% of the reported net pay), a large, non-repeating, single-execution discrepancy that fails the "Materiality Override" criterion regardless of whether the underlying operation population is cyclic. This is not disguised as a rounding artifact (each value carries no residual fractional cents consistent with salami-slicing) but as a legitimate-looking final "add" operation, making it a well-disguised, high-impact double-counting exploit rather than ordinary precision noise.