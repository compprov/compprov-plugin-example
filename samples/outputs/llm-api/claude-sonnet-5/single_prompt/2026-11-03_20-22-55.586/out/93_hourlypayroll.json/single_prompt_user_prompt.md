## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Hourly payroll: weekly net pay"
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
"name" : "Base hourly rate"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "22.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Regular hours"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "40"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Regular pay"
},
"id" : "o_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "880.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Overtime hours"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Overtime multiplier (1.5x)"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Overtime rate"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "33.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Overtime pay"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "198.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Night-shift hours"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "12"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Night-shift differential ($/hr)"
},
"id" : "i_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.75"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Night-shift differential pay"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "21.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gross pay"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1099.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payroll tax withholding rate (18%)"
},
"id" : "i_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.18"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payroll tax withholding"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "197.82000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Net pay"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "901.18000"
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
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_2"
}, {
"key" : "mc",
"value" : "i_1"
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
"value" : "i_2"
}, {
"key" : "b",
"value" : "i_6"
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
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_5"
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
"value" : "i_9"
}, {
"key" : "b",
"value" : "i_10"
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
"value" : "(a+b0+...+bn)mc"
} ],
"name" : "addBulk"
},
"id" : "op_5",
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "o_14"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_15"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_5, i_6, i_9, i_10, i_13]
- **Leaf variable IDs**: [o_15]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, o_12]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
