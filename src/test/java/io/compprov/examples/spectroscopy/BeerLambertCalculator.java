package io.compprov.examples.spectroscopy;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Meta;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Beer-Lambert law, linear form: {@code A = ε × c × l} (molar absorptivity × molar concentration
 * × path length), applied to a 3-point calibration series of the same dye solution to demonstrate
 * the linear relationship between absorbance and concentration.
 */
public class BeerLambertCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Spectroscopy: Beer-Lambert calibration series")));
        BeerLambertDataProvider dp = new BeerLambertDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Shared instrument constants ===
        final var epsilon = ctx.wrapBigDecimal(dp.fetchMolarAbsorptivity(), descriptor("Molar absorptivity, ε (L/(mol·cm))"));
        final var pathLength = ctx.wrapBigDecimal(dp.fetchPathLength(), descriptor("Path length, l (cm)"));

        // === Calibration point 1 ===
        final var c1 = ctx.wrapBigDecimal(dp.fetchConcentration1(), descriptor("Concentration (mol/L)", Meta.of("point", "1")));
        final var epsC1 = epsilon.multiply(c1, mc, descriptor("ε × c (point 1)"));
        final var a1 = epsC1.multiply(pathLength, mc, descriptor("Absorbance, A1", Meta.of("point", "1")));

        // === Calibration point 2 ===
        final var c2 = ctx.wrapBigDecimal(dp.fetchConcentration2(), descriptor("Concentration (mol/L)", Meta.of("point", "2")));
        final var epsC2 = epsilon.multiply(c2, mc, descriptor("ε × c (point 2)"));
        final var a2 = epsC2.multiply(pathLength, mc, descriptor("Absorbance, A2", Meta.of("point", "2")));

        // === Calibration point 3 ===
        final var c3 = ctx.wrapBigDecimal(dp.fetchConcentration3(), descriptor("Concentration (mol/L)", Meta.of("point", "3")));
        final var epsC3 = epsilon.multiply(c3, mc, descriptor("ε × c (point 3)"));
        final var a3 = epsC3.multiply(pathLength, mc, descriptor("Absorbance, A3", Meta.of("point", "3")));

        // === Linearity cross-checks: absorbance ratios should track concentration ratios ===
        final var ratio21 = a2.divide(a1, mc, descriptor("Absorbance ratio A2/A1 (expect ≈ c2/c1)"));
        final var ratio31 = a3.divide(a1, mc, descriptor("Absorbance ratio A3/A1 (expect ≈ c3/c1)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
