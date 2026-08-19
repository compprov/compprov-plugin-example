package io.compprov.examples.loan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.wrappers.WrappedBigDecimal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Lineage Disconnection / Context Substitution attack: the 6-month chain of interest/principal
 * subtract operations is computed correctly and replays cleanly in isolation — but the reported
 * "Ending balance" is a freshly-wrapped literal with no operation edge back to that chain, which
 * is left dangling in the graph, unused.
 */
public class LoanAmortizationCalculatorLineageDisconnection {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Loan: 6-month amortization")));
        LoanDataProvider dp = new LoanDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var payment = ctx.wrapBigDecimal(dp.fetchMonthlyPayment(), descriptor("Monthly payment"));
        final var periodRate = ctx.wrapBigDecimal(dp.fetchMonthlyPeriodRate(), descriptor("Monthly period rate (0.4%)"));
        final var prepayment = ctx.wrapBigDecimal(dp.fetchPrepaymentAmount(), descriptor("Extra principal prepayment (month 4)"));

        var balance = ctx.wrapBigDecimal(dp.fetchStartingPrincipal(), descriptor("Starting principal"));

        final List<WrappedBigDecimal> interestByMonth = new ArrayList<>(LoanDataProvider.MONTHS);
        final List<WrappedBigDecimal> escrowByMonth = new ArrayList<>(LoanDataProvider.MONTHS);

        for (int month = 1; month <= LoanDataProvider.MONTHS; month++) {
            String lbl = "[Month %d]".formatted(month);

            var interest = balance.multiply(periodRate, mc, descriptor("Interest accrued " + lbl));
            var principalPortion = payment.subtract(interest, mc, descriptor("Principal portion " + lbl));

            String balanceLabel = (month == LoanDataProvider.MONTHS) ? "Ending balance (computed, unused)" : "Balance after payment " + lbl;
            balance = balance.subtract(principalPortion, mc, descriptor(balanceLabel));

            if (month == LoanDataProvider.PREPAYMENT_MONTH) {
                balance = balance.subtract(prepayment, mc, descriptor("Balance after prepayment " + lbl));
            }

            var escrow = ctx.wrapBigDecimal(dp.fetchMonthlyEscrow(), descriptor("Escrow collected " + lbl));

            interestByMonth.add(interest);
            escrowByMonth.add(escrow);
        }

        // === Reported ending balance: a disconnected literal standing in for the real chain ===
        final var endingBalance = ctx.wrapBigDecimal(new BigDecimal("228657.19"), descriptor("Ending balance"));

        // === Totals ===
        final var totalInterestPaid = interestByMonth.get(0)
                .addBulk(interestByMonth.subList(1, interestByMonth.size()), mc, descriptor("Total interest paid"));
        final var totalEscrowCollected = escrowByMonth.get(0)
                .addBulk(escrowByMonth.subList(1, escrowByMonth.size()), mc, descriptor("Total escrow collected"));

        final var otherScheduledPayments = new ArrayList<WrappedBigDecimal>(LoanDataProvider.MONTHS - 1);
        for (int i = 1; i < LoanDataProvider.MONTHS; i++) {
            otherScheduledPayments.add(payment);
        }
        final var totalScheduledPayments = payment.addBulk(otherScheduledPayments, mc, descriptor("Total scheduled payments (6 months)"));

        final var totalOutlay = totalScheduledPayments
                .add(totalEscrowCollected, mc, descriptor("After escrow"))
                .add(prepayment, mc, descriptor("Total amount paid by borrower"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
