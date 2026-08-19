package io.compprov.examples.billsplit;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Restaurant bill split among a group of diners.
 *
 * <p>Subtotal = sum of menu items. Tip is calculated on the pre-tax subtotal (common convention).
 * Grand total = subtotal + tax + tip, split evenly among the diners.
 */
public class BillSplitCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Bill split: restaurant group check")));
        BillSplitDataProvider dp = new BillSplitDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Menu items ===
        final var entree1 = ctx.wrapBigDecimal(dp.fetchEntree1Price(), descriptor("Entree 1"));
        final var entree2 = ctx.wrapBigDecimal(dp.fetchEntree2Price(), descriptor("Entree 2"));
        final var appetizer = ctx.wrapBigDecimal(dp.fetchAppetizerPrice(), descriptor("Shared appetizer"));
        final var dessert = ctx.wrapBigDecimal(dp.fetchDessertPrice(), descriptor("Shared dessert"));

        final var subtotal = entree1.addBulk(List.of(entree2, appetizer, dessert), mc, descriptor("Pre-tax subtotal"));

        // === Tax and tip (both on pre-tax subtotal) ===
        final var taxRate = ctx.wrapBigDecimal(dp.fetchSalesTaxRate(), descriptor("Sales tax rate (8%)"));
        final var salesTax = subtotal.multiply(taxRate, mc, descriptor("Sales tax"));

        final var tipRate = ctx.wrapBigDecimal(dp.fetchTipRate(), descriptor("Tip rate (20%)"));
        final var tip = subtotal.multiply(tipRate, mc, descriptor("Tip (on pre-tax subtotal)"));

        // === Grand total and even split ===
        final var grandTotal = subtotal.addBulk(List.of(salesTax, tip), mc, descriptor("Grand total"));
        final var dinerCount = ctx.wrapBigDecimal(dp.fetchDinerCount(), descriptor("Diner count"));
        final var perDinerShare = grandTotal.divide(dinerCount, mc, descriptor("Per-diner share"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
