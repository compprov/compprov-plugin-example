# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 92.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud (Double Counting)** — Score: 92 — CONFIRMED: a repeated-argument double count in the foundational `addBulk` operation inflates the sample sum and corrupts every downstream statistic.
- **Precision and Scale Tampering** — Score: 8 — Clean: all operations replay correctly against the single, consistently-applied MathContext (precision 16, HALF_EVEN); small-magnitude deltas observed (e.g., in o_12) are explained entirely by legitimate propagation of a previously-rounded operand, not by convention switching or scale manipulation.
- **Semantic Type and Context Cast** — Score: 5 — Clean: units (mL / mL²), naming, and business meaning (sample → deviation → squared deviation → sum of squares → variance → std dev) remain consistent end-to-end with no unexplained relabeling.

#### Anomaly Localization (Topological & Provenance Fraud — Double Counting)
- **op_1** (`addBulk`, resultId `o_8`, "Sum of samples (mL)") consumes `i_2` ("[0] Sample value (mL)" = 12.5) **twice** — once as argument `a` and again as argument `b5` — alongside the other five distinct samples `i_3, i_4, i_5, i_6, i_7`.
- This yields `o_8 = 92.6`, versus the mathematically correct sum of the six *distinct* measurements, which is `80.1` (12.5+15.2+11.8+14.1+13.6+12.9).
- `o_8` feeds **op_2** (`divide` by `i_9 = 6`) producing the corrupted mean `o_10 = 15.43333333333333`, instead of the true mean `13.35`.
- Every subsequent node derives from this corrupted mean: deviations `o_11, o_13, o_15, o_17, o_19, o_21`; squared deviations `o_12, o_14, o_16, o_18, o_20, o_22`; sum of squared deviations `o_23`; sample variance `o_25`; and the terminal result, sample standard deviation `o_26 = 2.585214368932162`.
- Recomputing the entire pipeline correctly (sum=80.1, mean=13.35, Σ(dev²)=7.375, variance=1.475, std dev≈1.2145) shows the reported terminal output `o_26` is roughly **double** the true value — a massive, material distortion of the final statistical result.

#### Details (Topological & Provenance Fraud — Double Counting)
The repeated-argument pattern `addBulk(a=i_2, ..., b5=i_2, ...)` is structurally identical to a legitimate "doubled value" operation (e.g., `multiply(x,x)` as a power, or an intentional ×2 adjustment), which is why local, step-by-step replay of `op_1` passes perfectly — the wrapper faithfully sums whatever arguments it is given, and the declared MathContext is correctly applied at every step downstream. This is precisely what allows the fraud to evade naive schema/replay validation: each operation is locally correct, and the corrupted mean propagates through a chain of otherwise textbook statistics computations (deviation, square, sum, divide-by-(n-1), sqrt) that are all individually sound.

However, per the Double Counting invariant, a repeated argument is only legitimate when the multiplier is explicitly stated by the result's name/meta and matches other graph evidence (e.g., "doubled", "×2", a stated duplicate measurement). Here, `o_8` is named plainly "Sum of samples (mL)" with no meta indicating any sample should be weighted twice, and the sample-count variable `i_9` used later for the mean division is `6` — matching the count of *distinct* sample inputs (`i_2..i_7`), not 7. This mismatch (7 additions divided by a count of 6) is the structural signature of disguised double counting: the first replicate measurement is silently given double weight in the mean and in every subsequent deviation/variance calculation.

**Consequence:** The reported mean (15.43 mL) is overstated by ~2.08 mL (~15.6%) relative to the true mean (13.35 mL), and the reported standard deviation (2.585 mL) is roughly double the true value (≈1.21 mL). In a titration/QC context, this could mask or fabricate out-of-spec variability, directly affecting downstream acceptance criteria, control-limit decisions, or regulatory reporting that depend on these replicate statistics.

#### Anomaly Localization (Precision and Scale Tampering)
No violation found. All operations consistently use `i_1` (precision=16, HALF_EVEN) as the MathContext argument. Spot-checked recomputation (e.g., `o_12 = o_11 * o_11`) shows deltas on the order of 1e-14, fully attributable to squaring an already-rounded 15-16 significant-digit intermediate (`o_11`) rather than the full repeating-decimal value — expected, non-cyclic rounding propagation, not evidence of a directional or coarsened rounding mode.

#### Details (Precision and Scale Tampering)
HAlF_EVEN at precision 16 is applied uniformly across every `add`, `divide`, `multiply`, and `sqrt` operation; no directional rounding modes (DOWN/UP/FLOOR/CEILING) or coarser contexts appear anywhere in the graph, so there is no basis for a salami-slicing or precision-degradation finding here.

#### Anomaly Localization (Semantic Type and Context Cast)
No violation found. Units (mL for linear quantities, mL² for squared-deviation/variance quantities) and descriptor names are applied consistently and correctly through every transformation (sample → deviation → squared deviation → sum of squares → variance → standard deviation), with the dimensional arithmetic (mL − mL = mL; mL × mL = mL²; mL² / dimensionless = mL²; sqrt(mL²) = mL) matching the operations performed at each step.

#### Details (Semantic Type and Context Cast)
No node exhibits a C_source ≠ C_target mismatch; all metadata present is self-consistent with the standard descriptive-statistics formula being executed, aside from the numerical corruption already flagged under the Double Counting finding above.