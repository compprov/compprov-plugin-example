# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 80.0

## Anomaly Localization (If Detected)

**Implicated nodes:** `i_14`–`i_19` (six gas-fee amounts in ETH), `i_5`–`i_8` (dated ETH/USDC rates), `op_1`–`op_6` (six `convert` operations), `op_7` (`addBulk` aggregation), `o_21`–`o_26` (per-transaction gas costs in USDC), and critically **`o_27`** ("Total gas fees in USDC") together with the downstream chain `o_33` ("Gross yield in USDC"), `op_14`/`o_34` ("Platform fee in USDC"), `op_15`/`o_35` ("Net profit in USDC").

**Flow of the defect:**
1. Six separate on-chain gas expenditures (`i_14`–`i_19`, all in ETH) are meticulously converted to USDC using date-matched ETH/USDC rates (`i_5`–`i_8`), each conversion verified arithmetically exact (`op_1`–`op_6` → `o_21…o_26`).
2. These are correctly summed via `addBulk` (`op_7`) into `o_27` = **111.433600 USDC**, an exact, fully-verified figure representing the total real-world cost incurred to generate the yield.
3. Independently, five yield legs (`o_29`, `o_30`, `i_11`, `o_31`, `o_32`) are summed in `op_13` into `o_33` = **1738.305562 USDC** ("Gross yield") — this aggregation **never references `o_27`**.
4. The platform fee (`op_14`) is computed as 3% of `o_33` (not of `o_33 - o_27`), giving `o_34` = 52.149166 USDC.
5. "Net profit" (`op_15`) is computed strictly as `o_33 - o_34` = **1686.156396 USDC**.
6. `o_27` (Total gas fees) is structurally confirmed as a **leaf node** — it is never consumed by any further operation. It exists in the graph, fully computed with production-grade precision, and then silently discarded from the final profit figure.

## Details

Every individual arithmetic step in this graph was re-derived with exact rational arithmetic and matches the reported values to the unit (including correct application of the documented `DOWN`/truncation rounding convention in ops `op_11`, `op_12`, `op_14`). There is no salami-slicing pattern, no mismatched conversion rate, and no mid-pipeline downcast — the classic "precision tampering" signatures are absent at the level of individual operations.

However, the invariant that matters here is **asset conservation across the full pipeline**, not just within isolated operations. The pipeline computes a fully legitimate, transaction-by-transaction, date-matched gas cost total (`o_27` = 111.4336 USDC) — an amount that is neither negligible nor cosmetic; it represents ~6.4% of gross yield. Despite this, `o_27` is never wired into the `Gross yield` (`o_33`), the `Platform fee` (`o_34`), or the `Net profit` (`o_35`) computation. The DAG's own `addBulk` at `op_13` deliberately lists five specific yield inputs and omits `o_27` even though it was available and of identical `Amount`/USDC type — this is not a missing dependency due to a type mismatch, it is a selective omission.

The practical effect: the reported "Net profit in USDC" (1686.156396) overstates the true economic profit (which should be `o_33 - o_34 - o_27` = 1738.305562 − 52.149166 − 111.433600 = **1574.722796 USDC**) by exactly the total gas cost, a **6.6% inflation of the bottom-line figure**. This is a textbook case of an accounting pipeline that *appears* rigorous — it computes and displays the cost with full precision, which would pass any casual review that just checks "was gas accounted for somewhere in the graph?" — while ensuring that cost never actually reduces the number that matters (the final reported profit). This is precisely the kind of "local math checks out, aggregate invariant fails" defect the audit discipline warns about: each operation replays correctly, but the omission at the aggregation boundary (`op_13`) causes systematic value leakage that benefits whoever is incentivized to report a higher net profit figure (e.g., a fund manager, platform operator, or fee-taking counterparty).

This does not fit the narrow decimal-truncation definition of "Precision and Scale Tampering" verbatim, but it squarely violates the stated invariant that "asset conservation must hold across all intermediate steps, at a materiality threshold appropriate to the values involved," and constitutes the kind of "subtle arithmetic logic flaw" this audit was also chartered to detect. Given the graph offers no annotation, meta field, or documented rationale explaining why gas costs are computed but intentionally excluded from net profit (e.g., no note stating "gas paid from a separate treasury and already excluded by design"), the burden of an innocent explanation is not met by anything actually present in the CPG.