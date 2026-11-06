# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 95.0

#### Per-Vector Verdict

| Attack Vector | Sub-pattern | Score | Verdict |
|---|---|---|---|
| 1. Topological & Provenance Fraud | Lineage Disconnection / Context Substitution | 95 | **CONFIRMED** — the true root coordinate (`i_2`) is never consumed anywhere in the graph; the function-residual value (`i_5`) is substituted for it in the final branch computation, corrupting the terminal "Root" output. |
| 2. Precision and Scale Tampering | — | 3 | Clean — every operation reproduces its declared `MathContext` (precision 16, HALF_EVEN) exactly; no rounding-mode drift, no premature truncation, no scale mismatch detected. |
| 3. Semantic Type and Context Cast | — | 90 | **CONFIRMED** — `o_20` ("Lower-endpoint branch value") is typed and replays as a `BigDecimal` consistent with its formula, but its actual business content is a function residual (`sin(min)`), not the endpoint coordinate its name/role demands. |

Overall risk reflects the maximum across vectors: **95**.

---

#### Anomaly Localization (Attack Vector 1 — Lineage Disconnection / Context Substitution)

- **Root never consumed:** `i_2` ("Lower endpoint, min" = `3.141592653589793`, i.e. π, the actual root candidate) is a root variable and a declared leaf — it appears in no operation's `arguments` anywhere in the graph (confirmed against every op_1…op_13 argument list).
- **Substitute path:** `op_12` (`multiply`) computes `o_20` ("Lower-endpoint branch value") from `o_19` (branch-taken flag) × **`i_5`** ("f(min) = sin(min)", the *function value* at the endpoint, ≈1.2246e-16) — not from `i_2` (the endpoint itself).
- **Contrast with the parallel, correctly-built branch:** `op_9` computes `o_17` ("Initial-guess branch value") as `o_12` (within-accuracy flag) × **`i_3`** ("Initial guess", the actual x-value). This is the correct pattern: flag × coordinate. `op_12` breaks this exact pattern by using flag × function-value instead of flag × coordinate (`i_2`).
- **Terminal corruption:** `op_13` (`add`) sums `o_17` (0.0) + `o_20` (1.2246...E-16) → `o_21` ("Root") = `1.224646799147353E-16`. The reported "Root" of a `sin(x)=0` search landing near 3.5 is therefore ~1.22e-16 — essentially zero — instead of the correct answer, π ≈ `3.141592653589793`, which was sitting unused in `i_2` the entire time.

#### Details (Vector 1)
All of the boolean-flag machinery (`op_1`–`op_11`) is arithmetically flawless and correctly determines that the initial guess should be rejected (`o_18=1`) and the lower endpoint should be accepted (`o_16=1`, `o_19=1`). This flawless surrounding logic is exactly what makes the attack dangerous: a casual auditor who only replays each operation's formula against its own inputs will find every single step "passes." The fraud is purely a lineage substitution at `op_12`: the operation that should pull in the accepted endpoint's *coordinate* (`i_2`) instead pulls in that endpoint's *function value* (`i_5`). Because `i_2` is a root variable with full, unambiguous descriptor metadata ("Lower endpoint, min") mirroring `i_3`'s role ("Initial guess"), and because `i_2` is never consumed anywhere else, this is a textbook Lineage Disconnection: a computed/selected branch value is silently replaced by an unrelated root value that plays no role in the formula it is purported to represent. The practical consequence is total corruption of the solver's terminal output — the caller receives a near-zero "root" for an equation whose actual nearby root is π, a difference of 16 orders of magnitude, with no technical validation able to catch it because types, contexts, and local formula replay all check out.

---

#### Anomaly Localization (Attack Vector 3 — Semantic Type and Context Cast)

- **Variable:** `o_20`, descriptor name "Lower-endpoint branch value," `valueClass` `java.math.BigDecimal`.
- **Declared role (by name/position, mirroring `o_17`):** the x-coordinate of the accepted lower endpoint, i.e., the value that should equal `i_2` ("Lower endpoint, min") when the lower-endpoint branch is taken.
- **Actual content:** the output of `op_12 = o_19 * i_5`, i.e., the branch-taken flag times `i_5` ("f(min) = sin(min)"), a function **residual**, not a coordinate.
- **Consumer:** `op_13` adds `o_20` directly into `o_21` ("Root") as if it were a coordinate contribution, exactly paralleling how `o_17` (a genuine coordinate contribution) is added in the same operation.

#### Details (Vector 3)
Technically, `o_20` is a `BigDecimal` produced by a type-safe `multiply`, and its own formula `(a*b)mc` replays perfectly against its declared `MathContext`. But its declared business role ("Lower-endpoint branch value", standing in for the accepted root candidate, by direct structural analogy to `o_17`/`i_3`) conflicts explicitly with its actual content (a function value/residual, `i_5`). This is precisely the gap the Semantic Type and Context Cast vector targets: 100% technical type continuity and mathematical self-consistency, while the underlying domain meaning — "this is the x-value of the accepted root" vs. "this is how close the function got to zero" — has been silently swapped. The graph nowhere reconciles this swap (no metadata states "branch value is defined as the residual, not the coordinate"), so there is an explicit, unresolved contradiction between originating metadata/role and downstream consumption. The result is that the final reported "Root" is semantically a sine-function residual masquerading as a root coordinate.

---

#### Precision and Scale Tampering — Supporting Analysis (Vector 2, clean)
Recomputing each step at full precision and comparing to the declared `MathContext` (precision 16, HALF_EVEN) confirms exact agreement at every operation (`op_1`, `op_5` correctly round 17-significant-digit inputs down to 16 digits under HALF_EVEN; `op_2`/`op_6` compares, `op_3`/`op_7` max, `op_4`/`op_8` subtract, `op_9`–`op_13` multiply/add all reproduce their reported values to the last digit). No directional rounding modes, no coarser contexts, no mid-pipeline downcasts, and no scale truncation are present. This vector is clean; the severity of the overall audit stems entirely from the structural/semantic substitution described above, not from numeric rounding behavior.