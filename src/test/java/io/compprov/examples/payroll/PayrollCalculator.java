package io.compprov.examples.payroll;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Calculates net pay for a single biweekly pay period.
 *
 * <p>Net pay = gross pay (base salary + bonus)
 *            − pretax deductions (401k + health premium)
 *            − federal withholding (two-bracket progressive)
 *            − state withholding (flat rate)
 */
public class PayrollCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Payroll: biweekly net pay")));
        PayrollDataProvider dp = new PayrollDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Gross pay ===
        final var baseSalary = ctx.wrapBigDecimal(dp.fetchBaseSalary(), descriptor("Base salary"));
        final var bonus = ctx.wrapBigDecimal(dp.fetchBonus(), descriptor("Bonus"));
        final var grossPay = baseSalary.add(bonus, mc, descriptor("Gross pay"));

        // === Pretax deductions ===
        final var contribution401k = ctx.wrapBigDecimal(dp.fetch401kContribution(), descriptor("401k contribution"));
        final var healthPremium = ctx.wrapBigDecimal(dp.fetchHealthPremium(), descriptor("Health premium"));
        final var pretaxDeductions = contribution401k.add(healthPremium, mc, descriptor("Pretax deductions"));

        // === Taxable income ===
        final var taxableIncome = grossPay.subtract(pretaxDeductions, mc, descriptor("Taxable income"));

        // === Federal withholding: two-bracket progressive ===
        final var bracket1Ceiling = ctx.wrapBigDecimal(dp.fetchBracket1Ceiling(), descriptor("Bracket 1 ceiling"));
        final var bracket1Rate = ctx.wrapBigDecimal(dp.fetchBracket1Rate(), descriptor("Bracket 1 rate (10%)"));
        final var bracket2Rate = ctx.wrapBigDecimal(dp.fetchBracket2Rate(), descriptor("Bracket 2 rate (22%)"));

        final var bracket1Portion = taxableIncome.min(bracket1Ceiling, descriptor("Bracket 1 portion"));
        final var bracket2Portion = taxableIncome.subtract(bracket1Portion, mc, descriptor("Bracket 2 portion"));
        final var bracket1Tax = bracket1Portion.multiply(bracket1Rate, mc, descriptor("Bracket 1 tax"));
        final var bracket2Tax = bracket2Portion.multiply(bracket2Rate, mc, descriptor("Bracket 2 tax"));
        final var federalTax = bracket1Tax.add(bracket2Tax, mc, descriptor("Federal tax withheld"));

        // === State withholding: flat rate ===
        final var stateTaxRate = ctx.wrapBigDecimal(dp.fetchStateTaxRate(), descriptor("State tax rate (5%)"));
        final var stateTax = taxableIncome.multiply(stateTaxRate, mc, descriptor("State tax withheld"));

        // === Net pay ===
        final var netPay = taxableIncome
                .subtract(federalTax, mc, descriptor("After federal withholding"))
                .subtract(stateTax, mc, descriptor("Net pay"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
