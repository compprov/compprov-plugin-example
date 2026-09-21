# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

## Audit Summary

A full forward-propagation trace was performed for every mandatory contributor to the terminal output `o_15` (Net pay), focusing on the three Topological & Provenance Fraud sub-patterns: Calculation Omission (M=0), Double Counting (M>1), and Lineage Disconnection/Context Substitution (M=1, wrong source).

### Path Tracing
- `i_3` (Regular hours) × `i_2` (Base hourly rate) → `op_1` → `o_4` (Regular pay = 880.00) ✓
- `i_2` × `i_6` (Overtime multiplier) → `op_2` → `o_7` (Overtime rate = 33.000) ✓
- `i_5` (Overtime hours) × `o_7` → `op_3` → `o_8` (Overtime pay = 198.000) ✓
- `i_9` (Night-shift hours) × `i_10` (Night differential) → `op_4` → `o_11` (Night-shift pay = 21.00) ✓
- `o_4` + `o_8` + `o_11` → `op_5` (addBulk) → `o_12` (Gross pay = 1099.000) ✓
- `o_12` × `i_13` (tax rate 0.18) → `op_6` → `o_14` (Tax withholding = 197.82000) ✓
- `o_12` − `o_14` → `op_7` → `o_15` (Net pay = 901.18000) ✓

All arithmetic replays correctly end-to-end (880 + 198 + 21 = 1099; 1099 × 0.18 = 197.82; 1099 − 197.82 = 901.18).

### Reference-Data Cross-Check
- **Leaf set** `{o_15}`: this is the single, legitimate terminal output — no unconsumed intermediate deduction, cost, or credit variable was found dangling as a dead-end. No Calculation Omission candidate exists.
- **Reused variables** `{i_2, o_12}`: both are benign, non-competing reuse patterns.
  - `i_2` (Base hourly rate) feeds `op_1` (regular pay = rate × hours) and `op_2` (overtime rate = rate × 1.5) — two *distinct* derived quantities, not the same entity summed twice into the same rollup.
  - `o_12` (Gross pay) feeds `op_6` (tax calculation basis) and `op_7` (final subtraction basis) — this is the expected shape of a gross-to-net calculation (tax is computed from gross, then subtracted from the same gross), not duplicate accumulation. No Double Counting confirmed.
- **Root inputs** `{i_1, i_2, i_3, i_5, i_6, i_9, i_10, i_13}`: all are consumed exactly where expected; none exactly or semantically mirrors a computed sibling's name/role/units in a way suggesting a substituted constant was swapped in for a computed value.
- **Exact-name leaf collisions**: none detected, and a broader by-role scan (rate, hours, multiplier, differential, tax rate, gross, net) found no orphaned computed variable whose role was quietly replaced by a hardcoded root at the terminal step.

### Conclusion
Every mandatory contributor (regular pay, overtime pay, night-differential pay, tax withholding) has path multiplicity M = 1 into the terminal aggregation, and the terminal output `o_15` derives from a fully connected, unbroken forward-propagation chain from true root inputs. No evidence of Calculation Omission, Double Counting, or Lineage Disconnection/Context Substitution was found in this graph.

#### Anomaly Localization (If Detected)
None identified.

#### Details
The payroll computation is internally consistent: gross pay is the sum of exactly three independently-computed pay components (regular, overtime, night-differential), each traceable to a unique root-input chain with no re-entry or duplication; tax withholding is computed once from gross pay and subtracted once to yield net pay. The two instances of variable reuse identified by the structural reference data are legitimate, non-fraudulent shared-basis patterns (a rate used for two distinct calculations, and a gross figure used as the basis for both a derived tax amount and the final subtraction) rather than duplicate accumulation into the same rollup. No leaf variable represents an unconsumed mandatory deduction, and no root/input hardcoded value duplicates or displaces a computed sibling.