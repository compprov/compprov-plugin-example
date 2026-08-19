package io.compprov.examples.collision;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a 1-D two-body elastic collision, chosen so total mass divides
 * evenly and the resulting velocities are exact decimals.
 */
public class ElasticCollisionDataProvider {

    /** Mass of body 1 (kg). */
    public BigDecimal fetchMass1() {
        return new BigDecimal("3.0");
    }

    /** Initial velocity of body 1 (m/s). */
    public BigDecimal fetchVelocity1() {
        return new BigDecimal("4.0");
    }

    /** Mass of body 2 (kg). */
    public BigDecimal fetchMass2() {
        return new BigDecimal("5.0");
    }

    /** Initial velocity of body 2 (m/s), moving in the opposite direction. */
    public BigDecimal fetchVelocity2() {
        return new BigDecimal("-2.0");
    }
}
