package io.compprov.examples.defects4j;

import java.math.BigDecimal;

/**
 * Data from Defects4J Math-72 ({@code BrentSolverTest.testRootEndpoints}): solve sin(x) on [π, 4] with
 * initial guess 3.5, where the lower endpoint is the root. Function values are evaluated in double,
 * as the solver does; 1e-15 is the solver's default function-value accuracy.
 */
public class Math72BrentEndpointRootDataProvider {

    public BigDecimal fetchMin() {
        return BigDecimal.valueOf(Math.PI);
    }

    public BigDecimal fetchInitial() {
        return new BigDecimal("3.5");
    }

    public BigDecimal fetchValueAtMin() {
        return BigDecimal.valueOf(Math.sin(Math.PI));
    }

    public BigDecimal fetchValueAtInitial() {
        return BigDecimal.valueOf(Math.sin(3.5));
    }

    public BigDecimal fetchFunctionValueAccuracy() {
        return new BigDecimal("1E-15");
    }
}
