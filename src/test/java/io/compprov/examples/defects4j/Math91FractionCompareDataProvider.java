package io.compprov.examples.defects4j;

import java.math.BigDecimal;

/**
 * Data from Defects4J Math-91 ({@code FractionTest.testCompareTo}): two approximations of π,
 * 1068966896/340262731 ≈ π − 3.07e-18 and 411557987/131002976 ≈ π + 1.936e-17.
 */
public class Math91FractionCompareDataProvider {

    public BigDecimal fetchFirstNumerator() {
        return new BigDecimal("1068966896");
    }

    public BigDecimal fetchFirstDenominator() {
        return new BigDecimal("340262731");
    }

    public BigDecimal fetchSecondNumerator() {
        return new BigDecimal("411557987");
    }

    public BigDecimal fetchSecondDenominator() {
        return new BigDecimal("131002976");
    }
}
