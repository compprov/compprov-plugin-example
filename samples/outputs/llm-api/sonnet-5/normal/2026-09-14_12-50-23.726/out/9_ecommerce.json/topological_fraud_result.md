# Summary
- **Verdict**: CLEAN
- **Risk score**: 8.0

## Summary
A full forward-propagation replay of this order-total pipeline was performed, cross-referencing every root INPUT, every operation, and every OUTPUT against the three Topological & Provenance Fraud sub-patterns (Omission M=0, Double-Counting M>1, Lineage Disconnection M=1-wrong-source).

## Anomaly Localization (If Detected)
No confirmed violation of the EXPECTED_INVARIANTS was found. One structural flag from the reference data was investigated in depth and resolved as benign:

- **o_23 ("Taxable amount")** is consumed by two operations: `op_11` (multiply, with `i_24` tax rate → `o_25` Sales tax) and `op_12` (add, with `o_25` → `o_26` Order total). This matches the system-flagged "consumed by >1 operation" set.
  - Tracing the causal paths from `o_23` to the terminal `o_26`: Path A is direct (`o_23` → `op_12` as addend `a`). Path B is indirect (`o_23` → `op_11` as multiplicand `a` → `o_25` → `op_12` as addend `b`).
  - This is the standard `Total = Base + Base×Rate` tax formula, not a duplication of the same contribution. `o_23` plays two distinct financial roles (pre-tax total, and tax base) rather than the same cost/revenue entity being counted twice toward the same line item. The dedup-sum check `S_dedup = S_reported` holds: `223.961328 = 207.3716 + 16.589728`, and `16.589728` is a genuinely distinct, correctly-derived tax liability, not a re-injection of `o_23` itself.

## Details
End-to-end arithmetic verification (MathContext precision=16, HALF_EVEN):
- Line totals: 29.99×3=89.97, 14.50×2=29.00, 59.00×1=59.00, 9.99×5=49.95 → Subtotal (`o_15`) = 227.92. Exact match, all four line totals feed `addBulk` (`op_5`) exactly once each (M=1 for each SKU line total).
- SAVE10: multiplier = 1−0.10 = 0.90 (`o_17`); 227.92×0.90 = 205.128 (`o_18`). LOYALTY5: multiplier = 1−0.05 = 0.95 (`o_20`); 205.128×0.95 = 194.8716 (`o_21`). Both discount rates (`i_16`, `i_19`) and the shared identity constant `i_2` ("1.0") are consumed exactly once per discount branch — the reuse of `i_2` across two independent subtract operations is a benign shared mathematical constant, structurally analogous to the excluded `MathContext` reuse, not a double-counted entity.
- Shipping fee (`i_22`, flagged taxable=true, California) is added once (`op_10`) into `o_23` = 194.8716+12.50 = 207.3716 — correctly included in the taxable base per its own metadata.
- Tax: 207.3716×0.08 = 16.589728 (`o_25`), consistent with `i_24`.
- Order total: 207.3716+16.589728 = 223.961328 (`o_26`) — matches reported value exactly.

No leaf variables other than the terminal `o_26` exist, so no Calculation-Omission dead-ends were found. No duplicate/near-duplicate names were found among root INPUTs vs computed OUTPUTs (the leaf-name-collision set is empty, and manual scan of all 26 variable names found no semantic near-duplicates, e.g. no second "Shipping fee", "Subtotal", or "Sales tax" masquerading as a hardcoded root). Every downstream operation consumes the literal `resultId` produced by its logical predecessor (verified op-by-op: `op_5`→`o_15`→`op_7`(a)…→`op_9`→`o_21`→`op_10`→`o_23`→`op_11`/`op_12`→`o_25`/`o_26`), so `Origin_Propagation_Valid` holds — there is no evidence of a substituted/hardcoded stand-in overriding a computed sibling anywhere in the chain.

## Conclusion
This graph is a well-formed, arithmetically consistent order-total computation. The single reuse flagged by structural heuristics (`o_23`) is fully explained by the standard base+tax formula and does not constitute double counting of a single cost/revenue entity into the same aggregate. Residual risk score reflects only the inherent structural complexity of a multi-branch tax/discount pipeline warranting periodic re-audit, not a confirmed finding.