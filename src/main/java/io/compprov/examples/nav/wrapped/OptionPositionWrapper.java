package io.compprov.examples.nav.wrapped;

import io.compprov.core.ComputationContext;
import io.compprov.core.variable.VariableTrack;
import io.compprov.core.variable.VariableWrapper;
import io.compprov.core.variable.WrappedVariable;
import io.compprov.examples.nav.model.OptionPosition;

/**
 * {@link VariableWrapper} factory that turns a plain {@link OptionPosition} into a
 * provenance-tracked {@link WrappedOptionPosition}. Registered with a
 * {@link io.compprov.core.ComputationEnvironment} via
 * {@code environment.registerWrapper(OptionPosition.class, new OptionPositionWrapper())},
 * typically from an {@link io.compprov.core.EnvironmentCustomizer} such as
 * {@link io.compprov.plugin.example.nav.NavEnvironmentCustomizer}.
 */
public class OptionPositionWrapper implements VariableWrapper<OptionPosition> {
    @Override
    public WrappedVariable wrap(ComputationContext context, VariableTrack variableTrack, OptionPosition value) {
        return new WrappedOptionPosition(context, variableTrack, value);
    }
}
