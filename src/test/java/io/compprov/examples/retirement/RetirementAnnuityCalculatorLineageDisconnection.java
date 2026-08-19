package io.compprov.examples.retirement;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Lineage Disconnection / Context Substitution attack: the 6-year compounding loop is computed
 * correctly and replays cleanly in isolation — but the reported "Ending balance" is a
 * freshly-wrapped literal with no operation edge back to that chain, which is left dangling in
 * the graph, unused. Total contributions and total growth are then derived from the disconnected
 * figure instead of the real one.
 */
public class RetirementAnnuityCalculatorLineageDisconnection {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Retirement: 6-year annuity projection")));
        RetirementAnnuityDataProvider dp = new RetirementAnnuityDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var contribution = ctx.wrapBigDecimal(dp.fetchAnnualContribution(), descriptor("Annual contribution"));
        final var rate = ctx.wrapBigDecimal(dp.fetchAnnualGrowthRate(), descriptor("Annual growth rate (7%)"));

        var balance = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Balance (start)"));

        for (int year = 1; year <= RetirementAnnuityDataProvider.CONTRIBUTION_YEARS; year++) {
            String lbl = "[Year %d]".formatted(year);

            var interest = balance.multiply(rate, mc, descriptor("Interest earned " + lbl));
            balance = balance.add(interest, mc, descriptor("Balance after interest " + lbl));

            String balanceLabel = (year == RetirementAnnuityDataProvider.CONTRIBUTION_YEARS)
                    ? "Ending balance (computed, unused)"
                    : "Balance after contribution " + lbl;
            balance = balance.add(contribution, mc, descriptor(balanceLabel));
        }

        // === Reported ending balance: a disconnected literal standing in for the real chain ===
        final var endingBalance = ctx.wrapBigDecimal(new BigDecimal("44399.53"), descriptor("Ending balance"));

        // === Totals ===
        final var yearsCount = ctx.wrapBigDecimal(
                new BigDecimal(RetirementAnnuityDataProvider.CONTRIBUTION_YEARS), descriptor("Contribution years"));
        final var totalContributions = contribution.multiply(yearsCount, mc, descriptor("Total contributions"));
        final var totalGrowth = endingBalance.subtract(totalContributions, mc, descriptor("Total growth (interest earned)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
