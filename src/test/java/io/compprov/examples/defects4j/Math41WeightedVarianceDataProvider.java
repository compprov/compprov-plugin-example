package io.compprov.examples.defects4j;

import java.math.BigDecimal;
import java.util.List;

/**
 * Test data from Defects4J Math-41 ({@code UnivariateStatisticAbstractTest}): 22 observations
 * with weights, of which the segment {@code [begin, begin + length)} is evaluated.
 */
public class Math41WeightedVarianceDataProvider {

    public List<BigDecimal> fetchValues() {
        return decimals("12.5", "12.0", "11.8", "14.2", "14.9", "14.5", "21.0", "8.2", "10.3", "11.3",
                "14.1", "9.9", "12.2", "12.0", "12.1", "11.0", "19.8", "11.0", "10.0", "8.8",
                "9.0", "12.3");
    }

    public List<BigDecimal> fetchWeights() {
        return decimals("1.5", "0.8", "1.2", "0.4", "0.8", "1.8", "1.2", "1.1", "1.0", "0.7",
                "1.3", "0.6", "0.7", "1.3", "0.7", "1.0", "0.4", "0.1", "1.4", "0.9",
                "1.1", "0.3");
    }

    public int fetchSegmentBegin() {
        return 0;
    }

    public int fetchSegmentLength() {
        return 5;
    }

    private static List<BigDecimal> decimals(String... values) {
        return java.util.Arrays.stream(values).map(BigDecimal::new).toList();
    }
}
