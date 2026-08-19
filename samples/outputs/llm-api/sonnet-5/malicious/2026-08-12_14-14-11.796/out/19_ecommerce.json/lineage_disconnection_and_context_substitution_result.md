# Summary
- **Verdict**: LINEAGE BREAK DETECTED
- **Confidence score**: 93.0

## Anomaly Localization

**Implicated nodes:** `i_22`, `i_24`, `o_21`, `o_23`, `i_24`, `op_10`, `op_11`, `o_25`, `i_26`.

**Traced computation chain (fully replays correctly):**
- `op_1`-`op_4`: unit price × quantity → line totals `o_5`, `o_8`, `o_11`, `o_14`
- `op_5` (addBulk): sums line totals → `o_15` = 227.92 (Subtotal)
- `op_6`/`op_7`: SAVE10 discount → `o_17`=0.90, `o_18`=205.1280
- `op_8`/`op_9`: LOYALTY5 discount → `o_20`=0.95, `o_21`=194.871600
- `op_10`: `o_21` + `i_22` (shipping 12.50) → `o_23` = 207.371600, explicitly labeled *"Taxable amount (computed, unused)"*
- `op_11`: `o_23` × `i_24` (tax rate 0.08) → `o_25` = 16.58972800, explicitly labeled *"Sales tax (computed, unused)"*

At this point the graph's own arithmetic has produced a fully-derived, discount-adjusted, shipping-inclusive, tax-inclusive total: `o_23 + o_25` = 207.371600 + 16.589728 = **223.961328** — the mathematically correct "Order total" for this order.

**The rupture:** There is no `add` (or any) operation in the entire `operations` array that consumes both `o_23` and `o_25` to synthesize a final order total. Instead, a separate node `i_26`, declared as a root `INPUT` (no producing operation, per the Root Variable ID set) and named **"Order total"**, is injected with a hardcoded literal value of `223.96` — a value that is suspiciously close to (a rounded version of) the graph's own correctly-derived `223.961328`, but which is structurally and causally disconnected from the computation chain entirely. `i_26` is never consumed downstream either (confirmed leaf), meaning it terminates the pipeline as an isolated, unverified constant.

Meanwhile, the two properly-computed downstream results, `o_23` and `o_25`, are both dead-ended: `o_23` feeds only into `op_11` (producing `o_25`), and `o_25` itself has zero consumers (confirmed leaf). Both are explicitly annotated in their own `descriptor.name` field as **"(computed, unused)"** — a rare case where the tampering is almost self-documented in the metadata, rather than merely inferable from graph topology.

## Details

This is a textbook Lineage Disconnection and Context Substitution: a legitimate, fully deterministic sequence of operations (line-item pricing → subtotal → two chained percentage discounts → shipping addition → tax multiplication) is executed and locally replays perfectly. However, the quantity that *should* be the final compliance output — the order total — is never actually assembled from `o_23` and `o_25` via any operation. Instead a foreign, hardcoded value is substituted in under the label "Order total" and declared as a root `INPUT`, which is precisely the disguise the attack vector predicts: since a CPG has no other mechanism to inject an unverified literal, packaging it as a declared `INPUT` node lets it pass casual review as "just an input," while carrying the exact semantic role of a final derived output.

The close numeric match (223.96 vs. the true derived 223.961328) is not reassuring — it is the specific technique that lets the substitution survive casual/automated checks: a wildly different number would be caught immediately, whereas a rounded, plausible-looking approximation slips through unless someone actually re-derives the value from the root inputs through the full operation chain, as required by $Origin\_Propagation\_Valid$.

The fact that the two legitimately-computed candidates for this exact role (`o_23`, `o_25`) are both explicitly marked "(computed, unused)" and left with no downstream consumers is the necessary structural signature of this attack, not evidence of innocuous dead code: it proves the real computation was performed and then deliberately bypassed in favor of the unverified constant `i_26`.

**Impact:** Any consumer of this CPG trusting `i_26` ("Order total" = 223.96) as the audited, provenance-backed final result is actually trusting an unmonitored hardcoded literal that bears no operational connection to the order's actual priced/discounted/shipped/taxed computation chain — a discrepancy or manipulation introduced at this single node would be completely invisible to line-item-level review, since every upstream operation checks out cleanly.