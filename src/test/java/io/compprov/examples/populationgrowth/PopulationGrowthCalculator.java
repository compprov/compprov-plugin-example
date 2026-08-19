package io.compprov.examples.populationgrowth;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Meta;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Continuous exponential population growth {@code N(t) = N0 × e^(r×t)} for a bacterial culture,
 * evaluated at three future time checkpoints.
 */
public class PopulationGrowthCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Biology: exponential population growth")));
        PopulationGrowthDataProvider dp = new PopulationGrowthDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Inputs ===
        final var n0 = ctx.wrapBigDecimal(dp.fetchInitialPopulation(), descriptor("Initial population, N0 (cells)"));
        final var r = ctx.wrapBigDecimal(dp.fetchGrowthRate(), descriptor("Continuous growth rate, r (per hour)"));
        final var t1 = ctx.wrapBigDecimal(dp.fetchTime1(), descriptor("Time checkpoint t1 (hours)", Meta.of("checkpoint", "1")));
        final var t2 = ctx.wrapBigDecimal(dp.fetchTime2(), descriptor("Time checkpoint t2 (hours)", Meta.of("checkpoint", "2")));
        final var t3 = ctx.wrapBigDecimal(dp.fetchTime3(), descriptor("Time checkpoint t3 (hours)", Meta.of("checkpoint", "3")));

        // === Checkpoint 1: N(t1) = N0 × e^(r×t1) ===
        final var rt1 = r.multiply(t1, mc, descriptor("r × t1 (growth exponent)"));
        final var expRt1 = rt1.expDouble(descriptor("e^(r×t1)"));
        final var nT1 = n0.multiply(expRt1, mc, descriptor("Population at t1 (cells)", Meta.of("checkpoint", "1")));

        // === Checkpoint 2: N(t2) = N0 × e^(r×t2) ===
        final var rt2 = r.multiply(t2, mc, descriptor("r × t2 (growth exponent)"));
        final var expRt2 = rt2.expDouble(descriptor("e^(r×t2)"));
        final var nT2 = n0.multiply(expRt2, mc, descriptor("Population at t2 (cells)", Meta.of("checkpoint", "2")));

        // === Checkpoint 3: N(t3) = N0 × e^(r×t3) ===
        final var rt3 = r.multiply(t3, mc, descriptor("r × t3 (growth exponent)"));
        final var expRt3 = rt3.expDouble(descriptor("e^(r×t3)"));
        final var nT3 = n0.multiply(expRt3, mc, descriptor("Population at t3 (cells)", Meta.of("checkpoint", "3")));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
