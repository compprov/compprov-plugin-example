package io.compprov.examples.escapevelocity;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for computing Earth's escape velocity at the surface and at a
 * low-Earth-orbit altitude.
 */
public class EscapeVelocityDataProvider {

    /** Newtonian gravitational constant, G (N·m²/kg²). */
    public BigDecimal fetchGravitationalConstant() {
        return new BigDecimal("0.00000000006674");
    }

    /** Mass of Earth, M (kg). */
    public BigDecimal fetchPlanetMass() {
        return new BigDecimal("5972000000000000000000000");
    }

    /** Mean radius of Earth, r (m). */
    public BigDecimal fetchPlanetRadius() {
        return new BigDecimal("6371000");
    }

    /** Altitude above the surface for the comparison calculation, e.g. low Earth orbit (m). */
    public BigDecimal fetchAltitude() {
        return new BigDecimal("400000");
    }
}
