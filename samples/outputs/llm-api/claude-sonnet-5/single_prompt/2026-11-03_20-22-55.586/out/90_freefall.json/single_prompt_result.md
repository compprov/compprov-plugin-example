# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: 0/100 — No omission, substitution, or double counting detected; cross-check leaf is non-mandatory and independently verifies the computation.
- **Precision and Scale Tampering**: 5/100 — All operations replay correctly against the declared DECIMAL64 (precision 16, HALF_EVEN) MathContext; no truncation, downcasting, or salami-slicing pattern found.
- **Semantic Type and Context Cast**: 0/100 — Business meaning (height, gravity, mass, time, velocity, KE) and units remain consistent end-to-end; no implicit re-labeling or domain contradiction.

**Overall Risk Score: 4/100 (Clean)**

---

#### Anomaly Localization (If Detected)
None. No variable or operation exhibits a confirmed violation under any of the three attack vectors.

#### Details

**Vector 1 — Topological & Provenance Fraud**
The graph computes two independent paths to velocity: `o_11 = sqrt(2h·g)` (op_4→op_5) and a cross-check `o_12 = g·t` (op_6, using `o_9 = sqrt(2h/g)` from op_2→op_3). Both leaves (`o_12`, `o_15`) were examined: `o_12` is explicitly named "Impact velocity cross-check" and is numerically consistent with `o_11` (44.27188724235731 both), confirming it is a genuine validation artifact rather than a bypassed or omitted mandatory contributor — it makes no claim on the terminal KE result and the terminal result (`o_15`, Kinetic Energy) is reached solely through the `o_11 → o_13 → o_15` chain, which is the physically correct path (KE = ½mv², with v from 2gh, not from gt). No root variable substitutes for a computed value: all six roots (`i_1`–`i_6`) are consumed only in their legitimate original roles, and the multi-use of `i_3` (g) and `o_7` (2h) each feed two independent, non-overlapping branches (time-of-fall and velocity), neither of which double-counts into the final KE. The repeated argument in `op_7` (`multiply(o_11, o_11)`) is a legitimate squaring operation (v²), matching the invariant's allowance for powers.

**Vector 2 — Precision and Scale Tampering**
Recomputing each step with exact rational arithmetic and comparing to the reported BigDecimal values confirms correct application of the declared MathContext (`i_1`: precision 16, HALF_EVEN) at every operation:
- `o_8 = 200/9.8` (exact 1000/49) rounds correctly to `20.40816326530612`.
- `o_9 = sqrt(o_8)` rounds correctly to `4.517539514526256`.
- `o_11 = sqrt(1960)` rounds correctly (HALF_EVEN round-up at the 16th digit) to `44.27188724235731`, and the independent cross-check `o_12 = 9.8 × o_9` reproduces the identical rounded value, confirming internal consistency rather than drift.
- `o_13 = o_11²` correctly reduces to `1960.000000000000` within the declared precision, and `o_15 = 1.25 × o_13 = 2450.000000000000` is exact.
The chosen context (HALF_EVEN, precision 16 / DECIMAL64) is a standard round-to-nearest convention applied uniformly across all nine operations — no directional rounding mode (DOWN/UP/FLOOR/CEILING), no premature scale truncation, and no downcast to float/double/int anywhere in the pipeline. No evidence of residual leakage or salami slicing.

**Vector 3 — Semantic Type and Context Cast**
Tracing descriptor metadata from roots to the terminal result shows full semantic continuity: height (`i_2`) and the constant 2 combine into `2h` (`o_7`), which splits into two unit-consistent branches — `2h/g` (s²) → `sqrt` → time (s), and `2h·g` (m²/s²) → `sqrt` → velocity (m/s). Velocity is squared (`o_13`, m²/s²) and multiplied by half-mass (`o_14`, kg) to yield Kinetic Energy in Joules (`o_15`), which is dimensionally and semantically correct (kg·m²/s² = J). No variable is silently re-mapped to a different business meaning, no metadata contradiction exists between a variable's origin and its consumption, and the one wrapper-style reuse (`o_7` into two operations) preserves its declared meaning ("2 × h") in both consumers. No evidence of context substitution (e.g., gross/net, risk-multiplier/discount-factor style swaps).

**Conclusion**: This is a well-formed, internally self-verifying physics computation (free-fall time, impact velocity with an independent cross-check, and kinetic energy) with no structural, arithmetic, or semantic irregularities detected.