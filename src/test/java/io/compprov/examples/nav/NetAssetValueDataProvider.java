package io.compprov.examples.nav.model;

import java.math.BigDecimal;
import java.util.Random;

public class NetAssetValueDataProvider {

    private Random random = new Random();

    public Rate fetchBtcUsdPrice() {
        return new Rate(Currency.BTC, Currency.USD, new BigDecimal("68989.72"));
    }

    public Rate fetchEthUsdPrice() {
        return new Rate(Currency.ETH, Currency.USD, new BigDecimal("2083.31"));
    }

    public Rate fetchUsdcUsdPrice() {
        return new Rate(Currency.USDC, Currency.USD, new BigDecimal("1.01"));
    }

    public Rate fetchWstEthEthPrice() {
        return new Rate(Currency.WSTETH, Currency.ETH, new BigDecimal("1.243492"));
    }

    public Amount fetchBinanceBtcAmount() {
        return new Amount(Currency.BTC, new BigDecimal(random.nextDouble(10)));
    }

    public Amount fetchBinanceEthAmount() {
        return new Amount(Currency.ETH, new BigDecimal(random.nextDouble(100)));
    }

    public Amount fetchBinanceUsdcAmount() {
        return new Amount(Currency.USDC, new BigDecimal(random.nextDouble(100_000)));
    }

    public Amount fetchMorphoUsdcAmount() {
        return new Amount(Currency.USDC, new BigDecimal(random.nextDouble(100_000)));
    }

    public Amount fetchStakedEthAmount() {
        return new Amount(Currency.ETH, new BigDecimal(random.nextDouble(100)));
    }

    public Amount fetchStakedLidoAmount() {
        return new Amount(Currency.WSTETH, new BigDecimal(random.nextDouble(100)));
    }

}
