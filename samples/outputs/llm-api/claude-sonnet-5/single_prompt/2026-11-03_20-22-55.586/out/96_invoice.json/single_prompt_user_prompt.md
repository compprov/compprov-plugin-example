## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Invoice: 2/10 net 30 settlement"
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
"name" : "Invoice subtotal (as billed)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "48000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity invoiced"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "400"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity received (goods receipt)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "398"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Unit price"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "120.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quantity variance (invoiced − received)"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Variance adjustment amount"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "240.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Adjusted subtotal (after three-way match)"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "47760.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tax rate (7%)"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.07"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tax"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3343.2000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Adjusted subtotal plus tax"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "51103.2000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Early-payment discount rate (2%)"
},
"id" : "i_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.02"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Early-payment discount"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "955.2000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Amount remitted"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "50148.0000"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
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
"value" : "i_5"
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
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_11"
}, {
"key" : "b",
"value" : "o_13"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_14"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_9, i_12]
- **Leaf variable IDs**: [o_14]
- **Variable IDs consumed as an argument by more than one operation**: [o_8]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
