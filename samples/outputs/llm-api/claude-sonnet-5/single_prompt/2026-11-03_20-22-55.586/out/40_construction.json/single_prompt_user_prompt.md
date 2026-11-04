## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Construction: residential project bid"
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
"name" : "Lumber"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "18500.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Concrete"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9200.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Roofing materials"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6300.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Electrical materials"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4100.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total materials cost"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "31800.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Carpentry hours"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "180"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Carpentry rate ($/hr)"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "55.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Carpentry labor cost"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9900.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Electrical hours"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Electrical rate ($/hr)"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "75.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Electrical labor cost"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4500.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total labor cost"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "14400.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Direct cost (materials + labor)"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "46200.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Overhead rate (10%)"
},
"id" : "i_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Overhead"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4620.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Cost including overhead"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "50820.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "basis",
"value" : "markup-on-cost-including-overhead"
} ],
"name" : "Profit margin rate (15%)"
},
"id" : "i_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.15"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Profit margin"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7623.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total bid price"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "58443.000000"
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
"value" : "i_7"
}, {
"key" : "b",
"value" : "i_8"
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
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_10"
}, {
"key" : "b",
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
}, {
"key" : "b",
"value" : "i_18"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
}, {
"key" : "b",
"value" : "o_19"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_20"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_7, i_8, i_10, i_11, i_15, i_18]
- **Leaf variable IDs**: [i_4, o_20]
- **Variable IDs consumed as an argument by more than one operation**: [o_14, o_17]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
