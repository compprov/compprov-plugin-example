package io.compprov.examples.circuit;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * A DC circuit with two resistors in series ({@code Rseries = R1 + R2}) feeding a parallel pair
 * ({@code Rparallel = (R3 × R4) / (R3 + R4)}). Given a supply voltage, computes total resistance,
 * total current via Ohm's law ({@code I = V / R}), and total power dissipated, cross-checked two
 * ways ({@code P = I × V} and {@code P = I² × R}).
 */
public class ElectricalCircuitCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Electrical circuit: series-parallel resistor network")));
        ElectricalCircuitDataProvider dp = new ElectricalCircuitDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Inputs ===
        final var r1 = ctx.wrapBigDecimal(dp.fetchR1(), descriptor("Resistor R1, series (Ω)"));
        final var r2 = ctx.wrapBigDecimal(dp.fetchR2(), descriptor("Resistor R2, series (Ω)"));
        final var r3 = ctx.wrapBigDecimal(dp.fetchR3(), descriptor("Resistor R3, parallel (Ω)"));
        final var r4 = ctx.wrapBigDecimal(dp.fetchR4(), descriptor("Resistor R4, parallel (Ω)"));
        final var v = ctx.wrapBigDecimal(dp.fetchSupplyVoltage(), descriptor("Supply voltage (V)"));

        // === Series segment ===
        final var rSeries = r1.add(r2, mc, descriptor("Series resistance, R1 + R2 (Ω)"));

        // === Parallel segment: Rparallel = (R3 × R4) / (R3 + R4) ===
        final var r3r4Product = r3.multiply(r4, mc, descriptor("R3 × R4"));
        final var r3r4Sum = r3.add(r4, mc, descriptor("R3 + R4 (Ω)"));
        final var rParallel = r3r4Product.divide(r3r4Sum, mc, descriptor("Parallel resistance, Rparallel (Ω)"));

        // === Total resistance ===
        final var rTotal = rSeries.add(rParallel, mc, descriptor("Total resistance, Rtotal (Ω)"));

        // === Ohm's law: I = V / Rtotal ===
        final var current = v.divide(rTotal, mc, descriptor("Total current, I (A)"));

        // === Power: P = I × V ===
        final var power = current.multiply(v, mc, descriptor("Total power dissipated, P = I × V (W)"));

        // === Cross-check: P = I² × Rtotal ===
        final var exponent2 = ctx.wrapInteger(2, descriptor("Exponent 2 (square)"));
        final var currentSquared = current.pow(exponent2, mc, descriptor("I² (A²)"));
        final var powerCheck = currentSquared.multiply(rTotal, mc, descriptor("Total power cross-check, P = I² × Rtotal (W)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
