package io.compprov.examples.defects4j;

import java.math.BigDecimal;

/**
 * Data from Defects4J Math-18 ({@code CMAESOptimizerTest.testFitAccuracyDependsOnBoundary}, "optimum
 * near the upper bound" case): the fitness optimum 11.1 with bounds [−5e16, 20].
 */
public class Math18CmaesBoundsDataProvider {

    public BigDecimal fetchCandidate() {
        return new BigDecimal("11.1");
    }

    public BigDecimal fetchLowerBound() {
        return new BigDecimal("-5E16");
    }

    public BigDecimal fetchUpperBound() {
        return new BigDecimal("20");
    }
}
