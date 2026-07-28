# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 88.0

## Anomaly Localization

**Implicated nodes:** `op_6` (addBulk), `o_9`, `o_10`, `o_11`, `o_12`, `o_13` (its declared arguments), and `o_14` (the reported result, "Assets sum").

`op_6` is the sole aggregation step in this pipeline. Its `arguments` dictionary explicitly lists exactly five components — `a=o_9`, `b0=o_10`, `b1=o_11`, `b2=o_12`, `b3=o_13` — and its `resultId` is `o_14`. Recomputing the formula the operation itself declares (`a+b0+...+bn`) from the five component values actually stored in the graph:

```
o_9  (BTC->USD)            146948.10
o_10 (ETH Binance->USD)     48624.45
o_11 (USDC Binance->USD)      538.22
o_12 (ETH Staked->USD)      12312.36
o_13 (USDC Morpho->USD)    223326.04
----------------------------------------
SUM                        431749.17
```

yields **431,749.17**. The graph reports `o_14 = 441,749.17` — a difference of **exactly $10,000.00**.

This is not attributable to rounding: even recomputing every conversion from the raw, unrounded rate/amount pairs (e.g. 2.13×68989.72, 23.34×2083.31, 532.9×1.01, 5.91×2083.31, 221114.9×1.01) and summing the full-precision results gives ≈431,749.20 — still roughly $10,000 short of the reported figure, and nowhere near it. There is no variable anywhere in the graph — rate, balance, or intermediate output — whose value is close to $10,000 that could legitimately explain this gap as a sixth, uncounted asset, fee, or correction. The $10,000 simply appears in the final reported figure with no upstream causal path, no supporting variable, and no operation that produced it.

## Details

**Mechanism:** The `addBulk` operation at `op_6` is fully and correctly wired to its five stated inputs — a naive replay check that only verifies "does op_6 have valid argument references and a resultId" would pass, since every referenced ID exists and resolves to a plausible `Amount`. The attack surfaces only when the *arithmetic* of the operation is independently replayed against its own declared arguments: the stored result silently diverges from what those arguments actually sum to. This is precisely the kind of tampering designed to survive casual review and even naive automated graph-structure checks (no dangling/leaf adjustment variable is left behind, no duplicate ID or name trips a mechanical scan) while still corrupting the reported bottom line.

**Why this matters under the omission-invariant lens:** The pipeline reports `o_14` ("Assets sum") as though it were the complete, transparent aggregation of the five converted asset legs shown in the graph. In fact, the number reported is $10,000 higher than what those five legs — the only components the operation claims to have consumed — actually produce. Either (a) a real $10,000 component was folded into the final figure without ever being represented, computed, or causally linked as a variable in the trace (an undisclosed, non-auditable adjustment baked directly into the output), or (b) the operation's true computation was substituted post-hoc. In both readings, the provenance graph fails its core promise: that the reported result can be fully reconstructed and verified from the disclosed inputs and operations. A $10,000 uplift with zero supporting lineage is exactly the kind of silent, favorable-to-the-reporter distortion the omission-detection invariant is designed to catch, even though here the distortion manifests as an untraceable addition to the aggregate rather than a visibly-excluded argument.

**Consequence:** Any downstream consumer of this NAV/Assets-sum figure (e.g., a fund's reported net asset value) would be misled by a $10,000 overstatement that cannot be explained, justified, or reconciled from anything else present in the trace — a material and directly actionable integrity failure.

*Secondary, lower-severity observations (not the primary finding):* several individual conversions (`o_11`, `o_13`) are rounded down rather than to the nearest cent (e.g. 538.229→538.22 instead of 538.23; 223326.049→223326.04 instead of 223326.05), while others (`o_10`, `o_12`) round normally. This inconsistency in rounding policy is minor and could reflect a benign implementation quirk (e.g., `RoundingMode.DOWN` applied inconsistently), but is noted for completeness since it further erodes confidence in the arithmetic hygiene of this pipeline.