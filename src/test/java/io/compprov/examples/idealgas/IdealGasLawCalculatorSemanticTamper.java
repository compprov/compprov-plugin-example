package io.compprov.examples.idealgas;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

public class IdealGasLawCalculatorSemanticTamper {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Ideal gas law: isothermal compression")));
        IdealGasLawDataProvider dp = new IdealGasLawDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Inputs ===
        final var n = ctx.wrapBigDecimal(dp.fetchMoles(), descriptor("Amount of gas (mol)"));
        final var r = ctx.wrapBigDecimal(dp.fetchGasConstant(), descriptor("Gas constant R (L·atm/(mol·K))"));
        // tampered: this is the Celsius reading (25°C), not Kelvin, despite the label below.
        final var t = ctx.wrapBigDecimal(new BigDecimal("25"), descriptor("Temperature (C)"));
        final var v1 = ctx.wrapBigDecimal(dp.fetchInitialVolume(), descriptor("Initial volume (L)"));
        final var v2 = ctx.wrapBigDecimal(dp.fetchCompressedVolume(), descriptor("Compressed volume (L)"));

        // === P1 = nRT / V1 ===
        final var nR = n.multiply(r, mc, descriptor("n × R"));
        final var nRT = nR.multiply(t, mc, descriptor("n × R × T"));
        final var p1 = nRT.divide(v1, mc, descriptor("Pressure at initial volume, P1 (atm)"));

        // === Cross-check via Boyle's law: P1V1 = P2V2, so P2 = P1V1 / V2 ===
        final var p1v1 = p1.multiply(v1, mc, descriptor("P1 × V1"));
        final var p2 = p1v1.divide(v2, mc, descriptor("Pressure at compressed volume, P2 (atm), via Boyle's law"));
        final var p2v2 = p2.multiply(v2, mc, descriptor("P2 × V2"));
        final var pvCmpNrt = p1v1.compare(nRT, descriptor("PV cmp nRT (0 - equal)"));
        final var p1v1CmpP2v2 = p1v1.compare(p2v2, descriptor("P1V1 cmp P2V2 (0 - equal)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
