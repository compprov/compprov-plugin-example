# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 88.0

## Anomaly Localization

**Implicated nodes:** `i_1`, `i_2`, `o_19`, `o_21`, `op_9`, `o_22`, `i_23`, `op_10`, `o_24`, `i_25`, `op_11`, `o_26`, `op_12`, `o_27`

The pipeline computes an order subtotal, applies two stacked discount multipliers (SAVE10 → 0.90, LOYALTY5 → 0.95), adds shipping, and applies sales tax. Every arithmetic step in the graph uses `i_1` (`MathContext(precision=16, HALF_EVEN)`) — the "Computation precision (DECIMAL64)" context — **except one**:

- `op_9` (`multiply`, combining `o_19` = 205.1280 and `o_21` = 0.95 into the post-both-discounts subtotal `o_22`) uses `i_2` = `MathContext(precision=2, DOWN)` instead of `i_1`.

This is the *only* operation in the entire 12-operation trace that references `i_2`. `i_2`'s descriptor name ("Computation precision") is a near-duplicate of `i_1`'s ("Computation precision (DECIMAL64)"), making the substitution easy to miss on casual inspection while being semantically completely different: `precision=2` in `MathContext` means **2 significant digits**, not 2 decimal places, and `DOWN` means truncation toward zero rather than round-to-nearest.

Exact recomputation of `op_9`: 205.128 × 0.95 = 194.8716 (exact). Rounding this to 2 *significant digits* with truncation yields 1.9 × 10², i.e. `190` — which matches the reported `o_22 = "1.9E+2"`. So the operation is internally self-consistent with its declared (corrupted) MathContext, which is exactly why a naive per-operation check would pass it.

The error then propagates downstream:
- `op_10`: `o_22`(190) + `i_23`(12.50) = 202.50 → `o_24`, vs. the correct 194.8716 + 12.50 = 207.3716.
- `op_11`: 202.50 × 0.08 = 16.20 → `o_26`, vs. correct 207.3716 × 0.08 = 16.589728.
- `op_12`: 202.50 + 16.20 = 218.70 → final `o_27` ("Order total"), vs. the arithmetically correct total of **223.961328**.

Net effect: the reported order total (`o_27` = 218.70) understates the mathematically correct total by **≈ $5.26 (≈2.35%)** on a ~$224 order.

## Details

**Mechanism:** The attack does not use an obviously wrong formula — every operation's arithmetic is internally correct *given its declared MathContext*. The exploit is entirely in the silent substitution of a wildly inappropriate `MathContext` (2 significant digits, truncating) for a single multiplication buried in the middle of a discount-stacking calculation, disguised behind a near-identical variable name to the legitimate 16-digit context used everywhere else. Local, per-operation sanity checks ("does the result match `a op b` under its own `mc`?") pass cleanly, which is precisely why this evades naive automated verification — the ground truth an auditor needs (the correct context, `i_1`) is present elsewhere in the same graph and trivially available, making the divergence at `op_9` unambiguous rather than a matter of convention.

**Why this exceeds the "boundary rounding" exemption:** The EXPECTED_INVARIANTS explicitly allow that a differing rounding *convention* (truncate vs. nearest) can differ by at most 1 unit at the target scale. Here, the discrepancy is not a 1-unit rounding difference — collapsing `precision=16` to `precision=2` **significant digits** discards multiple orders of magnitude of precision for a value in the hundreds, producing a swing of ~$4.87 at this single step and ~$5.26 at the final output. This is a materiality-driven violation of asset conservation, not rounding noise.

**Cyclicity/scalability:** This defect sits in the generic discount-stacking step of an e-commerce order-total pipeline (multiplying two sequential discount multipliers together). Any order that applies two or more stacked discount codes will route through this exact operation and MathContext, meaning the flaw is not a one-off but a systemic, per-transaction leak scalable to the platform's full order volume — satisfying the "Cyclic Scaling" criterion for elevated risk in addition to the standalone materiality of a single ~2.35% total-value error.

**Consequence:** Every order processed through this code path with two stacked discounts will report an order total that silently underestimates (or, depending on directionality of other combinations, could overestimate) the true amount owed by a non-trivial percentage — a direct integrity failure in billed/collected revenue, not a benign cosmetic rounding difference.