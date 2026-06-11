package io.compprov.tools.difference;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigIntegerDifferenceCalculator implements NormalizedDifferenceCalculator<BigInteger> {
    @Override
    public double normalizedDifference(BigInteger o1, BigInteger o2) {
        final var max = new BigDecimal(o1.abs().max(o2.abs()));
        return (max.equals(BigInteger.ZERO))
                ? 0 : BigDecimal.ONE.min(new BigDecimal(o1.subtract(o2).abs()).divide(max, 8, RoundingMode.HALF_DOWN)).doubleValue();
    }
}
