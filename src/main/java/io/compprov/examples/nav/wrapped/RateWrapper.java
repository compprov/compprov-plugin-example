package io.compprov.examples.nav.wrapped;

import io.compprov.core.ComputationContext;
import io.compprov.core.variable.VariableTrack;
import io.compprov.core.variable.VariableWrapper;
import io.compprov.core.variable.WrappedVariable;
import io.compprov.examples.nav.model.Rate;

/**
 * {@link VariableWrapper} factory that turns a plain {@link Rate} into a provenance-tracked
 * {@link WrappedRate}. Registered with a {@link io.compprov.core.ComputationEnvironment} via
 * {@code environment.registerWrapper(Rate.class, new RateWrapper())}, typically from an
 * {@link io.compprov.core.EnvironmentCustomizer} such as {@link io.compprov.plugin.example.nav.NavEnvironmentCustomizer}.
 */
public class RateWrapper implements VariableWrapper<Rate> {
    @Override
    public WrappedVariable wrap(ComputationContext context, VariableTrack variableTrack, Rate value) {
        return new WrappedRate(context, variableTrack, value);
    }
}