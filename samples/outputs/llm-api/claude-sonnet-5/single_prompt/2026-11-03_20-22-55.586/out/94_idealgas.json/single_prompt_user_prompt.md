## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Ideal gas law: isothermal compression"
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
"name" : "Amount of gas (mol)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.5"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gas constant R (L·atm/(mol·K))"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.0821"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Temperature (K)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "298"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Initial volume (L)"
},
"id" : "i_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Compressed volume (L)"
},
"id" : "i_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "4"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "n × R"
},
"id" : "o_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.20525"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "n × R × T"
},
"id" : "o_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "61.16450"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Pressure at initial volume, P1 (atm)"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "6.11645"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "P1 × V1"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "61.16450"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Pressure at compressed volume, P2 (atm), via Boyle's law"
},
"id" : "o_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "15.291125"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "P2 × V2"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "61.164500"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "PV cmp nRT (0 - equal)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "P1V1 cmp P2V2 (0 - equal)"
},
"id" : "o_14",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0"
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
"value" : "i_3"
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
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_7"
}, {
"key" : "b",
"value" : "i_4"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
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
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "i_6"
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
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_11"
}, {
"key" : "b",
"value" : "i_6"
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
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_8"
} ],
"resultId" : "o_13"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_12"
} ],
"resultId" : "o_14"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_5, i_6]
- **Leaf variable IDs**: [o_13, o_14]
- **Variable IDs consumed as an argument by more than one operation**: [i_5, i_6, o_8, o_10]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
