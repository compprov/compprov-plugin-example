package io.compprov.examples.billsplit;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a restaurant bill split among a group of diners: four menu
 * items, sales tax, and a tip on the pre-tax subtotal, split evenly.
 */
public class BillSplitDataProvider {

    public static final int DINER_COUNT = 4;

    public BigDecimal fetchEntree1Price() {
        return new BigDecimal("24.00");
    }

    public BigDecimal fetchEntree2Price() {
        return new BigDecimal("19.50");
    }

    public BigDecimal fetchAppetizerPrice() {
        return new BigDecimal("12.00");
    }

    public BigDecimal fetchDessertPrice() {
        return new BigDecimal("9.00");
    }

    public BigDecimal fetchSalesTaxRate() {
        return new BigDecimal("0.08");
    }

    public BigDecimal fetchTipRate() {
        return new BigDecimal("0.20");
    }

    public BigDecimal fetchDinerCount() {
        return new BigDecimal(DINER_COUNT);
    }
}
