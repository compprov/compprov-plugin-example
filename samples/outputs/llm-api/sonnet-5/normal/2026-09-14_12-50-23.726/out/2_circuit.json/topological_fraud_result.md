# Summary
- **Verdict**: CLEAN
- **Risk score**: 6.0

## Summary
This CPG models a series–parallel resistor network (R1+R2 in series, combined with the parallel combination of R3‖R4), then derives current, power (P = I·V), and a power cross-check (P = I²·Rtotal). The audit specifically targeted path-multiplicity violations (M=0 omission, M>1 double counting) and lineage/context substitution (M=1, wrong source), using the supplied structural reference sets as a starting point.

#### Anomaly Localization (If Detected)
No confirmed Topological & Provenance Fraud was found. Specifically:

- **Leaves (o_13, o_16):** Both are legitimate dual terminal outputs — Power computed via P=I·V (op_7) and independently cross-checked via P=I²·Rtotal (op_9). Neither is an unconsumed 'dropped' mandatory contributor (M=0); they are themselves the final reported results of two independent, intentional verification formulas. No leaf shares a name with another variable (confirmed by the reference set), and no plausible near-duplicate stand-in was found among roots or intermediates.
- **Multiply-consumed IDs (i_4, i_5, i_6, o_11, o_12):** Each instance of reuse was traced end-to-end:
  - i_4 (R3) and i_5 (R4) are each consumed by op_2 (product) and op_3 (sum), both of which converge deterministically at op_4 to compute the single correct parallel-resistance value (o_10 = R3·R4/(R3+R4)). This is the mathematically required shape of the product-over-sum parallel formula, not duplication into a summed aggregate.
  - i_6 (V) is consumed by op_6 (I=V/Rtotal) and op_7 (P=I·V) — required by the P=V²/R identity, not a repeated addend.
  - o_11 (Rtotal) is consumed by op_6 (current calc) and op_9 (I²·Rtotal cross-check) — two independent, non-summing verification branches, each terminating at a distinct leaf (o_13 vs o_16), not converging into one aggregation twice.
  - o_12 (I) is consumed by op_7 (P=IV) and op_8 (I² for cross-check) — same pattern, feeding two distinct terminal outputs, not double-counted into one.
  None of these reuses feed the *same* terminal aggregation twice; each pair of paths terminates at a distinct, separately-reported output, satisfying $S_{dedup} = S_{reported}$ for each output independently.
- **Root inputs (i_1–i_6, i_14):** No root input's name, unit, or role mirrors a computed sibling's descriptor (e.g., no INPUT named 'Total resistance' or 'Current' shadowing o_11/o_12). No hardcoded literal masquerades as a computed quantity.
- **Arithmetic replay:** o_7=250, o_8=180000, o_9=900, o_10=200, o_11=450, o_12≈0.26667, o_13=32, o_15≈0.071111, o_16≈32 — all values replay correctly against the stated formulas from true root inputs (i_2..i_6), confirming Origin_Propagation_Valid holds along the full chain to both terminal outputs.

#### Details
The graph implements two textbook-correct series-parallel resistor formulas plus a self-consistency check (P=IV vs P=I²R), which structurally requires reusing R3, R4, V, I, and Rtotal more than once. This reuse pattern superficially resembles the M>1 double-counting signature the reference sets flag, but in every case the reused variable feeds two *distinct*, independently-reported terminal outputs via a single correct formula path each — not the same aggregation twice. No leaf was found abandoned as a dropped mandatory contributor, and no root INPUT was found substituting for an orphaned computed sibling. Given the thoroughness of this trace and the absence of any structural or naming evidence of tampering, the residual risk score reflects only the inherent complexity of the multi-path reuse pattern (which merits continued monitoring) rather than any confirmed violation.