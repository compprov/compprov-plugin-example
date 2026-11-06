package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Buggy version of Defects4J Math-11 / MATH-929: the normalisation exponent is computed as the
 * integer division {@code -dim / 2}, which truncates −1/2 to 0 for odd dimensions.
 */
public class Math11MvnDensityCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Probability: multivariate normal density")));
        Math11MvnDensityDataProvider dp = new Math11MvnDensityDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var k = ctx.wrapBigDecimal(dp.fetchDimension(), descriptor("Dimension, k"));
        final var mu = ctx.wrapBigDecimal(dp.fetchMean(), descriptor("Mean, μ"));
        final var sigma = ctx.wrapBigDecimal(dp.fetchCovariance(), descriptor("Covariance, Σ"));
        final var x = ctx.wrapBigDecimal(dp.fetchPoint(), descriptor("Evaluation point, x"));
        final var pi = ctx.wrapBigDecimal(dp.fetchPi(), descriptor("π"));
        final var two = ctx.wrapBigDecimal(new BigDecimal("2"), descriptor("Constant 2"));
        final var minusHalf = ctx.wrapBigDecimal(new BigDecimal("-0.5"), descriptor("Constant −1/2"));
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("Constant 1"));

        // === Normalisation (2π)^(−k/2) ===
        final var twoPi = two.multiply(pi, mc, descriptor("2π"));
        final var normExponent = k.negate(mc, descriptor("−k")) //defect here: integer division
                .divideToIntegralValue(two, mc, descriptor("Normalisation exponent, −k/2"));
        final var lnTwoPi = twoPi.lnDouble(descriptor("ln(2π)"));
        final var normalisation = lnTwoPi.multiply(normExponent, mc, descriptor("ln of normalisation factor"))
                .expDouble(descriptor("Normalisation factor, (2π)^(−k/2)"));

        // === det(Σ)^(−1/2) ===
        final var detFactor = one.divide(sigma.sqrt(mc, descriptor("√det(Σ)")), mc, descriptor("Determinant factor, det(Σ)^(−1/2)"));

        // === exp(−½ (x−μ)ᵀ Σ⁻¹ (x−μ)) ===
        final var inverse = one.divide(sigma, mc, descriptor("Inverse covariance, Σ⁻¹"));
        final var diff = x.subtract(mu, mc, descriptor("Deviation, x − μ"));
        final var quad = diff.multiply(inverse, mc, descriptor("(x − μ)ᵀ Σ⁻¹"))
                .multiply(diff, mc, descriptor("Quadratic form, (x − μ)ᵀ Σ⁻¹ (x − μ)"));
        final var expTerm = quad.multiply(minusHalf, mc, descriptor("Exponent, −½ · quadratic form"))
                .expDouble(descriptor("Exponential term"));

        // === Density ===
        final var density = normalisation.multiply(detFactor, mc, descriptor("Normalisation · determinant factor"))
                .multiply(expTerm, mc, descriptor("Density, f(x)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
