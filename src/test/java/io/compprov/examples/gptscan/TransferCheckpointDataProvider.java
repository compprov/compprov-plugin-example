package io.compprov.examples.gptscan;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for a token transfer plus reward checkpoint, modeled directly after
 * the "Wrong Checkpoint Order" motivating example in Sun et al., GPTScan (Fig. 2, the Code4rena
 * {@code 2022-04-backd} finding): a checkpoint that must snapshot the sender's balance from
 * before this transfer, not after.
 */
public class TransferCheckpointDataProvider {

    public BigInteger fetchSenderBalanceBefore() {
        return BigInteger.valueOf(10_000);
    }

    public BigInteger fetchTransferAmount() {
        return BigInteger.valueOf(3_000);
    }

    /** Reward accrual rate applied to whichever balance the checkpoint captures. */
    public BigInteger fetchRewardRatePerToken() {
        return BigInteger.valueOf(2);
    }
}
