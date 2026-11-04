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
 * Buggy version of Defects4J Math-65 / MATH-377: χ² divides each squared residual by its weight
 * (Σ r²/w) while the RMS separately multiplies by it (Σ w·r²), so the parameter-error scale is off by
 * a factor of w.
 */
public class Math65WeightedChiSquareCalculatorBuggy {

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

        // === Squared residuals, weighted two different ways ===
        final var weight = ctx.wrapBigDecimal(dp.fetchWeight(), descriptor("Observation weight, w"));
        final List<WrappedBigDecimal> rmsTerms = new ArrayList<>(n);
        final List<WrappedBigDecimal> chiSquareTerms = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            final var r = radius.subtract(distances.get(i), mc, descriptor("[%d] Residual".formatted(i)));
            final var r2 = r.multiply(r, mc, descriptor("[%d] Squared residual".formatted(i)));
            rmsTerms.add(r2.multiply(weight, mc, descriptor("[%d] Weighted squared residual".formatted(i))));
            chiSquareTerms.add(r2.divide(weight, mc, descriptor("[%d] Chi-square term".formatted(i)))); //defect here: r²/w instead of r²·w
        }
        final var criterion = rmsTerms.get(0).addBulk(rmsTerms.subList(1, n), mc, descriptor("RMS criterion, Σ w·r²"));
        final var chiSquare = chiSquareTerms.get(0).addBulk(chiSquareTerms.subList(1, n), mc, descriptor("Chi-square, Σ r²/w"));

        // === RMS and parameter-error scale ===
        criterion.divide(count, mc, descriptor("Mean weighted squared residual")).sqrt(mc, descriptor("RMS"));
        final var params = ctx.wrapBigDecimal(BigDecimal.valueOf(dp.fetchParameterCount()), descriptor("Number of parameters, p"));
        final var dof = count.subtract(params, mc, descriptor("Degrees of freedom, n − p"));
        chiSquare.divide(dof, mc, descriptor("Chi-square per degree of freedom"))
                .sqrt(mc, descriptor("Parameter error scale, √(χ² / (n − p))"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
