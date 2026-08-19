# Compprov Analytics Summary

Generated: 2026-08-13T07:42:07.131340Z[UTC]

## Overview

| File | Calculation | Chronology | Highlights | Calculation omission | Lineage disconnection | Precision tampering | Semantic violation | Double counting |
|---|---|---|---|---|---|---|---|---|
| C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\gptscan\vault_first_deposit.json | ✅ Valid | ✅ Valid | 2 | CALCULATION OMISSION DETECTED (88.0) | CLEAN (88.0) | SUSPICIOUS LOGIC (63.0) | SEMANTIC CAST DETECTED (66.0) | CLEAN (66.0) |
| C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\gptscan\transfer_checkpoint.json | ✅ Valid | ✅ Valid | 0 | CLEAN (72.0) | CLEAN (88.0) | CLEAN (92.0) | CONTEXT MISMATCH (63.0) | CLEAN (88.0) |
| C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\gptscan\collateral_valuation.json | ✅ Valid | ✅ Valid | 0 | CLEAN (88.0) | CLEAN (93.0) | CLEAN (90.0) | SEMANTIC CAST DETECTED (78.0) | CLEAN (92.0) |
| C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\gptscan\governance_vote_weight.json | ✅ Valid | ✅ Valid | 0 | CLEAN (85.0) | CLEAN (93.0) | CLEAN (97.0) | SEMANTIC CAST DETECTED (74.0) | CLEAN (97.0) |
| C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\gptscan\interest_accrual_distribution.json | ✅ Valid | ✅ Valid | 2 | CALCULATION OMISSION DETECTED (90.0) | LINEAGE BREAK DETECTED (89.0) | ANOMALY DETECTED (88.0) | SEMANTIC CAST DETECTED (90.0) | DOUBLE COUNTING DETECTED (83.0) |

## Highlights

### C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\gptscan\vault_first_deposit.json

- Multiple leaves detected: 2 please make sure the CPG has no gaps and substitutions
- Multi-used variables detected, please check CPG for double-counting

### C:\projs\LOCAL\compprov\compprov-plugin-example\samples\snapshots\gptscan\interest_accrual_distribution.json

- Multiple leaves detected: 2 please make sure the CPG has no gaps and substitutions
- Multi-used variables detected, please check CPG for double-counting

