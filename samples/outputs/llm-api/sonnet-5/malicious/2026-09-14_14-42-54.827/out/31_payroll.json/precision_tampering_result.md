# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 82.0

## Anomaly Localization

**Implicated nodes:** `i_1` (MathContext, precision=16, HALF_EVEN), `i_2` (MathContext, precision=2, DOWN), `o_13`, `o_14`, `i_12`, `op_6`, `op_7`, `o_15`, `o_16`, `op_8`, `o_17`, `op_10`, `o_20`, `op_11`, `o_21`.

**Flow of the tampering:**

1. `o_13` (Bracket 1 portion = 2000.00) and `o_14` (Bracket 2 portion = 1650.00) are correctly derived from taxable income (`o_9` = 3650.00).
2. `op_6` computes Bracket 1 tax (`o_15`) using `mc = i_1` (precision 16, HALF_EVEN) → exact result 200.0000, correctly reported.
3. `op_7` computes Bracket 2 tax (`o_16`) as `o_14 * i_12` = 1650.00 * 0.22 = **363.0000** exactly — but this single operation is the *only* arithmetic step in the entire pipeline that is passed `mc = i_2` (precision 2, rounding **DOWN**) instead of the pipeline-standard `i_1` (precision 16, HALF_EVEN) used by every other add/subtract/multiply in the graph.
4. Rounding 363.0000 to 2 significant digits with DOWN truncation yields `3.6E+2` (360), a **$3.00 understatement** of Bracket 2 tax that is silently laundered into scientific notation, obscuring the loss of two full digits of scale precision (cents → tens place).
5. This corrupted value (`o_16` = 360) is summed into `o_17` (Federal tax withheld = `o_15 + o_16` = 200 + 360 = 560.0000), which should have been 563.00.
6. The error propagates linearly through `op_10` (`o_20 = o_9 - o_17` = 3650 − 560 = 3090.0000, should be 3087.00) and `op_11` (`o_21` Net pay = `o_20 - o_19` = 3090.00 − 182.50 = **2907.5000**, should be **2904.5000**).

**Net effect:** the employee's reported net pay is inflated by exactly $3.00 per pay period, entirely attributable to a single mis-scoped MathContext argument on `op_7`.

## Details

- **Mechanism:** Every other monetary operation in this payroll pipeline (`op_1`–`op_6`, `op_8`–`op_11`) consistently uses `i_1` (precision=16, HALF_EVEN), which is effectively a no-op rounding constraint for currency amounts of this magnitude (it never truncates below 2 decimal places for these values). `op_7` alone substitutes `i_2` (precision=2, DOWN), a context that forces the result down to only 2 *significant digits* — collapsing $363.00 to $360, a full two orders of magnitude coarser than the cent-level precision required for currency. This is not a rounding-mode nuance (e.g., HALF_UP vs HALF_EVEN, which could differ by at most 1 unit at the cent scale); it is an arbitrary rescoping of precision that produces a $3.00 absolute error — far beyond the 1-unit-at-target-scale ceiling described for legitimate rounding-mode disagreements.
- **Why casual checks pass:** A naive validator checking "does this operation respect *some* declared MathContext" would find `op_7` compliant — it does match `i_2` exactly. The tampering is invisible to argument-presence or per-operation consistency checks; it only surfaces when the *choice* of MathContext is compared against the rest of the pipeline's established precision convention (`i_1`), revealing `i_2` as an out-of-band, single-use precision override introduced solely at the highest-value tax-rate multiplication.
- **Materiality and cyclicality:** This computation represents one operation within what the descriptor identifies as a "biweekly net pay" run — i.e., an operation that recurs for every employee, every pay period, whenever taxable income exceeds the Bracket 1 ceiling. The population of executions is scalable (grows with employee count and pay cycles), and the bias is directionally consistent (always undercounts Bracket 2 tax, always inflates net pay / underwithholds tax owed to the taxing authority). This satisfies the salami-slicing profile: a small-looking, consistently-directed skim on a scalable operation population, with an identifiable, quantifiable per-execution loss ($3.00) rather than a canceling artifact. Even in isolation for a single employee/period, a deterministic $3.00 miscalculation in a tax-withholding computation is a materially incorrect result, not noise.
- **Consequence:** Federal tax withheld is understated by $3.00 per affected paycheck, and net pay is correspondingly overstated by the same amount — a systematic, repeatable discrepancy between the payroll system's reported withholding and the true statutory liability, compounding across employees and pay periods.