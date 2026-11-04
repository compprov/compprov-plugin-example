## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Electrical circuit: series-parallel resistor network"
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
"name" : "Resistor R1, series (Ω)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Resistor R2, series (Ω)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "150"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Resistor R3, parallel (Ω)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "300"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Resistor R4, parallel (Ω)"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "600"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Supply voltage (V)"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "120"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Series resistance, R1 + R2 (Ω)"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "250"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "R3 × R4"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "180000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "R3 + R4 (Ω)"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "900"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Parallel resistance, Rparallel (Ω)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "200"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total resistance, Rtotal (Ω)"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "450"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total current, I (A)"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.2666666666666667"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total power dissipated, P = I × V (W)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "32.00000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Exponent 2 (square)"
},
"id" : "i_14",
"valueClass" : "java.lang.Integer"
},
"value" : 2
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "I² (A²)"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.07111111111111113"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total power cross-check, P = I² × Rtotal (W)"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "32.00000000000001"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
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
"value" : "i_4"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_3",
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
"resultId" : "o_9"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "i_6"
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
"value" : "(a^n)mc"
} ],
"name" : "pow"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "n",
"value" : "i_14"
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
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_15"
}, {
"key" : "b",
"value" : "o_11"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_16"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_14]
- **Leaf variable IDs**: [o_13, o_16]
- **Variable IDs consumed as an argument by more than one operation**: [i_4, i_5, i_6, o_11, o_12]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
