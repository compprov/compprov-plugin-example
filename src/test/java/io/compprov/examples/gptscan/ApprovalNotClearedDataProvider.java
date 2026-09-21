package io.compprov.examples.gptscan;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for a stale-allowance drain, modeled after the "Approval Not
 * Cleared" scenario in Sun et al., GPTScan (Table 1): a vault approves a spender for an
 * operation, the spender only pulls part of what it was approved for, and the leftover
 * allowance is never reset to zero afterward.
 */
public class ApprovalNotClearedDataProvider {

    /** Vault's token balance before the spender's operation. */
    public BigInteger fetchVaultTokenBalance() {
        return BigInteger.valueOf(1_000_000);
    }

    /** Amount the vault approves the spender/strategy contract for. */
    public BigInteger fetchApprovedAmount() {
        return BigInteger.valueOf(400_000);
    }

    /** The spender only pulls part of its approved allowance to complete this round's operation. */
    public BigInteger fetchSpenderPullAmount() {
        return BigInteger.valueOf(150_000);
    }
}
