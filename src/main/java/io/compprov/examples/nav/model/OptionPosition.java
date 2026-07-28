package io.compprov.examples.nav.model;

import java.math.BigDecimal;

/**
 * A vanilla {@link OptionType} position with a strike {@link Rate} and a notional {@link Amount}.
 *
 * @param type   whether this position is a call or a put
 * @param strike the strike rate; its {@link Rate#to()} currency is also the currency
 *               {@link #payout(Rate)} returns its result in
 * @param size   the notional size the per-unit intrinsic value is multiplied by
 */
public record OptionPosition(OptionType type, Rate strike, Amount size) {

    /**
     * Computes this position's payout at expiry/settlement.
     *
     * @param price the settlement/spot rate to evaluate the position against
     * @return {@code max(intrinsic, 0) × size}, where {@code intrinsic} is
     * {@code price − strike} for a {@link OptionType#CALL} and {@code strike − price} for a
     * {@link OptionType#PUT}
     */
    public Amount payout(Rate price) {
        var diff = (type == OptionType.CALL)
                ? price.rate().subtract(strike.rate())
                : strike.rate().subtract(price.rate());

        return new Amount(strike.to(), diff.max(BigDecimal.ZERO).multiply(size.amount));
    }
}
