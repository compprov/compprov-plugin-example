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
"name" : "Error scale"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1E-9"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial time, t0"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Coefficient, 3"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Step fraction, 0.01"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.01"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t0): t⁵"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t0): 3t⁵"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t0)"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "360.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y0 / scale"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-3.600E+11"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖y0 / scale‖²"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.2960000E+23"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t0) / scale"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.600E+11"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖y'(t0) / scale‖²"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.2960000E+23"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Norm ratio²"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Norm ratio"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Rough step guess"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.01"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Euler increment"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.600"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Euler state, y1"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-356.400"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Euler time, t1"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.01"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t1): t⁵"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1E-10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t1): 3t⁵"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3E-10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "y'(t1)"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "356.4000000003"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Derivative change"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-3.5999999997"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Derivative change / scale"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-3599999999.7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖Derivative change / scale‖²"
},
"id" : "o_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.295999999784000E+19"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖Derivative change / scale‖"
},
"id" : "o_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3599999999.7"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖y'' / scale‖"
},
"id" : "o_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.5999999997E+11"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "‖y'(t0) / scale‖"
},
"id" : "o_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.600E+11"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Largest scaled derivative"
},
"id" : "o_31",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.600E+11"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Integrator order"
},
"id" : "i_32",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "0.01 / largest scaled derivative"
},
"id" : "o_33",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.777777777777778E-14"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ln(0.01 / largest scaled derivative)"
},
"id" : "o_34",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-31.21454005438466"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ln(...) / order"
},
"id" : "o_35",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-3.901817506798082"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Order-root step estimate"
},
"id" : "o_36",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.02020515504676624"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Growth limit, 100"
},
"id" : "i_37",
"valueClass" : "java.math.BigDecimal"
},
"value" : "100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cancellation guard, 1e-12"
},
"id" : "i_38",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1E-12"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Minimum step"
},
"id" : "i_39",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.000001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Maximum step"
},
"id" : "i_40",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "|Rough step guess|"
},
"id" : "o_41",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.01"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Growth-limited step"
},
"id" : "o_42",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Step before guards"
},
"id" : "o_43",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.02020515504676624"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "|t0|"
},
"id" : "o_44",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cancellation floor"
},
"id" : "o_45",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E-12"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Step after cancellation guard"
},
"id" : "o_46",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.02020515504676624"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Step after minimum bound"
},
"id" : "o_47",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.02020515504676624"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial step size"
},
"id" : "o_48",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.02020515504676624"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(+a)mc"
} ],
"name" : "plus"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
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
"value" : "(a*b0*...*bn)mc"
} ],
"name" : "multiplyBulk"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
}, {
"key" : "b0",
"value" : "i_6"
}, {
"key" : "b1",
"value" : "i_6"
}, {
"key" : "b2",
"value" : "i_6"
}, {
"key" : "b3",
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "o_5"
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
"id" : "op_6",
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
"value" : "o_11"
}, {
"key" : "b",
"value" : "o_5"
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_18"
}, {
"key" : "b",
"value" : "o_11"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_13",
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
}, {
"key" : "b",
"value" : "o_18"
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
"value" : "(a*b0*...*bn)mc"
} ],
"name" : "multiplyBulk"
},
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_21"
}, {
"key" : "b0",
"value" : "o_21"
}, {
"key" : "b1",
"value" : "o_21"
}, {
"key" : "b2",
"value" : "o_21"
}, {
"key" : "b3",
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
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
}, {
"key" : "b",
"value" : "o_22"
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
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_23"
}, {
"key" : "b",
"value" : "o_20"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_24"
}, {
"key" : "b",
"value" : "o_11"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_25"
}, {
"key" : "b",
"value" : "o_5"
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
"id" : "op_20",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "b",
"value" : "o_26"
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_21",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_27"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_22",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_28"
}, {
"key" : "b",
"value" : "o_18"
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
"value" : "o_15"
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
"id" : "op_24",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_30"
}, {
"key" : "b",
"value" : "o_29"
} ],
"resultId" : "o_31"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_25",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
}, {
"key" : "b",
"value" : "o_31"
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
"value" : "Ln(a)"
} ],
"name" : "Ln_double"
},
"id" : "op_26",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_33"
} ],
"resultId" : "o_34"
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
"value" : "o_34"
}, {
"key" : "b",
"value" : "i_32"
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
"value" : "Exp(a)"
} ],
"name" : "Exp_double"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_29",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_18"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_30",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_37"
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
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_31",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_42"
}, {
"key" : "b",
"value" : "o_36"
} ],
"resultId" : "o_43"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_32",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_33",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_38"
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_34",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_43"
}, {
"key" : "b",
"value" : "o_45"
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
"id" : "op_35",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_46"
}, {
"key" : "b",
"value" : "i_39"
} ],
"resultId" : "o_47"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_36",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_47"
}, {
"key" : "b",
"value" : "i_40"
} ],
"resultId" : "o_48"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_6, i_7, i_8, i_32, i_37, i_38, i_39, i_40]
- **Leaf variable IDs**: [i_4, o_48]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, o_5, i_6, i_7, i_8, o_11, o_15, o_18]
- **Operations that consume the same variable more than once**: op_2 ("multiplyBulk") consumes [i_6] more than once; op_6 ("multiply") consumes [o_12] more than once; op_8 ("multiply") consumes [o_14] more than once; op_15 ("multiplyBulk") consumes [o_21] more than once; op_20 ("multiply") consumes [o_26] more than once
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
