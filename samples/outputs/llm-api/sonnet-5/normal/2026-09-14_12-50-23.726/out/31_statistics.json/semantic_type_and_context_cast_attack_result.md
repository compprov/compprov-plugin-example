# Summary
- **Verdict**: CLEAN
- **Risk score**: 12.0

## Anomaly Localization (If Detected)
No confirmed Semantic Type and Context Cast violation was found. One structural weak point worth flagging for human review:

- **i_24** (`Degrees of freedom, n - 1`, value `5`) is declared as a **root INPUT** rather than being derived via an explicit `subtract` operation from **i_9** (`Sample count, n`, value `6`). It is consumed only in **op_16** (`divide`, `o_23 / i_24 -> o_25`, labeled `Sample variance`).

All other variable-to-operation chains were traced end-to-end:
- i_2..i_7 (`[k] Sample value (mL)`) -> op_1 `addBulk` -> o_8 (`Sum of samples (mL)`) -> op_2 `divide` by i_9 -> o_10 (`Mean (mL)`)
- o_10 paired correctly, index-by-index, with each original sample (i_2->o_11, i_3->o_13, i_4->o_15, i_5->o_17, i_6->o_19, i_7->o_21) to produce `[k] Deviation from mean (mL)`, each squared into `[k] Squared deviation (mL²)` via self-multiplication (op_4, op_6, op_8, op_10, op_12, op_14).
- All six squared deviations feed op_15 `addBulk` -> o_23 (`Sum of squared deviations (mL²)`) -> op_16 `divide` by i_24 -> o_25 (`Sample variance (mL²)`) -> op_17 `sqrt` -> o_26 (`Sample standard deviation (mL)`).

Units (mL -> mL² -> mL) and labels (deviation, squared deviation, sum of squared deviations, sample variance, sample standard deviation) are internally consistent at every hop, and all index-tagged intermediate values ([0]..[5]) map to the correct originating sample throughout the graph. No variable is relabeled, no `descriptor`/unit is silently dropped or swapped mid-flow, and no MathContext substitution occurs (a single `i_1` DECIMAL64-equivalent context is used identically in every operation).

## Details
The dataset represents a standard descriptive-statistics pipeline (mean, deviations, sum of squares, Bessel-corrected sample variance, standard deviation). Full manual replay of the arithmetic (sum = 80.1, mean = 13.35, per-sample deviations, squared deviations, sum-of-squares = 7.375, variance = 1.475, stdev ≈ 1.2145) matches every stored `value` exactly, and every `descriptor.name` for each hop is semantically appropriate for the operation performed (e.g., "Sample variance" is correctly computed by dividing by degrees of freedom n-1 rather than n, which is itself correctly labeled and value-consistent with n=6).

The one notable structural gap is that `i_24` ("Degrees of freedom, n - 1") is injected as an independent root INPUT rather than being computed from `i_9` ("Sample count, n") via an explicit, auditable `subtract` node. Per the stated invariant that domain-linked transformations must be backed by explicit transformation logic, this is a latent weakness: nothing in the graph structurally guarantees that the degrees-of-freedom value stays synchronized with the sample count if either were altered independently in a future run — an attacker (or a data-entry error) could supply a mismatched dof value without any structural check catching it. In this specific instance, however, the numeric values are mutually consistent (6 and 5) and the semantic label of `i_24` is honest and unambiguous — there is no observed case of a value being consumed under a conflicting business definition (no Gross/Net swap, no unit reinterpretation, no relabeled multiplier/discount-style cast). Therefore this is reported as a moderate provenance-hygiene observation rather than a confirmed Semantic Type and Context Cast attack, warranting a low-to-moderate risk score rather than a high one.