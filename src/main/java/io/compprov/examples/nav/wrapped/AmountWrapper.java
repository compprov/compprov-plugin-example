package io.compprov.examples.nav.wrapped;

import io.compprov.core.ComputationContext;
import io.compprov.core.variable.VariableTrack;
import io.compprov.core.variable.VariableWrapper;
import io.compprov.core.variable.WrappedVariable;
import io.compprov.examples.nav.model.Amount;

/**
 * {@link VariableWrapper} factory that turns a plain {@link Amount} into a provenance-tracked
 * {@link WrappedAmount}. Registered with a {@link io.compprov.core.ComputationEnvironment} via
 * {@code environment.registerWrapper(Amount.class, new AmountWrapper(), ...)}, typically from
 * an {@link io.compprov.core.EnvironmentCustomizer} such as {@link io.compprov.plugin.example.nav.NavEnvironmentCustomizer}.
 */
public class AmountWrapper implements VariableWrapper<Amount> {
    @Override
    public WrappedVariable wrap(ComputationContext context, VariableTrack variableTrack, Amount value) {
        return new WrappedAmount(context, variableTrack, value);
    }
}
