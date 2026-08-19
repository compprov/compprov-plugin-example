package io.compprov.examples.profit;

import io.compprov.core.meta.Meta;
import io.compprov.examples.TestComputationContext;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Calculates the net profit in USDC for a DeFi portfolio as of June 30, 2026.
 *
 * <p>Profit = gross yield (in USDC at today's prices)
 * − total gas fees (in USDC at deposit-date prices)
 * − platform fee (3% of gross yield)
 */
public class ProfitCalculatorGasOmission {

    @Test
    public void calculate() {

        final var ctx = new TestComputationContext("DeFi portfolio profit calculation");
        ProfitDataProvider dp = new ProfitDataProvider();

        // === Exchange rates — June 30, 2026 ===
        final var btcUsdcRate = ctx.wrap(
                dp.fetchBtcUsdcRate(),
                descriptor("BTC/USDC rate", Meta.of("date", "2026-06-30", "source", "market")));
        final var ethUsdcRate = ctx.wrap(
                dp.fetchEthUsdcRate(),
                descriptor("ETH/USDC rate", Meta.of("date", "2026-06-30", "source", "market")));
        final var usdtUsdcRate = ctx.wrap(
                dp.fetchUsdtUsdcRate(),
                descriptor("USDT/USDC rate", Meta.of("date", "2026-06-30", "source", "market")));
        final var wbtcBtcPegRate = ctx.wrap(
                dp.fetchWbtcBtcPegRate(),
                descriptor("WBTC/BTC peg rate", Meta.of("source", "AAVE, fully collateralized 1:1")));

        // === ETH rates at deposit dates — for converting gas to USDC ===
        final var ethRateJune1 = ctx.wrap(
                dp.fetchEthUsdcRateJune1(),
                descriptor("ETH/USDC rate", Meta.of("date", "2026-06-01", "source", "market")));
        final var ethRateJune3 = ctx.wrap(
                dp.fetchEthUsdcRateJune3(),
                descriptor("ETH/USDC rate", Meta.of("date", "2026-06-03", "source", "market")));
        final var ethRateJune4 = ctx.wrap(
                dp.fetchEthUsdcRateJune4(),
                descriptor("ETH/USDC rate", Meta.of("date", "2026-06-04", "source", "market")));
        final var ethRateJune8 = ctx.wrap(
                dp.fetchEthUsdcRateJune8(),
                descriptor("ETH/USDC rate", Meta.of("date", "2026-06-08", "source", "market")));

        // === Protocol yields in native token ===
        final var wbtcAaveYield = ctx.wrap(
                dp.fetchAaveWbtcYield(),
                descriptor("wBTC yield", Meta.of("protocol", "AAVE", "network", "ETH", "deposit", "2026-06-01")));
        final var ethAaveYield = ctx.wrap(
                dp.fetchAaveEthYield(),
                descriptor("ETH yield", Meta.of("protocol", "AAVE", "network", "ETH", "deposit", "2026-06-04")));
        final var usdcAaveYield = ctx.wrap(
                dp.fetchAaveUsdcYield(),
                descriptor("USDC yield", Meta.of("protocol", "AAVE", "network", "ETH", "deposit", "2026-06-08")));
        final var usdtMorphoYield = ctx.wrap(
                dp.fetchMorphoUsdtYield(),
                descriptor("USDT yield", Meta.of("protocol", "Morpho", "deposit", "2026-06-03")));
        final var ethLidoEtherfiYield = ctx.wrap(
                dp.fetchLidoEtherfiEthYield(),
                descriptor("ETH yield", Meta.of("protocol", "Lido+EtherFi", "network", "ETH", "deposit", "2026-06-01")));

        // === Gas fees in ETH at each deposit ===
        final var gasAaveWbtc = ctx.wrap(
                dp.fetchGasAaveWbtcDeposit(),
                descriptor("Gas (ETH)", Meta.of("tx", "wBTC→AAVE", "date", "2026-06-01")));
        final var gasAaveEth = ctx.wrap(
                dp.fetchGasAaveEthDeposit(),
                descriptor("Gas (ETH)", Meta.of("tx", "ETH→AAVE", "date", "2026-06-04")));
        final var gasAaveUsdc = ctx.wrap(
                dp.fetchGasAaveUsdcDeposit(),
                descriptor("Gas (ETH)", Meta.of("tx", "USDC→AAVE", "date", "2026-06-08")));
        final var gasMorphoUsdt = ctx.wrap(
                dp.fetchGasMorphoUsdtDeposit(),
                descriptor("Gas (ETH)", Meta.of("tx", "USDT→Morpho", "date", "2026-06-03")));
        final var gasLido = ctx.wrap(
                dp.fetchGasLidoStake(),
                descriptor("Gas (ETH)", Meta.of("tx", "ETH→Lido", "date", "2026-06-01")));
        final var gasEtherfi = ctx.wrap(
                dp.fetchGasEtherfiRestake(),
                descriptor("Gas (ETH)", Meta.of("tx", "stETH→EtherFi", "date", "2026-06-01")));

        // === Platform fee rate ===
        final var platformFeeRate = ctx.wrapBigDecimal(
                dp.fetchPlatformFeeRate(),
                descriptor("Platform fee rate (3%)"));

        // === Convert gas fees to USDC using the ETH price at each deposit date ===
        var gasAaveWbtcConverted = gasAaveWbtc.convert(ethRateJune1, descriptor("Gas (wBTC/AAVE) in USDC"));
        var gasAaveEthConverted = gasAaveEth.convert(ethRateJune4, descriptor("Gas (ETH/AAVE) in USDC"));
        var gasAaveUsdcConverted = gasAaveUsdc.convert(ethRateJune8, descriptor("Gas (USDC/AAVE) in USDC"));
        var gasMorphoUsdtConverted = gasMorphoUsdt.convert(ethRateJune3, descriptor("Gas (USDT/Morpho) in USDC"));
        var gasLidoConverted = gasLido.convert(ethRateJune1, descriptor("Gas (ETH/Lido) in USDC"));
        var gasEtherfiConverted = gasEtherfi.convert(ethRateJune1, descriptor("Gas (stETH/EtherFi) in USDC"));
        final var totalGasFees = gasAaveWbtcConverted
                .addBulk(List.of(gasAaveEthConverted,
                                gasAaveUsdcConverted,
                                gasMorphoUsdtConverted,
                                gasLidoConverted,
                                gasEtherfiConverted),
                        descriptor("Total gas fees in USDC"));

        // === Convert all yields to USDC at today's prices ===
        var wbtcAaveYieldInBtc = wbtcAaveYield.convert(wbtcBtcPegRate, descriptor("wBTC yield in BTC (peg-adjusted)"));
        var wbtcAaveYieldConverted = wbtcAaveYieldInBtc.convert(btcUsdcRate, descriptor("wBTC yield in USDC"));
        var ethAaveYieldConverted = ethAaveYield.convert(ethUsdcRate, descriptor("ETH(AAVE) yield in USDC"));
        var usdcAaveYieldConverted = usdcAaveYield;
        var usdtMorphoYieldConverted = usdtMorphoYield.convert(usdtUsdcRate, descriptor("USDT(Morpho) yield in USDC"));
        var ethLidoEtherfiYieldConverted = ethLidoEtherfiYield.convert(ethUsdcRate, descriptor("ETH(Lido+EtherFi) yield in USDC"));
        final var grossYield = wbtcAaveYieldConverted
                .addBulk(List.of(
                                ethAaveYieldConverted,
                                usdcAaveYieldConverted,
                                usdtMorphoYieldConverted,
                                ethLidoEtherfiYieldConverted),
                        descriptor("Gross yield in USDC"));

        // === Platform fee = 3% of gross yield ===
        final var platformFee = grossYield.scale(platformFeeRate, descriptor("Platform fee in USDC"));

        // === Net profit ===
        final var netProfit = grossYield
                //.subtract(totalGasFees, descriptor("After gas deduction"))
                .subtract(platformFee, descriptor("Net profit in USDC"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
