package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.wrappers.WrappedBigDecimal;
import io.compprov.core.wrappers.WrappedMathContext;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Math-74 / MATH-338 (Commons Math {@code EmbeddedRungeKuttaIntegrator.integrate}
 * feeding {@code AdaptiveStepsizeIntegrator.initializeStep}): the error scale is absTol + relTol·|y|, and the
 * initial step is h = (0.01 / max(‖y'/scale‖, ‖y''/scale‖))^(1/order), clamped to the step bounds.
 */
public class Math74RkInitialStepCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("ODE: Runge-Kutta initial step size from error scale")));
        Math74RkInitialStepDataProvider dp = new Math74RkInitialStepDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var y0 = ctx.wrapBigDecimal(dp.fetchInitialState(), descriptor("Initial state, y0"));
        final var absTol = ctx.wrapBigDecimal(dp.fetchAbsoluteTolerance(), descriptor("Absolute tolerance"));
        final var relTol = ctx.wrapBigDecimal(dp.fetchRelativeTolerance(), descriptor("Relative tolerance"));

        // === Error scale ===
        final var scale = absTol.add(relTol.multiply(y0.abs(mc, descriptor("|y0|")), mc, descriptor("Relative tolerance · |y0|")),
                mc, descriptor("Error scale"));

        initialStep(ctx, mc, dp, y0, scale);

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }

    static void initialStep(DefaultComputationContext ctx, WrappedMathContext mc, Math74RkInitialStepDataProvider dp,
                            WrappedBigDecimal y0, WrappedBigDecimal scale) {
        final var t0 = ctx.wrapBigDecimal(dp.fetchInitialTime(), descriptor("Initial time, t0"));
        final var three = ctx.wrapBigDecimal(BigDecimal.valueOf(3), descriptor("Coefficient, 3"));
        final var hundredth = ctx.wrapBigDecimal(new BigDecimal("0.01"), descriptor("Step fraction, 0.01"));

        // === Rough first guess h = 0.01 · ‖y/scale‖ / ‖y'/scale‖ ===
        final var yDot0 = derivative(t0, y0, three, mc, "y'(t0)");
        final var yOnScale = y0.divide(scale, mc, descriptor("y0 / scale"));
        final var yOnScale2 = yOnScale.multiply(yOnScale, mc, descriptor("‖y0 / scale‖²"));
        final var yDotOnScale = yDot0.divide(scale, mc, descriptor("y'(t0) / scale"));
        final var yDotOnScale2 = yDotOnScale.multiply(yDotOnScale, mc, descriptor("‖y'(t0) / scale‖²"));
        final var hGuess = hundredth.multiply(yOnScale2.divide(yDotOnScale2, mc, descriptor("Norm ratio²")).sqrt(mc, descriptor("Norm ratio")),
                mc, descriptor("Rough step guess"));

        // === Euler step and second-derivative estimate ===
        final var y1 = y0.add(hGuess.multiply(yDot0, mc, descriptor("Euler increment")), mc, descriptor("Euler state, y1"));
        final var t1 = t0.add(hGuess, mc, descriptor("Euler time, t1"));
        final var yDot1 = derivative(t1, y1, three, mc, "y'(t1)");
        final var dDiff = yDot1.subtract(yDot0, mc, descriptor("Derivative change")).divide(scale, mc, descriptor("Derivative change / scale"));
        final var yDDotOnScale = dDiff.multiply(dDiff, mc, descriptor("‖Derivative change / scale‖²")).sqrt(mc, descriptor("‖Derivative change / scale‖"))
                .divide(hGuess, mc, descriptor("‖y'' / scale‖"));

        // === Step from h^order · max(‖y'/scale‖, ‖y''/scale‖) = 0.01 ===
        final var maxInv2 = yDotOnScale2.sqrt(mc, descriptor("‖y'(t0) / scale‖")).max(yDDotOnScale, descriptor("Largest scaled derivative"));
        final var order = ctx.wrapBigDecimal(BigDecimal.valueOf(dp.fetchOrder()), descriptor("Integrator order"));
        final var h1 = hundredth.divide(maxInv2, mc, descriptor("0.01 / largest scaled derivative"))
                .lnDouble(descriptor("ln(0.01 / largest scaled derivative)"))
                .divide(order, mc, descriptor("ln(...) / order"))
                .expDouble(descriptor("Order-root step estimate"));

        // === Clamp ===
        final var hundred = ctx.wrapBigDecimal(BigDecimal.valueOf(100), descriptor("Growth limit, 100"));
        final var tiny = ctx.wrapBigDecimal(new BigDecimal("1E-12"), descriptor("Cancellation guard, 1e-12"));
        final var minStep = ctx.wrapBigDecimal(dp.fetchMinStep(), descriptor("Minimum step"));
        final var maxStep = ctx.wrapBigDecimal(dp.fetchMaxStep(), descriptor("Maximum step"));
        hundred.multiply(hGuess.abs(mc, descriptor("|Rough step guess|")), mc, descriptor("Growth-limited step"))
                .min(h1, descriptor("Step before guards"))
                .max(tiny.multiply(t0.abs(mc, descriptor("|t0|")), mc, descriptor("Cancellation floor")), descriptor("Step after cancellation guard"))
                .max(minStep, descriptor("Step after minimum bound"))
                .min(maxStep, descriptor("Initial step size"));
    }

    // y' = 3t⁵ − y (TestProblem6)
    private static WrappedBigDecimal derivative(WrappedBigDecimal t, WrappedBigDecimal y, WrappedBigDecimal three,
                                                WrappedMathContext mc, String label) {
        final var t5 = t.multiplyBulk(List.of(t, t, t, t), mc, descriptor(label + ": t⁵"));
        return three.multiply(t5, mc, descriptor(label + ": 3t⁵")).subtract(y, mc, descriptor(label));
    }
}
