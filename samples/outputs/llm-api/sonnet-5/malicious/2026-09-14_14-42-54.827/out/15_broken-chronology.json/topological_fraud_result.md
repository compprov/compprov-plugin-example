# Summary
- **Verdict**: CLEAN
- **Risk score**: 8.0

## Summary
This CPG models a 10-leg ETH/USDC option book payout on 2026-06-30. Each leg follows an identical 3-operation chain (subtract → max(·,0) → multiply-by-size), and all 10 leg payouts are combined in a single terminal `addBulk` (op_31) into `o_54` ("Total payout in USDC"). A full audit for the three Topological & Provenance Fraud sub-patterns (Omission M=0, Double-Counting M>1, Lineage Disconnection M=1-wrong-source) was performed.

### Path multiplicity check (M=0 / M>1)
All 10 legs were traced end-to-end:
- [0] CALL K=4630: i_3,i_4→op_1→o_6→op_2→o_7→op_3→o_8 → addBulk arg `a`
- [1] PUT K=4710: i_9,i_3→op_4→o_11→op_5→o_12→op_6→o_13 → addBulk `b0`
- [2] CALL K=4670: →o_16→o_17→o_18 → addBulk `b1`
- [3] PUT K=4550: →o_21→o_22→o_23 → addBulk `b2`
- [4] PUT K=4730: →o_26→o_27→o_28 → addBulk `b3`
- [5] CALL K=4630: →o_31→o_32→o_33 → addBulk `b4`
- [6] CALL K=4710: →o_36→o_37→o_38 → addBulk `b5`
- [7] PUT K=4690: →o_41→o_42→o_43 → addBulk `b6`
- [8] PUT K=4590: →o_46→o_47→o_48 → addBulk `b7`
- [9] PUT K=4550: →o_51→o_52→o_53 → addBulk `b8`

Each payout output is consumed exactly once by op_31, and op_31's 10 slots (`a`+`b0..b8`) account for exactly the 10 legs — no leg is missing (M=0) and no payout variable is fed into the terminal aggregation more than once (M>1). The only multi-consumed non-MathContext variables (`i_2` "Zero floor" and `i_3` "spot price") are genuine shared parameters used as arguments *inside* each leg's own subtract/max step, not values that are themselves summed into the terminal result twice — this is the benign shared-basis pattern, not accumulation fraud.

### Lineage / substitution check (M=1, wrong source)
The only leaf is `o_54`, and it is the legitimate terminal output. No leaf/output shares an exact display name with another variable (per structural data), and manual inspection of every root INPUT found no root that mirrors a computed sibling's name, units, or role in a way suggesting a hardcoded stand-in was substituted for a computed variable. Every op's `resultId` is consumed only by its documented downstream consumer(s); no operation swaps in a different variable ID at the last mile.

### Arithmetic re-derivation
Full recomputation of each leg confirms all intermediate and payout values are numerically correct (e.g., leg [4]: 4730-4650=80, max(80,0)=80, 80×7.8507=628.056 ✓.; legs [2],[3],[6],[8],[9] correctly resolve to 0 since OTM). Summing all 10 payouts: 137.278+170.586+0+0+628.056+88.426+0+239.364+0+0 = 1263.7100, which exactly matches the reported `o_54` value of 1263.7100. $S_{dedup} = S_{reported}$ holds.

### Noted but non-violating observation
Two pairs of legs share the same strike/type (CALL K=4630 at legs [0]/[5]; PUT K=4550 at legs [3]/[9]) but with distinct sizes (6.8639 vs 4.4213; 2.8299 vs 5.9155) and distinct variable IDs/index labels. This is consistent with multiple legitimate tranches/lots at the same strike (common in real option books) rather than the same root entity being re-entered under a new ID — the sizes differ, so this is not literal duplication of a single position, and it does not affect path multiplicity of any single entity. It is flagged only as a minor documentation gap (no metadata explaining the multiple tranches), not as confirmed tampering.

## Conclusion
No Calculation Omission, no Double-Counting, and no Lineage Disconnection/Context Substitution was found. Every mandatory contributor (each option leg's payout) reaches the terminal `addBulk` exactly once, forward propagation from true root inputs reproduces the reported terminal value exactly, and no hardcoded stand-in supersedes a computed sibling. Risk is low, retained slightly above zero only for the undocumented duplicate-strike tranches noted above.