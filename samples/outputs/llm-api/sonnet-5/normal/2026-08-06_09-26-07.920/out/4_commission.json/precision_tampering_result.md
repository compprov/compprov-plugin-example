# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

#### Anomaly Localization (If Detected)
No anomaly localized. Full recomputation of every operation against the reported values:

- op_1 `min(i_3=135000.00, i_4=50000.00)` → o_9 = 50000.00 ✅ (matches reported)
- op_2 `min(i_3=135000.00, i_5=100000.00)` → o_10 = 100000.00 ✅
- op_3 `(o_10 - o_9)mc` = 100000.00 - 50000.00 = 50000.00 → o_11 ✅
- op_4 `(i_3 - o_10)mc` = 135000.00 - 100000.00 = 35000.00 → o_12 ✅
- op_5 `max(o_12, i_2)` = max(35000.00, 0) = 35000.00 → o_13 ✅
- op_6 `(o_9 * i_6)mc` = 50000.00 * 0.05 = 2500.0000 → o_14 ✅
- op_7 `(o_11 * i_7)mc` = 50000.00 * 0.08 = 4000.0000 → o_15 ✅
- op_8 `(o_13 * i_8)mc` = 35000.00 * 0.12 = 4200.0000 → o_16 ✅
- op_9 `addBulk(o_14, o_15, o_16)mc` = 2500.0000 + 4000.0000 + 4200.0000 = 10700.0000 → o_17 ✅

Every reported value is an exact match against arbitrary-precision recomputation (Δ = 0 at every step) — the inputs happen to be round decimal figures, so no rounding boundary is even exercised in this trace.

#### Details
The pipeline implements a standard three-tier progressive commission schedule: tier1 (0–50k @5%), tier2 (50k–100k @8%), tier3 (>100k, floored at 0 via max, @12%), summed via `addBulk`. All arithmetic operations that could be scale-sensitive (`subtract`, `multiply`, `addBulk`) explicitly carry the `mc` argument bound to `i_1` (MathContext: precision=16, HALF_EVEN), which is the documented, non-truncating, banker's-rounding default expected by the invariants. No operation silently omits the MathContext, no operation downcasts to float/double/int, and no unit-mixing (e.g., differing decimal scales for a shared currency) is present — all monetary values are consistently BigDecimal in the same currency/scale domain.

The three variables flagged as multiply-consumed (i_3, o_9, o_10) are legitimate: i_3 (monthly revenue) is reused as the base for two independent `min` clamps (tier1 and tier2 ceilings), and o_9/o_10 are reused to compute the tier-2 portion (difference) and the tier-3 uncapped remainder (difference) — both are standard, non-duplicative tiered-boundary arithmetic, not evidence of a hidden skim or double-counted amount. The floor via `max(o_12, i_2)` correctly guards against a negative tier-3 contribution when revenue falls below the tier-2 ceiling, and does not truncate any fractional cents since the value here is already positive and exact.

No salami-slicing pattern is present: there is no scalable population of operations (the tier count is structurally fixed at three by business logic, not by transaction/user volume), and every recomputed result is bit-for-bit identical to the exact rational result — there are no residual sub-cent deltas anywhere to accumulate toward a beneficiary. Asset conservation holds exactly: 2500.0000 + 4000.0000 + 4200.0000 = 10700.0000, and the tier boundaries (50000/100000) exactly partition the 135000.00 revenue with no leakage or double-booking.

Conclusion: no precision/scale tampering, rounding-mode abuse, or unit-mixing is present in this graph. The computation is internally consistent, exact, and conforms to the declared MathContext throughout.