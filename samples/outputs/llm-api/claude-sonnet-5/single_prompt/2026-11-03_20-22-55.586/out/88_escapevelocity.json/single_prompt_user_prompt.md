## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Orbital mechanics: escape velocity"
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
"name" : "Gravitational constant, G (N·m²/kg²)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.674E-11"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Planet mass, M (kg)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5972000000000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Planet radius, r (m)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6371000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Altitude above surface (m)"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "400000"
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
"name" : "G × M, standard gravitational parameter (m³/s²)"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "398571280000000.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2 × G × M"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "797142560000000.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2GM / r at surface (m²/s²)"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "125120477.1621410"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Escape velocity at surface (m/s)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "11185.72649237147"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Radius at altitude, r + altitude (m)"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6771000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2GM / r at altitude (m²/s²)"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "117728926.3033525"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Escape velocity at altitude (m/s)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "10850.29613897024"
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
"value" : "i_3"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
}, {
"key" : "b",
"value" : "o_7"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
}, {
"key" : "b",
"value" : "i_4"
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_4"
}, {
"key" : "b",
"value" : "i_5"
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
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
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
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_13"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6]
- **Leaf variable IDs**: [o_10, o_13]
- **Variable IDs consumed as an argument by more than one operation**: [i_4, o_8]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
