# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 90.0

#### Per-Vector Verdict

1. **Topological & Provenance Fraud** — Score: 5/100. No omission, lineage substitution, or double counting detected. Every root reaches the single terminal leaf `o_26` exactly once through its intended path; the two multi-use nodes (`i_2`, `o_23`) are legitimate (two independent discount-complement calculations, and a taxable base reused once to derive tax and once to sum into the final total).

2. **Precision and Scale Tampering** — Score: 5/100. The MathContext `i_1` (precision=16, HALF_EVEN) is applied uniformly to every operation. Exact rational recomputation of every step (line totals, subtotal, two sequential discounts, shipping addition, tax multiply, final add) matches the reported values bit-for-bit, including scale growth (e.g., 227.92 × 0.90 = 205.1280, 205.1280 × 0.95 = 194.871600, etc.). No truncation, premature rounding, salami-slicing pattern, or downcasting is present.

3. **Semantic Type and Context Cast** — Score: 90/100. The variable `i_24` is explicitly labeled "Tax rate (8%, California)" but its stored numeric payload is `0.02` (2%), not `0.08` (8%). This is an explicit, in-graph contradiction between the variable's own declared business meaning and its numeric content, which then flows — technically valid, mathematically replayable — into the terminal order total, materially understating the tax and the final charged amount.

---

#### Anomaly Localization — Semantic Type and Context Cast
- **Variable**: `i_24` — `descriptor.name = "Tax rate (8%, California)"`, `valueClass = java.math.BigDecimal`, `value = "0.02"`.
- **Consuming operation**: `op_11` (`multiply`, formula `(a*b)mc`) — `a = o_23` (Taxable amount, 207.371600), `b = i_24` (0.02), `mc = i_1` → `resultId = o_25` (Sales tax, 4.14743200).
- **Downstream propagation**: `o_25` flows into `op_12` (`add`) together with `o_23`, producing the terminal leaf `o_26` ("Order total" = 211.51903200).
- **Flow summary**: Root `i_24` is declared and documented as an 8% California statutory tax rate, but the numeric value actually encoded and consumed by `op_11` is 0.02 (2%). The arithmetic of `op_11` and `op_12` replays perfectly against the *stored* value, and the `BigDecimal` type is preserved end-to-end, so all technical/type-level and local-replay checks pass. The business-meaning contract implied by the variable's own name/metadata ("8%, California") is violated by its own payload before it ever leaves the root node, and that falsified rate is what determines the final "Order total."

#### Details
The attack exploits exactly the gap this vector targets: technical type continuity (`BigDecimal` in, `BigDecimal` out) and mathematical correctness of each step are both intact, so schema validation and step-by-step replay give a false sense of assurance. The semantic failure is embedded in the root variable itself — its descriptor commits to an 8% California sales-tax rate (a specific, auditable, jurisdiction-tied regulatory figure), while the value actually wired into the tax-multiply operation is 2%, a 6-percentage-point (75%) understatement of the rate relative to its own label. Because no other variable in the graph supplies or cross-checks the "real" 8% rate, there is no reconciling metadata anywhere in the graph that resolves this contradiction — the burden-of-proof bar for a benign explanation is not met.

Consequence: the reported `Sales tax` (`o_25` = 4.14743200) and `Order total` (`o_26` = 211.51903200) are both understated. Recomputing with the rate the metadata claims (8% of the 207.371600 taxable base) yields a sales tax of ~16.589728 and a correct order total of ~223.961328 — a discrepancy of roughly $12.44 per order, i.e., about 5.9% of the true total, silently diverted away from the reported tax liability on every order this logic is applied to. This is a textbook Semantic Type and Context Cast: the numeric/business meaning was altered while every structural and type-level control continued to validate cleanly.