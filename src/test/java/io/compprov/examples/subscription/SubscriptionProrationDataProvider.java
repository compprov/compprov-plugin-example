package io.compprov.examples.subscription;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a mid-cycle subscription plan upgrade: the customer switches
 * from an old plan to a new plan partway through a 30-day billing cycle.
 */
public class SubscriptionProrationDataProvider {

    public BigDecimal fetchDaysInMonth() {
        return new BigDecimal("30");
    }

    public BigDecimal fetchDaysRemaining() {
        return new BigDecimal("12");
    }

    public BigDecimal fetchOldPlanMonthlyPrice() {
        return new BigDecimal("29.00");
    }

    public BigDecimal fetchNewPlanMonthlyPrice() {
        return new BigDecimal("79.00");
    }
}
