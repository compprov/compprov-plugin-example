# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

## Audit Summary

This CPG models a subscription mid-cycle upgrade proration: `net charge = new_prorated_charge − old_plan_refund`, where both sub-quantities are derived from a shared `days_in_cycle` (i_2) and `days_remaining` (i_3) basis.

### Path Multiplicity Analysis

- **Old plan branch:** i_4 (old price) → op_1 (divide by i_2) → o_5 (old daily rate) → op_2 (multiply by i_3) → o_6 (old refund) → op_5 (subtract) → **o_10**. M(o_6, o_10) = 1.
- **New plan branch:** i_7 (new price) → op_3 (divide by i_2) → o_8 (new daily rate) → op_4 (multiply by i_3) → o_9 (new prorated charge) → op_5 (subtract) → **o_10**. M(o_9, o_10) = 1.
- Both mandatory contributors (old refund as a subtracted deduction, new prorated charge as the base charge) reach the single terminal subtraction (op_5) exactly once each. No mandatory contributor is dropped (M=0) and none is duplicated into the aggregation (M>1).

### Reused Root Inputs (i_2, i_3)

i_2 ("days in cycle") and i_3 ("days remaining") are each consumed by two operations (op_1/op_3 and op_2/op_4 respectively). This is a shared allocation basis — the same cycle length and same remaining-days count legitimately apply to both the old-plan and new-plan rate/proration calculations, which are distinct quantities that only converge once, at the final subtraction. This matches the benign "shared tax rate consumed by non-competing branches" pattern, not duplication of a single entity into the same rollup.

### Leaf / Substitution Check

- Only leaf is o_10, which is the intended terminal output — not an orphaned dead-end masking a bypass.
- The reference data's leaf-name-collision set is empty; manual inspection found no INPUT variable masquerading as a computed sibling (no near-duplicate names, no hardcoded override sitting alongside a computed twin for old refund, new charge, or net charge).
- All root inputs (i_1, i_2, i_3, i_4, i_7) are consumed by the graph; none are silently superseded.

### Arithmetic Verification

Recomputing under MathContext(16, HALF_EVEN):
- o_5 = 29/30 → 0.9666666666666667 ✓
- o_6 = o_5 × 12 → 11.60000000000000 ✓
- o_8 = 79/30 → 2.633333333333333 ✓
- o_9 = o_8 × 12 → 31.60000000000000 ✓
- o_10 = o_9 − o_6 → 20.00000000000000 ✓

All intermediate and terminal values replay exactly against the stated precision context, confirming full forward-propagation validity ($Origin\_Propagation\_Valid$ holds) — the terminal output derives strictly from the graph's own root inputs through its own declared operation chain, with no substituted context at any step.

## Conclusion

No Calculation Omission, Double Counting, or Lineage Disconnection/Context Substitution pattern was found. Every mandatory contributor (old refund, new prorated charge) has path multiplicity exactly 1 into the terminal subtraction, the shared day-count inputs are legitimate non-competing allocation bases, and the terminal result is arithmetically and topologically consistent with full forward propagation from the true root inputs. Residual risk score reflects only the inherent limits of static graph auditing (e.g., inability to verify domain correctness of the proration formula itself against external business rules) rather than any detected structural violation.