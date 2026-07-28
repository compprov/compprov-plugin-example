# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Confidence score**: 58.0

## Anomaly Localization

**Implicated nodes:** `i_12`, `i_2`, `op_4` → `o_23`; `o_24`, `i_19`, `op_13` → `o_32`; downstream consumers `op_5` (`o_24`), `op_14`/`op_15` (`o_33`, `o_34`).

Two independent, unambiguous departures from the mathematically expected HALF_UP/HALF_EVEN rounding convention were found in the `WrappedAmount` `convert`/`scale` operations, both of which round **down** relative to the exact value:

1. **`op_4` (convert i_12,i_2 → o_23, "ETH(Lido+EtherFi) yield in USDC")**
   Exact product: `0.16910563 × 4650 = 786.3411795` — an exact tie at the 6th decimal. HALF_UP and HALF_EVEN both resolve this tie to `786.341180`. The graph reports `786.341179`, which is only reachable via ROUND_DOWN/FLOOR.

2. **`op_13` (scale o_24,i_19 → o_32, "Platform fee in USDC")**
   Exact product: `1738.305562 × 0.03 = 52.14916686`. This is **not** a tie — the 7th digit is `8`, so every standard rounding mode (HALF_UP, HALF_EVEN, HALF_DOWN, CEILING, UP) rounds this to `52.149167`. The graph reports `52.149166`, again only reachable via ROUND_DOWN/FLOOR (or an unrounded truncation of extra digits).

No `MathContext` (`mc`) argument is attached to either operation, so there is no declared, in-graph justification for the non-default rounding behavior.

## Details

**Mechanism:** Both flagged operations silently truncate rather than round the true arithmetic result by one unit in the last reported decimal place (1 unit at 6-decimal USDC scale). This is precisely the mechanical signature described in the attack vector (ROUND_DOWN/FLOOR instead of HALF_EVEN/HALF_UP), and because no MathContext is present, a casual/local recomputation check comparing only against the immediate next operation's declared arguments would pass — the arithmetic "chains" correctly from each already-truncated value to the next.

**Why it evades naive checks:** Structural duplicate/leaf-name heuristics find nothing (confirmed empty by the provided structural sets), and each individual arithmetic step is internally self-consistent with its (already-corrupted) inputs — `addBulk` (`op_5`) and `subtract` (`op_14`, `op_15`) correctly propagate the tainted values without further error. Only full-precision recomputation from the raw inputs reveals the two 1-ULP deviations.

**Consequence / materiality check:** Recomputing the entire pipeline in full, uninterrupted precision (no intermediate rounding at all) gives an exact net profit of `1574.7227958110`, which rounds to `1574.722796` — identical to the reported `o_34`. The two down-roundings occur on opposite sides of the final subtraction (one inflates the subtrahend chain via a smaller `o_23`→`o_24`, the other shrinks the fee being subtracted), and they cancel out exactly rather than accumulating toward any beneficiary. This matches the invariant guidance that canceling deltas argue against a beneficiary-driven exploit.

**Assessment:** This is a confirmed, reproducible deviation from the expected default rounding convention (particularly `op_13`, which is not even a tie case and thus cannot be excused as boundary noise) — it should not be dismissed as clean. However, per the stated invariants, genuine Salami Slicing requires a *scalable* operation population (here it is capped at one conversion per portfolio asset, not per-transaction/per-user volume) and demonstrated accumulation toward an identifiable sink — neither of which is present; the deltas cancel in the final reported figure. This is therefore best characterized as a systemic rounding-mode/precision-handling flaw (SUSPICIOUS LOGIC) rather than a confirmed value-extraction exploit (ANOMALY DETECTED), but it warrants remediation and further scrutiny of the underlying `WrappedAmount` rounding implementation, since the same latent bias could become material if the population of conversions were ever scaled up or if cancellation did not occur by coincidence in a future run.