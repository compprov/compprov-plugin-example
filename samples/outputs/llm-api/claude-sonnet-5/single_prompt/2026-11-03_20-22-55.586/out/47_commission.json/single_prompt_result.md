# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 92.0

#### Per-Vector Verdict
- **Topological & Provenance Fraud — Double Counting**: 92/100 — Confirmed: Tier 1 commission (`o_14`) is summed twice into the terminal result via `op_9`.
- **Precision and Scale Tampering**: 5/100 — Clean: MathContext (DECIMAL64, HALF_EVEN, precision 16) is applied consistently across every arithmetic step; all recomputed values match reported values exactly.
- **Semantic Type and Context Cast**: 3/100 — Clean: every variable's declared business meaning (tier ceiling, tier rate, tier portion, tier commission) is preserved consistently through its consuming operation; no relabeling or metadata suppression detected.

---

#### Anomaly Localization (Topological & Provenance Fraud — Double Counting)
- **Implicated nodes**: `o_14` (Tier 1 commission, value `2500.0000`), `op_9` (`addBulk`, resultId `o_17`), `o_17` (Total commission, leaf/terminal output).
- **Structural reference data corroboration**: The reference data explicitly flags `op_9 (