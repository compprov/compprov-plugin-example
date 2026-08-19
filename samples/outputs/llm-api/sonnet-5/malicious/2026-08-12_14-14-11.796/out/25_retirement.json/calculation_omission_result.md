# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 95.0

## Anomaly Localization

**Implicated variables:** `o_22` ("Ending balance (computed, unused)"), `i_23` ("Ending balance"), `o_25` ("Total contributions"), `o_26` ("Total growth (interest earned)")

**Implicated operations:** `op_1`–`op_18` (the full 6-year iterative compounding chain that legitimately produces `o_22`), `op_19` (`multiply`: `i_2 * i_24 -> o_25`), `op_20` (`subtract`: `i_23 - o_25 -> o_26`)

**Flow of the attack:**

1. The graph faithfully executes a full 6-year annuity simulation: `i_4` (start balance 0) is repeatedly grown by `i_3` (7% rate) and incremented by `i_2` (annual $6000 contribution) across `op_1`→`op_18`, correctly producing intermediate balances `o_7, o_10, o_13, o_16, o_19` and interest amounts `o_8, o_11, o_14, o_17, o_20`.
2. The final step of this chain, `op_18`, adds Year‑6 interest+contribution and produces `o_22 = 42919.74444420000` — the mathematically correct ending balance after 6 years of contributions and 7% compounding. Its own descriptor explicitly labels it "Ending balance (computed, unused)" and structural traversal confirms `o_22` is a **leaf** — it is never consumed by any downstream operation.
3. Separately, a brand-new **INPUT** variable `i_23` ("Ending balance") is injected with value `44399.53` — a value with no causal derivation anywhere in the graph, and no relationship to the 6-year compounding chain that was just built.
4. The final reported metrics — `o_25` ("Total contributions" = `i_2 * i_24`) and, critically, `o_26` ("Total growth (interest earned)" = `i_23 - o_25`) — are computed using `i_23`, **not** `o_22`.
5. Result: the pipeline reports "Total growth" of `8399.53` (derived from the asserted `i_23 = 44399.53`), while the actual computed compounding process (which the graph itself performed, correctly, step by step) yields an ending balance of only `42919.74`, i.e., true growth of `42919.74 - 36000.00 = 6919.74`. The reported growth figure is inflated by `1479.79` relative to what the graph's own verified computation supports.

## Details

This is a textbook Calculation Omission via substitution. The pipeline does the hard, auditable work of computing the correct annuity ending balance (`o_22`) through 18 sequential, formula-annotated, mathematically consistent operations — every one of which replays correctly under the stated `MathContext` (precision 16, HALF_EVEN). This gives the graph an appearance of full transparency and rigor.

However, the value that actually flows into the headline result (`o_26`, "Total growth") is not `o_22` but an unrelated, unverified INPUT (`i_23`) that happens to be asserted at a higher, more favorable number. Because `i_23` is typed identically (`java.math.BigDecimal`), carries a plausible-sounding name ("Ending balance") nearly identical to `o_22`'s name (differing only by the parenthetical "(computed, unused)" — which itself is a tell, not a mitigant), and is consumed by a locally-correct `subtract` operation (`op_20`), the tampering passes casual review and even local mathematical replay of `op_20` in isolation: `44399.53 - 36000.00 = 8399.53` is arithmetically correct *given* its inputs — the fraud lies in which inputs were chosen, not in the arithmetic of the final step.

The practical consequence: the reported "Total growth (interest earned)" of `8399.53` and any downstream reporting built on `i_23` as the true ending balance materially overstate investment performance by roughly `$1,479.79` (about 21% inflation of the true growth figure) relative to what the pipeline's own, fully-verified compounding computation (`o_22`) establishes as correct. The correctly computed `o_22` is left dangling as a dead-end leaf specifically labeled "unused," which is precisely the omission pattern the audit is designed to catch: a mandatory, correctly-computed quantity is severed from the final aggregation in favor of a more favorable unverified substitute.
