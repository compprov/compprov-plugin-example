# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Anomaly Localization (If Detected)
No variable or operation exhibits a business-context mismatch under identical technical typing. The full lineage was traced:

- `i_1` ("Amount to taker (pre-fee)", BigInteger) → consumed by `op_1` (multiply with `i_2`) and `op_3` (subtract against `o_5`).
- `i_2` ("Fee rate (basis points)") and `i_3` ("Basis points denominator") → feed `op_1`/`op_2` respectively.
- `op_1`: `i_1 * i_2` → `o_4` ("Amount * fee rate") — labeled purely as an intermediate product, not re-cast as a final fee or net amount.
- `op_2`: `o_4 / i_3` → `o_5` ("Taker fee (rounded down)") — correctly represents the bps-fee formula `amount*rate/denominator`, truncated via integer division, matching its "rounded down" label.
- `op_3`: `i_1 - o_5` → `o_6` ("Amount credited to taker") — subtracts the just-computed fee from the same pre-fee amount, consistent with a Gross → Net transition performed via an explicit, auditable `subtract` operation.

All numeric values replay correctly (1000003×37=37,000,111; ⌊37,000,111/10,000⌋=3,700; 1,000,003−3,700=996,303), and the descriptor names at each hop remain semantically coherent with the operation performed on them.

## Details
The attack vector under audit — silent re-mapping of business context (e.g., Gross→Net, or stripping/altering `domainType`/`units`/`taxStatus` metadata) while preserving type continuity — requires evidence of a declared business meaning at one node being violated or silently substituted at a downstream consumer. In this graph:

1. **Metadata surface is empty everywhere** (`descriptor.meta: []` on every variable and only `formula` meta on operations). There is no `domainType`, `units`, or `taxStatus` tag present anywhere in the graph to strip or contradict — so the specific mechanism of "metadata suppression while types persist" has no target to exploit here; there is nothing to silently remove.
2. **Descriptor names are internally consistent across the full lineage.** `i_1` is explicitly labeled "pre-fee" and is used twice — once as the multiplicand for the fee calculation and once as the minuend for the final credit calculation — both uses are the textbook definition of a pre-fee gross amount in an AMM fee-deduction flow (fee is computed on gross, then subtracted from the same gross to yield net). This dual consumption was flagged structurally (`i_1` used by >1 operation), but tracing its semantic role shows no contradiction: both consumers treat it identically as "the pre-fee amount," not as two different domain entities.
3. **The Gross→Net transition (`o_6 = i_1 - o_5`) is backed by an explicit `subtract` operation** with a documented formula (`a-b`), satisfying the invariant that domain transitions require auditable transformation logic rather than an implicit relabeling or identity/wrapper pass-through. No node performs a no-op/identity cast that would let a downstream consumer silently reinterpret a value's business meaning.
4. No leaf variable shares a name collision with another node (confirmed by the provided structural set), and no intermediate output (`o_4`, `o_5`) is consumed by an operation whose name/formula is inconsistent with its declared label (e.g., a "fee" value being fed into an operation that treats it as a full principal, or a pre-fee value being downstream treated as though already net).

**Consequence assessment:** Given the complete absence of domain-tag metadata to manipulate, and given that every operation's declared name/formula matches its numeric behavior and matches the descriptor-name semantics of its inputs and outputs, I find no instance where C_source != C_target under matching technical types. This is a small, tightly-scoped, mathematically and semantically coherent fee-deduction pipeline. I cannot rule out with absolute certainty that richer metadata existed upstream and was stripped before this snapshot (the graph gives no positive evidence either way), which is the basis for a confidence score short of maximum, but no violation of the stated invariants is demonstrable from the graph as given.