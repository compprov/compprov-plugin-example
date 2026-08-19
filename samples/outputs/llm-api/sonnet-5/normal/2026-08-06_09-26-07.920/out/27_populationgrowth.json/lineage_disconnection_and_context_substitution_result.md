# Summary
- **Verdict**: CLEAN
- **Confidence score**: 90.0

## Audit Summary

This CPG models three independent exponential-growth checkpoint calculations (t1, t2, t3) sharing the same root inputs (N0 = i_2, r = i_3, MathContext = i_1) and per-checkpoint time inputs (i_4, i_5, i_6).

### Forward Propagation Trace

**Checkpoint 1:**
- op_1: multiply(i_3=0.03, i_4=5, mc=i_1) → o_7 = 0.15 ✓ (r×t1)
- op_2: Exp(o_7) → o_8 = 1.161834242728283 ✓ (e^0.15, correct to 16 sig figs)
- op_3: multiply(i_2=1000000, o_8, mc=i_1) → o_9 = 1161834.242728283 ✓

**Checkpoint 2:**
- op_4: multiply(i_3, i_5=10, mc=i_1) → o_10 = 0.30 ✓
- op_5: Exp(o_10) → o_11 = 1.3498588075760032 ✓ (e^0.30)
- op_6: multiply(i_2, o_11, mc=i_1) → o_12 = 1349858.807576003 ✓ (rounded per 16-digit precision context)

**Checkpoint 3:**
- op_7: multiply(i_3, i_6=20, mc=i_1) → o_13 = 0.60 ✓
- op_8: Exp(o_13) → o_14 = 1.8221188003905089 ✓ (e^0.60)
- op_9: multiply(i_2, o_14, mc=i_1) → o_15 = 1822118.800390509 ✓

Every downstream multiply/Exp operation consumes the exact `resultId` of its immediate predecessor (o_7→o_8→o_9; o_10→o_11→o_12; o_13→o_14→o_15). No operation substitutes a foreign, hardcoded, or unverified variable in place of the properly computed intermediate.

### Structural Reference Cross-Check

- **Leaf outputs** (o_9, o_12, o_15): these are the three reported "Population at t_k" results — the intended final terminal outputs of three parallel, non-competing checkpoint branches. Each is the last step of its own fully-connected chain back to the shared root inputs (i_1, i_2, i_3) plus its own checkpoint time input (i_4/i_5/i_6). There is no computed sibling elsewhere in the graph representing the same quantity (e.g., no second "Population at t1" variable) that was used downstream in place of o_9, o_12, or o_15 — so labeling them terminal is not a rationalization here; it reflects the graph's actual design (three independent checkpoints, not a multi-stage pipeline where one output should feed the next).
- **Name-collision set**: empty, and manual review of all variable `descriptor.name` fields confirms no near-duplicate, reworded, or role-equivalent naming that could indicate a disguised hardcoded stand-in (e.g., no second "r", "N0", or duplicate exponent/population variable competing for the same argument slot).
- **Multiply-consumed variables** (i_2, i_3): both are legitimately reused root constants (N0 and r) across the three independent checkpoint branches — expected and transparent, not evidence of substitution.

### Numerical Verification

All intermediate and final values recompute correctly under DECIMAL64-equivalent precision (16 significant digits, HALF_EVEN): 0.03×5=0.15, e^0.15≈1.161834242728283, 1,000,000×that = 1,161,834.242728283; similarly for t2 and t3 branches, with o_12 and o_15 showing expected rounding to 16 significant digits. No value shows the telltale sign of a subtly divergent, independently-sourced constant approximating a computed value.

### Conclusion

No evidence of Lineage Disconnection or Context Substitution was found. Each reported OUTPUT (o_9, o_12, o_15) is fully and exclusively derived from the graph's own computation chain, traceable back to the true root INPUT nodes (i_1–i_6), with no orphaned computed twin bypassed in favor of a hardcoded or foreign value at any step.