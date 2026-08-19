package io.compprov.examples.manufacturing;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import org.junit.jupiter.api.Test;

import java.math.MathContext;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;

/**
 * Bill-of-materials rollup for a single assembly, extended to a full production run.
 *
 * <p>Material cost per unit = sum of (component unit cost × quantity-per-assembly) across five
 * components. Total unit cost = material cost + labor + overhead. Total run cost = total unit
 * cost × production run quantity.
 */
public class ManufacturingBomCalculator {

    @Test
    public void calculate() {

        final var ctx = new DefaultComputationContext(
                DefaultComputationEnvironment.create(), new DataContext(descriptor("Manufacturing: BOM rollup and production run cost")));
        ManufacturingBomDataProvider dp = new ManufacturingBomDataProvider();

        final var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));

        // === Component costs (unit cost x quantity-per-assembly) ===
        final var circuitBoardCost = ctx.wrapBigDecimal(dp.fetchCircuitBoardUnitCost(), descriptor("Circuit board unit cost"))
                .multiply(ctx.wrapBigDecimal(dp.fetchCircuitBoardQtyPerAssembly(), descriptor("Circuit board qty/assembly")), mc, descriptor("Circuit board cost per assembly"));

        final var enclosureCost = ctx.wrapBigDecimal(dp.fetchEnclosureUnitCost(), descriptor("Enclosure unit cost"))
                .multiply(ctx.wrapBigDecimal(dp.fetchEnclosureQtyPerAssembly(), descriptor("Enclosure qty/assembly")), mc, descriptor("Enclosure cost per assembly"));

        final var connectorCost = ctx.wrapBigDecimal(dp.fetchConnectorUnitCost(), descriptor("Connector unit cost"))
                .multiply(ctx.wrapBigDecimal(dp.fetchConnectorQtyPerAssembly(), descriptor("Connector qty/assembly")), mc, descriptor("Connector cost per assembly"));

        final var fastenerCost = ctx.wrapBigDecimal(dp.fetchFastenerUnitCost(), descriptor("Fastener unit cost"))
                .multiply(ctx.wrapBigDecimal(dp.fetchFastenerQtyPerAssembly(), descriptor("Fastener qty/assembly")), mc, descriptor("Fastener cost per assembly"));

        final var cableCost = ctx.wrapBigDecimal(dp.fetchCableUnitCost(), descriptor("Cable unit cost"))
                .multiply(ctx.wrapBigDecimal(dp.fetchCableQtyPerAssembly(), descriptor("Cable qty/assembly")), mc, descriptor("Cable cost per assembly"));

        final var materialCostPerUnit = circuitBoardCost.addBulk(
                List.of(enclosureCost, connectorCost, fastenerCost, cableCost), mc, descriptor("Material cost per unit"));

        // === Labor, overhead, total unit cost ===
        final var laborCostPerUnit = ctx.wrapBigDecimal(dp.fetchLaborCostPerUnit(), descriptor("Labor cost per unit"));
        final var overheadCostPerUnit = ctx.wrapBigDecimal(dp.fetchOverheadCostPerUnit(), descriptor("Overhead cost per unit"));

        final var totalUnitCost = materialCostPerUnit.addBulk(
                List.of(laborCostPerUnit, overheadCostPerUnit), mc, descriptor("Total unit cost"));

        // === Production run ===
        final var runQuantity = ctx.wrapBigDecimal(dp.fetchProductionRunQuantity(), descriptor("Production run quantity"));
        final var totalRunCost = totalUnitCost.multiply(runQuantity, mc, descriptor("Total run cost"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
