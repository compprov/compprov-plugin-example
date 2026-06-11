package io.compprov.tools.commutativity;

import io.compprov.core.Snapshot;
import io.compprov.core.meta.Pair;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommutativeSet implements Commutativity {

    private final Set<String> commutativeArgumentNames;

    public CommutativeSet(Set<String> commutativeArgumentNames) {
        this.commutativeArgumentNames = commutativeArgumentNames;
    }

    @Override
    public List<Pair> getCommutativeArguments(Snapshot.Operation operation) {
        return operation.arguments()
                .stream()
                .filter(pair -> commutativeArgumentNames.contains(pair.key()))
                .collect(Collectors.toList());
    }
}
