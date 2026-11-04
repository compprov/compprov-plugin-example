package io.compprov.examples.defects4j;

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
 * Buggy version of Defects4J Math-57 / MATH-546: the running sum of D(x)² is an {@code int}, so
 * {@code sum += d * d} truncates every step; small distances vanish, r becomes 0 and the first
 * point is always chosen.
 */
public class Math57KMeansSeedingCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Clustering: k-means++ seeding of the second centre")));
        Math57KMeansSeedingDataProvider dp = new Math57KMeansSeedingDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var center = ctx.wrapBigDecimal(dp.fetchFirstCenter(), descriptor("First centre"));
        final var scale = ctx.wrapBigDecimal(dp.fetchDistanceScale(), descriptor("Distance scale"));
        final List<BigDecimal> rawPoints = dp.fetchRemainingPoints();
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("Constant 1"));

        // === Cumulative squared distances to the nearest centre ===
        var sum = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Initial sum, 0"));
        final List<WrappedBigDecimal> cumulative = new ArrayList<>();
        for (int i = 0; i < rawPoints.size(); i++) {
            final var p = ctx.wrapBigDecimal(rawPoints.get(i), descriptor("[%d] Point".formatted(i)));
            final var d = p.subtract(center, mc, descriptor("[%d] Offset from nearest centre".formatted(i)))
                    .abs(mc, descriptor("[%d] Unscaled distance".formatted(i)))
                    .multiply(scale, mc, descriptor("[%d] Distance to nearest centre".formatted(i)));
            final var dSq = d.multiply(d, mc, descriptor("[%d] Squared distance".formatted(i)));
            sum = sum.add(dSq, mc, descriptor("[%d] Sum before narrowing".formatted(i))) //defect here: int accumulator
                    .divideToIntegralValue(one, mc, descriptor("[%d] Cumulative squared distance".formatted(i)));
            cumulative.add(sum);
        }

        // === Selection threshold and choice ===
        final var u = ctx.wrapBigDecimal(dp.fetchUniformDraw(), descriptor("Uniform random draw, u"));
        final var r = u.multiply(sum, mc, descriptor("Selection threshold, r"));
        Math57KMeansSeedingCalculator.selectFirstReaching(ctx, mc, cumulative, r);

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
