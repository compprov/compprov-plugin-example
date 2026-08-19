# Summary
- **Verdict**: CLEAN
- **Confidence score**: 80.0

## Methodology
Every arithmetic operation in the amortization chain (op_1 through op_24) was recomputed using exact rational/decimal arithmetic and then re-rounded according to the *exact* semantics of `java.math.BigDecimal` operations under the declared `MathContext` (`i_1`: precision=16, HALF_EVEN), including tracking of scale propagation (subtract/add scale = max(scale(a),scale(b)); multiply scale = scale(a)+scale(b) prior to MathContext rounding) and explicit significant-digit counting to determine exactly when the 16-digit precision ceiling forces a rounding step.

### Key checkpoints verified bit-for-bit
- **o_6→o_21 (Months 1–4 interest/principal/balance):** All multiply/subtract chains reproduce the reported values exactly, including cases with no rounding needed (fewer than 16 significant digits).
- **o_16 (Month 3 balance):** Exact pre-rounding value is `236867.50336000000` (17 significant digits) → correctly rounds (HALF_EVEN, discarded digit = 0) to `236867.5033600000` (16 sig figs). Matches reported value exactly.
- **o_20 (Month 4 balance):** Exact value `235814.973373440000` (18 digits) rounds down (discarded digits = "00") to `235814.9733734400`. Matches.
- **o_24 (Month 5 principal):** Exact `1076.7401065062400` → rounds to `1076.740106506240`. Matches.
- **o_25 (Month 5 balance):** Exact `229738.233266933760` — discarded fraction "60" > 0.5 → correctly rounds *up* the 16th digit (7→8) to `229738.2332669338`. Matches exactly, confirming genuine HALF_EVEN (not truncation/floor) is being applied.
- **o_28 (Month 6 principal):** Exact `1081.0470669322648` — discarded digit 8 → rounds up 4→5 → `1081.047066932265`. Matches.
- **o_29 ("Ending balance, computed, unused"):** Exact `228657.186200001535` — discarded fraction "35" < 0.5 → rounds down to `228657.1862000015`. Matches, and independently cross-checks: `240000.00 − (Σ scheduled principal Month1–6 + prepayment 5000.00) = 228657.1862000015`, confirming full asset conservation across the six-month schedule.
- **o_32 (Total interest):** Exact sum of the six monthly interest figures = `5657.1862000014952` → rounds down (discarded digit 2) to `5657.186200001495`. Matches.
- **o_33/o_34/o_35/o_36 (escrow, scheduled payments, totals):** All addBulk/add operations reproduce exactly (`2400.00`, `12000.00`, `14400.00`, `19400.00`); no scale/unit mixing detected, and the final "Total amount paid by borrower" is arithmetically consistent with 6×(payment+escrow) + prepayment.

No operation shows a rounding-mode or scale deviation of any kind — every HALF_EVEN round-up and round-down decision (including several genuine round-up cases that a truncating/floor implementation would have gotten wrong) matches the reported figures exactly. There is no evidence of systematic directional bias (salami slicing), no evidence of a differing MathContext being silently substituted, and no unit/scale mixing.

## Anomaly Localization (structural observation, not a confirmed precision-tampering violation)
- **`o_29`** ("Ending balance (computed, unused)", value `228657.1862000015`) is a leaf node: it is never consumed by any downstream operation.
- **`i_31`** ("Ending balance", value `228657.19`) is simultaneously a root (no producing operation) *and* a leaf (never consumed) — it is fully disconnected from the rest of the computation graph.
- These two nodes describe the *same* real-world quantity, computed independently (one derived, one apparently hand-supplied/external), yet neither feeds into any of the final reported outputs (`o_32`–`o_36`), and they are never reconciled against each other by any operation in the graph.

## Details
The disconnected duplicate "Ending balance" pair is a textbook shape for a *shadow-value* substitution pattern — a precise computed figure discarded and replaced by an unverified, coarser-precision externally-sourced figure — which is exactly the kind of thing this audit vector is designed to catch. In this instance, however, the two values agree to the cent (`228657.1862000015` rounds to `228657.19`), and — critically — **neither value propagates into any of the reported final outputs**, so there is no material or even immaterial effect on `o_32` (total interest), `o_33` (total escrow), or `o_36` (total amount paid). Asset conservation was independently verified end-to-end using only the values that *do* participate in the operational chain, and it holds exactly.

Given that (1) every single rounding decision across 24 operations exactly matches the mathematically correct MathContext(16, HALF_EVEN) result — including several non-trivial round-up cases that a naive truncating implementation would fail — and (2) the one structurally odd pair of orphaned nodes does not feed into or affect any reported figure, there is no demonstrated Precision/Scale Tampering impact on this pipeline's outputs. The orphaned "Ending balance" input (`i_31`) and unused computed twin (`o_29`) are flagged for governance/data-hygiene review (why does an unused, hand-entered INPUT duplicating a computed OUTPUT exist at all?), but this does not rise to a confirmed violation of the stated EXPECTED_INVARIANTS for this specific attack vector.