# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 74.0

## Anomaly Localization

**Implicated nodes:** `o_7, o_8, o_9, o_10` (Collision payout), `o_15, o_16, o_17, o_18` (Comprehensive payout), `o_23, o_24, o_25, o_26` (Liability payout), `op_13` (addBulk), `o_27` ("Total payout (computed, unused)"), `i_28` ("Total payout", INPUT), `op_14`, `op_15`, `o_30`, `o_31`.

The graph faithfully computes each of the three claim payouts (`o_10`, `o_18`, `o_26` = 6000.0000 / 12000.00 / 4750.0000) and correctly sums them via `op_13` (`addBulk`) into `o_27` = 22750.0000. This is arithmetically exact and internally consistent — a perfect "decoy" computation.

However, `o_27` is a terminal **leaf** node: it is never consumed by any downstream operation (confirmed by the structural leaf-node reference set). Instead, the two operations that actually drive the financially consequential outputs — `op_14` (reinsurance recovery, `o_30`) and `op_15` (net loss, `o_31`) — both take their "Total payout" operand from `i_28`, a **root INPUT variable** with no producing operation at all (confirmed in the structural root-node reference set). `i_28` merely happens to carry the value `22750.00`, numerically equal to `o_27`, but there is **no edge, no equality-check operation, and no lineage** in the graph connecting `o_27` to `i_28`.

Flow of the exploit pattern: Collision + Comprehensive + Liability payouts → `op_13` → `o_27` (proven-correct, but dead-end) ⇴ **(lineage gap)** ⇴ `i_28` (asserted from outside the graph) → `op_14`/`op_15` → `o_30` (Reinsurance recovery), `o_31` (Net loss).

## Details

This is a textbook provenance-break vulnerability: the pipeline manufactures a fully-auditable, arithmetically perfect computation of the total claims payout (`o_27`), which gives a casual reviewer (or an automated replay check) high confidence that "the math checks out." But that verified value is discarded — it is explicitly annotated "(computed, unused)" — and the value actually propagated into the two most consequential downstream financial calculations (reinsurance recovery owed, and net retained loss) is a **freestanding, unverified INPUT** (`i_28`) that shares only a name and a coincidental value with the computed total.

Because `i_28` is a root variable, nothing in the CPG enforces `i_28 == o_27`. An adversary (or a faulty upstream integration) could set `i_28` to any value whatsoever — inflating it to increase apparent reinsurance recovery, or deflating it to understate net loss — while leaving every claim-level operation (`op_1`–`op_12`) and the honest `op_13` summation completely untouched and fully verifiable. A reviewer who validates `op_1`–`op_13` in isolation (as local replay would) sees a clean, correct claims-adjudication chain and would conclude the pipeline is sound, never noticing that the number actually used for reinsurance/net-loss accounting bypasses that chain entirely.

In this specific snapshot the values happen to reconcile (`o_27` = `i_28` = 22750.00/22750.0000), so there is no currently-manifested numeric discrepancy — this is why the arithmetic replay of every individual operation passes with zero delta. But the *design* itself is the vulnerability: it creates an unaudited injection point exactly at the boundary where claim-level payouts are converted into portfolio-level reinsurance and loss figures, which is precisely the mechanism the EXPECTED_INVARIANTS require to be provable ("asset conservation must hold across all intermediate steps"). Here, conservation from the claims stage into the reinsurance/net-loss stage is asserted, not proven, by the graph. This is a materially significant break, not single-ULP rounding noise — it fully decouples a financially critical figure from its supposed derivation, which is a higher-severity issue than an isolated rounding-mode discrepancy would be.

**Impact:** Any downstream consumer trusting `o_30`/`o_31` as "proven from the claims ledger" is exposed to silent substitution risk; the presence of the correct, unused `o_27` computation actively launders this risk by giving auditors a false sense that the whole pipeline is internally verified end-to-end.