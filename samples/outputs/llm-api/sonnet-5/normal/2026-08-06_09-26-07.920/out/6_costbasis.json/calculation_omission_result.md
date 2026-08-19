# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

## Anomaly Localization (If Detected)
No qualifying anomaly was found. Every variable that should feed into the final reported result (`o_18`, Realized gain/loss) does so through a fully traceable, arithmetically consistent chain:

- Lot costs: `i_2*i_3=o_4` (100×42.50=4250.00), `i_5*i_6=o_7` (150×38.25=5737.50), `i_8*i_9=o_10` (75×51.00=3825.00) — all via `op_1..op_3`.
- Aggregate totals: `addBulk(i_2,i_5,i_8)=o_11` (325 total shares) and `addBulk(o_4,o_7,o_10)=o_12` (13812.50 total cost) — via `op_4`, `op_5`.
- Weighted-average cost: `o_12/o_11=o_13` (13812.50/325=42.50) — via `op_6`.
- Sale proceeds: `i_14*i_15=o_16` (120×47.75=5730.00) — via `op_7`.
- Cost basis of shares sold: `i_14*o_13=o_17` (120×42.50=5100.00) — via `op_8`.
- Realized gain/loss: `o_16-o_17=o_18` (5730.00-5100.00=630.00) — via `op_9`, the sole leaf/final result.

All intermediate outputs (`o_4, o_7, o_10, o_11, o_12, o_13, o_16, o_17`) are consumed by a downstream operation; none are stranded as dead-ends. The only leaf is `o_18`, which is the pipeline's intended final output, consistent with a complete computation.

## Details
A Calculation Omission attack requires a mandatory adjustment (fee, tax, correction, cross-check credit, etc.) that is computed correctly somewhere in the graph but silently excluded from the final aggregation — typically surfacing as an unconsumed intermediate variable or a final operation whose argument list omits a component that its own semantics demand.

In this graph, there is no such component. The pipeline models a textbook weighted-average cost-basis / partial-sale gain calculation:
1. Compute per-lot costs.
2. Aggregate total shares and total cost.
3. Derive weighted-average cost per share.
4. Compute sale proceeds and cost basis of the shares actually sold (using the weighted-average, not any single lot's price — correct methodology).
5. Subtract cost basis from proceeds to get realized gain/loss.

Every numeric value recomputes exactly under standard BigDecimal arithmetic at the declared `MathContext` (precision 16, HALF_EVEN), and every declared variable — including all three lots, both aggregate totals, and the weighted-average — has an active causal path into `o_18`. There are no additional named variables (e.g., a separately computed transaction fee, broker commission, wash-sale adjustment, or tax withholding) that appear in the variable list but are absent from `op_9`'s arguments. The single leaf (`o_18`) is the expected terminal output, not an orphaned side calculation.

Given the absence of any qualifying mandatory-adjustment variable that is computed but excluded, and the full internal arithmetic consistency of the graph, this trace does not exhibit the Calculation Omission pattern as defined. Confidence is high but not absolute, since a sufficiently sophisticated omission (e.g., an adjustment that was never even instantiated as a variable, rather than computed-and-dropped) would be undetectable from graph structure alone — such an omission would leave no trace in this CPG format by construction.