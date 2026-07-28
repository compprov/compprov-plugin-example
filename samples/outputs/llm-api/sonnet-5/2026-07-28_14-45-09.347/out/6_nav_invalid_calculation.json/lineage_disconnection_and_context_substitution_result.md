# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 93.0

## Anomaly Localization (If Detected)

**Implicated nodes:** `o_9`, `o_10`, `o_11`, `o_12`, `o_13`, `op_6`, `o_14`.

The attack surface examined (name-collision-based Intermediate Context Hijacking on the sole leaf `o_14`) does **not** show an exact or near-name-collision substitute — no other variable in the graph shares `o_14`'s name/role ('Assets sum'), and `o_14` has no downstream consumer to bypass. So the textbook name-collision variant of Context Substitution is *not* present here.

However, forward-propagating the graph's own declared computation chain reveals a direct violation of `Origin_Propagation_Valid` at the final aggregation step itself, `op_6` (`addBulk`):

- `op_1..op_5` (`convert`) produce `o_9=146948.10`, `o_10=48624.45`, `o_11=538.22`, `o_12=12312.36`, `o_13=223326.04` from the correctly-referenced root inputs (`i_1..i_8`). These are the exact `resultId`s consumed as arguments (`a,b0,b1,b2,b3`) of `op_6`.
- Summing the *actual arguments consumed by op_6* — the very values the graph itself asserts feed the sum — yields:
  `146948.10 + 48624.45 + 538.22 + 12312.36 + 223326.04 = 431749.17`
- The value stored at `resultId` `o_14` ('Assets sum') is `441749.17` — exactly **$10,000.00 higher** than the value that forward propagation of `op_6`'s own declared arguments produces.
- This is not a rounding artifact: even summing the unrounded intermediate products (2.13×68989.72 + 23.34×2083.31 + 532.9×1.01 + 5.91×2083.31 + 221114.9×1.01 ≈ 431749.20) confirms the true derivable total is ~431,749, never ~441,749. The discrepancy is a clean, round $10,000 — inconsistent with any accumulation of legitimate rounding noise (which tops out around $0.03 across five conversions).

The attack flow: five legitimately-computed, correctly-sourced conversion outputs (`o_9`–`o_13`) are correctly wired as arguments into `op_6`, satisfying local structural and even local mathematical replay expectations for the upstream steps. But the *result* recorded for `op_6` (`o_14`) is not the value that operation would actually produce from those arguments — it is a value with an injected $10,000 excess, silently substituted at the final compliance-facing output. Local replay of each `convert` step passes; a careless reviewer checking that arguments reference the correct upstream `resultId`s would also see everything wired correctly. Only full forward-propagation of the arithmetic — comparing $O_{derived}$ (≈431,749.17-431,749.20) against $O_{reported}$ (441,749.17) — exposes the rupture.

## Details

**Mechanism:** This is a variant of Lineage Disconnection distinct from the classic name-collision hijack: rather than swapping which *variable* is consumed as an argument, the adversary (or compromised pipeline) has kept the argument wiring of `op_6` fully intact and provenance-correct, but overridden the *stored result* of the operation node itself. The `arguments` list of `op_6` faithfully points at `o_9..o_13` — passing any check that inspects "does this operation consume the correct upstream resultIds." The corruption lives entirely in the `value` field attached to `o_14`, which was populated independently of the `addBulk` formula the operation node claims to execute. This slips past casual/local review because:
1. Every individual `convert` operation replays correctly (to within ±0.01 rounding) against its own root inputs.
2. The argument-to-resultId wiring into `op_6` is syntactically perfect — no orphaned or substituted argument IDs, no name collisions to trip exact-match heuristics.
3. Only recomputing the `addBulk` formula itself and comparing to the recorded `o_14.value` exposes the injected $10,000.

**Consequences:** `o_14` ('Assets sum') is the terminal OUTPUT of this NAV computation — the leaf node representing total portfolio assets in USD. A NAV/assets figure inflated by exactly $10,000 relative to what the fund's own declared, auditable computation chain (BTC/ETH/USDC balances × their converted rates) actually supports is a direct and material misstatement of a compliance-critical financial output. Because the tampering occurs at the final summation result rather than at an argument substitution point, it is even harder to catch via lineage-tracing tools that only validate "did operation X consume the correct upstream variable ID" without independently re-deriving and checking the numeric result against that operation's own formula.

**Secondary observations (lower materiality):** `o_10`, `o_11`, and `o_13` each show a small (~$0.01) downward rounding deviation from their inputs relative to standard round-half-up arithmetic (e.g., `o_11` = 532.90 × 1.01 = 538.229 → expected 538.23, recorded 538.22). These are individually immaterial and plausibly attributable to a different/legitimate rounding mode (e.g., HALF_DOWN vs HALF_UP), but are noted because they cannot cumulatively explain the $10,000 gap at `o_14`, and their consistent one-directional bias is itself worth follow-up in a full audit.