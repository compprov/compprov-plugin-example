package io.compprov.examples.defects4j;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data from Defects4J Math-55 ({@code Vector3DTest.testCrossProductCancellation}): two nearly parallel
 * vectors whose exact cross product is (−1, 2, 1).
 */
public class Math55CrossProductDataProvider {

    public List<BigDecimal> fetchFirstVector() {
        return List.of(new BigDecimal("9070467121"), new BigDecimal("4535233560"), BigDecimal.ONE);
    }

    public List<BigDecimal> fetchSecondVector() {
        return List.of(new BigDecimal("9070467123"), new BigDecimal("4535233561"), BigDecimal.ONE);
    }
}
