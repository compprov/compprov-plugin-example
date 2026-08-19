package io.compprov.examples.pendulum;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a simple pendulum in the small-angle regime.
 */
public class PendulumPeriodDataProvider {

    /** Pendulum length (m). */
    public BigDecimal fetchLength() {
        return new BigDecimal("2.0");
    }

    /** Standard gravitational acceleration (m/s²). */
    public BigDecimal fetchGravity() {
        return new BigDecimal("9.8");
    }
}
