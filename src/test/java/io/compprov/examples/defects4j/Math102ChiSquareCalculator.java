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
 * Fixed version of Defects4J Math-102 / MATH-175 (Commons Math {@code ChiSquareTestImpl.chiSquare}):
 * when the totals differ by more than 10E-6, expected counts are rescaled by ratio = Σobserved / Σexpected
 * before computing χ² = Σ (o − ratio·e)² / (ratio·e). The tolerance check is a 0/1 flag,
 * max(compare(gap, tolerance), 0), selecting between ratio and 1.
 */
public class Math102ChiSquareCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Statistics: chi-square goodness-of-fit statistic")));
        Math102ChiSquareDataProvider dp = new Math102ChiSquareDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final List<BigDecimal> rawExpected = dp.fetchExpected();
        final List<BigDecimal> rawObserved = dp.fetchObserved();
        final List<WrappedBigDecimal> expected = new ArrayList<>();
        final List<WrappedBigDecimal> observed = new ArrayList<>();
        for (int i = 0; i < rawExpected.size(); i++) {
            expected.add(ctx.wrapBigDecimal(rawExpected.get(i), descriptor("[%d] Expected count".formatted(i))));
            observed.add(ctx.wrapBigDecimal(rawObserved.get(i), descriptor("[%d] Observed count".formatted(i))));
        }

        // === Rescale expected counts to the observed total ===
        final var sumExpected = expected.get(0).addBulk(expected.subList(1, expected.size()), mc, descriptor("Sum of expected counts"));
        final var sumObserved = observed.get(0).addBulk(observed.subList(1, observed.size()), mc, descriptor("Sum of observed counts"));
        final var totalsGap = sumExpected.subtract(sumObserved, mc, descriptor("Difference between totals"))
                .abs(mc, descriptor("Absolute difference between totals"));
        final var tolerance = ctx.wrapBigDecimal(new BigDecimal("10E-6"), descriptor("Rescaling tolerance"));
        final var zero = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Zero"));
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("One"));
        final var rescale = totalsGap.compare(tolerance, descriptor("Totals gap vs tolerance"))
                .max(zero, descriptor("Totals differ beyond tolerance, 1 = yes"));
        final var ratio = sumObserved.divide(sumExpected, mc, descriptor("Rescaling ratio"));
        final var effectiveRatio = rescale.multiply(ratio, mc, descriptor("Rescaling ratio if applied"))
                .add(one.subtract(rescale, mc, descriptor("Rescaling skipped, 1 = yes")), mc, descriptor("Effective rescaling ratio"));

        // === Chi-square statistic ===
        final List<WrappedBigDecimal> terms = new ArrayList<>();
        for (int i = 0; i < expected.size(); i++) {
            final var scaledExpected = effectiveRatio.multiply(expected.get(i), mc, descriptor("[%d] Rescaled expected count".formatted(i)));
            final var dev = observed.get(i).subtract(scaledExpected, mc, descriptor("[%d] Deviation".formatted(i)));
            final var devSq = dev.multiply(dev, mc, descriptor("[%d] Squared deviation".formatted(i)));
            terms.add(devSq.divide(scaledExpected, mc, descriptor("[%d] Chi-square contribution".formatted(i))));
        }
        final var chiSquare = terms.get(0).addBulk(terms.subList(1, terms.size()), mc, descriptor("Chi-square statistic"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
