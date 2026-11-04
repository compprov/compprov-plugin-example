# Summary
- **Verdict**: CLEAN
- **Risk score**: 3.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud** — Score: 2/100. No omission, substitution, or double counting detected; every root variable is consumed exactly once in its proper branch, all five BOM sub-costs flow into the material cost subtotal, and the single terminal leaf (`o_22`) is the only leaf in the graph.
2. **Precision and Scale Tampering** — Score: 3/100. All five multiplications, two additions, and the final multiplication are mathematically exact at the stated `MathContext` (precision 16, HALF_EVEN) with zero rounding delta at any step; the declared context is consistent across every operation and appropriate for a two-decimal (cent-scale) monetary quantity.
3. **Semantic Type and Context Cast** — Score: 2/100. Business meaning is preserved end-to-end: unit cost × qty/assembly → cost-per-assembly (5 branches) → summed into material cost per unit → + labor + overhead → total unit cost → × production run quantity → total run cost. No relabeling, unit shift, or gross/net conflation is present anywhere in the chain.

#### Anomaly Localization (If Detected)
None. Full trace of the BOM rollup:
- `op_1`: i_2(14.50) × i_3(1) = o_4(14.50) — circuit board
- `op_2`: i_5(6.25) × i_6(1) = o_7(6.25) — enclosure
- `op_3`: i_8(0.85) × i_9(4) = o_10(3.40) — connector
- `op_4`: i_11(0.05) × i_12(12) = o_13(0.60) — fastener
- `op_5`: i_14(2.10) × i_15(2) = o_16(4.20) — cable
- `op_6`: addBulk(o_4,o_7,o_10,o_13,o_16) = o_17(28.95) — material cost/unit, matches exact sum 14.50+6.25+3.40+0.60+4.20=28.95
- `op_7`: addBulk(o_17, i_18=8.00, i_19=3.50) = o_20(40.45) — total unit cost
- `op_8`: o_20(40.45) × i_21(5000) = o_22(202250.00) — total run cost, exact

Every root feeds exactly one operation, every intermediate BOM sub-cost feeds the material cost subtotal exactly once, and the subtotal flows linearly into the final result with no re-entry, no bypass, and no duplicated contribution. The structural reference data confirms zero multi-use variables, zero repeated-argument operations, and zero duplicate leaf names, which is corroborated by manual trace.

#### Details
This is a straightforward, linear BOM-rollup-to-production-cost pipeline with no branching reconvergence, no alternate root substituted for a computed leaf, and no leaf other than the terminal result (so no mandatory adjustment could have been dropped). All arithmetic (unit cost × quantity per sub-component, bulk summation into material cost, addition of labor/overhead, and final multiplication by run quantity) replays exactly under the declared DECIMAL64-equivalent MathContext (precision 16, HALF_EVEN), which is a standard, non-directional rounding mode at a precision far exceeding the two-decimal monetary resolution needed — so there is no salami-slicing, scale-truncation, or coarse-context finding. Business labels (unit cost, qty/assembly, cost per assembly, material cost per unit, labor, overhead, total unit cost, run quantity, total run cost) track consistently through every operation with no implicit relabeling or context stripping that contradicts upstream metadata. No corrective action is required; the graph presents no confirmed or ambiguous violation under any of the three attack vectors.