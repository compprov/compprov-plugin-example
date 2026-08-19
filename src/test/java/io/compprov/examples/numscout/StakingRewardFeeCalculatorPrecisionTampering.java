package io.compprov.examples.numscout;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Precision and Scale Tampering attack — NumScout "Operator Order Issue" pattern (Chen et al.,
 * Fig. 3): the developer fee is computed by dividing the accumulated reward by the percent
 * denominator <em>before</em> multiplying by the fee percent, instead of the other way around.
 * Both orderings are algebraically equivalent over the rationals, but {@link
 * java.math.BigInteger#divide} truncates toward zero, so evaluating the division first discards
 * a fraction of the reward that the correct ordering would have preserved: on the reference
 * inputs (199 units, 10% fee) this silently understates the fee from 19 to 10, a ~47% shortfall
 * that recurs on every payout.
 *
 * <p>Contrast {@link GameWinnerPayoutCalculator}, which reconciles its split explicitly and
 * discloses the leftover remainder as a named output — the transparency that's absent here.
 */
public class StakingRewardFeeCalculatorPrecisionTampering {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Staking: developer fee deduction")));
        StakingRewardFeeDataProvider dp = new StakingRewardFeeDataProvider();

        final var accumulatedReward = ctx.wrapBigInteger(dp.fetchAccumulatedReward(), descriptor("Accumulated staking reward"));
        final var feePercent = ctx.wrapBigInteger(dp.fetchDeveloperFeePercent(), descriptor("Developer fee percent"));
        final var percentDenominator = ctx.wrapBigInteger(dp.fetchPercentDenominator(), descriptor("Percent denominator (100)"));

        // tampered: divide first, then multiply — truncation happens before the fee percent is applied
        final var rewardDividedByDenominator = accumulatedReward.divide(percentDenominator, descriptor("Reward / denominator"));
        final var developerReward = rewardDividedByDenominator.multiply(feePercent, descriptor("Developer reward (fee)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
