package io.compprov.examples.numscout;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for a liquidity-pool join, modeled after the "Exchange Problem"
 * example in Chen et al., NumScout (Fig. 5, Balancer {@code joinPool}): a small request for BPT
 * (Balancer Pool Token) shares against a large existing pool supply, scaled by a fixed-point
 * constant {@code BONE}. The pooled asset is USDC, a real 6-decimal token the paper itself names
 * (alongside USDT and XRP) as a top-5-by-market-cap asset with decimals precision below 18.
 */
public class LiquidityPoolJoinDataProvider {

    public BigInteger fetchPoolAmountOut() {
        return BigInteger.valueOf(1000);
    }

    public BigInteger fetchPoolTotalSupply() {
        return BigInteger.valueOf(5_000_000_000L);
    }

    /** Balancer's fixed-point unit: 1e18. */
    public BigInteger fetchScaleConstant() {
        return BigInteger.TEN.pow(18);
    }

    /** The pool's USDC holding, at USDC's own native 6 decimals. */
    public BigInteger fetchPoolUsdcBalance() {
        return BigInteger.TEN.pow(6);
    }
}
