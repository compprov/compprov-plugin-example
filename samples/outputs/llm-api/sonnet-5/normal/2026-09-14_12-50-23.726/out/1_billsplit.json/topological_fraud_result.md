# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

## Audit Summary

This CPG models a straightforward restaurant bill-splitting pipeline: sum four line items into a subtotal, derive tax and tip from that subtotal, sum subtotal+tax+tip into a grand total, then divide by diner count for a per-person share. All arithmetic was independently re-derived and matches the stored values exactly:

- o_6 (subtotal) = 24.00+19.50+12.00+9.00 = 64.50 ✔
- o_8 (tax) = 64.50*0.08 = 5.1600 ✔
- o_10 (tip) = 64.50*0.20 = 12.9000 ✔
- o_11 (grand total) = 64.50+5.16+12.90 = 82.5600 ✔
- o_13 (per-diner share) = 82.56/4 = 20.6400 ✔

### Path Multiplicity Review (M(V, terminal))

The structural reference data flags `o_6` as consumed by more than one operation (op_2, op_3, op_4). Tracing forward, `o_6` reaches the terminal chain (o_11 → o_13) via three causal routes: directly as an addend in op_4, and indirectly through op_2→o_8→op_4 and op_3→o_10→op_4.

This was scrutinized against the Double Counting pattern, but it does not qualify as fraud: `o_8` (tax) and `o_10` (tip) are *new derived entities* (subtotal scaled by distinct, independently-declared rates), not the same value re-injected unchanged. The formula `subtotal + tax + tip = total` is the textbook, fully transparent shape of this domain (tax and tip legitimately depend on the same base), and each of subtotal, tax, and tip is added into the grand total exactly once — there is no re-addition of subtotal itself beyond its single direct term in op_4, and no re-addition of tax/tip beyond their single appearance. This is a documented, auditable proportional derivation, not an undocumented duplicate path for the *same* quantity.

### Omission Review (M = 0)

All declared contributor inputs (both entrees, both shared items, tax rate, tip rate, diner count) are consumed by an operation that feeds forward to the terminal output. The sole true leaf, `o_13`, is the graph's intended terminal result itself, not an orphaned contributor.

### Lineage Disconnection Review

The leaf-name-collision set is empty, and no root INPUT was found mirroring a computed sibling's name, units, role, or value (e.g., no hardcoded stand-in for `o_6`, `o_8`, `o_10`, or `o_11` exists among the roots). Every downstream operation consumes the literal `resultId` of its true predecessor step (op_4 consumes `o_6`, `o_8`, `o_10` — all genuine `resultId`s; op_5 consumes `o_11`, the genuine `resultId` of op_4). Full forward propagation from roots reproduces the reported terminal value exactly, so `Origin_Propagation_Valid` holds.

### Deduplicated Sum Check

S_dedup (24.00+19.50+12.00+9.00+5.16+12.90) = 82.56 = S_reported (o_11). No discrepancy.

## Conclusion

No Calculation Omission, Double Counting, or Lineage Disconnection pattern was substantiated. The `o_6` reuse is a benign, standard shared-basis computation (subtotal underlying both tax and tip, each added once), fully consistent with the graph's own declared formulas and metadata. Risk is assessed as low, reflecting a clean, internally consistent pipeline with no evidence of tampering.