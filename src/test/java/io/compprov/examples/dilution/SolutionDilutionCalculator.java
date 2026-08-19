package io.compprov.examples.dilution;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Meta;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Two sequential dilution steps of a NaCl stock solution, each governed by
 * {@code C1V1 = C2V2}, ending at a target working concentration. Also computes the mass of
 * solute present in the final working volume ({@code mass = concentration × volume × molarMass}).
 */
public class SolutionDilutionCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Chemistry: serial dilution of NaCl stock")));
        SolutionDilutionDataProvider dp = new SolutionDilutionDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Dilution step 1: stock -> intermediate concentration ===
        final var stockConcentration = ctx.wrapBigDecimal(
                dp.fetchStockConcentration(), descriptor("Stock concentration, C1 (mol/L)", Meta.of("step", "1")));
        final var step1AliquotVolume = ctx.wrapBigDecimal(
                dp.fetchStep1AliquotVolume(), descriptor("Stock aliquot volume, V1 (L)", Meta.of("step", "1")));
        final var step1FinalVolume = ctx.wrapBigDecimal(
                dp.fetchStep1FinalVolume(), descriptor("Step 1 final volume, V2 (L)", Meta.of("step", "1")));

        final var step1C1V1 = stockConcentration.multiply(step1AliquotVolume, mc, descriptor("C1 × V1 (step 1)"));
        final var intermediateConcentration = step1C1V1.divide(
                step1FinalVolume, mc, descriptor("Intermediate concentration (mol/L)", Meta.of("step", "1")));

        // === Dilution step 2: intermediate -> working concentration ===
        final var step2AliquotVolume = ctx.wrapBigDecimal(
                dp.fetchStep2AliquotVolume(), descriptor("Intermediate aliquot volume, V1 (L)", Meta.of("step", "2")));
        final var step2FinalVolume = ctx.wrapBigDecimal(
                dp.fetchStep2FinalVolume(), descriptor("Step 2 final volume, V2 (L)", Meta.of("step", "2")));

        final var step2C1V1 = intermediateConcentration.multiply(step2AliquotVolume, mc, descriptor("C1 × V1 (step 2)"));
        final var workingConcentration = step2C1V1.divide(
                step2FinalVolume, mc, descriptor("Working (final) concentration (mol/L)", Meta.of("step", "2")));

        // === Mass of solute present in the final working volume ===
        final var molarMass = ctx.wrapBigDecimal(dp.fetchMolarMass(), descriptor("Molar mass of NaCl (g/mol)"));
        final var molesInFinal = workingConcentration.multiply(step2FinalVolume, mc, descriptor("Moles of solute in final volume (mol)"));
        final var massInFinal = molesInFinal.multiply(molarMass, mc, descriptor("Mass of solute in final volume (g)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
