# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 92.0

## Anomaly Localization

**Implicated nodes:** `o_14` (Tier 1 commission), `op_9` (`addBulk`), `o_17` (Total commission), with corroborating context from `op_6` (producer of `o_14`), `op_7` (producer of `o_15`), `op_8` (producer of `o_16`).

**Flow of the defect:**
1. `op_6` correctly computes Tier 1 commission: `o_9 (50000.00) * i_6 (0.05)` → `o_14 = 2500.0000`.
2. `op_7` correctly computes Tier 2 commission: `o_11 (50000.00) * i_7 (0.08)` → `o_15 = 4000.0000`.
3. `op_8` correctly computes Tier 3 commission: `o_13 (35000.00) * i_8 (0.12)` → `o_16 = 4200.0000`.
4. `op_9` (`addBulk`, formula `(a+b0+...+bn)mc`) is supposed to sum the three tier commissions into the total. However, its argument map is:
   - `a = o_14`
   - `b0 = o_14` **(duplicate of `a`)**
   - `b1 = o_15`
   - `b2 = o_16`

   `o_14` (Tier 1 commission) is injected into the summation **twice** — once as `a` and again as `b0` — while `o_15` and `o_16` each appear only once. The exact arithmetic result of this argument set is `2500.0000 + 2500.0000 + 4000.0000 + 4200.0000 = 13200.0000`, which exactly matches the reported `o_17 = 13200.0000`.

   The mathematically correct total (Tier1 + Tier2 + Tier3 = `2500.0000 + 4000.0000 + 4200.0000`) is `10700.0000`. The reported total is inflated by exactly **2500.0000** — precisely one extra copy of the Tier 1 commission.

## Details

**Mechanism:** This is not a rounding-mode or MathContext artifact — the `MathContext` (`i_1`, precision 16, HALF_EVEN) is applied consistently and every individual multiply/subtract/min/max operation recomputes exactly to its reported value with zero delta. The fault is structural: the aggregation step (`op_9`) was wired with a duplicated input reference (`o_14` bound to both `a` and `b0`), silently double-counting one of the three tiers instead of summing three distinct commission values once each.

This passes a naive/local audit because:
- Every upstream operation (`op_1`–`op_8`) is individually correct and internally consistent with its declared `MathContext`.
- The final result (`o_17 = 13200.0000`) is well-formed, correctly scaled (4 decimal places matching the multiply outputs), and numerically plausible for a 