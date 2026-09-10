package io.compprov.examples.pendulum;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Precision and Scale Tampering attack: every operation in this graph uses DECIMAL64 except the
 * {@code sqrt(L/g)} step, which is silently evaluated at 3 significant digits with DOWN
 * rounding — a precision gap sandwiched between the division that feeds it and the multiply that
 * consumes it, both at standard precision.
 */
public class PendulumPeriodCalculatorPrecisionTampering {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Pendulum: small-angle period")));
        PendulumPeriodDataProvider dp = new PendulumPeriodDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision"));
        final var lowPrecisionMc = ctx.wrapMathContext(
                new MathContext(3, RoundingMode.DOWN), descriptor("Computation precision"));

        // === Inputs ===
        final var length = ctx.wrapBigDecimal(dp.fetchLength(), descriptor("Pendulum length, L (m)"));
        final var g = ctx.wrapBigDecimal(dp.fetchGravity(), descriptor("Gravitational acceleration, g (m/s²)"));
        final var pi = ctx.wrapBigDecimal(new BigDecimal("3.14159265358979"), descriptor("Constant π"));
        final var two = ctx.wrapBigDecimal(new BigDecimal("2"), descriptor("Constant 2"));
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("Constant 1"));

        // === Period: T = 2π × sqrt(L/g) ===
        final var lOverG = length.divide(g, mc, descriptor("L / g (s²)"));
        // tampered: evaluated at 3 significant digits instead of DECIMAL64
        final var sqrtLOverG = lOverG.sqrt(lowPrecisionMc, descriptor("sqrt(L/g) (s)"));
        final var twoPi = two.multiply(pi, mc, descriptor("2π"));
        final var period = twoPi.multiply(sqrtLOverG, mc, descriptor("Period, T (s)"));

        // === Frequency: f = 1/T ===
        final var frequency = one.divide(period, mc, descriptor("Frequency, f (Hz)"));

        // === Cross-check: angular frequency ω = 2π/T should equal sqrt(g/L) ===
        final var omega = twoPi.divide(period, mc, descriptor("Angular frequency, ω = 2π/T (rad/s)"));
        final var gOverL = g.divide(length, mc, descriptor("g / L (1/s²)"));
        final var omegaCheck = gOverL.sqrt(mc, descriptor("Angular frequency cross-check, sqrt(g/L) (rad/s)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
