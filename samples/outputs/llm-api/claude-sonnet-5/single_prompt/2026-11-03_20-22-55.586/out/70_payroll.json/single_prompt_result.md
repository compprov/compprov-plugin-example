# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 82.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: Score 5/100 — No omission, substitution, or double-counting detected; every root is consumed exactly where expected and the sole leaf (`o_21`) legitimately aggregates all upstream branches.
- **Precision and Scale Tampering**: Score 82/100 — Confirmed finding: `op_7` silently swaps the graph's standard `MathContext` (precision 16, HALF_EVEN) for a drastically coarser, directional-rounding context (precision 2, DOWN) that corrupts the Bracket 2 tax figure and propagates into the reported Net Pay.
- **Semantic Type and Context Cast**: Score 5/100 — Business labels, units, and consumption order are internally consistent (taxable income correctly feeds both bracket splitting and state-tax computation; net pay correctly nets federal and state tax exactly once each); no C_source != C_target contradiction found.

**Overall risk_score = 82** (driven by the Precision and Scale Tampering finding, the most severe confirmed violation).

---

#### Anomaly Localization (Precision and Scale Tampering)
- **Variables**: `i_1` (MathContext, precision=16, HALF_EVEN — used pervasively), `i_2` (MathContext, precision=2, DOWN — used only once), `o_14` (Bracket 2 portion = 1650.00), `o_16` (Bracket 2 tax), `o_17` (Federal tax withheld), `o_20` (After federal withholding), `o_21` (Net pay, terminal output).
- **Operation**: `op_7` — `multiply(o_14, i_12, mc=i_2)` → `o_16`.
- **Flow**: Every other arithmetic step in this graph (`op_1, op_2, op_3, op_5, op_6, op_8, op_9, op_10, op_11`) uses `i_1` (precision 16, HALF_EVEN) — a precision adequate for currency arithmetic with round-to-nearest convention. `op_7` alone substitutes `i_2`, a context with only **2 significant digits** and the directional **DOWN** (truncating) rounding mode. The exact product `1650.00 * 0.22 = 363.00` is forced to 2 significant digits under truncation, yielding **360** (serialized as `3.6E+2`) instead of **363.00** — a `Δ = 3.00` discrepancy that replays perfectly against its own (tampered) context and therefore passes any local/unit check.
 - `o_16` (360, should be 363) feeds `op_8`: `o_17 = o_15 + o_16 = 200 + 360 = 560` (should be `563`).
 - `o_17` feeds `op_10`: `o_20 = o_9 - o_17 = 3650 - 560 = 3090` (should be `3087`).
 - `o_20` feeds `op_11`: `o_21 = o_20 - o_19 = 3090 - 182.50 = 2907.50` (should be `2904.50`).
 - The terminal reported Net Pay (`o_21 = 2907.50`) is therefore inflated by exactly **$3.00** relative to the mathematically correct computation using the graph's own standard context.

#### Details (Precision and Scale Tampering)
The mechanism exploits the gap between "this step replays exactly against its declared MathContext" (true — `op_7` is internally self-consistent) and "the declared MathContext is itself legitimate for the quantity and consistent with the rest of the pipeline" (false). `i_2`'s precision of 2 *significant digits* (not 2 decimal places) is a severe degradation wholly unsuited to currency math — it is markedly coarser than the `i_1` context used everywhere else in the graph — and its DOWN rounding mode is directional (always truncates toward zero) rather than the HALF_EVEN convention used consistently elsewhere. Per the task's invariants, a directional mode or degraded precision isolated to one step of an otherwise round-to-nearest pipeline, when it changes a value that reaches the reported result, is a confirmed finding regardless of local self-consistency. This is not a 1-ULP rounding-mode artifact (which could differ by at most 1 unit at the target scale) — the precision collapse from 16 to 2 significant digits produces a $3.00 error on a $563.00 tax figure (~0.5%), which both exceeds the 