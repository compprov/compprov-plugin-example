package io.compprov.examples.forwardcontract;

import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.Currency;
import io.compprov.examples.nav.model.Rate;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a 90-day EUR/USD FX forward contract valued via covered interest
 * rate parity.
 */
public class ForwardContractDataProvider {

    public Amount fetchNotionalAmount() {
        return new Amount(Currency.EUR, new BigDecimal("2500000.00"));
    }

    /** Spot EUR/USD rate — a genuine external market quote, not a derived quantity. */
    public Rate fetchSpotRate() {
        return new Rate(Currency.EUR, Currency.USD, new BigDecimal("1.0850"));
    }

    /** Domestic (USD) interest rate for the 90-day contract period. */
    public BigDecimal fetchDomesticInterestRate() {
        return new BigDecimal("0.0525");
    }

    /** Foreign (EUR) interest rate for the 90-day contract period. */
    public BigDecimal fetchForeignInterestRate() {
        return new BigDecimal("0.0375");
    }

    public BigDecimal fetchOne() {
        return BigDecimal.ONE;
    }
}
