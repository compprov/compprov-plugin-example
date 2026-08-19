package io.compprov.examples.collision;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * 1-D two-body elastic collision. Final velocities:
 * <pre>
 *   v1' = ((m1 − m2)v1 + 2m2v2) / (m1 + m2)
 *   v2' = ((m2 − m1)v2 + 2m1v1) / (m1 + m2)
 * </pre>
 * Total momentum before ({@code m1v1 + m2v2}) and after ({@code m1v1' + m2v2'}) are each
 * computed as separate graph nodes to make conservation of momentum visible in the CPG.
 */
public class ElasticCollisionCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Physics: 1-D elastic collision")));
        ElasticCollisionDataProvider dp = new ElasticCollisionDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Inputs ===
        final var m1 = ctx.wrapBigDecimal(dp.fetchMass1(), descriptor("Mass 1, m1 (kg)"));
        final var v1 = ctx.wrapBigDecimal(dp.fetchVelocity1(), descriptor("Initial velocity 1, v1 (m/s)"));
        final var m2 = ctx.wrapBigDecimal(dp.fetchMass2(), descriptor("Mass 2, m2 (kg)"));
        final var v2 = ctx.wrapBigDecimal(dp.fetchVelocity2(), descriptor("Initial velocity 2, v2 (m/s)"));
        final var two = ctx.wrapBigDecimal(new BigDecimal("2"), descriptor("Constant 2"));

        final var totalMass = m1.add(m2, mc, descriptor("Total mass, m1 + m2 (kg)"));

        // === Final velocity of body 1 ===
        final var m1MinusM2 = m1.subtract(m2, mc, descriptor("m1 - m2 (kg)"));
        final var term1a = m1MinusM2.multiply(v1, mc, descriptor("(m1 - m2) × v1"));
        final var twoM2 = two.multiply(m2, mc, descriptor("2 × m2 (kg)"));
        final var term1b = twoM2.multiply(v2, mc, descriptor("2m2 × v2"));
        final var numerator1 = term1a.add(term1b, mc, descriptor("Numerator for v1'"));
        final var v1Prime = numerator1.divide(totalMass, mc, descriptor("Final velocity, v1' (m/s)"));

        // === Final velocity of body 2 ===
        final var m2MinusM1 = m2.subtract(m1, mc, descriptor("m2 - m1 (kg)"));
        final var term2a = m2MinusM1.multiply(v2, mc, descriptor("(m2 - m1) × v2"));
        final var twoM1 = two.multiply(m1, mc, descriptor("2 × m1 (kg)"));
        final var term2b = twoM1.multiply(v1, mc, descriptor("2m1 × v1"));
        final var numerator2 = term2a.add(term2b, mc, descriptor("Numerator for v2'"));
        final var v2Prime = numerator2.divide(totalMass, mc, descriptor("Final velocity, v2' (m/s)"));

        // === Momentum conservation check ===
        final var momentumBefore1 = m1.multiply(v1, mc, descriptor("m1 × v1 (before)"));
        final var momentumBefore2 = m2.multiply(v2, mc, descriptor("m2 × v2 (before)"));
        final var momentumBefore = momentumBefore1.add(momentumBefore2, mc, descriptor("Total momentum before collision (kg·m/s)"));

        final var momentumAfter1 = m1.multiply(v1Prime, mc, descriptor("m1 × v1' (after)"));
        final var momentumAfter2 = m2.multiply(v2Prime, mc, descriptor("m2 × v2' (after)"));
        final var momentumAfter = momentumAfter1.add(momentumAfter2, mc, descriptor("Total momentum after collision (kg·m/s)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
