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
"name" : "DeFi portfolio profit calculation at 2026-06-30"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "date",
"value" : "2026-06-30"
}, {
"key" : "source",
"value" : "market"
} ],
"name" : "BTC/USDC rate"
},
"id" : "i_1",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "BTC",
"to" : "USDC",
"rate" : "109800"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "date",
"value" : "2026-06-30"
}, {
"key" : "source",
"value" : "market"
} ],
"name" : "ETH/USDC rate"
},
"id" : "i_2",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4650"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "date",
"value" : "2026-06-30"
}, {
"key" : "source",
"value" : "market"
} ],
"name" : "USDT/USDC rate"
},
"id" : "i_3",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "USDT",
"to" : "USDC",
"rate" : "0.9998"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "source",
"value" : "AAVE, fully collateralized 1:1"
} ],
"name" : "WBTC/BTC peg rate"
},
"id" : "i_4",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "WBTC",
"to" : "BTC",
"rate" : "1"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "date",
"value" : "2026-06-01"
}, {
"key" : "source",
"value" : "market"
} ],
"name" : "ETH/USDC rate"
},
"id" : "i_5",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4480"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "date",
"value" : "2026-06-03"
}, {
"key" : "source",
"value" : "market"
} ],
"name" : "ETH/USDC rate"
},
"id" : "i_6",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4420"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "date",
"value" : "2026-06-04"
}, {
"key" : "source",
"value" : "market"
} ],
"name" : "ETH/USDC rate"
},
"id" : "i_7",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4390"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "date",
"value" : "2026-06-08"
}, {
"key" : "source",
"value" : "market"
} ],
"name" : "ETH/USDC rate"
},
"id" : "i_8",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4310"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "protocol",
"value" : "AAVE"
}, {
"key" : "network",
"value" : "ETH"
}, {
"key" : "deposit",
"value" : "2026-06-01"
} ],
"name" : "wBTC yield"
},
"id" : "i_9",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.00369452",
"currency" : "WBTC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "protocol",
"value" : "AAVE"
}, {
"key" : "network",
"value" : "ETH"
}, {
"key" : "deposit",
"value" : "2026-06-04"
} ],
"name" : "ETH yield"
},
"id" : "i_10",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.027353420000000000",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "protocol",
"value" : "AAVE"
}, {
"key" : "network",
"value" : "ETH"
}, {
"key" : "deposit",
"value" : "2026-06-08"
} ],
"name" : "USDC yield"
},
"id" : "i_11",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "119.583561",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "protocol",
"value" : "Morpho"
}, {
"key" : "deposit",
"value" : "2026-06-03"
} ],
"name" : "USDT yield"
},
"id" : "i_12",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "299.589041",
"currency" : "USDT"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "protocol",
"value" : "Lido+EtherFi"
}, {
"key" : "network",
"value" : "ETH"
}, {
"key" : "deposit",
"value" : "2026-06-01"
} ],
"name" : "ETH yield"
},
"id" : "i_13",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.169105630000000000",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "tx",
"value" : "wBTC→AAVE"
}, {
"key" : "date",
"value" : "2026-06-01"
} ],
"name" : "Gas (ETH)"
},
"id" : "i_14",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.005000000000000000",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "tx",
"value" : "ETH→AAVE"
}, {
"key" : "date",
"value" : "2026-06-04"
} ],
"name" : "Gas (ETH)"
},
"id" : "i_15",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.003960000000000000",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "tx",
"value" : "USDC→AAVE"
}, {
"key" : "date",
"value" : "2026-06-08"
} ],
"name" : "Gas (ETH)"
},
"id" : "i_16",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.003000000000000000",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "tx",
"value" : "USDT→Morpho"
}, {
"key" : "date",
"value" : "2026-06-03"
} ],
"name" : "Gas (ETH)"
},
"id" : "i_17",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.003960000000000000",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "tx",
"value" : "ETH→Lido"
}, {
"key" : "date",
"value" : "2026-06-01"
} ],
"name" : "Gas (ETH)"
},
"id" : "i_18",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.004000000000000000",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "tx",
"value" : "stETH→EtherFi"
}, {
"key" : "date",
"value" : "2026-06-01"
} ],
"name" : "Gas (ETH)"
},
"id" : "i_19",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.005200000000000000",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Platform fee rate (3%)"
},
"id" : "i_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.03"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "wBTC yield in BTC (peg-adjusted)"
},
"id" : "o_21",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.00369452",
"currency" : "BTC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "wBTC yield in USDC"
},
"id" : "o_22",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "405.658296",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ETH(AAVE) yield in USDC"
},
"id" : "o_23",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "127.193403",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "USDT(Morpho) yield in USDC"
},
"id" : "o_24",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "299.529123",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ETH(Lido+EtherFi) yield in USDC"
},
"id" : "o_25",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "786.341179",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gross yield in USDC"
},
"id" : "o_26",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "1738.305562",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gas (wBTC/AAVE) in USDC"
},
"id" : "o_27",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "22.400000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gas (ETH/AAVE) in USDC"
},
"id" : "o_28",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "17.384400",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gas (USDC/AAVE) in USDC"
},
"id" : "o_29",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "12.930000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gas (USDT/Morpho) in USDC"
},
"id" : "o_30",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "17.503200",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gas (ETH/Lido) in USDC"
},
"id" : "o_31",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "17.920000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gas (stETH/EtherFi) in USDC"
},
"id" : "o_32",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "23.296000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "basis",
"value" : "realized at deposit moment"
} ],
"name" : "Total gas fees in USDC"
},
"id" : "o_33",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "111.433600",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Platform fee in USDC"
},
"id" : "o_34",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "52.149166",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "After gas deduction"
},
"id" : "o_35",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "1626.871962",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Net profit in USDC"
},
"id" : "o_36",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "1574.722796",
"currency" : "USDC"
}
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
}, {
"key" : "r",
"value" : "i_4"
} ],
"resultId" : "o_21"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "o_21"
}, {
"key" : "r",
"value" : "i_1"
} ],
"resultId" : "o_22"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_10"
}, {
"key" : "r",
"value" : "i_2"
} ],
"resultId" : "o_23"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_12"
}, {
"key" : "r",
"value" : "i_3"
} ],
"resultId" : "o_24"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_13"
}, {
"key" : "r",
"value" : "i_2"
} ],
"resultId" : "o_25"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a+b0+...+bn"
} ],
"name" : "addBulk"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b0",
"value" : "o_23"
}, {
"key" : "b1",
"value" : "i_11"
}, {
"key" : "b2",
"value" : "o_24"
}, {
"key" : "b3",
"value" : "o_25"
} ],
"resultId" : "o_26"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_14"
}, {
"key" : "r",
"value" : "i_5"
} ],
"resultId" : "o_27"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_15"
}, {
"key" : "r",
"value" : "i_7"
} ],
"resultId" : "o_28"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_16"
}, {
"key" : "r",
"value" : "i_8"
} ],
"resultId" : "o_29"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_17"
}, {
"key" : "r",
"value" : "i_6"
} ],
"resultId" : "o_30"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_18"
}, {
"key" : "r",
"value" : "i_5"
} ],
"resultId" : "o_31"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "convert(a,r)"
} ],
"name" : "convert"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_19"
}, {
"key" : "r",
"value" : "i_5"
} ],
"resultId" : "o_32"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a+b0+...+bn"
} ],
"name" : "addBulk"
},
"id" : "op_13",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "o_27"
}, {
"key" : "b0",
"value" : "o_28"
}, {
"key" : "b1",
"value" : "o_29"
}, {
"key" : "b2",
"value" : "o_30"
}, {
"key" : "b3",
"value" : "o_31"
}, {
"key" : "b4",
"value" : "o_32"
} ],
"resultId" : "o_33"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a*f"
} ],
"name" : "scale"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "f",
"value" : "i_20"
} ],
"resultId" : "o_34"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a-b"
} ],
"name" : "subtract"
},
"id" : "op_15",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "b",
"value" : "o_33"
} ],
"resultId" : "o_35"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a-b"
} ],
"name" : "subtract"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "o_35"
}, {
"key" : "b",
"value" : "o_34"
} ],
"resultId" : "o_36"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_8, i_9, i_10, i_11, i_12, i_13, i_14, i_15, i_16, i_17, i_18, i_19, i_20]
- **Leaf variable IDs**: [o_36]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_5, o_26]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
