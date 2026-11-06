## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Loan: 6-month amortization"
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
"name" : "Monthly payment"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Monthly period rate (0.4%)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.004"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Extra principal prepayment (month 4)"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Starting principal"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "240000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest accrued [Month 1]"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "960.00000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Principal portion [Month 1]"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1040.00000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after payment [Month 1]"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "238960.00000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Escrow collected [Month 1]"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "400.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest accrued [Month 2]"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "955.84000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Principal portion [Month 2]"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1044.16000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after payment [Month 2]"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "237915.84000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Escrow collected [Month 2]"
},
"id" : "i_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "400.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest accrued [Month 3]"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "9.5E+2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Principal portion [Month 3]"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1050.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after payment [Month 3]"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "236865.84000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Escrow collected [Month 3]"
},
"id" : "i_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "400.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest accrued [Month 4]"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "947.46336000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Principal portion [Month 4]"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1052.53664000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after payment [Month 4]"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "235813.3033600000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after prepayment [Month 4]"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "230813.3033600000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Escrow collected [Month 4]"
},
"id" : "i_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "400.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest accrued [Month 5]"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "923.2532134400000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Principal portion [Month 5]"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1076.746786560000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Balance after payment [Month 5]"
},
"id" : "o_26",
"valueClass" : "java.math.BigDecimal"
},
"value" : "229736.5565734400"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Escrow collected [Month 5]"
},
"id" : "i_27",
"valueClass" : "java.math.BigDecimal"
},
"value" : "400.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Interest accrued [Month 6]"
},
"id" : "o_28",
"valueClass" : "java.math.BigDecimal"
},
"value" : "918.9462262937600"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Principal portion [Month 6]"
},
"id" : "o_29",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1081.053773706240"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Ending balance"
},
"id" : "o_30",
"valueClass" : "java.math.BigDecimal"
},
"value" : "228655.5027997338"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Escrow collected [Month 6]"
},
"id" : "i_31",
"valueClass" : "java.math.BigDecimal"
},
"value" : "400.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total interest accrued"
},
"id" : "o_32",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5655.502799733760"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total escrow collected"
},
"id" : "o_33",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2400.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total scheduled payments (6 months)"
},
"id" : "o_34",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total amount paid by borrower"
},
"id" : "o_35",
"valueClass" : "java.math.BigDecimal"
},
"value" : "19400.00"
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
"value" : "i_6"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_6"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
}, {
"key" : "b",
"value" : "i_4"
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
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_6",
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_2"
} ],
"resultId" : "o_15"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "o_15"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
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
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_17"
}, {
"key" : "b",
"value" : "i_4"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "o_19"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_12",
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_13",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_21"
}, {
"key" : "b",
"value" : "i_5"
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
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b",
"value" : "i_4"
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
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "o_24"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
}, {
"key" : "b",
"value" : "o_25"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_26"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_17",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_28"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_18",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "o_28"
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
"id" : "op_19",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_26"
}, {
"key" : "b",
"value" : "o_29"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_30"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_20",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b0",
"value" : "o_11"
}, {
"key" : "b1",
"value" : "o_15"
}, {
"key" : "b2",
"value" : "o_19"
}, {
"key" : "b3",
"value" : "o_24"
}, {
"key" : "b4",
"value" : "o_28"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_32"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_21",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_10"
}, {
"key" : "b0",
"value" : "i_14"
}, {
"key" : "b1",
"value" : "i_18"
}, {
"key" : "b2",
"value" : "i_23"
}, {
"key" : "b3",
"value" : "i_27"
}, {
"key" : "b4",
"value" : "i_31"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_33"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_22",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b0",
"value" : "i_3"
}, {
"key" : "b1",
"value" : "i_3"
}, {
"key" : "b2",
"value" : "i_3"
}, {
"key" : "b3",
"value" : "i_3"
}, {
"key" : "b4",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_34"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_23",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_34"
}, {
"key" : "b0",
"value" : "o_33"
}, {
"key" : "b1",
"value" : "i_5"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_35"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_10, i_14, i_18, i_23, i_27, i_31]
- **Leaf variable IDs**: [o_30, o_32, o_35]
- **Variable IDs consumed as an argument by more than one operation**: [i_3, i_4, i_5, i_6, o_7, o_9, o_11, o_13, o_15, o_17, o_19, o_22, o_24, o_26, o_28]
- **Operations that consume the same variable more than once**: op_22 ("addBulk") consumes [i_3] more than once
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
