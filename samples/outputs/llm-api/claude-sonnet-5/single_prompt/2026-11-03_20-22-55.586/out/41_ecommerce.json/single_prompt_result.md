# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 80.0

#### Per-Vector Verdict

1. **Topological & Provenance Fraud — Calculation Omission**: **CONFIRMED (Score: 80)** — A fully-computed revenue component ("Restocking fee total", `o_28`) is built, taxed, and totaled, then silently dropped before the terminal `Order total` (`o_29`).
2. **Precision and Scale Tampering**: **CLEAN (Score: 2)** — Every operation replays exactly against its declared `MathContext` (DECIMAL64, HALF_EVEN, precision 16); scale propagation is textbook BigDecimal behavior with zero residual leakage at any step.
3. **Semantic Type and Context Cast**: **CLEAN (Score: 5)** — No variable's declared business meaning is silently remapped; the restocking-fee/tax chain is internally consistent (same tax rate, no unit/jurisdiction contradiction), and the omission above is topological, not semantic.

---

#### Anomaly Localization (Topological & Provenance Fraud — Calculation Omission)

- **Implicated nodes**: `i_26` ("Restocking fee (revenue)"), `op_12` (multiply, tax on restocking fee), `o_27` ("Tax on restocking fee" = 1.2000), `op_13` (add, i_26+o_27), `o_28` ("Restocking fee total" = 16.2000) — and the terminal node it fails to reach, `o_29` ("Order total" = 223.96132800), produced solely by `op_14` from `o_23` (Taxable amount) and `o_25` (Sales tax).

- **Flow reconstruction**:
 1. `i_26` (15.00, restocking fee revenue) is fed into `op_12` together with the jurisdiction's tax rate `i_24` (0.08) to compute `o_27` = 1.2000 (tax owed on the fee).
 2. `op_13` adds `i_26` + `o_27` to produce `o_28` = 16.2000, explicitly labeled **"Restocking fee total"** — i.e., the fully tax-inclusive amount that should be charged/collected.
 3. `o_28` is never consumed by any downstream operation. It is a dead-end leaf per the structural reference data.
 4. Meanwhile, `op_14` computes the terminal `Order total` (`o_29`) strictly as `o_23 (Taxable amount) + o_25 (Sales tax)` = 207.371600 + 16.589728 = 223.961328, with no path back to `i_26`/`o_27`/`o_28`.

- **Why this is a violation, not a benign side-computation**: The variable is not a neutral "reference only" output — its own name ("...total") and its construction (principal + its own tax, using the same `mc`/tax-rate context as every other order component) mark it as a finished, chargeable amount meant to be rolled into the order's grand total, exactly like the shipping fee (`i_22`) is rolled into `o_23`. Unlike the shipping fee, which is explicitly merged into the taxable base and thus the final total, the restocking fee's fully-computed total is built and then discarded.

#### Details (Topological & Provenance Fraud)

The mechanism works because every individual step is mathematically perfect (`op_12` and `op_13` replay exactly against `i_1`'s MathContext) and type-safe (`BigDecimal` throughout) — a naive validator checking "does each operation compute correctly" and "are all types consistent" would find nothing wrong. The flaw is purely in the wiring: the subgraph computing the restocking fee's tax-inclusive total is topologically disconnected from the terminal aggregation (`op_14`), even though it clearly belongs there by name, by shared tax context, and by structural parallel to the shipping fee treatment.

Practical impact: the reported `Order total` of 223.96132800 omits a legitimate, fully-taxed charge of 16.2000, understating the true total by roughly 7%. In a real order-processing pipeline, this is exactly the pattern used to make a provenance trail *appear* to account for a fee (for audit/compliance purposes) while never actually billing or collecting it in the number that matters — the one presented to the customer or reconciled against revenue. This is a textbook Calculation Omission: the mandatory contributor is computed, fully formed, and verifiably dropped before the terminal output.

---

#### Supplementary Notes (Precision & Semantic vectors)

- All eight monetary operations (`op_1`–`op_14`, excluding the omission) reproduce their exact rational results under the declared DECIMAL64/HALF_EVEN context with correct BigDecimal scale propagation (scale = sum/max of operand scales at each step); no salami-slicing, truncation, or rounding-mode anomaly was found anywhere in the graph.
- No variable's `descriptor` metadata is contradicted by its downstream use: the shipping fee's `taxable=true`/`California` tags align with its consumption into the taxable base; the restocking fee and its tax use the same jurisdiction's tax rate (`i_24`) consistently. The sole issue is the broken edge into the terminal aggregation, which is a provenance/topological fault, not a mislabeling of business meaning.