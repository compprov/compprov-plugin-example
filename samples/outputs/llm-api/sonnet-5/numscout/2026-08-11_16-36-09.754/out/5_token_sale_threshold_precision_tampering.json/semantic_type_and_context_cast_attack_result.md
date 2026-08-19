# Summary
- **Verdict**: SEMANTIC CAST DETECTED
- **Confidence score**: 87.0

## Anomaly Localization

**Implicated variables:** `i_1` (Payment sent, wei), `i_2` (1 ether in wei), `i_3` (threshold, whole ether), `o_5` ("Payment"), `o_6`, `o_7` (gate variables), `o_8` ("Payment, rescaled to wei"), `o_9`, `o_10` ("Raw excess payment above threshold"), `o_11` ("Excess payment above threshold, bonus‑eligible").

**Implicated operations:** `op_1` (divide, produces `o_5`), `op_4` (multiply, produces `o_8` from `o_5`), `op_6` (subtract, produces `o_10`), `op_7` (multiply, produces final output `o_11`).

**Attack flow:**

1. `op_1`: `o_5 = i_1 / i_2` = 3500000000000000000 / 1000000000000000000 = **3** (BigInteger, floor/truncating division). `o_5`'s true business meaning is *"number of whole ether units paid, floored"* — a value expressly fit only for **threshold-gate comparison** (its immediate consumer, `op_2`, correctly compares it against `i_3`, the *whole-ether* threshold). The 0.5‑ether remainder (500000000000000000 wei) is discarded here and never appears again anywhere in the graph.
2. `o_5` is legitimately consumed once for its designed purpose in `op_2`/`op_3` (gate decision, `o_6`/`o_7`).
3. `op_4` **reuses the same `o_5`** — the structural reference data explicitly flags `o_5` as consumed by more than one operation — in `multiply(o_5, i_2)` to produce `o_8`, relabeled `"Payment, rescaled to wei"`. This name implies fidelity to the original payment, but mathematically it can only ever reconstruct `floor(i_1/i_2) * i_2`, i.e. **3000000000000000000 wei**, not the actual payment of **3500000000000000000 wei** held in `i_1`. No transformation node acknowledges or preserves the discarded remainder.
4. `op_6`: `o_10 = o_8 - o_9 = 3e18 - 1e18 = 2e18`. Because `o_8` was built from the truncated `o_5` rather than from the raw payment `i_1`, this "raw excess" is short by exactly the discarded 0.5 ether.
5. `op_7`: `o_11 = o_10 * o_7 = 2e18 * 1 = 2e18` — the final, leaf output labelled *"Excess payment above threshold (wei, bonus-eligible)"*.

A faithful computation of bonus-eligible excess should have used the untruncated raw input directly (`i_1 - o_9 = 3.5e18 - 1e18 = 2.5e18`). Instead, the pipeline silently substitutes a gate-purpose, truncated integer (`o_5`) for a precision-critical financial quantity, producing a final bonus-eligible excess that is **0.5 ETH (500000000000000000 wei) lower** than the actual amount overpaid above the threshold.

## Details

This is a textbook Semantic Type and Context Cast: `o_5` is technically a `BigInteger`, and every downstream consumer is also `BigInteger`-typed, so type checking and mathematical replay both pass cleanly — `op_4`'s multiply is arithmetically self-consistent and `op_6`/`op_7` correctly propagate whatever value they're given. What breaks is *business* continuity: `o_5` was created (via floor division) exclusively to answer a boolean question ("did the buyer pay ≥ 1 whole ether?"), a context where discarding the fractional remainder is harmless. It is then implicitly re-cast — via a plain `multiply` with no documented rounding/remainder-adjustment step — into a stand-in for the buyer's actual payment amount, a context where the discarded remainder is financially material. The variable's own metadata (`descriptor.meta` is empty for `o_5`) contains no unit/precision annotation flagging it as "floored" or "gate-only", which is precisely the metadata suppression that permits the cast to survive casual and automated review: the label conveniently changes from ambiguous "Payment" to confidence-inspiring "Payment, rescaled to wei" with no explicit transformation node justifying the reinterpretation, in direct violation of the stated invariant that domain reinterpretation requires an explicit, auditable transformation.

**Consequence:** the reported "bonus-eligible excess" (`o_11` = 2.0 ETH) understates the true excess contribution (2.5 ETH) by exactly the truncated remainder from the gate check. In a token-sale bonus/allocation context this directly and silently disadvantages the purchaser (or conversely could be exploited to under-allocate tokens/bonuses) while every individual operation remains locally "correct" and fully replayable — which is exactly the profile of a targeted, single-substitution semantic cast rather than an incidental rounding artifact, given that a numerically-precise alternative (`i_1`) was available and untouched throughout the graph.