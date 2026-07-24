package io.compprov.examples.nav.wrapped;

import io.compprov.core.ComputationContext;
import io.compprov.core.meta.Descriptor;
import io.compprov.core.variable.AbstractWrappedVariable;
import io.compprov.core.variable.VariableTrack;
import io.compprov.examples.nav.model.Rate;

import java.util.List;
import java.util.function.Function;

/**
 * Provenance-tracked wrapper around a {@link Rate}. Unlike {@link WrappedAmount}, a rate is
 * only ever consumed as an argument to another wrapped variable's operation (see
 * {@link WrappedAmount#convert(WrappedRate, Descriptor)}) and never itself the receiver of a
 * tracked operation, so it declares no operations of its own.
 */
public class WrappedRate extends AbstractWrappedVariable<Rate> {
    public WrappedRate(ComputationContext context, VariableTrack variableTrack, Rate value) {
        super(context, variableTrack, value);
    }

    /**
     * @throws RuntimeException always; {@code WrappedRate} exposes no operations of its own
     */
    @Override
    public Function<List<Object>, Object> getFunction(Descriptor operationDescriptor) {
        throw new RuntimeException("No functions available");
    }
}

