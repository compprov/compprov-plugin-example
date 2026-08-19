package io.compprov.examples.commission;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a three-tier progressive monthly sales commission schedule.
 */
public class SalesCommissionDataProvider {

    public BigDecimal fetchMonthlyRevenue() {
        return new BigDecimal("135000.00");
    }

    /** Upper bound of the first commission tier. */
    public BigDecimal fetchTier1Ceiling() {
        return new BigDecimal("50000.00");
    }

    /** Upper bound of the second commission tier (cumulative revenue). */
    public BigDecimal fetchTier2Ceiling() {
        return new BigDecimal("100000.00");
    }

    public BigDecimal fetchTier1Rate() {
        return new BigDecimal("0.05");
    }

    public BigDecimal fetchTier2Rate() {
        return new BigDecimal("0.08");
    }

    public BigDecimal fetchTier3Rate() {
        return new BigDecimal("0.12");
    }
}
