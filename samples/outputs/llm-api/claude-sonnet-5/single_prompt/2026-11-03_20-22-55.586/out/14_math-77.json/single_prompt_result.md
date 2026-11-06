# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 95.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud — Double Counting**: Score 95/100. CONFIRMED — the "running maximum" accumulator is fed into itself through a `max` then `add` pair every iteration, causing the chain to double instead of track a true maximum, inflating the final result ~21x (128 vs. true L∞ norm of 6).
- **Precision and Scale Tampering**: Score 8/100. No finding — the single MathContext (`DECIMAL64`, precision 16, HALF_EVEN) is applied consistently and every `add`/`abs` replays exactly against it; no rounding-mode or scale manipulation detected.
- **Semantic Type and Context Cast**: Score 15/100. No independent cast attack — the mislabeling of `o_6`/`o_10`/`o_14`/`o_18`/`o_22` as "Running maximum" is a symptom of the Vector 1 arithmetic/double-counting flaw rather than a separate business-context relabeling of an otherwise-correct value.

#### Anomaly Localization (Topological & Provenance Fraud — Double Counting)
Implicated variables: `o_6`, `o_9`, `o_10`, `o_13`, `o_14`, `o_17`, `o_18`, `o_21`, `o_22`, `o_25`, `o_26`.
Implicated operations: `op_3`, `op_5`, `op_6`, `op_8`, `op_9`, `op_11`, `op_12`, `op_14`, `op_15`, `op_17`, `op_18`.

The intended algorithm for an L∞ norm is `running_max = max(running_max, |component|)`. Instead, each iteration after the first computes:
```
o_9  = max(o_6, o_8)     // = o_6 whenever |component| <= running max
o_10 = add(o_6, o_9, mc) // = o_6 + o_6 = 2*o_6 whenever o_9 == o_6
```
Trace of the actual values confirms this: component abs values are `[4, 0, 3, 1, 6, 3]`, so the true running max sequence should be `4, 4, 4, 4, 6, 6`. Instead the graph produces `o_6=4, o_10=8, o_14=16, o_18=32, o_22=64, o_26=128` — the accumulator doubles at every step regardless of whether a new maximum actually appears, because the "max" result (`o_9`, `o_13`, `o_17`, `o_21`, `o_25`) is re-added to the very same running-max variable that fed it (`o_6`, `o_10`, `o_14`, `o_18`, `o_22` — all flagged in structural reference data as multi-consumed). This matches the Double Counting invariant precisely: `add(x, max(x,y))` collapses to `add(x,x)` whenever `x>=y`, which is a disguised double count with no stated multiplier (no "doubled"/"2x" semantics anywhere in the graph) and no matching structural justification (there are not two independent periods or branches to sum — there is only one scalar running maximum).

#### Details (Topological & Provenance Fraud — Double Counting)
Local replay of every single operation passes: each `abs`, `max`, and `add` node is internally consistent with its own declared MathContext and arguments, so naive per-step validation shows no error. The flaw only becomes visible when tracing the *chain* of the running-maximum variable across iterations: the same scalar (`o_6`, then `o_10`, etc.) is consumed both as the left operand of the `add` and, indirectly through the `max`, reinjected as the result of comparing itself against the new component. Because the `max` degenerates to an identity pass-through whenever the running value already dominates the new component (true for every step after the first in this vector, since the true maximum 6 only appears at index 4, and even then the pattern still doubles via `o_18+o_21`), the accumulator grows geometrically (`4 -> 8 -> 16 -> 32 -> 64 -> 128`) instead of staying flat at the correct maximum. The final reported "L-infinity norm" (`o_26` = 128) is therefore wrong by more than 20x versus the mathematically correct answer of 6 (`max(|-4|,|0|,|3|,|1|,|-6|,|3|)`). This is a severe, material arithmetic/provenance defect — every downstream consumer of this pipeline's output would receive a grossly inflated norm, which in real financial/engineering risk-metric contexts (e.g., worst-case exposure, max displacement, max drawdown) would represent a catastrophic overstatement, materially misleading any decision built on it.