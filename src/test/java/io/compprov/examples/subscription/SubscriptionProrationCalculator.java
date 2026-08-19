package io.compprov.examples.subscription;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Mid-billing-cycle plan upgrade proration.
 *
 * <p>{@code dailyRate = monthlyPrice / daysInMonth}; {@code proratedAmount = dailyRate ×
 * remainingDays}. The customer is refunded the unused portion of the old plan and charged the
 * prorated portion of the new plan for the same remaining days; {@code netCharge = newProrated −
 * oldRefund}.
 */
public class SubscriptionProrationCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Subscription: mid-cycle plan upgrade proration")));
        SubscriptionProrationDataProvider dp = new SubscriptionProrationDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        final var daysInMonth = ctx.wrapBigDecimal(dp.fetchDaysInMonth(), descriptor("Days in billing cycle"));
        final var daysRemaining = ctx.wrapBigDecimal(dp.fetchDaysRemaining(), descriptor("Days remaining in cycle"));

        // === Old plan refund ===
        final var oldPlanPrice = ctx.wrapBigDecimal(dp.fetchOldPlanMonthlyPrice(), descriptor("Old plan monthly price"));
        final var oldPlanDailyRate = oldPlanPrice.divide(daysInMonth, mc, descriptor("Old plan daily rate"));
        final var oldPlanRefund = oldPlanDailyRate.multiply(daysRemaining, mc, descriptor("Old plan refund (unused days)"));

        // === New plan prorated charge ===
        final var newPlanPrice = ctx.wrapBigDecimal(dp.fetchNewPlanMonthlyPrice(), descriptor("New plan monthly price"));
        final var newPlanDailyRate = newPlanPrice.divide(daysInMonth, mc, descriptor("New plan daily rate"));
        final var newPlanProratedCharge = newPlanDailyRate.multiply(daysRemaining, mc, descriptor("New plan prorated charge (remaining days)"));

        // === Net charge to customer ===
        final var netCharge = newPlanProratedCharge.subtract(oldPlanRefund, mc, descriptor("Net charge (new prorated − old refund)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
