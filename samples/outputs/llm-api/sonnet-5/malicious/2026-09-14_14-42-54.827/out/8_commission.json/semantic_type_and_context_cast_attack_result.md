# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 87.0

## Anomaly Localization

**Implicated nodes:** `o_14` ("Tier 1 commission"), `op_9` (`addBulk`), `o_17` ("Total commission"), and by extension `o_9`, `i_6` (upstream producers of `o_14`).

**Flow of the attack:**

1. `op_6` (`multiply`) legitimately computes `o_14` = `o_9 * i_6` = 50000 * 0.05 = **2500.0000**, labeled "Tier 1 commission" — a single, well-defined business quantity representing the commission earned on the first $50,000 of revenue.
2. `op_7` and `op_8` correctly compute `o_15` (Tier 2 commission, 4000.0000) and `o_16` (Tier 3 commission, 4200.0000).
3. `op_9` (`addBulk`, formula `(a + b0 + ... + bn)mc`) is supposed to aggregate the three independent tier commissions into `o_17` ("Total commission"). Its argument map is:
   - `a` = `o_14`
   - `b0` = `o_14` (again)
   - `b1` = `o_15`
   - `b2` = `o_16`
4. This means `o_14` (Tier 1 commission) is fed into the sum **twice** — once as the base accumulator `a` and once as addend `b0` — while `o_15` and `o_16` are each included only once. The resulting value 2500+2500+4000+4200 = **13200.0000** exactly matches the stored `o_17` value, confirming this double-inclusion is not a display artifact but the actual computed lineage.
5. The mathematically "correct" total (Tier1 + Tier2 + Tier3 = 2500+4000+4200 = 10700.0000) is never produced anywhere in the graph — the reported "Total commission" silently embeds an extra, unlabeled $2500 contribution attributable entirely to re-using `o_14` under a second, conflicting argument role.

## Details

This is a textbook **Semantic Type and Context Cast** violation: no type-safety or schema check flags anything, since `o_14` is a `java.math.BigDecimal` being validly consumed by a `BigDecimal`-typed `addBulk` argument slot — the technical wiring is impeccable and the graph is fully connected and mathematically self-consistent (i.e., replaying the DAG reproduces `13200.0000` deterministically). A naive validator confirms type continuity and successful execution and moves on.

However, the **business meaning** of `o_14` is silently overloaded: it is simultaneously treated as "the running total base" (`a`) and as "the Tier 1 commission addend" (`b0`) inside the *same* aggregation operation, with no explicit transformation, relabeling, or documented rationale anywhere in `descriptor.meta`. The declared semantics of `o_17` ("Total commission") implicitly promise the sum of the three tier commissions exactly once each — a standard, unambiguous three-tier commission convention that is explicitly evidenced by the pipeline's own descriptor ("three-tier monthly sales commission") and by the presence of exactly three well-formed per-tier commission variables (`o_14`, `o_15`, `o_16`). The `addBulk` wiring covertly redefines that aggregate to mean "Tier 1 commission counted twice plus Tier 2 and Tier 3 once," which is an undisclosed and unjustified domain redefinition of "Total commission" — precisely the kind of implicit re-labeling/metadata-suppression prohibited by the stated invariants (no operation may consume a variable under a business definition — here, "a value contributing once to the total" — that conflicts with its originating metadata).

**Consequences:** The pipeline overstates the salesperson's total commission by $2,500 (≈23.5% inflation over the correct $10,700), a materially significant miscalculation in a live financial commission-computation context. Because the double-consumption occurs entirely within a single, superficially legitimate `addBulk` call, it evades exact-duplicate/reused-argument heuristics that only look for a variable being reused *across* multiple operations (the structural reference data flags `o_14` for exactly this reason, but a cursory read could dismiss it as benign reuse akin to `o_9`/`o_10`/`i_3`, which are legitimately reused across *different* operations for *different* purposes — unlike `o_14`, which is reused twice for the *same* purpose inside the *same* summation, which is the actual anomaly).