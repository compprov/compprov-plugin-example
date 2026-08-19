package io.compprov.examples.retirement;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a future-value-of-annuity retirement projection: a fixed annual
 * contribution compounded annually at a fixed rate over a fixed number of years.
 */
public class RetirementAnnuityDataProvider {

    public static final int CONTRIBUTION_YEARS = 6;

    public BigDecimal fetchAnnualContribution() {
        return new BigDecimal("6000.00");
    }

    public BigDecimal fetchAnnualGrowthRate() {
        return new BigDecimal("0.07");
    }
}
