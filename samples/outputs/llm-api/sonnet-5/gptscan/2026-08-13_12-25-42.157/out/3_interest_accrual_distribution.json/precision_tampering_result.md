# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:** `i_1` (Staked principal), `i_2` (Rate accumulator BEFORE accrual = 500), `i_3` (Interest accrued THIS period, bps = 37), `o_5` (Rate accumulator AFTER accrual = 537), `op_1` (add), `o_6` (Principal * accrued rate), `op_2` (multiply), `o_7` (Distributed reward), `op_3` (divide).

**Flow of the substitution:**
1. `op_1` correctly computes `o_5 = i_2 + i_3 = 500 + 37 = 537` — the legitimate "rate accumulator after accrual." This value is arithmetically perfect and satisfies any local replay check.
2. `op_2`, labelled *"Principal * accrued rate"*, is wired as `o_6 = i_1 * i_2` (100000 * 500 = 50,000,000) — i.e., principal multiplied by the **stale pre-accrual accumulator** (`i_2`), NOT by the period-specific accrual (`i_3 = 37`) and NOT by the freshly-computed post-accrual accumulator (`o_5 = 537`) that the pipeline just produced one step earlier.
3. `op_3` divides `o_6 / i_4 = 50,000,000 / 10,000 = 5,000`, producing the final `o_7` "Distributed reward."

Each individual operation is arithmetically exact (BigInteger, no rounding drift), so a naive replay of `op_1`, `op_2`, `op_3` in isolation shows zero delta. The tampering is not in the math — it is in **which operand was wired into `op_2`**.

## Details

**Why this is a logic/precision-tampering violation, not benign design:** The variable naming and structure make the intended computation unambiguous — this is a per-period staking accrual: `o_5` is explicitly named "Rate accumulator (after accrual)" and is correctly derived as `i_2 + i_3`. The reward that should be distributed *for this period* is the principal multiplied by the period's accrual rate, i.e., `i_1 * i_3 / i_4` (or equivalently, distributing based on the incremental accumulator delta, `i_3`). Instead, `op_2` multiplies principal by `i_2` — the **accumulator value from before this period's accrual was even added** — a number that has no independent standing as a per-period reward rate; it's the *cumulative* rate carried in from prior periods.

- Correct reward (using `i_3`, the actual bps accrued this period): `100000 * 37 / 10000 = 370`.
- Reported reward (using `i_2` instead): `100000 * 500 / 10000 = 5000`.
- Delta = 4630, a **~13.5x overpayment** relative to the value that should have been distributed for this single accrual period.

This is far beyond the "single rounding-mode, ≤1 unit at target scale" ceiling described in the invariants — it is a categorical misuse of the wrong operand, not a rounding-convention artifact. It also matches the structural reference finding: `i_2` is the only variable consumed by more than one operation (`op_1` and `op_2`), and here it is reused in a context (`op_2`) where the semantically correct operand (`i_3`, or the newly derived `o_5`) was readily available and even computed one step prior.

**Why it evades casual review:** `o_5` is computed correctly and stored as a legitimate output, lending the whole trace an appearance of correctness and internal consistency (