## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Statistics: simple linear regression sum of squared errors"
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
"name" : "[0] x"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "110.7178495"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] y"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8906.8377"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial Sxx, 0"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial Syy, 0"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial Sxy, 0"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] x"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "110.7264895"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] y"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8913.0454"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Observations so far, n"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Observations incl. this one, n + 1"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Update factor, n / (n + 1)"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] x deviation from running mean"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0086400"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] y deviation from running mean"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.2077"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] dx²"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00007464960000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Sxx increment"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000037324800000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Sxx"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000037324800000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] dy²"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "38.53553929"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Syy increment"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "19.267769645"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Syy"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "19.267769645"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] dx·dy"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.05363452800"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Sxy increment"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.026817264000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Sxy"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.026817264000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] x mean shift"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0043200"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Running x mean"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "110.7221695"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] y mean shift"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.10385"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Running y mean"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8909.94155"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] x"
},
"id" : "i_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "110.7351295"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] y"
},
"id" : "i_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8919.2531"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Observations so far, n"
},
"id" : "i_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Observations incl. this one, n + 1"
},
"id" : "i_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Update factor, n / (n + 1)"
},
"id" : "o_31",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.6666666666666667"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] x deviation from running mean"
},
"id" : "o_32",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0129600"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] y deviation from running mean"
},
"id" : "o_33",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.31155"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] dx²"
},
"id" : "o_34",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00016796160000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Sxx increment"
},
"id" : "o_35",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0001119744000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Sxx"
},
"id" : "o_36",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0001492992000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] dy²"
},
"id" : "o_37",
"valueClass" : "java.math.BigDecimal"
},
"value" : "86.7049634025"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Syy increment"
},
"id" : "o_38",
"valueClass" : "java.math.BigDecimal"
},
"value" : "57.80330893500000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Syy"
},
"id" : "o_39",
"valueClass" : "java.math.BigDecimal"
},
"value" : "77.07107858000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] dx·dy"
},
"id" : "o_40",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.120677688000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Sxy increment"
},
"id" : "o_41",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.08045179200000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Sxy"
},
"id" : "o_42",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.1072690560000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] x mean shift"
},
"id" : "o_43",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0043200"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Running x mean"
},
"id" : "o_44",
"valueClass" : "java.math.BigDecimal"
},
"value" : "110.7264895"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] y mean shift"
},
"id" : "o_45",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.10385"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Running y mean"
},
"id" : "o_46",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8913.04540"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sxy²"
},
"id" : "o_47",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.01150665037513114"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Regression sum of squares, Sxy² / Sxx"
},
"id" : "o_48",
"valueClass" : "java.math.BigDecimal"
},
"value" : "77.07107858000003"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Unclamped SSE, Syy − Sxy² / Sxx"
},
"id" : "o_49",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-3E-14"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lower bound for SSE, 0"
},
"id" : "i_50",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sum of squared errors"
},
"id" : "o_51",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
}, {
"key" : "b",
"value" : "i_10"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
}, {
"key" : "b",
"value" : "i_3"
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
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "o_12"
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
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b",
"value" : "o_11"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_4"
}, {
"key" : "b",
"value" : "o_15"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "o_13"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
}, {
"key" : "b",
"value" : "o_11"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_5"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "o_13"
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
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_20"
}, {
"key" : "b",
"value" : "o_11"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_13",
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
"resultId" : "o_23"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "o_23"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "i_10"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_29"
}, {
"key" : "b",
"value" : "i_30"
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
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_27"
}, {
"key" : "b",
"value" : "o_24"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_28"
}, {
"key" : "b",
"value" : "o_26"
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
"id" : "op_20",
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
"id" : "op_21",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_34"
}, {
"key" : "b",
"value" : "o_31"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_22",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
"value" : "o_35"
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
"id" : "op_23",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_33"
}, {
"key" : "b",
"value" : "o_33"
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
"id" : "op_24",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_37"
}, {
"key" : "b",
"value" : "o_31"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_25",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_19"
}, {
"key" : "b",
"value" : "o_38"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_26",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_32"
}, {
"key" : "b",
"value" : "o_33"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_27",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_40"
}, {
"key" : "b",
"value" : "o_31"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_28",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b",
"value" : "o_41"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_29",
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
"resultId" : "o_43"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_30",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_24"
}, {
"key" : "b",
"value" : "o_43"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_31",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_33"
}, {
"key" : "b",
"value" : "i_30"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_32",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "b",
"value" : "o_45"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_33",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_42"
}, {
"key" : "b",
"value" : "o_42"
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
"id" : "op_34",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_47"
}, {
"key" : "b",
"value" : "o_36"
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
"id" : "op_35",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_39"
}, {
"key" : "b",
"value" : "o_48"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_49"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_36",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_49"
}, {
"key" : "b",
"value" : "i_50"
} ],
"resultId" : "o_51"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_8, i_9, i_10, i_27, i_28, i_29, i_30, i_50]
- **Leaf variable IDs**: [o_44, o_46, o_51]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3, i_10, o_11, o_12, o_13, o_24, o_26, i_30, o_31, o_32, o_33]
- **Operations that consume the same variable more than once**: op_4 ("multiply") consumes [o_12] more than once; op_7 ("multiply") consumes [o_13] more than once; op_20 ("multiply") consumes [o_32] more than once; op_23 ("multiply") consumes [o_33] more than once; op_33 ("multiply") consumes [o_42] more than once
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
