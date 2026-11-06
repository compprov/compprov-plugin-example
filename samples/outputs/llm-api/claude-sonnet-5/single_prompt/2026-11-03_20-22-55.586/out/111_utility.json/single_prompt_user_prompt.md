## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Utility: tiered residential electricity bill"
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
"name" : "Zero (tier floor)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Monthly usage (kWh)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1180"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 1 ceiling (500 kWh)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "500"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 2 ceiling (1000 kWh)"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 1 rate ($0.10/kWh)"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 2 rate ($0.14/kWh)"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.14"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 3 rate ($0.18/kWh)"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.18"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Usage through tier 1"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "500"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Usage through tier 2"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 2 usage portion"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "500"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 3 usage portion (uncapped)"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "180"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 3 usage portion (floored)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "180"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 1 cost"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "50.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 2 cost"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "70.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 3 cost"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "32.40"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total energy cost"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "152.40"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Monthly service charge"
},
"id" : "i_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total bill"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "164.90"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
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
} ],
"resultId" : "o_9"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_5"
} ],
"resultId" : "o_10"
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
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_9"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "i_2"
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
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
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
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_11"
}, {
"key" : "b",
"value" : "i_7"
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
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "i_8"
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
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b0",
"value" : "o_15"
}, {
"key" : "b1",
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_10",
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
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_8, i_18]
- **Leaf variable IDs**: [o_19]
- **Variable IDs consumed as an argument by more than one operation**: [i_3, o_9, o_10]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
