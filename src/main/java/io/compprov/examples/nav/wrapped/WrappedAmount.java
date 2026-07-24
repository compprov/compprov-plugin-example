package io.compprov.examples.nav.wrapped;

import io.compprov.core.ComputationContext;
import io.compprov.core.meta.Descriptor;
import io.compprov.core.operation.WrappedArgument;
import io.compprov.core.variable.AbstractWrappedVariable;
import io.compprov.core.variable.VariableTrack;
import io.compprov.core.wrappers.WrappedBigDecimal;
import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.Rate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static io.compprov.core.meta.Meta.formula;

/**
 * Provenance-tracked wrapper around {@link Amount}, exposing {@code add}, {@code convert}, and
 * variadic {@code addBulk} as CPG-recorded operations. Each operation is declared once as a
 * {@link Descriptor} constant (name plus a human-readable {@code formula} used in audit logs)
 * mapped to the {@link Amount} logic it delegates to; the public methods below record the
 * operation via {@link #execute} and return the tracked result.
 * <p>
 * See the compprov-core README section "Extending with custom type wrappers" for the full
 * three-step pattern this class is part of: a {@code Wrapped<Type>} (this class), a
 * {@link io.compprov.core.variable.VariableWrapper} factory ({@link AmountWrapper}), and
 * registration with a {@link io.compprov.core.ComputationEnvironment}.
 */
public class WrappedAmount extends AbstractWrappedVariable<Amount> {
    private static final Descriptor OP_ADD = Descriptor.descriptor("add", formula("a+b"));
    private static final Descriptor OP_SUBTRACT = Descriptor.descriptor("subtract", formula("a-b"));
    private static final Descriptor OP_SCALE = Descriptor.descriptor("scale", formula("a*f"));
    private static final Descriptor OP_ADD_BULK = Descriptor.descriptor("addBulk", formula("a+b0+...+bn"));
    private static final Descriptor OP_CONVERT = Descriptor.descriptor("convert", formula("convert(a,r)"));

    private static final Map<Descriptor, Function<List<Object>, Object>> functionsMap;

    static {
        Map<Descriptor, Function<List<Object>, Object>> functions = new HashMap<>();

        functions.put(OP_ADD, (arguments) -> {
            Amount a = (Amount) arguments.get(0);
            Amount b = (Amount) arguments.get(1);
            return a.add(b);
        });

        functions.put(OP_SUBTRACT, (arguments) -> {
            Amount a = (Amount) arguments.get(0);
            Amount b = (Amount) arguments.get(1);
            return a.subtract(b);
        });

        functions.put(OP_SCALE, (arguments) -> {
            Amount a = (Amount) arguments.get(0);
            BigDecimal f = (BigDecimal) arguments.get(1);
            return a.scale(f);
        });

        functions.put(OP_CONVERT, (arguments) -> {
            Amount a = (Amount) arguments.get(0);
            Rate r = (Rate) arguments.get(1);
            return a.convert(r);
        });

        functions.put(OP_ADD_BULK, (arguments) -> {
            Amount result = (Amount) arguments.get(0);
            for (int i = 1; i < arguments.size(); i++) {
                result = result.add((Amount) arguments.get(i));
            }
            return result;
        });

        functionsMap = Collections.unmodifiableMap(functions);
    }

    public WrappedAmount(ComputationContext context, VariableTrack variableTrack, Amount value) {
        super(context, variableTrack, value);
    }

    /**
     * @param operationDescriptor one of {@code OP_ADD}, {@code OP_ADD_BULK}, or {@code OP_CONVERT}
     * @return the lambda that performs the given operation, or {@code null} if unrecognized
     */
    @Override
    public Function<List<Object>, Object> getFunction(Descriptor operationDescriptor) {
        return functionsMap.get(operationDescriptor);
    }

    public WrappedAmount add(WrappedAmount val, Descriptor resultDescriptor) {
        Objects.requireNonNull(val, "val");
        return (WrappedAmount) execute(
                OP_ADD,
                "a", this,
                "b", val,
                resultDescriptor);
    }

    public WrappedAmount subtract(WrappedAmount val, Descriptor resultDescriptor) {
        Objects.requireNonNull(val, "val");
        return (WrappedAmount) execute(
                OP_SUBTRACT,
                "a", this,
                "b", val,
                resultDescriptor);
    }

    public WrappedAmount scale(WrappedBigDecimal factor, Descriptor resultDescriptor) {
        Objects.requireNonNull(factor, "factor");
        return (WrappedAmount) execute(
                OP_SCALE,
                "a", this,
                "f", factor,
                resultDescriptor);
    }

    /**
     * Records a {@code convert} operation in the CPG and returns the tracked result in the
     * other currency named by {@code rate}, per {@link Amount#convert(io.compprov.examples.nav.model.Rate)}.
     *
     * @param rate             the exchange rate to apply
     * @param resultDescriptor descriptor for the result variable, or {@code null} to auto-name it
     * @return a new {@code WrappedAmount} tracking the converted value
     */
    public WrappedAmount convert(WrappedRate rate, Descriptor resultDescriptor) {
        Objects.requireNonNull(rate, "rate");
        return (WrappedAmount) execute(
                OP_CONVERT,
                "a", this,
                "r", rate,
                resultDescriptor);
    }

    /**
     * Records a single {@code addBulk} operation in the CPG that sums this amount with an
     * arbitrary number of other amounts, avoiding the extra recorded operations a chain of
     * individual {@link #add} calls would add to the graph.
     *
     * @param values           the amounts to add to this one; all must share this amount's currency
     * @param resultDescriptor descriptor for the result variable, or {@code null} to auto-name it
     * @return a new {@code WrappedAmount} tracking the sum of this amount and every value in {@code values}
     */
    public WrappedAmount addBulk(List<WrappedAmount> values, Descriptor resultDescriptor) {
        Objects.requireNonNull(values, "val");
        final var arguments = new ArrayList<WrappedArgument>(values.size() + 1);
        arguments.add(new WrappedArgument("a", this));
        for (int i = 0; i < values.size(); i++) {
            arguments.add(new WrappedArgument("b" + i, values.get(i)));
        }
        return (WrappedAmount) execute(
                OP_ADD_BULK,
                arguments,
                resultDescriptor);
    }
}
