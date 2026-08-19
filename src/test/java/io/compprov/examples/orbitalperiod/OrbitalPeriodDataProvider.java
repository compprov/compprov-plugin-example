package io.compprov.examples.orbitalperiod;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for computing the orbital period of a geostationary satellite
 * around Earth via Kepler's third law.
 */
public class OrbitalPeriodDataProvider {

    /** Orbital radius, r, measured from Earth's center (m) — geostationary orbit. */
    public BigDecimal fetchOrbitalRadius() {
        return new BigDecimal("42164000");
    }

    /** Newtonian gravitational constant, G (N·m²/kg²). */
    public BigDecimal fetchGravitationalConstant() {
        return new BigDecimal("0.00000000006674");
    }

    /** Mass of Earth, M (kg). */
    public BigDecimal fetchCentralBodyMass() {
        return new BigDecimal("5972000000000000000000000");
    }
}
