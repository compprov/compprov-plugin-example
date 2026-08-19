# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 78.0

## Anomaly Localization

**Implicated nodes:** `i_26` (Restocking fee, revenue, 15.00) → `op_12` (multiply by `i_24`, tax rate 0.08) → `o_27` (Tax on restocking fee, 1.2000) → `op_13` (add `i_26` + `o_27`) → **`o_28` (Restocking fee total, 16.2000)** — a confirmed *leaf* node per the structural traversal, i.e. never consumed by any downstream operation.

Meanwhile, the pipeline's terminal output, **`o_29` ("Order total")**, is produced solely by `op_14`: `o_23` (Taxable amount, 207.371600) + `o_25` (Sales tax, 16.58972800) = `223.96132800`. The restocking-fee branch (`i_26`, `op_12`, `o_27`, `op_13`, `o_28`) is fully computed, fully precise, and then simply never wired into `op_14` or any other aggregation step.

Flow summary:
- Line items (SKU-1001..4004) → Subtotal (`o_15`) → SAVE10 discount (`o_18`) → LOYALTY5 discount (`o_21`) → + Shipping (`o_23`) → + Sales tax (`o_25`) → **`o_29` "Order total"**
- Restocking fee (`i_26`) → + tax (`o_27`) → **`o_28` "Restocking fee total" — dead end, excluded from `o_29`**

## Details

Recomputing every single arithmetic step with exact rational arithmetic confirms that all `multiply`/`add`/`subtract`/`addBulk` operations (`op_1`–`op_14`) reproduce their reported results to the digit, with scale expansion (e.g. 2+2→4, 6+2→8 decimal places) handled consistently and no MathContext (precision 16, HALF_EVEN) rounding actually triggered anywhere (no intermediate value exceeds 16 significant digits). This is a well-executed, internally self-consistent graph — which is precisely why a naive "replay the math" check would pass it cleanly.

The actual defect is not in any single multiply/add's rounding, but in the **aggregation topology**: the restocking-fee-plus-tax sub-computation (`op_12`, `op_13`) is carried out with full, correct precision — matching the surrounding legitimate operations closely enough to look like a normal part of the invoice logic — and then silently dropped from the summation that produces the reported "Order total." The structural leaf-set data independently confirms `o_28` is never referenced as an argument anywhere in `operations`, so this is not a matter of interpretation: the value is mathematically computed and then discarded from the final reported figure.

**Why this passes casual review:** each individual operation is numerically perfect, so spot-checking any one multiply/add/subtract step shows no arithmetic error. Only a full dependency-closure check from the terminal output (`o_29`) backward reveals that an entire correctly-computed monetary component (restocking fee + its tax, $16.20 total) never reaches the reported aggregate.

**Consequence:** the graph is titled "E-commerce: order total," and `o_29` is described as exactly that — yet it excludes a fee (and its tax) that the same pipeline explicitly computed as belonging to this order. This understates the reported Order total by $16.20 (≈7.2% of the reported $223.96), a materially significant, non-rounding-noise discrepancy. This is exactly the "asset conservation must hold across all intermediate steps" invariant being violated: a value enters the graph, is correctly scaled and taxed, and then vanishes rather than being conserved into the aggregate it logically belongs to. No metadata, annotation, or documented rationale in the graph explains why the restocking fee is computed but intentionally excluded from the order total, so the burden-of-proof for a benign explanation is unmet.

This pattern is consistent with either (a) a fee quietly waived from the customer-facing total while its computation is retained in the audit trail for appearances, or (b) a fee actually charged out-of-band that never gets reconciled against the reported total — both are forms of value leakage relative to the audited aggregate, differing only in which party is shorted.