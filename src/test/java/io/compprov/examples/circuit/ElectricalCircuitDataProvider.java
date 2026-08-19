package io.compprov.examples.circuit;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a DC circuit: two resistors in series feeding a parallel pair
 * of resistors, driven by a fixed supply voltage.
 */
public class ElectricalCircuitDataProvider {

    /** Series resistor 1 (Ω). */
    public BigDecimal fetchR1() {
        return new BigDecimal("100");
    }

    /** Series resistor 2 (Ω). */
    public BigDecimal fetchR2() {
        return new BigDecimal("150");
    }

    /** Parallel resistor 3 (Ω). */
    public BigDecimal fetchR3() {
        return new BigDecimal("300");
    }

    /** Parallel resistor 4 (Ω). */
    public BigDecimal fetchR4() {
        return new BigDecimal("600");
    }

    /** Supply voltage (V). */
    public BigDecimal fetchSupplyVoltage() {
        return new BigDecimal("120");
    }
}
