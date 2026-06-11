package io.compprov.tools.commutativity;

import io.compprov.core.Snapshot;
import io.compprov.core.meta.Pair;

import java.util.List;

@FunctionalInterface
public interface Commutativity {

    public List<Pair> getCommutativeArguments(Snapshot.Operation operation);
}
