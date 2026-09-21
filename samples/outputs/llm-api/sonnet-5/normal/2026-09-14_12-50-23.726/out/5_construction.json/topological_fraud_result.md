# Summary
- **Verdict**: CLEAN
- **Risk score**: 6.0

## Summary
This audit traced every root input (i_1 MathContext, i_2\u2013i_5 materials, i_7/i_8 carpentry, i_10/i_11 electrical, i_15 overhead rate, i_18 profit margin rate) forward through the full operation chain to the sole terminal output `o_20` ("Total bid price"). No Calculation Omission, Double Counting, or Lineage Disconnection was substantiated.

#### Anomaly Localization (If Detected)
No confirmed violation was localized. Two structural reuse points flagged by the reference data were specifically inspected as M>1 candidates:

- **`o_14` ("Direct cost")** is consumed by `op_6` (multiply by `i_15` \u2192 `o_16` Overhead) and by `op_7` (add to `o_16` \u2192 `o_17` Cost including overhead).
- **`o_17` ("Cost including overhead")** is consumed by `op_8` (multiply by `i_18` \u2192 `o_19` Profit margin) and by `op_9` (add to `o_19` \u2192 `o_20` Total bid price).

Expanding the terminal formula: `o_20 = o_17 + o_19 = o_17*(1+i_18) = (o_14 + o_14*i_15)*(1+i_18) = o_14*(1+i_15)*(1+i_18)`. Each root cost/labor entity embedded in `o_14` is multiplied by the overhead and margin factors exactly once \u2014 this is the standard cost-plus-overhead-plus-margin construction bid formula (base is reused once as the multiplicand for its own derived surcharge, then the surcharge is added once), not two independent additions of the same raw cost into the rollup. The `i_18` node's own metadata explicitly documents this as `basis: markup-on-cost-including-overhead`, i.e., an auditable, declared allocation/markup basis \u2014 exactly the documented-split exception the invariants carve out for legitimate reuse.

All root inputs (materials i_2\u2013i_5, labor i_7/i_8/i_10/i_11, overhead rate i_15, margin rate i_18) are consumed exactly once by their respective producing operations, with no unused roots and no root/computed-sibling name collisions. The only leaf variable is `o_20` itself, which is expected (it is the terminal output, not an orphaned dead-end). No other variable in the graph exhibits `M=0` (unconsumed dead-end masquerading as a completed computation).

Full forward propagation was replayed end-to-end and every intermediate value matches the recorded value exactly:
`o_6=38100.00`, `o_9=9900.00`, `o_12=4500.00`, `o_13=14400.00`, `o_14=52500.00`, `o_16=5250.0000`, `o_17=57750.0000`, `o_19=8662.500000`, `o_20=66412.500000` \u2014 all consistent with `Origin_Propagation_Valid`, i.e., the terminal output derives from the graph's own root inputs via its own declared computation chain, with no substituted or hardcoded stand-in values at any junction (no root `INPUT` mirrors the name/role of any computed sibling that was bypassed).

#### Details
The reuse of `o_14` and `o_17` is structurally identical to a standard cost-plus-overhead-plus-margin pricing formula (`total = base * (1+overhead%) * (1+margin%)`), where a subtotal is legitimately used both as (a) the base for computing its own surcharge and (b) an addend alongside that surcharge to build the next subtotal. This differs categorically from the double-counting attack pattern, in which the *same* cost/revenue entity is added into the *same* terminal aggregate via two independent, non-overlapping paths that both resolve as separate addends. Here there is exactly one accumulation path per unique root entity into `o_20`, and the profit-margin basis is explicitly documented in the graph's own metadata, satisfying the "documented split/allocation logic" exception. No naming collisions, orphaned computed twins, or hardcoded literal overrides masquerading as computed variables were found. Overall this graph presents as a clean, internally consistent construction bid pipeline.