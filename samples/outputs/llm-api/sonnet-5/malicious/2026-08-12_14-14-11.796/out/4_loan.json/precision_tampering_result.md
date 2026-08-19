# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 83.0

## Anomaly Localization

**Implicated nodes:** `o_32` ("Total escrow collected"), `i_9`, `i_13`, `i_17`, `i_22`, `i_26`, `i_30` (monthly escrow inputs), `op_21` (addBulk producing `o_32`), `o_33` ("Total scheduled payments"), `op_22` (addBulk producing `o_33`), `i_4` (extra prepayment), `op_23` (add producing `o_34`), and the final output `o_34` ("Total amount paid by borrower").

**Flow of the defect:**
1. Six monthly escrow inputs (`i_9, i_13, i_17, i_22, i_26, i_30`, $400.00 each) are correctly summed via `op_21` into `o_32` = **$2,400.00** ("Total escrow collected").
2. `op_22` sums six copies of the monthly payment `i_2` into `o_33` = **$12,000.00** ("Total scheduled payments").
3. `op_23` computes the terminal, headline output `o_34` ("Total amount paid by borrower") as `o_33 + i_4` = 12,000.00 + 5,000.00 = **$17,000.00** — **`o_32` is never passed as an argument to `op_23` or to any other downstream operation.**
4. The structural reference data confirms `o_32` is a **leaf node** (never consumed as an argument anywhere), i.e., a fully-computed, named, materially significant quantity that is silently dropped from the pipeline's final aggregate.

The true total cash outflow from the borrower over the 6-month window is principal+interest payments ($12,000.00) + prepayment ($5,000.00) + escrow ($2,400.00) = **$19,400.00**, but the graph reports **$17,000.00** — an understatement of exactly **$2,400.00**, matching the entire escrow total to the penny.

## Details

All 19 amortization operations (`op_1`–`op_19`) were independently recomputed using exact rational arithmetic and cross-checked against the declared `MathContext` (precision=16, HALF_EVEN). Every single one — including several non-trivial cases where the exact unrounded result carried 17–18 significant digits and required genuine MathContext rounding (`o_16`, `o_19`, `o_20`, `o_24`, `o_25`, `o_28`, `o_29`, `o_31`) — reproduces the stored value exactly under HALF_EVEN semantics. There is **no evidence of rounding-mode manipulation, salami-slicing, or scale-mixing** anywhere in the interest/principal amortization chain; that portion of the pipeline is clean and internally consistent.

The anomaly instead sits in the final aggregation logic (`op_23`), which is structurally identical to a legitimate `add` operation and passes local replay perfectly (17,000.00 = 12,000.00 + 5,000.00 is mathematically correct *for the two arguments it was given*). This is precisely why it survives casual/local verification: each operation is locally sound, and the fraud is not in miscalculating any individual step but in the **selective omission of an entire, already-computed, correctly-labeled term (`o_32`)** from the final summation. This is a subtler cousin of the "reducing scale before completing an aggregation" pattern in the attack definition — here an entire named sub-total is excised from the borrower-total rollup rather than merely truncated, but the effect is the same: a systematic, material shortfall siphoned out of the reported final figure, undetectable without tracing every leaf variable back into (or out of) the terminal output.

**Consequences:** Any downstream consumer relying on `o_34` ("Total amount paid by borrower") — e.g., for regulatory disclosure, borrower statements, or reconciliation against actual cash collected — will see a figure understated by $2,400.00 (≈14% of the reported total), exactly equal to the escrow collected and separately reported in `o_32`. Because `o_32` is computed, labeled, and stored but structurally orphaned (a leaf with no downstream consumer), this is not an accidental omission of an unused constant — escrow collection was clearly modeled as part of the borrower's cash outflow, then dropped only from the headline total. This satisfies the asset-conservation invariant violation criterion at a materiality far beyond single-ULP/rounding-convention noise, and there is no metadata, annotation, or documented rationale in the graph justifying the exclusion.