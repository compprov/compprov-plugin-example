# Summary
- **Verdict**: SUSPICIOUS LOGIC
- **Risk score**: 68.0

## Anomaly Localization

**Implicated nodes:** `o_14`, `o_15`, `o_16` (computed "Federal tax withheld"), `i_17` (INPUT "Federal tax withheld"), `op_8`, `op_10`, `o_20`, `o_21`.

Trace of the affected chain:

1. `op_6`/`op_7` compute `o_14` (Bracket 1 tax, 200.0000) and `o_15` (Bracket 2 tax, 363.0000) from the taxable income `o_8`.
2. `op_8` (`add`) sums `o_14 + o_15` → `o_16` ("Federal tax withheld", 563.0000). This is the *audited, derived* federal withholding figure, fully backed by an explicit bracket-tax computation.
3. Per the structural reference data, `o_16` is a **leaf** — it is never consumed by any downstream operation. The computation that produces it is a dead end.
4. Instead, `op_10` (`subtract`, `o_8 - i_17 → o_20`) consumes `i_17`, a freshly declared **root INPUT** node that carries the *identical descriptor name* ("Federal tax withheld") and a numerically equal value (563.00 vs. 563.0000, note the differing scale — 2 decimals vs. 4, consistent with `i_17` being a distinct externally-supplied literal rather than a byte-for-byte carry of `o_16`).
5. `o_20` ("After federal withholding") and ultimately `o_21` ("Net pay") are therefore derived from the unaudited `i_17`, not from the verified bracket-tax derivation chain (`o_12`→`o_14`, `o_13`→`o_15`→`o_16`).

## Details

This is a textbook Semantic Type and Context Cast: technical type continuity is perfect (`BigDecimal → BigDecimal`), the descriptor label is preserved exactly ("Federal tax withheld" appears on both `o_16` and `i_17"), and the numeric values happen to coincide in this trace — so naive schema/type validation and even a naive "does the final number look right" replay both pass cleanly. Yet the actual provenance backing the value consumed in the net-pay calculation is silently switched: the graph presents a fully derived, bracket-by-bracket computation of federal withholding (`o_16`) purely for show, while the operation that actually feeds the net-pay result (`op_10`) instead consumes a same-named but *unconnected* root INPUT (`i_17`) that has no operation, no formula, and no lineage tying it to the bracket-tax logic at all.

This violates the stated invariants in two ways:
- "No operation may consume a variable under a business definition that conflicts with its originating metadata" — `i_17`'s originating metadata is "raw external input," not "verified computed withholding," yet it is consumed as if it were the latter under an identical label.
- "Domain transitions must be backed by explicit, auditable transformation logic, not merely by an operation that happens to be type-safe" — there is no transformation node linking `o_16` to `i_17`; the switch from computed value to injected value is implicit and undocumented.

**Consequence:** Because the downstream `Net pay` figure is mathematically anchored to `i_17` rather than to the audited bracket computation `o_16`, an adversary (or a future, less carefully checked run) can set `i_17` to any value independent of the actual tax bracket math. The graph would still show a perfectly correct-looking bracket-tax derivation (`o_16`) as a decoy, while the real net-pay result silently diverges from that derivation. The current trace is only benign because the two values happen to match numerically (and even then, with a suspicious scale mismatch, 563.00 vs 563.0000, suggesting `i_17` was independently authored rather than copied). This decoupling of the "shown" computation from the "used" computation, under an identical business label, is precisely the disguise mechanism the context-cast attack vector describes, and it should be treated as a material provenance/integrity finding pending confirmation of an explicit, auditable link between `o_16` and `i_17`.