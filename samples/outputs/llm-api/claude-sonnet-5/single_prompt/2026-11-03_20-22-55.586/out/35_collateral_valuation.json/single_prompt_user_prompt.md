## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Lending: collateral valuation"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Collateral amount"
},
"id" : "i_1",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Pool reserve (base token)"
},
"id" : "i_2",
"valueClass" : "java.math.BigInteger"
},
"value" : "500"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Pool reserve (quote token)"
},
"id" : "i_3",
"valueClass" : "java.math.BigInteger"
},
"value" : "1050000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Oracle price (quote per base)"
},
"id" : "i_4",
"valueClass" : "java.math.BigInteger"
},
"value" : "2000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Pool spot price (quote per base)"
},
"id" : "o_5",
"valueClass" : "java.math.BigInteger"
},
"value" : "2100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Collateral value (USD)"
},
"id" : "o_6",
"valueClass" : "java.math.BigInteger"
},
"value" : "2100000"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a/b"
} ],
"name" : "divide"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_5"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a*b"
} ],
"name" : "multiply"
},
"id" : "op_2",
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
- **Root variable IDs**: [i_1, i_2, i_3, i_4]
- **Leaf variable IDs**: [i_4, o_6]
- **Variable IDs consumed as an argument by more than one operation**: [] (none detected)
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
