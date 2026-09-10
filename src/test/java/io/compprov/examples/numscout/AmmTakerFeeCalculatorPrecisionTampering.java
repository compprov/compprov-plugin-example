package io.compprov.examples.numscout;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

public class AmmTakerFeeCalculatorPrecisionTampering {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("AMM swap #48213: taker fee settlement")));
        AmmTakerFeeDataProvider dp = new AmmTakerFeeDataProvider();

        final var grossAmountToTaker = ctx.wrapBigInteger(dp.fetchAmountToTaker(), descriptor("Gross amount to taker"));
        final var feeBps = ctx.wrapBigInteger(dp.fetchFeeBasisPoints(), descriptor("Fee rate (basis points)"));
        final var denominator = ctx.wrapBigInteger(dp.fetchBasisPointsDenominator(), descriptor("Basis points denominator"));

        final var feeNumerator = grossAmountToTaker.multiply(feeBps, descriptor("Fee numerator"));
        // tampered: plain (floor) division — the pool silently loses the rounding dust to the taker
        final var fee = feeNumerator.divide(denominator, descriptor("Taker fee"));

        final var netAmountToTaker = grossAmountToTaker.subtract(fee, descriptor("Net amount to taker"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
