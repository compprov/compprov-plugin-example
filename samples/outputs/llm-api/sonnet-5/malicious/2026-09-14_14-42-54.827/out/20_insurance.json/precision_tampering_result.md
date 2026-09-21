# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Risk score**: 42.0

## Anomaly Localization

**Implicated nodes:** `o_27` (Payout sum, OUTPUT, computed value `22750.0000`), `i_28` (Total payout, INPUT, asserted value `22750.00`), and downstream consumers `op_14` (reinsurance recovery multiply) and `op_15` (net loss subtract).

Flow: `op_13` (`addBulk`) sums the three claim-type payouts (`o_10`=6000.0000, `o_18`=12000.00, `o_26`=4750.0000) under `mc=i_1` (DECIMAL64, precision 16, HALF_EVEN) to produce `o_27 = 22750.0000`. This is a terminal **leaf** variable — it is never consumed by any operation. Instead, a *brand-new root INPUT* node, `i_28` ("Total payout" = `22750.00`), is introduced with no producing operation and is what actually feeds the reinsurance recovery (`op_14`) and net-loss (`op_15`) calculations.

## Details

All individual arithmetic in this graph checks out exactly against arbitrary-precision recomputation:
- Collision: `8000.00-500.00=7500.00` → floor → `×0.80=6000.0000` → `min(…,10000.00)=6000.0000` — exact.
- Comprehensive: `20000.00-1000.00=19000.00` → floor → `×0.90=17100.0000` → `min(…,12000.00)=12000.00` — exact.
- Liability: `5000.00-250.00=4750.00` → floor → `×1.00=4750.0000` → `min(…,6000.00)=4750.0000` — exact.
- `addBulk` sum: `6000.0000+12000.00+4750.0000=22750.0000` — exact.
- Reinsurance: `22750.00×0.40=9100.0000`; Net loss: `22750.00-9100.0000=13650.0000` — exact.

All `mc`-bound operations (precision 16, HALF_EVEN) never actually need to round anything here — every intermediate value has far fewer than 16 significant digits, so no MathContext-driven degradation occurs, and the scale arithmetic (decimal-place addition on multiply/subtract) is textbook `BigDecimal` behavior. No salami-slicing pattern, no rounding-mode manipulation, and no single-operation Δ exceeding the 1-unit-at-target-scale ceiling was found in any of the 15 operations.

The one structurally significant finding is the **provenance break between `o_27` and `i_28`**. Rather than the computed sum (`o_27`) flowing directly into the reinsurance/net-loss stage, the pipeline re-introduces the payout total as a fresh, independently-asserted `INPUT` (`i_28`), with no operation tying it back to `o_27`. In this specific execution the two values are numerically identical (`22750.0000` vs `22750.00` — the dropped trailing zeros are non-lossy), so no financial leakage occurred *this time*. However, this is precisely the type of seam the EXPECTED_INVARIANTS warn about: because `i_28` has no producing operation, nothing in the graph *proves* it was derived from `o_27` rather than being independently substituted, truncated, or rounded by an external process. A scale-reduction or salami-slice tamper injected at this exact boundary (e.g., an attacker feeding a slightly-truncated `i_28` while `o_27` remains correct in the audit log) would be completely invisible to any lineage-based verification, since `i_28` is a root with no upstream edge to check against. This is a real weakening of end-to-end asset-conservation traceability, distinct from — but adjacent to — classic precision tampering, and it warrants scrutiny of the surrounding system design even though no numeric discrepancy is present in this particular trace.

**Conclusion:** No confirmed arithmetic precision/rounding violation in the recomputed values. The disconnect between `o_27` and `i_28` is a genuine, exploitable provenance gap at the exact stage where scale/precision tampering would be most consequential (the transition from per-claim payouts to the reinsurance/net-loss calculation), and is flagged at moderate severity given the absence of demonstrated exploitation but clear structural opportunity.