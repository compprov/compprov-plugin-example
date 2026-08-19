package io.compprov.examples.hourlypayroll;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a single weekly pay period for an hourly wage worker: regular
 * hours, overtime hours, a night-shift differential, and a flat payroll tax withholding rate.
 */
public class HourlyPayrollDataProvider {

    public BigDecimal fetchHourlyRate() {
        return new BigDecimal("22.00");
    }

    public BigDecimal fetchRegularHours() {
        return new BigDecimal("40");
    }

    public BigDecimal fetchOvertimeHours() {
        return new BigDecimal("6");
    }

    public BigDecimal fetchOvertimeMultiplier() {
        return new BigDecimal("1.5");
    }

    /** Hours worked on the night shift, eligible for the shift differential. */
    public BigDecimal fetchNightShiftHours() {
        return new BigDecimal("12");
    }

    public BigDecimal fetchNightShiftDifferentialPerHour() {
        return new BigDecimal("1.75");
    }

    public BigDecimal fetchPayrollTaxWithholdingRate() {
        return new BigDecimal("0.18");
    }
}
