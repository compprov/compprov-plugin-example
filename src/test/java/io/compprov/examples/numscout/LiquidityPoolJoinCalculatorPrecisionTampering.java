package io.compprov.examples.numscout;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Precision and Scale Tampering attack — NumScout "Exchange Problem" pattern (Chen et al., Fig.
 * 5): the standard {@code poolAmountOut}/{@code poolTotal}/{@code BONE} share-contribution
 * arithmetic, but the pool's token balance is expressed at a low, 6-decimal precision (as with
 * real assets such as USDC or USDT) instead of the 18-decimal precision the {@code BONE} scaling
 * assumes. The two-step scaling ({@code (tokenBalance * ratio) / BONE}) collapses to zero before
 * ever reaching the division that would surface a nonzero contribution requirement: the user
 * receives real pool shares while contributing zero tokens.
 *
 * <p>Contrast {@link GameWinnerPayoutCalculator}, which reconciles its split explicitly and
 * discloses the leftover remainder as a named output — the transparency that's absent here.
 */
public class LiquidityPoolJoinCalculatorPrecisionTampering {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("DeFi: liquidity pool join")));
        LiquidityPoolJoinDataProvider dp = new LiquidityPoolJoinDataProvider();

        final var poolAmountOut = ctx.wrapBigInteger(dp.fetchPoolAmountOut(), descriptor("Requested pool shares (poolAmountOut)"));
        final var poolTotal = ctx.wrapBigInteger(dp.fetchPoolTotalSupply(), descriptor("Total pool share supply"));
        final var bone = ctx.wrapBigInteger(dp.fetchScaleConstant(), descriptor("Fixed-point scale constant (BONE)"));
        // tampered: token balance expressed at 6 decimals instead of the assumed 18
        final var tokenBalance = ctx.wrapBigInteger(dp.fetchTokenBalanceLowDecimals(), descriptor("Pool token balance (6 decimals)"));

        final var poolAmountOutScaled = poolAmountOut.multiply(bone, descriptor("poolAmountOut * BONE"));
        final var ratio = poolAmountOutScaled.divide(poolTotal, descriptor("Contribution ratio"));

        final var balanceTimesRatio = tokenBalance.multiply(ratio, descriptor("Token balance * ratio"));
        // tampered: truncates to zero — the pool mints shares for a zero-cost contribution
        final var tokenAmountIn = balanceTimesRatio.divide(bone, descriptor("Required token contribution (tokenAmountIn)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
