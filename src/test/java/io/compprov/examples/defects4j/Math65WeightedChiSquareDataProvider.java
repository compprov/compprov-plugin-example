package io.compprov.examples.defects4j;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data from Defects4J Math-65 ({@code LevenbergMarquardtOptimizerTest.testCircleFitting}): the test's five
 * circle points, the fitted centre it asserts, and the weight 2 it uses for the weighted refit.
 */
public class Math65WeightedChiSquareDataProvider {

    public List<BigDecimal> fetchPointsX() {
        return List.of(new BigDecimal("30.0"), new BigDecimal("50.0"), new BigDecimal("110.0"), new BigDecimal("35.0"), new BigDecimal("45.0"));
    }

    public List<BigDecimal> fetchPointsY() {
        return List.of(new BigDecimal("68.0"), new BigDecimal("-6.0"), new BigDecimal("-20.0"), new BigDecimal("15.0"), new BigDecimal("97.0"));
    }

    public BigDecimal fetchCenterX() {
        return new BigDecimal("96.07590211815305");
    }

    public BigDecimal fetchCenterY() {
        return new BigDecimal("48.13516790438953");
    }

    public BigDecimal fetchWeight() {
        return new BigDecimal("2.0");
    }

    public int fetchParameterCount() {
        return 2;
    }
}
