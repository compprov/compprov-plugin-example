# Summary
- **Verdict**: DOUBLE COUNTING DETECTED
- **Confidence score**: 90.0

## Anomaly Localization

**Implicated nodes:** `o_27` (Interest accrued [Month 6]), `op_20` (addBulk → `o_31`), `o_31` (Total interest paid, before true-up), `op_21` (add → `o_32`), `o_32` (Year-end interest true-up, terminal/leaf output).

**Traced attack flow:**

1. `op_20` (`addBulk`) computes `o_31` = `o_6 + o_10 + o_14 + o_18 + o_23 + o_27`, i.e., the sum of interest accrued across **all six months, including Month 6 (`o_27`)**. This is the legitimate, fully-inclusive "Total interest paid" rollup — arithmetically verified: 960.00000 + 955.84000000 + 951.66336000000 + 947.4700134400000 + 923.2598934937600 + 918.9529330677352 ≈ 5657.186200001... which matches the reported `o_31` value.

2. `op_21` (`add`) then computes `o_32` = `o_31 + o_27` — i.e., it takes the already-complete six-month interest total (`o_31`, which structurally already contains `o_27`) and **adds `o_27` a second time**.

3. `o_32` ("Year-end interest true-up") is a terminal leaf output of the graph (confirmed by the leaf set: `o_36`, `o_29`, `o_32`) — it is the final reported figure for this metric, not an intermediate that gets reconciled or netted out later.

**Path multiplicity:** The entity `o_27` (Month 6 accrued interest, itself derived from the root financial inputs `i_5`, `i_3`, `i_2`, `i_4` via the amortization chain `o_21→o_25→o_27`) reaches the terminal output `o_32` via **two distinct paths**:
- Path A: `o_27 → op_20 → o_31 → op_21 → o_32`
- Path B: `o_27 → op_21 → o_32` (direct)

This gives `M(o_27, op_21) = 2`, in direct violation of the stated invariant that path multiplicity into a terminal aggregation must equal 1 absent an explicit, documented allocation/adjustment rule.

## Details

**Mechanism:** The fraud is concealed by the innocuous label "Year-end interest true-up," which superficially suggests a legitimate accounting adjustment (e.g., day-count correction, accrual reconciliation, etc.). However, the actual formula is transparent in the graph: `add(a=o_31, b=o_27)` — there is no separate adjustment input, correction factor, or documented rationale (the `meta` array for both `op_21` and `o_32` is empty). The second operand `b` is literally the same variable ID (`o_27`) already summed into `a` (`o_31`) one operation earlier. This is precisely the "intermediate subtotal consumed again by a later aggregation" pattern the audit is designed to catch — it passes casual/local replay (each operation's arithmetic is internally correct given its inputs) and evades the naive duplicate-name/duplicate-ID heuristics (no exact name or ID collision — `o_27` is legitimately reused elsewhere too, so simple multi-consumer flags alone don't distinguish this malicious reuse from `o_27`'s other legitimate consumers `op_18` and `op_19`).

**Why it's not benign parameter reuse:** Compare to genuinely legitimate multi-use variables in this graph — `i_2` (monthly payment) and `i_3` (rate) are constants reapplied once per month to *distinct* monthly calculations, and `i_4` (prepayment) is legitimately used once to reduce principal balance and once to count as cash paid by the borrower (two conceptually different metrics). `o_27`'s reuse is different in kind: it is consumed by a rollup (`op_20`) that already represents the complete six-month total, and then re-added into a second aggregate whose sole stated purpose is a "true-up" of that very same total — with no distinguishing adjustment logic, split ratio, or annotation to justify the re-addition.

**Consequences:** The reported "Year-end interest true-up" (`o_32` = 6576.139133069230) is inflated by exactly the Month 6 interest amount (918.9529330677352) relative to the true six-month total interest (`o_31` = 5657.186200001495) — a ~16% overstatement. If this true-up figure feeds external reporting (tax deduction claims, investor interest-income statements, or borrower-facing disclosures), it materially misstates the loan's interest economics via double-counted accrual, exactly matching the "Topological Accumulation Fraud via Double Counting" pattern (inflation variant) described in the audit brief.