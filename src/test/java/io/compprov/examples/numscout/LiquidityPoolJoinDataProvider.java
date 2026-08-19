package io.compprov.examples.numscout;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for a liquidity-pool join, modeled after the "Exchange Problem"
 * example in Chen et al., NumScout (Fig. 5, Balancer {@code joinPool}): a small request for pool
 * shares against a large existing pool supply, scaled by a fixed-point constant {@code BONE}.
 *
 * <p>The two token-balance methods represent the exact same real-world holding of "1 token" —
 * one expressed with 18 decimals (matching {@code BONE}'s own scale), the other with only 6
 * decimals, as with real low-decimal assets such as USDC or USDT.
 */
public class LiquidityPoolJoinDataProvider {

    public BigInteger fetchPoolAmountOut() {
        return BigInteger.valueOf(1);
    }

    public BigInteger fetchPoolTotalSupply() {
        return BigInteger.valueOf(5_000_000_000L);
    }

    /** Balancer's fixed-point unit: 1e18. */
    public BigInteger fetchScaleConstant() {
        return BigInteger.TEN.pow(18);
    }

    /** "1 token" held at the pool's native 18-decimal precision. */
    public BigInteger fetchTokenBalanceHighDecimals() {
        return BigInteger.TEN.pow(18);
    }

    /** The same "1 token" held at a low, 6-decimal precision (e.g., USDC-like). */
    public BigInteger fetchTokenBalanceLowDecimals() {
        return BigInteger.TEN.pow(6);
    }
}
