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
 * Fixed version of Defects4J Math-77 / MATH-326 (Commons Math {@code ArrayRealVector.getLInfNorm()}):
 * running maximum of the absolute component values, max = max(max, |a|).
 */
public class Math77LInfNormCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Linear algebra: L-infinity norm of a vector")));
        Math77LInfNormDataProvider dp = new Math77LInfNormDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final List<BigDecimal> components = dp.fetchComponents();
        var max = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Initial maximum, 0"));
        for (int i = 0; i < components.size(); i++) {
            final var a = ctx.wrapBigDecimal(components.get(i), descriptor("[%d] Vector component".formatted(i)));
            final var absA = a.abs(mc, descriptor("[%d] Absolute component".formatted(i)));
            final String label = i == components.size() - 1 ? "L-infinity norm" : "[%d] Running maximum".formatted(i);
            max = max.max(absA, descriptor(label));
        }

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
