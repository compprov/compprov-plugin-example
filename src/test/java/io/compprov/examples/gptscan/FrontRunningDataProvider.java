package io.compprov.examples.gptscan;

import java.math.BigInteger;

/**
 * Fixed, reproducible inputs for a mint front-running attack, modeled after the "Front Running"
 * scenario in Sun et al., GPTScan (Table 1) and its {@code 2021-08-yield} example (Fig. 8): a
 * {@code mint()} that infers the caller's deposit from a contract-wide {@code cached} balance
 * instead of a per-user record, so whoever calls it first claims whatever tokens are sitting
 * unaccounted-for in the pool.
 */
public class FrontRunningDataProvider {

    /** Existing pool share supply before either actor's mint call. */
    public BigInteger fetchInitialTotalSupply() {
        return BigInteger.valueOf(1_000_000);
    }

    /** Contract-wide accounted balance before either actor's mint call — starts equal to the pool's actual balance. */
    public BigInteger fetchInitialCachedBalance() {
        return BigInteger.valueOf(1_000_000);
    }

    /** The pool's actual token balance, matching {@code cached} before user A's deposit lands. */
    public BigInteger fetchInitialPoolBalance() {
        return BigInteger.valueOf(1_000_000);
    }

    /** Tokens user A transfers into the pool via a router, ahead of their own follow-up {@code mint()} call. */
    public BigInteger fetchUserADepositAmount() {
        return BigInteger.valueOf(250_000);
    }
}
