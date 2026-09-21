# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

## Audit Summary

This CPG models a standard 1-D elastic collision physics computation (conservation of momentum, canonical elastic collision velocity formulas). A full trace of business context (`descriptor.name`/units) from root INPUT variables through every OUTPUT-producing operation was performed, specifically hunting for semantic mismatches where technical type continuity (`BigDecimal -> BigDecimal`) could mask a covert business-meaning swap.

### Trace of Domain Context Continuity

- `i_2` (Mass 1, m1, kg) and `i_4` (Mass 2, m2, kg) are consistently used as masses throughout: `o_7` (m1+m2), `o_8`/`o_14` (mass differences), `o_10`/`o_16` (2×mass scalings), `o_20`/`o_23` (momentum terms). No cross-labeling of m1 as m2 or vice versa was found — each operand slot (`a`/`b`) in `subtract`/`multiply` nodes maps to the correct source variable matching the resulting descriptor's stated formula.
- `i_3` (v1, m/s) and `i_5` (v2, m/s) are consistently consumed as velocities in momentum (`o_20`, `o_21`) and numerator terms (`o_9`, `o_11`, `o_15`, `o_17`) that structurally match the canonical elastic-collision equations: v1' = [(m1-m2)v1 + 2m2v2]/(m1+m2), v2' = [(m2-m1)v2 + 2m1v1]/(m1+m2).
- `i_6` ("Constant 2") is used identically as a pure scalar multiplier in both `o_10` (2×m2) and `o_16` (2×m1) — no semantic re-purposing detected.
- `o_13` (v1', after) and `o_19` (v2', after) are fed into `o_23`/`o_24` explicitly as "after collision" velocities multiplied against the correct corresponding mass, producing `o_25` (momentum after) which numerically equals `o_22` (momentum before) — confirming both arithmetic correctness and that no Gross/Net-style silent domain reinterpretation occurred (e.g., a "before" value silently consumed as "after", or vice versa).
- `i_1` (MathContext) is used uniformly as the `mc` argument across all 19 operations, with no substitution or context drift.

### Findings

No instance was found where a variable's originating `descriptor` (mass, velocity, momentum, scalar constant) conflicts with the business assumption of a downstream consuming operation. Every `subtract`/`multiply`/`add`/`divide` node's declared formula and result-variable name are consistent with the operand variables' declared originating meaning. No identity/wrapper operation silently re-casts one physical quantity (e.g., a "Standard Risk Multiplier"-style relabeling) into another domain entity. Momentum conservation (`o_22` = `o_25` = 2.00 kg·m/s) is preserved end-to-end, which would not hold under a stealth semantic substitution of mass/velocity operands.

Minor observation: several intermediate variables (`o_9`, `o_11`, `o_12`, etc.) carry empty `descriptor.meta` arrays, but their `descriptor.name` fields fully and unambiguously state their business meaning, and per audit guidance this is a standard, benign omission of redundant metadata rather than an indicator of tampering.

### Conclusion

No Semantic Type and Context Cast Attack pattern was identified. Business context (C_source) is consistently preserved into C_target at every node in this graph; type continuity and semantic continuity move together rather than diverging. This graph appears clean with respect to the specified attack vector.