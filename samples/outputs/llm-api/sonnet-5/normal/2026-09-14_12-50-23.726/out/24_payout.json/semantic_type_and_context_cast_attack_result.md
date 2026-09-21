# Summary
- **Verdict**: CLEAN
- **Risk score**: 8.0

## Summary
This audit specifically targeted **Semantic Type and Context Cast** attacks — cases where technical type continuity (`valueClass`, wrapper classes, mathematical replay) is preserved while the underlying business meaning (`descriptor`, domain tags, units, tax/adjustment status) is silently altered or re-mapped.

## Methodology
1. Mapped every variable's technical type (`Rate`, `OptionPosition`, `Amount`) against its declared business role (`descriptor.name`, embedded `type`/`currency`/`strike` fields).
2. Traced the lineage of the spot rate `i_1` (`ETH/USDC spot (2026-06-30)`) into all 10 `payout` operations (`op_1`–`op_10`), verifying that each consuming operation used it under the same declared context (spot ETH→USDC exchange rate) with no relabeling.
3. Independently recomputed each `payout(pos, price)` result from the raw `type` (CALL/PUT), `strike`, and `size` fields embedded in each `OptionPosition` input, to confirm that the option-type semantics actually drove the correct formula (CALL: max(0, spot-strike)*size; PUT: max(0, strike-spot)*size) rather than being silently swapped or mislabeled while keeping the same numeric output.
4. Verified the `addBulk` aggregation (`op_11`) consumes only `Amount`/`USDC` payouts, with no currency, precision, or domain-tag mismatch, en route to the final `Total payout in USDC` (`o_22`).

## Findings
- All 10 CALL/PUT payout computations reproduce exactly against the declared `type` field and the shared spot rate `i_1` (e.g., `i_2` CALL strike 4630 → (4650-4630)*6.8639 = 137.278, matching `o_12`; `i_6` PUT strike 4730 → (4730-4650)*7.8507 = 628.056, matching `o_16`). No CALL/PUT formula swap, strike/spot inversion, or `from`/`to` rate-direction flip was detected — a common vector for this attack class (e.g., quietly inverting a `Rate`'s `from`/`to` while preserving the `Rate` type) is absent here.
- The aggregation (`op_11`) sums exactly the ten `Amount`/`USDC` payout variables into `o_22`; no cross-currency, cross-precision, or gross/net relabeling occurs. The arithmetic sum (1263.710000) is exact.
- `i_1` is legitimately reused across 10 operations under an unchanged business context (spot rate), consistent with the structural note that such reuse is expected and not anomalous by itself.
- Per-variable `descriptor.meta` arrays are empty, but per the stated audit convention, absence of explicit domain tags on self-explanatory intermediate/output variables (payouts, subtotal-style aggregation) is not itself evidence of stripping or tampering — there is no explicit originating tag here that was later contradicted or removed downstream.
- No node was identified where a variable's originating domain definition (e.g., "Gross", "Pre-tax", "Local currency") was implicitly reinterpreted downstream as a conflicting concept (e.g., "Net", "Post-tax", "Base currency") while preserving type. All Amounts remain USDC throughout, all Rates remain ETH→USDC throughout, and option type semantics are causally consistent with the recomputed payouts.

## Anomaly Localization (If Detected)
No confirmed Semantic Type and Context Cast violation was localized. No variable/operation pair exhibits `C_source != C_target` under identical technical types with an explicit contradicting label or metadata suppression event.

## Details
The graph's sparse metadata (empty `descriptor.meta` on intermediate/output variables) creates some structural opacity, which is the kind of gap this attack class exploits in general — but in this instance there is no explicit conflicting label, no formula/type mismatch, and no observable re-mapping of business context between producer and consumer nodes. Full independent recomputation of every payout from the embedded `type`, `strike`, and `size` fields against the shared spot rate reproduced every reported output value exactly, which would not hold if a CALL/PUT semantic swap or rate-direction inversion had occurred while preserving numeric type. Given the absence of any explicit metadata contradiction or unexplained business-context transition, residual risk is assessed as low, reflecting only the inherent auditability limitation of sparse metadata rather than any confirmed tampering.