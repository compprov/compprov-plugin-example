# Summary
- **Verdict**: ANOMALY DETECTED
- **Risk score**: 72.0

## Anomaly Localization

**Implicated nodes:** `i_8` (ETH balance, source "Trust Nodes"), `i_9` (WSTETH balance, source "Lido"), `op_1` (`add`), `o_11` ("WSTETH balance"), `i_3` (WSTETH/ETH rate), `op_2` (`convert`), `o_12` ("WSTETH->ETH"), `i_2` (ETH/USD rate), `op_6` (`convert`), `o_16` ("ETH(Staked)->USD"), `op_8` (`addBulk`), `o_18` ("Assets sum").

**Flow of the defect:**
1. `op_1` computes `o_11 = i_9 + i_8`, i.e. it directly adds a **WSTETH**-denominated balance (`i_9 = 51.629114720513456404 WSTETH`) to an **ETH**-denominated balance (`i_8 = 0.314511152401086846 ETH`), with no conversion applied to either operand. The result, `51.943625872914543250`, is exactly the sum of the two raw numeric magnitudes (verified digit-by-digit) — confirming the operation truly is a naked, unit-blind addition rather than a mislabeled but internally-converted quantity. The result is then re-labeled `o_11 = 