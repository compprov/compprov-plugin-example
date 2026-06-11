package io.compprov.tools.difference;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalDifferenceCalculator implements NormalizedDifferenceCalculator<BigDecimal> {
    @Override
    public double normalizedDifference(BigDecimal o1, BigDecimal o2) {
        final var max = o1.abs().max(o2.abs());
        return (max.compareTo(BigDecimal.ZERO) == 0)
                ? 0 : BigDecimal.ONE.min(o1.subtract(o2).abs().divide(max, 8, RoundingMode.HALF_DOWN)).doubleValue();
    }
}
