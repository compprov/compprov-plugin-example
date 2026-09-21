# Summary
- **Verdict**: CLEAN
- **Risk score**: 3.0

## Audit Summary
No Topological & Provenance Fraud (Calculation Omission, Double Counting, or Lineage Disconnection/Context Substitution) was found in this tiered electricity billing CPG. The graph is a small, fully-connected pipeline and every mandatory contributor to the terminal output `o_19` ("Total bill") has path multiplicity M = 1, originating from its own genuine computation chain.

### Anomaly Localization (If Detected)
None. No variable or operation is implicated in a confirmed violation.

### Details

**Forward trace to terminal output `o_19`:**
- `op_1` min(i_3=1180, i_4=500) → `o_9`=500 ("Usage through tier 1")
- `op_2` min(i_3=1180, i_5=1000) → `o_10`=1000 ("Usage through tier 2")
- `op_3` subtract(o_10, o_9) → `o_11`=500 ("Tier 2 usage portion")
- `op_4` subtract(i_3, o_10) → `o_12`=180 ("Tier 3 usage portion, uncapped")
- `op_5` max(o_12, i_2=0) → `o_13`=180 ("Tier 3 usage portion, floored")
- `op_6` multiply(o_9, i_6=0.10) → `o_14`=50.00 (Tier 1 cost)
- `op_7` multiply(o_11, i_7=0.14) → `o_15`=70.00 (Tier 2 cost)
- `op_8` multiply(o_13, i_8=0.18) → `o_16`=32.40 (Tier 3 cost)
- `op_9` addBulk(o_14, o_15, o_16) → `o_17`=152.40 (Total energy cost)
- `op_10` add(o_17, i_18=12.50) → `o_19`=164.90 (Total bill)

**Reference-data cross-checks:**
- *Leaf set* = {o_19} only. `o_19` is the legitimate sole terminal output, not an omitted mandatory contributor left dangling — every other output (`o_9`–`o_17`) is consumed exactly once downstream. No M=0 (Calculation Omission) candidates exist; all tier costs and the service charge (`i_18`) are present in the final `addBulk`/`add` chain that produces the reported total.
- *Leaf name-collision set* = empty, and a broader semantic scan finds no near-duplicate/renamed stand-in for any computed variable (e.g., no second "Total bill", "Total energy cost", or tier-cost variable exists anywhere as a root INPUT that could be silently substituted in place of a computed sibling). No Lineage Disconnection/Context Substitution pattern is present — every downstream operation consumes the actual `resultId` of the correct upstream step (verified argument-by-argument above), not a look-alike.
- *Multi-consumed variable set* = {i_3, o_9, o_10}. Each is reused for legitimate, non-overlapping structural reasons inherent to tiered-rate billing rather than duplicate aggregation:
  - `i_3` (monthly usage) feeds three `min`/`subtract` operations that carve the single usage value into three mutually exclusive tier bands (tier1, tier2-only, tier3-only) — a standard fan-out, not a double-count, since each resulting band is summed into the final total exactly once.
  - `o_9` (cumulative usage through tier 1) is used once to compute tier-1 cost (`op_6`) and once as the subtrahend to derive the tier-2-only band (`op_3`). These are non-competing uses that both terminate through disjoint cost variables (`o_14` and `o_15`) which are combined only once in `op_9`.
  - `o_10` (cumulative usage through tier 2) is used analogously to derive tier-2-only (`op_3`) and tier-3-only (`op_4`) bands — again disjoint downstream costs, not duplicated inputs to the same aggregation.
  - Deduplicated sum check: 50.00 + 70.00 + 32.40 = 152.40 = `S_dedup` for energy cost, matching `S_reported` (`o_17`) exactly; plus service charge 12.50 gives 164.90, matching `o_19` exactly. No divergence between deduplicated and reported consolidation.
- *Root variable set*: all nine roots (`i_1`–`i_8`, `i_18`) are consumed somewhere in the graph; none are orphaned, and none duplicate the name/role/units of a computed sibling (e.g., `i_2` "Zero (tier floor)" has no computed counterpart it could be masking, `i_18` "Monthly service charge" is a genuine external boundary input with no computed twin).

**Conclusion:** Every mandatory contributor (tier-1 cost, tier-2 cost, tier-3 cost, and service charge) reaches the terminal `o_19` via exactly one causal path, all intermediate values replay correctly under full forward propagation from true root inputs, and the reported arithmetic (164.90) is internally consistent with the deduplicated computation. This is a clean, well-formed tiered-billing pipeline with no evidence of the described attack family.