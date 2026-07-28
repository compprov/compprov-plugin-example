# Summary
- **Verdict**: DOUBLE COUNTING DETECTED
- **Confidence score**: 95.0

## Anomaly Localization

**Implicated root entities (gas-cost inputs):** `i_13`, `i_14`, `i_15`, `i_16`, `i_17`, `i_18`

**Implicated intermediate conversions (gas legs, all in the "multiply-consumed" reference set):** `o_20`, `o_21`, `o_22`, `o_23`, `o_24`, `o_25`

**Implicated per-position net-yield nodes (where gas is netted the FIRST time):** `o_28` (uses `o_20`), `o_30` (uses `o_21`), `o_31` (uses `o_22`), `o_33` (uses `o_23`), `o_35`/`o_36` (use `o_24` and `o_25`)

**Implicated aggregation nodes:** `o_26` (`addBulk` of all six gas legs), `o_37` (`addBulk` of the five already-gas-net position subtotals), `op_20` (the second, redundant subtraction), `o_39`, and finally the terminal output `o_40` ("Net profit in USDC")

### Attack flow

Each gas-cost leg (`i_13`…`i_18`) is converted to USDC (`o_20`…`o_25`) and then travels to the terminal output `o_40` via **two independent, non-overlapping-looking paths that both survive local replay**:

- **Path A (embedded deduction):** `o_20` → subtracted from `o_27` in `op_9` → `o_28` → summed into `o_37` (`op_18`). The analogous chain applies for `o_21`→`o_30`, `o_22`→`o_31`, `o_23`→`o_33`, and `o_24`+`o_25`→`o_35`→`o_36`, all summed into `o_37` ("Gross yield in USDC"). This means `o_37`, despite its label, is **already net of all six gas fees** — verified numerically: raw yields sum to `1738.305562`, and `1738.305562 − 111.4336 (total gas) = 1626.871962`, which is exactly the reported `o_37`.
- **Path B (explicit re-deduction):** the same six gas legs (`o_20`…`o_25`) are separately summed via `op_7` into `o_26` ("Total gas fees in USDC" = 111.4336), and `op_20` then computes `o_39 = o_37 − o_26` ("After gas deduction") — subtracting the identical gas total **a second time** from a subtotal (`o_37`) that already had it netted in.

`o_39` feeds directly into `op_21` (`o_40 = o_39 − o_38`), so the double subtraction propagates unmodified into the terminal output. Reversing the second subtraction shows the true value: `o_37 − o_38 = 1626.871962 − 48.806158 = 1578.065804`, versus the reported `Net profit = 1466.632204` — a discrepancy of exactly `111.433600`, i.e. precisely `o_26`, proving the entire gas total was deducted twice.

## Details

The fraud is structurally disguised by two devices that defeat naive/local checks:

1. **Local arithmetic is flawless at every single node.** Every `convert`, `subtract`, and `addBulk` operation is individually correct given its stated inputs — a step-by-step replay of each operation in isolation would pass. The anomaly only appears when tracing the *global* path multiplicity of the six gas-fee entities all the way to the terminal output, exactly as required by the audit methodology (multiplicity $M > 1$ for `i_13`…`i_18` into `o_40`, all six routed as deductions).
2. **Deceptive labeling masks the reuse.** `o_37` is named "Gross yield in USDC," strongly implying it is *pre*-deduction, when it is mathematically already *post*-gas. `o_39` is named "After gas deduction," implying this is the *first and only* point gas is removed. This naming convention is precisely the kind of look-alike/secondary-path camouflage the attack vector describes — an auditor scanning names and single-hop provenance would conclude gas is deducted exactly once, per position, then rolled up cleanly — when in fact it is netted at the position level *and* re-subtracted in aggregate.
3. **The structural reference data corroborates this exactly:** `o_20`–`o_25` (the six gas-conversion outputs) are all flagged as consumed by more than one operation — each feeds both its position-level `subtract` (into the `o_37` lineage) and the `addBulk` that produces `o_26` (later subtracted from `o_37` again). This is not benign shared-parameter reuse (like a `MathContext` or a rate reused across independent conversions) — it is the same cost entity's *value* being counted twice into the same subtractive rollup chain terminating at `o_40`.

**Consequence:** The reported "Net profit in USDC" (`o_40 = 1466.632204`) is deflated by `111.4336` USDC relative to the value implied by the graph's own upstream computations (`1578.065804`), a ~7.6% understatement of profit. This is the deduction-side mirror of accumulation fraud explicitly named in the invariant set: "a cost that has already been netted into an intermediate subtotal must not be subtracted again from a later aggregate that already incorporates that subtotal" — which is precisely what happens with `o_26` being subtracted from `o_37` in `op_20`.