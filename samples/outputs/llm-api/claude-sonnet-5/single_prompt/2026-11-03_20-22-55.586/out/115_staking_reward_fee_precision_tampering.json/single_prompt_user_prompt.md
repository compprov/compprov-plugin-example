## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Staking: developer fee deduction"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Accumulated staking reward"
},
"id" : "i_1",
"valueClass" : "java.math.BigInteger"
},
"value" : "199"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Developer fee percent"
},
"id" : "i_2",
"valueClass" : "java.math.BigInteger"
},
"value" : "10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Percent denominator (100)"
},
"id" : "i_3",
"valueClass" : "java.math.BigInteger"
},
"value" : "100"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Reward / denominator"
},
"id" : "o_4",
"valueClass" : "java.math.BigInteger"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Developer reward (fee)"
},
"id" : "o_5",
"valueClass" : "java.math.BigInteger"
},
"value" : "10"
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
"value" : "i_1"
}, {
"key" : "b",
"value" : "i_3"
} ],
"resultId" : "o_4"
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
"value" : "o_4"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_5"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3]
- **Leaf variable IDs**: [o_5]
- **Variable IDs consumed as an argument by more than one operation**: [] (none detected)
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
