package io.compprov.examples.defects4j;

import java.math.BigDecimal;

/**
 * Test data from Defects4J Math-11 ({@code MultivariateNormalDistributionTest.testUnivariateDistribution}):
 * a 1-dimensional normal with mean −1.5 and covariance [[1]]. The test evaluates random points;
 * here the density is evaluated at x = 0.
 */
public class Math11MvnDensityDataProvider {

    public BigDecimal fetchDimension() {
        return new BigDecimal("1");
    }

    public BigDecimal fetchMean() {
        return new BigDecimal("-1.5");
    }

    public BigDecimal fetchCovariance() {
        return new BigDecimal("1");
    }

    public BigDecimal fetchPoint() {
        return new BigDecimal("0");
    }

    public BigDecimal fetchPi() {
        return new BigDecimal("3.141592653589793");
    }
}
