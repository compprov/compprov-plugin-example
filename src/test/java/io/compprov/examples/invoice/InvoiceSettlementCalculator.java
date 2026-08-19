package io.compprov.examples.invoice;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Supplier invoice settlement under "2/10 net 30" early-payment terms.
 *
 * <p>A three-way match (PO vs. goods receipt vs. invoice) finds the supplier over-billed by a
 * small quantity variance, which is deducted before tax. The early-payment discount is then
 * applied to the adjusted (pre-tax) goods amount only, consistent with standard practice of not
 * discounting tax.
 */
public class InvoiceSettlementCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Invoice: 2/10 net 30 settlement")));
        InvoiceSettlementDataProvider dp = new InvoiceSettlementDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var invoiceSubtotal = ctx.wrapBigDecimal(dp.fetchInvoiceSubtotal(), descriptor("Invoice subtotal (as billed)"));

        // === Three-way match quantity variance ===
        final var quantityInvoiced = ctx.wrapBigDecimal(dp.fetchQuantityInvoiced(), descriptor("Quantity invoiced"));
        final var quantityReceived = ctx.wrapBigDecimal(dp.fetchQuantityReceived(), descriptor("Quantity received (goods receipt)"));
        final var unitPrice = ctx.wrapBigDecimal(dp.fetchUnitPrice(), descriptor("Unit price"));

        final var quantityVariance = quantityInvoiced.subtract(quantityReceived, mc, descriptor("Quantity variance (invoiced − received)"));
        final var varianceAdjustment = quantityVariance.multiply(unitPrice, mc, descriptor("Variance adjustment amount"));
        final var adjustedSubtotal = invoiceSubtotal.subtract(varianceAdjustment, mc, descriptor("Adjusted subtotal (after three-way match)"));

        // === Tax on the adjusted (matched) subtotal ===
        final var taxRate = ctx.wrapBigDecimal(dp.fetchTaxRate(), descriptor("Tax rate (7%)"));
        final var tax = adjustedSubtotal.multiply(taxRate, mc, descriptor("Tax"));
        final var subtotalWithTax = adjustedSubtotal.add(tax, mc, descriptor("Adjusted subtotal plus tax"));

        // === Early-payment discount (2/10 net 30), applied to the adjusted goods amount only ===
        final var discountRate = ctx.wrapBigDecimal(dp.fetchEarlyPaymentDiscountRate(), descriptor("Early-payment discount rate (2%)"));
        final var earlyPaymentDiscount = adjustedSubtotal.multiply(discountRate, mc, descriptor("Early-payment discount"));

        // === Amount remitted ===
        final var amountRemitted = subtotalWithTax.subtract(earlyPaymentDiscount, mc, descriptor("Amount remitted"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
