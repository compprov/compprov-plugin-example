package io.compprov.examples.insurance;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for three auto insurance claims (collision, comprehensive,
 * liability), each adjudicated against its own deductible / coinsurance rate / policy limit,
 * plus a reinsurance recovery rate applied to the insurer's total payout.
 */
public class ClaimsDataProvider {

    // === Collision claim ===
    public BigDecimal fetchCollisionClaimAmount() {
        return new BigDecimal("8000.00");
    }

    public BigDecimal fetchCollisionDeductible() {
        return new BigDecimal("500.00");
    }

    public BigDecimal fetchCollisionCoinsuranceRate() {
        return new BigDecimal("0.80");
    }

    public BigDecimal fetchCollisionPolicyLimit() {
        return new BigDecimal("10000.00");
    }

    // === Comprehensive claim (claim size exceeds its policy limit) ===
    public BigDecimal fetchComprehensiveClaimAmount() {
        return new BigDecimal("20000.00");
    }

    public BigDecimal fetchComprehensiveDeductible() {
        return new BigDecimal("1000.00");
    }

    public BigDecimal fetchComprehensiveCoinsuranceRate() {
        return new BigDecimal("0.90");
    }

    public BigDecimal fetchComprehensivePolicyLimit() {
        return new BigDecimal("12000.00");
    }

    // === Liability claim ===
    public BigDecimal fetchLiabilityClaimAmount() {
        return new BigDecimal("5000.00");
    }

    public BigDecimal fetchLiabilityDeductible() {
        return new BigDecimal("250.00");
    }

    public BigDecimal fetchLiabilityCoinsuranceRate() {
        return new BigDecimal("1.00");
    }

    public BigDecimal fetchLiabilityPolicyLimit() {
        return new BigDecimal("6000.00");
    }

    public BigDecimal fetchReinsuranceRate() {
        return new BigDecimal("0.40");
    }
}
