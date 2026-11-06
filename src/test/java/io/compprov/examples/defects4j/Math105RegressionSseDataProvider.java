package io.compprov.examples.defects4j;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data modelled on Defects4J Math-105 ({@code SimpleRegressionTest.testSSENonNegative}): the
 * test's x values with three evenly spaced, collinear y values. The test's own y values
 * (8915.102, 8919.302, 8923.502) cancel to exactly 0 in DECIMAL64, so y was chosen where the
 * cancellation goes negative in DECIMAL64, as the test's values do in double.
 */
public class Math105RegressionSseDataProvider {

    public List<BigDecimal> fetchX() {
        return List.of(new BigDecimal("110.7178495"), new BigDecimal("110.7264895"), new BigDecimal("110.7351295"));
    }

    public List<BigDecimal> fetchY() {
        return List.of(new BigDecimal("8906.8377"), new BigDecimal("8913.0454"), new BigDecimal("8919.2531"));
    }
}
