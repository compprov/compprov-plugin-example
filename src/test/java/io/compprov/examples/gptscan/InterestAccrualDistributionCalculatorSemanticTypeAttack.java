package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Meta;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Semantic Type and Context Cast attack — GPTScan "Wrong Interest Rate Order" pattern (Sun et
 * al., Table 1): a rate accumulator that is correctly refreshed each period, and a distribution
 * step that must consume the freshly-refreshed value rather than the value carried over from the
 * prior period.
 *
 * <p>Both rate candidates are honestly computed and honestly typed ({@code BigInteger} ->
 * {@code BigInteger}), share the same descriptor name, and are distinguished only by a
 * {@code period} tag — the way a real accumulator's periodic snapshots would actually be logged.
 * The reward numerator is itself tagged for the current period, yet is built from the
 * accumulator's prior-period reading rather than the current-period one computed alongside it.</p>
 */
public class InterestAccrualDistributionCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Staking: interest accrual and distribution")));
        InterestAccrualDistributionDataProvider dp = new InterestAccrualDistributionDataProvider();

        final var principal = ctx.wrapBigInteger(dp.fetchStakedPrincipal(), descriptor("Staked principal"));
        final var rateAtPriorPeriod = ctx.wrapBigInteger(dp.fetchRateBeforeAccrual(),
                descriptor("Rate accumulator", Meta.of("period", Integer.toString(dp.fetchPriorPeriod()))));
        final var periodInterestDelta = ctx.wrapBigInteger(dp.fetchPeriodInterestDeltaBps(),
                descriptor("Interest accrued (bps)", Meta.of("period", Integer.toString(dp.fetchCurrentPeriod()))));
        final var denominator = ctx.wrapBigInteger(dp.fetchBasisPointsDenominator(), descriptor("Basis points denominator"));

        // Accrual still runs and produces a correct, independently-valid node...
        final var rateAtCurrentPeriod = rateAtPriorPeriod.add(periodInterestDelta,
                descriptor("Rate accumulator", Meta.of("period", Integer.toString(dp.fetchCurrentPeriod()))));

        // tampered: distribution silently reads the prior-period rate instead of rateAtCurrentPeriod above
        final var rewardNumerator = principal.multiply(rateAtPriorPeriod,
                descriptor("Reward numerator", Meta.of("period", Integer.toString(dp.fetchCurrentPeriod()))));
        final var reward = rewardNumerator.divide(denominator,
                descriptor("Distributed reward", Meta.of("period", Integer.toString(dp.fetchCurrentPeriod()))));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
