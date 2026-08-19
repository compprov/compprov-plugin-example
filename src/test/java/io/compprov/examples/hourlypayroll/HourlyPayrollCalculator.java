package io.compprov.examples.hourlypayroll;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Weekly net pay for an hourly wage worker.
 *
 * <p>Gross pay = regular pay (hours × rate) + overtime pay (hours × rate × 1.5) + night-shift
 * differential (flat per-hour amount × night-shift hours). Net pay = gross pay − flat payroll tax
 * withholding.
 */
public class HourlyPayrollCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Hourly payroll: weekly net pay")));
        HourlyPayrollDataProvider dp = new HourlyPayrollDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var hourlyRate = ctx.wrapBigDecimal(dp.fetchHourlyRate(), descriptor("Base hourly rate"));

        // === Regular pay ===
        final var regularHours = ctx.wrapBigDecimal(dp.fetchRegularHours(), descriptor("Regular hours"));
        final var regularPay = regularHours.multiply(hourlyRate, mc, descriptor("Regular pay"));

        // === Overtime pay ===
        final var overtimeHours = ctx.wrapBigDecimal(dp.fetchOvertimeHours(), descriptor("Overtime hours"));
        final var overtimeMultiplier = ctx.wrapBigDecimal(dp.fetchOvertimeMultiplier(), descriptor("Overtime multiplier (1.5x)"));
        final var overtimeRate = hourlyRate.multiply(overtimeMultiplier, mc, descriptor("Overtime rate"));
        final var overtimePay = overtimeHours.multiply(overtimeRate, mc, descriptor("Overtime pay"));

        // === Night-shift differential ===
        final var nightShiftHours = ctx.wrapBigDecimal(dp.fetchNightShiftHours(), descriptor("Night-shift hours"));
        final var nightShiftDifferentialRate = ctx.wrapBigDecimal(dp.fetchNightShiftDifferentialPerHour(), descriptor("Night-shift differential ($/hr)"));
        final var nightShiftDifferential = nightShiftHours.multiply(nightShiftDifferentialRate, mc, descriptor("Night-shift differential pay"));

        // === Gross pay ===
        final var grossPay = regularPay.addBulk(
                List.of(overtimePay, nightShiftDifferential), mc, descriptor("Gross pay"));

        // === Net pay ===
        final var withholdingRate = ctx.wrapBigDecimal(dp.fetchPayrollTaxWithholdingRate(), descriptor("Payroll tax withholding rate (18%)"));
        final var withholding = grossPay.multiply(withholdingRate, mc, descriptor("Payroll tax withholding"));
        final var netPay = grossPay.subtract(withholding, mc, descriptor("Net pay"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
