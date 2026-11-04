package io.compprov.examples.defects4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Data modelled on Defects4J Math-69 ({@code PearsonsCorrelationTest.testPValueNearZero}): x = i,
 * y = i + 1/(i + 1). The test uses 120 rows; 14 is where its comment says the buggy p-value first
 * vanishes, which keeps the graph small.
 */
public class Math69PearsonPValueDataProvider {

    private static final int DIMENSION = 14;

    public List<BigDecimal> fetchX() {
        final List<BigDecimal> x = new ArrayList<>(DIMENSION);
        for (int i = 0; i < DIMENSION; i++) {
            x.add(BigDecimal.valueOf(i));
        }
        return x;
    }

    public List<BigDecimal> fetchY() {
        final List<BigDecimal> y = new ArrayList<>(DIMENSION);
        for (int i = 0; i < DIMENSION; i++) {
            y.add(BigDecimal.valueOf(i + 1 / ((double) i + 1)));
        }
        return y;
    }
}
