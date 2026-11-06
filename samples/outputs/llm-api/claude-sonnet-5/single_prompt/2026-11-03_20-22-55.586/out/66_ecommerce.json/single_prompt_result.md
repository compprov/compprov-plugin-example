# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 93.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud** — Score: 5/100. No omission, substitution, or double counting detected; all roots and intermediate outputs are consumed exactly once along a single causal chain into `o_27`, and the only multi-use variables (`i_3`, `o_24`) are legitimate shared-constant / net-then-reused-for-total patterns.
2. **Precision and Scale Tampering** — Score: 93/100. Confirmed: a directional, coarse-precision `MathContext` (`i_2`, precision=2, rounding=DOWN) is silently substituted for the graph's standard `MathContext` (`i_1`, precision=16, HALF_EVEN) at exactly one step (`op_9`), truncating a $195-scale intermediate value down to a 2-significant-figure number and destroying ~$4.87 of value in that single step.
3. **Semantic Type and Context Cast** — Score: 5/100. Business labels (Subtotal → Discount multipliers → Taxable amount → Sales tax → Order total) align consistently with the arithmetic performed; shipping fee's taxable/jurisdiction tags are honored correctly in the taxable-amount aggregation.

Overall risk is driven by the confirmed Precision and Scale Tampering finding (Vector 2).

#### Anomaly Localization (Precision and Scale Tampering)
- **Operation `op_9`** (`multiply`, formula `(a*b)mc`): consumes `a = o_19` ("Subtotal after LOYALTY5" input, value 205.1280), `b = o_21` (0.95), and critically `mc = i_2`.
- **`i_2`** ("Computation precision", `java.math.MathContext{precision=2, roundingMode=DOWN}`) — a root variable used **only** in `op_9`, nowhere else in the graph.
- Every other arithmetic step in the pipeline (`op_1`–`op_8`, `op_10`–`op_12`) uses `i_1` (`MathContext{precision=16, roundingMode=HALF_EVEN}`), the graph's de-facto standard context for all money-scale computations.
- Resulting variable **`o_22`** ("Subtotal after LOYALTY5") is reported as `1.9E+2` (i.e., 190), and this truncated value flows forward into `o_24` (Taxable amount, 202.50), `o_26` (Sales tax, 16.20), and finally into the terminal **`o_27`** ("Order total", 218.7000).

**Flow of the attack:** Subtotal (227.92) → SAVE10 discount (×0.90) = 205.1280 → LOYALTY5 discount (×0.95) should equal 194.8716 under the graph's own standard context (`i_1`), but is instead computed under `i_2` and reported as 190 → +shipping (12.50) = 202.50 → +8% tax (16.20) = **218.70 (reported Order Total)**.

#### Details (Precision and Scale Tampering)
Recomputing the full pipeline with the graph's own standard context (`i_1`, precision 16, HALF_EVEN) consistently applied at every multiply/add/subtract step:
- Subtotal = 89.97+29.00+59.00+49.95 = 227.92 (matches)
- After SAVE10 = 227.92×0.90 = 205.1280 (matches reported `o_19`)
- After LOYALTY5 = 205.1280×0.95 = **194.8716** (reported `o_22` = 190 — Δ = 4.8716)
- Taxable amount = 194.8716+12.50 = **207.3716** (reported `o_24` = 202.50 — Δ = 4.8716)
- Sales tax = 207.3716×0.08 = **16.589728** (reported `o_26` = 16.2000 — Δ = 0.389728)
- Order total = 207.3716+16.589728 = **223.961328** (reported `o_27` = 218.7000 — Δ ≈ **5.2613**, ~2.35% of the correct total)

The mechanism works because `op_9` replays perfectly against its own *locally declared* context `i_2` — local verification of "does the output match MathContext-rounding of the inputs" passes with zero discrepancy, so naive replay-based audits see no error. The violation only surfaces when the declared context itself is checked against the invariant that it must not be "markedly coarser than the rest of the graph" nor use a directional (DOWN) rounding mode inconsistent with the HALF_EVEN convention used everywhere else for this money-valued pipeline. Precision=2 significant digits is far coarser than the cents-level resolution (effectively 5-6 significant digits) required for an order in the $100–$300 range, and DOWN (truncation toward zero) guarantees the skimmed amount is always lost in the same direction, never corrected.

**Impact:** This single substitution drains approximately $5.26 (≈2.3%) from the reported Order Total relative to the value the graph's own declared standard context would have produced. Per the Materiality Override invariant, this is large enough to constitute a confirmed material precision flaw regardless of whether the pipeline is cyclic; if this order-total computation template is re-used per transaction (as its generic SKU/discount/tax structure suggests), the same ~2.3% skim would recur on every order processed by this logic, compounding the severity further into a systemic value-draining defect rather than an isolated rounding artifact.