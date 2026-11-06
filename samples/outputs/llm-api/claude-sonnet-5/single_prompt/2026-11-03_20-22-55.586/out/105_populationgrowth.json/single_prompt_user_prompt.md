## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Biology: exponential population growth"
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
"name" : "Initial population, N0 (cells)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Continuous growth rate, r (per hour)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.03"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "checkpoint",
"value" : "1"
} ],
"name" : "Time checkpoint t1 (hours)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "5"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "checkpoint",
"value" : "2"
} ],
"name" : "Time checkpoint t2 (hours)"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "10"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "checkpoint",
"value" : "3"
} ],
"name" : "Time checkpoint t3 (hours)"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "20"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "r × t1 (growth exponent)"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.15"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "e^(r×t1)"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.161834242728283"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "checkpoint",
"value" : "1"
} ],
"name" : "Population at t1 (cells)"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1161834.242728283"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "r × t2 (growth exponent)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.30"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "e^(r×t2)"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.3498588075760032"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "checkpoint",
"value" : "2"
} ],
"name" : "Population at t2 (cells)"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1349858.807576003"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "r × t3 (growth exponent)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.60"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "e^(r×t3)"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.8221188003905089"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "checkpoint",
"value" : "3"
} ],
"name" : "Population at t3 (cells)"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1822118.800390509"
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
"value" : "Exp(a)"
} ],
"name" : "Exp_double"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
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
"value" : "i_2"
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
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_5"
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
"value" : "Exp(a)"
} ],
"name" : "Exp_double"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
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
"value" : "i_2"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_6"
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
"value" : "Exp(a)"
} ],
"name" : "Exp_double"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
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
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
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
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6]
- **Leaf variable IDs**: [o_9, o_12, o_15]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
