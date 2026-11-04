## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "ODE: Runge-Kutta initial step size from error scale"
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
"name" : "Initial state, y0"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-360.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Absolute tolerance"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1E-9"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Relative tolerance"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1E-9"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "|y0|"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "360.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Relative tolerance · |y0|"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.600E-7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Error scale"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.610E-7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial time, t0"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Coefficient, 3"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Step fraction, 0.01"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.01"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t0): t⁵"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t0): 3t⁵"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t0)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "360.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y0 / scale"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-997229916.8975069"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖y0 / scale‖²"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.944675071554085E+17"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t0) / scale"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "997229916.8975069"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖y'(t0) / scale‖²"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.944675071554085E+17"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Norm ratio²"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Norm ratio"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Rough step guess"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.01"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Euler increment"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.600"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Euler state, y1"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-356.400"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Euler time, t1"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.01"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t1): t⁵"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1E-10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t1): 3t⁵"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3E-10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t1)"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "356.4000000003"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Derivative change"
},
"id" : "o_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-3.5999999997"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Derivative change / scale"
},
"id" : "o_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-9972299.168144044"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖Derivative change / scale‖²"
},
"id" : "o_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "99446750698966.39"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖Derivative change / scale‖"
},
"id" : "o_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9972299.168144044"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖y'' / scale‖"
},
"id" : "o_31",
"valueClass" : "java.math.BigDecimal"
},
"value" : "997229916.8144044"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖y'(t0) / scale‖"
},
"id" : "o_32",
"valueClass" : "java.math.BigDecimal"
},
"value" : "997229916.8975069"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Largest scaled derivative"
},
"id" : "o_33",
"valueClass" : "java.math.BigDecimal"
},
"value" : "997229916.8975069"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Integrator order"
},
"id" : "i_34",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "0.01 / largest scaled derivative"
},
"id" : "o_35",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.002777777777778E-11"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ln(0.01 / largest scaled derivative)"
},
"id" : "o_36",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-25.325662096051776"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ln(...) / order"
},
"id" : "o_37",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-3.165707762006472"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Order-root step estimate"
},
"id" : "o_38",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.042184274819003574"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Growth limit, 100"
},
"id" : "i_39",
"valueClass" : "java.math.BigDecimal"
},
"value" : "100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cancellation guard, 1e-12"
},
"id" : "i_40",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1E-12"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Minimum step"
},
"id" : "i_41",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Maximum step"
},
"id" : "i_42",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "|Rough step guess|"
},
"id" : "o_43",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.01"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Growth-limited step"
},
"id" : "o_44",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Step before guards"
},
"id" : "o_45",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.042184274819003574"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "|t0|"
},
"id" : "o_46",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cancellation floor"
},
"id" : "o_47",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E-12"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Step after cancellation guard"
},
"id" : "o_48",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.042184274819003574"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Step after minimum bound"
},
"id" : "o_49",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.042184274819003574"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial step size"
},
"id" : "o_50",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.042184274819003574"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_5"
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
"value" : "i_4"
}, {
"key" : "b",
"value" : "o_5"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "o_6"
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
"value" : "(a*b0*...*bn)mc"
} ],
"name" : "multiplyBulk"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
}, {
"key" : "b0",
"value" : "i_8"
}, {
"key" : "b1",
"value" : "i_8"
}, {
"key" : "b2",
"value" : "i_8"
}, {
"key" : "b3",
"value" : "i_8"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
}, {
"key" : "b",
"value" : "o_11"
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
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "o_7"
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
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b",
"value" : "o_14"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "o_7"
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
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_15"
}, {
"key" : "b",
"value" : "o_17"
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"id" : "op_13",
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
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_20"
}, {
"key" : "b",
"value" : "o_13"
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
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
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
"value" : "(a*b0*...*bn)mc"
} ],
"name" : "multiplyBulk"
},
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_23"
}, {
"key" : "b0",
"value" : "o_23"
}, {
"key" : "b1",
"value" : "o_23"
}, {
"key" : "b2",
"value" : "o_23"
}, {
"key" : "b3",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_25"
}, {
"key" : "b",
"value" : "o_22"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_20",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "b",
"value" : "o_13"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_21",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_27"
}, {
"key" : "b",
"value" : "o_7"
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
"id" : "op_22",
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_23",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_24",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_30"
}, {
"key" : "b",
"value" : "o_20"
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_25",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_26",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_32"
}, {
"key" : "b",
"value" : "o_31"
} ],
"resultId" : "o_33"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_27",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_10"
}, {
"key" : "b",
"value" : "o_33"
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
"value" : "Ln(a)"
} ],
"name" : "Ln_double"
},
"id" : "op_28",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_35"
} ],
"resultId" : "o_36"
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
"value" : "o_36"
}, {
"key" : "b",
"value" : "i_34"
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
"value" : "Exp(a)"
} ],
"name" : "Exp_double"
},
"id" : "op_30",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_37"
} ],
"resultId" : "o_38"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_31",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_20"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_32",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_39"
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
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_33",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_44"
}, {
"key" : "b",
"value" : "o_38"
} ],
"resultId" : "o_45"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_34",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
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
"id" : "op_35",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_40"
}, {
"key" : "b",
"value" : "o_46"
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
"value" : "max(a,b)"
} ],
"name" : "max"
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
"id" : "op_37",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_48"
}, {
"key" : "b",
"value" : "i_41"
} ],
"resultId" : "o_49"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_38",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_49"
}, {
"key" : "b",
"value" : "i_42"
} ],
"resultId" : "o_50"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_8, i_9, i_10, i_34, i_39, i_40, i_41, i_42]
- **Leaf variable IDs**: [o_50]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, o_7, i_8, i_9, i_10, o_13, o_17, o_20]
- **Operations that consume the same variable more than once**: op_4 ("multiplyBulk") consumes [i_8] more than once; op_8 ("multiply") consumes [o_14] more than once; op_10 ("multiply") consumes [o_16] more than once; op_17 ("multiplyBulk") consumes [o_23] more than once; op_22 ("multiply") consumes [o_28] more than once
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
