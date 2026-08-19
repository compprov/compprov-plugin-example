# Summary
- **Verdict**: CLEAN
- **Confidence score**: 93.0

## Audit Summary

This CPG models a physics free-fall computation (h=100m, g=9.8 m/s², m=2.5kg) under a single, consistently-applied `MathContext` (`i_1`: precision=16, HALF_EVEN) referenced explicitly by every one of the nine operations (`op_1`–`op_9`). Because every arithmetic node carries an explicit `mc` argument, the EXPECTED_INVARIANTS provide clear ground truth for what "correct" rounding looks like at each step, and I recomputed each operation independently using exact rational/high-precision arithmetic to compare against the reported stored values.

### Step-by-step recomputation (exact arithmetic vs. reported)
- `op_1` (200*2*... wait, h×2): 100×2 = 200 → `o_7`="200" ✅ exact
- `op_2` (2h/g): 200/9.8 = 1000/49 = 20.40816326530612244897959... → rounded to 16 sig figs (HALF_EVEN) = 20.40816326530612 → `o_8` matches exactly ✅
- `op_3` (sqrt(2h/g)): sqrt(1000)/7 = 4.51753951452625619... → 16 sig figs = 4.517539514526256 → `o_9` matches exactly ✅
- `op_4` (2h×g): 200×9.8 = 1960.0 → `o_10` matches exactly ✅
- `op_5` (sqrt(2hg)): sqrt(1960) = 14√10 = 44.27188724235731064798... → 16 sig figs = 44.27188724235731 → `o_11` matches exactly ✅
- `op_6` (g×t cross-check): 9.8 × 4.517539514526256 = 44.2718872423573088 → 16 sig figs (HALF_EVEN, tie-breaks correctly on the trailing 8) = 44.27188724235731 → `o_12` matches `o_11` bit-for-bit, as physically expected (v=√(2gh)=gt is an algebraic identity) ✅
- `op_7` (v²): I manually squared 44.27188724235731 via exact big-integer multiplication. The unrounded exact product is 1959.9999999999999426250057104361 (i.e., a tiny, expected residual from the earlier sqrt/round chain). Applying HALF_EVEN rounding to 16 sig figs correctly carries this all the way up to 1960.000000000000 — exactly matching the reported `o_13`. This is the step most likely to conceal a "residual leakage" style tamper (rounding down instead of up to quietly drop the correction), and it does **not** — the carry/round-up was applied faithfully. ✅
- `op_8` (0.5×m): 0.5×2.5 = 1.25 → `o_14` matches exactly ✅
- `op_9` (KE=0.5·m·v²): 1.25×1960.000000000000 = 2450.000000000000 (scale-consistent with BigDecimal multiply-then-round-to-16-sig-figs) → `o_15` matches exactly, and independently agrees with the physical identity KE=mgh=2.5×9.8×100=2450 ✅

### Structural checks
- The reused variables flagged structurally (`i_3`, `o_7`, `o_11`) are legitimately reused with unchanged values across their consuming operations (g reused for op_2/op_4/op_6; 2h reused for op_2/op_4; v reused for self-squaring in op_7) — no substitution of a similarly-named but differently-valued variable was found.
- Both leaf outputs (`o_12`, `o_15`) are terminal cross-check/report values, consistent with their role as independent verification results rather than values being silently dropped from further aggregation.
- No mixed units, no downcasting to float/double, no alternate/undeclared rounding mode, and no scale truncation prior to a later aggregation step were found anywhere in the trace.

## Anomaly Localization (If Detected)
None. No variable or operation exhibits a discrepancy between the exact recomputed value and the reported value beyond what the declared `MathContext` (precision=16, HALF_EVEN) mandates. The single step with the largest latent rounding residual (`op_7`, squaring `o_11`) was independently verified via exact big-integer multiplication and found to round *correctly* (HALF_EVEN carry-up to 1960.000000000000), which is the opposite of a salami-slicing / residual-leakage pattern (that would manifest as an *incorrect* downward truncation hiding the residual).

## Details
Every operation explicitly carries the `mc` (MathContext) argument pointing to `i_1` (precision 16, HALF_EVEN), giving unambiguous ground truth for correct rounding at each step. Manual high-precision recomputation of all nine operations — including the numerically delicate divide→sqrt→square round-trip (`op_2`→`op_3`→`op_7`) and the independent cross-check path (`op_4`→`op_5`→`op_6`) — reproduces the exact reported decimal strings in every case, including correct HALF_EVEN carry propagation. The two independently-derived velocity values (`o_11` via √(2gh), `o_12` via g·t) agree bit-for-bit, and the final energy conservation check (KE = ½mv² = mgh = 2450 J) holds exactly. There is no evidence of scale mixing, non-standard rounding modes, precision downcasting, or residual-leakage/salami-slicing patterns anywhere in this graph. This is consistent with a correctly-instrumented, non-tampered computation.