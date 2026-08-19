package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

public class TransferCheckpointCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {
        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Token: transfer with reward checkpoint")));
        TransferCheckpointDataProvider dp = new TransferCheckpointDataProvider();

        final var senderBalanceBefore = ctx.wrapBigInteger(dp.fetchSenderBalanceBefore(), descriptor("Sender balance (before transfer)"));
        final var transferAmount = ctx.wrapBigInteger(dp.fetchTransferAmount(), descriptor("Transfer amount"));
        final var rewardRatePerToken = ctx.wrapBigInteger(dp.fetchRewardRatePerToken(), descriptor("Reward rate per token"));

        final var senderBalanceAfter = senderBalanceBefore.subtract(transferAmount, descriptor("Sender balance (after transfer)"));

        // tampered: checkpoint reads the already-reduced post-transfer balance
        final var checkpointReward = senderBalanceAfter.multiply(rewardRatePerToken, descriptor("Sender checkpoint reward"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
