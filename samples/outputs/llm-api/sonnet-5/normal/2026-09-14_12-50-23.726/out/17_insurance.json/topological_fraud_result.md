# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

## Audit Summary

This CPG models a three-line insurance adjudication pipeline (Collision, Comprehensive, Liability) that computes per-line payouts, aggregates them into a Total Payout, then derives a Reinsurance Recovery and a Net Loss. A full forward-propagation replay and a topological path-multiplicity analysis were performed against every candidate flagged by the structural reference data plus an independent scan for near-duplicate names/roles.

### Forward-propagation replay (arithmetic check)
- Collision: 8000.00 − 500.00 = 7500.00 → max(·,0)=7500.00 → ×0.80 = 6000.0000 → min(·,10000.00) = 6000.0000 ✔ (o_7→o_10)
- Comprehensive: 20000.00 − 1000.00 = 19000.00 → max(·,0)=19000.00 → ×0.90 = 17100.0000 → min(·,12000.00) = 12000.00 ✔ (o_15→o_18)
- Liability: 5000.00 − 250.00 = 4750.00 → max(·,0)=4750.00 → ×1.00 = 4750.0000 → min(·,6000.00) = 4750.0000 ✔ (o_23→o_26)
- Total payout o_27 = addBulk(o_10, o_18, o_26) = 6000.0000+12000.00+4750.0000 = 22750.0000 ✔ — each of the three line payouts contributes exactly once (M=1 for o_10, o_18, o_26 into o_27).
- Reinsurance recovery o_29 = o_27 × i_28(0.40) = 9100.000000 ✔
- Net loss o_30 = o_27 − o_29 = 13650.000000 ✔

All `resultId`s are consumed by the literal, correct downstream operation argument at every step (op_1…op_15); no step substitutes a foreign/hardcoded value for a computed sibling's `resultId`.

### Reference-set follow-up
- **Leaf set** `[o_30]`: o_30 is the legitimate final Net Loss output, correctly fed by o_27 and o_29 with no unconsumed mandatory contributor left dangling. No Calculation-Omission leaf was found among the per-line intermediates — every net-of-deductible, floored, coinsurance, and payout variable is consumed by exactly one downstream op.
- **Multi-consumed IDs** `[i_2, o_27]`:
  - `i_2` ("Zero (claim floor)") is consumed by three independent `max` flooring operations (op_2, op_6, op_10), one per claim line. This is a shared constant used identically to the MathContext pattern — it never itself flows additively into the terminal sum, so it does not create M>1 accumulation risk.
  - `o_27` ("Total payout") is consumed by op_14 (to compute recovery) and op_15 (to compute net loss = o_27 − o_29, where o_29 itself derives from o_27×0.40). This is the standard, explicitly-labeled reinsurance formula ("Reinsurance recovery rate (40%)" input, `(a-b)mc` subtract formula), i.e. Net = Gross − rate·Gross. This is a documented, auditable proportional relationship, not an undocumented double-count of an independent entity into the same aggregate — it satisfies the invariant's carve-out for documented splits rather than violating it.
- **Name-collision leaf set**: empty — confirmed no computed OUTPUT leaf is shadowed by a same-named/role INPUT root elsewhere, and a manual scan of all `Claim amount` / `Deductible` / `Coinsurance rate` / `Policy limit` variables shows each is distinguished by an explicit `claimType` meta tag (Collision/Comprehensive/Liability) and used exactly once in its own line's computation chain — no cross-line substitution or aliasing.
- **Root set**: all 15 root inputs are consumed exactly where their name/meta indicate (each per-line deductible, rate, and limit feeds only its own line; `i_1` MathContext and `i_2` zero are legitimately global constants; `i_28` recovery rate feeds only op_14). No orphaned root mirrors a computed sibling's name/role.

### Conclusion
No Calculation Omission (M=0), no undocumented Double Counting (M>1), and no Lineage Disconnection / Context Substitution (wrong-source M=1) was found. Every per-line payout reaches the Total Payout exactly once, and the only multi-path variable (`o_27`) is used in a transparently documented recovery/net-loss formula rather than a disguised duplication. $S_{dedup}$ of the three line payouts (6000.0000+12000.00+4750.0000=22750.0000) matches $S_{reported}$ (o_27=22750.0000).