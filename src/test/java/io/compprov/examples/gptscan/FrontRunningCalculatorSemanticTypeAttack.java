package io.compprov.examples.gptscan;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Meta;
import org.junit.jupiter.api.Test;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Front Running — GPTScan "Front Running" pattern (Sun et al., Table 1):
 *
 * <blockquote>Scenario: mint or vest or collect token/liquidity/earning and assign them to the
 * address recipient or to variable.<br>
 * Property: and this operation could be front run to benefit the account/address that can be
 * controlled by the parameter and has no sender check in the function code.</blockquote>
 *
 * <p>Modeled after the paper's own worked example, the real {@code 2021-08-yield} project (Fig.
 * 8): a {@code mint()} that computes the caller's deposit as
 * {@code pool.balanceOf(this) - cached} — a contract-wide delta, not a per-user one. Threading
 * {@code cached} and the pool balance through two actors' {@code mint()} calls, in the vulnerable
 * order, shows exactly how that missing sender check gets exploited. Actors are tagged only
 * "userA"/"userB" in the graph (never a role like "attacker"), so a fraud-detection prompt has
 * to infer the problem from the data flow, not from a metadata hint:
 *
 * <ol>
 *   <li>User A transfers deposit tokens into the pool via a router (a separate transaction from
 *       minting, by design), raising the pool's actual balance without touching {@code cached}.</li>
 *   <li>User B calls {@code mint()} first (tx 1). {@code deposit = poolBalance - cached} computes
 *       to exactly A's transfer, because nothing ties the delta to who funded it — User B is
 *       minted shares for tokens they never deposited, and {@code cached} absorbs the delta.</li>
 *   <li>User A's own {@code mint()} call executes next (tx 2), against the now-updated
 *       {@code cached}. The delta available to them is zero — they funded the deposit and
 *       received nothing.</li>
 * </ol>
 */
public class FrontRunningCalculatorSemanticTypeAttack {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Yield pool: two mint() calls sharing one cached balance")));
        FrontRunningDataProvider dp = new FrontRunningDataProvider();

        final var totalSupply = ctx.wrapBigInteger(dp.fetchInitialTotalSupply(), descriptor("Total supply (t0)", Meta.of("actor", "pool")));
        final var cached0 = ctx.wrapBigInteger(dp.fetchInitialCachedBalance(), descriptor("cached (t0)", Meta.of("actor", "pool")));
        final var poolBalance0 = ctx.wrapBigInteger(dp.fetchInitialPoolBalance(), descriptor("pool.balanceOf(this) (t0)", Meta.of("actor", "pool")));

        // === tx 0: User A transfers tokens in via the router — a real balance change, uncached ===
        final var userADeposit = ctx.wrapBigInteger(dp.fetchUserADepositAmount(),
                descriptor("User A's router transfer (LP tokens)", Meta.of("actor", "userA")));
        final var poolBalance1 = poolBalance0.add(userADeposit,
                descriptor("pool.balanceOf(this) (after tx 0)", Meta.of("actor", "pool")));

        // === tx 1: User B calls mint() — deposit = poolBalance - cached, with no sender check ===
        final var depositUserB = poolBalance1.subtract(cached0,
                descriptor("deposit seen by User B's mint() call", Meta.of("actor", "userB")));
        final var mintedUserB = totalSupply.multiply(depositUserB,
                        descriptor("totalSupply * deposit", Meta.of("actor", "userB")))
                .divide(cached0, descriptor("Shares minted to User B", Meta.of("actor", "userB")));
        final var cached1 = cached0.add(depositUserB,
                descriptor("cached (after tx 1)", Meta.of("actor", "pool")));

        // === tx 2: User A's own mint() executes next — the delta they funded is already gone ===
        final var depositUserA = poolBalance1.subtract(cached1,
                descriptor("deposit seen by User A's mint() call", Meta.of("actor", "userA")));
        final var mintedUserA = totalSupply.multiply(depositUserA,
                        descriptor("totalSupply * deposit", Meta.of("actor", "userA")))
                .divide(cached1, descriptor("Shares minted to User A", Meta.of("actor", "userA")));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
