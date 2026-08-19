package io.compprov.examples.numscout;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for a staking-pool developer fee deduction. Numbers are taken
 * directly from the "Operator Order Issue" example in Chen et al., NumScout (Fig. 3): an
 * accumulated reward of 199 units, with a 10% developer fee.
 */
public class StakingRewardFeeDataProvider {

    public BigInteger fetchAccumulatedReward() {
        return BigInteger.valueOf(199);
    }

    public BigInteger fetchDeveloperFeePercent() {
        return BigInteger.valueOf(10);
    }

    public BigInteger fetchPercentDenominator() {
        return BigInteger.valueOf(100);
    }
}
