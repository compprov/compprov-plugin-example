package io.compprov.examples.defects4j;

import java.math.BigDecimal;

/**
 * Data from Defects4J Math-74 ({@code AdamsMoultonIntegratorTest.polynomial}): TestProblem6
 * (y' = 3t⁵ − y, y(0) = −360) started by a Dormand-Prince 8(5,3) integrator with the test's
 * tolerances (absolute and relative 1e-9) and step bounds (1e-6 to 0.1).
 */
public class Math74RkInitialStepDataProvider {

    public BigDecimal fetchInitialTime() {
        return BigDecimal.ZERO;
    }

    public BigDecimal fetchInitialState() {
        return new BigDecimal("-360.0");
    }

    public BigDecimal fetchAbsoluteTolerance() {
        return new BigDecimal("1E-9");
    }

    public BigDecimal fetchRelativeTolerance() {
        return new BigDecimal("1E-9");
    }

    public BigDecimal fetchMinStep() {
        return new BigDecimal("1E-6");
    }

    public BigDecimal fetchMaxStep() {
        return new BigDecimal("0.1");
    }

    public int fetchOrder() {
        return 8;
    }
}
