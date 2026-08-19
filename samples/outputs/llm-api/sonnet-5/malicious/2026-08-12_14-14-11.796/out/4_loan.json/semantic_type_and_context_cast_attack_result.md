# Summary
- **Verdict**: CONTEXT MISMATCH
- **Confidence score**: 78.0

## Anomaly Localization

**Implicated nodes:** `i_9, i_13, i_17, i_22, i_26, i_30` (Escrow collected, months 1–6) → `op_21` → `o_32` ("Total escrow collected" = 2400.00) `[LEAF — never consumed downstream]`

`i_2` (Monthly payment) → `op_22` (addBulk × 6) → `o_33` ("Total scheduled payments (6 months)" = 12000.00)

`o_33` + `i_4` (Extra principal prepayment = 5000.00) → `op_23` (`add`) → `o_34` (**"Total amount paid by borrower"** = 17000.00)

**Attack flow:** The graph independently and correctly computes `o_32` = Total escrow collected (2400.00) via a legitimate `addBulk` over all six monthly escrow INPUTs. This variable is fully reconciled, type-consistent, and mathematically correct as a standalone artifact. However, it is structurally a dead-end leaf — it feeds **no** downstream operation. Meanwhile, `op_23` synthesizes the graph's terminal, headline output — `o_34`, explicitly labeled **"Total amount paid by borrower"** — by adding only `o_33` (scheduled P&I payments) and `i_4` (the month-4 prepayment). Escrow, despite being indisputably money paid out-of-pocket by the borrower every month, is silently excluded from the aggregate that carries the borrower-facing "total amount paid" label.

Numerically: 12000.00 (scheduled) + 5000.00 (prepay) = 17000.00 — the arithmetic is flawless and `op_23`'s formula `(a+b)mc` replays perfectly. Node connectivity and type continuity (`BigDecimal → BigDecimal`) are fully intact. But the *declared business scope* of `o_34` ("amount paid by **borrower**", not "amount paid **to lender**" or "P&I paid") semantically requires inclusion of the escrow disbursements the borrower also remitted. The true borrower outflow is 12000.00 + 2400.00 (escrow) + 5000.00 (prepay) = 19400.00, a **2400.00 / ~14% understatement** relative to what the label promises.

## Details

This is a textbook Semantic Type and Context Cast: a variable's declared context ("Total amount paid by borrower" — an unqualified, all-inclusive aggregate) is quietly narrowed to a different, undisclosed context ("Total loan-servicing payments excluding escrow") by the specific choice of which upstream nodes feed the final `add` operation. No metadata field (`descriptor.meta`) documents this scope restriction; no explicit domain-transformation node (e.g., "exclude escrow from total") exists to justify dropping `o_32` from the sum. The computation is 100% type-safe (`BigDecimal + BigDecimal = BigDecimal`) and passes local mathematical replay trivially, which is exactly why casual/automated validation would clear it — the check only confirms `op_23`'s formula matches its inputs, not that the inputs chosen are the correct *set* implied by the output's business name.

The structural evidence reinforces this: `o_32` is flagged as a leaf variable that is fully computed but never consumed by any downstream operation — a strong signal that a legitimate aggregate was deliberately built and then orphaned rather than wired into the final borrower-facing total. In a regulatory/consumer-disclosure context (loan servicing, TILA/RESPA-style total-payment disclosures), silently omitting escrow from a "total amount paid by borrower" figure is a materially misleading understatement, even though every individual arithmetic step is internally consistent and auditable in isolation.

**Consequence:** Any downstream consumer, auditor, or regulator relying on `o_34` as the authoritative "total amount paid by borrower" will underreport the borrower's true cash outflow by exactly the escrow total (2400.00 in this trace), despite the pipeline having already computed the correct escrow figure elsewhere in the graph. This is precisely the gap between technical type/mathematical validity and business-semantic correctness that the Semantic Type and Context Cast attack vector targets.