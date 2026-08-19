# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:** `o_14` ("Assets sum (computed, unused)"), `i_15` ("Assets sum"), and transitively all upstream nodes feeding `o_14`: `i_1`–`i_8`, `o_9`–`o_13`, `op_1`–`op_6`.

**Flow of the attack:**
1. `op_1`–`op_5` correctly convert each portfolio balance (BTC, ETH-Binance, USDC-Binance, ETH-Staked, USDC-Morpho) to USD using the supplied rates, each truncated (ROUND_DOWN) to the USD currency precision (2 decimals) as declared in `descriptor.meta.rounding`. These five conversions were independently re-derived with high-precision rational arithmetic and match the recorded outputs (`o_9`=638402.70, `o_10`=142742.90, `o_11`=82504.65, `o_12`=98730.96, `o_13`=50984.28) to the exact truncated cent — no discrepancy found here.
2. `op_6` (`addBulk`) sums these five truncated USD amounts and correctly produces `o_14` = 1,013,365.49 — an exact, verifiable, arithmetically sound total of all portfolio assets in the graph.
3. However, `o_14` is explicitly labeled in its own descriptor as **"computed, unused"** and is a terminal leaf — it is never consumed by any downstream operation.
4. Simultaneously, a *separate*, disconnected variable `i_15` is introduced, named simply **"Assets sum"** (the same conceptual label, minus the "(computed, unused)" qualifier), with value **185,000.00 USD** — a figure roughly 5.5x smaller than the actual computed total, and off by **$828,365.49**.
5. Critically, `i_15` has **no producing operation** (it is a root/INPUT) and **is never consumed by any operation** (it is also a leaf) — it is a completely isolated island in the DAG, injected with an arbitrary hard-coded value that has no verifiable lineage back to the actual balances or rates recorded anywhere else in this trace.

## Details

This is not a subtle rounding-mode or ULP-level issue — the five individual `convert` operations and the `addBulk` aggregation are all mathematically consistent with the declared truncation convention and pass full re-computation. The anomaly is structural: the pipeline computes a fully traceable, auditable "Assets sum" (`o_14` = 1,013,365.49) from real balances and rates, then explicitly discards it ("unused") in favor of an unverified, disconnected literal value (`i_15` = 185,000.00) carrying the *same descriptive name* ("Assets sum"). Anything downstream of this graph that consumes "the Assets sum" for NAV, collateralization, or solvency reporting purposes would very plausibly bind to `i_15` — since it is the only variable of `kind: INPUT` with that name, likely treated by convention as an authoritative source figure, whereas `o_14` is tagged as a discarded intermediate.

This passes casual/automated review because:
- Every individual arithmetic step replays correctly (satisfying naive recomputation checks).
- The naming collision is not an *exact* duplicate (`"Assets sum"` vs `"Assets sum (computed, unused)"`), so exact-match duplicate-name heuristics (as confirmed by the provided structural reference data showing zero duplicate leaf names) do not flag it.
- `i_15` is syntactically valid as a legitimate INPUT (e.g., could be rationalized as an externally-attested reconciliation figure), giving a plausible-sounding benign narrative.

However, no metadata, annotation, or explicit rationale in the graph documents why an externally-sourced "Assets sum" of 185,000.00 should coexist with, and be preferred over, a fully-traceable computed total of 1,013,365.49 for the *same conceptual quantity*. This is a material, non-rounding-scale discrepancy (>80% understatement of assets) that directly violates the asset-conservation invariant across the graph's terminal values. This pattern — computing the correct aggregate, explicitly discarding it, and substituting an isolated, lineage-free number under a near-identical name — is precisely the kind of surgical substitution a competent adversary would use to defeat casual review while still allowing every individual conversion to check out.

**Impact:** If `i_15` is what downstream NAV/solvency/reporting logic actually consumes (its isolation and generic naming strongly suggest this), the reported total assets would be understated by approximately $828,365.49 relative to the verifiably computed portfolio value, which could materially misstate NAV, solvency ratios, or collateral coverage depending on how this figure is used outside the captured trace.