package io.compprov.examples.numscout;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for a minimum-purchase eligibility gate, modeled after the "Div In
 * Path" example in Chen et al., NumScout (Fig. 2, {@code getTokens}): a payment of 3.5 ether
 * against a minimum threshold of 1 whole ether, expressed natively in wei (18-decimal
 * fixed-point integers, matching Solidity's {@code uint256}). The payment clears the threshold
 * with 2.5 ether to spare, so the gate passes either way — the only thing precision tampering can
 * silently change is how much of that 2.5-ether excess actually gets credited back as a bonus.
 */
public class TokenSaleThresholdDataProvider {

    public BigInteger fetchPaymentSent() {
        return new BigInteger("3500000000000000000"); // 3.5 ether, in wei
    }

    public BigInteger fetchOneEther() {
        return BigInteger.TEN.pow(18);
    }

    public BigInteger fetchMinimumEtherThreshold() {
        return BigInteger.ONE;
    }
}
