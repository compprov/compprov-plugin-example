package io.compprov.tools.difference;

public class DoubleDifferenceCalculator implements NormalizedDifferenceCalculator<Double> {
    @Override
    public double normalizedDifference(Double o1, Double o2) {
        final var max = Math.max(Math.abs(o1), Math.abs(o2));
        return (max == 0) ? 0 : Math.min(1, Math.abs(o1 - o2) / max);
    }
}
