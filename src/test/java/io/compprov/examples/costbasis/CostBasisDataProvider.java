package io.compprov.examples.costbasis;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a weighted-average cost basis calculation across three stock
 * purchase lots, followed by a partial sale.
 */
public class CostBasisDataProvider {

    public BigDecimal fetchLot1Shares() {
        return new BigDecimal("45.678912");
    }

    public BigDecimal fetchLot1PricePerShare() {
        return new BigDecimal("210.50");
    }

    public BigDecimal fetchLot2Shares() {
        return new BigDecimal("67.234156");
    }

    public BigDecimal fetchLot2PricePerShare() {
        return new BigDecimal("340.75");
    }

    public BigDecimal fetchLot3Shares() {
        return new BigDecimal("22.891347");
    }

    public BigDecimal fetchLot3PricePerShare() {
        return new BigDecimal("1850.00");
    }

    public BigDecimal fetchSharesSold() {
        return new BigDecimal("60");
    }

    public BigDecimal fetchSalePricePerShare() {
        return new BigDecimal("650.00");
    }
}
