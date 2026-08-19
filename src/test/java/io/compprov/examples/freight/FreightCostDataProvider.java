package io.compprov.examples.freight;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a multi-package freight shipment: weight-based tiered per-pound
 * rate, a distance surcharge, and a fuel surcharge percentage.
 */
public class FreightCostDataProvider {

    public BigDecimal fetchTotalWeightPounds() {
        return new BigDecimal("620");
    }

    /** Upper bound (pounds) of the first weight tier. */
    public BigDecimal fetchTier1CeilingPounds() {
        return new BigDecimal("100");
    }

    /** Upper bound (pounds) of the second weight tier (cumulative). */
    public BigDecimal fetchTier2CeilingPounds() {
        return new BigDecimal("500");
    }

    public BigDecimal fetchTier1RatePerPound() {
        return new BigDecimal("0.85");
    }

    public BigDecimal fetchTier2RatePerPound() {
        return new BigDecimal("0.60");
    }

    public BigDecimal fetchTier3RatePerPound() {
        return new BigDecimal("0.40");
    }

    public BigDecimal fetchDistanceMiles() {
        return new BigDecimal("450");
    }

    public BigDecimal fetchDistanceSurchargeRatePerMile() {
        return new BigDecimal("0.15");
    }

    public BigDecimal fetchFuelSurchargeRate() {
        return new BigDecimal("0.09");
    }
}
