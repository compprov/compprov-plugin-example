package io.compprov.examples.profit;

import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.Currency;
import io.compprov.examples.nav.model.Rate;

import java.math.BigDecimal;

/**
 * Provides input data for the DeFi portfolio profit calculation scenario.
 *
 * <p>Portfolio (all on Ethereum mainnet, withdrawing June 30, 2026):
 * <ul>
 *   <li>9.3 wBTC — AAVE, deposited June 1 (29 days, 0.50% APY)</li>
 *   <li>16 ETH — AAVE, deposited June 4 (26 days, 2.40% APY)</li>
 *   <li>32 000 USDC — AAVE, deposited June 8 (22 days, 6.20% APY)</li>
 *   <li>50 000 USDT — Morpho, deposited June 3 (27 days, 8.10% APY)</li>
 *   <li>31.3 ETH — Lido staked, EtherFi restaked, June 1 (29 days, 6.80% APY combined)</li>
 * </ul>
 *
 * <p>Prices are approximate market values for June 2026.
 * Gas costs are estimated from typical Ethereum L1 activity for those dates.
 */
public class ProfitDataProvider {

    // -------------------------------------------------------------------------
    // Current exchange rates — June 30, 2026
    // -------------------------------------------------------------------------

    public Rate fetchBtcUsdcRate() {
        return new Rate(Currency.BTC, Currency.USDC, new BigDecimal("109800"));
    }

    public Rate fetchEthUsdcRate() {
        return new Rate(Currency.ETH, Currency.USDC, new BigDecimal("4650"));
    }

    public Rate fetchUsdtUsdcRate() {
        return new Rate(Currency.USDT, Currency.USDC, new BigDecimal("0.9998"));
    }

    // -------------------------------------------------------------------------
    // ETH/USDC rates at each deposit date (used to convert gas fees to USDC)
    // -------------------------------------------------------------------------

    /** June 1, 2026 */
    public Rate fetchEthUsdcRateJune1() {
        return new Rate(Currency.ETH, Currency.USDC, new BigDecimal("4480"));
    }

    /** June 3, 2026 */
    public Rate fetchEthUsdcRateJune3() {
        return new Rate(Currency.ETH, Currency.USDC, new BigDecimal("4420"));
    }

    /** June 4, 2026 */
    public Rate fetchEthUsdcRateJune4() {
        return new Rate(Currency.ETH, Currency.USDC, new BigDecimal("4390"));
    }

    /** June 8, 2026 */
    public Rate fetchEthUsdcRateJune8() {
        return new Rate(Currency.ETH, Currency.USDC, new BigDecimal("4310"));
    }

    // -------------------------------------------------------------------------
    // Protocol yields in native token (initial deposit × APY × days/365)
    // -------------------------------------------------------------------------

    /** 9.3 wBTC × 0.50% × 29d = 0.00369452 wBTC */
    public Amount fetchAaveWbtcYield() {
        return new Amount(Currency.WBTC, new BigDecimal("0.00369452"));
    }

    /** wBTC/BTC peg rate — AAVE's wBTC is fully collateralized 1:1 by custodied BTC. */
    public Rate fetchWbtcBtcPegRate() {
        return new Rate(Currency.WBTC, Currency.BTC, BigDecimal.ONE);
    }

    /** 16 ETH × 2.40% × 26d = 0.02735342 ETH */
    public Amount fetchAaveEthYield() {
        return new Amount(Currency.ETH, new BigDecimal("0.02735342"));
    }

    /** 32 000 USDC × 6.20% × 22d = 119.583561 USDC */
    public Amount fetchAaveUsdcYield() {
        return new Amount(Currency.USDC, new BigDecimal("119.583561"));
    }

    /** 50 000 USDT × 8.10% × 27d = 299.589041 USDT */
    public Amount fetchMorphoUsdtYield() {
        return new Amount(Currency.USDT, new BigDecimal("299.589041"));
    }

    /** 31.3 ETH × 6.80% × 29d = 0.16910563 ETH (Lido 4.5% + EtherFi restaking 2.3%) */
    public Amount fetchLidoEtherfiEthYield() {
        return new Amount(Currency.ETH, new BigDecimal("0.16910563"));
    }

    // -------------------------------------------------------------------------
    // Gas fees in ETH at each deposit
    // Estimated: gas_units × gwei_price × 1e-9 ETH
    // -------------------------------------------------------------------------

    /** wBTC deposit to AAVE, June 1: 250 000 gas × 20 gwei = 0.005 ETH */
    public Amount fetchGasAaveWbtcDeposit() {
        return new Amount(Currency.ETH, new BigDecimal("0.005"));
    }

    /** ETH deposit to AAVE, June 4: 220 000 gas × 18 gwei = 0.00396 ETH */
    public Amount fetchGasAaveEthDeposit() {
        return new Amount(Currency.ETH, new BigDecimal("0.00396"));
    }

    /** USDC deposit to AAVE, June 8: 200 000 gas × 15 gwei = 0.003 ETH */
    public Amount fetchGasAaveUsdcDeposit() {
        return new Amount(Currency.ETH, new BigDecimal("0.003"));
    }

    /** USDT deposit to Morpho, June 3: 180 000 gas × 22 gwei = 0.00396 ETH */
    public Amount fetchGasMorphoUsdtDeposit() {
        return new Amount(Currency.ETH, new BigDecimal("0.00396"));
    }

    /** ETH stake in Lido, June 1: 200 000 gas × 20 gwei = 0.004 ETH */
    public Amount fetchGasLidoStake() {
        return new Amount(Currency.ETH, new BigDecimal("0.004"));
    }

    /** stETH restake in EtherFi, June 1: 260 000 gas × 20 gwei = 0.0052 ETH */
    public Amount fetchGasEtherfiRestake() {
        return new Amount(Currency.ETH, new BigDecimal("0.0052"));
    }

    // -------------------------------------------------------------------------
    // Platform fee
    // -------------------------------------------------------------------------

    /** 3% platform fee applied to gross yield */
    public BigDecimal fetchPlatformFeeRate() {
        return new BigDecimal("0.03");
    }
}
