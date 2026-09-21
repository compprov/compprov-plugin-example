# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 88.0

## Anomaly Localization

**Implicated nodes:** `i_1` (Existing votes for proposal), `i_2` (Voter balance at proposal snapshot block, blockNumber=19000000), `i_3` (Voter balance at current block, blockNumber=19000050), `op_1` (add, formula `a+b`), `o_4` (New vote tally).

Flow: `op_1` computes `o_4 = i_1 + i_3` (250000 + 1000000 = 1250000). The snapshot-balance variable `i_2` (10000, tagged with `blockNumber: 19000000` — the proposal snapshot block) is created, fully typed as `java.math.BigInteger`, given a descriptive name identifying it as the governance-canonical voting balance, and then **never consumed by any operation** — it is a root AND a leaf simultaneously, i.e., dead data. Meanwhile `i_3`, explicitly labeled "Voter balance (current block)" and tagged with a *later* block number (19000050, 50 blocks after the snapshot), is fed directly into the tally addition in place of the snapshot value.

## Details

This is a textbook Semantic Type and Context Cast Attack. Technically, everything validates cleanly: `i_3` is a `BigInteger`, the target argument slot expects a `BigInteger`, and `add(a, b)` is a legitimate, auditable arithmetic operation — mathematical replay of `250000 + 1000000 = 1250000` is fully correct. A naive schema/type checker or replay-verifier sees no problem whatsoever.

The violation is at the business-semantics layer. In on-chain/off-chain governance systems, voting power is required by design to be measured **at the proposal snapshot block** specifically to prevent manipulation (e.g., an actor acquiring or borrowing tokens after the snapshot, or via a flash loan, to inflate their vote weight before tallying). The graph's own metadata confirms this convention: `i_2` is explicitly tagged as the snapshot-block balance and is the only variable whose `descriptor` and `blockNumber` meta align with governance-safe voting logic. Yet the `add` operation silently substitutes `i_3` — the *current*, later-block, 100x-larger balance — into the exact argument position where `i_2` (the snapshot balance) should semantically belong, given the variable naming and the goal of the pipeline ("New vote tally").

There is no explicit domain-transformation node justifying why current-block balance should be treated as valid, snapshot-equivalent voting power — no re-basing, no explicit adjustment logic, no annotation reconciling the two balances. The type system and operation signature (`a`, `b` → `BigInteger` add) tolerate this substitution perfectly, which is precisely the gap this attack vector exploits: `C_source` (current, mutable, post-snapshot balance) has been silently cast into `C_target`'s expected role (immutable, snapshot-locked voting balance), producing a materially wrong and manipulable governance tally while leaving the mathematically "correct-looking" snapshot variable (`i_2`) untouched and orphaned as a decoy.

**Consequence:** The reported `o_4` ("New vote tally" = 1,250,000) is inflated using a balance that can be freely manipulated after the snapshot cutoff (e.g., token transfers, flash loans, wash trading between blocks 19000000–19000050), completely defeating the purpose of snapshot-based governance and enabling vote-weight manipulation without violating any technical type or connectivity check.