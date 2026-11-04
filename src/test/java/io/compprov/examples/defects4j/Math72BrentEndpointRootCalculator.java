package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.wrappers.WrappedBigDecimal;
import io.compprov.core.wrappers.WrappedMathContext;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Math-72 / MATH-344 (Commons Math {@code BrentSolver.solve(f, min, max, initial)}):
 * if |f(initial)| is above the accuracy but |f(min)| is within it, the endpoint min is returned as the root.
 * Each early-exit check becomes a 0/1 flag, 1 − max(compare(|f|, accuracy), 0), that selects the returned
 * value; the later branches (bracketing, max endpoint, Brent iterations) aren't reached for this data.
 */
public class Math72BrentEndpointRootCalculator {

    record EarlyExit(WrappedBigDecimal initialBranch, WrappedBigDecimal minBranchTaken, WrappedBigDecimal yMin) {
    }

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Root finding: Brent solver early exit at a root endpoint")));
        Math72BrentEndpointRootDataProvider dp = new Math72BrentEndpointRootDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var min = ctx.wrapBigDecimal(dp.fetchMin(), descriptor("Lower endpoint, min"));
        final var exit = earlyExit(ctx, mc, dp);

        // === Result: the endpoint itself ===
        final var minBranch = exit.minBranchTaken().multiply(min, mc, descriptor("Lower-endpoint branch value"));
        exit.initialBranch().add(minBranch, mc, descriptor("Root"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }

    static EarlyExit earlyExit(DefaultComputationContext ctx, WrappedMathContext mc, Math72BrentEndpointRootDataProvider dp) {
        final var initial = ctx.wrapBigDecimal(dp.fetchInitial(), descriptor("Initial guess"));
        final var yInitial = ctx.wrapBigDecimal(dp.fetchValueAtInitial(), descriptor("f(initial) = sin(initial)"));
        final var yMin = ctx.wrapBigDecimal(dp.fetchValueAtMin(), descriptor("f(min) = sin(min)"));
        final var accuracy = ctx.wrapBigDecimal(dp.fetchFunctionValueAccuracy(), descriptor("Function value accuracy"));
        final var zero = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Zero"));
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("One"));

        // === Early-exit checks: 1 if |f| ≤ accuracy, else 0 ===
        final var initialOk = one.subtract(yInitial.abs(mc, descriptor("|f(initial)|"))
                .compare(accuracy, descriptor("|f(initial)| vs accuracy"))
                .max(zero, descriptor("|f(initial)| above accuracy, 1 = yes")), mc, descriptor("Initial guess within accuracy, 1 = yes"));
        final var minOk = one.subtract(yMin.abs(mc, descriptor("|f(min)|"))
                .compare(accuracy, descriptor("|f(min)| vs accuracy"))
                .max(zero, descriptor("|f(min)| above accuracy, 1 = yes")), mc, descriptor("Lower endpoint within accuracy, 1 = yes"));

        // === Branch selection: the initial guess wins first, then the lower endpoint ===
        final var initialBranch = initialOk.multiply(initial, mc, descriptor("Initial-guess branch value"));
        final var minBranchTaken = one.subtract(initialOk, mc, descriptor("Initial guess rejected, 1 = yes"))
                .multiply(minOk, mc, descriptor("Lower-endpoint branch taken, 1 = yes"));
        return new EarlyExit(initialBranch, minBranchTaken, yMin);
    }
}
