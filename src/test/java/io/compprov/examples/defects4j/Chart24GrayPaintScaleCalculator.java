package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Chart-24 (JFreeChart {@code GrayPaintScale.getPaint}): the value is clamped to
 * [lower, upper] and the gray level is g = (int) ((v − lower) / (upper − lower) · 255).
 */
public class Chart24GrayPaintScaleCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Charting: gray level for a paint-scale lookup")));
        Chart24GrayPaintScaleDataProvider dp = new Chart24GrayPaintScaleDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var toInt = ctx.wrapMathContext(new MathContext(0, RoundingMode.DOWN), descriptor("Truncate to integer ((int) cast)"));

        final var value = ctx.wrapBigDecimal(dp.fetchValue(), descriptor("Lookup value"));
        final var lower = ctx.wrapBigDecimal(dp.fetchLowerBound(), descriptor("Lower bound"));
        final var upper = ctx.wrapBigDecimal(dp.fetchUpperBound(), descriptor("Upper bound"));
        final var levels = ctx.wrapBigDecimal(BigDecimal.valueOf(255), descriptor("Maximum gray level, 255"));

        // === Clamp to the scale range ===
        final var v = value.max(lower, descriptor("Value raised to lower bound"))
                .min(upper, descriptor("Clamped value"));

        // === Gray level ===
        final var range = upper.subtract(lower, mc, descriptor("Scale range, upper − lower"));
        v.subtract(lower, mc, descriptor("Offset from lower bound"))
                .divide(range, mc, descriptor("Relative position in range"))
                .multiply(levels, mc, descriptor("Scaled gray level"))
                .setScale(toInt, descriptor("Gray level, g"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
