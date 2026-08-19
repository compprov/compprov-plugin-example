package io.compprov.examples.loan;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a 6-month slice of a mortgage amortization schedule: starting
 * principal, a flat monthly period rate, a fixed monthly payment, a monthly escrow collection
 * (property tax + insurance), and one extra principal-only prepayment in month 4.
 */
public class LoanDataProvider {

    public static final int MONTHS = 6;
    public static final int PREPAYMENT_MONTH = 4;

    public BigDecimal fetchStartingPrincipal() {
        return new BigDecimal("240000.00");
    }

    public BigDecimal fetchMonthlyPeriodRate() {
        return new BigDecimal("0.004");
    }

    public BigDecimal fetchMonthlyPayment() {
        return new BigDecimal("2000.00");
    }

    public BigDecimal fetchMonthlyEscrow() {
        return new BigDecimal("400.00");
    }

    public BigDecimal fetchPrepaymentAmount() {
        return new BigDecimal("5000.00");
    }
}
