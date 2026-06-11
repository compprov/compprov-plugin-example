package io.compprov.tools.difference;

import io.compprov.core.Snapshot;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DifferenceRegistry {
    private final Map<Class, NormalizedDifferenceCalculator> differenceCalculatorMap = new ConcurrentHashMap<>();

    private DifferenceRegistry() {
    }

    public static DifferenceRegistry emptyRegistry() {
        return new DifferenceRegistry();
    }

    public static DifferenceRegistry defaultRegistry() {
        final var registry = new DifferenceRegistry();
        registry.register(Integer.class, new IntegerDifferenceCalculator());
        registry.register(Long.class, new LongDifferenceCalculator());
        registry.register(Double.class, new DoubleDifferenceCalculator());
        registry.register(Float.class, new FloatDifferenceCalculator());
        registry.register(BigInteger.class, new BigIntegerDifferenceCalculator());
        registry.register(BigDecimal.class, new BigDecimalDifferenceCalculator());
        registry.register(MathContext.class, new MathContextDifferenceCalculator());
        return registry;
    }

    public void register(Class clazz, NormalizedDifferenceCalculator calculator) {
        this.differenceCalculatorMap.put(clazz, calculator);
    }

    public double normalizedDiff(Snapshot.Variable v1, Snapshot.Variable v2) {
        if (v1.value().getClass() != v2.value().getClass()) {
            throw new IllegalStateException("variable class mismatch: " + v1.value().getClass() + "vs " + v2.value().getClass());
        }
        final var diffCalculator = differenceCalculatorMap.get(v1.value().getClass());
        if (diffCalculator == null) {
            throw new IllegalStateException("Unable to find difference calculator for class " + v1.value().getClass());
        }
        return diffCalculator.normalizedDifference(v1.value(), v2.value());
    }
}
