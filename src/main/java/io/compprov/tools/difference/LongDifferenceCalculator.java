package io.compprov.tools.difference;

public class LongDifferenceCalculator implements NormalizedDifferenceCalculator<Long> {
    @Override
    public double normalizedDifference(Long o1, Long o2) {
        final var max = Math.max(Math.abs(o1), Math.abs(o2));
        return (max == 0) ? 0 : Math.min((double) Math.abs(o1 - o2) / max, 1);
    }
}
