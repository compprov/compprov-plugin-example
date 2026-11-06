## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Statistics: weighted sample variance of a data segment"
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
"name" : "[0] Observation"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Weight"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Observation"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Weight"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Observation"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "11.8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Weight"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Observation"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "14.2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Weight"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Observation"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "14.9"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Weight"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Weighted observation"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "18.75"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Weighted observation"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Weighted observation"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "14.16"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Weighted observation"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.68"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Weighted observation"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "11.92"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sum of weighted observations"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "60.11"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sum of weights (mean)"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Weighted mean"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.78936170212766"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Deviation from weighted mean"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-0.28936170212766"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Squared deviation"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.08373019465821663"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Weighted squared deviation"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.1255952919873249"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Weighted deviation"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-0.434042553191490"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Deviation from weighted mean"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-0.78936170212766"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Squared deviation"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.6230918967858766"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Weighted squared deviation"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.4984735174287013"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Weighted deviation"
},
"id" : "o_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-0.631489361702128"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Deviation from weighted mean"
},
"id" : "o_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-0.98936170212766"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Squared deviation"
},
"id" : "o_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.9788365776369406"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Weighted squared deviation"
},
"id" : "o_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.174603893164329"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Weighted deviation"
},
"id" : "o_31",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1.187234042553192"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Deviation from weighted mean"
},
"id" : "o_32",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.41063829787234"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Squared deviation"
},
"id" : "o_33",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.989900407424173"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Weighted squared deviation"
},
"id" : "o_34",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.7959601629696692"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Weighted deviation"
},
"id" : "o_35",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.564255319148936"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Deviation from weighted mean"
},
"id" : "o_36",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.11063829787234"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Squared deviation"
},
"id" : "o_37",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.454794024445449"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Weighted squared deviation"
},
"id" : "o_38",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.563835219556359"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Weighted deviation"
},
"id" : "o_39",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.688510638297872"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sum of weighted squared deviations"
},
"id" : "o_40",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.158468085106383"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sum of weighted deviations"
},
"id" : "o_41",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-2E-15"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sum of weights (variance)"
},
"id" : "o_42",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Squared sum of weighted deviations"
},
"id" : "o_43",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4E-30"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Correction term"
},
"id" : "o_44",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8.510638297872340E-31"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Corrected sum of weighted squared deviations"
},
"id" : "o_45",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.158468085106383"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Bias correction, 1"
},
"id" : "i_46",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Effective degrees of freedom, Σw − 1"
},
"id" : "o_47",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Weighted sample variance"
},
"id" : "o_48",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.664450833812536"
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
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_5"
}, {
"key" : "b",
"value" : "i_4"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
}, {
"key" : "b",
"value" : "i_6"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
}, {
"key" : "b",
"value" : "i_8"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_15"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_11"
}, {
"key" : "b",
"value" : "i_10"
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
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
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
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_17"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b0",
"value" : "i_5"
}, {
"key" : "b1",
"value" : "i_7"
}, {
"key" : "b2",
"value" : "i_9"
}, {
"key" : "b3",
"value" : "i_11"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
}, {
"key" : "b",
"value" : "o_18"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_19"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "o_19"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_20"
}, {
"key" : "b",
"value" : "o_20"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "o_21"
}, {
"key" : "mc",
"value" : "i_1"
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
"value" : "i_3"
}, {
"key" : "b",
"value" : "o_20"
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
"value" : "i_4"
}, {
"key" : "b",
"value" : "o_19"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_24"
}, {
"key" : "b",
"value" : "o_24"
}, {
"key" : "mc",
"value" : "i_1"
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
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_5"
}, {
"key" : "b",
"value" : "o_25"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_5"
}, {
"key" : "b",
"value" : "o_24"
}, {
"key" : "mc",
"value" : "i_1"
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
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
}, {
"key" : "b",
"value" : "o_19"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_28"
}, {
"key" : "b",
"value" : "o_28"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_29"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
}, {
"key" : "b",
"value" : "o_29"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_20",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
}, {
"key" : "b",
"value" : "o_28"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_21",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
}, {
"key" : "b",
"value" : "o_19"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_22",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_32"
}, {
"key" : "b",
"value" : "o_32"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_23",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
}, {
"key" : "b",
"value" : "o_33"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_34"
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
"value" : "i_9"
}, {
"key" : "b",
"value" : "o_32"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_35"
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
"value" : "i_10"
}, {
"key" : "b",
"value" : "o_19"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_26",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_36"
}, {
"key" : "b",
"value" : "o_36"
}, {
"key" : "mc",
"value" : "i_1"
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
"id" : "op_27",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_11"
}, {
"key" : "b",
"value" : "o_37"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_28",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_11"
}, {
"key" : "b",
"value" : "o_36"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_39"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_29",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b0",
"value" : "o_26"
}, {
"key" : "b1",
"value" : "o_30"
}, {
"key" : "b2",
"value" : "o_34"
}, {
"key" : "b3",
"value" : "o_38"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_40"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_30",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_23"
}, {
"key" : "b0",
"value" : "o_27"
}, {
"key" : "b1",
"value" : "o_31"
}, {
"key" : "b2",
"value" : "o_35"
}, {
"key" : "b3",
"value" : "o_39"
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
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_31",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b0",
"value" : "i_5"
}, {
"key" : "b1",
"value" : "i_7"
}, {
"key" : "b2",
"value" : "i_9"
}, {
"key" : "b3",
"value" : "i_11"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_32",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_41"
}, {
"key" : "b",
"value" : "o_41"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_33",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_43"
}, {
"key" : "b",
"value" : "o_42"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_34",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_40"
}, {
"key" : "b",
"value" : "o_44"
}, {
"key" : "mc",
"value" : "i_1"
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
"id" : "op_35",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_42"
}, {
"key" : "b",
"value" : "i_46"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_47"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_36",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_45"
}, {
"key" : "b",
"value" : "o_47"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_48"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_8, i_9, i_10, i_11, i_46]
- **Leaf variable IDs**: [o_48]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3, i_4, i_5, i_6, i_7, i_8, i_9, i_10, i_11, o_19, o_20, o_24, o_28, o_32, o_36, o_42]
- **Operations that consume the same variable more than once**: op_10 ("multiply") consumes [o_20] more than once; op_14 ("multiply") consumes [o_24] more than once; op_18 ("multiply") consumes [o_28] more than once; op_22 ("multiply") consumes [o_32] more than once; op_26 ("multiply") consumes [o_36] more than once; op_32 ("multiply") consumes [o_41] more than once
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
