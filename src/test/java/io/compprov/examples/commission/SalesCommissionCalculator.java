package io.compprov.examples.commission;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Three-tier progressive monthly sales commission: 5% on the first $50,000 of revenue, 8% on the
 * next $50,000, and 12% on any revenue above $100,000.
 */
public class SalesCommissionCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Commission: three-tier monthly sales commission")));
        SalesCommissionDataProvider dp = new SalesCommissionDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var zero = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Zero (tier floor)"));

        final var revenue = ctx.wrapBigDecimal(dp.fetchMonthlyRevenue(), descriptor("Monthly revenue"));
        final var tier1Ceiling = ctx.wrapBigDecimal(dp.fetchTier1Ceiling(), descriptor("Tier 1 ceiling ($50,000)"));
        final var tier2Ceiling = ctx.wrapBigDecimal(dp.fetchTier2Ceiling(), descriptor("Tier 2 ceiling ($100,000)"));
        final var tier1Rate = ctx.wrapBigDecimal(dp.fetchTier1Rate(), descriptor("Tier 1 rate (5%)"));
        final var tier2Rate = ctx.wrapBigDecimal(dp.fetchTier2Rate(), descriptor("Tier 2 rate (8%)"));
        final var tier3Rate = ctx.wrapBigDecimal(dp.fetchTier3Rate(), descriptor("Tier 3 rate (12%)"));

        // === Tiered revenue split ===
        final var revenueThroughTier1 = revenue.min(tier1Ceiling, descriptor("Revenue through tier 1"));
        final var revenueThroughTier2 = revenue.min(tier2Ceiling, descriptor("Revenue through tier 2"));
        final var tier2Portion = revenueThroughTier2.subtract(revenueThroughTier1, mc, descriptor("Tier 2 portion"));
        final var tier3Portion = revenue.subtract(revenueThroughTier2, mc, descriptor("Tier 3 portion (uncapped)"))
                .max(zero, descriptor("Tier 3 portion (floored)"));

        // === Commission per tier ===
        final var tier1Commission = revenueThroughTier1.multiply(tier1Rate, mc, descriptor("Tier 1 commission"));
        final var tier2Commission = tier2Portion.multiply(tier2Rate, mc, descriptor("Tier 2 commission"));
        final var tier3Commission = tier3Portion.multiply(tier3Rate, mc, descriptor("Tier 3 commission"));

        // === Total commission ===
        final var totalCommission = tier1Commission.addBulk(
                List.of(tier2Commission, tier3Commission), mc, descriptor("Total commission"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
