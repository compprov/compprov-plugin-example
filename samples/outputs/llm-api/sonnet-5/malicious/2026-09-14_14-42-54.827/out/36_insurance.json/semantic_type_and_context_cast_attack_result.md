# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 87.0

## Anomaly Localization (If Detected)

**Implicated nodes:** `o_27` ("Total payout"), `i_28` ("Reinsurance recovery rate (40%)"), `op_14` (multiply), `o_29` ("Reinsurance recovery"), `op_15` (add), `o_30` ("Net loss").

**Flow:**
1. `o_27` "Total payout" (22750.0000) is computed correctly via `addBulk` (op_13) from the three line-of-business payouts (Collision `o_10`, Comprehensive `o_18`, Liability `o_26`). No issue here.
2. `op_14` (`multiply`) computes `o_29` "Reinsurance recovery" = `o_27` * `i_28` (0.40) = 9100.000000. This is a legitimate, type-safe computation of the amount the primary insurer expects to recover from its reinsurer.
3. `op_15` (`add`, formula `(a+b)mc`) then computes `o_30` "Net loss" = `o_27` (Total payout, 22750.0000) **+** `o_29` (Reinsurance recovery, 9100.000000) = 31850.000000.

The attack signature is entirely in step 3: `o_29`, a *recovery/credit* quantity that by definition reduces the insurer's retained exposure, is fed into an `add` operation and relabeled downstream as "Net loss." Every technical check passes — `BigDecimal + BigDecimal -> BigDecimal`, correct wrapper class, correct `mc` argument, and the numeric replay is internally consistent (22750.0000 + 9100.000000 = 31850.000000 checks out arithmetically). But the *business semantics* of "Net loss after reinsurance" require **subtraction**, not addition: `Net loss = Gross/Total payout − Reinsurance recovery`. Instead the graph produces a value *larger* than the gross payout, which is the opposite of what reinsurance is supposed to accomplish (risk transfer reducing the cedant's net retained loss).

## Details

**Mechanism:** This is a textbook Semantic Type and Context Cast. The `descriptor.name` "Reinsurance recovery" explicitly identifies `o_29` as a recovery/credit amount — a quantity that should offset the insurer's loss. There is no explicit transformation node, sign-flip, or documented convention anywhere in the graph that re-casts this variable's role before it is consumed by `op_15`. Instead, `op_15` silently consumes it as though it were an *additional loss component* to be summed with the total payout, and the result is labeled "Net loss" — a label that, per standard actuarial/insurance convention and per the EXPECTED_INVARIANTS, requires an explicit, auditable Gross→Net transformation (subtraction of the recovery), not an ad hoc addition.

Because `add` is a perfectly valid, type-safe operation on two `BigDecimal` operands with a correctly-supplied `MathContext`, this passes structural/type validation, node-connectivity checks, and even mathematical replay (the stored value is consistent with the declared operation). Only a semantic audit — tracing what `o_29` *means* in context versus how `op_15` *uses* it — reveals the contradiction: a credit is being treated as a debit, and the final output's name ("Net loss") is a materially false label for what is actually closer to "Total payout + recovery" (a figure with no legitimate actuarial meaning, or possibly a disguised "gross exposure including ceded amount" metric being smuggled out under a "Net loss" label).

**Consequences:** If `o_30` "Net loss" is consumed downstream (e.g., for regulatory reserve calculations, reinsurance treaty settlements, or financial statement reporting), the reported net retained loss is overstated by exactly `2 × Reinsurance recovery` (here, overstated by 18,200 relative to the correct net loss of 13,650.0000). This could materially misstate the insurer's financial position — inflating loss reserves, misrepresenting solvency margins, or improperly justifying premium/rate increases — while every low-level type and arithmetic check in the pipeline reports green.

**Why casual/automated checks miss it:** The structural reference data shows no duplicate names, no reused non-MathContext variables feeding conflicting operations in an obviously suspicious pattern, and `o_29`/`o_30` are technically well-formed, uniquely-named `BigDecimal` outputs consistent with their declared operation's formula. The violation is purely in the *choice of operation* (`add` vs. the domain-required `subtract`) relative to the *declared business meaning* of its operands — exactly the gap this audit is designed to catch.