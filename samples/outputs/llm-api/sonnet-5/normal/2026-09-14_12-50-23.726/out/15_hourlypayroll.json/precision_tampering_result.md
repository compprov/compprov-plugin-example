# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

## Precision & Scale Tampering Audit — Hourly Payroll Pipeline

### Anomaly Localization (If Detected)
No confirmed anomaly located. All seven operations (op_1 through op_7) were independently recomputed using exact rational arithmetic and cross-checked against the declared `MathContext` (`i_1`: precision=16, HALF_EVEN).

| Op | Formula | Exact Result | Reported Result | Δ |
|----|---------|---------------|------------------|---|
| op_1 | 40 × 22.00 | 880.00 | 880.00 | 0 |
| op_2 | 22.00 × 1.5 | 33.000 | 33.000 | 0 |
| op_3 | 6 × 33.000 | 198.000 | 198.000 | 0 |
| op_4 | 12 × 1.75 | 21.00 | 21.00 | 0 |
| op_5 | 880.00+198.000+21.00 | 1099.000 | 1099.000 | 0 |
| op_6 | 1099.000 × 0.18 | 197.82000 | 197.82000 | 0 |
| op_7 | 1099.000−197.82000 | 901.18000 | 901.18000 | 0 |

Every intermediate and terminal value matches exact BigDecimal arithmetic (scale = sum/max of operand scales per standard multiply/add/subtract semantics), and none of the operands ever approach the 16-significant-digit precision ceiling of the declared MathContext, so HALF_EVEN rounding is never actually invoked anywhere in the pipeline — the context is present but inert for these magnitudes.

### Details
- **Reused variables** (`i_2` base rate, `o_12` gross pay) are legitimately consumed by multiple downstream operations (regular pay vs. overtime-rate calc; tax vs. net-pay calc respectively) — this is standard fan-out in a payroll DAG, not evidence of split-and-skim tampering.
- **No premature truncation observed**: scale on every multiply/add/subtract output is exactly what unrounded BigDecimal semantics dictate (e.g., 3-decimal overtime rate propagating its scale correctly into overtime pay and gross pay, tax withholding correctly carrying 5 decimal places from 3+2 scale addition).
- **No downcasting** to float/double/int detected; all wrapper classes remain `BigDecimal`/`MathContext` throughout.
- **No salami-slicing pattern**: there is no scalable, high-frequency operation population here (single employee, single pay period), and no deltas exist to accumulate toward a beneficiary sink in the first place, since every step is mathematically exact.
- **Rounding mode conformance**: the declared MathContext (HALF_EVEN, precision 16) is consistently passed to every arithmetic operation as the `mc` argument, satisfying the invariant that an explicit MathContext argument governs the operation; no operation contradicts its declared context.

**Conclusion**: This graph shows no material or even boundary-level precision/scale discrepancy. The pipeline is arithmetically exact end-to-end. Residual risk score reflects only the generic possibility that domain-level semantic assumptions (e.g., whether night-shift differential should stack multiplicatively with overtime under labor law) fall outside this audit's arithmetic-precision scope, not any detected tampering.