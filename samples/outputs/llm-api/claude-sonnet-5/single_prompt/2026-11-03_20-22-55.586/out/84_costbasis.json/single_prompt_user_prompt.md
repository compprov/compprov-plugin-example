## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Cost basis: weighted-average across 3 lots, partial sale"
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
"name" : "Lot 1 shares"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 1 price per share"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "42.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 1 cost"
},
"id" : "o_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4250.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 2 shares"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "150"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 2 price per share"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "38.25"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 2 cost"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5737.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 3 shares"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "75"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 3 price per share"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "51.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 3 cost"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3825.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total shares held"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "325"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total cost"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "13812.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Weighted-average cost per share"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "42.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Shares sold"
},
"id" : "i_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "120"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sale price per share"
},
"id" : "i_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "47.75"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sale proceeds"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5730.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cost basis of shares sold"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5100.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Realized gain/loss"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "630.00"
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
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b0",
"value" : "i_5"
}, {
"key" : "b1",
"value" : "i_8"
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
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_5",
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
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
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
"id" : "op_7",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_14"
}, {
"key" : "b",
"value" : "o_13"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
"value" : "o_17"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_18"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_5, i_6, i_8, i_9, i_14, i_15]
- **Leaf variable IDs**: [o_18]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_5, i_8, i_14]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
