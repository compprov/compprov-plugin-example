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
 * Buggy version of Defects4J Math-102 / MATH-175: the expected counts are used as-is even though
 * their total differs from the observed total, so χ² compares counts on different scales.
 */
public class Math102ChiSquareCalculatorBuggy {

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

        // === Chi-square statistic === //defect here: no rescaling of expected counts
        final List<WrappedBigDecimal> terms = new ArrayList<>();
        for (int i = 0; i < expected.size(); i++) {
            final var dev = observed.get(i).subtract(expected.get(i), mc, descriptor("[%d] Deviation".formatted(i)));
            final var devSq = dev.multiply(dev, mc, descriptor("[%d] Squared deviation".formatted(i)));
            terms.add(devSq.divide(expected.get(i), mc, descriptor("[%d] Chi-square contribution".formatted(i))));
        }
        final var chiSquare = terms.get(0).addBulk(terms.subList(1, terms.size()), mc, descriptor("Chi-square statistic"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
