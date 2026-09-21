# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 80.0

## Anomaly Localization

**Implicated nodes:** `o_9`, `o_10`, `o_11`, `o_12`, `o_13` (the five converted USD asset legs), `op_6` (`addBulk`), and `o_14` (`Assets sum`).

Flow: `i_4`→(`op_1`,rate `i_1`)→`o_9` = 146948.10 USD; `i_5`→(`op_2`,rate `i_2`)→`o_10` = 48624.45 USD; `i_6`→(`op_3`,rate `i_3`)→`o_11` = 538.22 USD; `i_7`→(`op_4`,rate `i_2`)→`o_12` = 12312.36 USD; `i_8`→(`op_5`,rate `i_3`)→`o_13` = 223326.04 USD. All five conversions are technically and semantically consistent (correct currency-to-rate pairing, no cross-asset mixing).

These five legs are then fed into `op_6`, declared as `addBulk` with formula `a+b0+...+bn`, producing `o_14` ("Assets sum", `Amount`/USD).

Replaying the declared formula on the graph's own stated leg values:

146948.10 + 48624.45 + 538.22 + 12312.36 + 223326.04 = **431749.17**

The graph instead records `o_14.value.amount = 441749.17` — exactly **$10,000.00 higher** than what the declared operation, its own formula metadata, and its own listed arguments produce. No sixth argument, adjustment node, reserve, or fee/accrual variable exists anywhere in the graph to account for this delta; `op_6`'s argument list only contains `a, b0, b1, b2, b3`, all of which are accounted for above.

## Details

This is the classic mechanism the attack vector describes: technical type continuity is perfect (every node in the chain is `io.compprov.examples.nav.model.Amount` denominated in USD; the wrapper class and operation name `addBulk` are unchanged; the argument keys match the declared formula signature `a+b0+...+bn`), so any schema/type validator or naive replay-by-name check would appear to pass. But the *business meaning* of `o_14` — "the literal sum of the five converted asset legs" — has been silently broken: the numeric payload no longer represents what its own descriptor ("Assets sum") and its own operation's formula metadata claim it represents. The output has been quietly re-mapped to a different, higher-valued business quantity (effectively "Assets sum + undisclosed $10,000") without any explicit transformation node, adjustment variable, or metadata annotation documenting the $10,000 addition (e.g., no `adjustment`, `reserve`, `manual override`, or `accrual` tag in `descriptor.meta`).

This exploits exactly the audit gap the invariants warn about: a domain transition (verified sub-total → reported NAV total) occurred without "explicit, auditable transformation logic" — the operation *looks* legitimate (right wrapper, right formula name, right argument shape) but its output contradicts its own declared semantics. Because the discrepancy lives entirely inside the value payload rather than in an added/renamed edge or relabeled `descriptor`, naive node-connectivity or schema-compliance checks (and even the structural duplicate/reuse heuristics supplied) would not flag it — there is no duplicate ID, no reused leaf name, no extra argument to inspect. Only re-deriving the arithmetic against the operation's own stated formula surfaces the semantic breach.

**Impact:** the final reported NAV ("Assets sum") is inflated by $10,000 relative to what the pipeline's own conversion legs and declared aggregation logic support, while every technical and type-level check would pass cleanly. This is a materially significant, single-point injection consistent with a targeted, disguised context/value cast rather than benign rounding (rounding noise elsewhere in the graph is at most ~$0.01–0.02 per leg, not $10,000 in aggregate).