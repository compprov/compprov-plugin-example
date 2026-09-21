# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 78.0

## Anomaly Localization

**Implicated nodes:** `i_1` (Sender balance, opening, 10000), `i_2` (Transfer amount, 3000), `i_3` (Reward rate, 2), `o_4` (Sender balance, closing, 7000, produced by `op_1`), `o_5` (Sender checkpoint reward, 14000, produced by `op_2`).

**Flow:**
1. `op_1` (`subtract`): `o_4 = i_1 - i_2` = 10000 - 3000 = 7000. `o_4` is explicitly tagged `balanceBasis: closing`. This step is internally consistent — opening balance minus transfer = closing balance.
2. `op_2` (`multiply`): `o_5 = o_4 * i_3` = 7000 * 2 = 14000. Critically, `o_5`'s own descriptor metadata tags it `balanceBasis: opening`, yet its sole numeric input (`a` argument) is `o_4`, which is explicitly and unambiguously tagged `balanceBasis: closing`.

The result `o_5` ("Sender checkpoint reward") is therefore mathematically derived from the **closing** balance while being declared, in its own provenance metadata, to be computed on an **opening**-balance basis.

## Details

This is a textbook Semantic Type and Context Cast: technical type continuity is perfectly preserved (`BigInteger -> BigInteger -> BigInteger`), the `multiply` operation is schema-valid, and the arithmetic replay is internally consistent (7000 * 2 = 14000 is not a computational error). What is corrupted is the *business meaning* attached to the result. The `balanceBasis` metadata tag is the graph's explicit, auditable declaration of which balance snapshot a reward calculation is meant to be indexed against. A naive/automated validator checking only type signatures, argument wiring, and arithmetic correctness would pass this node without complaint — exactly the gap this attack class is designed to exploit.

The practical consequence: if the checkpoint reward is contractually or by-design supposed to be computed against the pre-transfer (opening) balance — as its own label claims — the correct computation should have consumed `i_1` (10000), yielding a reward of 20000, not 14000. Instead, the pipeline silently substitutes the post-transfer closing balance (`o_4`, tagged `closing`) into a slot that is declared and consumed downstream as an "opening"-basis reward figure. This understates the reward by 6000 units (30%) while leaving no trace of the substitution other than the metadata contradiction between `o_4` and `o_5` — no explicit transformation node (e.g., a documented "basis conversion" or "post-transfer adjustment" operation) exists to justify remapping an opening-basis reward onto a closing-basis input.

This satisfies the invariant violation criteria directly: `C_source (closing, from o_4) != C_target (opening, declared on o_5)` despite identical, fully type-safe `BigInteger` arithmetic. The mismatch is an explicit, in-graph contradiction (not merely omitted metadata), which the audit discipline for this attack class requires be reported rather than rationalized away as an intermediate-variable metadata omission.