# Summary
- **Verdict**: SUSPICIOUS SUBSTITUTION
- **Confidence score**: 74.0

## Anomaly Localization

**Implicated nodes:** `i_24` (root INPUT, "Tax rate (8%, California)"), `op_11` (multiply: `o_23 * i_24 -> o_25`), `o_25` ("Sales tax"), `op_12` (add: `o_23 + o_25 -> o_26`), `o_26` ("Order total").

Every other segment of this pipeline replays cleanly and its documented metadata is internally consistent with its numeric payload:

- `i_16` "Discount rate (SAVE10)" = 0.10 → multiplier `o_17` = 1 − 0.10 = 0.90 ✔ (name matches value, matches usage)
- `i_19` "Discount rate (LOYALTY5)" = 0.05 → multiplier `o_20` = 1 − 0.05 = 0.95 ✔ (name matches value, matches usage)
- Line totals (`o_5,o_8,o_11,o_14`), `addBulk` to `o_15`, sequential discount chaining to `o_18`/`o_21`, shipping addition to `o_23` — all arithmetic reconciles exactly against stated decimal outputs under the declared `MathContext` (`i_1`, precision 16, HALF_EVEN).

However, `i_24` is labeled "Tax rate (8%, California)" — a descriptor that unambiguously declares an 8% (0.08) statutory rate — yet its stored `value` is `"0.02"`, a 2% rate. This is a 4x discrepancy between the variable's declared semantic identity and the number that is actually forward-propagated into the compliance-critical tax and total calculations.

`op_11` consumes `i_24` literally (`(a*b)mc` with `a=o_23`, `b=i_24`) to produce `o_25 = 4.14743200`, which local replay confirms is arithmetically correct **given the stored 0.02 value** (207.3716 × 0.02 = 4.147432). `op_12` then correctly sums `o_23 + o_25 = o_26 = 211.51903200`. Local deterministic replay therefore passes cleanly end-to-end — this is precisely why the substitution is dangerous: every operation is mathematically faithful to its own inputs, but one of those inputs does not correspond to the quantity it claims to represent.

Had the graph actually implemented the rate its own metadata declares (8%), the downstream values would have been:
- Sales tax: 207.3716 × 0.08 = **16.589728** (vs. reported 4.14743200)
- Order total: 207.3716 + 16.589728 = **223.961328** (vs. reported 211.51903200)

That is a ~12.44 unit understatement of the final reported "Order total" — a materially significant discrepancy for a compliance-labeled tax figure.

## Details

This is a variant of Context Substitution that hides inside a single root `INPUT` node rather than via a duplicate/near-duplicate variable ID: the node is declared with the full metadata trappings of a legitimate, audited 8% California sales-tax rate, but the payload actually wired into the multiply operation encodes an unrelated 2% figure. Because the substitution occurs entirely within one already-declared root input (there is no second, orphaned "real 8%" computed sibling elsewhere in the graph to point to as the bypassed twin — confirmed against the full variable list), this doesn't trip the exact-match name-collision or dual-consumer heuristics provided in the structural reference data. It is, however, exactly the kind of disguise those heuristics are designed to be blind to: a root `INPUT`, fully annotated, carrying a value inconsistent with its own declared context, silently routed into the two final compliance-relevant outputs (`o_25`, `o_26`).

Per the audit invariants, being declared as a root `INPUT` does not exempt a hardcoded/mismatched literal from scrutiny — this is precisely the disguise mechanism the invariants call out, since there is no other way to inject an unverified constant into this graph format. The value is not a rounding or precision artifact (0.02 vs 0.08 is not a MathContext/rounding-mode effect at 16-digit precision); it is a flat categorical mismatch between declared rate and applied rate.

**Consequence:** the reported `Sales tax` (`o_25`) and `Order total` (`o_26`) — the two most compliance-sensitive outputs in an e-commerce order pipeline — are computed from a rate that contradicts its own recorded justification/label, understating the customer's tax liability and the total charged by roughly 5.9% of the pre-tax taxable amount. Any downstream consumer (accounting, tax remittance, audit trail) trusting the `descriptor.name` metadata as the source of truth for what rate was applied would be misled, while a purely numeric/local replay check would report full success.

Given that no computed "true 8%" sibling exists elsewhere to definitively prove intentional bypass (as opposed to, e.g., a data-entry error propagated without correction), this is reported as a suspicious substitution rather than a fully confirmed structural lineage break — but the mismatch is unambiguous, material, and sits at the most compliance-critical junction in the graph, warranting escalation rather than dismissal.