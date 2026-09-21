# Compprov Analytics Summary

Generated: 2026-09-11T06:18:36.899981400Z[UTC]

## Overview

| № | File | Calculation | Chronology | Highlights | Precision tampering |
|---|---|---|---|---|---|
| 1 | C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\amm_taker_fee_precision_tampering.json | ✅ Valid | ✅ Valid | 1 | SUSPICIOUS LOGIC (58.0) |
| 2 | C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\game_winner_payout_precision_tampering.json | ✅ Valid | ✅ Valid | 0 | SUSPICIOUS LOGIC (68.0) |
| 3 | C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\liquidity_pool_join_precision_tampering.json | ✅ Valid | ✅ Valid | 2 | SUSPICIOUS LOGIC (60.0) |
| 4 | C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\staking_reward_fee_precision_tampering.json | ✅ Valid | ✅ Valid | 0 | ANOMALY DETECTED (88.0) |
| 5 | C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\token_sale_threshold_precision_tampering.json | ✅ Valid | ✅ Valid | 1 | ANOMALY DETECTED (85.0) |

## Highlights

### C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\amm_taker_fee_precision_tampering.json

- Multi-used variables detected, please check CPG for double-counting

### C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\liquidity_pool_join_precision_tampering.json

- Multiple leaves detected: 2 please make sure the CPG has no gaps and substitutions
- Multi-used variables detected, please check CPG for double-counting

### C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\token_sale_threshold_precision_tampering.json

- Multi-used variables detected, please check CPG for double-counting

