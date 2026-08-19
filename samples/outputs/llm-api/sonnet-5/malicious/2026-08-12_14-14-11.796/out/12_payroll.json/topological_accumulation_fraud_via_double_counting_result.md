# Summary
- **Verdict**: DOUBLE COUNTING DETECTED
- **Confidence score**: 95.0

## Anomaly Localization

**Implicated variables:** `i_3` (Bonus, 500.00), `i_2` (Base salary), `o_4` (Gross pay), `o_7` (Pretax deductions), `o_8` (Taxable income), `o_12`/`o_13`/`o_14`/`o_15` (bracket splits/taxes), `o_16` (Federal tax withheld), `o_18` (State tax withheld), `o_19` (After federal withholding), `o_20` (After state withholding), `o_21` (Net pay — terminal output).

**Implicated operations:** `op_1` (add: i_2 + i_3 → o_4), `op_3`…`op_11` (the full taxable-income → withholding chain), and critically `op_12` (add: o_20 + i_3 → o_21).

**Attack flow:**
1. `i_3` (Bonus = 500.00) is first consumed legitimately in `op_1` as part of Gross Pay: `o_4 = i_2 + i_3 = 4000.00`.
2. `o_4` flows forward through the entire payroll chain: pretax deductions are netted out (`o_8 = o_4 - o_7 = 3650.00`), federal tax is computed progressively on `o_8` via brackets (`o_16 = 563.00`), state tax is computed on `o_8` (`o_18 = 182.50`), and both are subtracted sequentially (`o_19 = o_8 - o_16 = 3087.00`; `o_20 = o_19 - o_18 = 2904.50`). At this point, `o_20` is the fully-taxed, fully-netted take-home pay and **already contains the bonus's after-tax contribution**.
3. `op_12` then re-injects the *same* root entity `i_3` a second time: `o_21 = o_20 + i_3 = 2904.50 + 500.00 = 3404.50`, reported as "Net pay."

This is a textbook path-multiplicity violation: `M(i_3, o_21) = 2` — once through the Gross Pay → Taxable Income → Withholding chain, and once again as a raw, untaxed re-addition at the terminal aggregation step. No allocation/split rationale, no "bonus reimbursement" or "non-taxable supplemental payment" metadata, and no documented business rule justifies this second injection anywhere in the graph's `descriptor.meta`.

## Details

**Mechanism:** The fraud survives local/casual replay because every individual operation is arithmetically self-consistent — `op_1` correctly computes `3500+500=4000`; every subtraction and multiplication in the tax bracket chain checks out exactly (`200.00 + 363.00 = 563.00`; `3650 - 563 - 182.5 = 2904.50`); and `op_12`'s `o_20 + i_3 = 3404.50` is itself numerically correct given its two inputs. A node-by-node auditor confirms every single op passes its own formula. The fraud is only visible at the *global* topology level: `i_3` has out-degree 2 (`op_1` and `op_12`), and both of its consuming operations lie on a path that converges at the same terminal output `o_21`. This is precisely the 