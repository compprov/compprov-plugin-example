package io.compprov.examples.defects4j;

import java.math.BigDecimal;
import java.util.List;

/**
 * Test data from Defects4J Math-102 ({@code ChiSquareTestTest.testChiSquare}): expected and
 * observed counts over 5 categories whose totals differ (1206 vs 1296).
 */
public class Math102ChiSquareDataProvider {

    public List<BigDecimal> fetchExpected() {
        return List.of(new BigDecimal("485"), new BigDecimal("541"), new BigDecimal("82"),
                new BigDecimal("61"), new BigDecimal("37"));
    }

    public List<BigDecimal> fetchObserved() {
        return List.of(new BigDecimal("500"), new BigDecimal("623"), new BigDecimal("72"),
                new BigDecimal("70"), new BigDecimal("31"));
    }
}
