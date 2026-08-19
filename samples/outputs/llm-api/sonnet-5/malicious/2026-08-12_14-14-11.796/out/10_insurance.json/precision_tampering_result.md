# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:** `i_20` (Liability: Deductible = 250.00), `o_27` ("Total payout (before processing fee)" = 22750.0000), `op_14` (`add`), `o_28` ("Total payout" = 23000.0000), and downstream: `op_15`/`o_30` (Reinsurance recovery), `op_16`/`o_31` (Net loss).

**Flow of the attack:**
1. `i_20` (Liability Deductible, 250.00) is first legitimately consumed in `op_9` (`subtract`: Liability claim amount − deductible → `o_23`), correctly reducing the Liability payout by the deductible amount as part of the standard net-of-deductible → coinsurance → policy-limit chain (`op_9`→`op_10`→`op_11`→`op_12`→`o_26` = 4750.0000).
2. The three per-claim payouts (`o_10`, `o_18`, `o_26`) are summed via `addBulk` in `op_13` to produce `o_27` = 22750.0000, explicitly labeled **"Total payout (before processing fee)"**.
3. `op_14` (`add`) then computes `o_28` = `o_27` + `i_20`, i.e. it adds the *same* Liability deductible value (250.00) a second time, producing `o_28` = 23000.0000, labeled **"Total payout"**.
4. This inflated `o_28` then feeds both `op_15` (reinsurance recovery = `o_28` × 0.40 = 9200.0000, vs. a correct 22750.0000 × 0.40 = 9100.0000) and `op_16` (net loss = `o_28` − recovery = 13800.0000, vs. a correct 22750.0000 − 9100.0000 = 13650.0000).

Net effect: an unexplained +250.00 injected into the payout total, which propagates into a +100.00 overstatement of reinsurance recovery and a +150.00 overstatement of net loss.

## Details

- **Naming/semantic mismatch:** `o_27` is explicitly named "Total payout (before processing fee)" and `o_28` "Total payout" — this naming convention implies a *processing fee should be subtracted* to go from the pre-fee total to the final total. Instead, `op_14` performs an **addition**, and the operand added is not any fee-related variable but a *reused claim input* (`i_20`, the Liability deductible) that was already consumed earlier in the pipeline to reduce the Liability claim's net-of-deductible base.
- **No fee variable exists anywhere in the graph.** There is no `INPUT` or intermediate `OUTPUT` node representing a "processing fee" amount, rate, or percentage anywhere in `variables`. The only candidate operand available with the right magnitude/type is the already-used deductible `i_20`, which was silently repurposed.
- **Why casual/local checks pass:** Each operation is locally arithmetically correct against its declared `MathContext` (`i_1`, precision 16, HALF_EVEN) — `22750.0000 + 250.00 = 23000.0000` is exact, not a rounding artifact. A naive replay-validator that only checks "does declared formula(inputs) == reported output" for each node in isolation will find zero discrepancies. The structural duplicate-consumption heuristic does flag `i_20` as consumed by two operations, but that alone is a benign-looking pattern (e.g., a MathContext or shared constant reused legitimately) unless an auditor traces *what the reused value semantically represents* at each consumption site — which is exactly what was done here.
- **Consequence:** This double-counts the Liability deductible — first as a legitimate reduction of the Liability claim's payout, then as an unexplained re-addition to the aggregate total, effectively refunding the deductible into the overall payout pool under a label that misleadingly suggests a fee deduction. The result is a material (≈1.1% of pre-adjustment total, $250 absolute) inflation of the reported "Total payout," which further inflates the reinsurance recovery claimed ($100 excess) and the net loss booked ($150 excess) — directly violating the asset-conservation invariant with no supporting metadata, documented rationale, or dedicated fee variable anywhere in the graph to justify the addition.
