# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: 5/100 — No omission, substitution, or double counting detected; the two flagged multi-use variables (i_2, o_23) are legitimate shared constants/intermediate bases.
- **Precision and Scale Tampering**: 5/100 — All operations replay exactly under the declared DECIMAL64 (precision 16, HALF_EVEN) MathContext; no rounding events, truncations, or scale manipulations occur anywhere in the chain.
- **Semantic Type and Context Cast**: 5/100 — Business meaning (unit price → line total → subtotal → discounted subtotal → taxable base → tax → order total) is preserved consistently end-to-end; the taxable-shipping inclusion is explicitly justified by its own metadata and matches the tax jurisdiction/rate used.

#### Anomaly Localization (If Detected)
No confirmed anomalies were localized. Items reviewed and cleared:
- **i_2 ("1.0") reused in op_6 and op_8**: Used only as the identity constant to derive two distinct discount complements (0.90 for SAVE10, 0.95 for LOYALTY5). Each consumption produces a different, independently-used result (o_17, o_20); no value is counted twice into the same total.
- **o_23 ("Taxable amount") reused in op_11 and op_12**: First consumed to compute sales tax (op_11: taxable_amount × rate), then consumed again in op_12 to add that tax back onto the same base (op_12: taxable_amount + tax) to form the order total. This is the standard total = base + tax pattern, not duplication of the base amount itself within the total.
- **Sequential discount stacking (SAVE10 then LOYALTY5)**: Multiplicative stacking (×0.90 then ×0.95, net ×0.855) is an explicit, auditable two-step transformation with distinct named rates/multipliers for each promotional code; both legs are fully wired into the terminal result via o_18→o_21→o_23→o_26. No root bypasses this chain, and no leaf other than the terminal o_26 exists, so no mandatory contributor is missing from the final result.
- **Taxable base composition (o_21 + i_22 shipping)**: Shipping fee carries explicit meta (`taxable: true`, `jurisdiction: California`) that matches the downstream tax-rate variable ("Tax rate (8%, California)"), reconciling the inclusion of shipping in the taxable base — this is a graph-internal justification, not an unsupported assumption.

#### Details
Recomputing every operation with exact rational arithmetic reproduces each reported value bit-for-bit:
- Line totals: 29.99×3=89.97, 14.50×2=29.00, 59.00×1=59.00, 9.99×5=49.95 → Subtotal 227.92 (exact, addBulk).
- SAVE10: 227.92×0.90=205.1280 (exact, scale 4 from BigDecimal scale addition).
- LOYALTY5: 205.1280×0.95=194.871600 (exact, scale 6).
- Taxable base: 194.871600+12.500000=207.371600 (exact).
- Tax: 207.371600×0.08=16.58972800 (exact, scale 8).
- Order total: 207.371600+16.58972800=223.96132800 (exact).
Every intermediate value fits comfortably within the declared 16-significant-digit DECIMAL64 context, so no rounding was ever actually invoked — the HALF_EVEN mode is never exercised and therefore cannot be shown to drain value, and no directional (DOWN/UP/FLOOR/CEILING) mode or coarser precision appears anywhere. All root variables are consumed, the sole leaf (o_26) is the genuine terminal output, and business metadata (taxable flag, jurisdiction, named discount codes) is consistent with how each variable is actually used downstream. No evidence in the graph supports a tampering narrative under any of the three vectors; this CPG presents as a clean, internally consistent e-commerce order-total computation.