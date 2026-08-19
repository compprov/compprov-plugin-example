package io.compprov.examples.depreciation;

import java.math.BigDecimal;

/**
 * Fixed, reproducible inputs for a straight-line depreciation schedule on a single fixed asset
 * (manufacturing equipment) over its 5-year useful life.
 */
public class AssetDepreciationDataProvider {

    public static final int USEFUL_LIFE_YEARS = 5;

    public BigDecimal fetchAssetCost() {
        return new BigDecimal("85000.00");
    }

    public BigDecimal fetchSalvageValue() {
        return new BigDecimal("10000.00");
    }

    public BigDecimal fetchUsefulLifeYears() {
        return new BigDecimal(USEFUL_LIFE_YEARS);
    }
}
