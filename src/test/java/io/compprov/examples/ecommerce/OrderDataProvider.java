package io.compprov.examples.ecommerce;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a 4-line order: unit prices/quantities, two stacked discount
 * codes, a flat (taxable) shipping fee, and a jurisdiction tax rate.
 */
public class OrderDataProvider {

    public record OrderLine(String sku, BigDecimal unitPrice, BigDecimal quantity) {
    }

    public java.util.List<OrderLine> fetchOrderLines() {
        return java.util.List.of(
                new OrderLine("SKU-1001", new BigDecimal("29.99"), new BigDecimal("3")),
                new OrderLine("SKU-2002", new BigDecimal("14.50"), new BigDecimal("2")),
                new OrderLine("SKU-3003", new BigDecimal("59.00"), new BigDecimal("1")),
                new OrderLine("SKU-4004", new BigDecimal("9.99"), new BigDecimal("5")));
    }

    /** First stacked discount code, "SAVE10". */
    public BigDecimal fetchDiscount1Rate() {
        return new BigDecimal("0.10");
    }

    /** Second stacked discount code, "LOYALTY5", applied on top of the first. */
    public BigDecimal fetchDiscount2Rate() {
        return new BigDecimal("0.05");
    }

    public BigDecimal fetchShippingFee() {
        return new BigDecimal("12.50");
    }

    /** Tax rate for the order's actual shipping destination (a high-tax jurisdiction). */
    public BigDecimal fetchTaxRate() {
        return new BigDecimal("0.08");
    }

    /** Tax rate for a different, lower-tax jurisdiction — used by the semantic tamper scenario. */
    public BigDecimal fetchAlternateJurisdictionTaxRate() {
        return new BigDecimal("0.02");
    }

    /** Revenue collected for a partial return, used by the omission scenario. */
    public BigDecimal fetchRestockingFee() {
        return new BigDecimal("15.00");
    }
}
