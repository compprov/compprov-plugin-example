package io.compprov.examples.orbitalperiod;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Kepler's third law for a circular orbit: {@code T = 2π × sqrt(r³ / (GM))}, applied to a
 * geostationary satellite around Earth. Also expresses the resulting period in hours.
 */
public class OrbitalPeriodCalculator {

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

        // === T = 2π × sqrt(r³ / (GM)) ===
        final var ratio = rCubed.divide(gm, mc, descriptor("r³ / (GM) (s²)"));
        final var sqrtRatio = ratio.sqrt(mc, descriptor("sqrt(r³/(GM)) (s)"));
        final var twoPi = two.multiply(pi, mc, descriptor("2π"));
        final var period = twoPi.multiply(sqrtRatio, mc, descriptor("Orbital period, T (s)"));

        // === Convert to hours for readability ===
        final var periodHours = period.divide(secondsPerHour, mc, descriptor("Orbital period, T (hours)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
