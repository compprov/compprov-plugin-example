package io.compprov.examples.nav.wrapped;

import io.compprov.core.ComputationContext;
import io.compprov.core.meta.Descriptor;
import io.compprov.core.variable.AbstractWrappedVariable;
import io.compprov.core.variable.VariableTrack;
import io.compprov.examples.nav.model.OptionPosition;
import io.compprov.examples.nav.model.Rate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static io.compprov.core.meta.Meta.formula;

/**
 * Provenance-tracked wrapper around {@link OptionPosition}, exposing {@code payout} as a
 * CPG-recorded operation. Follows the same pattern as {@link WrappedAmount}: the operation is
 * declared once as a {@link Descriptor} constant mapped to the {@link OptionPosition} logic it
 * delegates to, and the public {@link #payout} method records it via {@link #execute} and
 * returns the tracked result.
 * <p>
 * See the compprov-core README section "Extending with custom type wrappers" for the full
 * three-step pattern this class is part of: a {@code Wrapped<Type>} (this class), a
 * {@link io.compprov.core.variable.VariableWrapper} factory ({@link OptionPositionWrapper}), and
 * registration with a {@link io.compprov.core.ComputationEnvironment}.
 */
public class WrappedOptionPosition extends AbstractWrappedVariable<OptionPosition> {
    private static final Descriptor OP_PAYOUT = Descriptor.descriptor("payout", formula("pos.payout(price)"));
    private static final Map<Descriptor, Function<List<Object>, Object>> functionsMap;

    static {
        Map<Descriptor, Function<List<Object>, Object>> functions = new HashMap<>();

        functions.put(OP_PAYOUT, (arguments) -> {
            final var pos = (OptionPosition) arguments.get(0);
            final var price = (Rate) arguments.get(1);
            return pos.payout(price);
        });

        functionsMap = Collections.unmodifiableMap(functions);
    }

    public WrappedOptionPosition(ComputationContext context, VariableTrack variableTrack, OptionPosition value) {
        super(context, variableTrack, value);
    }

    /**
     * @param operationDescriptor the only recognized value is {@code OP_PAYOUT}
     * @return the lambda that performs the given operation, or {@code null} if unrecognized
     */
    @Override
    public Function<List<Object>, Object> getFunction(Descriptor operationDescriptor) {
        return functionsMap.get(operationDescriptor);
    }

    /**
     * Records a {@code payout} operation in the CPG and returns the tracked result of
     * {@link OptionPosition#payout(Rate)} at the given settlement price.
     *
     * @param price            the settlement/spot rate to evaluate the position's payout at
     * @param resultDescriptor descriptor for the result variable, or {@code null} to auto-name it
     * @return a new {@code WrappedAmount} tracking the position's payout
     */
    public WrappedAmount payout(WrappedRate price, Descriptor resultDescriptor) {
        Objects.requireNonNull(price, "price");
        return (WrappedAmount) execute(
                OP_PAYOUT,
                "pos", this,
                "price", price,
                resultDescriptor);
    }
}
