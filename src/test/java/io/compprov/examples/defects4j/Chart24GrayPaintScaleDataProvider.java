package io.compprov.examples.defects4j;

import java.math.BigDecimal;

/**
 * Data from Defects4J Chart-24 ({@code GrayPaintScaleTests.testGetPaint}): a lookup value of −0.5,
 * below the default [0, 1] range, which should map to black.
 */
public class Chart24GrayPaintScaleDataProvider {

    public BigDecimal fetchValue() {
        return new BigDecimal("-0.5");
    }

    public BigDecimal fetchLowerBound() {
        return BigDecimal.ZERO;
    }

    public BigDecimal fetchUpperBound() {
        return BigDecimal.ONE;
    }
}
