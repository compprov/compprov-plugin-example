# Summary
- **Verdict**: CLEAN
- **Confidence score**: 78.0

## Summary
A full trace of every variable through every operation was performed, cross-checking (1) the mathematical replay, (2) the declared business meaning encoded in each variable's `descriptor.name`, and (3) the argument-to-parameter mapping of each operation, looking specifically for a node where the technical type (`java.math.BigDecimal`) is preserved but the *semantic* referent of the value is silently swapped (e.g., a rate applied to the wrong bracket, a gross value fed into a step that treats it as net, or a domain tag quietly stripped/renamed).

### Trace of business context through the graph
- `i_3` (Monthly revenue, 135000.00) is capped twice: `min(i_3,i_4)=o_9` "Revenue through tier 1" (50000.00) and `min(i_3,i_5)=o_10` "Revenue through tier 2" (100000.00). Both caps correctly correspond to the ceilings named in their own variables (`i_4`=50000 tier‑1 ceiling, `i_5`=100000 tier‑2 ceiling).
- `o_11` "Tier 2 portion" = `o_10 - o_9` = 50000.00 — this is exactly the revenue band between the tier‑1 and tier‑2 ceilings, matching its name.
- `o_12`/`o_13` "Tier 3 portion (uncapped/floored)" = `i_3 - o_10` then `max(., 0)` = 35000.00 — correctly the revenue above the tier‑2 ceiling, floored at zero to guard against negative brackets when revenue < tier‑2 ceiling. The naming distinction ("uncapped" vs "floored") accurately reflects the two-step derivation, with no silent relabeling.
- Commission legs: `o_14 = o_9 * i_6` (tier‑1 bracket amount × tier‑1 rate 0.05 = 2500), `o_15 = o_11 * i_7` (tier‑2 bracket amount × tier‑2 rate 0.08 = 4000), `o_16 = o_13 * i_8` (tier‑3 bracket amount × tier‑3 rate 0.12 = 4200). Each rate is paired with the bracket amount whose name and numeric derivation matches that same tier — no rate/bracket cross-wiring is present.
- `o_17` = `addBulk(o_14,o_15,o_16)` = 10700.00, consistent with a standard three-tier progressive commission schedule.

### Metadata review
Every variable and operation in this graph carries an empty `descriptor.meta` array — there are no `domainType`, `units`, or `taxStatus` tags anywhere in the trace. This means the specific mechanism called out in the attack definition (quietly stripping or altering `meta.domainType`/units/taxStatus while preserving `valueClass`) has no populated metadata to strip or alter in the first place; the absence is uniform across all nodes rather than selective, so it does not by itself constitute evidence of a targeted cast on any one node.

### Findings
No node was found where a variable's declared business role (as expressed by its `descriptor.name` and its position in the min/subtract/max/multiply/addBulk chain) diverges from the role assumed by its consuming operation. Rates map to their correct brackets, cumulative caps map to their correct ceilings, and the floor/uncapped naming is internally consistent with the two-step subtract→max derivation. No identity/wrapper operation is used to re-cast a variable's meaning, and no reused variable (`i_3`, `o_9`, `o_10`) is consumed under a conflicting interpretation in its second usage — `o_9` is used both as "revenue through tier 1" for the subtraction chain and as the tier‑1 bracket amount for commission, which is the same semantic quantity in both cases; `o_10` is likewise used consistently as the tier‑2 cumulative cap in both downstream subtractions.

## Anomaly Localization (If Detected)
None identified. All variable IDs (`i_1`–`i_8`, `o_9`–`o_17`) and operation IDs (`op_1`–`op_9`) were reviewed; no C_source != C_target divergence was found at any edge.

## Details
The graph implements a textbook three-tier progressive commission formula with fully transparent, named intermediate variables (bracket revenues, bracket portions, per-tier commissions, total). Every operation's formula (`min`, `subtract`, `max`, `multiply`, `addBulk`) is applied to arguments whose declared names match the operation's mathematical role, and the arithmetic itself replays correctly to the stored output values. The one structural gap — universally empty `descriptor.meta` across all nodes — removes the primary surface this attack vector normally exploits (metadata suppression/relabeling), but since it is uniform rather than selectively stripped from one node, it reads as a minimal/simplified provenance record rather than a targeted cast. Given the absence of any naming/derivation mismatch, no domain-transition (gross→net, local→base currency, etc.) is asserted or required here, and no evidence of a semantic cast was found. Confidence is not maximal only because the total absence of domain metadata throughout the graph limits how conclusively metadata-based tampering can be ruled out versus merely being undocumented in this trace format.