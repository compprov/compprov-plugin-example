package io.compprov.examples.defects4j;

import java.math.BigDecimal;
import java.util.List;

/**
 * Test data from Defects4J Math-77 ({@code ArrayRealVectorTest.testBasicFunctions}): vector
 * v5 = [-4, 0, 3, 1, -6, 3], whose L∞ norm is 6.
 */
public class Math77LInfNormDataProvider {

    public List<BigDecimal> fetchComponents() {
        return List.of(new BigDecimal("-4"), new BigDecimal("0"), new BigDecimal("3"),
                new BigDecimal("1"), new BigDecimal("-6"), new BigDecimal("3"));
    }
}
