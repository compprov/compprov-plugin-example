package io.compprov.examples.statistics;

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

/**
 * Topological Accumulation Fraud (Double Counting) attack: sample [0] — the same origin
 * measurement already contributing once to the sum — is fed into the {@code addBulk} aggregation
 * a second time via a duplicate parallel path, while the reported sample count {@code n} stays
 * at 6, skewing the reported mean and every downstream statistic derived from it.
 */
public class DescriptiveStatisticsCalculatorDoubleCounting {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Statistics: titration replicate measurements")));
        DescriptiveStatisticsDataProvider dp = new DescriptiveStatisticsDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Sample values ===
        List<BigDecimal> rawValues = dp.fetchSampleValues();
        List<WrappedBigDecimal> values = new ArrayList<>(rawValues.size());
        for (int i = 0; i < rawValues.size(); i++) {
            values.add(ctx.wrapBigDecimal(rawValues.get(i), descriptor("[%d] Sample value (mL)".formatted(i))));
        }

        // === Mean — sample [0] fed into the sum twice via a duplicate parallel path ===
        final List<WrappedBigDecimal> sumTerms = new ArrayList<>(values.subList(1, values.size()));
        sumTerms.add(values.get(0));
        final var sum = values.get(0).addBulk(sumTerms, mc, descriptor("Sum of samples (mL)"));
        final var n = ctx.wrapBigDecimal(new BigDecimal(values.size()), descriptor("Sample count, n"));
        final var mean = sum.divide(n, mc, descriptor("Mean (mL)"));

        // === Deviations and squared deviations ===
        List<WrappedBigDecimal> squaredDeviations = new ArrayList<>(values.size());
        for (int i = 0; i < values.size(); i++) {
            final var deviation = values.get(i).subtract(mean, mc, descriptor("[%d] Deviation from mean (mL)".formatted(i)));
            final var squaredDeviation = deviation.multiply(deviation, mc, descriptor("[%d] Squared deviation (mL²)".formatted(i)));
            squaredDeviations.add(squaredDeviation);
        }

        // === Sample variance and standard deviation ===
        final var sumSquaredDeviations = squaredDeviations.get(0)
                .addBulk(squaredDeviations.subList(1, squaredDeviations.size()), mc, descriptor("Sum of squared deviations (mL²)"));
        final var nMinus1 = ctx.wrapBigDecimal(new BigDecimal(values.size() - 1), descriptor("Degrees of freedom, n - 1"));
        final var variance = sumSquaredDeviations.divide(nMinus1, mc, descriptor("Sample variance (mL²)"));
        final var stdDev = variance.sqrt(mc, descriptor("Sample standard deviation (mL)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
