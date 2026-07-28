package io.compprov.examples.nav;

import io.compprov.core.meta.Meta;
import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.Currency;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Calculates the net profit in USDC for a DeFi portfolio as of June 30, 2026.
 *
 * <p>Profit = gross yield (in USDC at today's prices)
 *           − total gas fees (in USDC at deposit-date prices)
 *           − platform fee (3% of gross yield)
 */
public class ProfitCalculator {

    @Test
    public void calculate() {

        final var ctx = new NavComputationContext("DeFi portfolio profit calculation");
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

        // === Convert all yields to USDC at today's prices ===
        final var grossYield = wbtcAaveYield.convert(btcUsdcRate, descriptor("wBTC yield in USDC"))
                .addBulk(List.of(
                                ethAaveYield.convert(ethUsdcRate, descriptor("ETH(AAVE) yield in USDC")),
                                usdcAaveYield,   // already in USDC
                                usdtMorphoYield.convert(usdtUsdcRate, descriptor("USDT(Morpho) yield in USDC")),
                                ethLidoEtherfiYield.convert(ethUsdcRate, descriptor("ETH(Lido+EtherFi) yield in USDC"))),
                        descriptor("Gross yield in USDC"));

        // === Convert gas fees to USDC using the ETH price at each deposit date ===
        final var totalGasFees = gasAaveWbtc.convert(ethRateJune1, descriptor("Gas (wBTC/AAVE) in USDC"))
                .addBulk(List.of(
                                gasAaveEth.convert(ethRateJune4, descriptor("Gas (ETH/AAVE) in USDC")),
                                gasAaveUsdc.convert(ethRateJune8, descriptor("Gas (USDC/AAVE) in USDC")),
                                gasMorphoUsdt.convert(ethRateJune3, descriptor("Gas (USDT/Morpho) in USDC")),
                                gasLido.convert(ethRateJune1, descriptor("Gas (ETH/Lido) in USDC")),
                                gasEtherfi.convert(ethRateJune1, descriptor("Gas (stETH/EtherFi) in USDC"))),
                        descriptor("Total gas fees in USDC"));

        // === Platform fee = 3% of gross yield ===
        final var platformFee = grossYield.scale(platformFeeRate, descriptor("Platform fee in USDC"));

        // === Net profit ===
        final var netProfit = grossYield
                .subtract(totalGasFees, descriptor("After gas deduction"))
                .subtract(platformFee, descriptor("Net profit in USDC"));

        // Store the provenance graph alongside the result
        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
