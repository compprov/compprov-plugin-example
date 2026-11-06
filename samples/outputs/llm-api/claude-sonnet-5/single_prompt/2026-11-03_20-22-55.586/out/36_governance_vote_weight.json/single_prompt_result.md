# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 95.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud — Lineage Disconnection / Context Substitution**: 95/100 — CONFIRMED. The snapshot-block voter balance (`i_2`) is computed/supplied but never consumed; a later, substantially larger current-block balance (`i_3`) is substituted into the tally in its place.
- **Precision and Scale Tampering**: 5/100 — Clean. Single `BigInteger` addition, no rounding, scaling, or type-downcast involved.
- **Semantic Type and Context Cast**: 85/100 — CONFIRMED. A balance explicitly labeled as belonging to a different (later, post-snapshot) block context is consumed by the tally operation as if it were the snapshot-authorized voting-power figure, despite `BigInteger -> BigInteger` type continuity.

---

#### Anomaly Localization (Topological & Provenance Fraud)
- **Root variables involved**: `i_1` ("Existing votes for proposal", 250000), `i_2` ("Voter balance (at proposal snapshot block)", blockNumber=19000000, value=10000, **leaf — never consumed by any operation**), `i_3` ("Voter balance (current block)", blockNumber=19000050, value=1000000).
- **Operation**: `op_1` — `add(a=i_1, b=i_3) -> o_4` ("New vote tally" = 1,250,000).
- **Flow**: The graph explicitly carries two candidate values for the same real-world entity — the voter's token balance — tagged with two different block numbers 50 blocks apart. The governance-correct value, per standard snapshot-voting design, is `i_2` (the balance *at the proposal snapshot block*, 19000000), which is the value that should determine voting power to prevent balance manipulation between proposal creation and vote casting. Instead, `op_1` consumes `i_3`, the balance at a *later* block (19000050), which is 100x larger (1,000,000 vs 10,000). `i_2` is left as an untouched, unconsumed leaf root — exactly the signature of a superseded/bypassed root described in the task's substitution pattern. The terminal output `o_4` (1,250,000 = 250,000 + 1,000,000) is therefore derived from an unauthorized, post-snapshot balance rather than the graph's own designated snapshot value.

#### Details (Topological & Provenance Fraud)
This is a textbook snapshot-voting bypass / flash-loan-style governance exploit pattern made visible at the data-lineage level: the pipeline retains both the legitimate snapshot balance and a convenient later balance, and routes the later (larger) one into the final tally while discarding the former. Local replay of `op_1` passes perfectly — `250000 + 1000000 = 1250000` is arithmetically correct — so naive validation (type-checking, formula replay) finds nothing wrong. The violation is purely about *which* root reaches the terminal result. Because `i_2` and `i_3` represent the same entity (voter balance) differing only by block height, and only one should legitimately gate voting power (the one fixed at proposal snapshot), the presence of a provenance path from `i_3` into `o_4` while `i_2` dead-ends is a Lineage Disconnection / Context Substitution violation. Impact: voting power attributable to this voter is inflated 100x (10,000 → 1,000,000), directly and materially corrupting the governance tally and potentially flipping proposal outcomes — this is a severe, high-impact finding regardless of whether it recurs.

---

#### Anomaly Localization (Semantic Type and Context Cast)
- **Variables**: `i_3` — declared business context: "Voter balance (current block)", blockNumber=19000050. Consumed by `op_1` as argument `b` into the computation of `o_4` ("New vote tally"), a context that structurally requires a *snapshot-locked* balance (as evidenced by the presence and naming of the parallel variable `i_2`, explicitly tagged blockNumber=19000000, "at proposal snapshot block").
- **Operation**: `op_1` (`add`).

#### Details (Semantic Type and Context Cast)
Both `i_2` and `i_3` are technically `java.math.BigInteger`, so type-checking and mathematical replay pass without issue. However, their `descriptor` metadata explicitly declares two different business contexts: one anchored to the governance-defined snapshot block (the authoritative voting-power reference), the other to an arbitrary later block. The consuming operation `op_1` treats `i_3` as though it were the snapshot-authorized balance by feeding it directly into the vote-tally formula alongside `i_1` ("Existing votes for proposal"), with no explicit transformation node reconciling the block-height discrepancy or justifying the substitution. This is an explicit conflict between a variable's originating metadata (current-block balance) and its downstream consumption (as snapshot voting power) — the precise signature of a Semantic Type and Context Cast attack: identical technical type, divergent business meaning, no auditable transformation bridging the two. The practical consequence mirrors the topological finding: the reported "New vote tally" silently incorporates balance changes that occurred after the proposal snapshot, undermining the integrity guarantee that snapshot-based voting is designed to provide.

---

No findings for Precision and Scale Tampering — the single `BigInteger` addition introduces no rounding, truncation, or scale conversion, and no `MathContext`/floating-point downcast is present in this graph.