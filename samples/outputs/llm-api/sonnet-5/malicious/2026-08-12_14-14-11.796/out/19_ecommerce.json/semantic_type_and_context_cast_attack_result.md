# Summary
- **Verdict**: SEMANTIC CAST DETECTED
- **Confidence score**: 81.0

## Anomaly Localization

**Implicated nodes:** `o_23` ("Taxable amount (computed, unused)"), `i_24` ("Tax rate (8%, California)"), `o_25` ("Sales tax (computed, unused)"), `i_26` ("Order total"), and the operations `op_10` (add: o_21+i_22→o_23) and `op_11` (multiply: o_23*i_24→o_25).

**Flow of the anomaly:**
1. The pipeline legitimately derives Subtotal (`o_15`=227.92) → SAVE10-adjusted (`o_18`=205.1280) → LOYALTY5-adjusted (`o_21`=194.871600). This chain is mathematically clean and fully traceable.
2. `op_10` adds shipping (`i_22`=12.50) to `o_21`, producing `o_23`, which is explicitly *named* "Taxable amount (computed, unused)".
3. `op_11` multiplies `o_23` by the CA tax rate (`i_24`=0.08), producing `o_25`, explicitly *named* "Sales tax (computed, unused)".
4. Despite both `o_23` and `o_25` being labeled "unused" in their own descriptor metadata, no further operation exists in the graph. Critically, **`i_26` ("Order total" = 223.96) is declared as a root `INPUT` variable** — it has no producing operation and is also a leaf (never consumed downstream). It is structurally isolated from the entire computation chain.
5. Numerically, `o_23` (207.371600) + `o_25` (16.58972800) = 223.961328 ≈ 223.96 — i.e., `i_26` matches almost exactly the sum of the two nodes that are explicitly labeled as "unused" and are never actually wired into any output via a modeled `add` operation.

## Details

This is a textbook instance of the target attack vector: technical type continuity (`BigDecimal` throughout) and local mathematical replay of every *modeled* operation succeed perfectly, while the semantic/business narrative embedded in the metadata is deliberately misleading and the final declared result is decoupled from provenance.

Two compounding issues create the exploit:

- **Metadata mislabeling (context suppression):** `o_23` and `o_25` are explicitly annotated as "computed, unused" in `descriptor.name`. This metadata tells any reviewer — human or automated — that these values are inert/vestigial and can be safely ignored. Yet their numeric values are precisely what is required to reconstruct the reported "Order total" to the cent. The label is a semantic lie: it re-casts functionally load-bearing tax/taxable-amount computations as dead-end artifacts, exactly the kind of implicit re-labeling/metadata suppression the invariants prohibit.
- **Ungrounded final result (missing transformation node):** Even granting that the tax chain is meaningful, there is no explicit `add` (or equivalent) operation connecting `o_23`/`o_25` to `i_26`. Instead, "Order total" is injected as a raw `INPUT` with no upstream operation at all. This means the number a customer/auditor sees as the authoritative final total is **not derived by any auditable computation in this graph** — it is asserted. An adversary could substitute any value for `i_26` (identical `valueClass`, identical `kind=INPUT` schema) and no mathematical-replay check would ever flag it, because replay only verifies operations that exist, and none touches `i_26`.

**Consequence:** The graph creates a convincing illusion that Order Total = Subtotal − discounts + shipping + tax, backed by seemingly complete arithmetic, while in fact the regulatory-sensitive tax computation is formally disconnected and disclaimed ("unused"), and the actual reported total is an unverified, independently-supplied number that merely happens to match. This is precisely the gap the Semantic Type and Context Cast attack exploits: schema/type validation and per-operation replay pass cleanly, but the declared business context ("this is the computed, tax-inclusive order total") is not backed by any explicit, auditable transformation, and the metadata actively (and falsely) disclaims the very computation that appears to justify the number. A financial control that trusts type-safety and local replay checks would sign off on this trace despite the final figure being provenance-free.