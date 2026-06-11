package io.compprov.tools.distance;

public abstract class IdBasedDistanceCalculator<T> implements DistanceCalculator<T> {
    @Override
    public double distance(T o1, T o2) {
        if (o1 == o2) {
            return 0;
        }
        if ((o1 == null) || (o2 == null)) {
            return 1;
        }
        if (extractId(o1).equals(extractId(o2))) {
            return 0;
        }
        return 1;
    }

    public abstract String extractId(T o);
}
