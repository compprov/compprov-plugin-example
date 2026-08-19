package io.compprov.examples.utility;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a tiered residential electricity bill: three usage tiers plus a
 * fixed monthly service charge.
 */
public class UtilityBillDataProvider {

    public BigDecimal fetchUsageKwh() {
        return new BigDecimal("1180");
    }

    /** Upper bound (kWh) of the first usage tier. */
    public BigDecimal fetchTier1CeilingKwh() {
        return new BigDecimal("500");
    }

    /** Upper bound (kWh) of the second usage tier (cumulative). */
    public BigDecimal fetchTier2CeilingKwh() {
        return new BigDecimal("1000");
    }

    public BigDecimal fetchTier1RatePerKwh() {
        return new BigDecimal("0.10");
    }

    public BigDecimal fetchTier2RatePerKwh() {
        return new BigDecimal("0.14");
    }

    public BigDecimal fetchTier3RatePerKwh() {
        return new BigDecimal("0.18");
    }

    public BigDecimal fetchMonthlyServiceCharge() {
        return new BigDecimal("12.50");
    }
}
