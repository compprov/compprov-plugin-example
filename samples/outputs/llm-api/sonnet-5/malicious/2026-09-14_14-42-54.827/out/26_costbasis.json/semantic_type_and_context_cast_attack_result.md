# Summary
- **Verdict**: CLEAN
- **Risk score**: 18.0

## Anomaly Localization (If Detected)
No variable was found where a technically type-safe operation silently re-mapped a business context in violation of its originating metadata (i.e., no confirmed instance of `C_source != C_target` backed by an explicit contradiction). All `BigDecimal` -> `BigDecimal` transitions were traced end-to-end:

- `i_3`("Lot 1") --setScale(mc=i_2)--> `o_4`("Lot 1 shares") --multiply(i_5, mc=i_1)--> `o_6`("Lot 1 cost")
- `i_7`("Lot 2") --setScale--> `o_8`("Lot 2 shares") --multiply(i_9)--> `o_10`("Lot 2 cost")
- `i_11`("Lot 3") --setScale--> `o_12`("Lot 3 shares") --multiply(i_13)--> `o_14`("Lot 3 cost")
- `o_4,o_8,o_12` --addBulk--> `o_15`("Total shares held"); `o_6,o_10,o_14` --addBulk--> `o_16`("Total cost")
- `o_16 / o_15` --divide--> `o_17`("Weighted-average cost per share")
- `i_18`("Shares sold") x `i_19`("Sale price") --multiply--> `o_20`("Sale proceeds")
- `i_18` x `o_17` --multiply--> `o_21`("Cost basis of shares sold")
- `o_20 - o_21` --subtract--> `o_22`("Realized gain/loss")

Every numeric result was independently recomputed and matches exactly (including MathContext precision/rounding semantics: `i_2`=precision 2/DOWN truncation on all three lot-share setScale ops, `i_1`=precision 16/HALF_EVEN on all money-denominated ops, confirmed against the 16-significant-digit outputs `o_17`, `o_21`, `o_22`). No hidden identity/wrapper operation, orphaned relabeling, or metadata-stripping step was located.

One item merits flagging for domain-expert (non-technical) review rather than as a confirmed violation: `o_21` ("Cost basis of shares sold") is derived from `i_18` ("Shares sold", 60 units) multiplied by `o_17`, a *blended* weighted-average cost per share computed across all three lots — rather than from any single lot's individually-tracked cost (`o_6`, `o_10`, `o_14`). Because the graph otherwise models discrete, individually priced "Lots" (suggesting a specific-identification/FIFO tax-lot recordkeeping convention), silently basing the sale's cost basis on an averaged metric instead of a specific lot has real downstream tax/reporting consequences (average-cost vs. specific-ID methods are not interchangeable under most tax regimes) and is the *category* of semantic drift this audit targets.

## Details
This pattern falls short of a confirmed Semantic Type/Context Cast attack under the stated invariants because the transformation is fully explicit and auditable: `o_17` is transparently computed via a labeled `divide` operation over clearly-named `o_16`/`o_15` totals, and is consumed under its own declared name ("Weighted-average cost per share") in `op_11`, with no relabeling, no metadata suppression, and no contradicting domain tag anywhere in the graph (e.g., no `taxStatus`/`method` field declaring "specific-identification required" that this computation violates). The invariant explicitly requires an *explicit contradiction* between originating and consuming definitions to qualify as a violation — here the average-cost computation is named consistently at every hop, so a validation pass checking business meaning (not just type) would still find internally-coherent labels.

Consequence if this were intentional/undisclosed: reported "Realized gain/loss" (`o_22` = 5918.62839678916) would differ from a FIFO/specific-lot calculation (which would price the 60 sold shares against Lot 1's $210.50 and part of Lot 2's $340.75 cost, yielding a materially different cost basis and gain figure), meaning downstream tax/regulatory reporting could be understated or overstated depending on lot cost dispersion — the mechanism described in the attack definition (unchanged types/math, shifted business meaning). Because no explicit contradicting metadata exists in this trace to prove intent versus a legitimate elected average-cost method, this is reported as a moderate-confidence observation for human/domain-expert resolution rather than a confirmed exploit.