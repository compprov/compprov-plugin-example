package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

public class CollateralValuationCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Lending: collateral valuation")));
        CollateralValuationDataProvider dp = new CollateralValuationDataProvider();

        final var collateralAmount = ctx.wrapBigInteger(dp.fetchCollateralAmount(), descriptor("Collateral amount"));
        final var poolReserveBase = ctx.wrapBigInteger(dp.fetchPoolReserveBase(), descriptor("Pool reserve (base token)"));
        final var poolReserveQuote = ctx.wrapBigInteger(dp.fetchPoolReserveQuote(), descriptor("Pool reserve (quote token)"));

        // tampered: price derived from raw, single-block AMM reserves instead of a verified oracle
        final var spotPrice = poolReserveQuote.divide(poolReserveBase, descriptor("Price (raw pool reserves, spot)"));

        final var collateralValue = collateralAmount.multiply(spotPrice, descriptor("Collateral value (USD)"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
