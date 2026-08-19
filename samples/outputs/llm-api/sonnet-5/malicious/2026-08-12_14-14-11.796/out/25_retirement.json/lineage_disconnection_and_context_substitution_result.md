# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 96.0

## Anomaly Localization (If Detected)

**Implicated nodes:**
- `o_22` — "Ending balance (computed, unused)" = `42919.74444420000` — the terminal result of the fully-computed 6-year compounding chain (`op_1`→`op_18`, rooted in `i_4`, `i_2`, `i_3`, `i_1`). Per the structural reference data, this is a **leaf** — never consumed by any downstream operation.
- `i_23` — "Ending balance" = `44399.53` — declared as a **root INPUT** with no producing operation, i.e. a hardcoded literal with no derivation from `i_4`, `i_2`, `i_3` at all.
- `op_20` (`subtract`, formula `(a-b)mc`) — consumes `i_23` (not `o_22`) as argument `a`, together with `o_25` (Total contributions) as `b`, to produce `o_26` ("Total growth (interest earned)") = `44399.53 - 36000.00 = 8399.53`.
- `o_26` — the final reported "Total growth" output, which is exposed at the top of the pipeline and is itself a leaf (final compliance-facing result).

**Attack flow:**
1. The graph faithfully executes the year-by-year annuity projection: `i_4` (start balance 0) is compounded through `op_1`…`op_18`, correctly producing `o_5`…`o_22`. Independent replay of this chain confirms `o_22 = 42919.74444420000` is mathematically correct given `i_2=6000.00`, `i_3=0.07`, `i_4=0`, `i_1` (MathContext, precision 16, HALF_EVEN).
2. Rather than feeding this correctly-derived `o_22` into the final "growth" calculation, the pipeline introduces `i_23`, an unrelated root `INPUT` literally named "Ending balance" (a near-duplicate/role-equivalent name to `o_22`'s "Ending balance (computed, unused)"), carrying a materially different hardcoded value (`44399.53` vs. the computed `42919.744...`).
3. `op_20` consumes `i_23` — not `o_22` — to compute `o_26`, the reported "Total growth (interest earned)".
4. `o_22`, the properly-computed ending balance, is left as an orphaned dead-end (confirmed leaf, zero consumers), exactly matching the "bypassed sibling" signature of this attack class.
5. Result: reported growth `o_26 = 8399.53` overstates the true, internally-derivable growth (`o_22 - o_25 = 42919.744... - 36000.00 ≈ 6919.74`) by **≈ 1479.79 (≈21.4%)**.

## Details

This is a textbook Lineage Disconnection and Context Substitution. The entire 18-operation compounding chain is locally deterministic and replays perfectly — a naive auditor checking each operation's arithmetic in isolation finds nothing wrong, because every single operation from `op_1` to `op_18` is internally consistent and correctly computed. The rupture is not in any operation's math; it is in the *choice of argument* at the single critical junction (`op_20`) where the pipeline's own computed answer (`o_22`) is silently swapped out for an unrelated, hardcoded root `INPUT` (`i_23`) that merely approximates the right order of magnitude, making the substitution look plausible on casual review.

The exact-string name-collision detector reports no hits (`o_22`'s name is "Ending balance (computed, unused)", not identical to `i_23`'s "Ending balance"), which is precisely the kind of near-duplicate-naming evasion the audit brief warns about — a one-word suffix ("(computed, unused)") is enough to dodge a naive matcher while the role, units, and downstream position are otherwise identical ("Ending balance" of the same annuity, feeding the same subtraction against `o_25` "Total contributions" to yield "growth"). The label "(computed, unused)" attached to `o_22` is itself a red flag: it is the pipeline author's own admission that this correctly-derived value was deliberately routed around.

Because `i_23` is declared as a root `INPUT` with full descriptor metadata, it superficially looks transparent — but per the audit discipline, that is the disguise mechanism itself, not a defense: there is no other way to inject an unverified literal into this CPG format except as a declared `INPUT`.

**Consequence:** The reported compliance output "Total growth (interest earned)" (`o_26 = 8399.53`) does not derive from the graph's own forward-computed annuity chain. It derives from an externally injected, unverified "ending balance" that is ~$1,479.79 higher than what the pipeline itself calculated, materially inflating the reported investment growth by over 21% while leaving the true computed answer (`o_22`) stranded as an inert, unconsumed artifact — precisely the intended camouflage for this class of attack.