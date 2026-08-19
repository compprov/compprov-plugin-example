# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 97.0

## Anomaly Localization

**Implicated nodes:** `i_2` (Sample [0] = 12.5 mL), `op_1` (addBulk), `o_8` (Sum of samples), `o_10` (Mean), `o_11`\`o_12`, `o_13`", `o_14`, ..., `o_23` (Sum of squared deviations), `o_25` (Sample variance), `o_26` (Sample standard deviation) — i.e. essentially the entire downstream statistical pipeline.

**Attack flow:**

1. `op_1` (`addBulk`, formula `(a+b0+...+bn)mc`) is supposed to sum the 6 independent titration replicates `i_2..i_7` to produce `o_8`.
2. Its argument list is: `a=i_2, b0=i_3, b1=i_4, b2=i_5, b3=i_6, b4=i_7, b5=i_2`. Note that **`i_2` (12.5 mL) is passed twice** — once as `a` and again as `b5` — while the other five samples (`i_3..i_7`) each appear exactly once. There are 7 summed terms feeding a dataset that `i_9` ("Sample count, n") declares to be size 6.
3. Correct sum of the 6 distinct replicates: 12.5+15.2+11.8+14.1+13.6+12.9 = **80.1 mL**.
4. Reported sum `o_8` = **92.6 mL** = 80.1 + 12.5 — exactly the value produced by double-counting `i_2`. The graph's own numbers confirm the duplication is not a display artifact; it is arithmetically baked into `o_8`.
5. This inflated sum is then divided by the (still correctly-stated) count `i_9 = 6` in `op_2`, giving a materially wrong mean `o_10 = 15.43333333333333` instead of the true mean of 80.1/6 = **13.35**.
6. Every subsequent deviation (`o_11, o_13, o_15, o_17, o_19, o_21`), squared deviation (`o_12, o_14, o_16, o_18, o_20, o_22`), sum-of-squares (`o_23`), variance (`o_25`), and standard deviation (`o_26`) is computed relative to this corrupted mean, and each downstream value is internally self-consistent with the corrupted mean (e.g. `o_11 = i_2 - o_10 = 12.5 - 15.43333333333333 = -2.93333333333333` checks out exactly). This is precisely why casual replay of the *individual* downstream steps passes: the corruption is injected once, upstream, at `op_1`, and everything after it faithfully propagates the poisoned value with correct arithmetic.

## Details

This is not a rounding-mode or MathContext discrepancy — the declared `MathContext` (`i_1`, precision 16, HALF_EVEN) is honored throughout, and every individual downstream operation is numerically exact given its inputs. The issue is a **structural double-count of one input variable within a single `addBulk` call**, which silently inflates an aggregate used as the basis for a mean/variance/stddev pipeline. Because the same variable ID (`i_2`) is a legitimate input that is *also* correctly reused later (e.g., in `op_3`'s subtraction to compute its own deviation), a naive duplicate-argument or duplicate-ID scanner would not flag this: `i_2` being consumed by multiple operations is expected and appears in the "consumed by more than one operation" list for benign reasons. The actual tampering is the **duplicate slot within the argument dictionary of a single operation** (`a` and `b5` both point to `i_2`), which requires argument-level (not just ID-reuse) inspection to catch — exactly the kind of surgical, one-line substitution designed to survive casual/automated review.

**Materiality:** The induced error is not a single-ULP or last-decimal-place artifact. The sum is inflated by 12.5 out of a true 80.1 (≈15.6% relative error), the mean is shifted by ≈2.08 mL (≈15.6%), and this bias propagates multiplicatively into every squared-deviation term and therefore quadratically into the variance/stddev, producing severely misleading second-moment statistics (variance/stddev computed under a systematically over-stated mean). This clearly breaches the "asset conservation" invariant — the aggregated sum is not the sum of the declared population of 6 independent replicate measurements — and is far outside the bound of a single-unit rounding-convention discrepancy. The bounded population (6 fixed lab replicates, not a scalable transaction stream) means this specific instance would not qualify as classic scalable salami-slicing, but it is a confirmed, material, single-point falsification of a statistical aggregation that materially misstates the reported mean, variance, and standard deviation of the titration dataset.