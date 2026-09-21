# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 78.0

## Anomaly Localization

**Implicated nodes:** `i_9`, `i_13`, `i_17`, `i_22`, `i_26`, `i_30` (Escrow collected, months 1–6) → `op_21` (`addBulk`) → `o_32` ("Total escrow collected" = 2400.00) → **[DEAD END — no consuming operation]**

**Terminal node under review:** `o_34` ("Total amount paid by borrower"), produced by `op_23`: `add(a=o_33, b=i_4, mc=i_1)` → 12000.00 + 5000.00 = **17000.00**

**Attack flow:**
1. Six escrow inputs (`i_9`, `i_13`, `i_17`, `i_22`, `i_26`, `i_30`, each 400.00) are correctly and transparently summed by `op_21` into `o_32` = 2400.00 ("Total escrow collected").
2. `o_32` is never referenced as an argument by any subsequent operation. It appears only in the leaf set (`[o_34, o_29, o_32, o_31]`) as a standalone terminal metric.
3. The graph's other clearly-labeled aggregate, `o_34` ("Total amount paid by borrower"), is computed via `op_23` using only two arguments: `o_33` ("Total scheduled payments (6 months)" = 6 × i_2 = 12000.00) and `i_4` (extra prepayment = 5000.00).
4. `o_32` is structurally excluded from the argument list of `op_23`, despite the escrow amounts being explicitly modeled in the graph as money "collected" from the borrower every month, i.e., an amount the borrower actually paid.
5. Result: $M(\text{escrow}, o_{34}) = 0$ — a Calculation Omission. The reported "Total amount paid by borrower" (17000.00) silently excludes the 2400.00 in escrow the borrower demonstrably paid over the same 6 months, understating the true total borrower outlay by ~14%.

## Details

**Mechanism:** The graph faithfully computes every escrow contribution and even rolls it up into a clean subtotal (`o_32`), which passes every local sanity check — the arithmetic of `op_21` is correct, and `o_32`'s value (2400.00) is internally consistent with the six 400.00 inputs. This is precisely why a naive audit (or the structural reference data alone) would miss the problem: nothing is *miscalculated*, and `o_32` legitimately appears as a leaf, which is expected for a genuine terminal metric like "Total escrow collected." The fraud is not in any single operation's math — it is in the terminal aggregation operation (`op_23`) that produces `o_34`, whose own descriptor name ("Total amount paid by borrower") makes an explicit, unambiguous claim to completeness that its actual argument list does not fulfill.

Contrast this with `o_33` ("Total scheduled payments (6 months)"), which correctly sums 6 instances of `i_2` — that reuse is benign because it represents six distinct time-period payments of the same constant, not one entity double-counted into a rollup. The escrow case is different: it is a wholly separate money flow (tracked in parallel, per month, alongside principal/interest) that is exactly the kind of mandatory contributor the EXPECTED_INVARIANTS describe — a component the terminal result's own formula and name imply it should incorporate — yet it is quietly dropped from that formula's argument list.

**Why it evades casual review:** An auditor spot-checking `op_23`'s arithmetic (12000 + 5000 = 17000) will find it locally correct. An auditor checking `o_32`'s arithmetic will also find it locally correct. Only a full path-multiplicity trace from every mandatory contributor forward to the terminal aggregation reveals that `o_32` never reaches `o_34` at all — it dead-ends as an isolated leaf while a nearly-identically-scoped terminal metric (`o_34`) reports as though the computation of "total amount paid" were complete.

**Consequence:** Any downstream consumer of this provenance graph (e.g., a loan servicer, an auditor reconciling borrower ledgers, or a regulator verifying total cash collected) would understate the borrower's true total outlay by exactly the omitted escrow total (2400.00 in this trace), a material and systematically reproducible discrepancy for every amortization run using this pipeline, not a one-off rounding artifact. Given the escrow variables are deliberately modeled per-month with full metadata and even rolled into their own auditable subtotal, the omission is not a plausible oversight in variable declaration — it is a targeted omission at the final aggregation step.