# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

## Precision and Scale Tampering Audit

### Anomaly Localization (If Detected)
No confirmed precision/scale tampering was identified. All arithmetic operations were independently recomputed using exact rational arithmetic and compared against the declared `MathContext` (precision=16, HALF_EVEN) supplied to every operation (`i_1`).

Step-by-step verification:
- `op_1` (subtract): 400 − 398 = 2 → matches `o_6` = 2
- `op_2` (multiply): 2 × 120.00 = 240.00 → matches `o_7` = 240.00
- `op_3` (subtract): 48000.00 − 240.00 = 47760.00 → matches `o_8` = 47760.00
- `op_4` (multiply): 47760.00 × 0.07 = 3343.2000 → matches `o_10` = 3343.2000 (scale = 2+2 = 4, standard BigDecimal multiply behavior)
- `op_5` (add): 47760.00 + 3343.2000 = 51103.2000 → matches `o_11` = 51103.2000
- `op_6` (multiply): 47760.00 × 0.02 = 955.2000 → matches `o_13` = 955.2000
- `op_7` (subtract): 51103.2000 − 955.2000 = 50148.0000 → matches `o_14` = 50148.0000

Every reported value is the exact rational result; the declared precision (16 significant digits) vastly exceeds the number of significant digits actually produced by any of these operations (max 8 digits), so no rounding event was ever actually triggered by the `MathContext`. Δ = 0 at every step.

The one structurally flagged item — `o_8` ("Adjusted subtotal after three-way match") being consumed by three separate operations (`op_4`, `op_5`, `op_6`) — is legitimate reuse: it correctly serves as the shared base for the tax calculation, the subtotal+tax rollup, and the early-payment discount calculation. No duplicate-name shadow variable or divergent copy of this value exists elsewhere in the graph.

### Details
The pipeline models a standard three-way-match invoice adjustment (quantity variance → adjusted subtotal), followed by tax computed on the adjusted subtotal, and an early-payment discount computed on the adjusted subtotal (excluding tax) — a common and defensible business convention (discount terms typically apply to goods value, not tax). This produces a fully traceable, closed arithmetic chain: 48000.00 − 240.00 (variance) + 3343.20 (tax) − 955.20 (discount) = 50148.00, which reconciles exactly with the final `o_14` leaf output.

No instance of forced scale reduction, mid-pipeline downcasting, non-standard rounding mode substitution, or sub-cent skimming toward an identifiable sink was found. The declared `MathContext` is applied uniformly and consistently across every operation, and because no operation actually required rounding (all results fit well within the 16-digit precision budget), the rounding-mode question (HALF_EVEN vs. any alternative) is moot for this specific execution trace — there is no rounding event whose direction could be biased.

Given the absence of any Δ discrepancy, any truncation, or any repeatable/scalable skimming pattern, this graph shows no material evidence of Precision and Scale Tampering. Risk score is set low but non-zero only to reflect that a single execution trace cannot fully rule out cyclic salami-slicing behavior if this pipeline is invoked at very large volume with different rounding-sensitive inputs — a concern not evidenced within this specific CPG.