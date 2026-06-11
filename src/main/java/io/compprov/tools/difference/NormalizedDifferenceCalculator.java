package io.compprov.tools.difference;

@FunctionalInterface
public interface NormalizedDifferenceCalculator<T> {
    /**
     * @param o1
     * @param o2
     * @return 0 - no difference, 1 - absolutely different
     */
    public double normalizedDifference(T o1, T o2);
}
