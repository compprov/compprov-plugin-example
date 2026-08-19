# Summary
- **Verdict**: CLEAN
- **Confidence score**: 91.0

## Anomaly Localization (If Detected)
None. Full forward propagation was performed and no lineage disconnection or context substitution was found.

## Details
**Full chain trace:**
- op_1: subtract(i_3=400, i_4=398) → o_6 = 2 (quantity variance)
- op_2: multiply(o_6=2, i_5=120.00) → o_7 = 240.00 (variance adjustment amount)
- op_3: subtract(i_2=48000.00, o_7=240.00) → o_8 = 47760.00 (adjusted subtotal)
- op_4: multiply(o_8=47760.00, i_9=0.07) → o_10 = 3343.2000 (tax)
- op_5: add(o_8=47760.00, o_10=3343.2000) → o_11 = 51103.2000 (adjusted subtotal + tax)
- op_6: multiply(o_8=47760.00, i_12=0.02) → o_13 = 955.2000 (early-payment discount)
- op_7: subtract(o_11=51103.2000, o_13=955.2000) → o_14 = 50148.0000 (amount remitted)

Every numeric result reproduces exactly under local replay, and — critically for this specific audit — every computed intermediate output (`o_6`, `o_7`, `o_8`, `o_10`, `o_11`, `o_13`) is consumed as the literal `resultId`-derived argument of the *next* logical operation in the chain. `o_8` (the adjusted, three-way-matched subtotal) is the single variable reused by multiple downstream operations (op_4, op_5, op_6), which is legitimate reuse of one properly-derived quantity, not a substitution — it is consumed identically each time, not swapped for a parallel hardcoded twin.

**Checks specific to the attack vector:**
- Root INPUTs (`i_1, i_2, i_3, i_4, i_5, i_9, i_12`): each feeds into the chain exactly once at its appropriate step (subtotal, quantities, unit price, tax rate, discount rate) — no root input duplicates or shadows the name/role of a computed sibling.
- Leaf set is only `{o_14}`, which is the natural, expected terminal output (Amount remitted) — it is not an orphaned computed variable being bypassed in favor of a hardcoded stand-in; it is the final result of the last operation (op_7) and has no reason to be consumed further.
- Name-collision set is empty, and a broader role-based scan (matching by `descriptor.meta`, units, or near-duplicate value) turns up no additional root INPUT masquerading under a computed OUTPUT's identity (e.g., no second '47760'-ish or '3343.2'-ish hardcoded INPUT competing with `o_8`, `o_10`, `o_11`, or `o_13`).
- No computed OUTPUT among `o_6, o_7, o_8, o_10, o_11, o_13` is left unconsumed while a same-named or same-role INPUT is substituted in its place downstream — each has exactly one (or, for `o_8`, several) legitimate consumer(s) matching the described formula.

No evidence of a parallel, injected value routed into any downstream operation in place of its properly-computed upstream twin. The full causal path from roots to the final `Amount remitted` output is intact and unbroken.

**Conclusion:** The graph shows no structural or semantic signature of Lineage Disconnection or Context Substitution. All EXPECTED_INVARIANTS regarding origin propagation are satisfied for this trace.