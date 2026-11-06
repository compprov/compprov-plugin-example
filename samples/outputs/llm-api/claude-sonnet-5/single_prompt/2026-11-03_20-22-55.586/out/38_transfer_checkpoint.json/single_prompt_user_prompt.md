## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Token: transfer with reward checkpoint"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "balanceBasis",
"value" : "opening"
}, {
"key" : "recordedAt",
"value" : "2026-09-08T10:14:59Z"
} ],
"name" : "Sender balance (opening)"
},
"id" : "i_1",
"valueClass" : "java.math.BigInteger"
},
"value" : "10000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Transfer amount"
},
"id" : "i_2",
"valueClass" : "java.math.BigInteger"
},
"value" : "3000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Reward rate per token"
},
"id" : "i_3",
"valueClass" : "java.math.BigInteger"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "balanceBasis",
"value" : "closing"
}, {
"key" : "recordedAt",
"value" : "2026-09-08T10:15:00Z"
} ],
"name" : "Sender balance (closing)"
},
"id" : "o_4",
"valueClass" : "java.math.BigInteger"
},
"value" : "7000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "balanceBasis",
"value" : "opening"
}, {
"key" : "recordedAt",
"value" : "2026-09-08T10:15:04Z"
} ],
"name" : "Sender checkpoint reward"
},
"id" : "o_5",
"valueClass" : "java.math.BigInteger"
},
"value" : "14000"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a-b"
} ],
"name" : "subtract"
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
"value" : "i_3"
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
