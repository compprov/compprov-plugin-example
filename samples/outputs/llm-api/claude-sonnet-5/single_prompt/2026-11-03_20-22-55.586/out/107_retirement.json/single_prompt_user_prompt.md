## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Retirement: 6-year annuity projection"
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
"name" : "Annual contribution"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Annual growth rate (7%)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.07"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance (start)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest earned [Year 1]"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after interest [Year 1]"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after contribution [Year 1]"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest earned [Year 2]"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "420.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after interest [Year 2]"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6420.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after contribution [Year 2]"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12420.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest earned [Year 3]"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "869.400000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after interest [Year 3]"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "13289.400000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after contribution [Year 3]"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "19289.400000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest earned [Year 4]"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1350.25800000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after interest [Year 4]"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20639.65800000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after contribution [Year 4]"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "26639.65800000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest earned [Year 5]"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1864.7760600000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after interest [Year 5]"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "28504.4340600000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after contribution [Year 5]"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "34504.4340600000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest earned [Year 6]"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2415.310384200000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after interest [Year 6]"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "36919.74444420000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Ending balance"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "42919.74444420000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Contribution years"
},
"id" : "i_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total contributions"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "36000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total growth (interest earned)"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6919.74444420000"
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
"value" : "i_4"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_4"
}, {
"key" : "b",
"value" : "o_5"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
}, {
"key" : "b",
"value" : "i_2"
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
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
"value" : "o_8"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
}, {
"key" : "b",
"value" : "i_2"
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
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_11"
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
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "i_2"
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
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "i_3"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "o_14"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_12",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_15"
}, {
"key" : "b",
"value" : "i_2"
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
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
"value" : "i_3"
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
"id" : "op_14",
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
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_18"
}, {
"key" : "b",
"value" : "i_2"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_19"
}, {
"key" : "b",
"value" : "i_3"
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
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_19"
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
"value" : "(a+b)mc"
} ],
"name" : "add"
},
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_21"
}, {
"key" : "b",
"value" : "i_2"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_22"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "i_23"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_24"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_20",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b",
"value" : "o_24"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_25"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_23]
- **Leaf variable IDs**: [o_25]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3, i_4, o_7, o_10, o_13, o_16, o_19]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
