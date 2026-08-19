# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 80.0

## Anomaly Localization

**Implicated nodes:** `o_12`, `o_13`, `o_14`, `o_15`, `o_16` (computed federal tax subgraph), `i_17` (input federal tax), `o_20`, `o_21` (final net pay aggregation), plus supporting inputs `i_9`, `i_10`, `i_11`.

**Flow of the attack:**

1. The graph faithfully and transparently computes a progressive federal tax liability from `o_8` (Taxable income = 3650.00):
   - `op_4`: `o_12 = min(o_8, i_9)` = min(3650.00, 2000.00) = 2000.00 ("Bracket 1 portion")
   - `op_5`: `o_13 = o_8 - o_12` = 1650.00 ("Bracket 2 portion")
   - `op_6`: `o_14 = o_12 * i_10` = 200.0000 ("Bracket 1 tax", 10%)
   - `op_7`: `o_15 = o_13 * i_11` = 363.0000 ("Bracket 2 tax", 22%)
   - `op_8`: `o_16 = o_14 + o_15` = 563.0000 — explicitly named **"Federal tax withheld (computed, unused)"**
2. `o_16` is never consumed by any downstream operation — it is a structural leaf, confirmed by the provided leaf-set (`o_16` appears there).
3. Instead, the final net-pay chain uses a *different*, independently supplied `INPUT` variable, `i_17` ("Federal tax withheld", value 563.00, a root node with **no producing operation** — it did not come from the bracket computation at all):
   - `op_10`: `o_20 = o_8 - i_17` = 3087.00
   - `op_11`: `o_21 = o_20 - o_19` = 2904.5000 ("Net pay", the reported final output)

The correctly-computed, auditable bracket-tax result (`o_16`) is severed from the aggregation that produces the reported `Net pay`. The value that actually reaches the final result is a free-standing input (`i_17`) that merely *happens* to coincide numerically with `o_16` in this instance.

## Details

This is a textbook Calculation Omission pattern as defined by the audit vector: a mandatory adjustment (federal tax withholding) is computed transparently and correctly in an isolated subgraph (`op_4`–`op_8`), producing a value whose own variable name ("...computed, unused") explicitly concedes that it is not wired into the result. The pipeline then substitutes an unrelated, externally-supplied root input (`i_17`) with no causal link to the bracket-calculation subgraph to perform the actual deduction in `op_10`/`op_11`.

Why this passes casual/local review:
- Local replay of the bracket arithmetic (`op_4`–`op_8`) is internally self-consistent and correct — an auditor spot-checking "is the federal tax computed correctly?" will get a satisfying "yes."
- The final aggregation (`op_10`, `op_11`) also replays correctly given its own inputs (`o_8`, `i_17`, `o_19`) — an auditor spot-checking "is net pay = taxable income − federal tax − state tax?" will also get a satisfying "yes," because `i_17` happens to equal `o_16` in this trace.
- Neither local check surfaces the disconnect: the value that actually reaches the reported output has zero causal dependency on the audited bracket computation. `i_17` is a root variable — it could be replaced by any figure the pipeline operator chooses, and the bracket subgraph (`o_16`) would still "correctly" compute 563.0000 in complete isolation, giving false assurance to any reviewer who only checks that the tax math is right.

**Consequence:** The reported "Net pay" is only as trustworthy as the un-vetted `i_17` input. The graph provides no causal proof that the withholding actually applied to net pay matches the withholding legally/correctly computed from the bracket schedule. This is precisely the attack surface the Calculation Omission vector targets — a verifiable computation is present in the graph purely for appearances, while the actual reported result silently bypasses it in favor of an independently controllable value. Should `i_17` ever diverge from the true bracket-computed value (intentionally or via manipulation), `o_21` (Net pay) would report an incorrect figure while the bracket subgraph would still "prove" the tax was computed correctly — an ideal disguise for underpaying or overpaying withholding without detection by shallow verification.

A benign explanation — e.g., that `i_17` represents an actual employer-remitted withholding amount that may legitimately differ from the freshly computed liability — is plausible in principle, but the graph contains no metadata, annotation, or documented rationale establishing that distinction; on the contrary, `o_16`'s own name ("computed, unused") signals awareness that the computed value was deliberately routed away from the result. Per audit discipline, this structural violation should be reported rather than resolved by narrative alone.