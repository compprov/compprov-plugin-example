# Summary
- **Verdict**: CLEAN
- **Confidence score**: 78.0

## Anomaly Localization (If Detected)
No variable ID or operation ID was found where the declared business context of a value changes while its technical type is preserved across a consuming operation. Tracing each of the three checkpoint sub-pipelines end-to-end:

- **Checkpoint 1**: i_3 (r) × i_4 (t1, checkpoint=1) → o_7 ("r×t1") → Exp → o_8 ("e^(r×t1)") × i_2 (N0) → o_9 ("Population at t1", checkpoint=1)
- **Checkpoint 2**: i_3 (r) × i_5 (t2, checkpoint=2) → o_10 → Exp → o_11 × i_2 (N0) → o_12 (checkpoint=2)
- **Checkpoint 3**: i_3 (r) × i_6 (t3, checkpoint=3) → o_13 → Exp → o_14 × i_2 (N0) → o_15 (checkpoint=3)

Each checkpoint's input time variable (`i_4`/`i_5`/`i_6`) is wired into the correct exponent-computation operation, and the resulting population output carries the matching `checkpoint` meta tag back to its source time input (1→1, 2→2, 3→3). No cross-checkpoint mixing, relabeling, or metadata suppression was found on this reuse path.

## Details
The graph implements a straightforward continuous exponential growth model N(t) = N0·e^(r·t), evaluated at three time checkpoints. All arithmetic replays exactly:
- r·t1 = 0.03×5 = 0.15 → e^0.15 = 1.161834242728283 → ×1,000,000 = 1,161,834.242728283 ✓
- r·t2 = 0.03×10 = 0.30 → e^0.30 = 1.3498588075760032 → ×1,000,000 = 1,349,858.807576003 ✓
- r·t3 = 0.03×20 = 0.60 → e^0.60 = 1.8221188003905089 → ×1,000,000 = 1,822,118.800390509 ✓

Regarding the specific attack vector under audit (semantic type/context casting): the reused variables flagged by the structural reference data (`i_2` = N0, `i_3` = r) are consumed identically in each of the three branches — always as "initial population" and "growth rate" respectively, feeding the same well-defined formula shape (`multiply` → `Exp` → `multiply`) with no divergence in units, business meaning, or domain framing (cells, per-hour rate, hours) between the three checkpoints. There is no `domainType`/`units`/`taxStatus` metadata anywhere in the graph that gets silently stripped or remapped, no identity/wrapper operation used to smuggle a variable into a different business context, and no case where a raw/unadjusted quantity is consumed downstream as though it were already adjusted (e.g., no Gross→Net or currency-basis shift hidden behind a type-preserving multiply). The `checkpoint` meta tags on inputs and their corresponding population outputs are internally consistent throughout.

One caveat limiting certainty: the domain metadata in this graph is very sparse (most `meta` arrays are empty, and only checkpoint tags are populated), which reduces the amount of explicit semantic signal available to cross-check against. This means a very subtle mislabeling that doesn't touch the `checkpoint` tag (e.g., swapping the *displayed* meaning of an intermediate exponent without an accompanying meta field) would be harder to catch purely from metadata — but no such indicator was found in the variable names, argument wiring, or numeric replay. Given the full mathematical and structural consistency and absence of any detected metadata suppression or mislabeling, this graph does not exhibit the defined Semantic Type and Context Cast attack signature, though the confidence is tempered by the limited metadata surface available for cross-validation.