package io.compprov.examples.escapevelocity;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Escape velocity from a planetary body, {@code v = sqrt(2GM/r)}, computed at the surface and
 * again at a higher altitude ({@code r + altitude}) for comparison, using Earth's mass and
 * radius.
 */
public class EscapeVelocityCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Orbital mechanics: escape velocity")));
        EscapeVelocityDataProvider dp = new EscapeVelocityDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Inputs ===
        final var g = ctx.wrapBigDecimal(dp.fetchGravitationalConstant(), descriptor("Gravitational constant, G (N·m²/kg²)"));
        final var m = ctx.wrapBigDecimal(dp.fetchPlanetMass(), descriptor("Planet mass, M (kg)"));
        final var r = ctx.wrapBigDecimal(dp.fetchPlanetRadius(), descriptor("Planet radius, r (m)"));
        final var altitude = ctx.wrapBigDecimal(dp.fetchAltitude(), descriptor("Altitude above surface (m)"));
        final var two = ctx.wrapBigDecimal(new BigDecimal("2"), descriptor("Constant 2"));

        // === Standard gravitational parameter: μ = G × M ===
        final var gm = g.multiply(m, mc, descriptor("G × M, standard gravitational parameter (m³/s²)"));
        final var twoGM = two.multiply(gm, mc, descriptor("2 × G × M"));

        // === Escape velocity at the surface: v = sqrt(2GM/r) ===
        final var ratioSurface = twoGM.divide(r, mc, descriptor("2GM / r at surface (m²/s²)"));
        final var vEscapeSurface = ratioSurface.sqrt(mc, descriptor("Escape velocity at surface (m/s)"));

        // === Escape velocity at altitude: v = sqrt(2GM/(r + altitude)) ===
        final var rAtAltitude = r.add(altitude, mc, descriptor("Radius at altitude, r + altitude (m)"));
        final var ratioAltitude = twoGM.divide(rAtAltitude, mc, descriptor("2GM / r at altitude (m²/s²)"));
        final var vEscapeAltitude = ratioAltitude.sqrt(mc, descriptor("Escape velocity at altitude (m/s)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
