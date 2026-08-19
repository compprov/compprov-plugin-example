package io.compprov.examples.idealgas;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for an ideal gas law scenario: a fixed amount of gas held at
 * constant temperature, first at an initial volume, then compressed to a smaller volume.
 */
public class IdealGasLawDataProvider {

    /** Amount of substance (mol). */
    public BigDecimal fetchMoles() {
        return new BigDecimal("2.5");
    }

    /** Ideal gas constant, R (L·atm/(mol·K)). */
    public BigDecimal fetchGasConstant() {
        return new BigDecimal("0.0821");
    }

    /** Absolute temperature (K), held constant across both volumes (isothermal). */
    public BigDecimal fetchTemperature() {
        return new BigDecimal("298");
    }

    /** Initial container volume (L). */
    public BigDecimal fetchInitialVolume() {
        return new BigDecimal("10");
    }

    /** Volume after compression (L). */
    public BigDecimal fetchCompressedVolume() {
        return new BigDecimal("4");
    }
}
