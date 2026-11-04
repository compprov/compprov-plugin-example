package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.wrappers.WrappedBigDecimal;
import io.compprov.core.wrappers.WrappedMathContext;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Math-55 / MATH-554 (Commons Math {@code Vector3D.crossProduct}): v1 is first
 * preconditioned as v3 = v1 − ρ·v2, ρ = rint(256 · (v1·v2) / |v2|²) / 256, which leaves the cross product
 * unchanged but removes the cancellation, then v3 × v2 is computed. The exponent rescaling step
 * (scalb) is a no-op here because both vectors have the same magnitude.
 */
public class Math55CrossProductCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Geometry: cross product of two nearly parallel 3D vectors")));
        Math55CrossProductDataProvider dp = new Math55CrossProductDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var rint = ctx.wrapMathContext(new MathContext(0, RoundingMode.HALF_EVEN), descriptor("Round to nearest integer (rint)"));

        final List<BigDecimal> rawV1 = dp.fetchFirstVector();
        final List<BigDecimal> rawV2 = dp.fetchSecondVector();
        final var x1 = ctx.wrapBigDecimal(rawV1.get(0), descriptor("v1.x"));
        final var y1 = ctx.wrapBigDecimal(rawV1.get(1), descriptor("v1.y"));
        final var z1 = ctx.wrapBigDecimal(rawV1.get(2), descriptor("v1.z"));
        final var x2 = ctx.wrapBigDecimal(rawV2.get(0), descriptor("v2.x"));
        final var y2 = ctx.wrapBigDecimal(rawV2.get(1), descriptor("v2.y"));
        final var z2 = ctx.wrapBigDecimal(rawV2.get(2), descriptor("v2.z"));

        // === Preconditioning: v3 = v1 − ρ·v2 ===
        final var n2 = sum3(x2.multiply(x2, mc, descriptor("v2.x²")), y2.multiply(y2, mc, descriptor("v2.y²")),
                z2.multiply(z2, mc, descriptor("v2.z²")), mc, "|v2|²");
        final var dot = sum3(x1.multiply(x2, mc, descriptor("v1.x·v2.x")), y1.multiply(y2, mc, descriptor("v1.y·v2.y")),
                z1.multiply(z2, mc, descriptor("v1.z·v2.z")), mc, "v1·v2");
        final var ratio = dot.divide(n2, mc, descriptor("Projection ratio, (v1·v2) / |v2|²"));
        final var c256 = ctx.wrapBigDecimal(BigDecimal.valueOf(256), descriptor("Rounding grid, 256"));
        final var rho = c256.multiply(ratio, mc, descriptor("Scaled projection ratio"))
                .setScale(rint, descriptor("Rounded scaled ratio"))
                .divide(c256, mc, descriptor("Preconditioning factor, ρ"));
        final var x3 = x1.subtract(rho.multiply(x2, mc, descriptor("ρ·v2.x")), mc, descriptor("v3.x"));
        final var y3 = y1.subtract(rho.multiply(y2, mc, descriptor("ρ·v2.y")), mc, descriptor("v3.y"));
        final var z3 = z1.subtract(rho.multiply(z2, mc, descriptor("ρ·v2.z")), mc, descriptor("v3.z"));

        // === Cross product v3 × v2 ===
        y3.multiply(z2, mc, descriptor("v3.y·v2.z")).subtract(z3.multiply(y2, mc, descriptor("v3.z·v2.y")), mc, descriptor("Cross product x"));
        z3.multiply(x2, mc, descriptor("v3.z·v2.x")).subtract(x3.multiply(z2, mc, descriptor("v3.x·v2.z")), mc, descriptor("Cross product y"));
        x3.multiply(y2, mc, descriptor("v3.x·v2.y")).subtract(y3.multiply(x2, mc, descriptor("v3.y·v2.x")), mc, descriptor("Cross product z"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }

    private static WrappedBigDecimal sum3(WrappedBigDecimal a, WrappedBigDecimal b, WrappedBigDecimal c,
                                          WrappedMathContext mc, String label) {
        return a.addBulk(List.of(b, c), mc, descriptor(label));
    }
}
