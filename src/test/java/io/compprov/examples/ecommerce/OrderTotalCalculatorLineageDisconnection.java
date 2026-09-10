package io.compprov.examples.ecommerce;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.wrappers.WrappedBigDecimal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

public class OrderTotalCalculatorLineageDisconnection {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("E-commerce: order total")));
        OrderDataProvider dp = new OrderDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("1.0"));

        final List<WrappedBigDecimal> lineTotals = new ArrayList<>();
        for (var line : dp.fetchOrderLines()) {
            var unitPrice = ctx.wrapBigDecimal(line.unitPrice(), descriptor("Unit price [%s]".formatted(line.sku())));
            var quantity = ctx.wrapBigDecimal(line.quantity(), descriptor("Quantity [%s]".formatted(line.sku())));
            lineTotals.add(unitPrice.multiply(quantity, mc, descriptor("Line total [%s]".formatted(line.sku()))));
        }
        final var subtotal = lineTotals.get(0).addBulk(lineTotals.subList(1, lineTotals.size()), mc, descriptor("Subtotal"));

        final var discount1Rate = ctx.wrapBigDecimal(dp.fetchDiscount1Rate(), descriptor("Discount rate (SAVE10)"));
        final var discount1Multiplier = one.subtract(discount1Rate, mc, descriptor("Discount multiplier (SAVE10)"));
        final var afterDiscount1 = subtotal.multiply(discount1Multiplier, mc, descriptor("Subtotal after SAVE10"));

        final var discount2Rate = ctx.wrapBigDecimal(dp.fetchDiscount2Rate(), descriptor("Discount rate (LOYALTY5)"));
        final var discount2Multiplier = one.subtract(discount2Rate, mc, descriptor("Discount multiplier (LOYALTY5)"));
        final var discountedSubtotal = afterDiscount1.multiply(discount2Multiplier, mc, descriptor("Subtotal after LOYALTY5"));

        final var shipping = ctx.wrapBigDecimal(dp.fetchShippingFee(), descriptor("Shipping fee"));
        final var taxableAmount = discountedSubtotal.add(shipping, mc, descriptor("Taxable amount"));

        final var taxRate = ctx.wrapBigDecimal(dp.fetchTaxRate(), descriptor("Tax rate (8%, California)"));
        final var salesTax = taxableAmount.multiply(taxRate, mc, descriptor("Sales tax"));

        //disconnection here
        final var tax = ctx.wrapBigDecimal(salesTax.getValue().multiply(BigDecimal.valueOf(0.9)), descriptor("Tax"));

        // === Reported order total: a disconnected literal standing in for taxableAmount + tax ===
        final var orderTotal = taxableAmount.add(tax, mc, descriptor("Order total"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
