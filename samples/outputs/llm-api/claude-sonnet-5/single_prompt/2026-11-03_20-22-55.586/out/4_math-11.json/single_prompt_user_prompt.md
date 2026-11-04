## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Probability: multivariate normal density"
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
"name" : "Dimension, k"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Mean, μ"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Covariance, Σ"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Evaluation point, x"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "π"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "3.141592653589793"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Constant 2"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Constant −1/2"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-0.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Constant 1"
},
"id" : "i_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2π"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.283185307179586"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "−k"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Normalisation exponent, −k/2"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ln(2π)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.8378770664093453"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "ln of normalisation factor"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0E-16"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Normalisation factor, (2π)^(−k/2)"
},
"id" : "o_15",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "√det(Σ)"
},
"id" : "o_16",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Determinant factor, det(Σ)^(−1/2)"
},
"id" : "o_17",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Inverse covariance, Σ⁻¹"
},
"id" : "o_18",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Deviation, x − μ"
},
"id" : "o_19",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "(x − μ)ᵀ Σ⁻¹"
},
"id" : "o_20",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Quadratic form, (x − μ)ᵀ Σ⁻¹ (x − μ)"
},
"id" : "o_21",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.25"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Exponent, −½ · quadratic form"
},
"id" : "o_22",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1.125"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Exponential term"
},
"id" : "o_23",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.32465246735834974"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Normalisation · determinant factor"
},
"id" : "o_24",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Density, f(x)"
},
"id" : "o_25",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.3246524673583497"
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
"value" : "i_7"
}, {
"key" : "b",
"value" : "i_6"
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
"value" : "(-a)mc"
} ],
"name" : "negate"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
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
"value" : "floor(a/b)mc"
} ],
"name" : "divideToIntegralValue"
},
"id" : "op_3",
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
"resultId" : "o_12"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "Ln(a)"
} ],
"name" : "Ln_double"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
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
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_13"
}, {
"key" : "b",
"value" : "o_12"
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
"value" : "Exp(a)"
} ],
"name" : "Exp_double"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
} ],
"resultId" : "o_15"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "sqrt(a)mc"
} ],
"name" : "sqrt"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_4"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_9"
}, {
"key" : "b",
"value" : "i_4"
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
"value" : "(a-b)mc"
} ],
"name" : "subtract"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_5"
}, {
"key" : "b",
"value" : "i_3"
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
"id" : "op_11",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_19"
}, {
"key" : "b",
"value" : "o_18"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_12",
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
"value" : "o_21"
}, {
"key" : "b",
"value" : "i_8"
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
"value" : "Exp(a)"
} ],
"name" : "Exp_double"
},
"id" : "op_14",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_22"
} ],
"resultId" : "o_23"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_15",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_15"
}, {
"key" : "b",
"value" : "o_17"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_16",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_24"
}, {
"key" : "b",
"value" : "o_23"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_25"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6, i_7, i_8, i_9]
- **Leaf variable IDs**: [o_25]
- **Variable IDs consumed as an argument by more than one operation**: [i_4, i_7, i_9, o_19]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
