# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 92.0

## Anomaly Localization

**Implicated nodes:** `o_14`, `o_15`, `op_8`, `o_16` (properly computed "Federal tax withheld"), `i_17` (hardcoded "Federal tax withheld" root INPUT), `op_10`, `o_20`, `op_11`, `o_21` (terminal "Net pay").

**Attack flow:**

1. `op_1`–`op_8` correctly compute the entire progressive federal withholding chain: gross pay (`o_4`) → pretax deductions (`o_7`) → taxable income (`o_8`) → bracket split (`o_12`, `o_13`) → bracket taxes (`o_14`, `o_15`) → summed federal tax withheld, `o_16` = 563.0000, via `op_8`.
2. `o_16` is never consumed by any subsequent operation. Per the structural reference data, it is a **leaf** — a fully computed, correctly-derived mandatory contributor to the terminal net-pay result that dead-ends.
3. Simultaneously, `i_17` — an `INPUT` with `kind: INPUT`, **no producing operation** (it is a root), and the *exact same display name* "Federal tax withheld" and value "563.00" — is injected directly as a hardcoded literal.
4. `op_10` (`subtract`, `o_8 - i_17` → `o_20`, "After federal withholding") consumes `i_17`, **not** `o_16`.
5. `op_11` (`subtract`, `o_20 - o_19` → `o_21`, "Net pay") then finalizes the terminal output using the tainted `o_20`.

So the terminal reported result `o_21` (Net pay = 2904.5000) is derived from a hardcoded stand-in (`i_17`) rather than from the graph's own transparently computed federal-withholding chain (`o_16`). The computed value is orphaned; the terminal path silently swaps in an unmonitored, externally-supplied constant at the critical junction.

## Details

This is a textbook **Lineage Disconnection / Context Substitution** ($M=1$, wrong source):

- Cardinality superficially looks fine — exactly one path reaches the terminal aggregation for "federal tax withheld" — so naive replay of `op_10` and `op_11` in isolation succeeds and produces numerically plausible results.
- The values of `o_16` and `i_17` are identical to the penny (563.0000 vs 563.00), which is precisely the disguise this attack requires: a wildly different substitute would be caught trivially, whereas an identical/near-identical stand-in survives casual auditing and even basic reconciliation checks.
- The fact that `i_17` carries full descriptive metadata and is declared as a legitimate `INPUT` is not exculpatory — it is the mechanism. A hardcoded constant masquerading under the computed variable's exact name, with `kind: INPUT` and no producing operation, is exactly what a substituted value looks like from inside a CPG. Genuine constants (e.g., `MathContext`, unit conversion factors) have no computed sibling anywhere in the graph; `i_17` does — `o_16`, produced two operations earlier in the very same pipeline.
- Consequence: the bracket-tax computation chain (`op_4`–`op_8`) becomes purely decorative — its true output (`o_16`) never actually influences the reported net pay. If bracket rates, ceilings, or salary inputs are altered upstream, `o_16` will change but `o_21` (Net pay) will not reflect that change at all, since the terminal chain is silently anchored to the static `i_17` value instead. This breaks $Origin\_Propagation\_Valid$: the terminal output does not derive from full forward propagation through the graph's own computation chain from true root inputs — it consumes a substituted, unmonitored context at the critical downstream step (`op_10`).
- This is precisely the pattern flagged by the structural reference set ("leaf variables sharing exact display name with another variable": `o_16` ↔ `i_17`), which the audit discipline explicitly instructs not to dismiss as coincidence — it is the textbook signature of context substitution, not benign duplication.

No other findings (bracket-portion reuse of `o_12`/`o_8`, or the multiple consumption of `o_8`) constitute genuine double-counting into the terminal aggregate — those are legitimate decompositions (bracket splitting, state-tax basis, federal-tax basis) that do not converge redundantly into `o_21`. The single, material, and severe issue is the `o_16` → `i_17` substitution feeding the terminal Net Pay calculation.