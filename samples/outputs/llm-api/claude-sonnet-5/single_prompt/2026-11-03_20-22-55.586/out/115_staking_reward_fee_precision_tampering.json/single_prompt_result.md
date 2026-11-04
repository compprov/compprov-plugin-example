# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 85.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud** — Score: 5/100. No omission, substitution, or double counting detected; all three root variables (i_1, i_2, i_3) are consumed exactly once along a single causal chain terminating at the sole leaf o_5, consistent with the structural reference data (no multi-use variables, no repeated-argument operations, no duplicate-name leaves).
2. **Precision and Scale Tampering** — Score: 85/100. A premature-division pattern truncates the fraction of the reward before it is multiplied by the fee percentage, producing a materially wrong terminal result.
3. **Semantic Type and Context Cast** — Score: 5/100. All `valueClass` types are consistently `java.math.BigInteger`; variable names accurately describe the operations performed (e.g., "Reward / denominator" genuinely holds `i_1/i_3`), with no relabeling or metadata stripping that would constitute a business-context cast.

---

#### Anomaly Localization (Precision and Scale Tampering)
- **Variables**: `i_1` (Accumulated staking reward = 199), `i_2` (Developer fee percent = 10), `i_3` (Percent denominator = 100), `o_4` (Reward / denominator = 1), `o_5` (Developer reward (fee) = 10, terminal leaf).
- **Operations**: `op_1` divide(i_1, i_3) → o_4; `op_2` multiply(o_4, i_2) → o_5.
- **Flow**: The computation evaluates the fee as `(i_1 / i_3) * i_2` instead of `(i_1 * i_2) / i_3`. Because all values are `BigInteger` (integer, truncating division), the division is performed *before* the multiplication, destroying the fractional information that the subsequent multiplication would have preserved and converted into fee units.

#### Details (Precision and Scale Tampering)
- **Exact correct order**: `(199 * 10) / 100 = 1990 / 100 = 19` (BigInteger truncation applied once, at the very end, which is the mathematically sound point to truncate).
- **Reported/actual order**: `floor(199 / 100) * 10 = 1 * 10 = 10`.
- **Δ = |19 − 10| = 9**, a **~47% relative error** on the developer fee itself, and **~4.5% of the entire reward pool (199)** — far beyond the ≤1-unit ceiling expected from a mere rounding-mode disagreement. This is not a boundary rounding artifact; it is a structural ordering flaw that discards the entire fractional remainder (`0.99` of the quotient) before it can be scaled back up by the multiplication.
- **Why local checks pass**: Each operation replays exactly against its own inputs — `op_1` correctly computes `199/100=1` under BigInteger semantics, and `op_2` correctly computes `1*10=10`. Both steps are locally 