package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Math-92 / MATH-241 (Commons Math {@code MathUtils.binomialCoefficient}, n ≤ 61
 * branch): exact multiplicative form, result = result · i / j for j = 1..k, i = n − k + 1..n.
 */
public class Math92BinomialCoefficientCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Combinatorics: binomial coefficient C(n, k)")));
        Math92BinomialCoefficientDataProvider dp = new Math92BinomialCoefficientDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final int n = dp.fetchN();
        final int k = dp.fetchK();
        var result = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("Initial product, 1"));
        for (int j = 1, i = n - k + 1; j <= k; i++, j++) {
            final var numeratorFactor = ctx.wrapBigDecimal(BigDecimal.valueOf(i), descriptor("[%d] Numerator factor, %d".formatted(j, i)));
            final var denominatorFactor = ctx.wrapBigDecimal(BigDecimal.valueOf(j), descriptor("[%d] Denominator factor, %d".formatted(j, j)));
            final String label = j == k ? "Binomial coefficient C(%d, %d)".formatted(n, k) : "[%d] Partial binomial product".formatted(j);
            result = result.multiply(numeratorFactor, mc, descriptor("[%d] Product with numerator factor".formatted(j)))
                    .divide(denominatorFactor, mc, descriptor(label));
        }

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
