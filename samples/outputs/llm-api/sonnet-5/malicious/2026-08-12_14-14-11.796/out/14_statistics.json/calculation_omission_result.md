# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 74.0

## Anomaly Localization

**Implicated nodes:** `i_2` (Sample [0], 12.5 mL), `i_3`–`i_7` (Samples [1]-[5]), `op_1` (addBulk → `o_8`), `i_9` (Sample count, n=6), `o_8` (Sum of samples), `o_10` (Mean), `o_11`–`o_22` (all per-sample deviations/squared deviations), `i_24` (n-1), `o_23` (Sum of squared deviations), `o_25` (Sample variance), `o_26` (Sample standard deviation, final reported output).

**Attack flow:**

1. `op_1` (`addBulk`, formula `(a+b0+...+bn)mc`) is supposed to sum the 6 independently measured samples `i_2`(12.5), `i_3`(15.2), `i_4`(11.8), `i_5`(14.1), `i_6`(13.6), `i_7`(12.9) exactly once each — consistent with `i_9` = "Sample count, n" = 6 and `i_24` = "Degrees of freedom, n-1" = 5, both of which explicitly assert a population of 6 distinct measurements.
2. However, `op_1`'s argument map is: `a=i_2, b0=i_3, b1=i_4, b2=i_5, b3=i_6, b4=i_7, b5=i_2`. This is **7 data arguments for a 6-sample dataset**, and the 7th slot (`b5`) silently re-references `i_2` instead of a distinct value. Sample [0] (12.5 mL) is therefore counted twice in the aggregation.
3. This produces `o_8` = 92.6, which is numerically self-consistent with its own (tampered) argument list (12.5+15.2+11.8+14.1+13.6+12.9+12.5 = 92.6) — so a naive "recompute this one operation from its own arguments" replay check passes cleanly.
4. `o_8` is then divided by `i_9` = 6 (the *correct, unmodified* sample count) in `op_2`, producing an inflated mean `o_10` = 15.4333... mL instead of the true mean of 80.1/6 = 13.35 mL.
5. Every downstream computation — the six deviations (`o_11,o_13,o_15,o_17,o_19,o_21`), their squares (`o_12,o_14,o_16,o_18,o_20,o_22`), their sum (`o_23`), the variance (`o_25`, correctly using Bessel's correction `i_24`=5), and finally the reported standard deviation (`o_26` = 2.5852...) — are all derived from the tainted mean, so the final reported statistic is systematically biased away from what a correct computation over the 6 declared samples would produce.

## Details

The mechanism that lets this survive casual review is subtle: the duplicate is not a new, suspicious variable injected into the graph (which would show up under "unconsumed variable" or "duplicate name" heuristics) — it is a **second reference to an already-legitimate input variable, added as an extra named argument (`b5`) within the very same `addBulk` operation that already legitimately uses `i_2` (as `a`)**. Structural exact-match checks such as "variables consumed by more than one operation" do flag `i_2`, but that is expected and unremarkable on its face because `i_2` is *also* legitimately reused later in `op_3` to compute its own deviation — the double-use *inside a single operation's argument list* is invisible to that kind of operation-level counting and requires inspecting the semantic intent of the operation (an addBulk over exactly n=6 declared samples) against its actual argument cardinality (7 arguments referencing only 6 distinct IDs).

The pipeline's own metadata is internally consistent about there being exactly 6 measurements: 6 "[i] Sample value" variables, `i_9`="6", and `i_24`="5" (n-1). The `addBulk` producing the reported sum, however, consumes 7 terms — one measurement (sample [0]) counted twice — while the divisor used immediately afterward (`i_9`=6) assumes only 6 terms were summed. This mismatch between the numerator's term count and the denominator is never reconciled or cross-checked anywhere in the graph; the true, correct aggregate (80.1 mL, true mean 13.35 mL) is never computed or exposed as a variable anywhere — only the inflated substitute is, and it is what silently propagates through every downstream operation to the final reported sample standard deviation.

Consequence: the reported mean is inflated by roughly +2.08 mL (~15.6%), and the reported standard deviation is derived entirely from this corrupted basis, giving a materially misleading titration replicate statistic while appearing, under any per-operation local replay, to be perfectly self-consistent.