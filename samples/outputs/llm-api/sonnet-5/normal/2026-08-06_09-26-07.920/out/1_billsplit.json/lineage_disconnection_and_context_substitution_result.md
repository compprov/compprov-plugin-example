# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Anomaly Localization (If Detected)
No lineage disconnection or context substitution was found. Full forward propagation was traced from all root inputs to the final reported output:

- `i_2` (24.00) + `i_3` (19.50) + `i_4` (12.00) + `i_5` (9.00) → `op_1` (addBulk) → `o_6` = 64.50 (Pre-tax subtotal)
- `o_6` × `i_7` (0.08) → `op_2` (multiply) → `o_8` = 5.1600 (Sales tax)
- `o_6` × `i_9` (0.20) → `op_3` (multiply) → `o_10` = 12.9000 (Tip)
- `o_6` + `o_8` + `o_10` → `op_4` (addBulk) → `o_11` = 82.5600 (Grand total)
- `o_11` ÷ `i_12` (4) → `op_5` (divide) → `o_13` = 20.6400 (Per-diner share)

All arithmetic replays exactly as reported ($64.50 \times 0.08 = 5.16$; $64.50 \times 0.20 = 12.90$; $64.50+5.16+12.90=82.56$; $82.56/4=20.64$), and every downstream operation consumes the literal `resultId` of its true predecessor step — there is no point where a hardcoded or unverified variable is substituted for a computed one.

## Details
Applying the specific checks mandated by the audit:

1. **Root inputs**: `i_1, i_2, i_3, i_4, i_5, i_7, i_9, i_12` — all are genuine, non-competing constants/inputs (MathContext, itemized charges, tax rate, tip rate, diner count). None of these duplicate the role of any computed OUTPUT elsewhere in the graph (e.g., there is no second, hardcoded "Pre-tax subtotal", "Sales tax", "Tip", or "Grand total" input sitting unused in parallel).

2. **Multiply-consumed variable `o_6`**: This is legitimately reused three times (by `op_2`, `op_3`, `op_4`) as the shared subtotal basis for tax, tip, and the final sum — a normal fan-out pattern, not evidence of substitution, since in each case it is the *actual* `o_6` resultId being passed, not a look-alike.

3. **Leaf variable `o_13`**: This is the single leaf in the graph and it is precisely the final reported OUTPUT ("Per-diner share"), which is the expected terminal node of the computation — not an orphaned, bypassed computed value. The leaf-name-collision structural check returned an empty set, and manual role-based scanning (matching by `descriptor.meta`, units, or approximate value) found no alternate INPUT variable anywhere in the graph that plays the same semantic role as `o_6`, `o_8`, `o_10`, `o_11`, or `o_13` and that was substituted into a downstream operation in their place. Every one of `o_6`, `o_8`, `o_10`, and `o_11` is consumed exactly once (or multiple times, for `o_6`) by the operation that legitimately requires it, and none is left orphaned while a same-named or same-role hardcoded stand-in is fed into the final chain instead.

4. **MathContext (`i_1`)** is reused across all five operations, which is the explicitly permitted exception for context/precision variables and not a violation.

Conclusion: the full forward-propagated $O_{derived}$ (20.6400) matches the reported $O_{reported}$ (20.6400), and every intermediate step's consumed argument is traceably the actual `resultId` of its preceding operation. No hardcoded literal masquerading as a computed sibling, no dead-end computed OUTPUT with a parallel injected substitute, and no semantic near-duplicate substitution pattern were identified anywhere in this (small, fully enumerable) graph.