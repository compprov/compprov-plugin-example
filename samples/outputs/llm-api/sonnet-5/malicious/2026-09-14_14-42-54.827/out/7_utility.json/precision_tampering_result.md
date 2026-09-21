# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 93.0

## Anomaly Localization

**Implicated nodes:** `o_15` (Tier 2 cost), `op_9` (`addBulk`), `o_17` (Total energy cost), `o_19` (Total bill), and by extension `o_11`/`op_7` (Tier 2 usage portion / multiply, whose output is silently orphaned).

**Flow of the defect:**

1. `op_7` (`multiply`) correctly computes `o_15 = o_11 * i_7 = 500 * 0.14 = 70.00` — the Tier 2 energy cost.
2. `op_9` (`addBulk`, formula `(a+b0+...+bn)mc`) is supposed to sum *all three* tier costs into the "Total energy cost" (`o_17`). Its declared argument list is only `{a: o_14, b0: o_16, mc: i_1}` — **`o_15` is never passed in**, despite the wrapper's own formula metadata implying an arbitrary-arity `b0...bn` accumulation designed exactly for this three-term sum.
3. As a direct consequence, `o_17 = o_14 + o_16 = 50.00 + 32.40 = 82.40`, silently dropping the $70.00 Tier 2 charge.
4. `op_10` (`add`) then computes `o_19 = o_17 + i_18 = 82.40 + 12.50 = 94.90`.
5. The structurally-confirmed fact that `o_15` is a **leaf variable** (never consumed as an argument by any operation) is the fingerprint of this defect: a fully computed, correctly-rounded intermediate charge is produced and then never enters any downstream aggregation.

Correct total bill, given the tiered inputs (500 kWh @ $0.10, 500 kWh @ $0.14, 180 kWh @ $0.18, plus $12.50 service charge) should be:
$50.00 + $70.00 + $32.40 + $12.50 = **$164.90**, not the reported **$94.90** — a $70.00 (≈74%) undercount.

## Details

This is not a sub-cent rounding-mode artifact or a MathContext boundary effect — every individual multiplication (`op_6`, `op_7`, `op_8`) and the tier-boundary `min`/`max`/`subtract` operations are all exactly correct to the reported scale. The flaw is a **missing addend in the aggregation step**: the `addBulk` operation's argument dictionary structurally excludes `o_15` from the sum it is supposed to perform over all tier costs. This is exactly the "forcibly reducing an intermediate value's scale/contribution before completing an aggregation, causing residual value leakage" pattern described in the attack vector definition — except here the leakage is total (100% of that term) rather than partial.

Why this passes casual review: every single-operation calculation is individually correct (each multiply, subtract, min/max step reconciles perfectly against its inputs), so any check that only verifies pointwise arithmetic per operation would find nothing wrong. The defect is only visible when tracing the *aggregation's argument set* against the full set of tier-cost outputs, and by noting that `o_15` — despite being a materially meaningful, correctly computed value — never appears as an argument anywhere downstream. The exact-match leaf/name-collision heuristics also don't catch this, since `o_15` doesn't share a name with anything else; it is simply orphaned.

**Consequence:** the reported "Total bill" (`o_19` = $94.90) materially understates the true amount owed ($164.90) by omitting the entire Tier 2 usage charge. Per the materiality-override guidance, a single large, non-cyclic arithmetic/aggregation defect of this magnitude (74% of the correct total) is a severe, reportable violation of asset conservation regardless of whether it recurs across billing cycles — and if this pipeline is reused per customer per month, the same tier-skipping logic would silently under-bill every account whose usage crosses into Tier 2, which would compound the severity further at scale.