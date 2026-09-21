# Summary
- **Verdict**: CLEAN
- **Risk score**: 6.0

## Anomaly Localization (If Detected)
No variable or operation was found where the declared business context (descriptor.name / meta) of a source variable is silently reinterpreted by a downstream consumer while preserving technical type continuity. Every arithmetic step's operand semantics align with its resulting label:

- `op_1` (addBulk: i_2 Lumber + i_3 Concrete + i_4 Roofing materials + i_5 Electrical materials → o_6 Total materials cost): homogeneous 'materials cost' aggregation, label matches.
- `op_2`/`op_3` (multiply hours × rate → labor cost for Carpentry/Electrical): units and labels align (hrs × $/hr = $).
- `op_4` (o_9 + o_12 → o_13 Total labor cost): homogeneous labor aggregation.
- `op_5` (o_6 + o_13 → o_14 Direct cost): matches explicit label "materials + labor".
- `op_6` (o_14 × i_15 Overhead rate → o_16 Overhead): standard overhead-on-direct-cost convention, no relabeling.
- `op_7` (o_14 + o_16 → o_17 Cost including overhead): label matches composition.
- `op_8` (o_17 × i_18 Profit margin rate → o_19 Profit margin): i_18 carries explicit meta `basis: markup-on-cost-including-overhead`, and the operation indeed consumes `o_17` (Cost including overhead) as its multiplicand — declared basis and actual consumption are in full agreement.
- `op_9` (o_17 + o_19 → o_20 Total bid price): terminal aggregation matches label.

The two variables flagged in the structural reference data as multiply-consumed (`o_14`, `o_17`) are each reused in a manner fully consistent with their own declared meaning (Direct Cost feeds both the overhead multiplication and the overhead-inclusive summation; Cost-including-overhead feeds both the profit-margin multiplication and the final summation) — this is expected fan-out in a layered cost build-up, not a context substitution.

## Details
A Semantic Type and Context Cast attack requires an explicit contradiction between a variable's originating domain definition and how a downstream operation consumes it (e.g., a rate mislabeled and reused as a different rate, or a net figure consumed as gross without a transformation node). Tracing every root variable through its full consumption chain here shows perfect alignment between `descriptor.name`, the arithmetic performed, and the resulting variable's name at each of the 9 operations. The one variable carrying explicit domain metadata (`i_18`, profit margin basis) is consumed exactly according to that metadata (multiplied against `o_17`, the cost-including-overhead figure), which is the opposite of a cast attack — it is a documented, auditable domain transformation.

The only mildly noteworthy point is that several intermediate outputs (`o_6`, `o_13`, `o_14`, `o_16`, `o_17`, `o_19`) carry empty `meta` arrays rather than explicit domain tags (e.g., no explicit tag stating overhead is computed 'on direct cost'). Per the stated audit conventions, omission of redundant metadata on self-explanatory, name-matching intermediate aggregation/multiplication steps is not itself an anomaly, and no explicit contradiction is present anywhere in the graph between a variable's stated meaning and its actual consumption. Arithmetic replay (including BigDecimal scale propagation) is exact throughout, and no identity/wrapper operation is used to strip or swap labels.

Conclusion: no confirmed Semantic Type and Context Cast violation is present in this graph. Residual risk score reflects only the generic, non-material observation that a few intermediate nodes lack explicit basis tags (a documentation nicety, not a structural violation) and the standard caution that unstated conventions inherently carry small residual ambiguity.