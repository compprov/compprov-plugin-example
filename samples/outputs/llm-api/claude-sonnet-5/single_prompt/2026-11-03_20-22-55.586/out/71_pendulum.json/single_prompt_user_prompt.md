## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Pendulum: small-angle period"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Computation precision"
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
"name" : "Computation precision"
},
"id" : "i_2",
"valueClass" : "java.math.MathContext"
},
"value" : {
"precision" : 3,
"roundingMode" : "DOWN"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Pendulum length, L (m)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gravitational acceleration, g (m/s²)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Constant π"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.14159265358979"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Constant 2"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Constant 1"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "L / g (s²)"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.2040816326530612"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "sqrt(L/g) (s)"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.451"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2π"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.28318530717958"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Period, T (s)"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.833716573537991"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Frequency, f (Hz)"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.3528934436627394"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Angular frequency, ω = 2π/T (rad/s)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.217294900221729"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "g / L (1/s²)"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.9"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Angular frequency cross-check, sqrt(g/L) (rad/s)"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.213594362117866"
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
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_4"
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
}, {
"key" : "mc",
"value" : "i_2"
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
"value" : "i_6"
}, {
"key" : "b",
"value" : "i_5"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_9"
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
"value" : "i_7"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_11"
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
"value" : "i_4"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_15"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7]
- **Leaf variable IDs**: [o_12, o_13, o_15]
- **Variable IDs consumed as an argument by more than one operation**: [i_3, i_4, o_10, o_11]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
