package io.compprov.examples.depreciation;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Straight-line depreciation of a fixed asset over its 5-year useful life.
 *
 * <p>{@code annualDepreciation = (cost − salvageValue) / usefulLifeYears}, applied evenly each
 * year. Accumulated depreciation and book value are tracked at the end of every year.
 */
public class AssetDepreciationCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Depreciation: 5-year straight-line schedule")));
        AssetDepreciationDataProvider dp = new AssetDepreciationDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var cost = ctx.wrapBigDecimal(dp.fetchAssetCost(), descriptor("Asset cost"));
        final var salvageValue = ctx.wrapBigDecimal(dp.fetchSalvageValue(), descriptor("Salvage value"));
        final var usefulLife = ctx.wrapBigDecimal(dp.fetchUsefulLifeYears(), descriptor("Useful life (years)"));

        final var depreciableBase = cost.subtract(salvageValue, mc, descriptor("Depreciable base"));
        final var annualDepreciation = depreciableBase.divide(usefulLife, mc, descriptor("Annual depreciation"));

        var accumulatedDepreciation = ctx.wrapBigDecimal(java.math.BigDecimal.ZERO, descriptor("Accumulated depreciation (start)"));
        var bookValue = cost;

        for (int year = 1; year <= AssetDepreciationDataProvider.USEFUL_LIFE_YEARS; year++) {
            String lbl = "[Year %d]".formatted(year);
            accumulatedDepreciation = accumulatedDepreciation.add(annualDepreciation, mc, descriptor("Accumulated depreciation " + lbl));
            bookValue = bookValue.subtract(annualDepreciation, mc, descriptor("Book value " + lbl));
        }

        final var endingBookValue = bookValue;
        final var totalAccumulatedDepreciation = accumulatedDepreciation;

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
