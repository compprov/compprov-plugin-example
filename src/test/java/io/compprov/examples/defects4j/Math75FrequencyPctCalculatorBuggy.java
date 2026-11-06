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
 * Buggy version of Defects4J Math-75 / MATH-329: {@code getPct(Object)} delegates to
 * {@code getCumPct}, so the reported percentage is the cumulative share of all values ≤ v.
 */
public class Math75FrequencyPctCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Statistics: frequency table percentage")));
        Math75FrequencyPctDataProvider dp = new Math75FrequencyPctDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Frequency table ===
        final List<BigDecimal> rawValues = dp.fetchDistinctValues();
        final List<BigDecimal> rawCounts = dp.fetchCounts();
        final List<WrappedBigDecimal> counts = new ArrayList<>(rawCounts.size());
        for (int i = 0; i < rawCounts.size(); i++) {
            counts.add(ctx.wrapBigDecimal(rawCounts.get(i), descriptor("Count of value %s".formatted(rawValues.get(i)))));
        }
        final var total = counts.get(0).addBulk(counts.subList(1, counts.size()), mc, descriptor("Total count"));

        // === Percentage of the queried value ===
        final int q = dp.fetchQueriedValueIndex();
        final var cumulativeCount = counts.get(0).addBulk(counts.subList(1, q + 1), mc, //defect here: cumulative count
                descriptor("Cumulative count up to value %s".formatted(rawValues.get(q))));
        final var pct = cumulativeCount.divide(total, mc, descriptor("Percentage of value %s".formatted(rawValues.get(q))));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
