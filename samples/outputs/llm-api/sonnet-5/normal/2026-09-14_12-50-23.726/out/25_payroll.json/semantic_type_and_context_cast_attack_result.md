# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

## Semantic Type and Context Cast Audit — Payroll: Biweekly Net Pay

### Anomaly Localization (If Detected)
No variable or operation was found where a value's declared business meaning (`descriptor.name`) is silently swapped for an incompatible one while type continuity (`java.math.BigDecimal`) is preserved. The full lineage was traced end-to-end:

- `i_2` (Base salary) + `i_3` (Bonus) --op_1(add)--> `o_4` (Gross pay) — consistent (gross = salary + bonus).
- `i_5` (401k contribution) + `i_6` (Health premium) --op_2(add)--> `o_7` (Pretax deductions) — consistent aggregation of pretax items.
- `o_4` (Gross pay) − `o_7` (Pretax deductions) --op_3(subtract)--> `o_8` (Taxable income) — standard, undisputed gross-to-taxable transformation.
- `o_8` (Taxable income) reused as: 
  - `min(o_8, i_9 Bracket1 ceiling)` --op_4--> `o_12` (Bracket 1 portion) — correct marginal-bracket semantics.
  - `o_8 − o_12` --op_5--> `o_13` (Bracket 2 portion) — correct remainder-bracket semantics.
  - `o_8 * i_17 (State tax rate)` --op_9--> `o_18` (State tax withheld) — taxable income consistently used as the tax base for both federal and state calculations (a stated, non-contradictory business assumption, not a relabeling).
  - `o_8 − o_16 (Federal tax withheld)` --op_10--> `o_19` (After federal withholding) — correctly labeled intermediate.
- `o_12` (Bracket 1 portion) * `i_10` (Bracket 1 rate) --op_6--> `o_14` (Bracket 1 tax); `o_13` (Bracket 2 portion) * `i_11` (Bracket 2 rate) --op_7--> `o_15` (Bracket 2 tax); sum --op_8--> `o_16` (Federal tax withheld) — fully consistent progressive-tax construction.
- `o_19` (After federal withholding) − `o_18` (State tax withheld) --op_11--> `o_20` (Net pay) — correctly composed final net-pay figure, arithmetically and semantically aligned (Net = Gross − Pretax − Federal tax − State tax = 4000 − 350 − 563 − 182.5 = 2904.50, matching the stored value).

No node exhibits a mismatch between its originating domain label and its downstream consumption context. The two multiply-consumed variables flagged structurally (`o_8`, `o_12`) are legitimate multi-use intermediates (taxable income base, bracket-1 portion) consumed strictly according to their declared meaning in every downstream operation — not relabeled or laundered through an identity/wrapper cast.

### Details
Every arithmetic step maps to an operation whose `descriptor.name`/formula matches its declared business semantics (`add` for aggregations, `subtract` for deductions/remainders, `multiply` for rate application, `min` for bracket capping). No variable is fed into a consumer under a business definition conflicting with its origin (e.g., no "Net" value silently reused as "Gross", no risk multiplier recast as a discount factor, no currency/unit conversion implied without a transformation node). The only debatable design choice — using the same `Taxable income` (`o_8`) as the base for both federal bracket tax and the flat state tax — is an explicit, visible, and common payroll convention (many states conform to the federal taxable-income base), not a hidden semantic substitution; it is transparent in the graph rather than laundered through an unlabeled identity operation.

All `MathContext` (`i_1`, DECIMAL64-equivalent, HALF_EVEN, precision 16) usage is uniform and non-anomalous. Numeric replay of every operation matches the stored output values exactly, and business labels remain coherent end-to-end from root inputs to the final `Net pay` leaf (`o_20`).

**Conclusion:** No Semantic Type and Context Cast violation is present in this graph. The pipeline demonstrates a clean, auditable, and semantically coherent gross-to-net payroll computation with no evidence of metadata suppression, implicit relabeling, or domain-context contradiction. Residual risk score reflects only the inherent, non-malicious ambiguity of using a single taxable-income base for two distinct tax jurisdictions (federal/state), which is a documented and common real-world convention rather than a demonstrated attack.