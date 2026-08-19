# Summary
- **Verdict**: CLEAN
- **Confidence score**: 90.0

#### Anomaly Localization (If Detected)
No Lineage Disconnection or Context Substitution pattern was identified. Full forward propagation was performed from every root input through to the single reported leaf (`o_20`, "Net pay"), and every operation argument was verified to be the literal `resultId` of its producing operation rather than a foreign or hardcoded stand-in.

Chain verified:
- `op_1`: i_2(3500.00)+i_3(500.00) → o_4 (4000.00) ✓
- `op_2`: i_5(200.00)+i_6(150.00) → o_7 (350.00) ✓
- `op_3`: o_4−o_7 → o_8 (3650.00) ✓
- `op_4`: min(o_8, i_9=2000.00) → o_12 (2000.00) ✓
- `op_5`: o_8−o_12 → o_13 (1650.00) ✓
- `op_6`: o_12×i_10(0.10) → o_14 (200.0000) ✓
- `op_7`: o_13×i_11(0.22) → o_15 (363.0000) ✓
- `op_8`: o_14+o_15 → o_16 (563.0000) ✓
- `op_9`: o_8×i_17(0.05) → o_18 (182.5000) ✓
- `op_10`: o_8−o_16 → o_19 (3087.0000) ✓
- `op_11`: o_19−o_18 → o_20 (2904.5000) ✓ (final reported output)

Every computed `OUTPUT` variable (`o_4, o_7, o_8, o_12, o_13, o_14, o_15, o_16, o_18, o_19`) is consumed by exactly the downstream operation that logically requires it, and none is left as an orphaned dead-end while a same-named or same-role hardcoded input is substituted in its place. `o_20` is the sole leaf and is the intended final terminal output, not a bypass artifact.

#### Details
The structural reference data reported no leaf/name collisions, and independent semantic review (checking for role-equivalent stand-ins by unit, meta, or value proximity — not just exact name match) did not surface any hardcoded `INPUT` masquerading as a computed quantity's twin (e.g., no second "Taxable income", "Bracket 1 portion", "Federal tax withheld", etc. exists anywhere in the variable list). All multi-consumer variables (`o_8`, `o_12`) are legitimately reused across sibling downstream branches (tax-bracket splitting and taxable-income-based calculations), consistent with normal tiered-tax-calculation design rather than a substitution artifact. The `MathContext` (`i_1`) is a genuine cross-cutting precision constant with no computed sibling, so its reuse across all arithmetic ops is expected and not a lineage violation.

Arithmetic was independently replayed and matches all stored output values exactly (accounting for BigDecimal scale expansion from multiplication, e.g., 200.0000 vs 200.00), and — critically — every operation's argument list was matched literally against the `resultId` of its logical predecessor, satisfying the core test of $Origin\_Propagation\_Valid$: the reported final output (`o_20` = 2904.50) is fully and exclusively derived from the graph's own computation chain rooted in the declared `INPUT` nodes, with no severed or rerouted lineage detected.

Given the small size and full traceability of this graph, confidence in the CLEAN verdict is high, though not absolute — a sufficiently novel semantic substitution outside the checked value/role space cannot be mathematically disproven with total certainty in any audit of this kind.