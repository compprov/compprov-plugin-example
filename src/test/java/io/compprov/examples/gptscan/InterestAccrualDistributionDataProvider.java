package io.compprov.examples.gptscan;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for accruing then distributing interest on a staked principal,
 * modeled after the "Wrong Interest Rate Order" scenario in Sun et al., GPTScan (Table 1): a
 * rate accumulator that is correctly refreshed each period, and a distribution step that must
 * consume the freshly-refreshed value rather than the value from before this period's accrual.
 */
public class InterestAccrualDistributionDataProvider {

    public BigInteger fetchStakedPrincipal() {
        return BigInteger.valueOf(100_000);
    }

    /** Rate accumulator (basis points) carried over from the end of the previous period. */
    public BigInteger fetchRateBeforeAccrual() {
        return BigInteger.valueOf(500);
    }

    /** Interest earned during this period, still to be folded into the accumulator. */
    public BigInteger fetchPeriodInterestDeltaBps() {
        return BigInteger.valueOf(37);
    }

    public BigInteger fetchBasisPointsDenominator() {
        return BigInteger.valueOf(10_000);
    }

    /** Period index the carried-over rate accumulator was last refreshed at. */
    public int fetchPriorPeriod() {
        return 41;
    }

    /** Period index this accrual/distribution cycle belongs to. */
    public int fetchCurrentPeriod() {
        return 42;
    }
}
