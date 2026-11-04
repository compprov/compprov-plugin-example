## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Physics: 1-D elastic collision"
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
"name" : "Mass 1, m1 (kg)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial velocity 1, v1 (m/s)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Mass 2, m2 (kg)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial velocity 2, v2 (m/s)"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-2.0"
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
"name" : "Total mass, m1 + m2 (kg)"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "m1 - m2 (kg)"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-2.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "(m1 - m2) × v1"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-8.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2 × m2 (kg)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "10.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2m2 × v2"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-20.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Numerator for v1'"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-28.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Final velocity, v1' (m/s)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-3.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "m2 - m1 (kg)"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "(m2 - m1) × v2"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-4.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2 × m1 (kg)"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2m1 × v1"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "24.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Numerator for v2'"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Final velocity, v2' (m/s)"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "m1 × v1 (before)"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "m2 × v2 (before)"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-10.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total momentum before collision (kg·m/s)"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "m1 × v1' (after)"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-10.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "m2 × v2' (after)"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total momentum after collision (kg·m/s)"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.00"
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
"value" : "i_4"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_2",
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
"id" : "op_3",
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
"id" : "op_4",
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
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
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
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "o_7"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_4"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "i_5"
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
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
}, {
"key" : "b",
"value" : "i_2"
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
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_12",
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_18"
}, {
"key" : "b",
"value" : "o_7"
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
"id" : "op_14",
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
"id" : "op_15",
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
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_20"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "o_13"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_4"
}, {
"key" : "b",
"value" : "o_19"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_23"
}, {
"key" : "b",
"value" : "o_24"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_25"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6]
- **Leaf variable IDs**: [o_22, o_25]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3, i_4, i_5, i_6, o_7]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
