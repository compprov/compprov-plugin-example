package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Buggy version of Defects4J Math-105 / MATH-85: SSE = Syy − Sxy² / Sxx is returned unclamped, so
 * cancellation on collinear data yields a negative sum of squared errors.
 */
public class Math105RegressionSseCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Statistics: simple linear regression sum of squared errors")));
        Math105RegressionSseDataProvider dp = new Math105RegressionSseDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Incremental centred sums (SimpleRegression.addData) ===
        final List<BigDecimal> rawX = dp.fetchX();
        final List<BigDecimal> rawY = dp.fetchY();
        var xbar = ctx.wrapBigDecimal(rawX.get(0), descriptor("[0] x"));
        var ybar = ctx.wrapBigDecimal(rawY.get(0), descriptor("[0] y"));
        var sumXX = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Initial Sxx, 0"));
        var sumYY = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Initial Syy, 0"));
        var sumXY = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Initial Sxy, 0"));
        for (int n = 1; n < rawX.size(); n++) {
            final var x = ctx.wrapBigDecimal(rawX.get(n), descriptor("[%d] x".formatted(n)));
            final var y = ctx.wrapBigDecimal(rawY.get(n), descriptor("[%d] y".formatted(n)));
            final var count = ctx.wrapBigDecimal(BigDecimal.valueOf(n), descriptor("[%d] Observations so far, n".formatted(n)));
            final var countNext = ctx.wrapBigDecimal(BigDecimal.valueOf(n + 1), descriptor("[%d] Observations incl. this one, n + 1".formatted(n)));
            final var factor = count.divide(countNext, mc, descriptor("[%d] Update factor, n / (n + 1)".formatted(n)));

            final var dx = x.subtract(xbar, mc, descriptor("[%d] x deviation from running mean".formatted(n)));
            final var dy = y.subtract(ybar, mc, descriptor("[%d] y deviation from running mean".formatted(n)));
            sumXX = sumXX.add(dx.multiply(dx, mc, descriptor("[%d] dx²".formatted(n))).multiply(factor, mc, descriptor("[%d] Sxx increment".formatted(n))),
                    mc, descriptor("[%d] Sxx".formatted(n)));
            sumYY = sumYY.add(dy.multiply(dy, mc, descriptor("[%d] dy²".formatted(n))).multiply(factor, mc, descriptor("[%d] Syy increment".formatted(n))),
                    mc, descriptor("[%d] Syy".formatted(n)));
            sumXY = sumXY.add(dx.multiply(dy, mc, descriptor("[%d] dx·dy".formatted(n))).multiply(factor, mc, descriptor("[%d] Sxy increment".formatted(n))),
                    mc, descriptor("[%d] Sxy".formatted(n)));
            xbar = xbar.add(dx.divide(countNext, mc, descriptor("[%d] x mean shift".formatted(n))), mc, descriptor("[%d] Running x mean".formatted(n)));
            ybar = ybar.add(dy.divide(countNext, mc, descriptor("[%d] y mean shift".formatted(n))), mc, descriptor("[%d] Running y mean".formatted(n)));
        }

        // === Sum of squared errors ===
        final var sxySquared = sumXY.multiply(sumXY, mc, descriptor("Sxy²"));
        final var explained = sxySquared.divide(sumXX, mc, descriptor("Regression sum of squares, Sxy² / Sxx"));
        final var sse = sumYY.subtract(explained, mc, descriptor("Sum of squared errors")); //defect here: no max(0, ...) clamp

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
