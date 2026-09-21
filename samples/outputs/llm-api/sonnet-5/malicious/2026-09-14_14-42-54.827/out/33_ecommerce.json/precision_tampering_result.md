# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 88.0

## Anomaly Localization

**Implicated nodes:** `i_24` (INPUT, "Tax rate (8%, California)"), `op_11` (multiply: o_23 * i_24 -> o_25), `o_25` ("Sales tax"), `op_12` (add: o_23 + o_25 -> o_26), `o_26` ("Order total").

Flow of the defect:
1. `o_23` ("Taxable amount") = 207.371600, correctly derived from the discounted subtotal plus shipping (verified exact against all upstream multiply/subtract/addBulk steps: o_5, o_8, o_11, o_14, o_15, o_17, o_18, o_20, o_21 all reconcile exactly to arbitrary precision against their stated formulas and the declared `MathContext` `i_1` [precision 16, HALF_EVEN]).
2. `i_24` is declared with `descriptor.name` = **"Tax rate (8%, California)"**, but its stored `value` is **0.02**, not 0.08.
3. `op_11` multiplies `o_23 * i_24` under `mc = i_1` and produces `o_25 = 4.14743200`. This is arithmetically *correct* given the stored value of `i_24` (207.3716 * 0.02 = 4.147432), so the operation itself passes any formula/MathContext consistency check.
4. `op_12` adds `o_23 + o_25` to yield `o_26 = 211.51903200`, which is likewise internally consistent with the (already corrupted) `o_25`.
5. Had `i_24` actually held the value implied by its own label (0.08, matching "8%, California"), the correct sales tax would be `207.3716 * 0.08 = 16.589728`, and the correct order total would be `207.3716 + 16.589728 = 223.961328` — a difference of **≈ $12.44 per order**, roughly 5.9% of the reported total.

## Details

This is not a rounding-mode or MathContext artifact — every operation in the graph, including `op_11` itself, reconciles exactly to its declared formula and `MathContext`, so naive per-operation arithmetic re-verification (and any check limited to formula/rounding consistency) passes cleanly. The defect is planted one layer upstream, in the **input variable's stored value vs. its own descriptive metadata**: the node is explicitly labeled as an 8% California tax rate but encodes a 0.02 (2%) multiplier — a ~4x understatement, not a boundary-rounding discrepancy of ≤1 ULP. This is exactly the kind of surgical, single-node substitution that a structural/formula-level audit would miss, since operation-level provenance (`op_11`'s formula `(a*b)mc`) is untouched and correctly executed — the falsification lives in the input payload, disguised behind an unaltered, human-trusted display name.

By the stated invariants: this is not 