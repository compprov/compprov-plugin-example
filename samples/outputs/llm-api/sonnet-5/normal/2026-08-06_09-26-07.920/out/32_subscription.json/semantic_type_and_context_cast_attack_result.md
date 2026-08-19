# Summary
- **Verdict**: CLEAN
- **Confidence score**: 78.0

## Anomaly Localization (If Detected)
No variable or operation exhibiting a genuine `C_source != C_target` semantic divergence was identified. Full trace of business context below:

- `i_4` (Old plan monthly price) → `op_1` (divide by `i_2`, Days in billing cycle) → `o_5` (Old plan daily rate). Context: price-per-day derived from monthly price, consistent.
- `o_5` → `op_2` (multiply by `i_3`, Days remaining in cycle) → `o_6` (Old plan refund, unused days). Context: refund = daily rate × unused (remaining) days — internally consistent, since "days remaining in cycle" is definitionally the "unused days" on the old plan.
- `i_7` (New plan monthly price) → `op_3` (divide by `i_2`) → `o_8` (New plan daily rate). Consistent.
- `o_8` → `op_4` (multiply by `i_3`) → `o_9` (New plan prorated charge, remaining days). Context: charge = daily rate × remaining days on the new plan — consistent with label.
- `o_9`, `o_6` → `op_5` (subtract, a−b) → `o_10` (Net charge = new prorated − old refund). Sign convention and argument order (`a=o_9`, `b=o_6`) match the declared formula and label exactly.

The structurally-flagged multi-consumption of `i_2` and `i_3` (each used in two operations) was investigated specifically for a possible context cast, since dual-use of a day-count is a classic vector for smuggling in a mismatched domain meaning. In this case, both consumers of `i_3` ("Days remaining in cycle") use it under the *same* business meaning — unused days on the old plan and remaining/chargeable days on the new plan are the same 12 days by construction of a mid-cycle proration; this is standard, auditable proration logic, not a re-labeling. Both consumers of `i_2` ("Days in billing cycle") use it as the shared cycle-length denominator for old and new plan daily-rate derivation, which is the expected assumption for an in-cycle plan swap.

## Details
A full re-derivation of the numeric pipeline was performed at the BigDecimal/MathContext level (precision 16, HALF_EVEN — which is exactly Java's `MathContext.DECIMAL64`, matching the descriptor name "Computation precision (DECIMAL64)", so no MathContext substitution/mislabel exists either):

- `o_5` = 29.00/30 → 0.9666666666666667 (verified exact rounding under HALF_EVEN, 16 sig figs) — matches.
- `o_6` = 0.9666666666666667×12 → exact 11.6000000000000004 → rounds to 11.60000000000000 — matches.
- `o_8` = 79.00/30 → 2.633333333333333 — matches.
- `o_9` = 2.633333333333333×12 → exact 31.599999999999996 → rounds (with carry cascade) to 31.60000000000000 — matches.
- `o_10` = 31.60000000000000 − 11.60000000000000 = 20.00000000000000 — matches.

All operation formulas (`(a/b)mc`, `(a*b)mc`, `(a-b)mc`) match their `descriptor.name` and argument bindings, argument keys are semantically ordered correctly (numerator/denominator, minuend/subtrahend), wrapper classes and MathContext references are uniform throughout, and no identity/pass-through node exists that could serve as a covert re-labeling point. Variable naming is internally coherent with the operations that consume/produce each value; no variable is fed into a downstream step whose declared business meaning conflicts with its origin.

One minor observation for audit hygiene: every variable's `descriptor.meta` array is empty (no currency code, tax status, or unit annotations), meaning the graph carries essentially no explicit domain metadata to independently corroborate the plain-English names. This absence of metadata is a latent risk surface for exactly the attack type under review (an adversary could exploit the total absence of domain tags to relabel a node's `descriptor.name` without technically "suppressing" any meta field), but no such relabeling event, near-duplicate identifier, or context inconsistency was found within this specific graph. The proration logic is complete, self-consistent, and the reused day-count variables are legitimately shared under one consistent business meaning each. Based on the evidence available, this graph does not show a confirmed Semantic Type and Context Cast; however, the total absence of domain metadata across all nodes should be treated as a standing structural weakness for future audits rather than fully dismissed.