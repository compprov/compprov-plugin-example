package io.compprov.examples.payout;

import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.Currency;
import io.compprov.examples.nav.model.OptionPosition;
import io.compprov.examples.nav.model.OptionType;
import io.compprov.examples.nav.model.Rate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Provides market data and generates a reproducible set of random daily ETH/USDC
 * option positions for the payout calculation demo.
 *
 * <p>Configuration:
 * <ul>
 *   <li>{@link #N} — number of positions to generate (configurable)</li>
 *   <li>Spot price: 4650 USDC/ETH (June 30, 2026)</li>
 *   <li>Strike grid: spot ± {@value #STRIKE_HALF_RANGE} × $20 steps
 *       → [4550 … 4750] in $20 increments</li>
 *   <li>Notional size: 0.1 – 10.0 ETH (random, 4 d.p.)</li>
 * </ul>
 */
public class PayoutDataProvider {

    /**
     * Configurable number of option positions to generate.
     */
    public static final int N = 10;

    private static final long SEED = 42L;
    private static final BigDecimal SPOT_PRICE = new BigDecimal("4650");
    private static final BigDecimal STRIKE_STEP = new BigDecimal("20");
    private static final int STRIKE_HALF_RANGE = 5;

    private static final List<BigDecimal> STRIKES;

    static {
        int count = 2 * STRIKE_HALF_RANGE + 1;
        List<BigDecimal> strikes = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            strikes.add(SPOT_PRICE.add(STRIKE_STEP.multiply(new BigDecimal(i - STRIKE_HALF_RANGE))));
        }
        STRIKES = Collections.unmodifiableList(strikes);
    }

    /**
     * ETH/USDC spot price as of June 30, 2026.
     */
    public Rate fetchSpotPrice() {
        return new Rate(Currency.ETH, Currency.USDC, SPOT_PRICE);
    }

    /**
     * Generates {@link #N} random positions with a fixed seed so results are
     * reproducible across runs.
     */
    public List<OptionPosition> generatePositions() {
        Random rng = new Random(SEED);
        List<OptionPosition> positions = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            OptionType type = rng.nextBoolean() ? OptionType.CALL : OptionType.PUT;
            BigDecimal strike = STRIKES.get(rng.nextInt(STRIKES.size()));
            BigDecimal size = BigDecimal.valueOf(0.1 + rng.nextDouble() * 9.9)
                    .setScale(4, RoundingMode.HALF_UP);
            positions.add(new OptionPosition(type, new Rate(Currency.ETH, Currency.USDC, strike), new Amount(Currency.ETH, size)));
        }
        return positions;
    }
}
