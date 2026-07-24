package io.compprov.examples.nav.model;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

/**
 * An exchange rate between two {@link Currency} values: one unit of {@code from} is worth
 * {@code rate} units of {@code to}. Used by {@link Amount#convert(Rate)} to convert an amount
 * from one currency into another.
 *
 * @param from the source currency
 * @param to   the target currency
 * @param rate units of {@code to} per one unit of {@code from}
 */
public record Rate(Currency from, Currency to, BigDecimal rate) {

    public Rate {
        requireNonNull(from);
        requireNonNull(to);
        requireNonNull(rate);
    }

    @Override
    public String toString() {
        return "Rate{" +
                "from=" + from +
                ", to=" + to +
                ", rate=" + rate.toPlainString() +
                '}';
    }
}
