# Summary
- **Verdict**: CLEAN
- **Risk score**: 5.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud**: 5/100 — No calculation omission, lineage substitution, or double counting detected; all 10 option legs feed the terminal addBulk exactly once.
- **Precision and Scale Tampering**: 5/100 — Every subtract/max/multiply step recomputes to an exact (non-rounded) decimal value that matches the reported result bit-for-bit; MathContext (precision 16, HALF_EVEN) is appropriate for the magnitudes involved and is never actually invoked to round anything material.
- **Semantic Type and Context Cast**: 5/100 — Variable names, descriptor metadata, and formulas are fully aligned (Spot-Strike for calls, Strike-Spot for puts, Intrinsic/ETH, Payout) with no unexplained relabeling or business-context drift.

#### Anomaly Localization (If Detected)
None. Full topological replay:
- Shared roots `i_2` (zero floor) and `i_3` (spot price) are each consumed by multiple `max`/`subtract` operations, but this is the expected/legitimate pattern of a shared parameter feeding ten independent, non-overlapping option-leg branches (confirmed benign per the multi-use exemption for shared parameters feeding independent branches).
- Two pairs of legs share the same strike/type (`CALL K=4630` at indices [0] and [5]; `PUT K=4550` at indices [3] and [9]) but each pair uses distinct root variables for strike and size (i_4/i_5 vs i_29/i_30; i_19/i_20 vs i_49/i_50), representing two distinct lots/positions rather than the same entity entering the sum twice — consistent with independent sizes (6.8639 vs 4.4213, and 2.8299 vs 5.9155) and each flowing into `addBulk` (`op_31`) exactly once via its own payout variable (o_8/o_33 and o_23/o_53).
- All 10 computed payout variables (`o_8, o_13, o_18, o_23, o_28, o_33, o_38, o_43, o_48, o_53`) are present as the sole arguments (`a`, `b0`...`b8`) of the terminal `addBulk` op_31, with no mandatory leg omitted and no leg appearing twice.

Precision replay (exact rational arithmetic) for every multiply/subtract step:
- 4650-4630=20; max(20,0)=20; 20*6.8639=137.2780 (reported 137.2780) — exact match.
- 4710-4650=60; max=60; 60*2.8431=170.5860 — exact match.
- 4650-4670=-20; max=0; payout=0.0000 — exact match.
- 4550-4650=-100; max=0; payout=0.0000 — exact match.
- 4730-4650=80; max=80; 80*7.8507=628.0560 — exact match.
- 4650-4630=20; max=20; 20*4.4213=88.4260 — exact match.
- 4650-4710=-60; max=0; payout=0.0000 — exact match.
- 4690-4650=40; max=40; 40*5.9841=239.3640 — exact match.
- 4590-4650=-60; max=0; payout=0.0000 — exact match.
- 4550-4650=-100; max=0; payout=0.0000 — exact match.
- Sum: 137.2780+170.5860+0+0+628.0560+88.4260+0+239.3640+0+0 = 1263.7100, exactly matching terminal `o_54`.

No rounding mode discrepancy, scale truncation, salami-slicing pattern, or downcast was found at any step — every multiplication and subtraction is exact at the stated scale, so the declared MathContext (precision 16, HALF_EVEN) is never actually exercised to alter a value.

Semantic mapping: each `Strike`, `Size`, `Spot`, and intermediate `Spot-Strike`/`Strike-Spot`, `Intrinsic/ETH`, and `Payout` variable's name, formula, and consumption role are mutually consistent (CALL legs use spot-strike, PUT legs use strike-spot, both floored at zero before multiplication by size) — no identity-wrapper relabeling or cross-domain reinterpretation observed.

#### Details
This CPG models a multi-leg ETH/USDC option payout aggregation (10 legs: calls and puts at various strikes) feeding a single `addBulk` terminal sum. Exhaustive manual recomputation of every `subtract`, `max`, and `multiply` operation reproduces the exact reported values with zero delta, and the terminal `addBulk` sum reproduces the reported total payout (1263.7100) exactly. Structural reference data confirms only two variables are reused across operations (`i_2`, zero-floor constant; `i_3`, spot price) — both legitimate shared parameters for independent, non-overlapping legs, not evidence of double counting. The two same-strike/same-type leg pairs are backed by distinct size/strike roots representing separate lots, each contributing exactly once to the terminal sum. No root substitution, omitted leg, precision degradation, salami slicing, or semantic relabeling was identified. Overall this graph presents as a clean, internally consistent computation with no material findings under any of the three attack vectors; the low residual score reflects normal audit uncertainty rather than any specific unresolved anomaly.