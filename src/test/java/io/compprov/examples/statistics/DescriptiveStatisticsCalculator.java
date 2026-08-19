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
 * Descriptive statistics (mean, sample variance, sample standard deviation) over 6 repeated
 * titration end-point measurements.
 *
 * <p>mean = Σx / n; deviation_i = x_i − mean; sample variance = Σ(deviation_i²) / (n − 1);
 * standard deviation = sqrt(variance).
 */
public class DescriptiveStatisticsCalculator {

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

        // === Mean ===
        final var sum = values.get(0).addBulk(values.subList(1, values.size()), mc, descriptor("Sum of samples (mL)"));
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
