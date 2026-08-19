package io.compprov.examples.payroll;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a single biweekly pay period: base salary, bonus, pretax
 * deductions, a two-bracket progressive federal withholding schedule, and a flat state rate.
 */
public class PayrollDataProvider {

    public BigDecimal fetchBaseSalary() {
        return new BigDecimal("3500.00");
    }

    public BigDecimal fetchBonus() {
        return new BigDecimal("500.00");
    }

    public BigDecimal fetch401kContribution() {
        return new BigDecimal("200.00");
    }

    public BigDecimal fetchHealthPremium() {
        return new BigDecimal("150.00");
    }

    /**
     * A non-taxable reimbursement of employee-incurred business expenses, used by the semantic
     * tamper scenario. Not part of the clean calculation.
     */
    public BigDecimal fetchExpenseReimbursement() {
        return new BigDecimal("120.00");
    }

    /** Upper bound of the first federal withholding bracket. */
    public BigDecimal fetchBracket1Ceiling() {
        return new BigDecimal("2000.00");
    }

    public BigDecimal fetchBracket1Rate() {
        return new BigDecimal("0.10");
    }

    public BigDecimal fetchBracket2Rate() {
        return new BigDecimal("0.22");
    }

    public BigDecimal fetchStateTaxRate() {
        return new BigDecimal("0.05");
    }
}
