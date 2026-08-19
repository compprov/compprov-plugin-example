package io.compprov.examples.statistics;

import java.math.BigDecimal;
import java.util.List;

/**
 * Fixed, reproducible inputs: 6 titration end-point volumes (mL) from repeated trials of the
 * same titration, used to compute descriptive statistics.
 */
public class DescriptiveStatisticsDataProvider {

    public List<BigDecimal> fetchSampleValues() {
        return List.of(
                new BigDecimal("12.5"),
                new BigDecimal("15.2"),
                new BigDecimal("11.8"),
                new BigDecimal("14.1"),
                new BigDecimal("13.6"),
                new BigDecimal("12.9"));
    }
}
