package io.compprov.examples.defects4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Data modelled on Defects4J Math-57 ({@code KMeansPlusPlusClustererTest.testSmallDistances}):
 * many identical points at 0 and one unique point at 1, with distances scaled by 0.001. The test
 * uses 10,000 repeated points; 8 are used here (plus the already-chosen first centre), which
 * leaves the defect unchanged. The first centre and the uniform draw are fixed instead of random.
 */
public class Math57KMeansSeedingDataProvider {

    public BigDecimal fetchFirstCenter() {
        return new BigDecimal("0");
    }

    public List<BigDecimal> fetchRemainingPoints() {
        final List<BigDecimal> points = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            points.add(new BigDecimal("0"));
        }
        points.add(new BigDecimal("1"));
        return points;
    }

    public BigDecimal fetchDistanceScale() {
        return new BigDecimal("0.001");
    }

    public BigDecimal fetchUniformDraw() {
        return new BigDecimal("0.73");
    }
}
