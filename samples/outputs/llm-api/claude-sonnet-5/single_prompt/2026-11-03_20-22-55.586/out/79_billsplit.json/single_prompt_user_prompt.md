## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Bill split: restaurant group check"
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
"name" : "Entree 1"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "24.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Entree 2"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "19.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Shared appetizer"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Shared dessert"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Pre-tax subtotal"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "64.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sales tax rate (8%)"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.08"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sales tax"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.1600"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tip rate (20%)"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tip (on pre-tax subtotal)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.9000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Grand total"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "82.5600"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Diner count"
},
"id" : "i_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Per-diner share"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20.6400"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b0",
"value" : "i_3"
}, {
"key" : "b1",
"value" : "i_4"
}, {
"key" : "b2",
"value" : "i_5"
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
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
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
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
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
"value" : "o_6"
}, {
"key" : "b0",
"value" : "o_8"
}, {
"key" : "b1",
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
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_11"
}, {
"key" : "b",
"value" : "i_12"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_13"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_7, i_9, i_12]
- **Leaf variable IDs**: [o_13]
- **Variable IDs consumed as an argument by more than one operation**: [o_6]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
