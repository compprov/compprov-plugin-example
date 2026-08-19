package io.compprov.examples;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Descriptor;
import io.compprov.core.meta.Meta;
import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.Currency;
import io.compprov.examples.nav.model.OptionPosition;
import io.compprov.examples.nav.model.Rate;
import io.compprov.examples.nav.wrapped.WrappedAmount;
import io.compprov.examples.nav.wrapped.WrappedOptionPosition;
import io.compprov.examples.nav.wrapped.WrappedRate;
import io.compprov.plugin.example.nav.NavEnvironmentCustomizer;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TestComputationContext extends DefaultComputationContext {
    protected static DefaultComputationEnvironment environment;

    static {
        environment = DefaultComputationEnvironment.create();
        new NavEnvironmentCustomizer().customize(environment);
    }

    /**
     * Every {@link Amount} operation ({@link WrappedAmount#add}, {@code subtract}, {@code scale},
     * {@code addBulk}, {@code convert}) truncates (rounds down) its result to the currency's
     * decimal precision — a deliberate, system-wide balance-safety invariant (see {@link Amount}'s
     * class Javadoc: a debit can never exceed the precise amount it was computed from), not an
     * undeclared precision loss. Recorded once on the graph's root descriptor, rather than
     * repeated on every individual operation, so an auditor has the declared, in-graph
     * justification for the rounding behavior without it cluttering every node.
     */
    private static final String ROUNDING_POLICY =
            "DOWN (Amount always truncates to the currency's decimal precision; balance-safety invariant)";

    /**
     * Decimal precision (scale) of every {@link Currency}, generated from {@link Currency#values()}
     * so it can't drift out of sync. Recorded once on the graph's root descriptor alongside
     * {@link #ROUNDING_POLICY} so an auditor can verify, from the graph alone, exactly how many
     * decimal places a given {@link Amount}'s currency was truncated to — without needing outside
     * knowledge of the {@link Currency} enum.
     */
    private static final Map<String, Integer> CURRENCY_PRECISIONS = Arrays.stream(Currency.values())
            .collect(Collectors.toMap(Currency::name, Currency::getDecimals, (a, b) -> a, LinkedHashMap::new));

    public TestComputationContext(String name) {
        super(environment, new DataContext(Descriptor.descriptor(
                name, Meta.of("rounding", ROUNDING_POLICY, "currencyPrecisions", CURRENCY_PRECISIONS))));
    }

    public WrappedAmount wrap(Amount amount, Descriptor descriptor) {
        return (WrappedAmount) super.wrap(amount, descriptor);
    }

    public WrappedRate wrap(Rate rate, Descriptor descriptor) {
        return (WrappedRate) super.wrap(rate, descriptor);
    }

    public WrappedOptionPosition wrap(OptionPosition position, Descriptor descriptor) {
        return (WrappedOptionPosition) super.wrap(position, descriptor);
    }
}
