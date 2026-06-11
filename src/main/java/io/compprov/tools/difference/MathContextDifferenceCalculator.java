package io.compprov.tools.difference;

import java.math.MathContext;

public class MathContextDifferenceCalculator implements NormalizedDifferenceCalculator<MathContext> {
    @Override
    public double normalizedDifference(MathContext o1, MathContext o2) {
        double diff = 0;
        if (o1.getPrecision() != o2.getPrecision()) {
            diff += 0.5;
        }
        if (!o1.getRoundingMode().equals(o2.getRoundingMode())) {
            diff += 0.5;
        }
        return diff;
    }
}
