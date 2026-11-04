package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Buggy version of Defects4J Math-92 / MATH-241: C(n, k) = floor(exp(Σ ln i − Σ ln j) + 0.5), with the
 * logarithms and exponential in double; the result loses the last digit and rounds to the wrong integer.
 */
public class Math92BinomialCoefficientCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Combinatorics: binomial coefficient C(n, k)")));
        Math92BinomialCoefficientDataProvider dp = new Math92BinomialCoefficientDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var floor = ctx.wrapMathContext(new MathContext(0, RoundingMode.FLOOR), descriptor("Round down to integer (floor)"));

        final int n = dp.fetchN();
        final int k = dp.fetchK();

        // === log(n! / k!) − log((n − k)!) ===
        var logSum = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Initial log sum, 0"));
        for (int i = k + 1; i <= n; i++) {
            final var factor = ctx.wrapBigDecimal(BigDecimal.valueOf(i), descriptor("Numerator factor, %d".formatted(i)));
            logSum = logSum.add(factor.lnDouble(descriptor("ln %d".formatted(i))), mc, descriptor("Log sum after +ln %d".formatted(i)));
        }
        for (int i = 2; i <= n - k; i++) {
            final var factor = ctx.wrapBigDecimal(BigDecimal.valueOf(i), descriptor("Denominator factor, %d".formatted(i)));
            logSum = logSum.subtract(factor.lnDouble(descriptor("ln %d".formatted(i))), mc, descriptor("Log sum after −ln %d".formatted(i)));
        }

        // === Back to an integer === //defect here: rounding through exp of a double log sum
        final var half = ctx.wrapBigDecimal(new BigDecimal("0.5"), descriptor("Rounding offset, 0.5"));
        logSum.expDouble(descriptor("exp(log sum)"))
                .add(half, mc, descriptor("exp(log sum) + 0.5"))
                .setScale(floor, descriptor("Binomial coefficient C(%d, %d)".formatted(n, k)));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
