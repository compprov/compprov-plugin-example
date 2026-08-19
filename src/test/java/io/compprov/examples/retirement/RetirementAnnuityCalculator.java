package io.compprov.examples.retirement;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Future value of an ordinary annuity: a fixed annual contribution compounded annually at a fixed
 * growth rate over 6 years.
 *
 * <p>Each year: {@code interest = balance × rate}; {@code balance = balance + interest +
 * contribution}. Total contributions and total growth (interest earned) are also reported.
 */
public class RetirementAnnuityCalculator {

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
                    ? "Ending balance"
                    : "Balance after contribution " + lbl;
            balance = balance.add(contribution, mc, descriptor(balanceLabel));
        }

        final var endingBalance = balance;

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
