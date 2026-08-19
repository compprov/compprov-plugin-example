package io.compprov.examples.construction;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Residential construction project bid: materials + labor (two trades) → direct cost → overhead
 * (percentage of materials + labor) → profit margin (percentage of cost including overhead) →
 * total bid price.
 */
public class ConstructionEstimateCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Construction: residential project bid")));
        ConstructionEstimateDataProvider dp = new ConstructionEstimateDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Materials ===
        final var lumber = ctx.wrapBigDecimal(dp.fetchLumberCost(), descriptor("Lumber"));
        final var concrete = ctx.wrapBigDecimal(dp.fetchConcreteCost(), descriptor("Concrete"));
        final var roofing = ctx.wrapBigDecimal(dp.fetchRoofingMaterialsCost(), descriptor("Roofing materials"));
        final var electricalMaterials = ctx.wrapBigDecimal(dp.fetchElectricalMaterialsCost(), descriptor("Electrical materials"));
        final var materialsCost = lumber.addBulk(
                List.of(concrete, roofing, electricalMaterials), mc, descriptor("Total materials cost"));

        // === Labor (two trades) ===
        final var carpentryHours = ctx.wrapBigDecimal(dp.fetchCarpentryHours(), descriptor("Carpentry hours"));
        final var carpentryRate = ctx.wrapBigDecimal(dp.fetchCarpentryRate(), descriptor("Carpentry rate ($/hr)"));
        final var carpentryLabor = carpentryHours.multiply(carpentryRate, mc, descriptor("Carpentry labor cost"));

        final var electricalHours = ctx.wrapBigDecimal(dp.fetchElectricalHours(), descriptor("Electrical hours"));
        final var electricalRate = ctx.wrapBigDecimal(dp.fetchElectricalRate(), descriptor("Electrical rate ($/hr)"));
        final var electricalLabor = electricalHours.multiply(electricalRate, mc, descriptor("Electrical labor cost"));

        final var laborCost = carpentryLabor.add(electricalLabor, mc, descriptor("Total labor cost"));

        // === Direct cost, overhead, profit margin ===
        final var directCost = materialsCost.add(laborCost, mc, descriptor("Direct cost (materials + labor)"));

        final var overheadRate = ctx.wrapBigDecimal(dp.fetchOverheadRate(), descriptor("Overhead rate (10%)"));
        final var overhead = directCost.multiply(overheadRate, mc, descriptor("Overhead"));

        final var costWithOverhead = directCost.add(overhead, mc, descriptor("Cost including overhead"));

        final var profitMarginRate = ctx.wrapBigDecimal(dp.fetchProfitMarginRate(), descriptor("Profit margin rate (15%)"));
        final var profitMargin = costWithOverhead.multiply(profitMarginRate, mc, descriptor("Profit margin"));

        // === Total bid price ===
        final var totalBidPrice = costWithOverhead.add(profitMargin, mc, descriptor("Total bid price"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
