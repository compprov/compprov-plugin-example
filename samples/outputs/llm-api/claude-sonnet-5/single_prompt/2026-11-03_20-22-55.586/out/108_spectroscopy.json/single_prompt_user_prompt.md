## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Spectroscopy: Beer-Lambert calibration series"
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
"name" : "Molar absorptivity, ε (L/(mol·cm))"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "15000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Path length, l (cm)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "point",
"value" : "1"
} ],
"name" : "Concentration (mol/L)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00001"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ε × c (point 1)"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.15000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "point",
"value" : "1"
} ],
"name" : "Absorbance, A1"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.1500000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "point",
"value" : "2"
} ],
"name" : "Concentration (mol/L)"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00002"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ε × c (point 2)"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.30000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "point",
"value" : "2"
} ],
"name" : "Absorbance, A2"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.3000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "point",
"value" : "3"
} ],
"name" : "Concentration (mol/L)"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00004"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ε × c (point 3)"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.60000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "point",
"value" : "3"
} ],
"name" : "Absorbance, A3"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.6000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Absorbance ratio A2/A1 (expect ≈ c2/c1)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Absorbance ratio A3/A1 (expect ≈ c3/c1)"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4"
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
"value" : "i_4"
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
"value" : "o_5"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "i_7"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
}, {
"key" : "b",
"value" : "i_3"
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
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_11"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
}, {
"key" : "b",
"value" : "o_6"
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
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "o_6"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_14"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_7, i_10]
- **Leaf variable IDs**: [o_13, o_14]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3, o_6]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
