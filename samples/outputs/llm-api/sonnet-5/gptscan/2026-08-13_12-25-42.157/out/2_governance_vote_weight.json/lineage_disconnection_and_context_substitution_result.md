# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

#### Anomaly Localization (If Detected)
None detected. The graph consists of exactly two root INPUT variables (`i_1` = 250000, `i_2` = 1000000), a single operation (`op_1`, `add`, formula `a+b`) consuming both as `a` and `b`, and a single OUTPUT variable (`o_3` = 1250000) that is precisely `250000 + 1000000`. There is no second computed variable competing for the role of `o_3`, no name collision (the structural reference data confirms zero leaf name collisions), and no orphaned computed sibling that was bypassed in favor of a hardcoded stand-in.

#### Details
This CPG is minimal in size (3 variables, 1 operation), leaving very little surface for a Lineage Disconnection / Context Substitution attack to hide in:

- **Root reachability**: Both root inputs (`i_1`, `i_2`) are directly and exclusively consumed by the sole operation `op_1`, whose `resultId` is `o_3`, the sole OUTPUT. Forward propagation from roots to the reported output is direct, single-hop, and unambiguous — $O_{derived} = O_{reported} = 1250000$.
- **No competing computed sibling**: The attack signature requires a computed variable that is orphaned (unconsumed) while a same-named or same-role hardcoded/foreign variable is substituted downstream in its place. Here there is only one OUTPUT and no duplicate or near-duplicate variable (by name, meta, or value) anywhere in the variable list that could serve as a disguised substitute. The leaf-name-collision set returned empty, and manual role-based scanning (checking `descriptor.name`, `meta`, and value proximity across all three variables) surfaces no plausible stand-in.
- **No unconsumed computed intermediate**: `o_3` is a leaf (never consumed further), but it is also the terminal reported output itself — not an intermediate that was quietly bypassed. There is no second 