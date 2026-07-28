# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 89.0

## Anomaly Localization

**Implicated nodes:**
- `o_20`–`o_25`: per-transaction gas costs converted to USDC (via `op_1`–`op_6`, all correctly derived from root inputs `i_13`–`i_18` and market rates `i_4`–`i_7`).
- `o_26` ("Total gas fees in USDC", value `111.433600`): computed by `op_7` (`addBulk`) as the sum of `o_20..o_25`. Verified arithmetically correct (22.4+17.3844+12.93+17.5032+17.92+23.296 = 111.4336).
- `o_27`–`o_30`: yield conversions (correct, feeding `op_12`).
- `o_31` ("Gross yield in USDC", `1738.305562`): `addBulk(o_27,o_28,i_10,o_29,o_30)` — correctly sums to the stated value. **Note: `o_26` is not an argument here.**
- `o_32` ("Platform fee in USDC", `52.149166`): `scale(o_31, i_19)` = 1738.305562 × 0.03 — correct.
- `o_33` ("Net profit in USDC", `1686.156396`): `op_14` = `subtract(o_31, o_32)` = 1738.305562 − 52.149166 — locally correct arithmetic, and this is the reported final output.

**The break:** `o_26`, the fully and correctly computed "Total gas fees in USDC", is a structural leaf — confirmed by the Structural Reference Data — meaning it is **never consumed by any downstream operation**. The only two operations that produce final compliance outputs (`op_12` building `o_31`, and `op_14` building the reported `o_33`) never reference `o_26` at all. Gas costs are computed transparently, aggregated, labeled, and then silently dropped from the profit-and-loss chain. The final "Net profit" is derived purely from `Gross yield − Platform fee`, never `Gross yield − Gas fees − Platform fee`.

Forward-propagating the graph's own computed quantities to their logical conclusion:

$O_{derived}$ = Gross yield − Total gas fees − Platform fee = 1738.305562 − 111.433600 − 52.149166 = **1574.722796** USDC

$O_{reported}$ (o_33) = **1686.156396** USDC

The discrepancy is exactly 111.433600 — i.e., precisely the value of the orphaned `o_26`. This is not rounding noise; it is the full magnitude of the bypassed computation, silently inflating reported net profit by ~7%.

## Details

This is a textbook Lineage Disconnection: a legitimate, fully-reachable-from-roots intermediate quantity (`o_26`, built from six correctly-executed `convert` operations over real gas amounts and real market rates) is computed with full provenance and then excluded from the terminal calculation that its name and position in the pipeline ("Total gas fees in USDC" sitting directly alongside "Gross yield" and "Platform fee" in the output list) strongly implies it was meant to feed. Instead of being substituted by a same-named hardcoded stand-in (the exact-match name-collision scan correctly found none), the substitution here takes the more subtle form of an **implicit zero**: the subtraction step (`op_14`) simply never references it, functionally equivalent to hardcoding a gas-cost contribution of 0 at the point where a real, computed, non-zero value was available and structurally adjacent.

Why this passes casual/local review:
- Every individual operation (`op_1`–`op_14`) replays deterministically and its output matches its declared arguments exactly — local mathematical validity holds at every single node.
- `o_26` is a well-formed, well-labeled `OUTPUT` node with a plausible-looking value, so it reads as an intentional, informative side-computation ("we show you the gas costs too") rather than a value that was supposed to be load-bearing in the final profit figure.
- The bypass is invisible to naive traversal because there is no illegal edge, no cycle, no type mismatch — the fraud is an *absence* of an edge that should exist between `o_26` and the subtraction step producing `o_33`, not a corrupted edge.

Consequence: the reported "Net profit in USDC" (`o_33` = 1686.156396) overstates the true, fully-cost-accounted profit (1574.722796) by exactly the total gas expenditure of the underlying DeFi positions. Any compliance, tax, or investor-facing report relying on `o_33` as the ground truth net profit is materially misstated, despite every individual arithmetic step in the graph being independently correct and replayable.