package io.compprov.examples.freefall;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for an object dropped from rest under gravity, with no air
 * resistance.
 */
public class FreeFallDataProvider {

    /** Drop height (m). */
    public BigDecimal fetchHeight() {
        return new BigDecimal("100");
    }

    /** Standard gravitational acceleration (m/s²). */
    public BigDecimal fetchGravity() {
        return new BigDecimal("9.8");
    }

    /** Mass of the falling object (kg). */
    public BigDecimal fetchMass() {
        return new BigDecimal("2.5");
    }
}
