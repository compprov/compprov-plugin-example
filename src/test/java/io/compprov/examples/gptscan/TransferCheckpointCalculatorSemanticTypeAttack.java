package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Meta;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Semantic Type and Context Cast attack — GPTScan "Wrong Checkpoint Order" pattern (Sun et al.,
 * Table 1; Fig. 2, the Code4rena {@code 2022-04-backd} finding): {@code userCheckpoint()} (line
 * 10-11) must snapshot the sender's balance from before the transfer's balance mutation (line
 * 6-7), not after.
 *
 * <p>Modeled as a metadata cast rather than a chronology defect: both balance candidates are
 * honestly computed and honestly typed ({@code BigInteger} -> {@code BigInteger}), and the graph
 * type-checks and replays perfectly. Ledger terms stand in for the paper's before/after language:
 * the checkpoint reward's own {@code balanceBasis} metadata declares "opening" — the balance a
 * checkpoint is contractually required to use — while the value it actually consumes is the
 * balance node whose own metadata honestly says "closing". The declared business context is
 * silently re-mapped at the point of consumption; each node's {@code recordedAt} timestamp
 * (derived from when {@code userCheckpoint()} actually fires relative to the transfer) makes the
 * contradiction auditable: the reward's declared basis is stamped before the transfer, but the
 * number it was built from was recorded after.</p>
 */
public class TransferCheckpointCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {
        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Token: transfer with reward checkpoint")));
        TransferCheckpointDataProvider dp = new TransferCheckpointDataProvider();

        final var senderBalanceOpening = ctx.wrapBigInteger(dp.fetchSenderBalanceBefore(),
                descriptor("Sender balance (opening)",
                        Meta.of("balanceBasis", "opening",
                                "recordedAt", dp.fetchPreTransferSnapshotAt().toString())));
        final var transferAmount = ctx.wrapBigInteger(dp.fetchTransferAmount(), descriptor("Transfer amount"));
        final var rewardRatePerToken = ctx.wrapBigInteger(dp.fetchRewardRatePerToken(), descriptor("Reward rate per token"));

        final var senderBalanceClosing = senderBalanceOpening.subtract(transferAmount,
                descriptor("Sender balance (closing)",
                        Meta.of("balanceBasis", "closing",
                                "recordedAt", dp.fetchTransferExecutedAt().toString())));

        // tampered: userCheckpoint() fires after the balance mutation (GPTScan Fig. 2, line 10-11
        // vs. line 6-7), so the reward is built from the closing-balance node above — yet the
        // result still declares itself computed on the opening balance, exactly the contract a
        // checkpoint reward is required to satisfy. Business meaning is silently re-mapped at
        // consumption; the technical type (BigInteger) never changes.
        final var checkpointReward = senderBalanceClosing.multiply(rewardRatePerToken,
                descriptor("Sender checkpoint reward",
                        Meta.of("balanceBasis", "opening",
                                "recordedAt", dp.fetchCheckpointRecordedAt().toString())));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
