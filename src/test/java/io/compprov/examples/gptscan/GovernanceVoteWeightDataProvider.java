package io.compprov.examples.gptscan;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for tallying a governance vote, modeled after the "Vote Manipulation
 * by Flashloan" scenario in Sun et al., GPTScan (Table 1): the same voter's balance, either a
 * verified snapshot taken at proposal creation (time-locked, unaffected by same-transaction
 * borrowing) or the raw current balance (which a flash loan can inflate for the duration of a
 * single transaction).
 */
public class GovernanceVoteWeightDataProvider {

    public BigInteger fetchExistingVotesForProposal() {
        return BigInteger.valueOf(250_000);
    }

    /** Balance at the historical, time-locked snapshot block used for eligibility. */
    public BigInteger fetchVerifiedSnapshotBalance() {
        return BigInteger.valueOf(10_000);
    }

    /** Block number at which the proposal was created and voting power should be snapshotted. */
    public long fetchProposalSnapshotBlock() {
        return 19_000_000L;
    }

    /** Real holding (10,000) plus a same-transaction flash loan of 990,000. */
    public BigInteger fetchFlashloanInflatedBalance() {
        return BigInteger.valueOf(1_000_000);
    }

    /** Block number at which this vote transaction executes — 50 blocks after proposal creation. */
    public long fetchVoteCastBlock() {
        return 19_000_050L;
    }
}
