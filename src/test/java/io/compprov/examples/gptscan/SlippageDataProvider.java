package io.compprov.examples.gptscan;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for an AMM sandwich attack, modeled after the "Slippage" scenario in
 * Sun et al., GPTScan (Table 1): a swap with no slip-limit/minimum-value check, executed while an
 * attacker's front-run and back-run bracket it in the same block.
 */
public class SlippageDataProvider {

    /** Pool's ETH reserve before any of the three swaps below. */
    public BigInteger fetchInitialReserveEth() {
        return BigInteger.valueOf(500_000);
    }

    /** Pool's USDC reserve before any of the three swaps below (implies a starting price of 2,000 USDC/ETH). */
    public BigInteger fetchInitialReserveUsdc() {
        return BigInteger.valueOf(1_000_000_000);
    }

    /** User B's tx-0 swap input, sized to noticeably move the price ahead of User A's swap. */
    public BigInteger fetchUserBSwapAmountUsdc() {
        return BigInteger.valueOf(50_000_000);
    }

    /** User A's swap, submitted with no minimum-output / slippage bound. */
    public BigInteger fetchUserASwapAmountUsdc() {
        return BigInteger.valueOf(20_000_000);
    }
}
