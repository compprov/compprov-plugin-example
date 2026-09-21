# Summary
- **Verdict**: CLEAN
- **Risk score**: 4.0

## Audit Summary
This CPG implements a standard three-tier progressive commission calculation. Full forward-propagation trace:

- `o_9` = min(i_3=135000, i_4=50000) = 50000 (revenue through tier 1)
- `o_10` = min(i_3=135000, i_5=100000) = 100000 (revenue through tier 2)
- `o_11` = o_10 - o_9 = 50000 (tier 2 portion)
- `o_12` = i_3 - o_10 = 35000 (tier 3 portion, uncapped)
- `o_13` = max(o_12, i_2=0) = 35000 (tier 3 portion, floored)
- `o_14` = o_9 * i_6(0.05) = 2500.0000 (tier 1 commission)
- `o_15` = o_11 * i_7(0.08) = 4000.0000 (tier 2 commission)
- `o_16` = o_13 * i_8(0.12) = 4200.0000 (tier 3 commission)
- `o_17` = addBulk(o_14, o_15, o_16) = 10700.0000 (total commission, terminal output)

### Path Multiplicity Review
`i_3` (monthly revenue), `o_9`, and `o_10` are each consumed by more than one downstream operation (per the structural reference set). Tracing each:
- `i_3` feeds `op_1` (min→o_9), `op_2` (min→o_10), and `op_4` (subtract→o_12). These are three *distinct* derived quantities (tier-1 capped revenue, tier-2 capped revenue, tier-3 uncapped revenue) that are mutually exclusive slices of the same revenue figure — a standard "running cap" tiering pattern, not duplication into the same terminal aggregate. Only `o_9`, `o_11`, and `o_13` (the three non-overlapping tier slices) ultimately reach `o_17` via `o_14`, `o_15`, `o_16` respectively — each exactly once.
- `o_9` is consumed by `op_3` (subtract, as subtrahend, to compute `o_11`) and `op_6` (multiply, to compute `o_14`). These are two different roles (used to derive the next tier's incremental base, and to compute tier 1's own commission) — not two paths of the *same* value into the terminal sum. `o_9` itself is not additively re-entered into `o_17`.
- `o_10` is similarly consumed by `op_3` (minuend) and `op_4` (subtrahend), both intermediate subtractions used once each to build non-overlapping tier bases.

Manual reconstruction confirms $S_{dedup}$: tier1 (50000×5%=2500) + tier2 (50000×8%=4000) + tier3 (35000×12%=4200) = 10700, exactly matching `o_17` = 10700.0000. No double counting is present.

### Omission Check
The only leaf variable is `o_17` itself (the intended terminal output). All other outputs (`o_9`–`o_16`) are intermediate values that are fully consumed downstream, ultimately converging into `o_17` via exactly one path each. No mandatory contributor (tier 1/2/3 commission) is dropped from the final `addBulk` operation's argument list, which correctly includes `o_14`, `o_15`, `o_16`.

### Lineage Substitution Check
No leaf/name collisions were found (per structural reference data), and no root `INPUT` shadows a computed sibling with the same name, units, or role. All root inputs (`i_1`–`i_8`) are consumed exactly where expected, and every downstream operation consumes the literal `resultId` of its true predecessor step (e.g., `op_6` consumes `o_9`, not a hardcoded stand-in for tier-1 revenue). Forward propagation from roots to `o_17` reproduces the reported value exactly.

## Conclusion
No Calculation Omission ($M=0$), no Double Counting ($M>1$), and no Lineage Disconnection/Context Substitution were identified. The reused variables (`i_3`, `o_9`, `o_10`) are legitimate shared bases for non-overlapping tier computations, consistent with a textbook progressive-tier commission formula, and the deduplicated sum matches the reported terminal output exactly.

#### Anomaly Localization (If Detected)
None detected — no variable/operation IDs are implicated in a confirmed violation.

#### Details
The apparent multi-consumption of `i_3`, `o_9`, and `o_10` (flagged structurally) is explained fully and consistently by the tiered-commission formula's need to compute successive capped revenue bands and their deltas; none of these reused values are additively re-entered into the terminal `addBulk` operation. Arithmetic reconciliation (2500 + 4000 + 4200 = 10700) matches the reported terminal value precisely, and every root input is legitimately and singly incorporated into the final result via its own unbroken computation chain.