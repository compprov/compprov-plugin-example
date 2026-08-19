# Summary
- **Verdict**: CLEAN
- **Confidence score**: 78.0

## Anomaly Localization (If Detected)
No variable or operation was found where a technically type-preserving operation smuggles in a changed business meaning (C_source != C_target) without an auditable, correctly-labeled transformation. Full trace below.

## Trace of Business Context Through the Graph

- `i_2` (Invoice subtotal, as billed, 48000.00) and `i_3`/`i_5` (400 * 120.00 = 48000.00) are mutually consistent — the 'as billed' subtotal is exactly quantity-invoiced × unit-price. No silent re-basing.
- `op_1` (`i_3 - i_4` → `o_6`, Quantity variance = invoiced − received = 400 − 398 = 2) matches its own formula/descriptor and argument order exactly (invoiced first, received second, as the name states).
- `op_2` (`o_6 * i_5` → `o_7`, Variance adjustment amount = 2 × 120.00 = 240.00) correctly converts a *quantity* variance into a *monetary* variance via the stated unit price — a legitimate, explicit domain transformation (quantity→amount), not an implicit relabel.
- `op_3` (`i_2 - o_7` → `o_8`, Adjusted subtotal = 48000.00 − 240.00 = 47760.00) is arithmetically identical to received-qty × unit-price (398 × 120 = 47760), confirming the three-way-match adjustment is semantically sound, not merely numerically convenient.
- `o_8` is subsequently reused (fan-out flagged structurally) as the tax base (`op_4`), the running total base (`op_5`), and the discount base (`op_6`). In each case it is consumed under the *same* declared meaning — "Adjusted subtotal (after three-way match)" — with no relabeling, no unit change, and no stripped metadata between uses. This is legitimate reuse of a shared base value, not a context cast.
- `op_4` (`o_8 * i_9` → `o_10`, Tax = 47760.00 × 0.07 = 3343.2000) applies the tax rate to the corrected (post-match) subtotal, which is the semantically correct base for tax — not the stale as-billed figure.
- `op_5` (`o_8 + o_10` → `o_11`, Adjusted subtotal plus tax = 51103.2000) is a straightforward, correctly labeled aggregation.
- `op_6` (`o_8 * i_12` → `o_13`, Early-payment discount = 47760.00 × 0.02 = 955.2000) applies the discount rate to the pre-tax adjusted subtotal — a defensible and explicitly labeled business choice (discount on goods amount, not tax), not a hidden gross/net substitution.
- `op_7` (`o_11 - o_13` → `o_14`, Amount remitted = 51103.2000 − 955.2000 = 50148.0000) closes the chain: (adjusted subtotal + tax) − discount, all terms traceable back to correctly labeled ancestors.

Every `descriptor.meta`/name pairing observed at a variable's point of *origin* is preserved at every point of *consumption*; no operation reinterprets a value under a conflicting business definition, and no metadata (units, tax status, domain tags) is stripped or overwritten en route.

## Details
The pipeline models a standard three-way-match invoice adjustment followed by tax and an early-payment discount. All quantity, rate, and monetary variables retain internally consistent, mutually corroborating semantics: the billed subtotal is reproducible from invoiced qty × unit price, the adjusted subtotal is reproducible from received qty × unit price, and downstream tax/discount/remittance calculations consistently reference the correctly adjusted base rather than a stale or mismatched one. The single fan-out node (`o_8`) — flagged structurally as consumed by more than one operation — was specifically audited for the classic "shared variable silently reinterpreted per consumer" pattern that defines this attack vector, and no such reinterpretation was found: all three consumers (tax, running-total, discount) use it under its one declared meaning.

The only debatable business-policy choice is that the early-payment discount is computed on the pre-tax adjusted subtotal rather than the tax-inclusive total. This is a legitimate, commonly-used AP policy (discount applies to goods value, not tax), it is consistently and explicitly labeled, and there is no metadata suggesting a different intended base — so it does not meet the bar of an unauthorized, undocumented context change. Because this is the one point where reasonable auditors could disagree on intended business rule (rather than a clear-cut relabeling/metadata-stripping event), full certainty is withheld, but no structural or semantic evidence of a Semantic Type and Context Cast attack was identified in this graph.