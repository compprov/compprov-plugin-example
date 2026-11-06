## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Payroll: biweekly net pay"
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
"name" : "Base salary"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3500.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Bonus"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "500.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gross pay"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "401k contribution"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "200.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Health premium"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "150.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Pretax deductions"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "350.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Taxable income"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3650.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Bracket 1 ceiling"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Bracket 1 rate (10%)"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Bracket 2 rate (22%)"
},
"id" : "i_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.22"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Bracket 1 portion"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2000.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Bracket 2 portion"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1650.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Bracket 1 tax"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "200.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Bracket 2 tax"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.6E+2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Federal tax withheld"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "560.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "State tax rate (5%)"
},
"id" : "i_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.05"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "State tax withheld"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "182.5000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "After federal withholding"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3090.0000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Net pay"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2907.5000"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a+b)mc"
} ],
"name" : "add"
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
"value" : "i_6"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_5"
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
"value" : "i_10"
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
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
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
"value" : "o_13"
}, {
"key" : "b",
"value" : "i_11"
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
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b",
"value" : "i_12"
}, {
"key" : "mc",
"value" : "i_2"
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
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_15"
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
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
}, {
"key" : "b",
"value" : "o_17"
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
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_20"
}, {
"key" : "b",
"value" : "o_19"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_21"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_6, i_7, i_10, i_11, i_12, i_18]
- **Leaf variable IDs**: [o_21]
- **Variable IDs consumed as an argument by more than one operation**: [o_9, o_13]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
