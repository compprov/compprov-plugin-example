package io.compprov.tools.commutativity;

import io.compprov.core.Snapshot;
import io.compprov.core.meta.Pair;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommutativeExceptSet implements Commutativity {

    private final Set<String> nonCommutativeArgumentNames;

    public CommutativeExceptSet(Set<String> nonCommutativeArgumentNames) {
        this.nonCommutativeArgumentNames = nonCommutativeArgumentNames;
    }

    @Override
    public List<Pair> getCommutativeArguments(Snapshot.Operation operation) {
        return operation.arguments()
                .stream()
                .filter(pair -> !nonCommutativeArgumentNames.contains(pair.key()))
                .collect(Collectors.toList());
    }
}
