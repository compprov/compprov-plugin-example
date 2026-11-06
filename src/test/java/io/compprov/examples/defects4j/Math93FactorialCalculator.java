package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Math-93 / MATH-240 (Commons Math {@code MathUtils.factorial}): for n < 21 the
 * exact value from the precomputed long table, i.e. the exact product 2 · 3 · … · n.
 */
public class Math93FactorialCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Combinatorics: factorial n!")));
        Math93FactorialDataProvider dp = new Math93FactorialDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final int n = dp.fetchN();
        var product = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("Initial product, 1"));
        for (int i = 2; i <= n; i++) {
            final var factor = ctx.wrapBigDecimal(BigDecimal.valueOf(i), descriptor("Factor, %d".formatted(i)));
            final String label = i == n ? "Factorial %d!".formatted(n) : "Partial product %d!".formatted(i);
            product = product.multiply(factor, mc, descriptor(label));
        }

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
