## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Free fall: drop from height"
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
"name" : "Drop height, h (m)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gravitational acceleration, g (m/s²)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Object mass, m (kg)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Constant 2"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Constant 0.5"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2 × h (m)"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "200"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2h / g (s²)"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20.40816326530612"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Time to fall, t (s)"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.517539514526256"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2h × g (m²/s²)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1960.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Impact velocity, v (m/s)"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "44.27188724235731"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Impact velocity cross-check, g × t (m/s)"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "44.27188724235731"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v² (m²/s²)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1960.000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "0.5 × m (kg)"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.25"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Kinetic energy at impact, KE (J)"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2450.000000000000"
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
"value" : "i_2"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
"value" : "i_3"
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
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
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
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
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
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "o_9"
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
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_11"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
}, {
"key" : "b",
"value" : "i_4"
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
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b",
"value" : "o_13"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_15"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6]
- **Leaf variable IDs**: [o_12, o_15]
- **Variable IDs consumed as an argument by more than one operation**: [i_3, o_7]
- **Operations that consume the same variable more than once**: op_7 ("multiply") consumes [o_11] more than once
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
