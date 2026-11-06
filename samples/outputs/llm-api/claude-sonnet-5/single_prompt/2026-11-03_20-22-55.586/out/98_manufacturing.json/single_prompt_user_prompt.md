## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Manufacturing: BOM rollup and production run cost"
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
"name" : "Circuit board unit cost"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "14.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Circuit board qty/assembly"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Circuit board cost per assembly"
},
"id" : "o_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "14.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Enclosure unit cost"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.25"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Enclosure qty/assembly"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Enclosure cost per assembly"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.25"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Connector unit cost"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.85"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Connector qty/assembly"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Connector cost per assembly"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.40"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Fastener unit cost"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.05"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Fastener qty/assembly"
},
"id" : "i_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Fastener cost per assembly"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cable unit cost"
},
"id" : "i_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cable qty/assembly"
},
"id" : "i_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cable cost per assembly"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4.20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Material cost per unit"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "28.95"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Labor cost per unit"
},
"id" : "i_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Overhead cost per unit"
},
"id" : "i_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total unit cost"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "40.45"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Production run quantity"
},
"id" : "i_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total run cost"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "202250.00"
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
"resultId" : "o_4"
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
"value" : "i_5"
}, {
"key" : "b",
"value" : "i_6"
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
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
}, {
"key" : "b",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_11"
}, {
"key" : "b",
"value" : "i_12"
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
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_14"
}, {
"key" : "b",
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
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_4"
}, {
"key" : "b0",
"value" : "o_7"
}, {
"key" : "b1",
"value" : "o_10"
}, {
"key" : "b2",
"value" : "o_13"
}, {
"key" : "b3",
"value" : "o_16"
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
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
}, {
"key" : "b0",
"value" : "i_18"
}, {
"key" : "b1",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_20"
}, {
"key" : "b",
"value" : "i_21"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_22"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_5, i_6, i_8, i_9, i_11, i_12, i_14, i_15, i_18, i_19, i_21]
- **Leaf variable IDs**: [o_22]
- **Variable IDs consumed as an argument by more than one operation**: [] (none detected)
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
