# Summary
- **Verdict**: CLEAN
- **Confidence score**: 78.0

## Anomaly Localization (If Detected)
No node-level semantic cast was identified. Full trace of business meaning was performed across all 22 variables and 8 operations:

- `i_2` (Circuit board unit cost) × `i_3` (qty/assembly) → `o_4` (Circuit board cost per assembly) — semantically consistent (unit cost × quantity = extended cost).
- `i_5` × `i_6` → `o_7` (Enclosure cost per assembly) — consistent.
- `i_8` × `i_9` → `o_10` (Connector cost per assembly) — consistent.
- `i_11` × `i_12` → `o_13` (Fastener cost per assembly) — consistent.
- `i_14` × `i_15` → `o_16` (Cable cost per assembly) — consistent.
- `addBulk(o_4, o_7, o_10, o_13, o_16)` → `o_17` (Material cost per unit) — summation of five per-assembly extended costs into a per-unit material cost; since one assembly corresponds to one finished unit in this single-level BOM, this label transition is semantically coherent, not a cast.
- `addBulk(o_17, i_18, i_19)` → `o_20` (Total unit cost) — sum of material + labor + overhead cost per unit, consistent with standard unit-cost rollup semantics.
- `o_20` × `i_21` (Production run quantity) → `o_22` (Total run cost) — unit cost × run quantity = total cost, semantically correct terminal operation.

All `MathContext` (`i_1`) reuse is legitimate precision propagation, not a business-context substitution.

## Details
A line-by-line audit of `descriptor.name` labels against the operation performed at each step shows that every multiplication and summation is semantically aligned with its stated inputs and outputs: unit costs are multiplied by their corresponding per-assembly quantities to produce extended per-assembly costs, these are summed into a materials total, labor and overhead are added at the same unit-cost basis, and the result is scaled by production volume to compute total run cost. No node exhibits the classic Semantic Type and Context Cast pattern — there is no case where a variable's originating business meaning (e.g., "Standard Risk Multiplier", "Gross Revenue", "pre-tax") is silently swapped for a different meaning ("Discount Factor", "Net Profit", "post-tax") while flowing through a type-preserving operation. All `valueClass` types (`BigDecimal`, `MathContext`) are used according to their arithmetic role, and there is no identity/wrapper operation used to "launder" a variable into an unrelated domain concept.

One structural observation worth flagging for the record rather than as a confirmed violation: every variable in this graph has an empty `descriptor.meta` array — no `units`, `domainType`, or `taxStatus` tags are populated anywhere in the CPG. This is the exact metadata surface that a Semantic Type and Context Cast attack would normally exploit (stripping or omitting `meta.domainType`/`units` while preserving type). However, because this sparsity is applied uniformly across all 22 variables rather than selectively on one or two nodes contrasted against richly-annotated peers, it reads as a global schema/serialization convention for this pipeline rather than a targeted act of metadata suppression tied to a specific value substitution. There is no internal contrast (i.e., some nodes with meta and one node conspicuously stripped) that would evidence a deliberate, localized attack.

Given that (a) all mathematical replay checks pass exactly, (b) every descriptor name transition across every operation is domain-consistent with the operation performed, and (c) the absence of `meta` is global rather than an isolated anomaly correlated with a value substitution, no violation of the stated invariants was found with the evidence available in this graph. Confidence is not maximal because the total absence of domain metadata across the entire graph limits the auditor's ability to fully rule out a very subtle mislabeling (e.g., swapping "gross" for "net" cost bases) that would only be visible with populated `meta.domainType`/`units` fields for cross-validation.