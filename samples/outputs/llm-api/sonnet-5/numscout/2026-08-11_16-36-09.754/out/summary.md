# Compprov Analytics Summary

Generated: 2026-08-11T12:00:17.182964200Z[UTC]

## Overview

| File | Calculation | Chronology | Highlights | Calculation omission | Lineage disconnection | Precision tampering | Semantic violation | Double counting |
|---|---|---|---|---|---|---|---|---|
| C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\liquidity_pool_join_precision_tampering.json | ✅ Valid | ✅ Valid | 1 | CLEAN (85.0) | CLEAN (88.0) | SUSPICIOUS LOGIC (63.0) | CLEAN (78.0) | CLEAN (80.0) |
| C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\game_winner_payout_precision_tampering.json | ✅ Valid | ✅ Valid | 0 | CLEAN (74.0) | CLEAN (95.0) | ANOMALY DETECTED (72.0) | CLEAN (78.0) | CLEAN (97.0) |
| C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\staking_reward_fee_precision_tampering.json | ✅ Valid | ✅ Valid | 0 | CLEAN (58.0) | CLEAN (82.0) | ANOMALY DETECTED (88.0) | CONTEXT MISMATCH (70.0) | CLEAN (78.0) |
| C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\token_sale_threshold_precision_tampering.json | ✅ Valid | ✅ Valid | 1 | CALCULATION OMISSION DETECTED (80.0) | CLEAN (74.0) | ANOMALY DETECTED (90.0) | SEMANTIC CAST DETECTED (87.0) | CLEAN (72.0) |
| C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\amm_taker_fee_precision_tampering.json | ✅ Valid | ✅ Valid | 1 | CLEAN (88.0) | CLEAN (92.0) | CLEAN (93.0) | CLEAN (88.0) | CLEAN (88.0) |

## Highlights

### C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\liquidity_pool_join_precision_tampering.json

- Multi-used variables detected, please check CPG for double-counting

### C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\token_sale_threshold_precision_tampering.json

- Multi-used variables detected, please check CPG for double-counting

### C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\numscout\amm_taker_fee_precision_tampering.json

- Multi-used variables detected, please check CPG for double-counting

