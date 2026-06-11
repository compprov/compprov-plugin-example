package io.compprov.tools;

import io.compprov.core.Snapshot;
import io.compprov.tools.commutativity.CommutativityRegistry;
import io.compprov.tools.difference.BigDecimalDifferenceCalculator;
import io.compprov.tools.difference.BigIntegerDifferenceCalculator;
import io.compprov.tools.difference.DifferenceRegistry;
import io.compprov.tools.difference.DoubleDifferenceCalculator;
import io.compprov.tools.difference.FloatDifferenceCalculator;
import io.compprov.tools.difference.IntegerDifferenceCalculator;
import io.compprov.tools.difference.LongDifferenceCalculator;
import io.compprov.tools.difference.MathContextDifferenceCalculator;
import io.compprov.tools.difference.NormalizedDifferenceCalculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Feel free to override this class and update logic. Pointer moves through snapshot 1 and tries to find best fit in snapshot 2.
 */
public class SnapshotComaparator {

    protected final CommutativityRegistry commutativityRegistry;
    protected final DifferenceRegistry differenceRegistry;
    protected final SnapshotNavigator snapshotNavigator1;
    protected final SnapshotNavigator snapshotNavigator2;
    protected Snapshot.Variable pointer1;
    protected Snapshot.Variable pointer2;

    //построить очередь из переменных и напротив оссациировать переменные с переменными
    //потом можно сопоставлять сколько влезет
    //нужно еще хранить уровень вложенности что бы понимать мне искать в этом уровне или углубиться И углубиться от какой переменной
    protected LinkedHashMap<Snapshot.Variable, Snapshot.Variable> variablesMapping;

    public enum CounterpartVariables {
        ALL,
        COMPATIBLE,
        BEST_FIT
    }

    public SnapshotComaparator(SnapshotNavigator snapshotNavigator1, SnapshotNavigator snapshotNavigator2,
                               DifferenceRegistry differenceRegistry,
                               CommutativityRegistry commutativityRegistry) {
        this.snapshotNavigator1 = snapshotNavigator1;
        this.snapshotNavigator2 = snapshotNavigator2;
        this.commutativityRegistry = commutativityRegistry;
        this.differenceRegistry = differenceRegistry;
    }

    public List<Snapshot.Variable> leaves() {
        return snapshotNavigator1.leaves();
    }

    public Result start(Snapshot.Variable leaf) {
        if (pointer1 != null) {
            throw new IllegalStateException("Already started");
        }
        pointer1 = leaf;
        final var leafClass = leaf.track().getValueClass();
        final var leafProducer = extractProducerString(snapshotNavigator1.producedBy(leaf.track().getId()));
        final var leafName = extractProducerString(snapshotNavigator1.producedBy(leaf.track().getDescriptor().getName()));

        var leaves2 = snapshotNavigator2.leaves()
                .stream()
                .filter(l -> l.track().getValueClass().equals(leafClass))
                .filter(l -> extractProducerString(snapshotNavigator2.producedBy(l.track().getId())).equals(leafProducer))
                .toList();
        if (leaves2.isEmpty()) {
            return new Result(Code.VARIABLE_NOT_FOUND, leaf);
        }
        if (leaves2.size() == 1) {
            pointer2 = leaves2.get(0);
        } else {
            leaves2 = leaves2.stream().filter(l -> l.track().getDescriptor().getName().equals(leafName)).toList();
            if (leaves2.size() == 1) {
                pointer2 = leaves2.get(0);
            } else {
                return new Result(Code.AMBIGUOUS_VARIABLE, leaf);
            }
        }

        final var diff = differenceRegistry.normalizedDiff(pointer1, pointer2);
        return new Result(Code.FOUND, diff);
    }

    protected void skip() {
        //тут надо придумать как перескакивать и еще надол придумать как хранить состояние тк там еще есть операции или операции не сравнивать или сравнивать но отдельно
    }

    protected void setCounterpartPointer(Snapshot.Variable pointer2) {
        this.pointer2 = pointer2;
    }

    public Snapshot.Variable getPointer1() {
        return pointer1;
    }

    public Snapshot.Variable getPointer2() {
        return pointer2;
    }

    protected List<Snapshot.Variable> counterpartVariables(CounterpartVariables filter) {

        //тут надо понимать я иду вглуюь от пременной или я стою в операции и какую то переменную

        //no filter
        var counterpartVariables = (pointer2 == null) ? snapshotNavigator2.leaves() : snapshotNavigator2.dependsOn(pointer2.track().getId());
        if (filter == CounterpartVariables.ALL) {
            return counterpartVariables;
        }

        //compatible
        final var underlyingClass = pointer1.track().getValueClass();
        final var underlyingProducer = extractProducerString(snapshotNavigator1.producedBy(pointer1.track().getId()));
        counterpartVariables = counterpartVariables
                .stream()
                .filter(l -> l.track().getValueClass().equals(underlyingClass))
                .filter(l -> extractProducerString(snapshotNavigator2.producedBy(l.track().getId())).equals(underlyingProducer))
                .toList();
        if (filter == CounterpartVariables.COMPATIBLE) {
            return counterpartVariables;
        }

        //best fit
        final var underlyingName = extractProducerString(snapshotNavigator1.producedBy(pointer1.track().getDescriptor().getName()));
        return counterpartVariables.stream().filter(l -> l.track().getDescriptor().getName().equals(underlyingName)).toList();
    }



    protected String extractProducerString(Optional<Snapshot.Operation> operationOptional) {
        if (operationOptional.isEmpty()) {
            return "";
        }
        return operationOptional.get().track().getDescriptor().getName() + "_" + operationOptional.get().track().getWrapperClass();
    }


    protected Snapshot.Variable findByName(String name, List<Snapshot.Variable> variables) {

    }

    public enum Code {
        FOUND,
        VARIABLE_NOT_FOUND,
        AMBIGUOUS_VARIABLE,

    }

    public record Result(Code code, Snapshot.Variable referenceVariable) {

    }

    //должен быть какой то перебор по типу - потом по структуре графа - потом по значениям. Если не совпало - проверка на коммутативность
    //или как вариант брать список кандидатов и обходить
    //текущий узел можно пропустить поэтому для обхода базового графа должна быть очередь
    //
    //функция  фикс
    //функция получить варианты
    //
    //1к1 компаратор сначала строит просто соответствие нод, если есть мисматч то дает ошибку
    //проверять по типу и [структоре]
    //
}
