# Summary
- **Verdict**: CLEAN
- **Risk score**: 3.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud** — Score: 3/100. No calculation omission, lineage substitution, or double counting detected; the tiered split is proportional and reconciles exactly to the base revenue.
2. **Precision and Scale Tampering** — Score: 2/100. All arithmetic recomputes exactly under the declared MathContext (precision 16, HALF_EVEN); no truncation, salami slicing, or downcasting observed.
3. **Semantic Type and Context Cast** — Score: 2/100. Business labels (ceilings, rates, tier portions) are used consistently with their declared roles throughout the pipeline; no silent re-mapping of domain meaning.

#### Anomaly Localization (If Detected)
None. Full trace of the graph:
- `o_9 = min(i_3, i_4)` = min(135000.00, 50000.00) = 50000.00 ("Revenue through tier 1") — correct tier-1 cap.
- `o_10 = min(i_3, i_5)` = min(135000.00, 100000.00) = 100000.00 ("Revenue through tier 2") — correct tier-2 cap.
- `o_11 = o_10 - o_9` = 50000.00 ("Tier 2 portion") — correct incremental slice.
- `o_12 = i_3 - o_10` = 35000.00 ("Tier 3 portion, uncapped"); `o_13 = max(o_12, i_2)` = 35000.00 (floored at zero, no-op here since positive) — correct.
- `o_14 = o_9 * i_6` = 50000.00 * 0.05 = 2500.0000 (Tier 1 commission).
- `o_15 = o_11 * i_7` = 50000.00 * 0.08 = 4000.0000 (Tier 2 commission).
- `o_16 = o_13 * i_8` = 35000.00 * 0.12 = 4200.0000 (Tier 3 commission).
- `o_17 = addBulk(o_14, o_15, o_16)` = 2500 + 4000 + 4200 = 10700.0000 (Total commission, the sole leaf/terminal).

All three tier slices (50000 + 50000 + 35000 = 135000.00) sum exactly to the root "Monthly revenue" (i_3), confirming no leakage, omission, or duplication across the tier boundaries. The multi-use variables (`i_3`, `o_9`, `o_10`) are each consumed twice only as legitimate boundary components of the progressive-tier formula (once as a cap, once as a subtraction operand), not as duplicated contributions to the final sum. The `MathContext` (`i_1`, precision 16, HALF_EVEN) is applied uniformly to every rounding-sensitive step (`subtract`, `multiply`, `addBulk`) and is a reasonable, round-to-nearest context with resolution far exceeding cent-level granularity; recomputation at arbitrary precision matches every reported value exactly, including the scale-expansion artifacts (e.g., `2500.0000`) which are standard BigDecimal scale-sum behavior from multiplying a scale-2 by a scale-2 operand, not evidence of degradation.

#### Details
This is a standard, well-formed three-tier progressive commission calculation. Revenue is decomposed into tier-bounded slices via `min`/`subtract`/`max(…,0)` floor-protection, each slice is multiplied by its own tier rate under a consistent, appropriately-precise HALF_EVEN MathContext, and the three tier commissions are summed into the single terminal output. The tier slices are mutually exclusive and exhaustive (they reconstruct the original revenue exactly), so there is no mandatory contributor omitted from the terminal result, no root value standing in for a computed one, and no value counted twice toward the total. Business metadata (tier ceilings, tier rates, "portion"/"commission" labels) is consistent with how each variable is actually consumed at every step, with no implicit relabeling or context stripping observed. No corrective action is required; this graph should be treated as a clean baseline.