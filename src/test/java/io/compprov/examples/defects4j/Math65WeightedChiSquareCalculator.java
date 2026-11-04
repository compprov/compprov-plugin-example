package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.wrappers.WrappedBigDecimal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Math-65 / MATH-377 (Commons Math {@code AbstractLeastSquaresOptimizer.getChiSquare}):
 * circle-fit residuals r = mean radius − distance, χ² = Σ w·r², RMS = √(χ² / n), and the parameter-error
 * scale √(χ² / (n − p)) that {@code guessParametersErrors} multiplies into √cov.
 */
public class Math65WeightedChiSquareCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Least squares: weighted chi-square of a circle fit")));
        Math65WeightedChiSquareDataProvider dp = new Math65WeightedChiSquareDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Distances to the fitted centre ===
        final var cx = ctx.wrapBigDecimal(dp.fetchCenterX(), descriptor("Fitted centre x"));
        final var cy = ctx.wrapBigDecimal(dp.fetchCenterY(), descriptor("Fitted centre y"));
        final List<BigDecimal> rawX = dp.fetchPointsX();
        final List<BigDecimal> rawY = dp.fetchPointsY();
        final int n = rawX.size();
        final List<WrappedBigDecimal> distances = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            final var px = ctx.wrapBigDecimal(rawX.get(i), descriptor("[%d] Point x".formatted(i)));
            final var py = ctx.wrapBigDecimal(rawY.get(i), descriptor("[%d] Point y".formatted(i)));
            final var dx = px.subtract(cx, mc, descriptor("[%d] x offset from centre".formatted(i)));
            final var dy = py.subtract(cy, mc, descriptor("[%d] y offset from centre".formatted(i)));
            final var d2 = dx.multiply(dx, mc, descriptor("[%d] dx²".formatted(i)))
                    .add(dy.multiply(dy, mc, descriptor("[%d] dy²".formatted(i))), mc, descriptor("[%d] Squared distance".formatted(i)));
            distances.add(d2.sqrt(mc, descriptor("[%d] Distance to centre".formatted(i))));
        }
        final var count = ctx.wrapBigDecimal(BigDecimal.valueOf(n), descriptor("Number of observations, n"));
        final var radius = distances.get(0).addBulk(distances.subList(1, n), mc, descriptor("Sum of distances"))
                .divide(count, mc, descriptor("Fitted radius (mean distance)"));

        // === Weighted squared residuals ===
        final var weight = ctx.wrapBigDecimal(dp.fetchWeight(), descriptor("Observation weight, w"));
        final List<WrappedBigDecimal> weightedSquares = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            final var r = radius.subtract(distances.get(i), mc, descriptor("[%d] Residual".formatted(i)));
            final var r2 = r.multiply(r, mc, descriptor("[%d] Squared residual".formatted(i)));
            weightedSquares.add(r2.multiply(weight, mc, descriptor("[%d] Weighted squared residual".formatted(i))));
        }
        final var chiSquare = weightedSquares.get(0).addBulk(weightedSquares.subList(1, n), mc, descriptor("Chi-square, Σ w·r²"));

        // === RMS and parameter-error scale ===
        chiSquare.divide(count, mc, descriptor("Mean weighted squared residual")).sqrt(mc, descriptor("RMS"));
        final var params = ctx.wrapBigDecimal(BigDecimal.valueOf(dp.fetchParameterCount()), descriptor("Number of parameters, p"));
        final var dof = count.subtract(params, mc, descriptor("Degrees of freedom, n − p"));
        chiSquare.divide(dof, mc, descriptor("Chi-square per degree of freedom"))
                .sqrt(mc, descriptor("Parameter error scale, √(χ² / (n − p))"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
