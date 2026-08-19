# Summary
- **Verdict**: CLEAN
- **Confidence score**: 88.0

## Audit Summary

The audit was scoped specifically to detect **Lineage Disconnection and Context Substitution**: cases where a properly-computed intermediate variable is silently bypassed in favor of a hardcoded, foreign, or context-mismatched value that merely approximates it, while the legitimately computed sibling is left as an orphaned dead-end.

### Structural Reference Data Cross-Check
- **Leaf variable set**: `[o_36]` only. `o_36` ("Net profit in USDC") is the terminal reported output of the pipeline, consumed by nothing further — this is the expected, singular terminal node of a well-formed DAG, not an orphaned bypass.
- **Name-collision set**: empty. No leaf shares an exact display name with any other variable, so the primary heuristic signal for this attack does not fire.
- Per audit instructions, I additionally performed a manual semantic-role scan (matching by `descriptor.meta`, unit, and position-in-formula) across every `OUTPUT` node (`o_21`–`o_36`) to look for a same-role `INPUT` substituted downstream instead of the computed sibling.

### Full Forward Propagation (Origin_Propagation_Valid check)
I recomputed every operation from true root inputs forward and compared against every stored `OUTPUT` value:

- `op_1` convert(i_9=0.00369452 WBTC, i_4=1 peg) → o_21 = 0.00369452 BTC ✓
- `op_2` convert(o_21, i_1=109800) → o_22 = 405.658296 USDC ✓
- `op_3` convert(i_10=0.02735342 ETH, i_2=4650) → o_23 = 127.193403 USDC ✓
- `op_4` convert(i_12=299.589041 USDT, i_3=0.9998) → o_24 = 299.529123 USDC ✓ (DOWN truncation confirmed)
- `op_5` convert(i_13=0.16910563 ETH, i_2=4650) → o_25 = 786.341179 USDC ✓ (DOWN truncation confirmed)
- `op_6` addBulk(o_22,o_23,i_11,o_24,o_25) → o_26 = 1738.305562 ✓
- `op_7`–`op_12`: each gas amount (i_14..i_19) converted using the ETH/USDC rate **dated to match its own transaction date** (i_5/i_6/i_7/i_8) → o_27..o_32, all reproduce exactly (22.4, 17.3844, 12.93, 17.5032, 17.92, 23.296) ✓
- `op_13` addBulk(o_27..o_32) → o_33 = 111.433600 ✓
- `op_14` scale(o_26, i_20=0.03) → o_34 = 52.149166 (DOWN truncation confirmed) ✓
- `op_15` subtract(o_26, o_33) → o_35 = 1626.871962 ✓
- `op_16` subtract(o_35, o_34) → o_36 = 1574.722796 ✓

Every computed `OUTPUT` node from `o_21` through `o_35` is consumed exactly once (or, in the legitimate reuse case of `o_26`, twice — for both the fee calculation and the gas-deduction subtraction) by the operation that should logically consume it, verified as the literal `resultId` of the prior step, not a stand-in. No computed intermediate is left unconsumed while a same-named or same-role `INPUT` is substituted in its place.

### Contextual Design Check
The pipeline deliberately uses two different rate contexts:
- **Current market rates** (`i_1`, `i_2`, `i_3` dated 2026-06-30) to value accumulated yield balances at report time.
- **Historical, transaction-dated rates** (`i_5`–`i_8`, each dated to match a specific gas-fee transaction) to value the cost of gas paid at the time it was spent.

This dual-context design is internally consistent (every gas conversion's rate date matches its transaction date exactly) and is a legitimate accounting convention (mark-to-market for holdings, historical cost for expenses), not evidence of tampering.

### Conclusion
No exact-match or semantic-role name collision was found pointing to a hardcoded substitute performing the same role as a bypassed computed sibling. Full forward propagation from all 20 root inputs reproduces every stored intermediate and the final `o_36` value bit-for-bit. No computed `OUTPUT` is orphaned in favor of a foreign/hardcoded value; the single leaf (`o_36`) is the graph's legitimate final result. I find no structural evidence of Lineage Disconnection or Context Substitution in this graph.

Confidence is high but not absolute — the semantic-role scan for disguised substitutions is inherently less mechanical than exact-match structural checks, so residual uncertainty remains for any substitution designed to evade even a manual role-based review.