# Summary
- **Verdict**: ANOMALY DETECTED
- **Confidence score**: 98.0

## Anomaly Localization

**Implicated nodes:**
- Input variable `i_2` — "Computation precision (tampered)" — `MathContext{precision=2, roundingMode=DOWN}`
- Input variable `i_1` — "Computation precision (DECIMAL64)" — `MathContext{precision=16, roundingMode=HALF_EVEN}` (the context used everywhere else in the pipeline)
- Operation `op_7` (`multiply`, formula `(a*b)mc`) — arguments `a=o_13`, `b=i_4`, **`mc=i_2`** — `resultId=o_15`
- Downstream propagation: `o_15` → `op_8` (→`o_16`) → `op_9` (→`o_17`) → `op_10`(`o_19`) → `op_11`(`o_20`) → `op_12`(`o_21`) → ... → all Month 4–6 figures (`o_19`–`o_30`) → `op_20` (`addBulk` → `o_32`, "Total interest paid")

**Attack flow:**
Every interest/principal-split operation in this amortization schedule (`op_1`, `op_4`, `op_7`, `op_10`, `op_14`, `op_17`) is structurally identical — `multiply(balance, rate, mc)` — and in every single case except one, `mc` is bound to `i_1` (the declared 16-digit HALF_EVEN computation context). `op_7`, computing "Interest accrued [Month 3]" (`o_15`), silently swaps in `i_2`, a 2-significant-digit, `DOWN` (truncating) context.

Recomputing `op_7` exactly: `o_13 (237915.84000000) * i_4 (0.004) = 951.66336000000...` exactly. Under the pipeline's own declared context `i_1` (precision 16, HALF_EVEN), this value requires no rounding at all (only 14 significant digits) and would be reported as `951.66336000000`. Instead, under `i_2` (precision 2, DOWN), `951.66336...` is truncated to its first two significant digits toward zero, yielding exactly `9.5E+2` (=950) — which is precisely the value stored in `o_15`. This is not a coincidental 1-ULP rounding artifact; it is a deterministic reproduction of the tampered context's output, proving `op_7` genuinely executed against `i_2` rather than `i_1`.

The resulting $1.66336 discrepancy is then baked into `o_16` (`i_3 - o_15`, i.e. Principal portion Month 3, inflated by the same amount) and into `o_17` (`o_13 - o_16 = o_13 - i_3 + o_15`, Balance after payment Month 3, deflated by the same amount). Because every subsequent month's interest, principal, and balance figure (`o_19`–`o_30`) is chained off `o_17`, the corruption propagates through the remainder of the schedule, and it is directly folded into the headline aggregate `o_32` ("Total interest paid"), which sums `o_7, o_11, o_15, o_19, o_24, o_28` via `op_20` (again using the *correct* `mc=i_1`, masking the fact that one of its addends was already corrupted upstream).

## Details

**Mechanism:** The attack does not tamper with a reported number directly (which a naive value-diff check might catch); instead it substitutes the *context object* governing rounding/precision for exactly one operation among a series of otherwise identical, correctly-parameterized operations. Because `multiply`'s formula annotation and wrapper class are unchanged, and the substituted argument (`i_2`) is a syntactically valid, properly-typed `MathContext` input variable, the operation replays perfectly under naive mathematical replay — the output is *exactly* what `multiply(a, b, mc)` produces given the supplied `mc`. Only a check that the correct/declared `MathContext` (`i_1`) was used, rather than merely checking arithmetic self-consistency, exposes the substitution.

**Why this passes casual review:** All six "balance/interest/principal" operations use the same formula label (`(a*b)mc`) and wrapper class; only the buried `mc` argument (`i_2` vs `i_1`) differs for one operation in twenty-four. The corrupted result (`9.5E+2`) is even internally consistent with everything computed from it (`o_16`, `o_17`, etc., all correctly derive from `o_15`), so pure 