package io.compprov.examples.gptscan;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for a vault's first deposit, modeled after the "Risky First Deposit"
 * scenario in Sun et al., GPTScan (Fig. 1, Code4rena {@code 2021-11-yaxis}): a deposit made while
 * total share supply is zero, and the small, permanently-locked liquidity floor (mirroring
 * Uniswap V2's {@code MINIMUM_LIQUIDITY} constant) that real vaults burn on the first deposit to
 * prevent the depositor from single-handedly setting the initial share price.
 */
public class VaultFirstDepositDataProvider {

    public BigInteger fetchFirstDepositAmount() {
        return BigInteger.valueOf(1_000_000);
    }

    public BigInteger fetchMinimumLiquidityConstant() {
        return BigInteger.valueOf(1_000);
    }

    /** Total share supply before this deposit — zero, since this is the vault's first deposit. */
    public BigInteger fetchPreviousTotalShareSupply() {
        return BigInteger.ZERO;
    }
}
