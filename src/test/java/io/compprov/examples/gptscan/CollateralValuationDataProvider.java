package io.compprov.examples.gptscan;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for valuing a collateral position, modeled after the "Price
 * Manipulation by AMM" scenario in Sun et al., GPTScan (Table 1): the same collateral amount
 * priced either from a verified oracle or from a same-block AMM spot ratio that a large,
 * same-transaction trade has skewed away from fair value.
 */
public class CollateralValuationDataProvider {

    public BigInteger fetchCollateralAmount() {
        return BigInteger.valueOf(1_000);
    }

    /** A trusted, time-weighted price feed, unaffected by single-block trading activity. */
    public BigInteger fetchVerifiedOraclePrice() {
        return BigInteger.valueOf(2_000);
    }

    public BigInteger fetchPoolReserveBase() {
        return BigInteger.valueOf(500);
    }

    /** Skewed by a large same-transaction trade immediately before this valuation reads it. */
    public BigInteger fetchPoolReserveQuote() {
        return BigInteger.valueOf(1_050_000);
    }
}
