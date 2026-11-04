package io.compprov.examples.defects4j;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Buggy version of Defects4J Math-91 / MATH-252: compares the fractions' double values (DECIMAL64 here);
 * both round to 3.141592653589793, so two different fractions compare as equal.
 */
public class Math91FractionCompareCalculatorBuggy {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Fractions: ordering of two close approximations of pi")));
        Math91FractionCompareDataProvider dp = new Math91FractionCompareDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Double precision (DECIMAL64)"));

        final var n1 = ctx.wrapBigDecimal(dp.fetchFirstNumerator(), descriptor("First numerator"));
        final var d1 = ctx.wrapBigDecimal(dp.fetchFirstDenominator(), descriptor("First denominator"));
        final var n2 = ctx.wrapBigDecimal(dp.fetchSecondNumerator(), descriptor("Second numerator"));
        final var d2 = ctx.wrapBigDecimal(dp.fetchSecondDenominator(), descriptor("Second denominator"));

        final var v1 = n1.divide(d1, mc, descriptor("First fraction value")); //defect here: compares rounded quotients
        final var v2 = n2.divide(d2, mc, descriptor("Second fraction value"));
        v1.compare(v2, descriptor("Fraction comparison"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
