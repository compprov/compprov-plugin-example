# Summary
- **Verdict**: CLEAN
- **Risk score**: 8.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud** — Score: 5/100. No Calculation Omission, Lineage Disconnection/Context Substitution, or Double Counting detected; all roots and intermediate weight/cost components converge exactly once into the single terminal output `o_24`.
2. **Precision and Scale Tampering** — Score: 8/100. No truncation, salami-slicing, or inappropriate rounding-mode usage found; one minor scale-presentation inconsistency noted (not a loss of value).
3. **Semantic Type and Context Cast** — Score: 0/100. All variable business meanings (tier weights, tier costs, surcharges) remain consistent with their consuming operations; no mislabeling or silent re-definition detected.

Overall risk is driven by the minor precision/formatting observation below; no confirmed attack was found in any vector.

#### Anomaly Localization (Topological & Provenance Fraud)
None. Trace of the full DAG confirms conservation at every stage:
- `o_9` (100) + `o_11` (400) + `o_13` (120) = `i_3` (620) — full weight accounted for across the three tiers, no leakage or omission.
- `o_14` (85.00) + `o_15` (240.00) + `o_16` (48.00) = `o_17` (373.00) — correctly aggregated via `addBulk` (op_9), each tier counted exactly once.
- `o_17` + `o_20` (distance surcharge) = `o_21` (pre-fuel subtotal); `o_21` is then used once as the fuel-surcharge base (op_12) and once as an additive component of the final total (op_13) — this is the standard "base + percentage-of-base" pattern, not duplication, since the same quantity is not counted twice *toward the same total component*.
- All multi-use variables (`i_3`, `o_9`, `o_10`, `o_21`) feed structurally distinct, non-overlapping downstream computations (tier-ceiling comparisons, tier-portion subtractions, and subtotal/surcharge split) consistent with a legitimate tiered-rate freight calculation.
- Single leaf `o_24` is the sole terminal, and no duplicate-named leaves or orphaned/superseded root variables exist.

#### Details (Topological & Provenance Fraud)
The weight is decomposed losslessly into non-overlapping tiers (0–100, 100–500, 500+) via min/subtract/max operations that sum back to the total input weight, and each tier's cost is computed once and aggregated exactly once into `o_17`. The distance surcharge and fuel surcharge are added via distinct operations without re-introducing any already-counted value. No root is bypassed by a substitute, and no computed leaf is excluded from the terminal sum improperly. This vector is clean.

#### Anomaly Localization (Precision and Scale Tampering)
- `o_23` (Fuel surcharge) = 440.50 × 0.09 = 39.6450 (exact, matches BigDecimal scale-addition convention, no rounding error against the declared `mc` precision 16 / HALF_EVEN).
- `o_24` (Total freight cost) = 440.50 + 39.6450 = 480.1450 (exact arithmetic sum, correctly reflects full precision of its inputs).

#### Details (Precision and Scale Tampering)
All multiplications and additions replay exactly against the single, consistently-applied MathContext (`i_1`: precision 16, HALF_EVEN) used throughout the graph — this is a round-to-nearest mode at a precision far finer than cents, and it is applied uniformly to every monetary operation, satisfying the invariant that the context suit the quantity and match the rest of the graph. No directional rounding modes (DOWN/UP/FLOOR/CEILING), no premature scale truncation, and no downcasting to float/double/int occur anywhere in the pipeline. The only notable observation is that the final total `o_24` carries four decimal places (480.1450) rather than being rounded to the conventional two-decimal cent resolution expected for a final invoiced freight charge — this is a retention of extra precision (not a loss of value or forced truncation), so it does not constitute Salami Slicing or residual-value leakage under the stated invariants. It is flagged only as a minor downstream formatting/reconciliation consideration, not a confirmed tampering finding, and is scored accordingly low.

#### Anomaly Localization (Semantic Type and Context Cast)
None. Business context was traced end-to-end:
- `i_3` ("Total shipment weight") → tier-ceiling comparisons (`o_9`, `o_10`) → tier-portion subtractions (`o_11`, `o_12`, `o_13`) — all consistently treated as weight quantities (lbs), never re-interpreted as cost or currency.
- `i_6`/`i_7`/`i_8` ("Tier N rate $/lb") are multiplied only against their corresponding tier-weight variables, producing tier cost variables (`o_14`–`o_16`), consistent with their declared per-pound rate semantics.
- `i_18`/`i_19` (distance, $/mile) combine to `o_20` (distance surcharge), which is added — not conflated — with the weight-based cost `o_17` to form `o_21` ("Pre-fuel subtotal"), an explicitly and correctly labeled aggregation.
- `i_22` ("Fuel surcharge rate 9%") is applied to `o_21` specifically labeled as the pre-fuel subtotal, consistent with standard freight fuel-surcharge-on-subtotal practice, then added back in `op_13` to produce the final total — no Gross/Net or pre/post-adjustment relabeling occurs without matching arithmetic.

#### Details (Semantic Type and Context Cast)
Every variable's descriptor name and role remain consistent with its consumption throughout the DAG: weight variables are only ever used in weight arithmetic, rate variables are only multiplied against their matching tier weights, and the subtotal-to-surcharge-to-total flow is both explicitly named and arithmetically backed (fuel surcharge is computed as the stated rate times the stated subtotal, then genuinely added — not substituted — into the final figure). No identity/wrapper pass-through silently changes business meaning, and no node consumes a variable under a conflicting business definition. This vector shows no evidence of attack.