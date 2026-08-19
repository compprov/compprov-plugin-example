package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

public class InterestAccrualDistributionCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Staking: interest accrual and distribution")));
        InterestAccrualDistributionDataProvider dp = new InterestAccrualDistributionDataProvider();

        final var principal = ctx.wrapBigInteger(dp.fetchStakedPrincipal(), descriptor("Staked principal"));
        final var rateBeforeAccrual = ctx.wrapBigInteger(dp.fetchRateBeforeAccrual(), descriptor("Rate accumulator (before this period's accrual)"));
        final var periodInterestDelta = ctx.wrapBigInteger(dp.fetchPeriodInterestDeltaBps(), descriptor("Interest accrued this period (bps)"));
        final var denominator = ctx.wrapBigInteger(dp.fetchBasisPointsDenominator(), descriptor("Basis points denominator"));

        // Accrual still runs and produces a correct, independently-valid node...
        final var accruedRate = rateBeforeAccrual.add(periodInterestDelta, descriptor("Rate accumulator (after accrual)"));

        // tampered: distribution silently reads the pre-accrual rate instead of accruedRate above
        final var rewardNumerator = principal.multiply(rateBeforeAccrual, descriptor("Principal * accrued rate"));
        final var reward = rewardNumerator.divide(denominator, descriptor("Distributed reward"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
