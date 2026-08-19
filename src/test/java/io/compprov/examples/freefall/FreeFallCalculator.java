package io.compprov.examples.freefall;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * An object dropped from rest at height {@code h} under gravity {@code g}: time to fall
 * {@code t = sqrt(2h/g)}, impact velocity {@code v = sqrt(2gh)} (cross-checked against
 * {@code v = g × t}), and kinetic energy at impact {@code KE = 0.5 × m × v²}.
 */
public class FreeFallCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Free fall: drop from height")));
        FreeFallDataProvider dp = new FreeFallDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Inputs ===
        final var h = ctx.wrapBigDecimal(dp.fetchHeight(), descriptor("Drop height, h (m)"));
        final var g = ctx.wrapBigDecimal(dp.fetchGravity(), descriptor("Gravitational acceleration, g (m/s²)"));
        final var m = ctx.wrapBigDecimal(dp.fetchMass(), descriptor("Object mass, m (kg)"));
        final var two = ctx.wrapBigDecimal(new BigDecimal("2"), descriptor("Constant 2"));
        final var half = ctx.wrapBigDecimal(new BigDecimal("0.5"), descriptor("Constant 0.5"));

        // === Time to fall: t = sqrt(2h/g) ===
        final var twoH = h.multiply(two, mc, descriptor("2 × h (m)"));
        final var twoHOverG = twoH.divide(g, mc, descriptor("2h / g (s²)"));
        final var t = twoHOverG.sqrt(mc, descriptor("Time to fall, t (s)"));

        // === Impact velocity: v = sqrt(2gh) ===
        final var twoGH = twoH.multiply(g, mc, descriptor("2h × g (m²/s²)"));
        final var v = twoGH.sqrt(mc, descriptor("Impact velocity, v (m/s)"));

        // === Cross-check: v = g × t ===
        final var vCheck = g.multiply(t, mc, descriptor("Impact velocity cross-check, g × t (m/s)"));

        // === Kinetic energy at impact: KE = 0.5 × m × v² ===
        final var vSquared = v.multiply(v, mc, descriptor("v² (m²/s²)"));
        final var halfM = half.multiply(m, mc, descriptor("0.5 × m (kg)"));
        final var ke = halfM.multiply(vSquared, mc, descriptor("Kinetic energy at impact, KE (J)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
