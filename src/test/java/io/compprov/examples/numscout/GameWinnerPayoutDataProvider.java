package io.compprov.examples.numscout;

import java.math.BigInteger;
import java.util.Random;

/**
 * Fixed, reproducible inputs for splitting a pooled game-interest reward across three winners,
 * modeled after the "Minor Amount Retention" example in Chen et al., NumScout (Fig. 4,
 * GoodGhosting): a total that is not evenly divisible by the winner count, so an integer split
 * leaves a one-unit remainder every round. GoodGhosting-style prize games run this same split
 * every round for the life of the contract; {@link #ROUND_COUNT} is how many rounds
 * {@code CycledGameWinnerPayoutCalculatorPrecisionTampering} actually replays to show what a
 * per-round remainder compounds into.
 */
public class GameWinnerPayoutDataProvider {

    public static final int ROUND_COUNT = 10;

    public BigInteger fetchTotalGameInterest(int n) {
        return BigInteger.valueOf(new Random().nextLong(1000) + 1000);
    }

    public BigInteger fetchTotalGameInterest() {
        return BigInteger.valueOf(1000);
    }

    public BigInteger fetchWinnerCount() {
        return BigInteger.valueOf(3);
    }
}
