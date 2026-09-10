package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Semantic Type and Context Cast attack — GPTScan "Price Manipulation by AMM" pattern (Sun et
 * al., Table 1; Fig. 7, the {@code 2021-09-sushimiso} finding): a value is derived from live
 * market reserves (or a live {@code balanceOf}/{@code totalSupply} read) instead of a stable,
 * external reference, then consumed downstream as though it were trustworthy.
 *
 * <p>Both price candidates are honestly computed and honestly typed ({@code BigInteger} ->
 * {@code BigInteger}), and the graph type-checks and replays perfectly. The data provider
 * supplies both an external reference price and the pool's own reserve ratio for the same
 * base/quote pair; only the pool-derived figure is wired into the collateral valuation — the
 * other candidate is computed and left in the graph, unconsumed.</p>
 */
public class CollateralValuationCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Lending: collateral valuation")));
        CollateralValuationDataProvider dp = new CollateralValuationDataProvider();

        final var collateralAmount = ctx.wrapBigInteger(dp.fetchCollateralAmount(), descriptor("Collateral amount"));
        final var poolReserveBase = ctx.wrapBigInteger(dp.fetchPoolReserveBase(), descriptor("Pool reserve (base token)"));
        final var poolReserveQuote = ctx.wrapBigInteger(dp.fetchPoolReserveQuote(), descriptor("Pool reserve (quote token)"));

        // A second, independently-sourced price for the same pair — computed here, present in
        // the graph, and never consumed by anything downstream.
        final var oraclePrice = ctx.wrapBigInteger(dp.fetchVerifiedOraclePrice(), descriptor("Oracle price (quote per base)"));

        final var spotPrice = poolReserveQuote.divide(poolReserveBase, descriptor("Pool spot price (quote per base)"));

        // tampered: collateral is valued off the pool's own reserve ratio, not the oracle price above
        final var collateralValue = collateralAmount.multiply(spotPrice, descriptor("Collateral value (USD)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
