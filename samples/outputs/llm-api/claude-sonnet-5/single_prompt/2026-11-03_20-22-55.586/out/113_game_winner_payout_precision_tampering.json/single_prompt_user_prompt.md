## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Game: winner interest payout"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total game interest (pool)"
},
"id" : "i_1",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Winner count"
},
"id" : "i_2",
"valueClass" : "java.math.BigInteger"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Per-winner payout"
},
"id" : "o_3",
"valueClass" : "java.math.BigInteger"
},
"value" : "333"
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
"value" : "i_2"
} ],
"resultId" : "o_3"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2]
- **Leaf variable IDs**: [o_3]
- **Variable IDs consumed as an argument by more than one operation**: [] (none detected)
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
