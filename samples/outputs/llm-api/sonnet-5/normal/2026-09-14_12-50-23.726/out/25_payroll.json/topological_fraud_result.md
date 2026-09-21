# Summary
- **Verdict**: CLEAN
- **Risk score**: 8.0

## Anomaly Localization (If Detected)
No Topological or Provenance Fraud pattern (Omission, Double-Counting, or Lineage Disconnection) was confirmed. The reference-flagged nodes were individually traced end-to-end and all resolve to legitimate, documented allocation logic:

- **o_8 (Taxable income)** — flagged as multi-consumed. Traced consumers: `op_4` (min, → o_12), `op_5` (subtract, → o_13), `op_9` (multiply by state rate i_17 → o_18), `op_10` (subtract federal tax o_16 → o_19). These are four *distinct, non-overlapping* uses of the same base quantity: (a) splitting it into two marginal-tax-bracket portions (o_12 + o_13 = o_8, a partition, not a duplication), (b) computing the state-tax liability, and (c) serving as the minuend for the post-federal-tax subtotal. None of these paths re-adds o_8's value into the terminal sum more than once — it is consumed once as a base for a *complementary partition* (bracket split) and once as an operand feeding sequential subtractions, which is the correct shape of a progressive-bracket tax engine, not the shape of double counting.
- **o_12 (Bracket 1 portion)** — flagged as multi-consumed by `op_5` (→ o_13, the bracket 2 remainder) and `op_6` (→ o_14, bracket-1 tax). These are two different derived quantities (remainder vs. tax-on-portion), each required exactly once downstream (o_13→o_15→o_16, and o_14→o_16). No value is summed twice into o_16 or later nodes.

**Terminal output**: `o_20` (Net pay) is the sole leaf variable, confirming it is the intended, singly-defined terminal node — not competing with an orphaned computed twin.

**Full forward re-derivation** (root inputs → o_20):
- o_4 = i_2+i_3 = 3500+500 = 4000.00 ✓
- o_7 = i_5+i_6 = 200+150 = 350.00 ✓
- o_8 = o_4-o_7 = 3650.00 ✓
- o_12 = min(o_8,i_9) = 2000.00 ✓
- o_13 = o_8-o_12 = 1650.00 ✓
- o_14 = o_12*i_10 = 200.00 ✓
- o_15 = o_13*i_11 = 363.00 ✓
- o_16 = o_14+o_15 = 563.00 ✓
- o_18 = o_8*i_17 = 182.50 ✓
- o_19 = o_8-o_16 = 3087.00 ✓
- o_20 = o_19-o_18 = 2904.50 ✓ (matches reported value)

Algebraically, o_20 = o_8 - (o_14+o_15) - o_18 = GrossPay - PretaxDeductions - FederalTax - StateTax, which is the correct, non-duplicative composition of a payroll net-pay formula — every mandatory contributor (gross pay, 401k, health premium, both tax brackets, state tax) is folded in exactly once.

## Details
All `resultId`s are consumed by literally the correct downstream operations — no stand-in/substituted arguments were found at any junction (Lineage Disconnection check: negative). No root `INPUT` masquerades under the name of a computed sibling (Context Substitution check: negative — the leaf-name-collision set is empty and manual review of every root confirms none semantically shadow a computed output). No mandatory contributor is left as an unconsumed dead-end other than the single, correct terminal node (Omission check: negative). The two flagged multi-consumption variables (o_8, o_12) both represent a documented, auditable partition/derivation pattern intrinsic to progressive tax-bracket computation (`min`/complement split, tax-on-portion), not unexplained duplication into the same aggregation — S_dedup equals S_reported when the arithmetic is replayed by hand.

Given a fully clean forward-propagation replay, correct arithmetic at every step, and benign explanations backed by explicit formula metadata (`formula` annotations on each operation) for every structurally-flagged reuse, this graph shows no confirmed instance of the three Topological & Provenance Fraud sub-patterns. Risk is scored low but non-zero only because the graph's dense reuse of o_8/o_12 requires domain trust that the bracket-split logic is legitimate (it is, per available metadata) rather than a disguised inflation — an assumption that cannot be 100% eliminated without additional out-of-band ground truth on the tax-bracket policy.