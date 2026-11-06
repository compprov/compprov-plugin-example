package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.wrappers.WrappedBigDecimal;
import io.compprov.core.wrappers.WrappedMathContext;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Math-57 / MATH-546 (Commons Math {@code KMeansPlusPlusClusterer.chooseInitialCenters}):
 * k-means++ picks the next centre as the first point whose cumulative D(x)² reaches
 * r = u · ΣD(x)², with the running sum kept as a double. Each comparison becomes a 0/1 flag,
 * 1 + min(compare(sum_i, r), 0), and the first flagged point's index is the result.
 */
public class Math57KMeansSeedingCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Clustering: k-means++ seeding of the second centre")));
        Math57KMeansSeedingDataProvider dp = new Math57KMeansSeedingDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var center = ctx.wrapBigDecimal(dp.fetchFirstCenter(), descriptor("First centre"));
        final var scale = ctx.wrapBigDecimal(dp.fetchDistanceScale(), descriptor("Distance scale"));
        final List<BigDecimal> rawPoints = dp.fetchRemainingPoints();

        // === Cumulative squared distances to the nearest centre ===
        var sum = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Initial sum, 0"));
        final List<WrappedBigDecimal> cumulative = new ArrayList<>();
        for (int i = 0; i < rawPoints.size(); i++) {
            final var p = ctx.wrapBigDecimal(rawPoints.get(i), descriptor("[%d] Point".formatted(i)));
            final var d = p.subtract(center, mc, descriptor("[%d] Offset from nearest centre".formatted(i)))
                    .abs(mc, descriptor("[%d] Unscaled distance".formatted(i)))
                    .multiply(scale, mc, descriptor("[%d] Distance to nearest centre".formatted(i)));
            final var dSq = d.multiply(d, mc, descriptor("[%d] Squared distance".formatted(i)));
            sum = sum.add(dSq, mc, descriptor("[%d] Cumulative squared distance".formatted(i)));
            cumulative.add(sum);
        }

        // === Selection threshold and choice ===
        final var u = ctx.wrapBigDecimal(dp.fetchUniformDraw(), descriptor("Uniform random draw, u"));
        final var r = u.multiply(sum, mc, descriptor("Selection threshold, r"));
        selectFirstReaching(ctx, mc, cumulative, r);

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }

    // Index of the first point whose cumulative D(x)² ≥ r, as in the solver's "if (dx[i] >= r) ... break".
    static WrappedBigDecimal selectFirstReaching(DefaultComputationContext ctx, WrappedMathContext mc,
                                                 List<WrappedBigDecimal> cumulative, WrappedBigDecimal r) {
        final var zero = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Zero"));
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("One"));
        var notChosen = one;
        final List<WrappedBigDecimal> weightedIndices = new ArrayList<>(cumulative.size());
        for (int i = 0; i < cumulative.size(); i++) {
            final var reached = one.add(cumulative.get(i)
                    .compare(r, descriptor("[%d] Cumulative squared distance vs threshold".formatted(i)))
                    .min(zero, descriptor("[%d] Below threshold, −1 = yes".formatted(i))), mc, descriptor("[%d] Threshold reached, 1 = yes".formatted(i)));
            final var chosen = reached.multiply(notChosen, mc, descriptor("[%d] Chosen, 1 = yes".formatted(i)));
            final var index = ctx.wrapBigDecimal(BigDecimal.valueOf(i), descriptor("[%d] Point index".formatted(i)));
            weightedIndices.add(chosen.multiply(index, mc, descriptor("[%d] Chosen · index".formatted(i))));
            if (i < cumulative.size() - 1) {
                notChosen = notChosen.subtract(chosen, mc, descriptor("[%d] No point chosen yet, 1 = yes".formatted(i + 1)));
            }
        }
        return weightedIndices.get(0).addBulk(weightedIndices.subList(1, weightedIndices.size()), mc,
                descriptor("Index of the second centre"));
    }
}
