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
 * Buggy version of Defects4J Math-93 / MATH-240: n! = round(floor(exp(Σ ln i) + 0.5)), with the logarithms
 * and exponential in double, so from 17! on the result is off in the last digits.
 */
public class Math93FactorialCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Combinatorics: factorial n!")));
        Math93FactorialDataProvider dp = new Math93FactorialDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var floor = ctx.wrapMathContext(new MathContext(0, RoundingMode.FLOOR), descriptor("Round down to integer (floor)"));

        final int n = dp.fetchN();
        var logSum = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Initial log sum, 0"));
        for (int i = 2; i <= n; i++) {
            final var factor = ctx.wrapBigDecimal(BigDecimal.valueOf(i), descriptor("Factor, %d".formatted(i)));
            logSum = logSum.add(factor.lnDouble(descriptor("ln %d".formatted(i))), mc, descriptor("Log sum after +ln %d".formatted(i)));
        }

        // === Back to an integer === //defect here: rounding through exp of a double log sum
        final var half = ctx.wrapBigDecimal(new BigDecimal("0.5"), descriptor("Rounding offset, 0.5"));
        logSum.expDouble(descriptor("exp(log sum)"))
                .add(half, mc, descriptor("exp(log sum) + 0.5"))
                .setScale(floor, descriptor("Factorial %d!".formatted(n)));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
