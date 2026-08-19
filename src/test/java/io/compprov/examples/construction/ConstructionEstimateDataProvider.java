package io.compprov.examples.construction;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a residential construction project bid: materials line items,
 * labor by trade, overhead percentage, and profit margin percentage.
 */
public class ConstructionEstimateDataProvider {

    public BigDecimal fetchLumberCost() {
        return new BigDecimal("18500.00");
    }

    public BigDecimal fetchConcreteCost() {
        return new BigDecimal("9200.00");
    }

    public BigDecimal fetchRoofingMaterialsCost() {
        return new BigDecimal("6300.00");
    }

    public BigDecimal fetchElectricalMaterialsCost() {
        return new BigDecimal("4100.00");
    }

    public BigDecimal fetchCarpentryHours() {
        return new BigDecimal("180");
    }

    public BigDecimal fetchCarpentryRate() {
        return new BigDecimal("55.00");
    }

    public BigDecimal fetchElectricalHours() {
        return new BigDecimal("60");
    }

    public BigDecimal fetchElectricalRate() {
        return new BigDecimal("75.00");
    }

    public BigDecimal fetchOverheadRate() {
        return new BigDecimal("0.10");
    }

    public BigDecimal fetchProfitMarginRate() {
        return new BigDecimal("0.15");
    }
}
