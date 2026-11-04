package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Buggy version of Defects4J Math-69 / MATH-371: the p-value is 2·(1 − CDF(t)) with CDF(t) = 1 − 0.5·I_x;
 * for large t the CDF rounds to 1, so the subtraction cancels and p becomes exactly 0.
 */
public class Math69PearsonPValueCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Statistics: p-value of a Pearson correlation")));
        Math69PearsonPValueDataProvider dp = new Math69PearsonPValueDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var ts = Math69PearsonPValueCalculator.tStatistic(ctx, mc, dp);
        final var beta = Math69PearsonPValueCalculator.incompleteBeta(ctx, mc, ts);

        // === Two-sided p-value ===
        final var half = ctx.wrapBigDecimal(new BigDecimal("0.5"), descriptor("One half, 0.5"));
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("Total probability, 1"));
        final var cdfT = one.subtract(half.multiply(beta, mc, descriptor("0.5·I_x")), mc, descriptor("t-distribution CDF at t, 1 − 0.5·I_x"));
        final var upperTail = one.subtract(cdfT, mc, descriptor("Upper-tail probability, 1 − CDF(t)")); //defect here: cancels when CDF(t) rounds to 1
        final var two = ctx.wrapBigDecimal(BigDecimal.valueOf(2), descriptor("Two-sided factor, 2"));
        two.multiply(upperTail, mc, descriptor("Correlation p-value"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
