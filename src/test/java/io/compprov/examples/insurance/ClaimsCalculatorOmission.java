package io.compprov.examples.insurance;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Meta;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

public class ClaimsCalculatorOmission {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Insurance: claims adjudication")));
        ClaimsDataProvider dp = new ClaimsDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        final var zero = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Zero (claim floor)"));

        final var collisionClaim = ctx.wrapBigDecimal(
                dp.fetchCollisionClaimAmount(), descriptor("Claim amount", Meta.of("claimType", "Collision")));
        final var collisionDeductible = ctx.wrapBigDecimal(dp.fetchCollisionDeductible(), descriptor("Deductible", Meta.of("claimType", "Collision")));
        final var collisionCoinsurance = ctx.wrapBigDecimal(dp.fetchCollisionCoinsuranceRate(), descriptor("Coinsurance rate", Meta.of("claimType", "Collision")));
        final var collisionLimit = ctx.wrapBigDecimal(dp.fetchCollisionPolicyLimit(), descriptor("Policy limit", Meta.of("claimType", "Collision")));

        final var collisionNetOfDeductible = collisionClaim.subtract(collisionDeductible, mc, descriptor("Collision: net of deductible"))
                .max(zero, descriptor("Collision: net of deductible (floored)"));
        final var collisionCoinsuranceAmount = collisionNetOfDeductible.multiply(collisionCoinsurance, mc, descriptor("Collision: coinsurance amount"));
        final var collisionPayout = collisionCoinsuranceAmount.min(collisionLimit, descriptor("Collision claim payout"));

        final var comprehensiveClaim = ctx.wrapBigDecimal(
                dp.fetchComprehensiveClaimAmount(), descriptor("Claim amount", Meta.of("claimType", "Comprehensive")));
        final var comprehensiveDeductible = ctx.wrapBigDecimal(dp.fetchComprehensiveDeductible(), descriptor("Deductible", Meta.of("claimType", "Comprehensive")));
        final var comprehensiveCoinsurance = ctx.wrapBigDecimal(dp.fetchComprehensiveCoinsuranceRate(), descriptor("Coinsurance rate", Meta.of("claimType", "Comprehensive")));
        final var comprehensiveLimit = ctx.wrapBigDecimal(dp.fetchComprehensivePolicyLimit(), descriptor("Policy limit", Meta.of("claimType", "Comprehensive")));

        final var comprehensiveNetOfDeductible = comprehensiveClaim.subtract(comprehensiveDeductible, mc, descriptor("Comprehensive: net of deductible"))
                .max(zero, descriptor("Comprehensive: net of deductible (floored)"));
        final var comprehensiveCoinsuranceAmount = comprehensiveNetOfDeductible.multiply(comprehensiveCoinsurance, mc, descriptor("Comprehensive: coinsurance amount"));
        final var comprehensivePayout = comprehensiveCoinsuranceAmount.min(comprehensiveLimit, descriptor("Comprehensive claim payout"));

        final var liabilityClaim = ctx.wrapBigDecimal(
                dp.fetchLiabilityClaimAmount(), descriptor("Claim amount", Meta.of("claimType", "Liability")));
        final var liabilityDeductible = ctx.wrapBigDecimal(dp.fetchLiabilityDeductible(), descriptor("Deductible", Meta.of("claimType", "Liability")));
        final var liabilityCoinsurance = ctx.wrapBigDecimal(dp.fetchLiabilityCoinsuranceRate(), descriptor("Coinsurance rate", Meta.of("claimType", "Liability")));
        final var liabilityLimit = ctx.wrapBigDecimal(dp.fetchLiabilityPolicyLimit(), descriptor("Policy limit", Meta.of("claimType", "Liability")));

        final var liabilityNetOfDeductible = liabilityClaim.subtract(liabilityDeductible, mc, descriptor("Liability: net of deductible"))
                .max(zero, descriptor("Liability: net of deductible (floored)"));
        final var liabilityCoinsuranceAmount = liabilityNetOfDeductible.multiply(liabilityCoinsurance, mc, descriptor("Liability: coinsurance amount"));
        final var liabilityPayout = liabilityCoinsuranceAmount.min(liabilityLimit, descriptor("Liability claim payout"));

        //tamper here liability payout is skipped
        final var totalPayout = collisionPayout.addBulk(
                List.of(comprehensivePayout/*, liabilityPayout*/), mc, descriptor("Total payout"));
        final var reinsuranceRate = ctx.wrapBigDecimal(dp.fetchReinsuranceRate(), descriptor("Reinsurance recovery rate (40%)"));
        // Computed correctly, but never subtracted below.
        final var reinsuranceRecovery = totalPayout.multiply(reinsuranceRate, mc, descriptor("Reinsurance recovery"));

        // === Net loss
        final var netLoss = totalPayout.subtract(reinsuranceRecovery, mc, descriptor("Net loss"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
