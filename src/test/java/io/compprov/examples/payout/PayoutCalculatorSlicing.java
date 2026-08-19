package io.compprov.examples.payout;

import io.compprov.core.wrappers.WrappedBigDecimal;
import io.compprov.examples.TestComputationContext;
import io.compprov.examples.nav.model.OptionPosition;
import io.compprov.examples.nav.model.OptionType;
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
public class PayoutCalculatorSlicing {

    @Test
    public void calculate() {

        final var ctx = new TestComputationContext("ETH/USDC daily options payout");

        PayoutDataProvider dp = new PayoutDataProvider();

        // === Shared constants ===
        var mc = ctx.wrapMathContext(MathContext.DECIMAL64, descriptor("Computation precision (DECIMAL64)"));
        var zero = ctx.wrapBigDecimal(BigDecimal.ZERO, descriptor("Zero (OTM floor)"));
        var spot = ctx.wrapBigDecimal(dp.fetchSpotPrice().rate(), descriptor("ETH/USDC spot (2026-06-30)"));

        //slicing
        var intrinsicScale = ctx.wrapMathContext(new MathContext(2, RoundingMode.DOWN), descriptor("Intrinsic scale"));

        // === Per-position payout: max(intrinsic, 0) × size ===
        List<OptionPosition> positions = dp.generatePositions();
        List<WrappedBigDecimal> payouts = new ArrayList<>(positions.size());

        for (int i = 0; i < positions.size(); i++) {
            OptionPosition pos = positions.get(i);
            String lbl = "[%d] %s K=%s".formatted(i, pos.type(), pos.strike().rate().toPlainString());

            var strike = ctx.wrapBigDecimal(pos.strike().rate(), descriptor("Strike " + lbl));
            var size = ctx.wrapBigDecimal(pos.size().getAmount(), descriptor("Size " + lbl));

            // Signed intrinsic value per ETH (may be negative for OTM positions)
            var diff = (pos.type() == OptionType.CALL)
                    ? spot.subtract(strike, mc, descriptor("Spot - Strike " + lbl))
                    : strike.subtract(spot, mc, descriptor("Strike - Spot " + lbl));

            // Clamp to zero: OTM options expire worthless
            var intrinsic = diff.max(zero, descriptor("Intrinsic/ETH " + lbl));

            // Payout = intrinsic × notional size
            var payout = intrinsic.multiply(size, mc, descriptor("Payout " + lbl));

            //slicing
            if (i > 0) {
                payout = payout.setScale(intrinsicScale, descriptor(""));
            }
            payouts.add(payout);
        }

        // === Sum all individual payouts ===
        var totalPayout = payouts.get(0)
                .addBulk(payouts.subList(1, payouts.size()), mc, descriptor("Total payout in USDC"));

        final var snapshot = ctx.snapshot();
        final var provenanceGraph = ctx.getEnvironment().toJson(snapshot);
        System.out.println(provenanceGraph);
    }
}
