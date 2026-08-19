package io.compprov.examples.numscout;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Splits a pooled game-interest reward across three winners by integer division, modeled after
 * the "Minor Amount Retention" example in Chen et al., NumScout (Fig. 4, GoodGhosting): the pool
 * rarely divides evenly, so the split leaves a small remainder. Unlike
 * {@link GameWinnerPayoutCalculatorPrecisionTampering}, this trace doesn't stop at the division —
 * it goes on to compute the total actually paid out and the leftover remainder as explicit,
 * named outputs, so the pool's conservation ({@code totalPayout + remainder == pool}) is fully
 * verifiable from the graph alone.
 */
public class GameWinnerPayoutCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Game: winner interest payout")));
        GameWinnerPayoutDataProvider dp = new GameWinnerPayoutDataProvider();

        var totalGameInterest = ctx.wrapBigInteger(dp.fetchTotalGameInterest(), descriptor("Total game interest (pool)"));
        final var winnerCount = ctx.wrapBigInteger(dp.fetchWinnerCount(), descriptor("Winner count"));

        final var perWinnerPayout = totalGameInterest.divide(winnerCount, descriptor("Per-winner payout (floor)"));
        final var totalPayout = perWinnerPayout.multiply(winnerCount, descriptor("Total payout"));
        final var remainder = totalGameInterest.subtract(totalPayout, descriptor("Game interest remainder"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
