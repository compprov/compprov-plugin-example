package io.compprov.examples.defects4j;

import java.math.BigDecimal;
import java.util.List;

/**
 * Test data from Defects4J Math-75 ({@code FrequencyTest.testPcts}): 8 observations of the
 * values 1, 2 and 3, tallied as a frequency table; the queried value is 3.
 */
public class Math75FrequencyPctDataProvider {

    public List<BigDecimal> fetchDistinctValues() {
        return List.of(new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"));
    }

    public List<BigDecimal> fetchCounts() {
        return List.of(new BigDecimal("2"), new BigDecimal("2"), new BigDecimal("4"));
    }

    public int fetchQueriedValueIndex() {
        return 2;
    }
}
