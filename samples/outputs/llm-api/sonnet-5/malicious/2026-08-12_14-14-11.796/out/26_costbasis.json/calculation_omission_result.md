# Summary
- **Verdict**: CALCULATION OMISSION DETECTED
- **Confidence score**: 66.0

## Anomaly Localization

**Implicated nodes:** `i_1` (Computation precision — DECIMAL64, precision 16, HALF_EVEN), `i_2` (Computation precision — *tampered*, precision 4, DOWN), `op_6` (divide → `o_14`), and the entire downstream cascade `o_14 → op_8 → o_18 → op_9 → o_19`.

Every arithmetic operation in this pipeline (`op_1`–`op_5`, `op_7`–`op_9`) uniformly consumes the standard precision context `i_1` (DECIMAL64 / HALF_EVEN) as its `mc` argument — this is the pipeline's documented, consistently-applied computational standard. The single exception is `op_6`, the operation that computes the **weighted-average cost per share** (`o_14 = o_13 / o_12`), which instead consumes `i_2` — a second `MathContext` variable whose own `descriptor.name` is explicitly `"Computation precision (tampered)"` and whose value (precision 4, `RoundingMode.DOWN`) is materially degraded (lower precision, truncating rather than banker's rounding) relative to `i_1`.

Flow of the corrupted parameter into the final reported result:
1. `op_6` produces `o_14` (weighted-average cost/share) using `i_2` instead of the pipeline-standard `i_1`.
2. `o_14` is consumed by `op_8` to compute `o_18` (Cost basis of shares sold).
3. `o_18` is consumed by `op_9` to compute `o_19` (Realized gain/loss) — the final leaf/reported output.

Thus the mandatory, consistently-used computational-integrity parameter `i_1` is silently omitted from exactly the one operation that feeds the tax-relevant cost-basis and gain/loss figures, and replaced by an unauthorized, self-labeled "tampered" substitute — while every other operation in the graph continues to reference the correct context, allowing casual/local review to conclude the graph "mostly" uses the right precision.

## Details

This is a textbook disguised substitution: an adversary does not need to delete or reroute a variable to bias a result — swapping a single named parameter (`mc`) on a single downstream-critical operation is sufficient, and it is far less conspicuous than fabricating a wrong numeric literal. Here, `i_1` is exactly the kind of "cross-check"/computational-standard element described in the invariant: it is used as the authoritative precision context everywhere else in the trace, making its exclusion from `op_6` a clear, localized deviation from the computation the pipeline's own structure says it should perform (i.e., the weighted-average divide should be executed under the same DECIMAL64/HALF_EVEN contract as every sibling operation).

Critically, for the specific numeric inputs in this trace (13812.5 / 325 = 42.5 exactly), the division terminates cleanly, so the degraded precision (`4`, `DOWN`) happens to produce an identical displayed value (`42.50`) to what `i_1` would have produced. This means a naive numeric replay of the graph would find no discrepancy — exactly the scenario a competent adversary would engineer: the tampering is structurally real (a mandatory, otherwise-universal precision/consistency control is excluded from one critical operation) but numerically inert for *this* instance. Under different lot sizes/prices that don't divide evenly, the same substitution would silently truncate the weighted-average cost per share downward (DOWN vs HALF_EVEN, 4 vs 16 significant digits), understating cost basis, overstating realized gain, and biasing the tax-relevant output — with no trace-level indicator other than the argument binding itself.

The practical impact is that the provenance graph does not actually guarantee the integrity control (uniform precision/rounding policy) it appears to enforce everywhere else; the one operation where the choice of context matters most for a downstream financial figure (cost basis of a partial sale) is exactly where the safeguard was quietly swapped out. This is a genuine, material deviation from the invariant that a mandatory correction/cross-check component with an established causal role in the pipeline must not be excluded from the operation whose result it is supposed to govern — even though, by design or coincidence, it does not change the displayed numbers in this particular run.