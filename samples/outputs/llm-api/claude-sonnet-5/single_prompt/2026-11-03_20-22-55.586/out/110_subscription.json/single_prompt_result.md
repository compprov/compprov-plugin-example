# Summary
- **Verdict**: CLEAN
- **Risk score**: 3.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: Score 3/100 — No omission, substitution, or double counting detected; all roots and intermediates trace cleanly to the single terminal output.
- **Precision and Scale Tampering**: Score 3/100 — All intermediate roundings replay exactly against the declared MathContext (precision 16, HALF_EVEN); final result matches the mathematically exact value with zero residual leakage.
- **Semantic Type and Context Cast**: Score 2/100 — Variable names, units, and formulas are mutually consistent across the full lineage; no business-meaning drift detected.

Overall risk is low; this appears to be a correctly implemented mid-cycle subscription proration calculation.

#### Anomaly Localization (Topological & Provenance Fraud)
None. Root variables (i_1 MathContext, i_2 days-in-cycle, i_3 days-remaining, i_4 old-price, i_7 new-price) are all consumed. i_2 and i_3 are each used twice (op_1/op_3 and op_2/op_4 respectively), but this is a legitimate shared-parameter pattern: i_2 scales both the old and new monthly prices into daily rates, and i_3 scales both daily rates into their respective remaining-day amounts — one contributing positively (o_9, new charge) and one negatively (o_6, old refund) to the single terminal output o_10. No value converges into the same total twice, and the sole leaf (o_10) is the intended terminal result, so there is no evidence of calculation omission, lineage substitution, or double counting.

#### Details (Topological & Provenance Fraud)
The formula o_10 = (new_daily_rate × remaining_days) − (old_daily_rate × remaining_days) is the standard proration delta formula for a mid-cycle upgrade (charge for new plan's remaining days minus credit for old plan's unused days). Structural reuse of i_2 and i_3 is functionally required by this formula, not duplication of the same monetary contribution. No unused roots, no duplicate-named leaves, and no orphaned computed branches exist.

#### Anomaly Localization (Precision and Scale Tampering)
All five operations (op_1–op_5) were recomputed under MathContext precision=16 / HALF_EVEN:
- op_1: 29/30 → 0.9666666666666667 (matches, correctly rounded up at the 16th digit)
- op_2: 0.9666666666666667×12 → 11.60000000000000 (matches, correctly rounded down)
- op_3: 79/30 → 2.633333333333333 (matches, correctly rounded down)
- op_4: 2.633333333333333×12 → 31.60000000000000 (matches, correctly rounded up — the tiny deficit from op_3's truncation is absorbed back by HALF_EVEN rounding at this step)
- op_5: 31.60000000000000 − 11.60000000000000 → 20.00000000000000 (exact, matches)

The mathematically exact answer (29×12/30 subtracted from 79×12/30 = 11.6 and 31.6, netting to 20.0 exactly) is reproduced exactly by the stepwise pipeline despite intermediate repeating-decimal truncations, because precision 16 is comfortably above the quantity's natural (cents) resolution.

#### Details (Precision and Scale Tampering)
The MathContext (precision 16, HALF_EVEN) is applied uniformly to every operation — no directional rounding modes, no premature truncation, and no downcast to float/double/int anywhere in the pipeline. Asset conservation holds exactly at every step and in the final result. There is no scalable population of operations here (this is a single-subscription, single-cycle computation), so even a hypothetical sub-cent bias would not qualify as Salami Slicing. No findings.

#### Anomaly Localization (Semantic Type and Context Cast)
None. Business context flows consistently: i_4 ("Old plan monthly price") → o_5 ("Old plan daily rate") → o_6 ("Old plan refund (unused days)"); i_7 ("New plan monthly price") → o_8 ("New plan daily rate") → o_9 ("New plan prorated charge (remaining days)"); o_9 − o_6 → o_10 ("Net charge (new prorated − old refund)"), exactly matching its own formula metadata.

#### Details (Semantic Type and Context Cast)
Each variable's descriptor name accurately reflects its arithmetic role at every hop, with no identity/wrapper pass-throughs that silently reinterpret a quantity's meaning (e.g., no gross value consumed as net, no risk multiplier relabeled as a discount factor). The `BigDecimal` type is preserved throughout with no implicit unit or domain-tag shifts. No findings.