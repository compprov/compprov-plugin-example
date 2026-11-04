# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 80.0

#### Per-Vector Verdict

| Attack Vector | Score | Verdict |
|---|---|---|
| Topological & Provenance Fraud | 5 | No omission, substitution, or double counting detected — all factors 2–17 are chained into the single terminal leaf o_55 via a clean sequential accumulation. |
| Precision and Scale Tampering | 80 | **Confirmed material defect** — the DECIMAL64 (precision=16) MathContext used for the entire ln-sum accumulation is too coarse to survive the subsequent `Exp` amplification, producing a terminal "exact" factorial that is numerically wrong by 3 units. |
| Semantic Type and Context Cast | 5 | Naming/metadata are internally consistent (Factor → ln → running log sum → exp → round-to-nearest → Factorial); no business-context relabeling detected. |

---

#### Anomaly Localization (Precision and Scale Tampering)

- **Root inputs**: `i_4`…`i_49` ("Factor, 2" … "Factor, 17")
- **Ln chain**: `op_1,op_3,op_5,...,op_31` → `o_5,o_8,o_11,...,o_50` (raw double-precision `Ln_double`, no `mc` argument)
- **Accumulation chain**: `op_2,op_4,...,op_32`, each an `add(a,b,mc=i_1)` with `i_1` = MathContext(precision=16, HALF_EVEN), terminating at `o_51` = 33.50507345013690 (≈ ln(17!))
- **Exponentiation**: `op_33` (`Exp_double`, no `mc`) → `o_53` = 355687428096003.2
- **Round-to-nearest emulation**: `op_34` adds `i_52` (0.5) under `i_1` → `o_54` = 355687428096003.7; `op_35` applies `i_2` (precision=0, FLOOR) → **`o_55` = 355687428096003**

**True value**: 17! = 355687428096000 (exact, verifiable by direct integer multiplication). The reported terminal output differs by **+3**, i.e. the computation is objectively wrong, not merely a rounding-convention artifact.

#### Details (Precision and Scale Tampering)

Each individual `add` step replays correctly against its own declared `MathContext` (`i_1`, precision 16, HALF_EVEN) — local verification of every operation passes. The flaw is systemic: the algorithm computes `ln(17!)` as a running sum of 16 individually-rounded logarithms, each truncated to 16 significant digits, and then recovers the integer factorial by exponentiating the sum and rounding to the nearest integer (`+0.5` then `FLOOR`). This "round half up via floor" technique is only valid if the absolute error of `exp(sum of logs)` relative to the true factorial is kept well under 0.5. Because `exp` amplifies any error in its argument multiplicatively (by a factor of ~e^33.5 ≈ 3.5×10^14), the per-step rounding noise introduced by capping precision at 16 significant digits accumulates across 16 additions and is amplified upon exponentiation, producing an absolute error of 3.2 in `o_53` — far beyond the 0.5 threshold the rounding step assumes is safe. The result is a final "factorial" that is off by 3, i.e. a deterministic, 100%-certain numeric answer is rendered incorrect despite every intermediate step being locally consistent with its declared context. This precisely matches the invariant: "a context ... no coarser than the quantity's natural resolution" — here, DECIMAL64 (16 significant digits) is materially too coarse for a quantity (the log of a 15–16-digit integer) whose recovery via `exp` requires a much larger safety margin of guard digits. Per the Materiality Override, this is flagged as high-risk regardless of the fact that this specific run is a single, non-repeating 17! computation — a wrong terminal result in an exact combinatorial calculation is a critical, provable flaw, and if this log-sum-exp pattern is reused for other factorial/combinatorics calls (clearly a parameterizable, scalable pattern — `n` can be arbitrary), the same precision shortfall will recur and can produce even larger absolute errors for larger `n`, amplifying the practical impact across any downstream consumer that treats this pipeline's output as an exact integer.

---

#### Anomaly Localization (Topological & Provenance Fraud)
None. The single leaf `o_55` is the clear terminal output; all 16 "Factor" roots (`i_4` through `i_49`, values 2–17) are each run through a dedicated `Ln_double` operation and folded into the running sum via exactly one `add` each, with no repeated arguments, no multi-consumed variables, and no duplicate-named leaves. The chain is a textbook, non-redundant log-sum-exp factorial computation.

#### Details (Topological & Provenance Fraud)
No evidence of calculation omission, lineage disconnection/context substitution, or double counting was found. The structural reference data (empty multi-use, repeated-argument, and duplicate-name sets) is corroborated by manual trace of the graph.

---

#### Anomaly Localization (Semantic Type and Context Cast)
None. `descriptor.name` values are coherent and consistent through every hop: "Factor, n" → "ln n" → "Log sum after +ln n" → "exp(log sum)" → "exp(log sum) + 0.5" → "Factorial 17!". The `MathContext` variables are clearly and accurately labeled for their actual role (`i_1` = "Computation precision (DECIMAL64)" used for the additions; `i_2` = "Round down to integer (floor)" used for the final rounding step), and their usage matches their stated purpose.

#### Details (Semantic Type and Context Cast)
No node exhibits a mismatch between its originating business/domain label and its downstream consumption; no implicit relabeling, unit swap, or gross/net style substitution is present. This vector is clean.