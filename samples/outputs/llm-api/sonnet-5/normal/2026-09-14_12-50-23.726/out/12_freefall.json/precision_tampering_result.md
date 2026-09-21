# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

## Precision & Scale Tampering Audit — Free Fall Pipeline

### Anomaly Localization (If Detected)
No confirmed violation of the stated `EXPECTED_INVARIANTS` was found. All nine operations (`op_1`–`op_9`) were independently recomputed using exact rational arithmetic and compared against the reported outputs and the declared `MathContext` (`i_1`: precision=16, HALF_EVEN):

- `op_1` (multiply 100×2 → `o_7`=200): exact, no rounding needed.
- `op_2` (divide 200/9.8 → `o_8`): exact value is 20.408163265306122448979591836734693877551... (period-42 repeating decimal of 1/49). Truncated to 16 significant digits with the 17th digit being '2' (<5) → correctly rounds down to `20.40816326530612`, matching the reported value exactly under HALF_EVEN.
- `op_3` (sqrt(`o_8`) → `o_9`): sqrt of the already-rounded `o_8` differs from sqrt(1000/49) by ~2.4e-15, far below the 16th-significant-digit threshold; reported `4.517539514526256` matches.
- `op_4` (multiply 200×9.8 → `o_10`=1960.0): exact.
- `op_5` (sqrt(1960) → `o_11`): reported `44.27188724235731` matches the known exact value of √1960 to 16 sig figs.
- `op_6` (9.8×`o_9` → `o_12`): recomputed as 44.2718872423573088..., where the 17th digit (8) forces a round-up of the 16th digit (0→1), yielding `44.27188724235731` — exactly matching the independently-computed `o_11` (v = gt cross-check holds).
- `op_7` (`o_11`×`o_11` → `o_13`): squaring the rounded `o_11` introduces an estimated error of ~4.4e-13 relative to the true 1960, which is below the 16-sig-fig display threshold (12 decimal places shown) — reported `1960.000000000000` is consistent.
- `op_8` (0.5×2.5 → `o_14`=1.25): exact.
- `op_9` (1.25×1960 → `o_15`=2450.000000000000): exact.

### Details
Every operation carries an explicit `mc` argument referencing `i_1` (precision=16, HALF_EVEN), and every reported result is consistent with applying that declared context to the exact mathematical result — including the one case (`op_2`) where the exact value falls very close to a rounding boundary. The two independent computation paths for impact velocity (`sqrt(2gh)` via `o_11` and `g·t` via `o_12`) agree to all 16 significant digits, and the two independent computations of `2h` (`o_7`, reused in both `op_2`/`op_3` and `op_4`/`op_5` paths) are internally consistent, showing no divergent scaling or hidden truncation between the two derivation chains.

No evidence of forced scale reduction, non-standard rounding mode, downcasting to `float`/`double`/integer, or directional bias suggestive of salami slicing was found. The small propagated errors from using rounded intermediates (`o_9` in `op_6`, `o_11` in `op_7`) are consistent with normal floating rounding propagation, remain within the single-ULP ceiling described in the invariants, and do not accumulate toward any identifiable sink — `o_12` and `o_15` are terminal leaf outputs, not further consumed. Because this is a single non-cyclic physics computation (not a scalable transaction/user-volume population), even if a sub-ULP discrepancy existed, it would not constitute genuine Salami Slicing per the stated invariants.

**Conclusion:** The graph's arithmetic is fully consistent with its declared `MathContext`, rounding mode, and conservation expectations. This appears to be a clean, correctly-computed free-fall physics pipeline with no material precision or scale tampering detected.