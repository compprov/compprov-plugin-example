# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 85.0

## Anomaly Localization

**Implicated nodes:** `i_1` (payment, 3.5 ETH in wei), `i_2` (1 ETH in wei), `op_1` (divide), `o_5` ("Payment"), `op_4` (multiply), `o_8` ("Payment, rescaled to wei"), `op_6` (subtract), `o_10` ("Raw excess payment above threshold"), `op_7` (multiply), `o_11` ("Excess payment above threshold, bonus-eligible").

**Flow of the defect:**
1. `op_1` computes `o_5 = i_1 / i_2` using `BigInteger` (integer, truncating) division: `3500000000000000000 / 1000000000000000000`. The mathematically exact result is `3.5`; `BigInteger.divide` truncates to `3`. The fractional `0.5 ether` (`500000000000000000` wei) is silently discarded at this single step.
2. `o_5 = 3` is then used for the gate check (`op_2`/`op_3`), which is benign here since flooring for a `>= threshold` gate is conservative.
3. Critically, `op_4` "rescales" the truncated `o_5` back into wei: `o_8 = o_5 * i_2 = 3 * 1e18 = 3000000000000000000`. This is presented as "Payment, rescaled to wei" — but it no longer represents the actual payment (`3.5e18` wei); it represents only the floor(payment/ether)*ether, permanently discarding the `0.5e18` wei fraction.
4. `op_6` subtracts the (already-corrupted) `o_8` from the threshold-in-wei `o_9` to get "raw excess" `o_10 = 3e18 - 1e18 = 2e18`.
5. `op_7` multiplies by the gate multiplier to produce the final bonus-eligible excess `o_11 = 2e18` wei (2.0 ETH).

**The mathematically correct bonus-eligible excess should be `i_1 - o_9 = 3.5e18 - 1e18 = 2.5e18` wei (2.5 ETH).** The graph reports `2.0e18` wei — a shortfall of exactly `0.5e18` wei (0.5 ETH), which is ~14% of the original payment and ~20% of the true excess. This wei amount is never re-added, refunded, or accounted for anywhere else in the graph; it simply vanishes from the trace between `i_1` and `o_11`.

## Details

This is not an isolated single-ULP rounding artifact. The EXPECTED_INVARIANTS note that a mismatched rounding convention (truncate vs. round) can differ from an alternative by at most 1 unit *at the target scale* — but here the "target scale" chosen by the pipeline itself (whole ether, via unscaled `BigInteger` division of two 18-decimal wei quantities) is coarser than the source asset's native precision (wei). Converting `wei -> whole-ether (truncating) -> wei` is a classic lossy unit round-trip: it does not preserve the source asset's native precision as required, and the loss (up to just under 1 full ether per transaction) is proportional to the truncated remainder, not bounded to a negligible rounding unit.

Why casual/local checks pass: each individual operation is locally "correct" for its declared (truncating) semantics — `BigInteger.divide` truncates by definition, `multiply` and `subtract` are exact — so no single operation looks anomalous in isolation. The vulnerability only becomes visible when tracing the full lineage from `i_1` to `o_11` and comparing against the true, unrescaled input value; a check that only validates each operation's local arithmetic (rather than end-to-end conservation of the original payment amount) would miss this.

**Consequences:** In a token-sale bonus/allocation context, `o_11` ("excess payment above threshold, bonus-eligible") directly determines how much bonus is issued to a purchaser. Any buyer who pays a non-integer multiple of 1 ether has the fractional remainder truncated away before the excess/bonus is computed, permanently losing bonus credit for that fraction — with no compensating output anywhere in the graph. Because every purchase in an active token sale is subject to this same division (`payment / etherUnit`), the flaw is structurally scalable across the entire population of buyers and payment amounts, not a one-off bounded artifact confined to this single trace — satisfying the "scalable population + consistent directional bias" criterion for a salami-slicing-style value leak, with the shortfall implicitly accruing to the contract/issuer rather than the paying buyer.

Given the materiality (0.5 ETH lost from a 3.5 ETH payment in this single trace) and the systemic, per-transaction, scalable nature of the flaw, this should be treated as a high-severity precision/scale tampering finding warranting remediation (e.g., using `BigDecimal`/rational arithmetic to track and carry forward the fractional wei remainder, or performing the excess calculation directly in wei without any ether-unit rounding round-trip).