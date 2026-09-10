package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Meta;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Semantic Type and Context Cast attack — GPTScan "Vote Manipulation by Flashloan" pattern (Sun
 * et al., Table 1): a vote weight is drawn from a live, same-transaction balance read instead of
 * a fixed historical checkpoint, then consumed downstream as though it were a settled holding.
 *
 * <p>Both balance candidates are honestly computed and honestly typed ({@code BigInteger} ->
 * {@code BigInteger}), and the graph type-checks and replays perfectly. The data provider
 * supplies both a checkpointed balance and the voter's live balance, each tagged with the block
 * number it was read at; only the live figure is wired into the tally — the other candidate is
 * computed and left in the graph, unconsumed.</p>
 */
public class GovernanceVoteWeightCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {
        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Governance: vote tally")));
        GovernanceVoteWeightDataProvider dp = new GovernanceVoteWeightDataProvider();

        final var existingVotes = ctx.wrapBigInteger(dp.fetchExistingVotesForProposal(), descriptor("Existing votes for proposal"));

        // A second, independently-sourced balance for the same voter — computed here, present
        // in the graph, and never consumed by anything downstream.
        final var voterBalanceAtSnapshot = ctx.wrapBigInteger(dp.fetchVerifiedSnapshotBalance(),
                descriptor("Voter balance (at proposal snapshot block)",
                        Meta.of("blockNumber", Long.toString(dp.fetchProposalSnapshotBlock()))));

        final var voterBalanceCurrent = ctx.wrapBigInteger(dp.fetchFlashloanInflatedBalance(),
                descriptor("Voter balance (current block)",
                        Meta.of("blockNumber", Long.toString(dp.fetchVoteCastBlock()))));

        // tampered: tally uses the live current-block balance, not the snapshot balance above
        final var newTally = existingVotes.add(voterBalanceCurrent, descriptor("New vote tally"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
