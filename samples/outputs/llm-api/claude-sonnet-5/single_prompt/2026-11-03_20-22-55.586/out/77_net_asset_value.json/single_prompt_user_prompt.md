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
"name" : "Nav calculation example"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "origin",
"value" : "Binance"
} ],
"name" : "BTC/USD rate"
},
"id" : "i_1",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "BTC",
"to" : "USD",
"rate" : "68989.72"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "origin",
"value" : "Binance"
} ],
"name" : "ETH/USD rate"
},
"id" : "i_2",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "ETH",
"to" : "USD",
"rate" : "2083.31"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "origin",
"value" : "Binance"
} ],
"name" : "WSTETH/ETH rate"
},
"id" : "i_3",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "WSTETH",
"to" : "ETH",
"rate" : "1.243492"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "origin",
"value" : "Binance"
} ],
"name" : "USDC/USD rate"
},
"id" : "i_4",
"valueClass" : "io.compprov.examples.nav.model.Rate"
},
"value" : {
"from" : "USDC",
"to" : "USD",
"rate" : "0.99993"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "source",
"value" : "Binance"
} ],
"name" : "BTC balance"
},
"id" : "i_5",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "2.91818801",
"currency" : "BTC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "source",
"value" : "Binance"
} ],
"name" : "ETH balance"
},
"id" : "i_6",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "71.465292706073725526",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "source",
"value" : "Binance"
} ],
"name" : "USDC balance"
},
"id" : "i_7",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "55009.396140",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "source",
"value" : "Trust Nodes"
} ],
"name" : "ETH balance"
},
"id" : "i_8",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.314511152401086846",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "source",
"value" : "Lido"
} ],
"name" : "WSTETH balance"
},
"id" : "i_9",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "51.629114720513456404",
"currency" : "WSTETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "source",
"value" : "Morpho"
} ],
"name" : "USDC balance"
},
"id" : "i_10",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "49195.878692",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "WSTETH balance"
},
"id" : "o_11",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "51.943625872914543250",
"currency" : "WSTETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "WSTETH->ETH"
},
"id" : "o_12",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "64.591483223962251215",
"currency" : "ETH"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "BTC->USD"
},
"id" : "o_13",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "201324.97",
"currency" : "USD"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ETH(Binance)->USD"
},
"id" : "o_14",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "148884.35",
"currency" : "USD"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "USDC(Binance)->USD"
},
"id" : "o_15",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "55005.54",
"currency" : "USD"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ETH(Staked)->USD"
},
"id" : "o_16",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "134564.08",
"currency" : "USD"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "USDC(Morpho)->USD"
},
"id" : "o_17",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "49192.43",
"currency" : "USD"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Assets sum"
},
"id" : "o_18",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "588971.37",
"currency" : "USD"
}
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a+b"
} ],
"name" : "add"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
}, {
"key" : "b",
"value" : "i_8"
} ],
"resultId" : "o_11"
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
"value" : "o_11"
}, {
"key" : "r",
"value" : "i_3"
} ],
"resultId" : "o_12"
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
"value" : "i_5"
}, {
"key" : "r",
"value" : "i_1"
} ],
"resultId" : "o_13"
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
"value" : "i_6"
}, {
"key" : "r",
"value" : "i_2"
} ],
"resultId" : "o_14"
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
"value" : "i_7"
}, {
"key" : "r",
"value" : "i_4"
} ],
"resultId" : "o_15"
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
"value" : "o_12"
}, {
"key" : "r",
"value" : "i_2"
} ],
"resultId" : "o_16"
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
"value" : "i_10"
}, {
"key" : "r",
"value" : "i_4"
} ],
"resultId" : "o_17"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a+b0+...+bn"
} ],
"name" : "addBulk"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b0",
"value" : "o_14"
}, {
"key" : "b1",
"value" : "o_15"
}, {
"key" : "b2",
"value" : "o_16"
}, {
"key" : "b3",
"value" : "o_17"
} ],
"resultId" : "o_18"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_8, i_9, i_10]
- **Leaf variable IDs**: [o_18]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_4]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
