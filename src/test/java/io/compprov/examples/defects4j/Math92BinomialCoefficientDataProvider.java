package io.compprov.examples.defects4j;

/**
 * Data modelled on Defects4J Math-92 ({@code MathUtilsTest.testBinomialCoefficientLarge}, which checks every
 * C(n, k) for n ≤ 200). The test's reported failure, C(48, 22), comes out right when the logarithms are summed
 * in DECIMAL64; the neighbouring C(48, 23) = 30957699535776 still rounds to the wrong integer, so it is used here.
 */
public class Math92BinomialCoefficientDataProvider {

    public int fetchN() {
        return 48;
    }

    public int fetchK() {
        return 23;
    }
}
