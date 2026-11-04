package io.compprov.examples.defects4j;

/**
 * Data from Defects4J Math-93 ({@code MathUtilsTest.testFactorial}): 17!, the first factorial the
 * log-based version gets wrong.
 */
public class Math93FactorialDataProvider {

    public int fetchN() {
        return 17;
    }
}
