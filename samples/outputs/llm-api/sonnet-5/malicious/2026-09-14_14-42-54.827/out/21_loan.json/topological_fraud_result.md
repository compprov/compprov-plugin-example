# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 92.0

## Anomaly Localization

**Implicated nodes:** `i_9, i_13, i_17, i_22, i_26, i_30` (six monthly escrow inputs) → `op_21` → `o_32` ("Total escrow collected", correctly computed leaf) — **ORPHANED, never consumed downstream**.

Instead, the terminal aggregation `op_23` (`addBulk`, resultId `o_35` — "Total amount paid by borrower") consumes:
```
arguments: a=o_34 (Total scheduled payments), b0=i_33 ("Total escrow"), b1=i_4 (prepayment)
```
where `i_33` is a **root INPUT with no producing operation** (hardcoded literal `2280.0000`), not the properly-computed `o_32` (`2400.00`).

**Attack flow:**
1. `i_9,i_13,i_17,i_22,i_26,i_30` (400.00 each, 6 months) are correctly summed by `op_21` into `o_32 = 2400.00` ("Total escrow collected") — this is the legitimate, fully-derived escrow total.
2. `o_32` is never referenced by any other operation (confirmed leaf per structural reference data) — a dead-end despite being the exact quantity the terminal formula for "amount paid by borrower" requires.
3. A separate root variable `i_33`, named "Total escrow" — a near-duplicate/paraphrase of `o_32`'s name "Total escrow collected" — is hardcoded to `2280.0000` and fed directly into `op_23`, the operation that produces the reported terminal output `o_35 = 19280.0000`.
4. Correct forward propagation would yield `o_34 (12000.00) + o_32 (2400.00) + i_4 (5000.00) = 19400.00`, not the reported `19280.0000`. The reported terminal understates the true total amount paid by the borrower by exactly `120.00`.

## Details

This is a textbook **Lineage Disconnection and Context Substitution** ($M=1$, wrong source) compounded with a **Calculation Omission** ($M=0$) for `o_32`:

- The exact-name-collision check (`o_32` vs `i_33`) legitimately returns empty because the names are not byte-identical ("Total escrow collected" vs "Total escrow") — but they are semantically identical in role, units (BigDecimal escrow currency amount), and position in the loan-statement formula. This is precisely the disguised-substitution pattern the audit discipline warns about: a naive exact-match heuristic is trivially defeated by light rewording while the underlying fraud shape is unchanged.
- Local replay of `op_23` passes cleanly: `12000.00 + 2280.0000 + 5000.00 = 19280.0000` is arithmetically self-consistent. The tampering is only visible via full forward propagation from root inputs, which reveals that the correctly-computed escrow total (`o_32`, derived transparently from the six monthly escrow inputs) never reaches the terminal node, while an unmonitored, un-derived constant does.
- `i_33` satisfies none of the exemptions for permissible hardcoded values (not a mathematical constant, unit-conversion factor, or precision context) — it is a numeric override of a quantity for which a fully computed sibling (`o_32`) exists elsewhere in the same graph, which is explicitly forbidden by the stated invariants.
- Practical impact: the reported "Total amount paid by borrower" (`o_35 = 19280.0000`) is understated by `120.00` relative to the value the pipeline's own transparent escrow computation supports. In a loan-servicing/audit context, this either conceals a shortfall in escrow collection reporting or artificially improves a borrower-facing total-cost figure — a materially misleading terminal output despite every individual computation step being internally consistent and independently verifiable.

No other path-multiplicity violations of concern were found: repeated consumption of `i_2`, `i_3`, `i_5`, and the monthly balance/interest chain variables (`o_6`
…`o_27`) reflects the expected month-over-month state-carrying structure of an amortization schedule (each variable feeds forward exactly once into the next month's calculation and once into its own single downstream aggregate), and `i_4` (prepayment) legitimately appears once in the balance-reduction chain and once in the total-paid rollup — these are distinct, non-competing quantities (loan balance vs. cash-flow total), not duplicate contributions to the same terminal sum. `o_29` (Ending balance) and `o_31` (Total interest accrued) are legitimate, fully-derived terminal leaves with no missing contributors.