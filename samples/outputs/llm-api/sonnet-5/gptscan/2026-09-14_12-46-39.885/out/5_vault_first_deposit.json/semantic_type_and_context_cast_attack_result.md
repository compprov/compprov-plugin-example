# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 74.0

## Anomaly Localization

**Implicated nodes:** `i_1` (First deposit amount), `i_2` (Minimum liquidity constant), `i_3` (Total share supply opening), `op_1` (min), `o_4` (Minimum liquidity floor), `op_2` (add), `o_5` (First depositor shares).

Flow of values:
1. `op_1 = min(i_1, i_2)` → `o_4` ("Minimum liquidity floor" = 1000). This node's declared business purpose is to establish the protocol's anti-inflation-attack floor — the quantity of liquidity that must be permanently locked/excluded from the first depositor's mintable shares (the classic Uniswap-V2-style `MINIMUM_LIQUIDITY` safeguard).
2. `op_2 = add(i_3, i_1)` → `o_5` ("First depositor shares" = 0 + 1,000,000 = 1,000,000). Critically, `op_2` never takes `o_4` as an argument. The depositor's minted shares are computed directly from the *raw, unadjusted* deposit amount (`i_1`), with no reference to the floor that was just computed in `op_1`.
3. `o_4` is a leaf — per the structural reference data it is never consumed by any downstream operation. It is computed and then silently discarded.

End state: `o_5` reports 1,000,000 shares issued to the first depositor, identical to the raw deposit amount, despite the graph itself declaring (via `o_4`) that 1,000 units constitute a "Minimum liquidity floor" that the domain model explicitly tracks as a distinct concept from ordinary mintable shares.

## Details

This is a textbook Semantic Type and Context Cast: technical type continuity is perfect (`BigInteger → BigInteger` throughout, `min`/`add` are both individually valid, side-by-side arithmetic replays correctly), and each operation is locally "legitimate." But the **business context** attached to `i_1` shifts silently between the two consuming operations:

- In `op_1`, `i_1` is treated as a *raw candidate amount* that must be clipped against a protocol-mandated floor — i.e., the model explicitly acknowledges that not all of the raw deposit can be safely treated as mintable liquidity.
- In `op_2`, the *same* `i_1` is fed into the share-minting addition as if it were already the fully-adjusted, floor-compliant mintable amount — with no subtraction, no reference to `o_4`, and no explicit transformation node reconciling the two.

The presence of a dedicated "Minimum liquidity floor" output that is structurally severed from the shares calculation is precisely the disguise pattern called out in the attack definition: the pipeline *appears* to implement the safety invariant (an operation with the right name and the right formula exists and produces the textbook 1000-unit floor), which is exactly what a naive schema/type/connectivity check would look for and find satisfied. But the semantic contract — that this floor must be deducted from (or otherwise reconcile with) the shares minted to the first depositor — is never honored downstream. `o_4` becomes a decorative, unused safety check (a "Potemkin invariant"), while `o_5` silently consumes `i_1` under the conflicting business definition of "already-adjusted mintable shares."

**Consequence:** The first depositor is minted the full 1,000,000 shares instead of the domain-implied 999,000 (1,000,000 − 1,000 floor), with no compensating lock/burn of the minimum-liquidity amount anywhere in the graph. This defeats the purpose of computing the floor at all, reintroducing the exact share-inflation/first-depositor-manipulation vector that the `min(a,b)` floor computation was ostensibly designed to prevent — while still passing type-checking, schema validation, and per-operation arithmetic replay with no discrepancy.

No metadata in the graph (`descriptor.meta` on `o_4`, `op_1`, or `op_2`) documents an explicit rationale for why the floor is computed but not applied; per the audit discipline, this absence of an auditable transformation/justification, combined with the concrete structural disconnect between `o_4` and `o_5`, is sufficient to treat this as a genuine violation rather than a benign convention.