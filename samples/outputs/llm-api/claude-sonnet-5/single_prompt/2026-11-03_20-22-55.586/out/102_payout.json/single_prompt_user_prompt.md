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
"name" : "ETH/USDC daily options payout"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ETH/USDC spot (2026-06-30)"
},
"id" : "i_1",
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
"meta" : [ ],
"name" : "Position [0]"
},
"id" : "i_2",
"valueClass" : "io.compprov.examples.nav.model.OptionPosition"
},
"value" : {
"type" : "CALL",
"strike" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4630"
},
"size" : {
"amount" : "6.863900000000000000",
"currency" : "ETH"
}
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Position [1]"
},
"id" : "i_3",
"valueClass" : "io.compprov.examples.nav.model.OptionPosition"
},
"value" : {
"type" : "PUT",
"strike" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4710"
},
"size" : {
"amount" : "2.843100000000000000",
"currency" : "ETH"
}
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Position [2]"
},
"id" : "i_4",
"valueClass" : "io.compprov.examples.nav.model.OptionPosition"
},
"value" : {
"type" : "CALL",
"strike" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4670"
},
"size" : {
"amount" : "9.043400000000000000",
"currency" : "ETH"
}
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Position [3]"
},
"id" : "i_5",
"valueClass" : "io.compprov.examples.nav.model.OptionPosition"
},
"value" : {
"type" : "PUT",
"strike" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4550"
},
"size" : {
"amount" : "2.829900000000000000",
"currency" : "ETH"
}
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Position [4]"
},
"id" : "i_6",
"valueClass" : "io.compprov.examples.nav.model.OptionPosition"
},
"value" : {
"type" : "PUT",
"strike" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4730"
},
"size" : {
"amount" : "7.850700000000000000",
"currency" : "ETH"
}
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Position [5]"
},
"id" : "i_7",
"valueClass" : "io.compprov.examples.nav.model.OptionPosition"
},
"value" : {
"type" : "CALL",
"strike" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4630"
},
"size" : {
"amount" : "4.421300000000000000",
"currency" : "ETH"
}
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Position [6]"
},
"id" : "i_8",
"valueClass" : "io.compprov.examples.nav.model.OptionPosition"
},
"value" : {
"type" : "CALL",
"strike" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4710"
},
"size" : {
"amount" : "3.927000000000000000",
"currency" : "ETH"
}
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Position [7]"
},
"id" : "i_9",
"valueClass" : "io.compprov.examples.nav.model.OptionPosition"
},
"value" : {
"type" : "PUT",
"strike" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4690"
},
"size" : {
"amount" : "5.984100000000000000",
"currency" : "ETH"
}
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Position [8]"
},
"id" : "i_10",
"valueClass" : "io.compprov.examples.nav.model.OptionPosition"
},
"value" : {
"type" : "PUT",
"strike" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4590"
},
"size" : {
"amount" : "8.277100000000000000",
"currency" : "ETH"
}
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Position [9]"
},
"id" : "i_11",
"valueClass" : "io.compprov.examples.nav.model.OptionPosition"
},
"value" : {
"type" : "PUT",
"strike" : {
"from" : "ETH",
"to" : "USDC",
"rate" : "4550"
},
"size" : {
"amount" : "5.915500000000000000",
"currency" : "ETH"
}
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [0]"
},
"id" : "o_12",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "137.278000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [1]"
},
"id" : "o_13",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "170.586000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [2]"
},
"id" : "o_14",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.000000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [3]"
},
"id" : "o_15",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.000000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [4]"
},
"id" : "o_16",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "628.056000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [5]"
},
"id" : "o_17",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "88.426000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [6]"
},
"id" : "o_18",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.000000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [7]"
},
"id" : "o_19",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "239.364000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [8]"
},
"id" : "o_20",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.000000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [9]"
},
"id" : "o_21",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "0.000000",
"currency" : "USDC"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total payout in USDC"
},
"id" : "o_22",
"valueClass" : "io.compprov.examples.nav.model.Amount"
},
"value" : {
"amount" : "1263.710000",
"currency" : "USDC"
}
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "pos.payout(price)"
} ],
"name" : "payout"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "pos",
"value" : "i_2"
}, {
"key" : "price",
"value" : "i_1"
} ],
"resultId" : "o_12"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "pos.payout(price)"
} ],
"name" : "payout"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "pos",
"value" : "i_3"
}, {
"key" : "price",
"value" : "i_1"
} ],
"resultId" : "o_13"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "pos.payout(price)"
} ],
"name" : "payout"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "pos",
"value" : "i_4"
}, {
"key" : "price",
"value" : "i_1"
} ],
"resultId" : "o_14"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "pos.payout(price)"
} ],
"name" : "payout"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "pos",
"value" : "i_5"
}, {
"key" : "price",
"value" : "i_1"
} ],
"resultId" : "o_15"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "pos.payout(price)"
} ],
"name" : "payout"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "pos",
"value" : "i_6"
}, {
"key" : "price",
"value" : "i_1"
} ],
"resultId" : "o_16"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "pos.payout(price)"
} ],
"name" : "payout"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "pos",
"value" : "i_7"
}, {
"key" : "price",
"value" : "i_1"
} ],
"resultId" : "o_17"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "pos.payout(price)"
} ],
"name" : "payout"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "pos",
"value" : "i_8"
}, {
"key" : "price",
"value" : "i_1"
} ],
"resultId" : "o_18"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "pos.payout(price)"
} ],
"name" : "payout"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "pos",
"value" : "i_9"
}, {
"key" : "price",
"value" : "i_1"
} ],
"resultId" : "o_19"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "pos.payout(price)"
} ],
"name" : "payout"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "pos",
"value" : "i_10"
}, {
"key" : "price",
"value" : "i_1"
} ],
"resultId" : "o_20"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "pos.payout(price)"
} ],
"name" : "payout"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "pos",
"value" : "i_11"
}, {
"key" : "price",
"value" : "i_1"
} ],
"resultId" : "o_21"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a+b0+...+bn"
} ],
"name" : "addBulk"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.examples.nav.wrapped.WrappedAmount"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b0",
"value" : "o_13"
}, {
"key" : "b1",
"value" : "o_14"
}, {
"key" : "b2",
"value" : "o_15"
}, {
"key" : "b3",
"value" : "o_16"
}, {
"key" : "b4",
"value" : "o_17"
}, {
"key" : "b5",
"value" : "o_18"
}, {
"key" : "b6",
"value" : "o_19"
}, {
"key" : "b7",
"value" : "o_20"
}, {
"key" : "b8",
"value" : "o_21"
} ],
"resultId" : "o_22"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_8, i_9, i_10, i_11]
- **Leaf variable IDs**: [o_22]
- **Variable IDs consumed as an argument by more than one operation**: [i_1]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
