package io.compprov.examples.heatconduction;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for steady-state 1-D heat conduction through a two-layer wall
 * (brick exterior wythe + fiberglass insulation), from a warm interior to a cold exterior.
 */
public class HeatConductionDataProvider {

    /** Interior temperature (°C). */
    public BigDecimal fetchInteriorTemp() {
        return new BigDecimal("22.0");
    }

    /** Exterior temperature (°C). */
    public BigDecimal fetchExteriorTemp() {
        return new BigDecimal("5.0");
    }

    /** Wall area, common to both layers (m²). */
    public BigDecimal fetchWallArea() {
        return new BigDecimal("10.0");
    }

    /** Thermal conductivity of layer 1, brick (W/(m·K)). */
    public BigDecimal fetchLayer1Conductivity() {
        return new BigDecimal("0.72");
    }

    /** Thickness of layer 1, brick (m). */
    public BigDecimal fetchLayer1Thickness() {
        return new BigDecimal("0.10");
    }

    /** Thermal conductivity of layer 2, fiberglass insulation (W/(m·K)). */
    public BigDecimal fetchLayer2Conductivity() {
        return new BigDecimal("0.04");
    }

    /** Thickness of layer 2, fiberglass insulation (m). */
    public BigDecimal fetchLayer2Thickness() {
        return new BigDecimal("0.05");
    }
}
