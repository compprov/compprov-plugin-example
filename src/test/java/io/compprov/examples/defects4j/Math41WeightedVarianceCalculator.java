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
 * Fixed version of Defects4J Math-41 / MATH-704 (Commons Math {@code Variance.evaluate(values,
 * weights, mean, begin, length)}): bias-corrected weighted variance of a data segment,
 * var = (Σw·d² − (Σw·d)² / Σw) / (Σw − 1), d = x − weighted mean, all sums over the segment.
 */
public class Math41WeightedVarianceCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Statistics: weighted sample variance of a data segment")));
        Math41WeightedVarianceDataProvider dp = new Math41WeightedVarianceDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Segment observations and weights ===
        final List<BigDecimal> rawValues = dp.fetchValues();
        final List<BigDecimal> rawWeights = dp.fetchWeights();
        final int begin = dp.fetchSegmentBegin();
        final int length = dp.fetchSegmentLength();
        final List<WrappedBigDecimal> values = new ArrayList<>(length);
        final List<WrappedBigDecimal> weights = new ArrayList<>(length);
        for (int i = begin; i < begin + length; i++) {
            values.add(ctx.wrapBigDecimal(rawValues.get(i), descriptor("[%d] Observation".formatted(i))));
            weights.add(ctx.wrapBigDecimal(rawWeights.get(i), descriptor("[%d] Weight".formatted(i))));
        }

        // === Weighted mean ===
        final List<WrappedBigDecimal> weightedValues = new ArrayList<>(length);
        for (int k = 0; k < length; k++) {
            weightedValues.add(weights.get(k).multiply(values.get(k), mc, descriptor("[%d] Weighted observation".formatted(begin + k))));
        }
        final var sumWeightedValues = sum(weightedValues, mc, "Sum of weighted observations");
        final var meanSumWeights = sum(weights, mc, "Sum of weights (mean)");
        final var mean = sumWeightedValues.divide(meanSumWeights, mc, descriptor("Weighted mean"));

        // === Weighted deviations ===
        final List<WrappedBigDecimal> weightedSquaredDeviations = new ArrayList<>(length);
        final List<WrappedBigDecimal> weightedDeviations = new ArrayList<>(length);
        for (int k = 0; k < length; k++) {
            final int i = begin + k;
            final var deviation = values.get(k).subtract(mean, mc, descriptor("[%d] Deviation from weighted mean".formatted(i)));
            final var squaredDeviation = deviation.multiply(deviation, mc, descriptor("[%d] Squared deviation".formatted(i)));
            weightedSquaredDeviations.add(weights.get(k).multiply(squaredDeviation, mc, descriptor("[%d] Weighted squared deviation".formatted(i))));
            weightedDeviations.add(weights.get(k).multiply(deviation, mc, descriptor("[%d] Weighted deviation".formatted(i))));
        }
        final var accum = sum(weightedSquaredDeviations, mc, "Sum of weighted squared deviations");
        final var accum2 = sum(weightedDeviations, mc, "Sum of weighted deviations");

        // === Bias-corrected weighted variance ===
        final var sumWeights = sum(weights, mc, "Sum of weights (variance)");
        final var accum2Squared = accum2.multiply(accum2, mc, descriptor("Squared sum of weighted deviations"));
        final var correction = accum2Squared.divide(sumWeights, mc, descriptor("Correction term"));
        final var numerator = accum.subtract(correction, mc, descriptor("Corrected sum of weighted squared deviations"));
        final var one = ctx.wrapBigDecimal(BigDecimal.ONE, descriptor("Bias correction, 1"));
        final var denominator = sumWeights.subtract(one, mc, descriptor("Effective degrees of freedom, Σw − 1"));
        final var variance = numerator.divide(denominator, mc, descriptor("Weighted sample variance"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }

    private static WrappedBigDecimal sum(List<WrappedBigDecimal> terms, WrappedMathContext mc, String label) {
        return terms.get(0).addBulk(terms.subList(1, terms.size()), mc, descriptor(label));
    }
}
