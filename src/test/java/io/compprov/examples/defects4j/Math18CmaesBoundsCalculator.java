package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Math-18 / MATH-867 (Commons Math {@code CMAESOptimizer.encode/decode/isFeasible}):
 * a bounded candidate is scaled into the optimizer's space as x / (upper − lower), checked against the
 * equally scaled bounds, and decoded back as (upper − lower) · x.
 */
public class Math18CmaesBoundsCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Optimization: CMA-ES bounded-variable encoding round trip")));
        Math18CmaesBoundsDataProvider dp = new Math18CmaesBoundsDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var x = ctx.wrapBigDecimal(dp.fetchCandidate(), descriptor("Candidate point, x"));
        final var lower = ctx.wrapBigDecimal(dp.fetchLowerBound(), descriptor("Lower bound"));
        final var upper = ctx.wrapBigDecimal(dp.fetchUpperBound(), descriptor("Upper bound"));

        // === Encode into the optimizer's space ===
        final var diff = upper.subtract(lower, mc, descriptor("Bound range, upper − lower"));
        final var encoded = x.divide(diff, mc, descriptor("Encoded candidate"));

        // === Feasibility against the encoded bounds ===
        final var lowerEncoded = lower.divide(diff, mc, descriptor("Encoded lower bound"));
        final var upperEncoded = upper.divide(diff, mc, descriptor("Encoded upper bound"));
        encoded.compare(lowerEncoded, descriptor("Encoded candidate vs encoded lower bound"));
        encoded.compare(upperEncoded, descriptor("Encoded candidate vs encoded upper bound"));

        // === Decode back to the user's space ===
        final var decoded = diff.multiply(encoded, mc, descriptor("Decoded candidate"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
