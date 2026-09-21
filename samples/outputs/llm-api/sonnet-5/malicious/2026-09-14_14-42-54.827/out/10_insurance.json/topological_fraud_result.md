# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 92.0

## Anomaly Localization

**Implicated nodes:** `i_20` (Liability Deductible, 250.00), `op_9` (subtract → `o_23`), `op_13` (addBulk → `o_27` "Payout"), `op_14` (add → `o_28` "Total payout"), `o_28`, `op_15` (→ `o_30` Reinsurance recovery), `op_16` (→ `o_31` Net loss, the graph's sole terminal/leaf output).

**Attack flow:**

1. `i_20` (Liability Deductible = 250.00) is correctly consumed once by `op_9` (`subtract`) as part of the legitimate Liability net-of-deductible chain: `i_20` → `op_9` → `o_23` → `op_10` → `o_24` → `op_11` → `o_25` → `op_12` → `o_26` (Liability claim payout = 4750.0000).
2. `o_26` is then correctly summed with the Collision and Comprehensive payouts (`o_10`, `o_18`) in `op_13` (`addBulk`) to produce `o_27` ("Payout" = 6000 + 12000 + 4750 = 22750.0000). This is the fully-formed, correctly deduplicated payout total.
3. Immediately afterward, `op_14` (`add`) computes `o_28` ("Total payout") as `o_27 + i_20` = 22750 + 250 = **23000.0000** — re-injecting the *same* `i_20` (Liability Deductible) that was already netted out earlier in the very chain that produced `o_26`/`o_27`.
4. `o_28` is the sole feed into `op_15` (reinsurance recovery = `o_28 * 0.40` = 9200.0000) and `op_16` (net loss = `o_28 - o_30` = 13800.0000), which is the graph's only leaf/terminal output (`o_31`).

Thus `i_20` reaches the terminal output `o_31` via **two distinct causal paths**: (a) embedded correctly inside `o_26`→`o_27`, and (b) added a second time, raw and unattenuated, directly into `o_28`. This is a textbook M>1 double-counting violation, not a documented allocation split — no metadata, formula annotation, or descriptor field explains why a per-claim deductible should be added back into a company-wide "Total payout" aggregate.

## Details

**Mechanism:** `op_14`'s formula metadata is the generic `(a+b)mc`, giving no indication that `b` should be a deductible rather than, say, a legitimate additional fee or adjustment. Because `i_20` is a real, correctly-typed, correctly-valued root INPUT that also participates validly elsewhere, a naive single-step replay of `op_14` looks completely legitimate — it is arithmetically correct addition of two valid operands. The fraud only becomes visible when tracing `i_20`'s *entire* forward path set and noting it re-converges on the same terminal aggregation lineage (`o_28`→`o_30`/`o_31`) that its own netting chain already feeds into via `o_27`.

This matches the structural reference data precisely: `i_20` and `o_28` both appear in the "consumed by more than one operation" set, and unlike `i_2` (a genuine, position-independent zero-floor constant reused identically across three *non-converging* branches), `i_20`'s second consumption converges directly back into the same terminal rollup as its first — the exact discriminator the spec calls out between benign constant reuse and true double counting.

**Consequences:** The reported `o_28` ("Total payout") is inflated by exactly the Liability deductible amount (250.00) versus the correct value of `o_27` (22750.0000). This error cascades multiplicatively through the reinsurance layer: reported Reinsurance recovery (`o_30` = 9200.0000) should be 22750 × 0.40 = 9100.0000, and reported Net loss (`o_31` = 13800.0000) should be 22750 − 9100 = 13650.0000. The terminal output is therefore misstated by 150.0000 (net loss overstated), directly attributable to the improper re-entry of `i_20` into the aggregation at `op_14`. This is a clear, material Topological Accumulation Fraud (M=2) on a mandatory deduction variable, propagating all the way to the graph's true terminal output.