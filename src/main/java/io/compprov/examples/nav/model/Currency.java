package io.compprov.examples.nav.model;

/**
 * Currencies and tokens supported by the NAV (Net Asset Value) example, each carrying the
 * decimal precision {@link Amount} uses to scale its underlying {@link java.math.BigDecimal}.
 */
public enum Currency {

    BTC(8),
    /** Wrapped BTC — a third-party-collateralized synthetic representation of BTC, tracked
     * separately from native {@link #BTC} since it trades on its own market and carries
     * depeg/counterparty risk. Convert to {@link #BTC} via an explicit peg {@link Rate}, never
     * by treating a {@code WBTC} amount as if it were already {@code BTC}. */
    WBTC(8),

    ETH(18),

    USDC(6),
    USDT(6),

    USD(2),
    EUR(2),
    WSTETH(18);

    private final int decimals;

    Currency(int decimals) {
        this.decimals = decimals;
    }

    /**
     * @return the number of decimal places an {@link Amount} in this currency is scaled to
     */
    public int getDecimals() {
        return decimals;
    }
}
