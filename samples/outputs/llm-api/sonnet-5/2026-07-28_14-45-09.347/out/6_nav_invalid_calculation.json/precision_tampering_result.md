# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 97.0

## Anomaly Localization

**Primary finding — material aggregation mismatch (asset conservation violation):**

- **Operation:** `op_6` (`addBulk`, formula `a+b0+...+bn`)
- **Arguments:** `a=o_9 (146948.10)`, `b0=o_10 (48624.45)`, `b1=o_11 (538.22)`, `b2=o_12 (12312.36)`, `b3=o_13 (223326.04)`
- **Result:** `o_14 = 441749.17` ("Assets sum")

Recomputing the sum of the five declared operands exactly:

```
146948.10 + 48624.45 + 538.22 + 12312.36 + 223326.04 = 431749.17
```

The recomputed exact sum is **431749.17**, but the graph's stored output for `o_14` is **441749.17** — a discrepancy of exactly **$10,000.00**. Re-deriving the five conversion outputs from raw inputs (rate × amount, full precision) and summing them before any rounding gives ≈431749.1991, which still rounds to 431749.20 — confirming the $10,000 gap is not attributable to any rounding convention, MathContext difference, or intermediate truncation. It is a flat, unexplained inflation of the reported total NAV asset sum.

This is far beyond the "single rounding-convention, ≤1-unit-at-target-scale" ceiling defined in the invariants (10,000 vs. an allowed maximum of 0.01 for a cent-scale operation) — it is a direct violation of asset conservation at a materiality level (~2.3% of total assets) that cannot be dismissed as noise.

**Secondary observation — directional rounding bias in the `convert` operations (contextual, not independently conclusive):**

- `op_2` (`i_5`,`i_2` → `o_10`): exact product = 48624.4554 → correct nearest-cent rounding = 48624.46; reported = 48624.45 (truncated down).
- `op_3` (`i_6`,`i_3` → `o_11`): exact product = 538.229 → correct nearest-cent rounding = 538.23; reported = 538.22 (truncated down).
- `op_5` (`i_8`,`i_3` → `o_13`): exact product = 223326.049 → correct nearest-cent rounding = 223326.05; reported = 223326.04 (truncated down).
- `op_1` and `op_4` happened to round down under both truncation and correct rounding, so they show no signal either way.

Every instance where the fractional remainder crossed the 0.5-cent threshold was resolved by rounding down rather than to nearest — a directionally consistent bias. However, per the audit invariants this occurs across only a small, structurally bounded population (one conversion per balance line in a fixed portfolio composition, not a volume-scalable transaction stream), and the individual deltas (≤$0.01 each) do not accumulate into, or explain, the $10,000 gap found in `op_6`. This is flagged as contextual evidence of lax/aggressive rounding discipline, not as an independent salami-slicing finding.

## Details

The decisive issue is the `addBulk` operation `op_6`: its declared arguments sum to 431749.17, yet its `resultId` (`o_14`) stores 441749.17. Because CPG replay tools typically verify that each operation's *inputs* trace back correctly and that *individual* conversions are locally self-consistent, a casual or purely structural review (checking IDs, argument wiring, valueClass consistency) would pass this graph — the argument wiring is fully valid and every referenced variable exists and is correctly typed. The tampering is purely in the *stored numeric payload* of the final output node, which silently diverges from what the upstream operation's own declared formula and arguments produce. This is precisely the class of attack that survives naive/local replay: the falsified node doesn't need a corrupted argument list, only a mismatched value at the terminal leaf, where no downstream consumer exists to re-flag the inconsistency (note `o_14` is the graph's only leaf/output-with-no-consumer node per the structural reference data).

**Consequence:** The reported Net Asset Value ("Assets sum") overstates total USD assets by exactly $10,000 relative to the sum of its own audited, itemized components. In a NAV/fund-accounting context this is a direct and material integrity violation — investors or downstream systems consuming `o_14` would receive an inflated valuation that cannot be reconciled against the underlying, correctly-computed per-asset conversions recorded in the very same graph. The secondary truncation bias across the four asset conversions further indicates a general pattern of favoring downward/rounded-down values elsewhere in the pipeline, which, while not independently material, is consistent with an environment where precision handling is being deliberately manipulated rather than merely careless.