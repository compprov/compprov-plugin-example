# Summary
- **Verdict**: CLEAN
- **Risk score**: 3.0

## Precision & Scale Tampering Audit

### Anomaly Localization (If Detected)
No anomalies detected. A full recomputation of every operation in the pipeline (op_1 through op_9) was performed using exact rational arithmetic and cross-checked against the declared `MathContext` (i_1: precision=16, HALF_EVEN) and standard BigDecimal scale-propagation rules (add/subtract preserve max operand scale; multiply sums operand scales).

| Op | Formula | Inputs | Exact Result | Reported Result | Δ |
|----|---------|--------|---------------|------------------|---|
| op_1 | addBulk | 18500.00+9200.00+6300.00+4100.00 | 38100.00 | 38100.00 (o_6) | 0 |
| op_2 | multiply | 180 × 55.00 | 9900.00 | 9900.00 (o_9) | 0 |
| op_3 | multiply | 60 × 75.00 | 4500.00 | 4500.00 (o_12) | 0 |
| op_4 | add | 9900.00+4500.00 | 14400.00 | 14400.00 (o_13) | 0 |
| op_5 | add | 38100.00+14400.00 | 52500.00 | 52500.00 (o_14) | 0 |
| op_6 | multiply | 52500.00×0.10 | 5250.0000 | 5250.0000 (o_16) | 0 |
| op_7 | add | 52500.00+5250.0000 | 57750.0000 | 57750.0000 (o_17) | 0 |
| op_8 | multiply | 57750.0000×0.15 | 8662.500000 | 8662.500000 (o_19) | 0 |
| op_9 | add | 57750.0000+8662.500000 | 66412.500000 | 66412.500000 (o_20) | 0 |

Every reported value exactly matches the arbitrary-precision arithmetic result — Δ = 0 across the entire lineage, from raw material/labor inputs through overhead and profit-margin markup to the final bid price (o_20).

### Details
- **MathContext usage**: All operations correctly thread the same `i_1` MathContext (precision 16, HALF_EVEN) as an explicit `mc` argument. None of the intermediate magnitudes in this graph (max 9 significant digits) approach the 16-digit precision ceiling, so the MathContext never actually needs to round anything — it is present but inert for this specific set of values. This is consistent with correct wrapper usage, not evidence of tampering.
- **Scale propagation**: The growing decimal scale seen in the outputs (`.00` → `.0000` → `.000000`) is the expected, correct behavior of BigDecimal `add`/`multiply` semantics (add preserves the larger operand scale, multiply sums operand scales). This is not precision loss; it is precision *gain* being faithfully carried forward with no truncation at any step. There is no premature scale reduction, no downcast to `float`/`double`/integer, and no intermediate rounding step that discards significant digits before an aggregation.
- **Reused variables (o_14, o_17)**: Both are consumed by two downstream operations each (o_14 by op_6 and op_7; o_17 by op_8 and op_9). Tracing both consumption paths confirms identical, unmodified values are used in each case — no divergent copies or silently rescaled duplicates feeding the two consumers.
- **Salami-slicing check**: No operation population here is scalable with transaction/user volume — it is a fixed, one-time bid computation with a bounded set of cost categories. Additionally, no delta exists to accumulate toward any beneficiary, since every step reconciles exactly.
- **Conservation check**: Materials (38100.00) + Labor (14400.00) = Direct Cost (52500.00); Direct Cost + Overhead (5250.0000) = Cost incl. Overhead (57750.0000); Cost incl. Overhead + Profit Margin (8662.500000) = Total Bid (66412.500000). Full asset conservation holds at every step with zero residual leakage.

**Conclusion**: This graph shows no evidence of precision/scale tampering, rounding-mode abuse, salami slicing, or premature truncation. All arithmetic is exact and fully traceable end-to-end. Risk is assessed as minimal, reflecting only the baseline uncertainty inherent in auditing any single execution trace.