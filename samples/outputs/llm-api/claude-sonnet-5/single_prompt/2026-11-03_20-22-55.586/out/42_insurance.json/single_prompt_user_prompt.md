## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Insurance: claims adjudication"
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
"name" : "Zero (claim floor)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Collision"
} ],
"name" : "Claim amount"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "8000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Collision"
} ],
"name" : "Deductible"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "500.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Collision"
} ],
"name" : "Coinsurance rate"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.80"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Collision"
} ],
"name" : "Policy limit"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "10000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Collision: net of deductible"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7500.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Collision: net of deductible (floored)"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7500.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Collision: coinsurance amount"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6000.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Collision claim payout"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6000.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Comprehensive"
} ],
"name" : "Claim amount"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Comprehensive"
} ],
"name" : "Deductible"
},
"id" : "i_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Comprehensive"
} ],
"name" : "Coinsurance rate"
},
"id" : "i_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.90"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Comprehensive"
} ],
"name" : "Policy limit"
},
"id" : "i_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Comprehensive: net of deductible"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "19000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Comprehensive: net of deductible (floored)"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "19000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Comprehensive: coinsurance amount"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "17100.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Comprehensive claim payout"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Liability"
} ],
"name" : "Claim amount"
},
"id" : "i_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Liability"
} ],
"name" : "Deductible"
},
"id" : "i_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "250.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Liability"
} ],
"name" : "Coinsurance rate"
},
"id" : "i_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "claimType",
"value" : "Liability"
} ],
"name" : "Policy limit"
},
"id" : "i_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Liability: net of deductible"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4750.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Liability: net of deductible (floored)"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4750.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Liability: coinsurance amount"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4750.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Liability claim payout"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4750.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total payout"
},
"id" : "o_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "18000.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Reinsurance recovery rate (40%)"
},
"id" : "i_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.40"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Reinsurance recovery"
},
"id" : "o_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "7200.000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Net loss"
},
"id" : "o_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "10800.000000"
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
"resultId" : "o_7"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
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
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
}, {
"key" : "b",
"value" : "i_5"
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
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
}, {
"key" : "b",
"value" : "i_6"
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
"id" : "op_5",
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
"resultId" : "o_15"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_15"
}, {
"key" : "b",
"value" : "i_2"
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
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
"value" : "i_13"
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
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
}, {
"key" : "b",
"value" : "i_14"
} ],
"resultId" : "o_18"
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
"value" : "i_19"
}, {
"key" : "b",
"value" : "i_20"
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
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_23"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_24"
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
"value" : "o_24"
}, {
"key" : "b",
"value" : "i_21"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_25"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_25"
}, {
"key" : "b",
"value" : "i_22"
} ],
"resultId" : "o_26"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b0",
"value" : "o_18"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_27"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_27"
}, {
"key" : "b",
"value" : "i_28"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_29"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_27"
}, {
"key" : "b",
"value" : "o_29"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_30"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_11, i_12, i_13, i_14, i_19, i_20, i_21, i_22, i_28]
- **Leaf variable IDs**: [o_26, o_30]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, o_27]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
