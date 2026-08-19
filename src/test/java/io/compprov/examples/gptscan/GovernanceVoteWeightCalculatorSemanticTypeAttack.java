package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

public class GovernanceVoteWeightCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {
        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Governance: vote tally")));
        GovernanceVoteWeightDataProvider dp = new GovernanceVoteWeightDataProvider();

        final var existingVotes = ctx.wrapBigInteger(dp.fetchExistingVotesForProposal(), descriptor("Existing votes for proposal"));
        // tampered: raw, same-transaction balance instead of the verified snapshot
        final var voterBalance = ctx.wrapBigInteger(dp.fetchFlashloanInflatedBalance(), descriptor("Voter balance (current)"));

        final var newTally = existingVotes.add(voterBalance, descriptor("New vote tally"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
