package io.compprov.examples.nav;

import io.compprov.core.meta.Meta;
import io.compprov.examples.TestComputationContext;
import io.compprov.examples.nav.model.NetAssetValueDataProvider;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetAssetValueCalculator {

    @Test
    public void calculate() {

        TestComputationContext ctx = new TestComputationContext("Nav calculation example");
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

        //convert to usd and sum
        final var nav = binanceBtcAmount.convert(btcUsdRate, descriptor("BTC->USD"))
                .addBulk(List.of(
                                binanceEthAmount.convert(ethUsdRate, descriptor("ETH(Binance)->USD")),
                                binanceUsdcAmount.convert(usdcUsdRate, descriptor("USDC(Binance)->USD")),
                                stakedEthAmount.convert(ethUsdRate, descriptor("ETH(Staked)->USD")),
                                morphoUsdcAmount.convert(usdcUsdRate, descriptor("USDC(Morpho)->USD"))),
                        descriptor("Assets sum"));

        final var netAssetValueSnapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(netAssetValueSnapshot);
        System.out.println(provenanceGraph);
    }
}
