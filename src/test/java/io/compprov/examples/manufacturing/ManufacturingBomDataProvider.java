package io.compprov.examples.manufacturing;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a bill-of-materials rollup of a single assembly: five component
 * line items (unit cost × quantity-per-assembly), per-unit labor and overhead, and a production
 * run quantity.
 */
public class ManufacturingBomDataProvider {

    public BigDecimal fetchCircuitBoardUnitCost() {
        return new BigDecimal("14.50");
    }

    public BigDecimal fetchCircuitBoardQtyPerAssembly() {
        return new BigDecimal("1");
    }

    public BigDecimal fetchEnclosureUnitCost() {
        return new BigDecimal("6.25");
    }

    public BigDecimal fetchEnclosureQtyPerAssembly() {
        return new BigDecimal("1");
    }

    public BigDecimal fetchConnectorUnitCost() {
        return new BigDecimal("0.85");
    }

    public BigDecimal fetchConnectorQtyPerAssembly() {
        return new BigDecimal("4");
    }

    public BigDecimal fetchFastenerUnitCost() {
        return new BigDecimal("0.05");
    }

    public BigDecimal fetchFastenerQtyPerAssembly() {
        return new BigDecimal("12");
    }

    public BigDecimal fetchCableUnitCost() {
        return new BigDecimal("2.10");
    }

    public BigDecimal fetchCableQtyPerAssembly() {
        return new BigDecimal("2");
    }

    public BigDecimal fetchLaborCostPerUnit() {
        return new BigDecimal("8.00");
    }

    public BigDecimal fetchOverheadCostPerUnit() {
        return new BigDecimal("3.50");
    }

    public BigDecimal fetchProductionRunQuantity() {
        return new BigDecimal("5000");
    }
}
