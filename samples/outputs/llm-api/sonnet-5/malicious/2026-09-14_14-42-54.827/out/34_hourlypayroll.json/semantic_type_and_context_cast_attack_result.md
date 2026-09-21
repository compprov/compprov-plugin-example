# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 82.0

## Anomaly Localization

**Implicated nodes:** `i_6` (root INPUT), `o_7` (op_2 result), `o_8` (op_3 result), `o_12` (op_5 result), `o_14` (op_6 result), `o_15` (op_7 result).

**Flow of the tainted value:**

1. `i_6` is declared with `descriptor.name = "Overtime multiplier (1.5x)"` — an explicit, unambiguous business-semantic claim that this variable represents the standard time-and-a-half (1.5x) overtime multiplier.
2. The **stored numeric payload of `i_6` is `1.25`**, not `1.50`. The technical type (`java.math.BigDecimal`) is fully valid and the value passes all schema/type checks — but it contradicts its own declared business label.
3. `op_2` (`multiply`) consumes `i_2` (Base hourly rate, 22.00) and `i_6` under the mc context `i_1`, producing `o_7` ("Overtime rate") = 22.00 * 1.25 = 27.5000. Mathematically self-consistent, but semantically this is a **1.25x rate being silently laundered through a variable whose label promises 1.5x**.
4. `op_3` propagates this mislabeled rate into `o_8` ("Overtime pay") = 6 * 27.5000 = 165.0000.
5. `op_5` (`addBulk`) folds the corrupted `o_8` into `o_12` ("Gross pay") = 880.00 + 165.0000 + 21.00 = 1066.0000.
6. `op_6` and `op_7` propagate the corrupted gross pay into `o_14` (tax withholding) and `o_15` (Net pay = 874.120000), so the final reported net pay is computed on a foundation that silently substituted a 1.25x multiplier for the declared, standard 1.5x overtime multiplier.

## Details

This is a textbook **Semantic Type and Context Cast Attack**. The technical layer is flawless: `i_6` is a correctly-typed `BigDecimal`, every downstream `multiply`/`addBulk`/`subtract` operation replays the arithmetic exactly as declared in its `formula` metadata, and every intermediate value is internally consistent with its stated formula. A naive validator checking type continuity (`BigDecimal -> BigDecimal`), node connectivity, and arithmetic replay would find nothing wrong.

The violation lives entirely in the **business-context layer**: the `descriptor.name` for `i_6` explicitly asserts the value represents a "1.5x" overtime multiplier — a well-known, often legally-mandated payroll constant — yet the actual value driving every downstream computation is `1.25`. There is no explicit transformation node (no documented discount, no proration, no labor-law exception) justifying the gap between the declared multiplier and the value consumed by `op_2`. Per the stated invariants, any change in semantic interpretation (here, from "standard 1.5x overtime" to "a reduced 1.25x rate") requires an explicit, auditable domain-transformation step — implicit relabeling or a mismatched constant value is prohibited regardless of whether the surrounding math is type-safe and internally consistent.

**Consequences:** Every downstream OUTPUT that depends on the overtime calculation — `o_7` (Overtime rate), `o_8` (Overtime pay), `o_12` (Gross pay), `o_14` (Tax withholding), and `o_15` (Net pay) — is computed on a silently substituted business definition. Recomputing with the multiplier the label actually promises (1.5x) yields: Overtime rate = 33.00, Overtime pay = 198.00, Gross pay = 1099.00, Tax = 197.82, Net pay = 901.18 — versus the reported 874.12. This is a material ~$27.06 per-period understatement of employee net pay, achieved purely by decoupling a variable's declared business meaning from its actual encoded value while preserving perfect type and arithmetic continuity — exactly the attack pattern this audit is designed to catch.