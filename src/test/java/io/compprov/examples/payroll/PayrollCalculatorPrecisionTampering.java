package io.compprov.examples.payroll;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;
import java.math.RoundingMode;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Precision and Scale Tampering attack: every operation in this graph uses the standard
 * DECIMAL64 {@link MathContext} except the bracket 2 tax multiply, which is silently evaluated
 * at 2 significant digits with DOWN rounding — a large-magnitude precision loss hidden among
 * otherwise-identical neighboring operations.
 */
public class PayrollCalculatorPrecisionTampering {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Payroll: biweekly net pay")));
        PayrollDataProvider dp = new PayrollDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision"));
        final var lowPrecisionMc = ctx.wrapMathContext(
                new MathContext(2, RoundingMode.DOWN), descriptor("Computation precision"));

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
        // tampered: bracket 2 tax evaluated at 2 significant digits instead of DECIMAL64
        final var bracket2Tax = bracket2Portion.multiply(bracket2Rate, lowPrecisionMc, descriptor("Bracket 2 tax"));
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
