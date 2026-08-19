# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 93.0

## Anomaly Localization

**Implicated variables:** `i_26` ("Restocking fee (revenue)", value 15.00), `i_24` ("Tax rate (8%, California)", value 0.08), `o_27` ("Tax on restocking fee", value 1.2000), `o_28` ("Restocking fee total", value 16.2000 — **unconsumed leaf**), `o_23` ("Taxable amount"), `o_25` ("Sales tax"), `o_29` ("Order total", value 223.96132800).

**Implicated operations:** `op_12` (multiply: `i_26` * `i_24` → `o_27`), `op_13` (add: `i_26` + `o_27` → `o_28`), `op_14` (add: `o_23` + `o_25` → `o_29`, the *final reported result*).

**Attack flow:**
1. The pipeline transparently computes a "Restocking fee (revenue)" (`i_26` = 15.00), correctly taxes it (`op_12` → `o_27` = 1.20), and correctly rolls it into a fully-formed "Restocking fee total" (`op_13` → `o_28` = 16.20). This is a complete, self-consistent sub-computation — nothing about its arithmetic is wrong.
2. However, the terminal aggregation that produces the pipeline's headline output, `op_14` (`add(o_23, o_25) → o_29`, "Order total"), only consumes `o_23` (Taxable amount) and `o_25` (Sales tax). It never references `o_28` (or `i_26`/`o_27` directly).
3. `o_28` is confirmed by graph traversal to be a **leaf node** — it is never consumed as an argument by any downstream operation. It dead-ends immediately after being computed.
4. The reported "Order total" (223.96132800) is therefore only `Taxable amount + Sales tax`, silently omitting the correctly-computed restocking fee and its associated tax (16.2000) that the variable's own name and role ("revenue" fee, with its own tax computed identically to the main sales tax via `i_24`) indicate should be part of the total amount charged/owed.
5. A complete order total should logically equal `o_23 + o_25 + o_28` (or equivalently `o_21 + i_22 + o_25 + o_28`), which would be `223.961328 + 16.20 = 240.161328`, not `223.96132800`.

## Details

This is a textbook Calculation Omission: the restocking fee is not fabricated or miscalculated in isolation — every intermediate figure (`o_27`, `o_28`) is arithmetically correct and even mirrors the tax treatment applied to the rest of the order (same `i_24` tax rate, same `mc` precision context), which is precisely what makes it look benign under casual review or a pure recomputation/replay check: every individual operation's math checks out. The fraud is purely structural — the mandatory component is computed but then never wired into the aggregation operation (`op_14`) that produces the pipeline's reported result (`o_29`).

Because `o_28` sits at a dead end (confirmed leaf, per structural traversal) while `o_29` is presented as "Order total" — implying a complete, final settlement figure — the pipeline reports an incomplete sum as though it were exhaustive. There is no annotation, comment, or metadata anywhere in the graph indicating that the restocking fee is intentionally excluded from the order total (e.g., no "informational only" or "non-billable" tag on `i_26`/`o_28`); its descriptor name ("Restocking fee (revenue)" / "Restocking fee total") strongly implies it is a billable amount that should increase the total owed.

**Impact:** The final reported "Order total" understates the true amount due by exactly the restocking fee total, 16.20 (7.2% of the naive total), consistently and reproducibly — the kind of surgical, low-visibility omission that would survive spot-checks of individual operations while systematically biasing the audited financial outcome.