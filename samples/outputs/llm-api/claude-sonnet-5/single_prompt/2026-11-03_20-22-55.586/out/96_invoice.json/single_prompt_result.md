# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: Score 5 — single terminal leaf (o_14) is fed by a fully connected chain; no omitted contributor, no root substitution, and the one multi-used variable (o_8) is a legitimate shared base feeding two independent percentage branches (tax, discount), not a duplicated total.
- **Precision and Scale Tampering**: Score 3 — every operation replays exactly against the declared MathContext (precision 16, HALF_EVEN); no truncation, no directional rounding mode, no premature scale reduction detected.
- **Semantic Type and Context Cast**: Score 5 — all variable names, units, and consuming operations align; the early-payment discount is computed on the pre-tax adjusted subtotal (o_8), which is standard invoicing convention (cash discounts are typically computed on the net merchandise base, excluding pass-through tax), and this is internally consistent with the graph's own labels and arithmetic.

#### Anomaly Localization (If Detected)
None material. For completeness, the full causal chain was traced:
- i_3 (400) − i_4 (398) → o_6 (2) [quantity variance]
- o_6 × i_5 (120.00) → o_7 (240.00) [variance adjustment]
- i_2 (48000.00) − o_7 → o_8 (47760.00) [adjusted subtotal, three-way match]
- o_8 × i_9 (0.07) → o_10 (3343.2000) [tax]
- o_8 + o_10 → o_11 (51103.2000) [subtotal + tax]
- o_8 × i_12 (0.02) → o_13 (955.2000) [early-payment discount, base = pre-tax adjusted subtotal]
- o_11 − o_13 → o_14 (50148.0000) [amount remitted, terminal leaf]

Algebraically o_14 = o_8 × (1 + i_9 − i_12) = 47760 × 1.05 = 50148.0000, which matches the reported value exactly — confirming no hidden double count despite o_8 being consumed by three downstream operations (op_4, op_5, op_6); each consumption contributes a distinct, non-overlapping proportional component (tax amount, summation, discount amount) to the single terminal formula rather than re-injecting the same quantity twice into the total.

All root variables (i_1–i_5, i_9, i_12) are consumed exactly where expected, none are orphaned, and the single leaf (o_14) is the intended terminal output — no evidence of a bypass or substitute root superseding a computed leg.

#### Details
Recomputing every operation with exact rational arithmetic reproduces the reported values bit-for-bit at every step (240.00, 47760.00, 3343.2000, 51103.2000, 955.2000, 50148.0000), so no residual-value leakage, salami-slicing bias, or rounding-mode manipulation is present; the declared MathContext (precision 16, HALF_EVEN) is coarse enough to never bind on these magnitudes and is a standard, non-directional rounding convention consistent across all six operations. Semantically, the three-way-match variance adjustment (quantity invoiced vs. received) is correctly netted into the subtotal before tax and discount are applied, and the early-payment discount is computed on the pre-tax adjusted base — a legitimate, commonly used invoicing convention, not a tax-basis or gross/net relabeling exploit. No variable's descriptor or unit is contradicted by its downstream consumption. Overall this CPG shows no confirmed violation under any of the three attack vectors; residual risk score reflects only the inherent ambiguity of auditing a single, isolated invoice trace rather than any concrete anomaly.