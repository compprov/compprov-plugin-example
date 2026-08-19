package io.compprov.examples.numscout;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Precision and Scale Tampering attack — NumScout "Div In Path" pattern (Chen et al., Fig. 2):
 * the payment is divided down into whole-ether units <em>before</em> being compared against the
 * threshold and before the buyer's excess-payment bonus is computed, instead of scaling the
 * threshold up. {@link java.math.BigInteger#divide} truncates toward zero, so a 3.5-ether payment
 * is silently treated as exactly 3 ether.
 *
 * <p>The payment clears the 1-ether threshold by a wide margin either way, so the gate itself
 * still passes — floor(payment / 1&nbsp;ether) &ge; threshold matches the full-precision
 * comparison here regardless of the truncation. The attack is entirely in what happens
 * <em>after</em> the gate: the same truncated, whole-ether value is reused to compute the buyer's
 * bonus credit (the payment in excess of the threshold), so the 0.5-ether fractional remainder
 * the payer actually sent is discarded from that downstream calculation — silently pocketed
 * rather than credited back, an asset-conservation violation a passing gate decision alone can't
 * reveal. The untampered form would compute the excess from the full-precision wei payment and
 * credit the buyer the full 2.5 ether.
 *
 * <p>Contrast {@link GameWinnerPayoutCalculator}, which reconciles its split explicitly and
 * discloses the leftover remainder as a named output — the transparency that's absent here.
 */
public class TokenSaleThresholdCalculatorPrecisionTampering {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Token sale: minimum purchase gate")));
        TokenSaleThresholdDataProvider dp = new TokenSaleThresholdDataProvider();

        final var paymentSent = ctx.wrapBigInteger(dp.fetchPaymentSent(), descriptor("Payment sent (wei)"));
        final var oneEther = ctx.wrapBigInteger(dp.fetchOneEther(), descriptor("1 ether, in wei"));
        final var minimumEtherThreshold = ctx.wrapBigInteger(dp.fetchMinimumEtherThreshold(), descriptor("Minimum purchase threshold (whole ether)"));
        final var zero = ctx.wrapBigInteger(BigInteger.ZERO, descriptor("Zero (gate floor)"));

        // tampered: truncating division evaluated before the comparison, discarding the fractional ether
        final var truncatedPaymentInEther = paymentSent.divide(oneEther, descriptor("Payment"));

        // Gate decision, built from the truncated payment: compare(truncatedPaymentInEther,
        // minimumEtherThreshold), floored into a 0/1 multiplier.
        final var gateDecision = truncatedPaymentInEther.compare(
                minimumEtherThreshold, descriptor("Purchase gate decision (payment vs. threshold)"));
        final var gateMultiplier = gateDecision.max(zero, descriptor("Gate multiplier (1 if payment >= threshold, else 0)"));

        // tampered: the bonus is derived from the already-truncated, whole-ether payment — rescaled
        // back to wei — instead of the original full-precision payment, so the fractional-ether
        // portion the payer actually sent never reaches the bonus calculation
        final var truncatedPaymentInWei = truncatedPaymentInEther.multiply(oneEther, descriptor("Payment, rescaled to wei"));
        final var scaledThreshold = minimumEtherThreshold.multiply(oneEther, descriptor("Threshold scaled to wei"));
        final var rawExcessPayment = truncatedPaymentInWei.subtract(
                scaledThreshold, descriptor("Raw excess payment above threshold (wei)"));
        final var excessPayment = rawExcessPayment.multiply(gateMultiplier, descriptor("Excess payment above threshold (wei, bonus-eligible)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
