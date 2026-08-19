# Summary
- **Verdict**: SEMANTIC CAST DETECTED
- **Confidence score**: 90.0

## Anomaly Localization

**Implicated nodes:** `i_2`, `i_3`, `op_2`, `o_6`, `op_3`, `o_7`

Flow of the attack:

1. `i_2` ("Rate accumulator (before this period's accrual)", value=500) represents the *cumulative stock* of interest that existed **prior** to this period.
2. `i_3` ("Interest accrued this period (bps)", value=37) represents the *flow* — the interest newly earned **during** this period.
3. `op_1` correctly computes `o_5 = i_2 + i_3 = 537` ("Rate accumulator (after accrual)") — this is a legitimate, semantically-consistent accumulator update.
4. `op_2` computes `o_6 = i_1 * i_2 = 100000 * 500 = 50,000,000`, and `o_6` is explicitly labeled **"Principal * accrued rate"**. But the operand actually used is `i_2` — the pre-existing cumulative accumulator, *not* `i_3`, the variable whose own descriptor name is literally "Interest accrued this period (bps)". The label on `o_6` asserts a business meaning ("accrued rate" = this period's accrual) that does not match the metadata of the variable actually consumed.
5. `op_3` divides `o_6` by `i_4` (basis points denominator) to yield `o_7 = 5,000`, labeled "Distributed reward" — i.e., the reward actually paid out to stakers this period.

If `op_2` had instead consumed `i_3` (the variable whose own metadata matches the operation's declared semantics), the result would be `100000 * 37 = 3,700,000`, and the final distributed reward would be `370`, not `5,000` — a ~13.5x difference.

## Details

This is a textbook Semantic Type and Context Cast: every type is `java.math.BigInteger` end-to-end, the `add`/`multiply`/`divide` operations replay mathematically without error, and `i_2` is technically a valid `BigInteger` operand for `multiply`. A schema/type checker or a naive mathematical replay tool sees nothing wrong. But the *business meaning* of the operand silently diverges from the operation's declared purpose: the node named "Principal * accrued rate" is not actually multiplying by the accrued rate (`i_3`); it is multiplying by the *pre-existing accumulated stock* (`i_2`), which has a distinct, explicitly documented domain meaning ("before this period's accrual"). No explicit domain-transformation step exists to justify treating `i_2` as if it were `i_3`'s semantic equivalent — the re-interpretation is implicit, exactly what the EXPECTED_INVARIANTS prohibit.

The structural reference data corroborates this: `i_2` is flagged as consumed by more than one operation (`op_1` and `op_2`). One of those consumptions (`op_1`, the accumulator update) is legitimate and consistent with `i_2`'s own metadata. The other (`op_2`) silently repurposes `i_2` under a different business label (`o_6` = "accrued rate"), which is the second, illegitimate reuse — a single surgical substitution hidden inside an otherwise clean, well-labeled pipeline.

**Consequence:** The pipeline distributes a staking reward computed from the *entire historical accumulated rate* (500 bps) rather than *this period's newly accrued interest* (37 bps), inflating the reported "Distributed reward" from an expected 370 units to 5,000 units — a ~13.5x overpayment per distribution cycle, while all type signatures, wrapper classes, and formula replays remain fully valid. This is precisely the attack pattern described: full technical/mathematical continuity, broken semantic continuity, C_source (`i_2`: pre-existing cumulative stock) ≠ C_target (label asserted by `o_6`/`op_2`: this period's accrued rate).