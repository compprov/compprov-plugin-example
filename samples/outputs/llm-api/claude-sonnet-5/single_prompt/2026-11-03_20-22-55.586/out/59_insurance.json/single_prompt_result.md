# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 85.0

#### Per-Vector Verdict

1. **Topological & Provenance Fraud — Lineage Disconnection and Context Substitution**: Score 85 — CONFIRMED. The computed subtotal `o_27` ("Payout sum") is a dead-end leaf; the downstream reinsurance/net-loss calculations instead consume a root variable `i_28` ("Total payout") that merely duplicates `o_27`'s value under a different id.
2. **Precision and Scale Tampering**: Score 5 — Clean. All `addBulk`, `subtract`, `multiply`, `min`/`max` steps reconcile exactly under the declared `MathContext` (precision 16, HALF_EVEN); no truncation, salami-slicing, or coarse-context substitution detected.
3. **Semantic Type and Context Cast**: Score 10 — No contradictory business-context relabeling found; claimType metadata and the deductible→floor→coinsurance→cap chain are internally consistent for all three claim lines.

Overall risk is driven by the Topological finding (max across vectors).

---

#### Anomaly Localization (Topological & Provenance Fraud)

- **Computed value bypassed**: `op_13` (`addBulk`) sums `o_10` (Collision payout, 6000.0000) + `o_18` (Comprehensive payout, 12000.00) + `o_26` (Liability payout, 4750.0000) → `o_27` ("Payout sum" = 22750.0000). `o_27` is confirmed by the structural reference data to be a **leaf** — it has zero downstream consumers.
- **Substitute root**: `i_28` ("Total payout" = 22750.00) is a **root variable** (per structural reference data, no operation produces it) that is fed directly into `op_14` (multiply by `i_29` reinsurance rate → `o_30` Reinsurance recovery) and `op_15` (subtract `o_30` → `o_31` Net loss, the graph's ultimate terminal output).
- **Flow of the attack**: Collision/Comprehensive/Liability branches (`i_3..i_6`, `i_11..i_14`, `i_19..i_22` → `op_1..op_12`) are faithfully computed into `o_10`, `o_18`, `o_26`, and correctly aggregated into `o_27` by `op_13`. That aggregate is then **discarded** — it never reaches `o_30` or `o_31`. Instead, `i_28`, an externally-supplied root with no computational lineage, stands in for it and is the sole ancestor of the terminal results `o_30` and `o_31`.

#### Details (Topological & Provenance Fraud)

This is a textbook **Lineage Disconnection and Context Substitution**: a computed value (`o_27`) and a root variable (`i_28`) play the identical business role ("sum of claim payouts" / "Total payout"), carry the same numeric value in this instance, and the root is consumed in place of the computed chain for every downstream step that matters. Local replay of `op_14` and `op_15` passes perfectly because `i_28`'s value happens to equal `o_27`'s — but that equality is not enforced anywhere in the graph; nothing ties `i_28` to the three underlying claim computations. Per the attack vector's own guidance, neither "the substitute is a root with full metadata" nor "the computed value has no consumers" nor "the value is close to the computed one" is a valid defense — they are exactly the symptoms this check is designed to catch.

**Practical impact**: Any future recomputation of the three individual claims (e.g., a change to a deductible, coinsurance rate, or policy limit) will correctly update `o_7..o_26` and the now-orphaned `o_27`, but will have **zero effect** on the reported `Reinsurance recovery` (`o_30`) or `Net loss` (`o_31`), because those are sourced from the independently-supplied `i_28`. This allows the true aggregated payout and the reported "Total payout" to silently diverge — an adversary (or a stale/manually-entered field) controlling `i_28` can misstate the terminal financial result while every individual claim calculation, and even the internal subtotal `o_27`, remains provably correct in isolation. This is precisely the kind of surgical substitution that passes casual type-checking and per-step replay but breaks true end-to-end provenance.

No additional findings were material under the Precision/Scale or Semantic Type/Context vectors: rounding contexts are uniform (HALF_EVEN, precision 16) and appropriate for currency math, the floor/coinsurance/cap sequence matches each variable's declared name, and claimType metadata is preserved consistently through each of the three independent branches.