package io.compprov.examples.loan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.wrappers.WrappedBigDecimal;
import org.junit.jupiter.api.Test;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Semantic Type and Context Cast attack: the month-4 extra payment is descriptor-labeled "Extra
 * principal prepayment (non-interest-bearing)" — implying it should reduce principal dollar for
 * dollar — but it's actually run through the same interest-accrual step as a regular payment
 * first, so a slice of it is silently diverted to interest instead of principal. The graph
 * type-checks and replays perfectly; only the declared "non-interest-bearing" meaning is wrong.
 */
public class LoanAmortizationCalculatorSemanticTamper {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Loan: 6-month amortization")));
        LoanDataProvider dp = new LoanDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var payment = ctx.wrapBigDecimal(dp.fetchMonthlyPayment(), descriptor("Monthly payment"));
        final var periodRate = ctx.wrapBigDecimal(dp.fetchMonthlyPeriodRate(), descriptor("Monthly period rate (0.4%)"));
        final var prepayment = ctx.wrapBigDecimal(
                dp.fetchPrepaymentAmount(), descriptor("Extra principal prepayment (non-interest-bearing)"));

        var balance = ctx.wrapBigDecimal(dp.fetchStartingPrincipal(), descriptor("Starting principal"));

        final List<WrappedBigDecimal> interestByMonth = new ArrayList<>(LoanDataProvider.MONTHS);
        final List<WrappedBigDecimal> escrowByMonth = new ArrayList<>(LoanDataProvider.MONTHS);

        for (int month = 1; month <= LoanDataProvider.MONTHS; month++) {
            String lbl = "[Month %d]".formatted(month);

            var interest = balance.multiply(periodRate, mc, descriptor("Interest accrued " + lbl));
            var principalPortion = payment.subtract(interest, mc, descriptor("Principal portion " + lbl));

            String balanceLabel = (month == LoanDataProvider.MONTHS) ? "Ending balance" : "Balance after payment " + lbl;
            balance = balance.subtract(principalPortion, mc, descriptor(balanceLabel));

            if (month == LoanDataProvider.PREPAYMENT_MONTH) {
                // tampered: interest is charged on the prepayment before it reduces principal,
                // despite the prepayment's own label claiming it's non-interest-bearing.
                var prepaymentInterest = prepayment.multiply(periodRate, mc, descriptor("Interest on prepayment " + lbl));
                var prepaymentPrincipalPortion = prepayment.subtract(prepaymentInterest, mc, descriptor("Prepayment principal portion " + lbl));
                balance = balance.subtract(prepaymentPrincipalPortion, mc, descriptor("Balance after prepayment " + lbl));
            }

            var escrow = ctx.wrapBigDecimal(dp.fetchMonthlyEscrow(), descriptor("Escrow collected " + lbl));

            interestByMonth.add(interest);
            escrowByMonth.add(escrow);
        }

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
