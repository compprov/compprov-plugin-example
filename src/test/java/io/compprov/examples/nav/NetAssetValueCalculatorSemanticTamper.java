package io.compprov.examples.nav;

import io.compprov.core.meta.Meta;
import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.Currency;
import io.compprov.examples.nav.model.NetAssetValueDataProvider;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetAssetValueCalculatorSemanticTamper {

    @Test
    public void calculate() {

        final var ctx = new NavComputationContext("Nav calculation example");
        NetAssetValueDataProvider dataProvider = new NetAssetValueDataProvider();

        //get rates
        final var btcUsdRate = ctx.wrap(
                dataProvider.fetchBtcUsdPrice(),
                descriptor("BTC/USD rate", Meta.of("origin", "Binance")));
        final var ethUsdRate = ctx.wrap(
                dataProvider.fetchEthUsdPrice(),
                descriptor("ETH/USD rate", Meta.of("origin", "Binance")));
        final var wstethEthRate = ctx.wrap(
                dataProvider.fetchWstEthEthPrice(),
                descriptor("WSTETH/ETH rate", Meta.of("origin", "Binance")));
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
                descriptor("ETH balance", Meta.of("source", "Trust Nodes")));
        final var stakedLidoEthAmount = ctx.wrap(
                dataProvider.fetchStakedLidoAmount(),
                descriptor("WSTETH balance", Meta.of("source", "Lido")));
        final var morphoUsdcAmount = ctx.wrap(
                dataProvider.fetchMorphoUsdcAmount(),
                descriptor("USDC balance", Meta.of("source", "Morpho")));

        //semanticx
        var wsteth = stakedLidoEthAmount.add(stakedEthAmount, descriptor("WSTETH balance"));
        var ethStaked = wsteth.convert(wstethEthRate, descriptor("WSTETH->ETH"));

        //convert to usd and sum
        final var nav = binanceBtcAmount.convert(btcUsdRate, descriptor("BTC->USD"))
                .addBulk(List.of(
                                binanceEthAmount.convert(ethUsdRate, descriptor("ETH(Binance)->USD")),
                                binanceUsdcAmount.convert(usdcUsdRate, descriptor("USDC(Binance)->USD")),
                                ethStaked.convert(ethUsdRate, descriptor("ETH(Staked)->USD")),
                                morphoUsdcAmount.convert(usdcUsdRate, descriptor("USDC(Morpho)->USD"))),
                        descriptor("Assets sum"));

        final var netAssetValueSnapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(netAssetValueSnapshot);
        System.out.println(provenanceGraph);
    }
}
