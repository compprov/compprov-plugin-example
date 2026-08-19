package io.compprov.examples.numscout;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Precision and Scale Tampering attack — NumScout "Precision Loss Trend" pattern (Chen et al.,
 * Fig. 6): the taker fee is computed with plain (floor) division instead of the ceiling division
 * the pool requires, so on every swap that does not divide evenly, the truncated dust silently
 * stays with the trader instead of the liquidity pool. On the reference inputs the fee comes out
 * one unit lower than the correctly-rounded value, understating the pool's collected fees on
 * every single trade.
 *
 * <p>Contrast {@link GameWinnerPayoutCalculator}, which reconciles its split explicitly and
 * discloses the leftover remainder as a named output — the transparency that's absent here.
 */
public class AmmTakerFeeCalculatorPrecisionTampering {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("AMM: taker fee deduction")));
        AmmTakerFeeDataProvider dp = new AmmTakerFeeDataProvider();

        final var amountToTaker = ctx.wrapBigInteger(dp.fetchAmountToTaker(), descriptor("Amount to taker (pre-fee)"));
        final var feeBps = ctx.wrapBigInteger(dp.fetchFeeBasisPoints(), descriptor("Fee rate (basis points)"));
        final var denominator = ctx.wrapBigInteger(dp.fetchBasisPointsDenominator(), descriptor("Basis points denominator"));

        final var feeNumerator = amountToTaker.multiply(feeBps, descriptor("Amount * fee rate"));
        // tampered: plain (floor) division — the pool silently loses the rounding dust to the taker
        final var fee = feeNumerator.divide(denominator, descriptor("Taker fee (rounded down)"));

        final var amountAfterFee = amountToTaker.subtract(fee, descriptor("Amount credited to taker"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
