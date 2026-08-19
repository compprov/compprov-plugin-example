package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

public class VaultFirstDepositCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("DeFi vault: first deposit")));
        VaultFirstDepositDataProvider dp = new VaultFirstDepositDataProvider();

        final var firstDepositAmount = ctx.wrapBigInteger(dp.fetchFirstDepositAmount(), descriptor("First deposit amount"));
        final var minimumLiquidityConstant = ctx.wrapBigInteger(dp.fetchMinimumLiquidityConstant(), descriptor("Minimum liquidity constant"));
        final var previousTotalShareSupply = ctx.wrapBigInteger(dp.fetchPreviousTotalShareSupply(), descriptor("Total share supply (before this deposit)"));

        // The floor is still computed correctly, in its own isolated subgraph...
        final var lockedFloor = firstDepositAmount.min(minimumLiquidityConstant, descriptor("Minimum liquidity floor"));

        // tampered: shares are minted straight from the raw deposit — lockedFloor above is never subtracted
        final var firstDepositorShares = previousTotalShareSupply.add(firstDepositAmount, descriptor("First depositor shares"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
