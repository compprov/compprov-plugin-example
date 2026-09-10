package io.compprov.examples.numscout;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static io.compprov.core.meta.Descriptor.descriptor;

public class LiquidityPoolJoinCalculatorPrecisionTampering {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("BPT join #77410: USDC contribution")));
        LiquidityPoolJoinDataProvider dp = new LiquidityPoolJoinDataProvider();

        final var ten = ctx.wrapBigInteger(BigInteger.TEN, descriptor("10 constant"));
        final var two = ctx.wrapBigInteger(BigInteger.TWO, descriptor("2 constant"));
        final var boneDecimals = ctx.wrapInteger(18, descriptor("BONE decimals"));
        final var boneMultiplier = ten.pow(boneDecimals, descriptor("BONE scale"));
        final var usdcDecimals = ctx.wrapInteger(6, descriptor("USDC decimals"));
        final var usdcMultiplier = ten.pow(usdcDecimals, descriptor("USDC scale"));
        final var bptRequested = ctx.wrapBigInteger(dp.fetchPoolAmountOut(), descriptor("Requested BPT (poolAmountOut)"));
        final var bptTotalSupply = ctx.wrapBigInteger(dp.fetchPoolTotalSupply(), descriptor("Total BPT supply"));
        final var poolUsdcBalance = ctx.wrapBigInteger(dp.fetchPoolUsdcBalance(), descriptor("Pool USDC balance"));

        // bdiv(poolAmountOut, poolTotal) = ((poolAmountOut * BONE) + poolTotal/2) / poolTotal
        final var contributionRatioNumerator = bptRequested.multiply(boneMultiplier, descriptor("Contribution ratio numerator"));
        final var halfBptTotalSupply = bptTotalSupply.divide(two, descriptor("Half of total BPT supply"));
        final var contributionRatioNumeratorRounded = contributionRatioNumerator.add(halfBptTotalSupply,
                descriptor("Contribution ratio numerator"));
        final var contributionRatio = contributionRatioNumeratorRounded.divide(bptTotalSupply, descriptor("Contribution ratio"));

        // bmul(bal, ratio) = ((bal * ratio) + BONE/2) / BONE
        // tampered: USDC balance is never normalized from its native 6 decimals to BONE's
        // 18-decimal terms, so this product truncates to zero on the division below
        final var usdcContributionNumerator = poolUsdcBalance.multiply(contributionRatio, descriptor("USDC contribution numerator"));
        final var halfBoneScale = boneMultiplier.divide(two, descriptor("Half of BONE scale"));
        final var usdcContributionNumeratorRounded = usdcContributionNumerator.add(halfBoneScale,
                descriptor("USDC contribution numerator"));
        final var requiredUsdcContribution = usdcContributionNumeratorRounded.divide(boneMultiplier,
                descriptor("Required USDC contribution (tokenAmountIn)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
