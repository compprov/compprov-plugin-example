package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Buggy version of Defects4J Math-18 / MATH-867: encoding first shifts by the lower bound,
 * (x − lower) / (upper − lower), so with a huge |lower| the shift swallows the candidate's digits and
 * the decoded point (upper − lower) · x + lower comes back coarse.
 */
public class Math18CmaesBoundsCalculatorBuggy {

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
        final var shifted = x.subtract(lower, mc, descriptor("Candidate shifted by lower bound")); //defect here: offset by the lower bound
        final var encoded = shifted.divide(diff, mc, descriptor("Encoded candidate"));

        // === Feasibility against the unit interval ===
        final var zero = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Encoded lower limit, 0"));
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("Encoded upper limit, 1"));
        encoded.compare(zero, descriptor("Encoded candidate vs encoded lower bound"));
        encoded.compare(one, descriptor("Encoded candidate vs encoded upper bound"));

        // === Decode back to the user's space ===
        final var scaled = diff.multiply(encoded, mc, descriptor("Rescaled candidate"));
        final var decoded = scaled.add(lower, mc, descriptor("Decoded candidate"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
