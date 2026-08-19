# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:**
- `i_8` — "Expense reimbursement (non-taxable)" = 120.00
- `o_9` — "Taxable income (before reimbursement)" = 3650.00 (correctly = Gross − Pretax deductions)
- `op_4` (add) — **the pivot operation**: `o_10 = o_9 + i_8` = 3770.00, labeled "Taxable income"
- `o_10` — "Taxable income" = 3770.00 (now contaminated with the non-taxable reimbursement)
- `o_14`, `o_15`, `o_16`, `o_17`, `o_18` — federal bracket/tax chain, all computed from the contaminated `o_10`
- `o_20` — state tax, computed as `o_10 * i_19` = 3770.00 × 0.05, also from contaminated `o_10`
- `o_21`, `o_22` — final withholding/net-pay chain, propagating the contamination through to the reported "Net pay"

**Flow of the defect:**
1. `op_3` correctly computes `o_9` = Gross (4000.00) − Pretax deductions (350.00) = 3650.00. Up to here the pipeline is sound.
2. `op_4` then computes `o_10 = o_9 + i_8`, i.e. it *adds the reimbursement explicitly flagged as "non-taxable" back into the taxable-income base*, and relabels the result "Taxable income". This is the injection point.
3. Every downstream tax computation — bracket apportionment (`o_14`/`o_15`), federal bracket tax (`o_16`/`o_17`/`o_18`), and state tax (`o_20`) — is computed off this inflated `o_10`, meaning the $120 reimbursement is taxed at the marginal federal rate (22%, since it falls entirely in bracket 2) plus the 5% state rate.
4. Net pay (`o_22 = o_21 − o_20 = (o_10 − o_18) − o_20`) never separates the reimbursement back out untaxed; it is folded into the same base that absorbed 27% withholding on that increment.

## Details

Every individual BigDecimal operation is internally self-consistent: each `add`/`subtract`/`multiply`/`min` call reproduces its declared `resultId` value exactly under the stated `MathContext` (precision 16, HALF_EVEN) with correctly propagated decimal scales (e.g. `2000.00 * 0.10 = 200.0000`, `1770.00 * 0.22 = 389.4000`, etc.). A purely mechanical replay of the arithmetic therefore raises no red flags — which is precisely why this qualifies as a *disguised* tampering rather than a naive one: the fraud is not in the arithmetic of any single operation, it is in which variables are fed into `op_4` and reused in the tax-computation subgraph.

The descriptor for `i_8` explicitly states "non-taxable." A correct payroll pipeline would keep the reimbursement outside of the taxable-income base entirely and add it back to net pay only *after* tax withholding is computed, so the full $120 reaches the employee untouched. Instead, this graph folds the reimbursement into the exact variable subsequently named and used as "Taxable income" for both the federal bracket calculation and the state tax multiplication. The predictable consequence: $120 × (22% + 5%) = $32.40 of a supposedly tax-exempt reimbursement is silently diverted into the federal/state withholding lines every pay period.

Reconstructing the counterfactual "correct" pipeline (tax computed on `o_9` = 3650.00, with the $120 reimbursement added back untaxed after withholding) yields Net Pay = 2904.50 + 120.00 = 3024.50, versus the reported 2992.10 — a $32.40 discrepancy, exactly equal to the combined marginal-rate tax improperly applied to the reimbursement. This is far larger than any single-ULP/rounding-mode artifact (which is capped at 1 unit in the last decimal place) and cannot be explained by MathContext choice — the `mc` arguments used throughout are consistent and correctly applied.

This defect is structurally repeatable: it lives in a single, well-defined operation pattern (`add(taxable_base, non_taxable_reimbursement) → mislabeled_taxable_base`) that would recur identically for every employee and every biweekly pay cycle in a real payroll system — a scalable population of transactions, not a one-off, bounded computation. Each instance skims a small, proportionally consistent fraction of a specific class of non-taxable payments into withholding, which is the defining shape of a salami-slicing exploit rather than an isolated rounding quirk. The deltas do not cancel elsewhere in the graph — they compound directly into the final reported "Net pay" (`o_22`), understating it materially relative to correct tax treatment of the reimbursement.

**Impact:** The reported Net Pay of 2992.10 systematically understates the employee's correct net pay by 32.40 (≈1.1% of net pay) per this reimbursement, due to a non-taxable amount being routed through the taxable-income computation before being partially returned via the withholding-subtraction chain. This is a material, reproducible, and scalable leakage — not benign rounding variance — squarely inside the scope of the Precision/Scale/Category-mixing tampering vector.