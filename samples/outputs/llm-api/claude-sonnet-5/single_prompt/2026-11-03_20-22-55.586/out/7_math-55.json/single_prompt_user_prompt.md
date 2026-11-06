## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Geometry: cross product of two nearly parallel 3D vectors"
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
"name" : "v1.x"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9070467121"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v1.y"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4535233560"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v1.z"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v2.x"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9070467123"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v2.y"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4535233561"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v2.z"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v1.y·v2.z"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4535233560"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v1.z·v2.y"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4535233561"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cross product x"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v1.z·v2.x"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9070467123"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v1.x·v2.z"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9070467121"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cross product y"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v1.x·v2.y"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.113668690110625E+19"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "v1.y·v2.x"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.113668690110625E+19"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cross product z"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E+4"
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
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_4"
}, {
"key" : "b",
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_3",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_4",
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
"value" : "i_7"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_11"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "i_6"
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
"value" : "i_3"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b",
"value" : "o_15"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_16"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7]
- **Leaf variable IDs**: [o_10, o_13, o_16]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3, i_4, i_5, i_6, i_7]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
