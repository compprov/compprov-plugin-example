package io.compprov.examples.heatconduction;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Fourier's law of heat conduction {@code Q = (k × A × ΔT) / d} through a two-layer wall in
 * series (brick + insulation). The interface temperature between the layers is derived from the
 * per-layer thermal resistances ({@code R = d/k}) so that the steady-state heat flux computed
 * independently through each layer via Fourier's law agrees.
 */
public class HeatConductionCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Heat conduction: two-layer wall, steady state")));
        HeatConductionDataProvider dp = new HeatConductionDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Inputs ===
        final var tIn = ctx.wrapBigDecimal(dp.fetchInteriorTemp(), descriptor("Interior temperature (°C)"));
        final var tOut = ctx.wrapBigDecimal(dp.fetchExteriorTemp(), descriptor("Exterior temperature (°C)"));
        final var area = ctx.wrapBigDecimal(dp.fetchWallArea(), descriptor("Wall area, A (m²)"));
        final var k1 = ctx.wrapBigDecimal(dp.fetchLayer1Conductivity(), descriptor("Layer 1 (brick) thermal conductivity, k1 (W/(m·K))"));
        final var d1 = ctx.wrapBigDecimal(dp.fetchLayer1Thickness(), descriptor("Layer 1 (brick) thickness, d1 (m)"));
        final var k2 = ctx.wrapBigDecimal(dp.fetchLayer2Conductivity(), descriptor("Layer 2 (insulation) thermal conductivity, k2 (W/(m·K))"));
        final var d2 = ctx.wrapBigDecimal(dp.fetchLayer2Thickness(), descriptor("Layer 2 (insulation) thickness, d2 (m)"));

        // === Total temperature difference and per-layer thermal resistance (R = d/k) ===
        final var deltaTTotal = tIn.subtract(tOut, mc, descriptor("Total temperature difference, interior - exterior (K)"));
        final var r1 = d1.divide(k1, mc, descriptor("Thermal resistance, layer 1 (m²·K/W)"));
        final var r2 = d2.divide(k2, mc, descriptor("Thermal resistance, layer 2 (m²·K/W)"));
        final var rTotal = r1.add(r2, mc, descriptor("Total thermal resistance (m²·K/W)"));

        // === Steady-state heat flux, and the interface temperature it implies ===
        final var heatFlux = deltaTTotal.divide(rTotal, mc, descriptor("Steady-state heat flux (W/m²)"));
        final var deltaT1 = heatFlux.multiply(r1, mc, descriptor("Temperature drop across layer 1 (K)"));
        final var interfaceTemp = tIn.subtract(deltaT1, mc, descriptor("Interface temperature, brick/insulation (°C)"));
        final var deltaT2 = interfaceTemp.subtract(tOut, mc, descriptor("Temperature drop across layer 2 (K)"));

        // === Heat flow through each layer, via Fourier's law directly (should agree) ===
        final var k1A = k1.multiply(area, mc, descriptor("k1 × A (layer 1)"));
        final var k1ADeltaT1 = k1A.multiply(deltaT1, mc, descriptor("k1 × A × ΔT1 (layer 1)"));
        final var q1 = k1ADeltaT1.divide(d1, mc, descriptor("Heat flow through layer 1, Q1 (W)"));

        final var k2A = k2.multiply(area, mc, descriptor("k2 × A (layer 2)"));
        final var k2ADeltaT2 = k2A.multiply(deltaT2, mc, descriptor("k2 × A × ΔT2 (layer 2)"));
        final var q2 = k2ADeltaT2.divide(d2, mc, descriptor("Heat flow through layer 2, Q2 (W)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
