# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 85.0

## Anomaly Localization

**Implicated variables:** `o_21, o_22, o_23, o_24, o_25, o_26` (individual gas-cost legs), `o_27` (Total gas fees), `o_29, o_31, i_11, o_34, o_36` (true per-stream yield-in-USDC values), `o_30, o_32, o_33, o_35, o_37, o_38` (unnamed, gas-netted stand-ins for the yield values), `o_39` ("Gross yield in USDC"), `o_40` ("Platform fee in USDC"), `o_41` ("After gas deduction"), `o_42` ("Net profit in USDC").

**Implicated operations:** `op_7` (addBulk → `o_27`), `op_10, op_12, op_13, op_15, op_17, op_18` (subtract ops that silently net gas into each yield stream), `op_19` (addBulk → `o_39`), `op_20` (scale → `o_40`), `op_21` (subtract → `o_41`), `op_22` (subtract → `o_42`).

**Attack flow:**
1. Each yield stream is correctly converted to USDC: `o_29`=405.658296, `o_31`=127.193403, `i_11`=119.583561, `o_34`=299.529123, `o_36`=786.341179. Their true sum ("real" gross yield) is **1738.305562**.
2. Each stream is then individually netted against its own gas leg (`op_10,12,13,15,17,18`): `o_30, o_32, o_33, o_35, o_38` = yield − gas(leg). These five results are unnamed (empty `descriptor.name`), unlike every other checkpoint in the graph.
3. `op_19`, the operation that produces the variable explicitly labeled **"Gross yield in USDC"** (`o_39`), does *not* consume the true, unadjusted yield values (`o_29, o_31, i_11, o_34, o_36`) that its own name says it should aggregate. Instead it quietly substitutes the already gas-netted stand-ins (`o_30, o_32, o_33, o_35, o_38`). Result: `o_39` = 1,626.871962, which is exactly 1,738.305562 − 111.4336 (`o_27`, Total gas fees) — i.e. `o_39` is secretly already net-of-gas despite being named "Gross yield".
4. `op_21` then produces `o_41`, explicitly labeled **"After gas deduction"**, by subtracting the full `o_27` (111.4336) from `o_39` a *second* time: 1,626.871962 − 111.4336 = 1,515.438362.
5. `op_20` computes the 3% platform fee off the already-deflated `o_39` (48.806158) instead of the true gross (which would yield ≈52.149166), and `op_22` subtracts that fee from `o_41` to produce the reported "Net profit in USDC" `o_42` = 1,466.632204.

Net effect: the true, correctly-computed gross-yield components (`o_29, o_31, i_11, o_34, o_36`) are omitted from the operation whose name and role (`op_19` → "Gross yield in USDC") require them, and are replaced by look-alike decoys that already embed the gas deduction. The gas total (`o_27`) is therefore folded into the reported result via two disjoint paths (once per-stream, once in bulk at `op_21`), and the platform fee is computed on the wrong (deflated) base.

## Details

Every individual arithmetic step in isolation is numerically correct (`0.005*4480=22.4`, `299.589041*0.9998=299.529123`, `109800*0.00369452=405.658296`, etc.), so a naive per-operation replay check passes cleanly — this is precisely why the tampering is disguised as a benign pipeline. The fraud only becomes visible when the *complete* formula implied by the variable names is reconstructed: "Gross yield" should equal the sum of the five yield-in-USDC legs before any deduction, but the operation that is supposed to produce that sum (`op_19`) omits those exact arguments and substitutes their gas-adjusted counterparts, which were themselves computed correctly elsewhere in the graph for what appears to be a different (unlabeled) purpose. The consequence is that the mandatory gas-fee deduction (`o_27`, itself correctly computed and clearly labeled "Total gas fees in USDC") ends up applied twice, and the platform fee is computed on an artificially shrunken base.

Quantified impact: the true single-deduction net profit (true gross 1,738.305562 − gas 111.4336 − 3% fee on true gross ≈52.149166) would be ≈1,574.72, whereas the graph reports `o_42` = 1,466.632204 — an understatement of roughly $108 (≈6.9% of the true net profit), silently pocketed via the duplicated gas deduction and the deflated fee base. This is exactly the pattern the omission-detection specification warns about: a mandatory component (`o_29, o_31, i_11, o_34, o_36`) that is correctly computed and even partially reused downstream, but is quietly excluded from the specific aggregation operation whose name and role demand it, in favor of a superficially similar substitute — an alteration engineered to survive local replay while corrupting the final reported figure.