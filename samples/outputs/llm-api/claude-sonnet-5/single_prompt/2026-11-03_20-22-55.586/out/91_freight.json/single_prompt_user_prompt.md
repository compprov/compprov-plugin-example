## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Freight: multi-package shipment cost"
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
"name" : "Total shipment weight (lbs)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "620"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 1 ceiling (100 lbs)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 2 ceiling (500 lbs)"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "500"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 1 rate ($0.85/lb)"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.85"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 2 rate ($0.60/lb)"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 3 rate ($0.40/lb)"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.40"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Weight through tier 1"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Weight through tier 2"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "500"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 2 weight portion"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "400"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 3 weight portion (uncapped)"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "120"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 3 weight portion (floored)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "120"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 1 cost"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "85.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 2 cost"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "240.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Tier 3 cost"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "48.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total weight-based cost"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "373.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Shipment distance (miles)"
},
"id" : "i_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "450"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Distance surcharge rate ($0.15/mile)"
},
"id" : "i_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.15"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Distance surcharge"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "67.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Pre-fuel subtotal"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "440.50"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Fuel surcharge rate (9%)"
},
"id" : "i_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.09"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Fuel surcharge"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "39.6450"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total freight cost"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "480.1450"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
}, {
"key" : "b",
"value" : "o_20"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_21"
}, {
"key" : "b",
"value" : "i_22"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_21"
}, {
"key" : "b",
"value" : "o_23"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_24"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_8, i_18, i_19, i_22]
- **Leaf variable IDs**: [o_24]
- **Variable IDs consumed as an argument by more than one operation**: [i_3, o_9, o_10, o_21]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
