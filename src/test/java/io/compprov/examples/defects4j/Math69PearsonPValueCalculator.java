package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.wrappers.WrappedBigDecimal;
import io.compprov.core.wrappers.WrappedMathContext;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Math-69 / MATH-371 (Commons Math {@code PearsonsCorrelation.getCorrelationPValues}):
 * Pearson r, t = |r·√((n − 2) / (1 − r²))|, and the two-sided p-value 2·CDF(−t), which stays representable
 * when p is tiny. As in {@code TDistribution.cumulativeProbability}, CDF(−t) = 0.5·I_x(ν/2, 1/2) with
 * x = ν / (ν + t²); for even ν the incomplete beta is the series tail √(1 − x)·Σ_{k ≥ ν/2} c_k·x^k,
 * c_k = c_{k−1}·(2k − 1)/(2k) (Abramowitz &amp; Stegun 26.7.3), truncated once terms drop below DECIMAL64.
 */
public class Math69PearsonPValueCalculator {

    // Tail terms beyond k = ν/2; each term is ~x ≈ 1/600 of the previous one, so 7 reach DECIMAL64.
    static final int TAIL_TERMS = 7;

    record TStatistic(WrappedBigDecimal t, WrappedBigDecimal dof, int dofValue) {
    }

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Statistics: p-value of a Pearson correlation")));
        Math69PearsonPValueDataProvider dp = new Math69PearsonPValueDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var ts = tStatistic(ctx, mc, dp);
        final var beta = incompleteBeta(ctx, mc, ts);

        // === Two-sided p-value ===
        final var half = ctx.wrapBigDecimal(new BigDecimal("0.5"), descriptor("One half, 0.5"));
        final var cdfMinusT = half.multiply(beta, mc, descriptor("t-distribution CDF at −t, 0.5·I_x"));
        final var two = ctx.wrapBigDecimal(BigDecimal.valueOf(2), descriptor("Two-sided factor, 2"));
        two.multiply(cdfMinusT, mc, descriptor("Correlation p-value"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }

    static WrappedBigDecimal incompleteBeta(DefaultComputationContext ctx, WrappedMathContext mc, TStatistic ts) {
        final var tSquared = ts.t().multiply(ts.t(), mc, descriptor("t²"));
        final var nuPlusTSquared = ts.dof().add(tSquared, mc, descriptor("ν + t²"));
        final var x = ts.dof().divide(nuPlusTSquared, mc, descriptor("x = ν / (ν + t²)"));
        final var sqrtOneMinusX = tSquared.divide(nuPlusTSquared, mc, descriptor("1 − x = t² / (ν + t²)"))
                .sqrt(mc, descriptor("√(1 − x)"));

        final int m = ts.dofValue() / 2;
        var term = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("[0] Series term, c_0 = 1"));
        final List<WrappedBigDecimal> tail = new ArrayList<>(TAIL_TERMS);
        for (int k = 1; k < m + TAIL_TERMS; k++) {
            final var odd = ctx.wrapBigDecimal(BigDecimal.valueOf(2L * k - 1), descriptor("[%d] 2k − 1".formatted(k)));
            final var even = ctx.wrapBigDecimal(BigDecimal.valueOf(2L * k), descriptor("[%d] 2k".formatted(k)));
            final var ratio = odd.divide(even, mc, descriptor("[%d] (2k − 1) / 2k".formatted(k)));
            term = term.multiply(x, mc, descriptor("[%d] c_{k−1}·x^k".formatted(k)))
                    .multiply(ratio, mc, descriptor("[%d] Series term, c_k·x^k".formatted(k)));
            if (k >= m) {
                tail.add(term);
            }
        }
        final var tailSum = tail.get(0).addBulk(tail.subList(1, tail.size()), mc, descriptor("Series tail, Σ_{k ≥ ν/2} c_k·x^k"));
        return sqrtOneMinusX.multiply(tailSum, mc, descriptor("Regularized incomplete beta, I_x(ν/2, 1/2)"));
    }

    static TStatistic tStatistic(DefaultComputationContext ctx, WrappedMathContext mc,
                                 Math69PearsonPValueDataProvider dp) {
        final List<BigDecimal> rawX = dp.fetchX();
        final List<BigDecimal> rawY = dp.fetchY();
        final int n = rawX.size();
        final List<WrappedBigDecimal> xs = new ArrayList<>(n);
        final List<WrappedBigDecimal> ys = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            xs.add(ctx.wrapBigDecimal(rawX.get(i), descriptor("[%d] x".formatted(i))));
            ys.add(ctx.wrapBigDecimal(rawY.get(i), descriptor("[%d] y".formatted(i))));
        }
        final var count = ctx.wrapBigDecimal(BigDecimal.valueOf(n), descriptor("Number of observations, n"));

        // === Pearson correlation ===
        final var meanX = xs.get(0).addBulk(xs.subList(1, n), mc, descriptor("Sum of x")).divide(count, mc, descriptor("Mean of x"));
        final var meanY = ys.get(0).addBulk(ys.subList(1, n), mc, descriptor("Sum of y")).divide(count, mc, descriptor("Mean of y"));
        final List<WrappedBigDecimal> xy = new ArrayList<>(n);
        final List<WrappedBigDecimal> xx = new ArrayList<>(n);
        final List<WrappedBigDecimal> yy = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            final var dx = xs.get(i).subtract(meanX, mc, descriptor("[%d] x deviation".formatted(i)));
            final var dy = ys.get(i).subtract(meanY, mc, descriptor("[%d] y deviation".formatted(i)));
            xy.add(dx.multiply(dy, mc, descriptor("[%d] dx·dy".formatted(i))));
            xx.add(dx.multiply(dx, mc, descriptor("[%d] dx²".formatted(i))));
            yy.add(dy.multiply(dy, mc, descriptor("[%d] dy²".formatted(i))));
        }
        final var sxy = xy.get(0).addBulk(xy.subList(1, n), mc, descriptor("Sxy"));
        final var sxx = xx.get(0).addBulk(xx.subList(1, n), mc, descriptor("Sxx"));
        final var syy = yy.get(0).addBulk(yy.subList(1, n), mc, descriptor("Syy"));
        final var r = sxy.divide(sxx.multiply(syy, mc, descriptor("Sxx·Syy")).sqrt(mc, descriptor("√(Sxx·Syy)")), mc,
                descriptor("Pearson correlation, r"));

        // === t statistic ===
        final var two = ctx.wrapBigDecimal(BigDecimal.valueOf(2), descriptor("Parameters estimated, 2"));
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("Unit, 1"));
        final var dof = count.subtract(two, mc, descriptor("Degrees of freedom, n − 2"));
        final var oneMinusR2 = one.subtract(r.multiply(r, mc, descriptor("r²")), mc, descriptor("1 − r²"));
        final var t = r.multiply(dof.divide(oneMinusR2, mc, descriptor("(n − 2) / (1 − r²)")).sqrt(mc, descriptor("√((n − 2) / (1 − r²))")),
                mc, descriptor("Signed t statistic")).abs(mc, descriptor("t statistic, |t|"));
        return new TStatistic(t, dof, n - 2);
    }
}
