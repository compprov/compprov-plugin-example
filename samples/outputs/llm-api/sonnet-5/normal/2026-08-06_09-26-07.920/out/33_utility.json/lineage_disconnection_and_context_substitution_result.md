# Summary
- **Verdict**: CLEAN
- **Confidence score**: 91.0

## Anomaly Localization (If Detected)
None identified. Full forward propagation from all nine root INPUT variables (i_1 MathContext, i_2 zero floor, i_3 usage=1180, i_4 tier1 ceiling=500, i_5 tier2 ceiling=1000, i_6/i_7/i_8 tier rates, i_18 service charge) through every operation (op_1 … op_10) reproduces every stored OUTPUT value exactly, and every intermediate OUTPUT is consumed by the operation that the declared lineage says it should be consumed by:

- op_1 min(i_3,i_4)=500 → o_9 (matches)
- op_2 min(i_3,i_5)=1000 → o_10 (matches)
- op_3 subtract(o_10,o_9,mc)=500 → o_11 (matches, consumes o_10/o_9, not any hardcoded twin)
- op_4 subtract(i_3,o_10,mc)=180 → o_12 (matches)
- op_5 max(o_12,i_2)=180 → o_13 (matches)
- op_6 multiply(o_9,i_6,mc)=50.00 → o_14 (consumes computed o_9, not the numerically identical root i_4=500)
- op_7 multiply(o_11,i_7,mc)=70.00 → o_15 (matches)
- op_8 multiply(o_13,i_8,mc)=32.40 → o_16 (matches)
- op_9 addBulk(o_14,o_15,o_16,mc)=152.40 → o_17 (matches)
- op_10 add(o_17,i_18,mc)=164.90 → o_19 (matches, final reported bill)

## Details
The structural reference data shows only a single true leaf (`o_19`), which is the intended terminal output of the pipeline — it is not an orphaned computed sibling being bypassed, it is the final deliverable and is correctly the endpoint of the chain rather than an input to anything else. No name-collision leaves were flagged, and manual role-based scanning (checking for hardcoded roots that numerically or semantically shadow a computed sibling, e.g. i_4=500 vs o_9=500, or i_5=1000 vs o_10=1000) confirms that every downstream multiply/add/subtract operation consumes the actual `resultId` of its preceding computed step rather than the coincidentally-identical root constant. There is no case where a computed OUTPUT variable is left unconsumed while a same-named or same-valued root INPUT is routed into a downstream operation in its place — the two necessary conditions for Lineage Disconnection and Context Substitution (an orphaned computed variable + a substituted foreign value reaching the final output) are both absent here.

Every tier boundary, portion, and cost figure traces cleanly and exclusively back through the graph's own operation chain to the nine root inputs, with no rupture, hardcoded override, or context substitution detected at any of the ten operation nodes. The graph, while small, shows no signature of the described attack vector.