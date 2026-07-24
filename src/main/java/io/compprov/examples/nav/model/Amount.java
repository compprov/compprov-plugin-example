package io.compprov.examples.nav.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * An immutable monetary value: a {@link Currency} paired with a {@link BigDecimal} amount,
 * scaled to that currency's {@link Currency#getDecimals() decimal precision} and truncated
 * (never rounded up) on construction and on every operation.
 * <p>
 * This is a plain domain type with no dependency on compprov-core; {@code io.compprov.examples.nav.wrapped}
 * wraps it for provenance tracking without modifying it, per the
 * {@link io.compprov.core.variable.VariableWrapper}/{@link io.compprov.core.variable.AbstractWrappedVariable}
 * extension pattern.
 */
public class Amount {
    final Currency currency;
    final BigDecimal amount;

    /**
     * @param currency the currency of this amount
     * @param amount   the numeric value, truncated (round down) to {@code currency}'s decimal precision
     */
    public Amount(Currency currency, BigDecimal amount) {
        this.currency = requireNonNull(currency);
        this.amount = requireNonNull(amount).setScale(currency.getDecimals(), RoundingMode.DOWN);
    }

    /**
     * Adds another amount in the same currency.
     *
     * @param amount the amount to add; must be in the same {@link Currency} as this amount
     * @return a new {@code Amount} holding the sum
     */
    public Amount add(Amount amount) {
        //The currency match check is intentionally skipped to create the possibility of semantic violation (test purpose).
        return new Amount(currency, this.amount.add(amount.amount));
    }

    public Amount subtract(Amount amount) {
        //The currency match check is intentionally skipped to create the possibility of semantic violation (test purpose).
        return new Amount(currency, this.amount.subtract(amount.amount));
    }

    public Amount scale(BigDecimal factor) {
        return new Amount(currency, this.amount.multiply(factor));
    }

    /**
     * Converts this amount to the other currency named in {@code rate}, using this amount's
     * currency to decide the direction: multiplying by {@link Rate#rate()} when this currency
     * is {@link Rate#from()}, dividing when it is {@link Rate#to()}.
     *
     * @param rate an exchange rate whose {@link Rate#from()} or {@link Rate#to()} matches this amount's currency
     * @return a new {@code Amount} in the other currency named by {@code rate}
     * @throws IllegalArgumentException if neither side of {@code rate} matches this amount's currency
     */
    public Amount convert(Rate rate) {
        if (currency == rate.from()) {
            return new Amount(rate.to(), amount.multiply(rate.rate()).setScale(rate.to().getDecimals(), RoundingMode.DOWN));
        } else if (currency == rate.to()) {
            return new Amount(rate.from(), amount.divide(rate.rate(), rate.from().getDecimals(), RoundingMode.DOWN));
        }
        throw new IllegalArgumentException("Invalid rate currency");
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "currency=" + currency +
                ", amount=" + amount.toPlainString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount1 = (Amount) o;
        return currency == amount1.currency && amount.compareTo(amount1.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }
}

