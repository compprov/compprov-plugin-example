package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fixed version of Defects4J Math-91 / MATH-252 (Commons Math {@code Fraction.compareTo}): compares the
 * exact cross products n1·d2 and d1·n2 in long arithmetic (DECIMAL128 here, exact for these 18-digit products).
 */
public class Math91FractionCompareCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Fractions: ordering of two close approximations of pi")));
        Math91FractionCompareDataProvider dp = new Math91FractionCompareDataProvider();

        final var exact = ctx.wrapMathContext(MathContext.DECIMAL128, descriptor("Exact long arithmetic (DECIMAL128)"));

        final var n1 = ctx.wrapBigDecimal(dp.fetchFirstNumerator(), descriptor("First numerator"));
        final var d1 = ctx.wrapBigDecimal(dp.fetchFirstDenominator(), descriptor("First denominator"));
        final var n2 = ctx.wrapBigDecimal(dp.fetchSecondNumerator(), descriptor("Second numerator"));
        final var d2 = ctx.wrapBigDecimal(dp.fetchSecondDenominator(), descriptor("Second denominator"));

        final var nOd = n1.multiply(d2, exact, descriptor("First numerator · second denominator"));
        final var dOn = d1.multiply(n2, exact, descriptor("First denominator · second numerator"));
        nOd.compare(dOn, descriptor("Fraction comparison"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
