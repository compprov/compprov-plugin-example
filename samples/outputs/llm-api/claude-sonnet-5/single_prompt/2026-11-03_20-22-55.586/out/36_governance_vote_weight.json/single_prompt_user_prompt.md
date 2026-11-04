## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Governance: vote tally"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Existing votes for proposal"
},
"id" : "i_1",
"valueClass" : "java.math.BigInteger"
},
"value" : "250000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "blockNumber",
"value" : "19000000"
} ],
"name" : "Voter balance (at proposal snapshot block)"
},
"id" : "i_2",
"valueClass" : "java.math.BigInteger"
},
"value" : "10000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "blockNumber",
"value" : "19000050"
} ],
"name" : "Voter balance (current block)"
},
"id" : "i_3",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "New vote tally"
},
"id" : "o_4",
"valueClass" : "java.math.BigInteger"
},
"value" : "1250000"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a+b"
} ],
"name" : "add"
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
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3]
- **Leaf variable IDs**: [i_2, o_4]
- **Variable IDs consumed as an argument by more than one operation**: [] (none detected)
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
