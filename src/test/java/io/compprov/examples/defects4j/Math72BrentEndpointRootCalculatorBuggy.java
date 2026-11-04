package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Buggy version of Defects4J Math-72 / MATH-344: on the endpoint early exit the solver returns
 * f(min), the function value, instead of min, the abscissa.
 */
public class Math72BrentEndpointRootCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Root finding: Brent solver early exit at a root endpoint")));
        Math72BrentEndpointRootDataProvider dp = new Math72BrentEndpointRootDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var min = ctx.wrapBigDecimal(dp.fetchMin(), descriptor("Lower endpoint, min"));
        final var exit = Math72BrentEndpointRootCalculator.earlyExit(ctx, mc, dp);

        // === Result ===
        final var minBranch = exit.minBranchTaken().multiply(exit.yMin(), mc, descriptor("Lower-endpoint branch value")); //defect here: f(min) instead of min
        exit.initialBranch().add(minBranch, mc, descriptor("Root"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
