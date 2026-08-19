package io.compprov.examples.spectroscopy;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a UV-Vis calibration series of the same dye solution at three
 * concentrations, measured in a fixed-path-length cuvette.
 */
public class BeerLambertDataProvider {

    /** Molar absorptivity, ε (L/(mol·cm)). */
    public BigDecimal fetchMolarAbsorptivity() {
        return new BigDecimal("15000");
    }

    /** Cuvette path length, l (cm). */
    public BigDecimal fetchPathLength() {
        return new BigDecimal("1.00");
    }

    /** Calibration concentration 1 (mol/L). */
    public BigDecimal fetchConcentration1() {
        return new BigDecimal("0.00001");
    }

    /** Calibration concentration 2 (mol/L). */
    public BigDecimal fetchConcentration2() {
        return new BigDecimal("0.00002");
    }

    /** Calibration concentration 3 (mol/L). */
    public BigDecimal fetchConcentration3() {
        return new BigDecimal("0.00004");
    }
}
