package io.compprov.examples.numscout;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Precision and Scale Tampering attack — NumScout "Minor Amount Retention" pattern (Chen et al.,
 * Fig. 4, GoodGhosting): splitting a pooled reward across winners by integer division always
 * leaves a small remainder, since the pool rarely divides evenly. The untampered version in
 * {@link GameWinnerPayoutCalculator} goes on to compute the total actually paid out and the
 * leftover remainder as explicit outputs, so the pool's conservation is verifiable from the graph.
 * Here the trace stops at the division: no operation ever establishes what was actually
 * distributed or what, if anything, is left over. The one-unit remainder isn't disclosed and
 * discarded — it has no declared destination in the graph at all, so the pool's conservation
 * can't be checked from this trace.
 */
public class GameWinnerPayoutCalculatorPrecisionTampering {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Game: winner interest payout")));
        GameWinnerPayoutDataProvider dp = new GameWinnerPayoutDataProvider();

        var totalGameInterest = ctx.wrapBigInteger(dp.fetchTotalGameInterest(), descriptor("Total game interest (pool)"));
        final var winnerCount = ctx.wrapBigInteger(dp.fetchWinnerCount(), descriptor("Winner count"));

        final var perWinnerPayout = totalGameInterest.divide(winnerCount, descriptor("Per-winner payout"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
