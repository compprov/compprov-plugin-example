# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 88.0

## Anomaly Localization

**Implicated nodes:** `o_6` (Interest accrued, Month 1), `op_20` (`addBulk` operation), `o_31` (Total interest accrued — terminal/leaf output).

**Attack flow:**
1. `op_1` correctly computes `o_6 = i_5 * i_3` (Month‑1 interest = 960.00000).
2. `o_6` is legitimately consumed once by `op_2` to derive `o_7` (Month‑1 principal portion), which is normal amortization flow.
3. `op_20` (`addBulk`, formula `(a+b0+...+bn)mc`) is the terminal aggregation that produces `o_31`, "Total interest accrued". Its argument map is:
   - `a` → `o_6`
   - `b0` → `o_6`   ← **duplicate of `a`**
   - `b1` → `o_10`
   - `b2` → `o_14`
   - `b3` → `o_18`
   - `b4` → `o_23`
   - `b5` → `o_27`
4. The six monthly interest values are `o_6, o_10, o_14, o_18, o_23, o_27`. A correct 6‑month total should sum each of these **exactly once**. Instead, `o_6` is passed into the sum twice (once as `a`, once as `b0`), giving `o_31 = o_6 + o_6 + o_10 + o_14 + o_18 + o_23 + o_27` — seven terms for six months.
5. Arithmetic verification: summing each interest value once yields `960 + 955.84 + 951.66336 + 947.47001344 + 923.2598934938 + 918.9529330677 = 5657.1862000015`. The reported `o_31 = 6617.186200001495`. The delta is exactly `960.00`, i.e., precisely one extra copy of `o_6` — confirming the duplication is not rounding noise but a literal re-consumption of the same root‑derived value.

This is a textbook **Topological Accumulation Fraud via Double Counting ($M>1$)**: `o_6`'s correctly-computed value reaches the terminal aggregation `op_20` → `o_31` via two causal argument slots on the *same* aggregation node, with no documented split/allocation logic (the `formula` metadata `(a+b0+...+bn)mc` gives no indication that `a` is meant to be pre-seeded with the first term separately — that would only be valid if `b0` started at `o_10`, not `o_6`).

## Details

**Mechanism:** `addBulk` operations accept a variadic map of named arguments (`a`, `b0..bn`). Because the argument dictionary is keyed by arbitrary names rather than positionally validated against a fixed schedule, nothing in the operation's local structure prevents the same `resultId` (`o_6`) from being bound to two different argument keys. A naive/local replay check on `op_20` alone would "pass" — the operation faithfully computes the sum of its listed arguments — but the *provenance* is corrupted: one root-derived entity (Month‑1 interest) is double-weighted in the rollup.

This also explains why `o_6` appears in the structural reference data's "consumed by more than one operation" list: it is consumed by `op_2` (legitimate, principal-portion calc) **and** twice by `op_20` (illegitimate double aggregation) — three total consumptions where only two (one per purpose) would be expected.

**Why casual/local checks pass:** Each of the six monthly sub-schedules (`op_1`/`op_2`/`op_3`, `op_4`/`op_5`/`op_6`, etc.) independently replays correctly against the loan's amortization formula. The tampering is isolated entirely to the final consolidation step (`op_20`), which is exactly the kind of surgical, single-node substitution that structural ID-matching heuristics (looking for duplicate *variable* IDs across the whole graph) can miss if they don't inspect argument-key collisions within a single operation's own argument list — here the reference data did correctly flag `o_6` as multiply-consumed, but flagging alone doesn't distinguish benign multi-purpose reuse (as with `o_8`, `o_12`, `o_16`, etc., which are reused once for next-period interest and once for next balance subtraction — different purposes, non-competing paths) from illegitimate reuse into the *same* aggregation node, which is what happened here.

**Consequences:** The reported "Total interest accrued" (`o_31 = 6617.19`) overstates the true total interest cost of the loan (`5657.19`) by exactly one month's interest payment (`$960.00`), a ~17% inflation. Although this particular terminal output (`o_31`) is not itself re-consumed by `o_34` (Total amount paid, which is independently and correctly derived from `o_33` + `o_32` + `i_4`), `o_31` is itself one of the graph's three terminal/leaf outputs and is presented as a finished, trustworthy disclosure figure (e.g., a regulatory finance-charge or interest-cost disclosure in a loan amortization report). A fraudulent/inflated interest-accrued figure reaching an end user unchallenged is a material integrity failure of the pipeline, independent of whether it propagates further downstream.

No other terminal outputs (`o_29`, `o_34`) show arithmetic or lineage anomalies; escrow, principal, and prepayment flows all check out with $M=1$ correctly, and no Calculation Omission or Lineage/Context Substitution pattern was found elsewhere in the graph. The single, confirmed, high-confidence finding is the duplicate-argument double-count of `o_6` inside `op_20`.