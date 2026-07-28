package io.compprov.examples.nav;

import io.compprov.core.ComputationEnvironment;
import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.wrappers.WrappedBigDecimal;
import io.compprov.examples.nav.model.OptionPosition;
import io.compprov.examples.nav.model.OptionType;
import io.compprov.examples.nav.wrapped.WrappedAmount;
import io.compprov.examples.nav.wrapped.WrappedOptionPosition;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static io.compprov.core.meta.Descriptor.descriptor;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Calculates the expiry payout for a portfolio of N daily ETH/USDC vanilla options.
 *
 * <p>Payout formulas (per-position):
 * <pre>
 *   CALL:  max(spot − strike, 0) × size
 *   PUT:   max(strike − spot, 0) × size
 * </pre>
 *
 * <p>Every arithmetic step — the intrinsic value per ETH, the OTM floor clamp, and
 * the notional multiplication — is recorded in the Calculation Provenance Graph so the
 * full derivation of each position's contribution can be audited or replayed.
 */
public class PayoutCalculator {

    @Test
    public void calculate() {

        final var ctx = new NavComputationContext("ETH/USDC daily options payout");

        PayoutDataProvider dp = new PayoutDataProvider();

        // === Shared constants ===
        var mc   = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        var zero = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Zero (OTM floor)"));
        var spot = ctx.wrap(dp.fetchSpotPrice(), descriptor("ETH/USDC spot (2026-06-30)"));

        // === Per-position payout: max(intrinsic, 0) × size ===
        final var generatedPositions = dp.generatePositions();
        final var positions = new ArrayList<WrappedOptionPosition>();
        for (int i = 0; i < generatedPositions.size(); i++) {
            positions.add(ctx.wrap(generatedPositions.get(i), descriptor("Position [%d]".formatted(i))));
        }

        List<WrappedAmount> payouts = new ArrayList<>(positions.size());
        for (int i = 0; i < positions.size(); i++) {
            final var position = positions.get(i);
            final var payout = position.payout(spot, descriptor("Payout [%d]".formatted(i)));
            payouts.add(payout);
        }

        // === Sum all individual payouts ===
        var totalPayout = payouts.get(0)
                .addBulk(payouts.subList(1, payouts.size()), descriptor("Total payout in USDC"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
