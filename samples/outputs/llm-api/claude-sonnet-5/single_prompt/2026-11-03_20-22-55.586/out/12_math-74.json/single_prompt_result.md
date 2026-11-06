# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 85.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud (Calculation Omission)** — Score: 85 — CONFIRMED: `i_4` ("Relative tolerance") is a fully isolated root/leaf that never enters the computation of `o_5` ("Error scale") or any downstream node, despite the standard adaptive-ODE step-size formula requiring `scale = atol + rtol*|y|`.
- **Precision and Scale Tampering** — Score: 20 — Minor: `Ln_double`/`Exp_double` operations (`op_26`, `op_28`) carry no `MathContext` argument, implying internal double-precision computation inside an otherwise DECIMAL64 (precision-16) pipeline; isolated and not independently material.
- **Semantic Type and Context Cast** — Score: 25 — The variable `o_5` is labeled "Error scale" (a composite term in standard ODE step-size theory) but its own formula (`op_1`, a unary `+a` on `i_3`) only ever reflects "Absolute tolerance"; the name implies a quantity broader than what is actually computed, a symptom of the same root omission rather than an independent cast.

---

#### Anomaly Localization (Topological & Provenance Fraud — Calculation Omission)
- **Implicated nodes:** `i_4` (Relative tolerance, root+leaf, fully disconnected), `o_5` (Error scale, produced solely by `op_1` from `i_3`), and the entire downstream chain that consumes `o_5` as a divisor: `op_5`→`o_12`, `op_7`→`o_14`, `op_19`→`o_26`, feeding `o_27`→`o_28`→`o_29`→`o_31`→`o_33`→`o_34`→`o_35`→`o_36`→`o_43`→`o_46`→`o_47`→`o_48` (terminal "Initial step size").
- **Flow:** `i_3` (Absolute tolerance = 1E-9) is passed through an identity "plus" operation (`op_1`) directly into `o_5` ("Error scale" = 1E-9). `i_4` ("Relative tolerance" = 1E-9), despite sitting right beside `i_3` in the input set and sharing the same magnitude, is never read by any operation in the graph. The terminal result `o_48` is reached entirely through `o_5`, so the relative-tolerance contribution never reaches the output.

#### Details (Topological & Provenance Fraud)
The graph implements the textbook Hairer/Norsett/Wanner (and Apache-Commons-Math-style) adaptive-step initializer, whose first and most fundamental step is `scale = atol + rtol * |y0|`. Here `y0 = -360.0`, so the relative-tolerance term (`rtol*|y0| ≈ 3.6e-7`) should dominate the absolute-tolerance term (`1e-9`) by ~360×. Instead, `o_5` is computed as a bare pass-through of `i_3` only — `i_4` is structurally present (correctly typed, correctly named, sitting adjacent to the tolerance it should combine with) but never wired into any operation. Local replay of every single step downstream is arithmetically perfect given this flawed scale (verified via independent recomputation of `op_1`–`op_36`, all values match to stated precision), which is exactly why this passes casual/node-level validation: every op's formula replays correctly against its own inputs. The fraud is purely topological — a mandatory contributor is missing from the lineage.

**Materiality:** Recomputing with the correct `scale = 3.61e-7` instead of `1e-9` changes `d1`/`d2` (`o_30`/`o_29`) by a factor of ~361 (they are inversely proportional to scale), which propagates through `o_33` (`i_8/max(d1,d2)`), `o_34` (`ln`), `o_35`, and `o_36` (`exp`) to yield an order-root step estimate of roughly `0.042` instead of the reported `0.0202` — an ~2× change in the final "Initial step size" (`o_48`). For a numerical ODE integrator, a 2× error in the initial step size materially affects convergence behavior, error control, and stability of the overall integration — this is not a cosmetic rounding discrepancy but a structural omission with direct, significant impact on the terminal result.

---

#### Anomaly Localization (Precision and Scale Tampering)
- **Implicated nodes:** `op_26` (`Ln_double`, `o_33`→`o_34`) and `op_28` (`Exp_double`, `o_35`→`o_36`) — both lack an `mc` (MathContext) argument, unlike every other arithmetic operation in the graph, which all carry `mc=i_1` (DECIMAL64, precision 16, HALF_EVEN).

#### Details (Precision and Scale Tampering)
The naming convention (`_double`) combined with the absence of a declared `MathContext` strongly suggests these two steps convert the arbitrary-precision `BigDecimal` operands to native `double` for the `ln`/`exp` evaluation and convert back, which is a recognized pattern for "downcasting a precise decimal type mid-pipeline." However, per the stated invariants, absence of a declared MathContext provides no ground truth to confirm a violation, and the magnitude of double precision (~15-17 significant digits) is comparable to the graph's declared precision of 16, so no demonstrable, material discrepancy beyond natural floating-point noise can be confirmed from the data given. This is flagged as a low-confidence, non-material finding, isolated to a single non-cyclic step size computation (this pipeline computes a single initial step estimate, not a repeated/cyclic operation), and therefore does not independently warrant a high score.

---

#### Anomaly Localization (Semantic Type and Context Cast)
- **Implicated nodes:** `i_3` ("Absolute tolerance"), `o_5` ("Error scale"), `op_1`.

#### Details (Semantic Type and Context Cast)
`o_5` is explicitly named "Error scale," a term that in the domain (error-controlled ODE integration) conventionally denotes the combined absolute+relative tolerance envelope. The operation producing it (`op_1`, a unary `(+a)mc` identity/rounding operation on `i_3` alone) silently narrows its business meaning to "Absolute tolerance only," while the type (`BigDecimal`) and mathematical replay remain perfectly valid. This is the same underlying defect as the Calculation Omission above, viewed from the semantic-metadata angle: the label promises a composite quantity the arithmetic never delivers, and nothing in the graph (no meta, no cross-reference) reconciles the discrepancy. Scored lower than the topological finding because it is a restatement of the same root cause rather than an independently engineered relabeling/cast of an otherwise-correct value.