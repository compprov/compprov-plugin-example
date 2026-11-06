package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Buggy version of Defects4J Math-74 / MATH-338: the error scale is the absolute tolerance alone, so the
 * relative-tolerance term is lost, the scaled derivatives are overstated and the initial step is too small.
 */
public class Math74RkInitialStepCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("ODE: Runge-Kutta initial step size from error scale")));
        Math74RkInitialStepDataProvider dp = new Math74RkInitialStepDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var y0 = ctx.wrapBigDecimal(dp.fetchInitialState(), descriptor("Initial state, y0"));
        final var absTol = ctx.wrapBigDecimal(dp.fetchAbsoluteTolerance(), descriptor("Absolute tolerance"));
        ctx.wrapBigDecimal(dp.fetchRelativeTolerance(), descriptor("Relative tolerance"));

        // === Error scale ===
        final var scale = absTol.plus(mc, descriptor("Error scale")); //defect here: relTol·|y| term dropped

        Math74RkInitialStepCalculator.initialStep(ctx, mc, dp, y0, scale);

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
