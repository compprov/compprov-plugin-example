## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Orbital mechanics: Kepler's third law"
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
"name" : "Orbital radius, r (m)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "42164000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gravitational constant, G (N·m²/kg²)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.674E-11"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Central body mass, M (kg)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5972000000000000000000000"
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
"name" : "Seconds per hour"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3600"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "G × M, standard gravitational parameter (m³/s²)"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "398571280000000.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Exponent 3 (cubic)"
},
"id" : "i_9",
"valueClass" : "java.lang.Integer"
},
"value" : 3
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "r³ (m³)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7.495928130694400E+22"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "r³ / (GM) (s²)"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "188069951.5201998"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "sqrt(r³/(GM)) (s)"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "13713.85983303752"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2π"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.28318530717958"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Orbital period, T (s)"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "86166.72260766155"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Orbital period, T (hours)"
},
"id" : "i_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "23.935"
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
"value" : "(a^n)mc"
} ],
"name" : "pow"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "n",
"value" : "i_9"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_8"
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_5",
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
"value" : "o_12"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_14"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_9, i_15]
- **Leaf variable IDs**: [i_7, o_14, i_15]
- **Variable IDs consumed as an argument by more than one operation**: [] (none detected)
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
