package io.compprov.examples.nav.model;

/**
 * Currencies and tokens supported by the NAV (Net Asset Value) example, each carrying the
 * decimal precision {@link Amount} uses to scale its underlying {@link java.math.BigDecimal}.
 */
public enum Currency {

    BTC(8),

    ETH(18),

    USDC(6),
    USDT(6),

    USD(2),
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
