package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Meta;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Approval Not Cleared — GPTScan "Approval Not Cleared" pattern (Sun et al., Table 1):
 *
 * <blockquote>Scenario: add or check approval via require/if statements before the token
 * transfer.<br>
 * Property: and there is no clear/reset of the approval when the transfer finishes its main
 * branch or encounters exceptions.</blockquote>
 *
 * <p>Threads the vault's {@code allowance} through two actors' operations, in the vulnerable
 * order, to show what the missing reset actually costs. Actors are tagged only "userA"/"userB"
 * in the graph — never "attacker" — so nothing in the fixture's own metadata gives away which
 * transaction is the problem:
 *
 * <ol>
 *   <li>Vault approves User A for an operation.</li>
 *   <li>User A only pulls part of what they were approved for (tx 1). Per ERC20 semantics the
 *       allowance decrements by exactly what was pulled — but nothing in the graph ever resets
 *       the leftover back to zero. There is no {@code approve(userA, 0)} node here at all, which
 *       is exactly the absence GPTScan's property names.</li>
 *   <li>User B calls {@code transferFrom} directly against that same still-live leftover
 *       allowance (tx 2) — no new approval, no vault interaction required — and draws it in
 *       full.</li>
 * </ol>
 */
public class ApprovalNotClearedCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Vault: one allowance drawn on by two transferFrom calls")));
        ApprovalNotClearedDataProvider dp = new ApprovalNotClearedDataProvider();

        final var vaultBalance0 = ctx.wrapBigInteger(dp.fetchVaultTokenBalance(), descriptor("Vault token balance (t0)", Meta.of("actor", "vault")));

        // === tx 0: Vault approves User A for this operation ===
        final var allowance0 = ctx.wrapBigInteger(dp.fetchApprovedAmount(),
                descriptor("Allowance granted to User A", Meta.of("actor", "vault")));

        // === tx 1: User A pulls only part of the allowance — the rest is never reset to zero ===
        final var userAPull = ctx.wrapBigInteger(dp.fetchSpenderPullAmount(),
                descriptor("User A's transferFrom amount", Meta.of("actor", "userA")));
        final var vaultBalance1 = vaultBalance0.subtract(userAPull,
                descriptor("Vault token balance (after tx 1)", Meta.of("actor", "vault")));
        final var allowanceRemaining = allowance0.subtract(userAPull,
                descriptor("Allowance remaining after tx 1", Meta.of("actor", "vault")));

        // === tx 2: User B draws on the same still-live allowance directly ===
        final var vaultBalanceFinal = vaultBalance1.subtract(allowanceRemaining,
                descriptor("Vault token balance (after tx 2)", Meta.of("actor", "userB")));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
