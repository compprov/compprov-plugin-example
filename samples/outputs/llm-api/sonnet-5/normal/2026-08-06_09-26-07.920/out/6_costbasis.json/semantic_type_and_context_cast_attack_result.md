# Summary
- **Verdict**: CLEAN
- **Confidence score**: 82.0

## Anomaly Localization (If Detected)
No variable or operation was found where the declared business context (C_source) diverges from the business context actually consumed downstream (C_target) while technical types stay identical. Every multiply/add/divide/subtract step in this trace maps onto a semantically coherent accounting operation for a weighted-average cost-basis calculation:

- `i_2`,`i_3` (Lot 1 shares, price) → `op_1` → `o_4` (Lot 1 cost) — shares × price = cost. Dimensionally consistent.
- `i_5`,`i_6` → `op_2` → `o_7` (Lot 2 cost); `i_8`,`i_9` → `op_3` → `o_10` (Lot 3 cost). Same pattern, consistent.
- `i_2`,`i_5`,`i_8` → `op_4` (addBulk) → `o_11` (Total shares held). Units (shares) preserved across all bulk-addition operands.
- `o_4`,`o_7`,`o_10` → `op_5` (addBulk) → `o_12` (Total cost). Units (currency) preserved.
- `o_12`,`o_11` → `op_6` (divide) → `o_13` (Weighted-average cost per share). Cost/shares = price-per-share; standard, auditable weighted-average formula, matches the descriptor's stated pipeline purpose ("weighted-average across 3 lots").
- `i_14`,`i_15` → `op_7` → `o_16` (Sale proceeds) = shares sold × sale price. Consistent.
- `i_14`,`o_13` → `op_8` → `o_17` (Cost basis of shares sold) = shares sold × weighted-average cost/share. This is the expected consuming operation for a weighted-average cost basis method — not an implicit relabeling, since `o_13`'s own defining operation (`op_6`) is transparent and traceable, and the consuming node's semantics ("shares × per-share cost basis") match `o_13`'s declared meaning exactly.
- `o_16`,`o_17` → `op_9` (subtract) → `o_18` (Realized gain/loss) = proceeds − cost basis. Standard realized-gain formula; no gross/net, pre-tax/post-tax, or currency substitution is introduced at this step.

All numeric replay checks out exactly (e.g., 13812.50/325 = 42.50 precisely, matching Lot 1's coincidental per-share price), and no identity/wrapper operation is used anywhere to silently swap one domain concept for another.

## Details
The defined attack vector requires either (a) explicit domain metadata (`meta.domainType`, units, tax status) being silently altered or stripped between producer and consumer, or (b) a value being passed through a type-preserving operation so a downstream node treats it as a different business entity than its origin declares (e.g., Gross→Net, Local→Base currency, raw multiplier recast as a discount factor).

In this graph, every variable's `descriptor.meta` array is empty — not selectively suppressed on some path while populated on another, but uniformly absent across every INPUT and OUTPUT node. This is a structural characteristic of the whole dataset rather than a differential stripping event isolated to one lineage, so it does not itself demonstrate the covert-remapping mechanism the attack vector describes (there is no populated domain-context baseline anywhere in the graph that a specific node then diverges from). Names (`descriptor.name`), which are the only domain-context carriers present here, remain consistent and accurately descriptive at every hop: "Lot N shares/price/cost" feed correctly-typed sums; "Total shares held"/"Total cost" feed a correctly-typed weighted-average division; "Weighted-average cost per share" is consumed by a node whose formula and naming both match its declared meaning; "Sale proceeds" and "Cost basis of shares sold" feed a subtraction that is exactly what a realized-gain formula requires. No node consumes a variable under a business definition that conflicts with its producing operation's declared name/meta, and no domain transition (gross→net, unadjusted→adjusted, local→base currency, one risk category recast as another) occurs anywhere in the trace.

Given the complete absence of populated domain metadata in this particular CPG, a component of the required evidence (metadata suppression differential) cannot be positively ruled in or out with full certainty — the audit can only confirm that names and mathematical roles align consistently end-to-end, which they do. This residual uncertainty (uniformly missing metadata across an entire financial pipeline is itself atypical for a well-instrumented provenance system) tempers full certainty but does not, on the evidence available, support a finding of an active semantic cast for this specific case.