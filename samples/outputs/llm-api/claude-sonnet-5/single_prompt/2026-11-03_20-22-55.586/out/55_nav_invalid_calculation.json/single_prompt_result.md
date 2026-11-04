# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 93.0

#### Per-Vector Verdict

1. **Topological & Provenance Fraud**: Score 8/100 — No omission, substitution, or double counting detected; all 8 roots are consumed exactly where expected, the two multi-used rate variables (i_2, i_3) legitimately price two distinct balances each, and all 5 conversions reach the single terminal leaf o_14 via op_6.
2. **Precision and Scale Tampering**: Score 93/100 — Confirmed severe arithmetic/conservation violation: the final `addBulk` (op_6) result does not equal the sum of its own declared arguments, off by exactly $10,000.00. Additional secondary finding: a consistent downward-truncation bias on 3 of 5 currency conversions.
3. **Semantic Type and Context Cast**: Score 5/100 — Clean; every rate's `from`/`to` currency pairing matches the currency of the balance it converts, with no relabeling or business-context drift observed.

**Overall risk_score reflects the maximum (most severe) finding: Vector 2, the $10,000 unexplained inflation of the reported NAV.**

---

#### Anomaly Localization (Precision and Scale Tampering — Materiality Override)
Implicated nodes: `op_6` (addBulk), its arguments `o_9, o_10, o_11, o_12, o_13`, and its result `o_14` ("Assets sum").

Recomputing op_6 exactly from its own declared arguments:
- o_9 = 146948.10
- o_10 = 48624.45
- o_11 = 538.22
- o_12 = 12312.36
- o_13 = 223326.04

Exact sum = 431749.17

Reported `o_14` = **441749.17**

Δ = |431749.17 − 441749.17| = **10,000.00 USD** — a flat, round-number discrepancy equal to roughly 2.3% of the correct total. This is not an artifact of rounding mode, scale conversion, or cumulative sub-cent drift (those effects are bounded to fractions of a cent per operation, see below); it is a direct violation of the addBulk operation's own stated formula (`a+b0+...+bn`), meaning the reported NAV is inflated by $10,000 relative to the sum of its own documented contributors. No sixth argument, adjustment, or fee variable exists anywhere in the graph to explain the gap — the terminal output simply does not reconcile with its own operation's inputs.

#### Details
Because every individual conversion (op_1–op_5) replays correctly against its own inputs in isolation, and because the addBulk operation's argument list is syntactically well-formed (5 named amounts, all present and valid `Amount` types), a schema-level or type-level validator would see nothing wrong. Only an exact-precision recomputation of the aggregation step itself exposes the fraud. The practical impact is a materially overstated Net Asset Value: investors, auditors, or counterparties relying on `o_14` would be shown a NAV that is $10,000 higher than the fund's own documented asset conversions support — a serious integrity failure regardless of whether this computation recurs periodically (the Materiality Override invariant applies: a single-execution error of this magnitude is high risk independent of cyclicity).

---

#### Anomaly Localization (Precision and Scale Tampering — Secondary: Truncation Bias)
Implicated nodes: `op_2` (resultId `o_10`), `op_3` (resultId `o_11`), `op_5` (resultId `o_13`).

- op_2: 23.34 ETH × 2083.31 = 48624.4554 exactly → correctly rounds to 48624.46 (HALF_UP/HALF_EVEN); reported `o_10` = 48624.45 (truncated down).
- op_3: 532.9 USDC × 1.01 = 538.229 exactly → correctly rounds to 538.23; reported `o_11` = 538.22 (truncated down).
- op_5: 221114.9 USDC × 1.01 = 223326.049 exactly → correctly rounds to 223326.05; reported `o_13` = 223326.04 (truncated down).

By contrast, op_1 (146948.1036) and op_4 (12312.3621) round down under either convention (third decimal <5), so they show no discrepancy — only the three cases where standard rounding would have rounded *up* exhibit the truncation, a one-sided pattern.

#### Details
None of these conversions carry a declared `MathContext` argument, so the graph provides no explicit rounding-mode ground truth, and each individual 1-cent delta sits at the edge of the 