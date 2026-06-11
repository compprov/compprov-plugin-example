package io.compprov.tools.distance;

import io.compprov.core.Snapshot;

public class VariableIdentityDistanceCalculator implements DistanceCalculator<Snapshot.Variable> {

    @Override
    public double distance(Snapshot.Variable v1, Snapshot.Variable v2) {
        if (v1 == v2) {
            return 0;
        }
        if ((v1 == null) || (v2 == null)) {
            return 1;
        }
        if (!v1.track().getValueClass().equals(v2.track().getValueClass())) {
            return 1;
        }

        int score = 0;
        if (v1.track().getDescriptor().getName().trim().equalsIgnoreCase(v2.track().getDescriptor().getName().trim())) {
            score++;
        }
    }
}
