## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "DeFi vault: first deposit"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "First deposit amount"
},
"id" : "i_1",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Minimum liquidity constant"
},
"id" : "i_2",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total share supply (opening)"
},
"id" : "i_3",
"valueClass" : "java.math.BigInteger"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Minimum liquidity floor"
},
"id" : "o_4",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "First depositor shares"
},
"id" : "o_5",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000000"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "min(a,b)"
} ],
"name" : "min"
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
"value" : "a+b"
} ],
"name" : "add"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_1"
} ],
"resultId" : "o_5"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3]
- **Leaf variable IDs**: [o_4, o_5]
- **Variable IDs consumed as an argument by more than one operation**: [i_1]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
