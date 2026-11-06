## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
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
"name" : "Strike [0] CALL K=4630"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4630"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [0] CALL K=4630"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.8639"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Spot - Strike [0] CALL K=4630"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [0] CALL K=4630"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [0] CALL K=4630"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "137.2780"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [1] PUT K=4710"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4710"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [1] PUT K=4710"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.8431"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [1] PUT K=4710"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [1] PUT K=4710"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [1] PUT K=4710"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "170.5860"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [2] CALL K=4670"
},
"id" : "i_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4670"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [2] CALL K=4670"
},
"id" : "i_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.0434"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Spot - Strike [2] CALL K=4670"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [2] CALL K=4670"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [2] CALL K=4670"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [3] PUT K=4550"
},
"id" : "i_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4550"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [3] PUT K=4550"
},
"id" : "i_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.8299"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [3] PUT K=4550"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [3] PUT K=4550"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [3] PUT K=4550"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [4] PUT K=4730"
},
"id" : "i_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4730"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [4] PUT K=4730"
},
"id" : "i_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7.8507"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [4] PUT K=4730"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "80"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [4] PUT K=4730"
},
"id" : "o_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "80"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [4] PUT K=4730"
},
"id" : "o_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "628.0560"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [5] CALL K=4630"
},
"id" : "i_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4630"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [5] CALL K=4630"
},
"id" : "i_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.4213"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Spot - Strike [5] CALL K=4630"
},
"id" : "o_31",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [5] CALL K=4630"
},
"id" : "o_32",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [5] CALL K=4630"
},
"id" : "o_33",
"valueClass" : "java.math.BigDecimal"
},
"value" : "88.4260"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [6] CALL K=4710"
},
"id" : "i_34",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4710"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [6] CALL K=4710"
},
"id" : "i_35",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.9270"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Spot - Strike [6] CALL K=4710"
},
"id" : "o_36",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [6] CALL K=4710"
},
"id" : "o_37",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [6] CALL K=4710"
},
"id" : "o_38",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [7] PUT K=4690"
},
"id" : "i_39",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4690"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [7] PUT K=4690"
},
"id" : "i_40",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.9841"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [7] PUT K=4690"
},
"id" : "o_41",
"valueClass" : "java.math.BigDecimal"
},
"value" : "40"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [7] PUT K=4690"
},
"id" : "o_42",
"valueClass" : "java.math.BigDecimal"
},
"value" : "40"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [7] PUT K=4690"
},
"id" : "o_43",
"valueClass" : "java.math.BigDecimal"
},
"value" : "239.3640"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [8] PUT K=4590"
},
"id" : "i_44",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4590"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [8] PUT K=4590"
},
"id" : "i_45",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8.2771"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [8] PUT K=4590"
},
"id" : "o_46",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [8] PUT K=4590"
},
"id" : "o_47",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [8] PUT K=4590"
},
"id" : "o_48",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike [9] PUT K=4550"
},
"id" : "i_49",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4550"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Size [9] PUT K=4550"
},
"id" : "i_50",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.9155"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Strike - Spot [9] PUT K=4550"
},
"id" : "o_51",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Intrinsic/ETH [9] PUT K=4550"
},
"id" : "o_52",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payout [9] PUT K=4550"
},
"id" : "o_53",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total payout in USDC"
},
"id" : "o_54",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1263.7100"
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_7"
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
"value" : "o_7"
}, {
"key" : "b",
"value" : "i_5"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_11"
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
"value" : "o_11"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_12"
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
"value" : "o_12"
}, {
"key" : "b",
"value" : "i_10"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_13"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_14"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_16"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_17"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
}, {
"key" : "b",
"value" : "i_15"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_19"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_21"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_21"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_22"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b",
"value" : "i_20"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_23"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_24"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_27"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_27"
}, {
"key" : "b",
"value" : "i_25"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_28"
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
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_29"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_31"
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
"value" : "o_31"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_32"
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
"value" : "o_32"
}, {
"key" : "b",
"value" : "i_30"
}, {
"key" : "mc",
"value" : "i_1"
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
"id" : "op_19",
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
"id" : "op_20",
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
"id" : "op_21",
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_22",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_39"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_41"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_23",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_41"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_42"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_24",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_42"
}, {
"key" : "b",
"value" : "i_40"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_43"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_25",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_44"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_46"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_26",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_46"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_47"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_27",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_47"
}, {
"key" : "b",
"value" : "i_45"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_28",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_49"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_51"
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
"value" : "o_51"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_52"
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
"value" : "o_52"
}, {
"key" : "b",
"value" : "i_50"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_53"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_31",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
}, {
"key" : "b0",
"value" : "o_13"
}, {
"key" : "b1",
"value" : "o_18"
}, {
"key" : "b2",
"value" : "o_23"
}, {
"key" : "b3",
"value" : "o_28"
}, {
"key" : "b4",
"value" : "o_33"
}, {
"key" : "b5",
"value" : "o_38"
}, {
"key" : "b6",
"value" : "o_43"
}, {
"key" : "b7",
"value" : "o_48"
}, {
"key" : "b8",
"value" : "o_53"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_54"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_9, i_10, i_14, i_15, i_19, i_20, i_24, i_25, i_29, i_30, i_34, i_35, i_39, i_40, i_44, i_45, i_49, i_50]
- **Leaf variable IDs**: [o_54]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
