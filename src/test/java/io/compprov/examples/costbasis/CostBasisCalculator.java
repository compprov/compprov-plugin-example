package io.compprov.examples.costbasis;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Weighted-average cost basis across three stock purchase lots, then realized gain/loss on
 * selling a partial position.
 *
 * <p>{@code weightedAvgCostPerShare = totalCost / totalShares}; realized gain/loss = sharesSold ×
 * (salePrice − weightedAvgCostPerShare).
 */
public class CostBasisCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Cost basis: weighted-average across 3 lots, partial sale")));
        CostBasisDataProvider dp = new CostBasisDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Lot 1 ===
        final var lot1Shares = ctx.wrapBigDecimal(dp.fetchLot1Shares(), descriptor("Lot 1 shares"));
        final var lot1Price = ctx.wrapBigDecimal(dp.fetchLot1PricePerShare(), descriptor("Lot 1 price per share"));
        final var lot1Cost = lot1Shares.multiply(lot1Price, mc, descriptor("Lot 1 cost"));

        // === Lot 2 ===
        final var lot2Shares = ctx.wrapBigDecimal(dp.fetchLot2Shares(), descriptor("Lot 2 shares"));
        final var lot2Price = ctx.wrapBigDecimal(dp.fetchLot2PricePerShare(), descriptor("Lot 2 price per share"));
        final var lot2Cost = lot2Shares.multiply(lot2Price, mc, descriptor("Lot 2 cost"));

        // === Lot 3 ===
        final var lot3Shares = ctx.wrapBigDecimal(dp.fetchLot3Shares(), descriptor("Lot 3 shares"));
        final var lot3Price = ctx.wrapBigDecimal(dp.fetchLot3PricePerShare(), descriptor("Lot 3 price per share"));
        final var lot3Cost = lot3Shares.multiply(lot3Price, mc, descriptor("Lot 3 cost"));

        // === Weighted-average cost basis ===
        final var totalShares = lot1Shares.addBulk(List.of(lot2Shares, lot3Shares), mc, descriptor("Total shares held"));
        final var totalCost = lot1Cost.addBulk(List.of(lot2Cost, lot3Cost), mc, descriptor("Total cost"));
        final var weightedAvgCostPerShare = totalCost.divide(totalShares, mc, descriptor("Weighted-average cost per share"));

        // === Partial sale ===
        final var sharesSold = ctx.wrapBigDecimal(dp.fetchSharesSold(), descriptor("Shares sold"));
        final var salePrice = ctx.wrapBigDecimal(dp.fetchSalePricePerShare(), descriptor("Sale price per share"));
        final var proceeds = sharesSold.multiply(salePrice, mc, descriptor("Sale proceeds"));
        final var costOfSharesSold = sharesSold.multiply(weightedAvgCostPerShare, mc, descriptor("Cost basis of shares sold"));

        // === Realized gain/loss ===
        final var realizedGainLoss = proceeds.subtract(costOfSharesSold, mc, descriptor("Realized gain/loss"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
