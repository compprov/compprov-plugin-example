package io.compprov.tools.distance;

import io.compprov.core.meta.Descriptor;

public interface DistanceCalculator<T> {
    /**
     *
     * @param o1
     * @param o2
     * @return 0 highest similarity, 1 max distance
     */
    public double distance(T o1, T o2);
}
