# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 95.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud (Double Counting)** — Score: 95/100 — CONFIRMED: the Bonus variable (`i_3`) is consumed twice on the path to the terminal result, once inside Gross Pay and once again as a raw addition to Net Pay.
- **Precision and Scale Tampering** — Score: 3/100 — CLEAN: all operations replay exactly under the declared DECIMAL64 (precision 16, HALF_EVEN) MathContext; no rounding, truncation, or scale-degradation anomalies detected.
- **Semantic Type and Context Cast** — Score: 70/100 — CONFIRMED: `i_3` ('Bonus') is consumed under two contradictory business roles (taxable income component vs. untaxed final disbursement) with no transformation node reconciling the shift.

---

#### Anomaly Localization (Topological & Provenance Fraud — Double Counting)
- **Implicated nodes**: `i_3` (Bonus, root, value 500.00), `op_1` (Gross pay = i_2 + i_3 → `o_4`), `o_4` → `op_3` (Taxable income = o_4 - o_7 → `o_8`) → `op_4`/`op_5`/`op_9`/`op_10` (bracket splits, state tax, federal-withholding subtraction) → `o_19` → `op_11` (After state withholding → `o_20`) → **`op_12`** (`Net pay = o_20 + i_3` → `o_21`, the terminal leaf).
- **Flow of the attack**: The Bonus (`i_3` = 500.00) is first folded into Gross Pay (`op_1`: 3500.00 + 500.00 = 4000.00), which then flows through the entire taxable-income → bracket-tax → federal/state-withholding chain (`op_3`→`op_4`→`op_5`→`op_6`→`op_7`→`op_8`→`op_9`→`op_10`→`op_11`), fully absorbing the Bonus's tax liability into `o_20` (After state withholding = 2904.5000). The structural reference data independently confirms `i_3` is consumed by more than one operation (`op_1` and `op_12`). At the final step, `op_12` re-adds the *same* `i_3` variable directly to `o_20` to produce `o_21` (Net pay = 2904.5000 + 500.00 = 3404.5000). Since `o_20` already reflects a Net Pay computed from a Gross Pay that included the Bonus, this final addition is a second, un-netted injection of the identical value into the terminal result.
- **Materiality**: The terminal output (`o_21`, Net pay = 3404.50) is inflated by exactly $500.00 versus the correct value (`o_20` = 2904.50) — a 17.2% overstatement of biweekly net pay. This is not sub-cent noise; it is a full-value duplication landing in the final reported figure.

#### Details (Topological & Provenance Fraud)
Local replay of every single operation passes: `op_1` correctly computes Gross Pay, and `op_12`'s formula `(a+b)mc` is arithmetically valid given its stated inputs. The fraud is invisible to any check that validates operations in isolation — it only surfaces when tracing `i_3`'s full lineage to the terminal node. Because `i_3` is a root variable with legitimate metadata (