# Summary
- **Verdict**: CLEAN
- **Confidence score**: 92.0

#### Anomaly Localization (If Detected)
No anomaly localized. All variables and operations were traced end-to-end:

- `op_1` (subtract): `i_1` (10000) - `i_2` (3000) = 7000 → stored in `o_4`. Exact match, no discrepancy.
- `op_2` (multiply): `o_4` (7000) * `i_3` (2) = 14000 → stored in `o_5`. Exact match, no discrepancy.

Both results are bit-for-bit exact matches with rational/arbitrary-precision recomputation. No delta (Δ = 0) exists at either step.

#### Details
This graph uses `java.math.BigInteger` throughout for all variables and `WrappedBigInteger` for both operations. BigInteger arithmetic is exact integer arithmetic with no concept of scale, rounding mode, or fractional truncation — subtraction and multiplication over integers cannot introduce sub-unit residue, salami-slicing leakage, or rounding-mode ambiguity, since there is no MathContext, no decimal scale, and no unit conversion anywhere in the pipeline. There is only a single unit (raw token count) used consistently across both operations, with no mixing of decimal precisions (e.g., no WEI/USDC-style conversion).

Both operations were independently recomputed with exact arithmetic and reproduce the reported values exactly. The structural reference data also confirms no duplicate consumption, no orphaned/duplicated naming collisions, and a clean root-to-leaf flow: `i_1, i_2 → op_1 → o_4`, then `o_4, i_3 → op_2 → o_5`, terminating at the single leaf `o_5`.

Since the attack vector under audit (Precision and Scale Tampering — scale truncation, non-standard rounding modes, unit mixing, or float/double downcasting) requires either a fractional/decimal representation or a multi-unit conversion to manifest, and this graph contains neither (pure integer arithmetic, single unit, no MathContext, no downcasting), there is no viable vector for the described tampering pattern to have occurred here. No evidence of asset non-conservation, rounding bias, or unit-scale mismatch was found.

Overall assessment: the graph is internally consistent, arithmetically exact, and structurally clean with respect to the specific attack vector requested for this audit.