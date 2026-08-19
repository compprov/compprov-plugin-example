package io.compprov.examples.utility;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Tiered residential electricity bill: $0.10/kWh for the first 500 kWh, $0.14/kWh for the next
 * 500 kWh, $0.18/kWh above that, plus a fixed monthly service charge.
 */
public class UtilityBillCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Utility: tiered residential electricity bill")));
        UtilityBillDataProvider dp = new UtilityBillDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var zero = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Zero (tier floor)"));

        final var usage = ctx.wrapBigDecimal(dp.fetchUsageKwh(), descriptor("Monthly usage (kWh)"));
        final var tier1Ceiling = ctx.wrapBigDecimal(dp.fetchTier1CeilingKwh(), descriptor("Tier 1 ceiling (500 kWh)"));
        final var tier2Ceiling = ctx.wrapBigDecimal(dp.fetchTier2CeilingKwh(), descriptor("Tier 2 ceiling (1000 kWh)"));
        final var tier1Rate = ctx.wrapBigDecimal(dp.fetchTier1RatePerKwh(), descriptor("Tier 1 rate ($0.10/kWh)"));
        final var tier2Rate = ctx.wrapBigDecimal(dp.fetchTier2RatePerKwh(), descriptor("Tier 2 rate ($0.14/kWh)"));
        final var tier3Rate = ctx.wrapBigDecimal(dp.fetchTier3RatePerKwh(), descriptor("Tier 3 rate ($0.18/kWh)"));

        // === Tiered usage split ===
        final var usageThroughTier1 = usage.min(tier1Ceiling, descriptor("Usage through tier 1"));
        final var usageThroughTier2 = usage.min(tier2Ceiling, descriptor("Usage through tier 2"));
        final var tier2Usage = usageThroughTier2.subtract(usageThroughTier1, mc, descriptor("Tier 2 usage portion"));
        final var tier3Usage = usage.subtract(usageThroughTier2, mc, descriptor("Tier 3 usage portion (uncapped)"))
                .max(zero, descriptor("Tier 3 usage portion (floored)"));

        // === Cost per tier ===
        final var tier1Cost = usageThroughTier1.multiply(tier1Rate, mc, descriptor("Tier 1 cost"));
        final var tier2Cost = tier2Usage.multiply(tier2Rate, mc, descriptor("Tier 2 cost"));
        final var tier3Cost = tier3Usage.multiply(tier3Rate, mc, descriptor("Tier 3 cost"));
        final var energyCost = tier1Cost.addBulk(List.of(tier2Cost, tier3Cost), mc, descriptor("Total energy cost"));

        // === Total bill ===
        final var serviceCharge = ctx.wrapBigDecimal(dp.fetchMonthlyServiceCharge(), descriptor("Monthly service charge"));
        final var totalBill = energyCost.add(serviceCharge, mc, descriptor("Total bill"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
