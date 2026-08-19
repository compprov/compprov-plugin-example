package io.compprov.examples.nav;

import io.compprov.core.meta.Meta;
import io.compprov.examples.TestComputationContext;
import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.Currency;
import io.compprov.examples.nav.model.NetAssetValueDataProvider;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Lineage Disconnection / Context Substitution attack: every balance is converted to USD and
 * summed correctly into a real {@code assetsSum} subgraph that replays cleanly in isolation —
 * but the reported NAV is a freshly-wrapped {@link Amount} literal with no operation edge back
 * to any of the underlying balances or rates, which are left dangling in the graph, unused.
 */
public class NetAssetValueCalculatorLineageDisconnection {

    @Test
    public void calculate() {

        final var ctx = new TestComputationContext("Nav calculation example");
        NetAssetValueDataProvider dataProvider = new NetAssetValueDataProvider();

        //get rates
        final var btcUsdRate = ctx.wrap(
                dataProvider.fetchBtcUsdPrice(),
                descriptor("BTC/USD rate", Meta.of("origin", "Binance")));
        final var ethUsdRate = ctx.wrap(
                dataProvider.fetchEthUsdPrice(),
                descriptor("ETH/USD rate", Meta.of("origin", "Binance")));
        final var usdcUsdRate = ctx.wrap(
                dataProvider.fetchUsdcUsdPrice(),
                descriptor("USDC/USD rate", Meta.of("origin", "Binance")));

        //get assets
        final var binanceBtcAmount = ctx.wrap(
                dataProvider.fetchBinanceBtcAmount(),
                descriptor("BTC balance", Meta.of("source", "Binance")));
        final var binanceEthAmount = ctx.wrap(
                dataProvider.fetchBinanceEthAmount(),
                descriptor("ETH balance", Meta.of("source", "Binance")));
        final var binanceUsdcAmount = ctx.wrap(
                dataProvider.fetchBinanceUsdcAmount(),
                descriptor("USDC balance", Meta.of("source", "Binance")));
        final var stakedEthAmount = ctx.wrap(
                dataProvider.fetchStakedEthAmount(),
                descriptor("ETH balance", Meta.of("source", "Stake")));
        final var morphoUsdcAmount = ctx.wrap(
                dataProvider.fetchMorphoUsdcAmount(),
                descriptor("USDC balance", Meta.of("source", "Morpho")));

        //convert to usd and sum — computed correctly, but never consumed below
        final var assetsSum = binanceBtcAmount.convert(btcUsdRate, descriptor("BTC->USD"))
                .addBulk(List.of(
                                binanceEthAmount.convert(ethUsdRate, descriptor("ETH(Binance)->USD")),
                                binanceUsdcAmount.convert(usdcUsdRate, descriptor("USDC(Binance)->USD")),
                                stakedEthAmount.convert(ethUsdRate, descriptor("ETH(Staked)->USD")),
                                morphoUsdcAmount.convert(usdcUsdRate, descriptor("USDC(Morpho)->USD"))),
                        descriptor("Assets sum (computed, unused)"));

        // === Reported NAV: a disconnected literal standing in for the real sum ===
        final var nav = ctx.wrap(
                new Amount(Currency.USD, new BigDecimal("185000.00")), descriptor("Assets sum"));

        final var netAssetValueSnapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(netAssetValueSnapshot);
        System.out.println(provenanceGraph);
    }
}
