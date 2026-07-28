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
 * Truncation is a deliberate, system-wide rounding policy, not an oversight: when paying out of
 * a fixed balance (e.g. a portfolio, a fee pool), rounding a debit up instead of down could
 * subtract more than is actually available and drive the balance negative. Always rounding down
 * guarantees a debit never exceeds the precise amount it was computed from, at the cost of
 * leaving a sub-unit residue behind on each operation.
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
     * @apiNote The currency match is intentionally <b>not</b> validated here. This lets a caller
     * add amounts of different currencies without either being converted first — a semantic
     * type/context violation that passes technical type-checking and mathematical replay, used
     * deliberately by some test scenarios in this project to exercise that failure mode. Do not
     * rely on this method to reject mismatched currencies.
     */
    public Amount add(Amount amount) {
        return new Amount(currency, this.amount.add(amount.amount));
    }

    /**
     * Subtracts another amount in the same currency.
     *
     * @param amount the amount to subtract; must be in the same {@link Currency} as this amount
     * @return a new {@code Amount} holding the difference
     * @apiNote The currency match is intentionally <b>not</b> validated here, for the same reason
     * as {@link #add(Amount)} — do not rely on this method to reject mismatched currencies.
     */
    public Amount subtract(Amount amount) {
        return new Amount(currency, this.amount.subtract(amount.amount));
    }

    /**
     * Multiplies this amount by a dimensionless factor (e.g. a fee rate), keeping the same
     * currency.
     *
     * @param factor the multiplier to apply
     * @return a new {@code Amount} holding the product, in the same currency as this amount
     */
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

