# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 88.0

#### Per-Vector Verdict
1. **Topological & Provenance Fraud — Calculation Omission**: Score **88** — A mandatory deduction (`Minimum liquidity floor`, o_4) is computed but never consumed anywhere; the terminal output (`First depositor shares`, o_5) is derived without it. Confirmed violation.
2. **Precision and Scale Tampering**: Score **5** — All operations are exact `BigInteger` integer arithmetic (`min`, `add`); no MathContext, rounding, or scale conversion is present. No evidence of precision degradation.
3. **Semantic Type and Context Cast**: Score **40** — The variable labeled `First depositor shares` is populated with a value that, by the graph's own naming and formula semantics, actually represents gross post-deposit supply rather than the net shares owed to the user after the liquidity lock. This is a secondary manifestation of the same underlying defect flagged under Vector 1, not an independent type-cast exploit, so it is scored below the primary finding.

---

#### Anomaly Localization (Topological & Provenance Fraud — Calculation Omission)
- **Variables involved**: `i_1` (First deposit amount = 1,000,000), `i_2` (Minimum liquidity constant = 1,000), `i_3` (Total share supply (opening) = 0), `o_4` (Minimum liquidity floor = 1,000, **leaf, never consumed**), `o_5` (First depositor shares = 1,000,000, **terminal output**).
- **Operations involved**: `op_1` = `min(i_1, i_2)` → `o_4`; `op_2` = `add(i_3, i_1)` → `o_5`.
- **Flow**: The graph faithfully reproduces the classic AMM/ERC-4626 "first deposit" bootstrap pattern (cf. Uniswap V2's `MINIMUM_LIQUIDITY` lock): it correctly *computes* the minimum-liquidity floor to be withheld (`o_4 = min(1,000,000, 1,000) = 1,000`), but then the terminal share-minting operation (`op_2`) ignores `o_4` entirely, computing `o_5 = i_3 + i_1 = 0 + 1,000,000 = 1,000,000` — crediting the first depositor with the *entire* deposited amount instead of `i_1 - o_4 = 999,000`. The structural reference data confirms `o_4` is a leaf with zero downstream consumers, i.e., it is dead-end output that never reaches the terminal result.

#### Details (Topological & Provenance Fraud)
In the canonical design this pattern mirrors, the minimum-liquidity amount must be permanently locked (effectively "burned" to a dead address) and excluded from the depositor's minted shares; the reported total supply must still include it so redemption math stays consistent. Here the floor is dutifully calculated — so a cursory audit sees the "security control" present in the graph — but it is never wired into the shares formula or into the supply ledger. Locally, both `op_1` and `op_2` replay perfectly against their own arguments (that is precisely why this is dangerous: unit/type/arithmetic checks all pass). The business consequence is that the first depositor receives 1,000 extra share units they are not entitled to under the design the graph itself encodes, and the protocol's supply accounting loses the dead-share anchor that the `MINIMUM_LIQUIDITY` mechanism exists to provide — reopening the exact donation/inflation attack vector (an attacker making the very first deposit, then donating assets directly to the pool to manipulate the share price for subsequent depositors) that `o_4`'s presence in the graph suggests was supposed to be mitigated. This is a textbook Calculation Omission: the mandatory adjustment is computed, is clearly the correct candidate value (matches the known formula role and the constant's name), and yet never reaches the terminal output.

#### Anomaly Localization (Semantic Type and Context Cast — secondary)
- **Variables involved**: `o_5` (`First depositor shares`), fed by `op_2` = `add(i_3, i_1)`.
- The name `First depositor shares` asserts a *net, user-owed* quantity, but the arithmetic performed (`opening supply + full deposit`) actually yields a *gross total supply* figure, conflating two distinct business concepts under one technical type (`BigInteger`) and one label.

#### Details (Semantic Type and Context Cast)
Because `o_5`'s `valueClass` and operation signature are unremarkable (`BigInteger` + `BigInteger` → `BigInteger`), schema/type validation and mathematical replay both pass without incident. Only cross-referencing the declared name against the domain's expected formula (shares = deposit − locked minimum) exposes the mismatch. This reinforces the Vector 1 finding: the same defect can be read either as an omitted deduction in the lineage, or as a quantity being silently relabeled from "gross supply" to "user shares" without the explicit subtraction that would make the label true.
