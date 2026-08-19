# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 91.0

## Anomaly Localization

**Implicated nodes:**
- `i_4` — "Extra principal prepayment (non-interest-bearing)", value `5000.00`
- `i_3` — "Monthly period rate (0.4%)"
- `op_13` → `o_21` — "Interest on prepayment [Month 4]" = `i_4 * i_3` = `20.00000`
- `op_14` → `o_22` — "Prepayment principal portion [Month 4]" = `i_4 - o_21` = `4980.00000`
- `op_15` → `o_23` — "Balance after prepayment [Month 4]" = `o_20 - o_22` (only 4980 credited, not the full 5000)
- `op_22` → `o_33` — "Total interest paid", an `addBulk` over `{o_6, o_10, o_14, o_18, o_25, o_29}` — **`o_21` is absent from this argument list**
- `op_26` → `o_37` — "Total amount paid by borrower" = `o_36 + i_4` (uses the *full* `5000.00`, not `4980.00`)

**Flow of the attack:**
1. `i_4` is explicitly labeled *non-interest-bearing* in its own descriptor, yet the graph computes `o_21 = i_4 * i_3 = 20.00000`, an "interest on prepayment" charge that contradicts the stated nature of the input.
2. That $20 is then subtracted out of the prepayment before it is applied to the balance: `o_22 = i_4 - o_21 = 4980.00`, so only `4980.00` — not the full `5000.00` the borrower actually pays — is credited against principal in `o_23`.
3. The `$20` that was carved out as "interest" is never routed into `o_33` ("Total interest paid"), whose `addBulk` operation (`op_22`) enumerates exactly six monthly accrued-interest terms (`o_6, o_10, o_14, o_18, o_25, o_29`) and omits `o_21`.
4. Independently, `o_37` ("Total amount paid by borrower") is built from `o_36 + i_4`, using the *full* `5000.00` prepayment — so the borrower is recorded as having paid $5000, $4980 of which reduces principal, and $20 of which simply vanishes: it is not applied to principal reduction at full value, and it is not reported anywhere as interest, fee, or any other line item.

Manual reconstruction confirms this: summing the six interest terms actually fed into `op_22` yields `5657.34652...`, matching the reported `o_33` value exactly — proving `o_21` (worth `20.00000`) was excluded from the total, not merely rounded away.

## Details

The mechanism exploits the fact that `o_21` is not a dead leaf — it is consumed once, by `op_14`, so a naive "unconsumed variable" check would clear it. The omission is instead structural: `o_21` is consumed *only* on the principal-reduction side of the ledger and is deliberately excluded from the aggregation operation (`op_22`) whose own name and role ("Total interest paid") obligate it to include every interest component the pipeline computes — including this one.

The consequence is a quiet $20 leak: the borrower's total cash outflow (`o_37`) is computed as if the entire $5000 prepayment reduced principal, while internally the pipeline only credits $4980 against the balance (`o_23`, and downstream `o_25`–`o_31`, `o_31` "Ending balance"). The missing $20 is charged as interest (per `o_21`'s own name and formula) but is never surfaced in the borrower-facing "Total interest paid" figure (`o_33`), understating the true cost of borrowing by exactly the amount silently diverted. This is compounded by the internal contradiction that `i_4` is documented as "non-interest-bearing" yet an interest charge is computed and applied against it anyway — a strong indicator that this is not incidental variance but a targeted, disguised omission consistent with the Calculation Omission attack pattern: a correctly computed adjustment (`o_21`) that is deliberately kept out of the final aggregate its name says it belongs in.
