package io.compprov.tools.commutativity;

import io.compprov.core.Snapshot;
import io.compprov.core.meta.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CommutativityRegistry {
    private final Map<String, Commutativity> registry = new ConcurrentHashMap<>();

    private CommutativityRegistry() {
    }

    public static CommutativityRegistry emptyRegistry() {
        return new CommutativityRegistry();
    }

    public static CommutativityRegistry defaultRegistry() {
        final var registry = new CommutativityRegistry();
        registry.register("io.compprov.core.wrappers.WrappedBigDecimal", "add", new CommutativeExceptSet(Set.of("mc")));
        registry.register("io.compprov.core.wrappers.WrappedBigDecimal", "multiply", new CommutativeExceptSet(Set.of("mc")));
        registry.register("io.compprov.core.wrappers.WrappedBigDecimal", "min", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigDecimal", "max", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigDecimal", "addBulk", new CommutativeExceptSet(Set.of("mc")));
        registry.register("io.compprov.core.wrappers.WrappedBigDecimal", "multiplyBulk", new CommutativeExceptSet(Set.of("mc")));
        registry.register("io.compprov.core.wrappers.WrappedBigDecimal", "minBulk", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigDecimal", "maxBulk", new CommutativeExceptSet(Collections.emptySet()));

        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "add", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "multiply", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "min", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "max", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "addBulk", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "multiplyBulk", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "minBulk", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "maxBulk", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "gcd", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "and", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "or", new CommutativeExceptSet(Collections.emptySet()));
        registry.register("io.compprov.core.wrappers.WrappedBigInteger", "xor", new CommutativeExceptSet(Collections.emptySet()));
        return registry;
    }

    public void register(String wrapperClass, String operationName, Commutativity commutativity) {
        registry.put(buildKey(wrapperClass, operationName), commutativity);
    }

    public List<Pair> getCommutativeArguments(Snapshot.Operation operation) {
        final var rule = registry.get(buildKey(operation));
        if (rule == null) {
            return Collections.emptyList();
        }
        return rule.getCommutativeArguments(operation);
    }

    private String buildKey(Snapshot.Operation operation) {
        return buildKey(operation.track().getWrapperClass(), operation.track().getDescriptor().getName());
    }

    private String buildKey(String wrapperClass, String operationName) {
        return wrapperClass + "::" + operationName;
    }

}
