## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ {
"key" : "rounding",
"value" : "DOWN (Amount always truncates to the currency's decimal precision; balance-safety invariant)"
}, {
"key" : "currencyPrecisions",
"value" : {
"BTC" : 8,
"WBTC" : 8,
"ETH" : 18,
"USDC" : 6,
"USDT" : 6,
"USD" : 2,
"EUR" : 2,
"WSTETH" : 18
}
} ],
"name" : "Forward contract: EUR/USD 90-day valuation"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Computation precision (DECIMAL64)"
},
"id" : "i_1",
"valueClass" : "java.math.MathContext"
},
"value" : {
"precision" : 16,
"roundingMode" : "HALF_EVEN"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Domestic (USD) interest rate, annual"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0525"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Foreign (EUR) interest rate, annual"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0375"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Day-count fraction (90/360, ACT/360)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.25"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "One"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Domestic (USD) interest rate, 90-day period"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.013125"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Foreign (EUR) interest rate, 90-day period"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.009375"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Domestic growth factor (1 + domestic rate)"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.013125"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Foreign growth factor (1 + foreign rate)"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.009375"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest rate parity ratio (forward/spot)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.003715170278638"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Notional amount (EUR)"
},
"id" : "i_11",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "2500000.00",
"currency" : "EUR"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Spot rate (EUR/USD)"
},
"id" : "i_12",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "EUR",
"to" : "USD",
"rate" : "1.0850"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Notional at spot rate (USD)"
},
"id" : "o_13",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "2712500.00",
"currency" : "USD"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Settlement amount at forward rate (USD)"
},
"id" : "o_14",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "2722577.39",
"currency" : "USD"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "basis",
"value" : "interest-rate-parity forward premium at valuation date"
} ],
"name" : "Mark-to-market value vs. spot (USD)"
},
"id" : "o_15",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "10077.39",
"currency" : "USD"
}
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_6"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_7"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_5"
}, {
"key" : "b",
"value" : "o_6"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_8"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_5"
}, {
"key" : "b",
"value" : "o_7"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_9"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
}, {
"key" : "b",
"value" : "o_9"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_10"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_11"
}, {
"key" : "r",
"value" : "i_12"
} ],
"resultId" : "o_13"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a*f"
} ],
"name" : "scale"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "f",
"value" : "o_10"
} ],
"resultId" : "o_14"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a-b"
} ],
"name" : "subtract"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b",
"value" : "o_13"
} ],
"resultId" : "o_15"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_11, i_12]
- **Leaf variable IDs**: [o_15]
- **Variable IDs consumed as an argument by more than one operation**: [i_4, i_5, o_13]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
