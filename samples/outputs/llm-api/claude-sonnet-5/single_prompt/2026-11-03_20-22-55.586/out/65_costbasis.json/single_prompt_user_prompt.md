## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Trade log #345"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Computation precision"
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
"name" : "Computation precision"
},
"id" : "i_2",
"valueClass" : "java.math.MathContext"
},
"value" : {
"precision" : 2,
"roundingMode" : "DOWN"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 1"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "45.678912"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 1 shares"
},
"id" : "o_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "45.67"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 1 price per share"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "210.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 1 cost"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9613.5350"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 2"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "67.234156"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 2 shares"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "67.23"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 2 price per share"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "340.75"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 2 cost"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "22908.6225"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 3"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "22.891347"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 3 shares"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "22.89"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 3 price per share"
},
"id" : "i_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1850.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lot 3 cost"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "42346.5000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total shares held"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "135.79"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total cost"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "74868.6575"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Weighted-average cost per share"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "551.3561933868473"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Shares sold"
},
"id" : "i_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sale price per share"
},
"id" : "i_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "650.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Sale proceeds"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "39000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cost basis of shares sold"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "33081.37160321084"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Realized gain/loss"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5918.62839678916"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_2"
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
"value" : "o_4"
}, {
"key" : "b",
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
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
}, {
"key" : "mc",
"value" : "i_2"
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
"value" : "setScale(a)mc"
} ],
"name" : "setScale"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_11"
}, {
"key" : "mc",
"value" : "i_2"
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
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "i_13"
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
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_4"
}, {
"key" : "b0",
"value" : "o_8"
}, {
"key" : "b1",
"value" : "o_12"
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
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
}, {
"key" : "b0",
"value" : "o_10"
}, {
"key" : "b1",
"value" : "o_14"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
"value" : "o_15"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_18"
}, {
"key" : "b",
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
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_18"
}, {
"key" : "b",
"value" : "o_17"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_12",
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
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_5, i_7, i_9, i_11, i_13, i_18, i_19]
- **Leaf variable IDs**: [o_22]
- **Variable IDs consumed as an argument by more than one operation**: [o_4, o_8, o_12, i_18]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
