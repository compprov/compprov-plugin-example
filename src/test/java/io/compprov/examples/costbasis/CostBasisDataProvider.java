package io.compprov.examples.costbasis;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a weighted-average cost basis calculation across three stock
 * purchase lots, followed by a partial sale.
 */
public class CostBasisDataProvider {

    public BigDecimal fetchLot1Shares() {
        return new BigDecimal("100");
    }

    public BigDecimal fetchLot1PricePerShare() {
        return new BigDecimal("42.50");
    }

    public BigDecimal fetchLot2Shares() {
        return new BigDecimal("150");
    }

    public BigDecimal fetchLot2PricePerShare() {
        return new BigDecimal("38.25");
    }

    public BigDecimal fetchLot3Shares() {
        return new BigDecimal("75");
    }

    public BigDecimal fetchLot3PricePerShare() {
        return new BigDecimal("51.00");
    }

    public BigDecimal fetchSharesSold() {
        return new BigDecimal("120");
    }

    public BigDecimal fetchSalePricePerShare() {
        return new BigDecimal("47.75");
    }
}
