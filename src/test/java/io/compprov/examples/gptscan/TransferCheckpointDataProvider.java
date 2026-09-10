package io.compprov.examples.gptscan;

import java.math.BigInteger;
import java.time.Instant;

/**
 * Fixed, reproducible inputs for a token transfer plus reward checkpoint, modeled directly after
 * the "Wrong Checkpoint Order" motivating example in Sun et al., GPTScan (Fig. 2, the Code4rena
 * {@code 2022-04-backd} finding): {@code userCheckpoint()} (line 10-11) must snapshot the sender's
 * balance from before this transfer's balance mutation (line 6-7), not after.
 */
public class TransferCheckpointDataProvider {

    /** Balance-mutation timestamp: GPTScan Fig. 2, line 6-7 ({@code balances[...] -= amount}). */
    private static final Instant TRANSFER_EXECUTED_AT = Instant.parse("2026-09-08T10:15:00Z");

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

    /** When the pre-transfer balance was last valid — one second before the transfer executes. */
    public Instant fetchPreTransferSnapshotAt() {
        return TRANSFER_EXECUTED_AT.minusSeconds(1);
    }

    /** Balance-mutation timestamp: GPTScan Fig. 2, line 6-7. */
    public Instant fetchTransferExecutedAt() {
        return TRANSFER_EXECUTED_AT;
    }

    /**
     * {@code userCheckpoint()} call timestamp: GPTScan Fig. 2, line 10-11 — fired four seconds
     * after the transfer instead of before it, the defect itself.
     */
    public Instant fetchCheckpointRecordedAt() {
        return TRANSFER_EXECUTED_AT.plusSeconds(4);
    }
}
