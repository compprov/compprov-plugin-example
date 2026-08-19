# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 90.0

#### Anomaly Localization (If Detected)

**Implicated nodes:** `i_1`, `i_2`, `i_3`, `i_4`, `o_5`, `o_6`, `o_7`, `op_1`, `op_2`, `op_3`.

Flow as computed:
- `op_1` (`add`): `o_5 = i_2 + i_3` = 500 + 37 = **537** ("Rate accumulator (after accrual)") — correctly computed, this is the accumulator that reflects this period's accrual.
- `op_2` (`multiply`): `o_6 = i_1 * i_2` = 100000 * **500** = 50,000,000 ("Principal * accrued rate") — uses the *pre-accrual* accumulator `i_2`, NOT the newly computed `o_5`.
- `op_3` (`divide`): `o_7 = o_6 / i_4` = 50,000,000 / 10,000 = **5,000** ("Distributed reward").

`o_5` is a leaf (per structural reference) — it is never consumed by any downstream operation. Meanwhile `o_6`, whose own display name is "Principal * accrued rate," is computed against the *stale* rate accumulator `i_2` rather than the freshly-accrued `o_5`. The interest accrued this period (`i_3` = 37 bps) therefore never enters the multiplication/division chain that produces the reported "Distributed reward" (`o_7`).

If the accrual had been correctly propagated, the reward should have been `i_1 * o_5 / i_4` = 100,000 * 537 / 10,000 = **5,370**, not 5,000 — a shortfall of 370 units (roughly 7.4% of the correct reward), silently withheld from the distribution.

#### Details

The graph transparently computes the updated rate accumulator (`o_5 = i_2 + i_3`), demonstrating that the accrual logic exists and executes correctly in isolation. However, the very next operation that is supposed to apply the accrued rate to the principal (`op_2`, labeled "Principal * accrued rate") reaches back past `o_5` and instead re-consumes the original, pre-accrual accumulator `i_2`. This is precisely the Calculation Omission pattern: a mandatory adjustment (`i_3`, this period's interest accrual) is measured and recorded (`o_5`) but severed from the causal path leading to the final reported result (`o_7`, "Distributed reward").

This passes casual/local review because every individual operation is internally consistent and arithmetically correct: `o_5 = i_2+i_3` checks out, and `o_7 = o_6/i_4` checks out given `o_6`. The tampering is not an arithmetic error but a wiring omission — `op_2` should have taken `o_5` as its second argument but instead was wired to `i_2`, which is also flagged structurally as being consumed by more than one operation (a signature of this kind of substitution: the stale value is reused downstream instead of being replaced by its updated successor).

**Impact:** The reported "Distributed reward" (`o_7` = 5,000) understates the reward that should be distributed to stakers by exactly the amount attributable to this period's accrued interest (370 units, computed correctly in `o_5` but never applied). This is a material, favorable-to-the-operator bias in the final output caused by a computed-but-unconsumed adjustment variable (`o_5`) — a textbook Calculation Omission.