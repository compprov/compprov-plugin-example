package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Descriptor;
import io.compprov.core.meta.Meta;
import io.compprov.core.wrappers.WrappedBigInteger;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Slippage / Sandwich Attack — GPTScan "Slippage" pattern (Sun et al., Table 1):
 *
 * <blockquote>Scenario: involve calculating swap/liquidity or adding liquidity, and there is asset
 * exchanges or price queries.<br>
 * Property: but this operation could be attacked by Slippage/Sandwich Attack due to no slip
 * limit/minimum value check.</blockquote>
 *
 * <p>Unlike the other {@code gptscan/} fixtures, this vulnerability isn't visible in a single
 * function's static shape — it only shows up once you thread the pool's reserves through three
 * sequential swaps from two actors, in the vulnerable order. The graph itself carries no
 * good/bad labels (actors are tagged only "userA"/"userB", never "victim"/"attacker") so a
 * fraud-detection prompt reading it has to reach the same conclusion the CPG structure supports,
 * not one primed by the fixture's own metadata:
 *
 * <ol>
 *   <li>User B's swap (tx 0) executes first, shifting the pool's reserves ahead of User A's.</li>
 *   <li>User A's swap (tx 1) executes next, against the tx-0-shifted reserves. Nothing in the
 *       graph ever compares its output to a minimum acceptable amount before it's paid out — the
 *       missing "slip limit/minimum value check" the property names.</li>
 *   <li>User B's second swap (tx 2) trades back into the pool, against the reserves User A's
 *       swap just moved.</li>
 * </ol>
 */
public class SlippageCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("AMM swaps: block#556489")));
        SlippageDataProvider dp = new SlippageDataProvider();

        final var reserveEth = ctx.wrapBigInteger(dp.fetchInitialReserveEth(), descriptor("Pool reserveETH (t0)", Meta.of("actor", "pool")));
        final var reserveUsdc = ctx.wrapBigInteger(dp.fetchInitialReserveUsdc(), descriptor("Pool reserveUSDC (t0)", Meta.of("actor", "pool")));

        final var userAIn = ctx.wrapBigInteger(dp.fetchUserASwapAmountUsdc(), descriptor("User A swap input (USDC)", Meta.of("actor", "userA")));

        // Quoted against the t0 reserves, before any other swap executes.
        final var userAQuoteAtT0 = getAmountOut(userAIn, reserveUsdc, reserveEth,
                "User A ETH quote at t0 reserves", Meta.of("actor", "userA"));

        // === tx 0: User B swaps USDC for ETH ===
        final var userBIn0 = ctx.wrapBigInteger(dp.fetchUserBSwapAmountUsdc(),
                descriptor("User B swap input, tx 0 (USDC)", Meta.of("actor", "userB")));
        final var userBOut0 = getAmountOut(userBIn0, reserveUsdc, reserveEth,
                "User B swap output, tx 0 (ETH)", Meta.of("actor", "userB"));
        final var reserveUsdc1 = reserveUsdc.add(userBIn0,
                descriptor("Pool reserveUSDC (after tx 0)", Meta.of("actor", "pool")));
        final var reserveEth1 = reserveEth.subtract(userBOut0,
                descriptor("Pool reserveETH (after tx 0)", Meta.of("actor", "pool")));

        // === tx 1: User A's swap executes against the tx-0 reserves ===
        final var userAOut1 = getAmountOut(userAIn, reserveUsdc1, reserveEth1,
                "User A swap output, tx 1 (ETH)", Meta.of("actor", "userA"));
        final var reserveUsdc2 = reserveUsdc1.add(userAIn,
                descriptor("Pool reserveUSDC (after tx 1)", Meta.of("actor", "pool")));
        final var reserveEth2 = reserveEth1.subtract(userAOut1,
                descriptor("Pool reserveETH (after tx 1)", Meta.of("actor", "pool")));

        // === tx 2: User B swaps ETH back into the pool ===
        final var userBOut2 = getAmountOut(userBOut0, reserveEth2, reserveUsdc2,
                "User B swap output, tx 2 (USDC)", Meta.of("actor", "userB"));
        final var reserveEth3 = reserveEth2.add(userBOut0,
                descriptor("Pool reserveETH (after tx 2)", Meta.of("actor", "pool")));
        final var reserveUsdc3 = reserveUsdc2.subtract(userBOut2,
                descriptor("Pool reserveUSDC (after tx 2)", Meta.of("actor", "pool")));

        // === Materiality: net USDC across User B's two swaps, and User A's output vs. the t0 quote ===
        final var userBNetUsdc = userBOut2.subtract(userBIn0,
                descriptor("User B net USDC, tx 0 vs. tx 2", Meta.of("actor", "userB")));
        final var userAOutputVsT0Quote = userAQuoteAtT0.subtract(userAOut1,
                descriptor("Difference: User A's t0 quote vs. tx-1 output (ETH)", Meta.of("actor", "userA")));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }

    /** Uniswap-V2-style constant-product quote: amountOut = reserveOut * amountIn / (reserveIn + amountIn). */
    private static WrappedBigInteger getAmountOut(WrappedBigInteger amountIn, WrappedBigInteger reserveIn,
                                                   WrappedBigInteger reserveOut, String label, Meta actor) {
        final var numerator = reserveOut.multiply(amountIn, descriptor(label + ": reserveOut * amountIn", actor));
        final var denominator = reserveIn.add(amountIn, descriptor(label + ": reserveIn + amountIn", actor));
        final Descriptor resultDescriptor = descriptor(label, actor);
        return numerator.divide(denominator, resultDescriptor);
    }
}
