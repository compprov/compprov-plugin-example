## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Optimization: CMA-ES bounded-variable encoding round trip"
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
"name" : "Candidate point, x"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "11.1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Lower bound"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-5E+16"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Upper bound"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Bound range, upper − lower"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.000000000000002E+16"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Candidate shifted by lower bound"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.000000000000001E+16"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Encoded candidate"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.9999999999999998"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Encoded lower limit, 0"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Encoded upper limit, 1"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Encoded candidate vs encoded lower bound"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Encoded candidate vs encoded upper bound"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Rescaled candidate"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5.000000000000001E+16"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Decoded candidate"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1E+1"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
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
"value" : "o_6"
}, {
"key" : "b",
"value" : "o_5"
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
"value" : "i_8"
} ],
"resultId" : "o_10"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
"value" : "i_9"
} ],
"resultId" : "o_11"
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
"value" : "o_5"
}, {
"key" : "b",
"value" : "o_7"
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
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_13"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_8, i_9]
- **Leaf variable IDs**: [o_10, o_11, o_13]
- **Variable IDs consumed as an argument by more than one operation**: [i_3, o_5, o_7]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
