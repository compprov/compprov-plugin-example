## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "AMM swap #48213: taker fee settlement"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gross amount to taker"
},
"id" : "i_1",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000003"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Fee rate (basis points)"
},
"id" : "i_2",
"valueClass" : "java.math.BigInteger"
},
"value" : "37"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Basis points denominator"
},
"id" : "i_3",
"valueClass" : "java.math.BigInteger"
},
"value" : "10000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Fee numerator"
},
"id" : "o_4",
"valueClass" : "java.math.BigInteger"
},
"value" : "37000111"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Taker fee"
},
"id" : "o_5",
"valueClass" : "java.math.BigInteger"
},
"value" : "3700"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Net amount to taker"
},
"id" : "o_6",
"valueClass" : "java.math.BigInteger"
},
"value" : "996303"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a*b"
} ],
"name" : "multiply"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "i_1"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_4"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a/b"
} ],
"name" : "divide"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_4"
}, {
"key" : "b",
"value" : "i_3"
} ],
"resultId" : "o_5"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a-b"
} ],
"name" : "subtract"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "i_1"
}, {
"key" : "b",
"value" : "o_5"
} ],
"resultId" : "o_6"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3]
- **Leaf variable IDs**: [o_6]
- **Variable IDs consumed as an argument by more than one operation**: [i_1]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
