## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Charting: gray level for a paint-scale lookup"
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
"name" : "Truncate to integer ((int) cast)"
},
"id" : "i_2",
"valueClass" : "java.math.MathContext"
},
"value" : {
"precision" : 0,
"roundingMode" : "DOWN"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lookup value"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-0.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lower bound"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Upper bound"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Maximum gray level, 255"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "255"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Value raised to lower bound"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Clamped value"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Scale range, upper − lower"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Offset from lower bound"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-0.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Relative position in range"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-0.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Scaled gray level"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-127.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gray level, g"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-127"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
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
} ],
"resultId" : "o_7"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
"value" : "i_5"
} ],
"resultId" : "o_8"
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
"value" : "i_5"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_4",
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
"id" : "op_5",
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
"value" : "i_6"
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
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "mc",
"value" : "i_2"
} ],
"resultId" : "o_13"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6]
- **Leaf variable IDs**: [o_8, o_13]
- **Variable IDs consumed as an argument by more than one operation**: [i_3, i_4, i_5]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
