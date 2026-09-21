package io.compprov.examples.forwardcontract;

import io.compprov.examples.TestComputationContext;
import org.junit.jupiter.api.Test;

import java.math.MathContext;

import io.compprov.core.meta.Meta;

import static io.compprov.core.meta.Descriptor.descriptor;

public class ForwardContractCalculator {

    @Test
    public void calculate() {

        final var ctx = new TestComputationContext("Forward contract: EUR/USD 90-day valuation");
        ForwardContractDataProvider dp = new ForwardContractDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Interest rate parity: a dimensionless scale factor, not a currency conversion ===
        final var domesticAnnualRate = ctx.wrapBigDecimal(dp.fetchDomesticInterestRate(), descriptor("Domestic (USD) interest rate, annual"));
        final var foreignAnnualRate = ctx.wrapBigDecimal(dp.fetchForeignInterestRate(), descriptor("Foreign (EUR) interest rate, annual"));
        final var dayCountFraction = ctx.wrapBigDecimal(dp.fetchDayCountFraction(), descriptor("Day-count fraction (90/360, ACT/360)"));
        final var one = ctx.wrapBigDecimal(dp.fetchOne(), descriptor("One"));

        // === Explicit annual-to-period conversion, applied to both legs before use ===
        final var domesticRate = domesticAnnualRate.multiply(dayCountFraction, mc, descriptor("Domestic (USD) interest rate, 90-day period"));
        final var foreignRate = foreignAnnualRate.multiply(dayCountFraction, mc, descriptor("Foreign (EUR) interest rate, 90-day period"));

        final var domesticFactor = one.add(domesticRate, mc, descriptor("Domestic growth factor (1 + domestic rate)"));
        final var foreignFactor = one.add(foreignRate, mc, descriptor("Foreign growth factor (1 + foreign rate)"));
        final var parityRatio = domesticFactor.divide(foreignFactor, mc, descriptor("Interest rate parity ratio (forward/spot)"));

        // === Notional, converted at today's spot rate — the graph's only Rate, a genuine external quote ===
        final var notional = ctx.wrap(dp.fetchNotionalAmount(), descriptor("Notional amount (EUR)"));
        final var spotRate = ctx.wrap(dp.fetchSpotRate(), descriptor("Spot rate (EUR/USD)"));
        final var notionalAtSpot = notional.convert(spotRate, descriptor("Notional at spot rate (USD)"));

        // === Settlement amount at the forward rate, via the parity ratio directly ===
        final var settlementAmount = notionalAtSpot.scale(parityRatio, descriptor("Settlement amount at forward rate (USD)"));

        // === Mark-to-market value: the forward contract's P&L relative to dealing at spot today ===
        final var markToMarketValue = settlementAmount.subtract(notionalAtSpot, descriptor("Mark-to-market value vs. spot (USD)",
                Meta.of("basis", "interest-rate-parity forward premium at valuation date")));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
