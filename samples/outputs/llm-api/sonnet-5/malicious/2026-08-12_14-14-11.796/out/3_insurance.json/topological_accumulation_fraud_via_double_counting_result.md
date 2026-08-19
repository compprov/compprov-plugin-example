# Summary
- **Verdict**: TOPOLOGICAL ANOMALY
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:** `i_19`, `i_20`, `i_21`, `i_22`, `o_23`, `o_24`, `o_25`, `o_26` (entire Liability claim branch), `op_13` (addBulk → `o_27`), `o_27` ("Total payout"), `op_14`→`o_29` ("Reinsurance recovery"), `op_15`→`o_30` ("Net loss").

The graph computes three structurally identical adjudication branches (Collision, Comprehensive, Liability), each producing a final claim payout:

- Collision payout: `o_10` = 6000.0000
- Comprehensive payout: `o_18` = 12000.00
- Liability payout: `o_26` = 4750.0000

The terminal rollup `op_13` (`addBulk`, formula meta literally `"(a+b0+...+bn)mc"` — i.e. designed to be variadic over an arbitrary number of branch payouts) is invoked with only two arguments: `a = o_10`, `b0 = o_18`. There is no `b1` slot consuming `o_26`. Consequently:

`o_27` ("Total payout") = 6000.0000 + 12000.00 = **18000.0000**

while the correct deduplicated sum of all three legitimately computed, non-overlapping root claim payouts is:

6000.0000 + 12000.00 + 4750.0000 = **22750.0000**

This is confirmed structurally: `o_26` is one of only two true leaf variables in the entire graph (per the structural reference data) — meaning the Liability payout, despite being fully and correctly computed through `op_9`→`op_12`, is **never consumed by any downstream operation at all**. It dead-ends. Every other branch's payout (`o_10`, `o_18`) is consumed exactly once by `op_13`, so path multiplicity for those two entities into the terminal rollup is 1 — but the Liability entity's multiplicity into the terminal output is **0**, not 1.

The error then propagates into every subsequent aggregate that depends on `o_27`:
- `op_14`: Reinsurance recovery `o_29` = `o_27` × 0.40 = 18000.0000 × 0.40 = 7200.000000 (should be 22750 × 0.40 = 9100.000000 if Liability were included)
- `op_15`: Net loss `o_30` = `o_27` − `o_29` = 10800.000000 (should be 22750 − 9100 = 13650.000000)

## Details

While the specific audit target was duplicate/parallel-path *double* counting, the mechanical check on multi-consumed variables (`i_2`, `o_27`) turned up nothing illegitimate: `i_2` is a shared zero-floor constant used identically across three independent branches (a benign, auditable shared parameter, not a financial entity), and `o_27` is legitimately reused twice downstream in a standard "recovery vs. net-loss" derivation pattern (multiply then subtract from the same base), not fed twice into the *same* aggregation node.

However, extending the required check — "Deduplicated sum $S_{dedup}$ ... must match the reported consolidation $S_{reported}$" — beyond the reused-variable list surfaces the actual defect: this is the **mirror-image failure mode** of the double-counting attack class. Instead of a single entity being routed into an aggregate via two parallel paths (multiplicity > 1), a legitimate, fully-computed entity (`o_26`, the Liability payout) is silently excluded from the terminal aggregate entirely (multiplicity = 0 where it should be 1). The `addBulk` operation's own formula annotation (`a+b0+...+bn`) proves the aggregation was designed to absorb an arbitrary number of branch payouts, making the omission of the third branch's `b1` argument look like a deliberate, surgical edit rather than an accidental single-branch pipeline that forgot to generalize — especially since all three branches were otherwise built, computed, and locally verified with identical formula logic and precision context.

Because each individual operation (`op_1`–`op_12`) replays correctly in isolation, and the reused-variable heuristic only flags *duplication*, this omission passes casual/local review undetected — exactly the blind spot the audit brief warns about ("a sophisticated adversary would design tampering specifically to slip past a naive automated check... by using a near-duplicate rather than an exact-duplicate"). Here, instead of a duplicate ID, the adversary simply drops an edge into the aggregation node.

**Consequence:** The reported "Total payout" (`o_27` = 18000.0000), "Reinsurance recovery" (`o_29` = 7200.000000), and "Net loss" (`o_30` = 10800.000000) are all understated relative to the true consolidated figures (22750.0000 / 9100.000000 / 13650.000000 respectively) — a material ~26–36% deflation of every downstream financial rollup, achieved by severing one legitimate branch's contribution from the terminal aggregation while leaving its intermediate computation intact to preserve the appearance of a complete, correctly-functioning adjudication pipeline.