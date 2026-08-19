package io.compprov.examples.dilution;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a two-step serial dilution of a NaCl stock solution down to a
 * target working concentration.
 */
public class SolutionDilutionDataProvider {

    /** Stock solution concentration (mol/L). */
    public BigDecimal fetchStockConcentration() {
        return new BigDecimal("2.00");
    }

    /** Volume of stock solution drawn for dilution step 1 (L). */
    public BigDecimal fetchStep1AliquotVolume() {
        return new BigDecimal("0.050");
    }

    /** Final volume after topping up dilution step 1 (L). */
    public BigDecimal fetchStep1FinalVolume() {
        return new BigDecimal("0.500");
    }

    /** Volume drawn from the step-1 solution for dilution step 2 (L). */
    public BigDecimal fetchStep2AliquotVolume() {
        return new BigDecimal("0.100");
    }

    /** Final volume after topping up dilution step 2 (L) — the working solution volume. */
    public BigDecimal fetchStep2FinalVolume() {
        return new BigDecimal("1.000");
    }

    /** Molar mass of NaCl (g/mol). */
    public BigDecimal fetchMolarMass() {
        return new BigDecimal("58.44");
    }
}
