## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Fractions: ordering of two close approximations of pi"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Exact long arithmetic (DECIMAL128)"
},
"id" : "i_1",
"valueClass" : "java.math.MathContext"
},
"value" : {
"precision" : 34,
"roundingMode" : "HALF_EVEN"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "First numerator"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1068966896"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "First denominator"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "340262731"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Second numerator"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "411557987"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Second denominator"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "131002976"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "First numerator · second denominator"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "140037844621482496"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "First denominator · second numerator"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "140037844621482497"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Fraction comparison"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "-1"
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
"value" : "i_2"
}, {
"key" : "b",
"value" : "i_5"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_2",
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
}, {
"key" : "b",
"value" : "o_7"
} ],
"resultId" : "o_8"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5]
- **Leaf variable IDs**: [o_8]
- **Variable IDs consumed as an argument by more than one operation**: [] (none detected)
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
