## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Token sale: minimum purchase gate"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payment sent (wei)"
},
"id" : "i_1",
"valueClass" : "java.math.BigInteger"
},
"value" : "3500000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "1 ether, in wei"
},
"id" : "i_2",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Minimum purchase threshold (whole ether)"
},
"id" : "i_3",
"valueClass" : "java.math.BigInteger"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Zero (gate floor)"
},
"id" : "i_4",
"valueClass" : "java.math.BigInteger"
},
"value" : "0"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payment"
},
"id" : "o_5",
"valueClass" : "java.math.BigInteger"
},
"value" : "3"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Purchase gate decision (payment vs. threshold)"
},
"id" : "o_6",
"valueClass" : "java.math.BigInteger"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Gate multiplier (1 if payment >= threshold, else 0)"
},
"id" : "o_7",
"valueClass" : "java.math.BigInteger"
},
"value" : "1"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Payment, rescaled to wei"
},
"id" : "o_8",
"valueClass" : "java.math.BigInteger"
},
"value" : "3000000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Threshold scaled to wei"
},
"id" : "o_9",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Raw excess payment above threshold (wei)"
},
"id" : "o_10",
"valueClass" : "java.math.BigInteger"
},
"value" : "2000000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Excess payment above threshold (wei, bonus-eligible)"
},
"id" : "o_11",
"valueClass" : "java.math.BigInteger"
},
"value" : "2000000000000000000"
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
"resultId" : "o_5"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a.compare(b)"
} ],
"name" : "compare"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_5"
}, {
"key" : "b",
"value" : "i_3"
} ],
"resultId" : "o_6"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "max(a,b)"
} ],
"name" : "max"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
}, {
"key" : "b",
"value" : "i_4"
} ],
"resultId" : "o_7"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a*b"
} ],
"name" : "multiply"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_5"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_8"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a*b"
} ],
"name" : "multiply"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "i_3"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_9"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a-b"
} ],
"name" : "subtract"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_8"
}, {
"key" : "b",
"value" : "o_9"
} ],
"resultId" : "o_10"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a*b"
} ],
"name" : "multiply"
},
"id" : "op_7",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_7"
} ],
"resultId" : "o_11"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4]
- **Leaf variable IDs**: [o_11]
- **Variable IDs consumed as an argument by more than one operation**: [i_2, i_3, o_5]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
