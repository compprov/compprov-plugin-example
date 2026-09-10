package io.compprov.examples.orbitalperiod;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Lineage Disconnection / Context Substitution attack: Kepler's third law is computed correctly
 * from the orbital radius and gravitational parameter, and replays cleanly in isolation — but
 * the reported period in hours is a freshly-wrapped literal with no operation edge back to that
 * derivation, which is left dangling in the graph, unused.
 */
public class OrbitalPeriodCalculatorLineageDisconnection {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Orbital mechanics: Kepler's third law")));
        OrbitalPeriodDataProvider dp = new OrbitalPeriodDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Inputs ===
        final var r = ctx.wrapBigDecimal(dp.fetchOrbitalRadius(), descriptor("Orbital radius, r (m)"));
        final var g = ctx.wrapBigDecimal(dp.fetchGravitationalConstant(), descriptor("Gravitational constant, G (N·m²/kg²)"));
        final var m = ctx.wrapBigDecimal(dp.fetchCentralBodyMass(), descriptor("Central body mass, M (kg)"));
        final var pi = ctx.wrapBigDecimal(new BigDecimal("3.14159265358979"), descriptor("Constant π"));
        final var two = ctx.wrapBigDecimal(new BigDecimal("2"), descriptor("Constant 2"));
        final var secondsPerHour = ctx.wrapBigDecimal(new BigDecimal("3600"), descriptor("Seconds per hour"));

        // === Standard gravitational parameter: μ = G × M ===
        final var gm = g.multiply(m, mc, descriptor("G × M, standard gravitational parameter (m³/s²)"));

        // === r³ ===
        final var exponent3 = ctx.wrapInteger(3, descriptor("Exponent 3 (cubic)"));
        final var rCubed = r.pow(exponent3, mc, descriptor("r³ (m³)"));

        // === T = 2π × sqrt(r³ / (GM)) — computed correctly, but never consumed below ===
        final var ratio = rCubed.divide(gm, mc, descriptor("r³ / (GM) (s²)"));
        final var sqrtRatio = ratio.sqrt(mc, descriptor("sqrt(r³/(GM)) (s)"));
        final var twoPi = two.multiply(pi, mc, descriptor("2π"));
        final var period = twoPi.multiply(sqrtRatio, mc, descriptor("Orbital period, T (s)"));

        // === Reported period: a disconnected literal standing in for period / secondsPerHour ===
        final var periodHours = ctx.wrapBigDecimal(new BigDecimal("23.935"), descriptor("Orbital period, T (hours)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
