# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 68.0

## Anomaly Localization

**Implicated nodes:** `i_14, i_15, i_16, i_17, i_18, i_19` (root gas-cost inputs, dated per-transaction) → `op_1…op_6` (per-leg `convert`) → `o_21, o_22, o_23, o_24, o_25, o_26` (per-leg gas cost in USDC) → `op_7` (`addBulk`) → **`o_27` "Total gas fees in USDC" = 111.433600 USDC**.

`o_27` is flagged by structural traversal as a **leaf** (never consumed as an argument by any downstream operation). Meanwhile, the graph's actual final compliance figure, **`o_35` "Net profit in USDC" = 1686.156396 USDC**, is produced exclusively by:

```
op_13 addBulk(o_29, o_30, i_11, o_31, o_32) -> o_33 "Gross yield in USDC"
op_14 scale(o_33, i_20=0.03)              -> o_34 "Platform fee in USDC"
op_15 subtract(o_33, o_34)                -> o_35 "Net profit in USDC"
```

No term in `op_15` (or anywhere upstream of `o_35`) references `o_27` or any of its precursor gas legs. The entire, fully-computed, historically-correct gas-cost lineage (six separate per-transaction conversions at transaction-date FX rates, summed into a verified total) terminates at `o_27` and is dropped from the final reported result.

## Details

**Why local replay passes cleanly:** Every individual operation is mathematically self-consistent — each `convert` in `op_1`–`op_6` correctly multiplies the historical gas amount by the FX rate dated to match the transaction date (e.g., `op_1`: 0.005 ETH × 4480 = 22.400000, using the 2026‑06‑01 rate for the 2026‑06‑01 wBTC→AAVE gas spend), `op_7`'s sum reproduces 111.433600 exactly, and the `o_33`/`o_34`/`o_35` chain reproduces the reported Net Profit (1738.305562 − 52.149166 = 1686.156396) with correct DOWN-truncation to USDC's 6-decimal precision. A syntax-level or purely arithmetic replay of every node individually will report zero errors.

**Why this is still a lineage violation:** The invariant requires that a reported OUTPUT reflect *full forward propagation from root inputs through the graph's own computation chain*, and that a computed OUTPUT going unconsumed is only legitimate if nothing else took its place for the same purpose downstream. Here, `o_27` is not an incidental byproduct — it is the deliberately, carefully computed "Total gas fees in USDC," built from six root cost inputs valued at correct historical FX rates specific to each transaction date. A quantity this carefully engineered is unambiguously meant to represent a real cost of generating the yield. Yet the `subtract` operation that produces the headline "Net profit" (`op_15`) only nets Gross Yield against the 3% Platform Fee — the gas-cost branch is a complete dead end, silently orphaned one hop before the final result. No name-collision or hardcoded stand-in variable was needed to hide this: the attack is achieved simply by never wiring `o_27` into `op_15`'s argument list, letting the correctly-computed cost vanish from the reported result while remaining present (and superficially reassuring) elsewhere in the graph.

**Consequences:** The reported "Net profit in USDC" of 1686.156396 overstates true net profit by the full amount of unaccounted transaction costs (111.433600 USDC, ≈6.2–6.6% of the reported net profit / gross yield). A downstream consumer of this CPG who trusts that "Net profit" nets out all costs computed in the trace (as its name and the graph's own careful gas-fee computation strongly imply) would be materially misled, despite every individual arithmetic step passing local verification. This is a textbook instance of the described attack class: a legitimately-derived, fully-verifiable branch is computed end-to-end and then quietly excluded from the terminal compliance figure, rather than being forward-propagated into it.