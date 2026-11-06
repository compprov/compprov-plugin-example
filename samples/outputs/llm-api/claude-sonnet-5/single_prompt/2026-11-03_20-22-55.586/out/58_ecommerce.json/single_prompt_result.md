# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 93.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud — Lineage Disconnection and Context Substitution**: 93/100 — CONFIRMED. The correctly computed sales tax is discarded and silently replaced by an unrelated root constant at the final summation step.
- **Precision and Scale Tampering**: 10/100 — Arithmetic under the declared `MathContext` (DECIMAL64, HALF_EVEN) replays exactly at every step in the legitimate chain; only a minor, non-malicious stylistic note (no final rounding to currency precision) is observed.
- **Semantic Type and Context Cast**: 35/100 — The substitute variable's business label ("Tax") does not correspond to the rate declared elsewhere in the graph (8% CA), constituting a secondary semantic contradiction riding on the same substitution.

---

#### Anomaly Localization (Topological & Provenance Fraud — Context Substitution)
- **Computed/abandoned node**: `o_25` ("Sales tax") — produced by `op_11`: `o_23 (Taxable amount, 207.371600) * i_24 (Tax rate 8%, California) = 16.58972800`. This is mathematically exact and fully provenance-linked back through the subtotal, two sequential discounts, and shipping fee.
- **Substitute node**: `i_26` ("Tax") — a **root** variable (no upstream operation, no metadata, no declared MathContext), value `14.930755200`.
- **Terminal consumer**: `op_12`: `o_27 ("Order total") = o_23 (207.371600) + i_26 (14.930755200) = 222.302355200`.
- Per the structural reference data, the leaf set is exactly `[o_25, o_27]`. `o_25` is a dead-end: it is fully computed from the graph's own causal chain (unit prices → quantities → line totals → subtotal → SAVE10 → LOYALTY5 → + shipping → × 8% tax rate) yet **never reaches** the terminal output `o_27`. Instead, `op_12` pulls in `i_26`, an uncomputed, unexplained constant that happens to occupy the identical argument slot (`b`, added to the same `o_23`) that the properly computed `o_25` should have occupied.
- Reverse-engineering `i_26`: `14.930755200 / 207.371600 = 0.072`, i.e., an effective **7.2%** rate — not the **8%** rate explicitly labeled on `i_24` ("Tax rate (8%, California)") and used to produce the (abandoned) `o_25`.

#### Details (Topological & Provenance Fraud)
The attack passes every local and type check: `i_26` is a well-typed `BigDecimal`, the `add` operation at `op_12` replays perfectly against its own inputs, and `o_23` is legitimately multi-used (once to seed the real tax calculation, once to seed the final total) — exactly the pattern the reference data flags as requiring manual review rather than dismissal. The violation only becomes visible when tracing the *role* each variable plays: the graph computes a fully provenance-backed, correctly-rounded sales tax (`o_25`) under its own declared 8% rate, then bypasses it entirely in favor of an opaque root constant that encodes a lower, undocumented ~7.2% effective rate. This is the canonical Lineage Disconnection / Context Substitution signature: a substitute root reaches the terminal result in place of the graph's own computed value for the same role (tax owed on the taxable amount).

**Impact**: The legitimate order total should be `o_23 + o_25 = 207.371600 + 16.589728 = 223.961328`, but the reported `Order total` is `222.302355200` — understating the bill (and the sales tax remitted) by `1.658972800` per order, roughly 0.75% of the order value. In a scalable e-commerce context (per-transaction, high-volume), this is not a one-off rounding artifact but a systematic, repeatable short-fall in collected/remitted sales tax across every order processed by this pipeline — a material, auditable discrepancy between the declared tax policy (8%, California) and the actual amount flowing to the final output.

#### Anomaly Localization (Semantic Type and Context Cast)
- `i_26` ("Tax") consumed at `op_12` in place of the properly derived `o_25` ("Sales tax") despite representing a different effective rate (7.2% vs. the graph's declared 8% at `i_24`).

#### Details (Semantic Type and Context Cast)
The node is technically a valid `BigDecimal` and the `add` at `op_12` is type-safe and mathematically self-consistent, so naive schema/type validation and local replay both pass. However, the business meaning asserted by the graph itself ("Tax rate (8%, California)" governing the taxable base) is silently contradicted by the value actually consumed under the generic label "Tax" at the point that reaches the terminal result. This is the same underlying exploit as the Vector 1 finding viewed through the metadata lens: a value is consumed as though it were "the tax" already computed under the stated policy, when it is not reconcilable with that policy. No explicit reconciling metadata is present on `i_26` to justify the discrepancy, so the contradiction stands.

#### Precision and Scale Tampering — Supplementary Note
All operations within the legitimate computation chain (`op_1`–`op_11`) reproduce exactly against the declared `MathContext` (precision 16, HALF_EVEN), with scale growth that is internally consistent (e.g., `205.1280`, `194.871600`, `207.371600`, `16.58972800` all match expected product/sum scales) — no truncation, salami-slicing, or inappropriate rounding-mode usage was found in this chain. The only mild observation is that the pipeline never rounds back down to 2-decimal currency precision before producing the final `Order total` (`222.302355200`, 9 decimal places) — not itself a confirmed violation, but worth flagging as a hygiene gap, independent of and far less severe than the Vector 1 substitution finding, which is the dominant risk driver for this graph.