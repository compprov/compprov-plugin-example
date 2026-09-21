# Summary
- **Verdict**: CLEAN
- **Risk score**: 15.0

## Audit Summary
This CPG models a EUR/USD 90-day forward valuation using covered interest-rate parity. I traced every root input forward to the sole terminal leaf (`o_15`, "Mark-to-market value vs. spot (USD)") and checked each of the three sub-patterns of Topological & Provenance Fraud against it.

### Path tracing to the terminal output
- `i_2` (USD rate) → `o_6` → `o_8` → `o_10` → `o_14` → `o_15` (single path, M=1)
- `i_3` (EUR rate) → `o_7` → `o_9` → `o_10` → `o_14` → `o_15` (single path, M=1)
- `i_4` (day-count fraction) is consumed by `op_1` **and** `op_2`, but these produce two distinct, non-competing quantities (`o_6` domestic periodic rate, `o_7` foreign periodic rate) that only later recombine via a *division* (`op_5`), not a duplicated addition into the same aggregate. Not double counting.
- `i_5` ("One") is consumed by `op_3` and `op_4` to build two distinct growth factors (`o_8`, `o_9`). It is a genuine constant with no computed sibling — legitimate reuse, not an origin entity being double-booked.
- `i_11` (Notional EUR) → `o_13` (single path into the FX-conversion step) — M=1.
- `i_12` (Spot rate) → `o_13` — M=1.

### The `o_13` dual-consumption (flagged in structural reference data)
`o_13` ("Notional at spot rate USD") is consumed by both `op_7` (`scale(a=o_13, f=o_10)` → `o_14`) and `op_8` (`subtract(a=o_14, b=o_13)` → `o_15`). This is the only real candidate for M>1 double counting in the graph. Algebraically, `o_15 = o_13*o_10 - o_13 = o_13*(o_10 - 1)`, i.e., the notional's spot-equivalent value is used once to compute the forward-scaled settlement and once as the baseline being netted out to isolate the forward premium. This is exactly the documented formula: `op_8`'s own `formula` metadata is `a-b`, and `o_15`'s descriptor metadata explicitly states `basis: interest-rate-parity forward premium at valuation date`. This is a standard, self-consistent MTM construction (forward-settlement minus spot-equivalent), not an undocumented inflation/deflation of the reported result — it satisfies the "explicit documented split/allocation logic" exception in the invariant rather than violating it.

### Omission check (M=0)
The only leaf is `o_15`; every upstream computed variable (`o_6`–`o_10`, `o_13`, `o_14`) is consumed by a downstream operation en route to it. No mandatory contributor (domestic rate, foreign rate, day-count, notional, spot rate) is silently dropped from the terminal formula.

### Lineage/substitution check (M=1, wrong source)
No leaf shares a name with another variable (confirmed empty per structural data), and a manual scan for semantically equivalent stand-ins (rewrapped/renamed roots) found none: `i_1`–`i_5`, `i_11`, `i_12` are all unique roots with a single unambiguous consumer chain each, and no hardcoded literal masquerades under a computed sibling's identity anywhere in the graph.

### Arithmetic verification
Recomputing each step (`0.0525*0.25=0.013125`; `0.0375*0.25=0.009375`; growth factors `1.013125`/`1.009375`; ratio `≈1.003715170278638`; `2,500,000*1.0850=2,712,500.00`; `2,712,500*1.003715170278638≈2,722,577.3994` truncated DOWN to `2,722,577.39`; `2,722,577.39-2,712,500.00=10,077.39`) confirms all reported values are internally consistent with the stated `MathContext` (precision 16, HALF_EVEN) and the declared DOWN-truncation rounding convention.

## Details
The only structurally interesting finding is the dual use of `o_13`, which the mechanical reference data correctly flags as a multi-consumption node. On inspection this is a legitimate, explicitly documented "forward premium" construction (settlement value minus spot-equivalent value of the same notional), not an unexplained double-booking of the notional into an additive rollup. No calculation omission, no double counting without documentation, and no lineage disconnection/context substitution were found elsewhere in the graph. Given the inherent complexity of this dual-path pattern and the reliance on a single metadata annotation as the documentation of intent, a residual, low level of risk is retained for human review, but no material evidence of tampering was identified.