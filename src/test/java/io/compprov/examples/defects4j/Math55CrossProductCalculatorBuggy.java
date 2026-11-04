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
 * Buggy version of Defects4J Math-55 / MATH-554: the cross product is computed directly from v1 and v2,
 * so for nearly parallel vectors each component subtracts two nearly equal 20-digit products and the
 * result cancels to 0.
 */
public class Math55CrossProductCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Geometry: cross product of two nearly parallel 3D vectors")));
        Math55CrossProductDataProvider dp = new Math55CrossProductDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final List<BigDecimal> rawV1 = dp.fetchFirstVector();
        final List<BigDecimal> rawV2 = dp.fetchSecondVector();
        final var x1 = ctx.wrapBigDecimal(rawV1.get(0), descriptor("v1.x"));
        final var y1 = ctx.wrapBigDecimal(rawV1.get(1), descriptor("v1.y"));
        final var z1 = ctx.wrapBigDecimal(rawV1.get(2), descriptor("v1.z"));
        final var x2 = ctx.wrapBigDecimal(rawV2.get(0), descriptor("v2.x"));
        final var y2 = ctx.wrapBigDecimal(rawV2.get(1), descriptor("v2.y"));
        final var z2 = ctx.wrapBigDecimal(rawV2.get(2), descriptor("v2.z"));

        // === Cross product v1 × v2 === //defect here: no preconditioning against cancellation
        y1.multiply(z2, mc, descriptor("v1.y·v2.z")).subtract(z1.multiply(y2, mc, descriptor("v1.z·v2.y")), mc, descriptor("Cross product x"));
        z1.multiply(x2, mc, descriptor("v1.z·v2.x")).subtract(x1.multiply(z2, mc, descriptor("v1.x·v2.z")), mc, descriptor("Cross product y"));
        x1.multiply(y2, mc, descriptor("v1.x·v2.y")).subtract(y1.multiply(x2, mc, descriptor("v1.y·v2.x")), mc, descriptor("Cross product z"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
