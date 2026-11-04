# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 75.0

#### Per-Vector Verdict

| Vector | Score | Verdict |
|---|---|---|
| Topological & Provenance Fraud | 8 | No omission, substitution, or double-counting detected — all 10 strike branches are independent and converge exactly once into the terminal sum. |
| Precision and Scale Tampering | 75 | Confirmed: systematic coarse-precision truncation (DOWN, 2 decimals) applied inconsistently across economically identical branches, causing demonstrable, directionally-consistent value leakage in a recurring ("daily") payout engine. |
| Semantic Type and Context Cast | 6 | CALL/PUT intrinsic-value subtraction order is correct and consistent for every strike; no metadata re-mapping or business-context drift detected. |

---

#### Anomaly Localization (Precision and Scale Tampering)

Implicated nodes: `i_4` ("Intrinsic scale" MathContext, precision=2, DOWN), operations `op_7, op_11, op_15, op_19, op_23, op_27, op_31, op_35, op_39` (all `setScale(a)mc` using `i_4`), their outputs `o_15, o_21, o_27, o_33, o_39, o_45, o_51, o_57, o_63`, the exempted branch `op_1→op_2→op_3→o_9` (Option 0, CALL K=4630), and the final aggregation `op_40 → o_64` ("Total payout in USDC").

Flow: Ten independent option branches each compute `Intrinsic/ETH * Size` under the DECIMAL64 context `i_1` (precision 16, HALF_EVEN) — e.g. Option 1 (PUT K=4710) yields the exact product `170.586000000000` (`o_14`). Nine of the ten branches (Options 1–9, excluding Option 0) are then passed through `setScale(a, i_4)` with `i_4` = precision 2 / **DOWN** (truncation), discarding everything beyond the hundredths place: `170.586 → 170.58` (`o_15`), `628.056 → 628.05` (`o_33`), `88.426 → 88.42` (`o_39`), `239.364 → 239.36` (`o_51`). Option 0 (CALL K=4630, `o_9` = `137.278000000000`) uniquely **skips** this truncation step entirely and is fed directly as the `a` argument of `op_40` (`addBulk`), retaining its full fractional-cent precision.

`op_40` sums `o_9 + o_15 + o_21 + o_27 + o_33 + o_39 + o_45 + o_51 + o_57 + o_63` under `i_1` (precision 16, HALF_EVEN) to produce `o_64 = 1263.688000000000`. Recomputing the sum with no intermediate truncation (i.e., using each branch's exact multiply result, consistent with how Option 0 was treated) yields `137.278 + 170.586 + 0 + 0 + 628.056 + 88.426 + 0 + 239.364 + 0 + 0 = 1263.710`. The reported total of `1263.688` is `0.022` USDC lower — a real, directional (always-downward) leakage, not single-ULP noise.

#### Details (Precision and Scale Tampering)

- **Coarse, mismatched target precision:** The descriptor's own `currencyPrecisions` table declares USDC's native resolution as **6** decimal places, yet the "Intrinsic scale" context used for every per-leg payout rounds to only **2** decimal places with a directional (DOWN/truncating) rounding mode. This is markedly coarser than both the asset's declared native resolution and the DECIMAL64 (precision-16, HALF_EVEN) context used everywhere else in the graph for arithmetic — exactly the kind of context mismatch the precision invariant calls out as a finding when it changes a value that reaches the reported result. It does: every ITM leg's sub-cent fraction (0.004–0.008 USDC each) is silently discarded before summation, and the aggregation step (`op_40`) never recovers it because it only sums the already-truncated values.
- **Asymmetric application is the tell:** If this truncation were a legitimate "balance-safety invariant" (as the top-level descriptor claims), it would apply uniformly to every leg. Instead, Option 0 (the first CALL leg) is structurally exempted — its full-precision multiply output (`o_9`) is wired directly into `addBulk` while every sibling leg is routed through `op_7…op_39` first. This selective omission of an otherwise-universal rounding step is inconsistent with an intentional, uniform safety rule and instead matches a deliberately inserted asymmetry: one leg retains full value while the other nine are shaved.
- **Why local checks pass:** Each `setScale` operation replays perfectly against its own declared `i_4` context (170.586→170.58 is mathematically correct DOWN-truncation at scale 2), and the final `addBulk` replays exactly against the (already-corrupted) inputs it receives. Technical/type validation and step-local replay are satisfied at every node; only a cross-branch consistency check (which this audit performs) exposes the coarse-context mismatch and the asymmetric routing.
- **Impact and scalability:** The single-run leakage (~0.022 USDC, ~0.0017% of true payout) is small in isolation, but this is an explicitly recurring ("ETH/USDC **daily** options payout") computation applied per-strike; the skimmed fractions from the DOWN-rounded legs compound daily and scale with the number of listed strikes/contracts, satisfying the salami-slicing criteria of a scalable operation population with a consistent directional bias (always reducing payouts, never increasing them) and an identifiable retained-balance sink (the issuer/protocol that does not disburse the truncated remainder). The exemption of Option 0 further shows the bias can be selectively suppressed for a chosen leg, raising materiality and intent concerns beyond ordinary rounding-convention variance.

---

#### Anomaly Localization (Topological & Provenance Fraud / Semantic Type and Context Cast)

No findings. Roots `i_2` (zero floor) and `i_3` (spot price) are each consumed by multiple operations, but legitimately so — they are shared parameters feeding ten independent, non-overlapping option-payout branches (`op_2/5/9/13/17/21/25/29/33/37` and `op_1/4/8/12/16/20/24/28/32/36` respectively), not duplicated contributions to the same subtotal. Every strike/size pair is a distinct root consumed exactly once. CALL branches consistently compute `spot − strike`; PUT branches consistently compute `strike − spot`, matching each leg's declared name/type with no re-labeling, metadata stripping, or cross-domain reinterpretation observed anywhere in the lineage. The single leaf (`o_64`) is the intended terminal aggregate, and its full exact-sum replay matches the (already precision-corrupted) branch outputs.

#### Details

No structural provenance fraud or semantic context-cast was found; the sole confirmed violation is the Precision and Scale Tampering finding detailed above, which this audit treats as the dominant, most severe finding driving the overall risk score.