package io.compprov.examples.invoice;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for settling a supplier invoice under "2/10 net 30" terms, including
 * a three-way match (purchase order vs. goods receipt vs. invoice) quantity variance adjustment.
 */
public class InvoiceSettlementDataProvider {

    public BigDecimal fetchInvoiceSubtotal() {
        return new BigDecimal("48000.00");
    }

    /** Unit price used for the three-way match quantity variance adjustment. */
    public BigDecimal fetchUnitPrice() {
        return new BigDecimal("120.00");
    }

    /** Quantity actually received per the goods receipt (three-way match). */
    public BigDecimal fetchQuantityReceived() {
        return new BigDecimal("398");
    }

    /** Quantity billed per the supplier invoice. */
    public BigDecimal fetchQuantityInvoiced() {
        return new BigDecimal("400");
    }

    public BigDecimal fetchTaxRate() {
        return new BigDecimal("0.07");
    }

    /** "2/10 net 30": 2% discount if paid within the discount window. */
    public BigDecimal fetchEarlyPaymentDiscountRate() {
        return new BigDecimal("0.02");
    }
}
