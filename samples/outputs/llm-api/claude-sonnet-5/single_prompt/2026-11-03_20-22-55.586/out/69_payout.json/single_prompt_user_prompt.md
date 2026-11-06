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
"WSTETH" : 18
}
} ],
"name" : "ETH/USDC daily options payout"
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
"name" : "Zero (OTM floor)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ETH/USDC spot (2026-06-30)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4650"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic scale"
},
"id" : "i_4",
"valueClass" : "java.math.MathContext"
},
"value" : {
"precision" : 2,
"roundingMode" : "DOWN"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [0] CALL K=4630"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4630"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [0] CALL K=4630"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.863900000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Spot - Strike [0] CALL K=4630"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [0] CALL K=4630"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [0] CALL K=4630"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "137.2780000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [1] PUT K=4710"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4710"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [1] PUT K=4710"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.843100000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [1] PUT K=4710"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [1] PUT K=4710"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [1] PUT K=4710"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "170.5860000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : ""
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "170.58"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [2] CALL K=4670"
},
"id" : "i_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4670"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [2] CALL K=4670"
},
"id" : "i_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.043400000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Spot - Strike [2] CALL K=4670"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [2] CALL K=4670"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [2] CALL K=4670"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E-18"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : ""
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [3] PUT K=4550"
},
"id" : "i_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4550"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [3] PUT K=4550"
},
"id" : "i_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.829900000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [3] PUT K=4550"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [3] PUT K=4550"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [3] PUT K=4550"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E-18"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : ""
},
"id" : "o_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [4] PUT K=4730"
},
"id" : "i_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4730"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [4] PUT K=4730"
},
"id" : "i_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7.850700000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [4] PUT K=4730"
},
"id" : "o_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "80"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [4] PUT K=4730"
},
"id" : "o_31",
"valueClass" : "java.math.BigDecimal"
},
"value" : "80"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [4] PUT K=4730"
},
"id" : "o_32",
"valueClass" : "java.math.BigDecimal"
},
"value" : "628.0560000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : ""
},
"id" : "o_33",
"valueClass" : "java.math.BigDecimal"
},
"value" : "628.05"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [5] CALL K=4630"
},
"id" : "i_34",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4630"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [5] CALL K=4630"
},
"id" : "i_35",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.421300000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Spot - Strike [5] CALL K=4630"
},
"id" : "o_36",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [5] CALL K=4630"
},
"id" : "o_37",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [5] CALL K=4630"
},
"id" : "o_38",
"valueClass" : "java.math.BigDecimal"
},
"value" : "88.42600000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : ""
},
"id" : "o_39",
"valueClass" : "java.math.BigDecimal"
},
"value" : "88.42"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [6] CALL K=4710"
},
"id" : "i_40",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4710"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [6] CALL K=4710"
},
"id" : "i_41",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.927000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Spot - Strike [6] CALL K=4710"
},
"id" : "o_42",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [6] CALL K=4710"
},
"id" : "o_43",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [6] CALL K=4710"
},
"id" : "o_44",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E-18"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : ""
},
"id" : "o_45",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [7] PUT K=4690"
},
"id" : "i_46",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4690"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [7] PUT K=4690"
},
"id" : "i_47",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.984100000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [7] PUT K=4690"
},
"id" : "o_48",
"valueClass" : "java.math.BigDecimal"
},
"value" : "40"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [7] PUT K=4690"
},
"id" : "o_49",
"valueClass" : "java.math.BigDecimal"
},
"value" : "40"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [7] PUT K=4690"
},
"id" : "o_50",
"valueClass" : "java.math.BigDecimal"
},
"value" : "239.3640000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : ""
},
"id" : "o_51",
"valueClass" : "java.math.BigDecimal"
},
"value" : "239.36"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [8] PUT K=4590"
},
"id" : "i_52",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4590"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [8] PUT K=4590"
},
"id" : "i_53",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8.277100000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [8] PUT K=4590"
},
"id" : "o_54",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [8] PUT K=4590"
},
"id" : "o_55",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [8] PUT K=4590"
},
"id" : "o_56",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E-18"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : ""
},
"id" : "o_57",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [9] PUT K=4550"
},
"id" : "i_58",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4550"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [9] PUT K=4550"
},
"id" : "i_59",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.915500000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [9] PUT K=4550"
},
"id" : "o_60",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [9] PUT K=4550"
},
"id" : "o_61",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [9] PUT K=4550"
},
"id" : "o_62",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E-18"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : ""
},
"id" : "o_63",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total payout in USDC"
},
"id" : "o_64",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1263.688000000000"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_5"
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_8"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
}, {
"key" : "b",
"value" : "i_6"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_10"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_12"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_13"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "i_11"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_14"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "mc",
"value" : "i_4"
} ],
"resultId" : "o_15"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_16"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_18"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_18"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_19"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_19"
}, {
"key" : "b",
"value" : "i_17"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_20"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_20"
}, {
"key" : "mc",
"value" : "i_4"
} ],
"resultId" : "o_21"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_22"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_24"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_24"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_25"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_25"
}, {
"key" : "b",
"value" : "i_23"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_26"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "mc",
"value" : "i_4"
} ],
"resultId" : "o_27"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_28"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_30"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_30"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_31"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_31"
}, {
"key" : "b",
"value" : "i_29"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_32"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_32"
}, {
"key" : "mc",
"value" : "i_4"
} ],
"resultId" : "o_33"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_20",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_34"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_36"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_21",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_36"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_37"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_22",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_37"
}, {
"key" : "b",
"value" : "i_35"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_38"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_23",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_38"
}, {
"key" : "mc",
"value" : "i_4"
} ],
"resultId" : "o_39"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_24",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_40"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_42"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_25",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_42"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_43"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_26",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_43"
}, {
"key" : "b",
"value" : "i_41"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_44"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_27",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_44"
}, {
"key" : "mc",
"value" : "i_4"
} ],
"resultId" : "o_45"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_28",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_46"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_48"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_29",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_48"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_49"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_30",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_49"
}, {
"key" : "b",
"value" : "i_47"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_50"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_31",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_50"
}, {
"key" : "mc",
"value" : "i_4"
} ],
"resultId" : "o_51"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_32",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_52"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_54"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_33",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_54"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_55"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_34",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_55"
}, {
"key" : "b",
"value" : "i_53"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_56"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_35",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_56"
}, {
"key" : "mc",
"value" : "i_4"
} ],
"resultId" : "o_57"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_36",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_58"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_60"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_37",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_60"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_61"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_38",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_61"
}, {
"key" : "b",
"value" : "i_59"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_62"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_39",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_62"
}, {
"key" : "mc",
"value" : "i_4"
} ],
"resultId" : "o_63"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_40",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
}, {
"key" : "b0",
"value" : "o_15"
}, {
"key" : "b1",
"value" : "o_21"
}, {
"key" : "b2",
"value" : "o_27"
}, {
"key" : "b3",
"value" : "o_33"
}, {
"key" : "b4",
"value" : "o_39"
}, {
"key" : "b5",
"value" : "o_45"
}, {
"key" : "b6",
"value" : "o_51"
}, {
"key" : "b7",
"value" : "o_57"
}, {
"key" : "b8",
"value" : "o_63"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_64"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_10, i_11, i_16, i_17, i_22, i_23, i_28, i_29, i_34, i_35, i_40, i_41, i_46, i_47, i_52, i_53, i_58, i_59]
- **Leaf variable IDs**: [o_64]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
