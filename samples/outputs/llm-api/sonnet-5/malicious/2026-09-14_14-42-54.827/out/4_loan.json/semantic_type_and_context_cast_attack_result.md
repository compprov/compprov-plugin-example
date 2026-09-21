# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Risk score**: 62.0

## Anomaly Localization

**Implicated nodes:** `i_9`, `i_13`, `i_17`, `i_22`, `i_26`, `i_30` (Escrow collected, months 1–6) → `op_21` (addBulk) → `o_32` ("Total escrow collected" = 2400.00) **[leaf, never consumed]**

`i_2` (Monthly payment) ×6 → `op_22` (addBulk) → `o_33` ("Total scheduled payments (6 months)" = 12000.00) → `op_23` (add) with `i_4` (Extra principal prepayment = 5000.00) → `o_34` ("Total amount paid by borrower" = 17000.00) **[leaf]**

**Flow of the mismatch:**
1. The graph explicitly and correctly computes a dedicated aggregate for escrow: `op_21` sums all six monthly escrow inputs into `o_32` ("Total escrow collected" = 2400.00). This node exists solely to represent money the borrower paid into escrow.
2. Separately, `op_22`/`op_23` compute `o_34`, labeled **"Total amount paid by borrower"**, as `(6 × Monthly payment) + Extra prepayment` = 17000.00.
3. `o_34` never references `o_32`. The two totals are structurally disconnected — `o_32` is a dead-end leaf that feeds nothing downstream.
4. The technical typing is flawless: every value is a correctly-propagated `java.math.BigDecimal`, every `add`/`addBulk` operation is mathematically self-consistent, and `MathContext` (`i_1`) is applied uniformly. A schema/type/replay validator would pass this graph without complaint.
5. However, the **business label** on `o_34` ("Total amount paid by borrower") asserts a scope — *all* cash the borrower actually paid — that the underlying computation does not deliver. The borrower demonstrably also paid $2,400.00 in escrow (a fact the graph itself proves via `o_32`), yet `o_34` silently narrows its scope to only loan principal/interest/prepayment components while retaining a descriptor that implies totality.

## Details

This is a textbook Semantic Type and Context Cast: the numeric pipeline is internally consistent and every intermediate figure (interest accrual, principal portions, balances, prepayment application) reconciles correctly against the amortization math — a naive audit that only checks type continuity (`BigDecimal → BigDecimal`) and arithmetic replay (12000 + 5000 = 17000) will find nothing wrong. The violation lives entirely in the **metadata/business-context layer**: the graph computes two mutually exclusive aggregates (`o_32` = escrow-only total, `o_34` = loan-only total) but only publishes `o_34` under a descriptor ("Total amount paid by borrower") whose plain-language business meaning necessarily subsumes *all* cash paid by the borrower, escrow included. The escrow total is not merged, referenced, footnoted, or otherwise reconciled against the "borrower total" — it is simply computed and abandoned as a stranded leaf, which is precisely the kind of quiet contextual narrowing this attack class describes: the value's origin (borrower-paid funds, per `i_9`…`i_30` metadata) contradicts its exclusion from the downstream node purporting to represent the borrower's full payment obligation.

**Consequence:** Any downstream consumer (regulator, borrower-facing statement, reconciliation system) trusting the label on `o_34` would understate the borrower's true total cash outflow by $2,400.00 (17000.00 reported vs. 19400.00 actual), while the graph's own internal ledger (`o_32`) proves the omitted amount was known and tracked. Because the omission is presented via a legitimately-typed `add` operation with no metadata contradiction visible to a schema checker, it is very likely to survive a technical review, which is exactly the mechanism this attack class exploits. Human resolution is recommended to confirm whether "Total amount paid by borrower" was intentionally scoped to exclude escrow (and if so, should be relabeled, e.g., "Total loan payments by borrower") or whether this is a deliberate under-reporting of borrower cash outflow.