## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Linear algebra: L-infinity norm of a vector"
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
"name" : "Initial maximum, 0"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Vector component"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Absolute component"
},
"id" : "o_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Larger of maximum and component"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[0] Running maximum"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Vector component"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Absolute component"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Larger of maximum and component"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[1] Running maximum"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Vector component"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Absolute component"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Larger of maximum and component"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[2] Running maximum"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "16"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Vector component"
},
"id" : "i_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Absolute component"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Larger of maximum and component"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "16"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[3] Running maximum"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "32"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Vector component"
},
"id" : "i_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-6"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Absolute component"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Larger of maximum and component"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "32"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[4] Running maximum"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "64"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Vector component"
},
"id" : "i_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Absolute component"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "[5] Larger of maximum and component"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "64"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "L-infinity norm"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "128"
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
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_4"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "o_4"
} ],
"resultId" : "o_5"
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
"value" : "i_2"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
}, {
"key" : "b",
"value" : "o_8"
} ],
"resultId" : "o_9"
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
"value" : "o_6"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_11"
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_12"
} ],
"resultId" : "o_13"
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
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_13"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_15"
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b",
"value" : "o_16"
} ],
"resultId" : "o_17"
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
"value" : "o_14"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_19"
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_18"
}, {
"key" : "b",
"value" : "o_20"
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
"value" : "o_18"
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
"value" : "|a|mc"
} ],
"name" : "abs"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_23"
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b",
"value" : "o_24"
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
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b",
"value" : "o_25"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_26"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_7, i_11, i_15, i_19, i_23]
- **Leaf variable IDs**: [o_26]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, o_6, o_10, o_14, o_18, o_22]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
