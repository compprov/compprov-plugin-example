package io.compprov.examples.freight;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Freight cost for a multi-package shipment: a weight-based tiered per-pound rate, plus a
 * distance surcharge, plus a fuel surcharge percentage applied to the pre-fuel subtotal.
 */
public class FreightCostCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Freight: multi-package shipment cost")));
        FreightCostDataProvider dp = new FreightCostDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var zero = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Zero (tier floor)"));

        final var totalWeight = ctx.wrapBigDecimal(dp.fetchTotalWeightPounds(), descriptor("Total shipment weight (lbs)"));
        final var tier1Ceiling = ctx.wrapBigDecimal(dp.fetchTier1CeilingPounds(), descriptor("Tier 1 ceiling (100 lbs)"));
        final var tier2Ceiling = ctx.wrapBigDecimal(dp.fetchTier2CeilingPounds(), descriptor("Tier 2 ceiling (500 lbs)"));
        final var tier1Rate = ctx.wrapBigDecimal(dp.fetchTier1RatePerPound(), descriptor("Tier 1 rate ($0.85/lb)"));
        final var tier2Rate = ctx.wrapBigDecimal(dp.fetchTier2RatePerPound(), descriptor("Tier 2 rate ($0.60/lb)"));
        final var tier3Rate = ctx.wrapBigDecimal(dp.fetchTier3RatePerPound(), descriptor("Tier 3 rate ($0.40/lb)"));

        // === Weight-based tiered cost ===
        final var weightThroughTier1 = totalWeight.min(tier1Ceiling, descriptor("Weight through tier 1"));
        final var weightThroughTier2 = totalWeight.min(tier2Ceiling, descriptor("Weight through tier 2"));
        final var tier2Weight = weightThroughTier2.subtract(weightThroughTier1, mc, descriptor("Tier 2 weight portion"));
        final var tier3Weight = totalWeight.subtract(weightThroughTier2, mc, descriptor("Tier 3 weight portion (uncapped)"))
                .max(zero, descriptor("Tier 3 weight portion (floored)"));

        final var tier1Cost = weightThroughTier1.multiply(tier1Rate, mc, descriptor("Tier 1 cost"));
        final var tier2Cost = tier2Weight.multiply(tier2Rate, mc, descriptor("Tier 2 cost"));
        final var tier3Cost = tier3Weight.multiply(tier3Rate, mc, descriptor("Tier 3 cost"));
        final var weightCost = tier1Cost.addBulk(List.of(tier2Cost, tier3Cost), mc, descriptor("Total weight-based cost"));

        // === Distance surcharge ===
        final var distanceMiles = ctx.wrapBigDecimal(dp.fetchDistanceMiles(), descriptor("Shipment distance (miles)"));
        final var distanceRate = ctx.wrapBigDecimal(dp.fetchDistanceSurchargeRatePerMile(), descriptor("Distance surcharge rate ($0.15/mile)"));
        final var distanceSurcharge = distanceMiles.multiply(distanceRate, mc, descriptor("Distance surcharge"));

        // === Fuel surcharge (percentage of pre-fuel subtotal) ===
        final var preFuelSubtotal = weightCost.add(distanceSurcharge, mc, descriptor("Pre-fuel subtotal"));
        final var fuelSurchargeRate = ctx.wrapBigDecimal(dp.fetchFuelSurchargeRate(), descriptor("Fuel surcharge rate (9%)"));
        final var fuelSurcharge = preFuelSubtotal.multiply(fuelSurchargeRate, mc, descriptor("Fuel surcharge"));

        // === Total freight cost ===
        final var totalFreightCost = preFuelSubtotal.add(fuelSurcharge, mc, descriptor("Total freight cost"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
