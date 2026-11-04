## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Subscription: mid-cycle plan upgrade proration"
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
"name" : "Days in billing cycle"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "30"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Days remaining in cycle"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Old plan monthly price"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "29.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Old plan daily rate"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.9666666666666667"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Old plan refund (unused days)"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "11.60000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "New plan monthly price"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "79.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "New plan daily rate"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.633333333333333"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "New plan prorated charge (remaining days)"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "31.60000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Net charge (new prorated − old refund)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20.00000000000000"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_1",
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_5",
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
"resultId" : "o_10"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_7]
- **Leaf variable IDs**: [o_10]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
