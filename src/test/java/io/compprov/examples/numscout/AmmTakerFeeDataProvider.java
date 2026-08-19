package io.compprov.examples.numscout;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for an AMM taker-fee deduction, modeled after the "Precision Loss
 * Trend" example in Chen et al., NumScout (Fig. 6): a swap amount and a fee rate in basis points,
 * chosen so that the fee does not divide evenly and the rounding direction is observable.
 */
public class AmmTakerFeeDataProvider {

    public BigInteger fetchAmountToTaker() {
        return BigInteger.valueOf(1_000_003L);
    }

    public BigInteger fetchFeeBasisPoints() {
        return BigInteger.valueOf(37);
    }

    public BigInteger fetchBasisPointsDenominator() {
        return BigInteger.valueOf(10_000);
    }
}
