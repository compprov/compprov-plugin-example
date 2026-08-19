package io.compprov.examples.populationgrowth;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for continuous exponential population growth of a bacterial
 * culture.
 */
public class PopulationGrowthDataProvider {

    /** Initial population count, N0 (cells). */
    public BigDecimal fetchInitialPopulation() {
        return new BigDecimal("1000000");
    }

    /** Continuous growth rate, r (per hour). */
    public BigDecimal fetchGrowthRate() {
        return new BigDecimal("0.03");
    }

    /** First time checkpoint (hours). */
    public BigDecimal fetchTime1() {
        return new BigDecimal("5");
    }

    /** Second time checkpoint (hours). */
    public BigDecimal fetchTime2() {
        return new BigDecimal("10");
    }

    /** Third time checkpoint (hours). */
    public BigDecimal fetchTime3() {
        return new BigDecimal("20");
    }
}
