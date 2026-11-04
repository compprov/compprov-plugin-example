# Summary
- **Verdict**: CLEAN
- **Risk score**: 3.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: Score 2/100 — No omission, substitution, or double counting detected; all roots are consumed, the single leaf (o_20) is the genuine terminal output, and every intermediate variable traces cleanly into it exactly once per causal role.
- **Precision and Scale Tampering**: Score 3/100 — A single, consistently-applied MathContext (precision=16, HALF_EVEN) is used across all 11 operations; exact rational recomputation of every step matches the reported value with zero delta; no premature truncation, no directional rounding modes, no downcasting.
- **Semantic Type and Context Cast**: Score 2/100 — Business labels (Gross pay, Pretax deductions, Taxable income, Bracket portions/taxes, Federal/State tax withheld, Net pay) align consistently with the arithmetic performed at each step; no node consumes a variable under a conflicting business definition.

**Overall risk_score: 3/100 — Clean graph.**

#### Anomaly Localization (If Detected)
None. Full trace for completeness:
- o_4 (Gross pay) = i_2 (3500.00) + i_3 (500.00) = 4000.00 — exact.
- o_7 (Pretax deductions) = i_5 (200.00) + i_6 (150.00) = 350.00 — exact.
- o_8 (Taxable income) = o_4 − o_7 = 3650.00 — exact; correctly nets pretax deductions out of gross before further computation, so they are neither omitted nor double counted downstream.
- o_12 (Bracket 1 portion) = min(o_8, i_9) = 2000.00; o_13 (Bracket 2 portion) = o_8 − o_12 = 1650.00. o_12/o_13 sum back to o_8 — a legitimate proportional split, not duplication.
- o_14 = o_12 × i_10 = 200.0000; o_15 = o_13 × i_11 = 363.0000; o_16 (Federal tax) = o_14 + o_15 = 563.0000 — exact bracket-tax aggregation.
- o_18 (State tax) = o_8 × i_17 = 182.5000 — exact.
- o_19 (After federal withholding) = o_8 − o_16 = 3087.0000; o_20 (Net pay) = o_19 − o_18 = 2904.5000 — exact; equivalent to Gross − Pretax − FederalTax − StateTax, each subtracted exactly once.

Multi-use variables o_8 (feeds op_4, op_5, op_9, op_10) and o_12 (feeds op_5, op_6) are structurally necessary (tax-bracket split plus independent downstream consumption of the common base), and reconciliation confirms no value is counted twice into the final net-pay total.

#### Details
All root variables (i_1, i_2, i_3, i_5, i_6, i_9, i_10, i_11, i_17) are consumed exactly where expected; the sole leaf (o_20, Net pay) is the intended terminal result and every computed intermediate (gross pay, pretax deductions, taxable income, bracket splits, bracket taxes, federal/state withholding) has a traceable, singular contribution to it. A single MathContext (precision 16, HALF_EVEN) is applied uniformly across every arithmetic operation, and exact rational replay of each step shows zero deviation from the reported values — no salami-slicing, no scale truncation, no mixed rounding conventions. Business semantics (names/meta) are consistent with the operations performed on them at every step (e.g., "Taxable income" is genuinely gross minus pretax deductions; "Federal tax withheld" is genuinely the sum of the two bracket taxes; "Net pay" is genuinely taxable income minus both federal and state withholding) with no unexplained relabeling, unit shifts, or implicit domain reinterpretation. No evidence of tampering was found under any of the three attack vectors; the low residual score reflects standard audit uncertainty rather than any concrete finding.