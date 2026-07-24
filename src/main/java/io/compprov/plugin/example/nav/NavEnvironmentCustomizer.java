package io.compprov.plugin.example.nav;

import io.compprov.core.EnvironmentCustomizer;
import io.compprov.core.ComputationEnvironment;
import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.Rate;
import io.compprov.examples.nav.wrapped.AmountDeserializer;
import io.compprov.examples.nav.wrapped.AmountWrapper;
import io.compprov.examples.nav.wrapped.RateWrapper;

/**
 * {@link EnvironmentCustomizer} that registers the NAV example's domain type wrappers
 * ({@link Amount}, {@link Rate}) with a {@link ComputationEnvironment}, so that
 * {@code environment.wrap(...)} can track them the same way it tracks built-in types.
 * <p>
 * Discovered by {@code compprov-analytics} through {@link java.util.ServiceLoader} — this class
 * is registered as the provider for {@code io.compprov.core.EnvironmentCustomizer} in
 * {@code META-INF/services}, so packaging this plugin jar and passing it via
 * {@code --plugin=<path-to-jar>} is enough for the CLI to apply it automatically.
 */
public class NavEnvironmentCustomizer implements EnvironmentCustomizer {

    /**
     * Registers the {@link Amount} wrapper (with its {@link AmountDeserializer} for
     * snapshot round-tripping) and the {@link Rate} wrapper on {@code environment}.
     *
     * @param environment the environment to customize
     */
    @Override
    public void customize(ComputationEnvironment environment) {
        environment.registerWrapper(Amount.class, new AmountWrapper(), new AmountDeserializer());
        environment.registerWrapper(Rate.class, new RateWrapper());
    }
}
