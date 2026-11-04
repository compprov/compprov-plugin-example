## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "BPT join #77410: USDC contribution"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "10 constant"
},
"id" : "i_1",
"valueClass" : "java.math.BigInteger"
},
"value" : "10"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "2 constant"
},
"id" : "i_2",
"valueClass" : "java.math.BigInteger"
},
"value" : "2"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "BONE decimals"
},
"id" : "i_3",
"valueClass" : "java.lang.Integer"
},
"value" : 18
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "BONE scale"
},
"id" : "o_4",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "USDC decimals"
},
"id" : "i_5",
"valueClass" : "java.lang.Integer"
},
"value" : 6
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "USDC scale"
},
"id" : "o_6",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Requested BPT (poolAmountOut)"
},
"id" : "i_7",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Total BPT supply"
},
"id" : "i_8",
"valueClass" : "java.math.BigInteger"
},
"value" : "5000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Pool USDC balance"
},
"id" : "i_9",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Contribution ratio numerator"
},
"id" : "o_10",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000000000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Half of total BPT supply"
},
"id" : "o_11",
"valueClass" : "java.math.BigInteger"
},
"value" : "2500000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Contribution ratio numerator"
},
"id" : "o_12",
"valueClass" : "java.math.BigInteger"
},
"value" : "1000000000002500000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Contribution ratio"
},
"id" : "o_13",
"valueClass" : "java.math.BigInteger"
},
"value" : "200000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "USDC contribution numerator"
},
"id" : "o_14",
"valueClass" : "java.math.BigInteger"
},
"value" : "200000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Half of BONE scale"
},
"id" : "o_15",
"valueClass" : "java.math.BigInteger"
},
"value" : "500000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "USDC contribution numerator"
},
"id" : "o_16",
"valueClass" : "java.math.BigInteger"
},
"value" : "700000000000000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Required USDC contribution (tokenAmountIn)"
},
"id" : "o_17",
"valueClass" : "java.math.BigInteger"
},
"value" : "0"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a^n"
} ],
"name" : "pow"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "i_1"
}, {
"key" : "n",
"value" : "i_3"
} ],
"resultId" : "o_4"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a^n"
} ],
"name" : "pow"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "i_1"
}, {
"key" : "n",
"value" : "i_5"
} ],
"resultId" : "o_6"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a*b"
} ],
"name" : "multiply"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "i_7"
}, {
"key" : "b",
"value" : "o_4"
} ],
"resultId" : "o_10"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a/b"
} ],
"name" : "divide"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "i_8"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_11"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a+b"
} ],
"name" : "add"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "o_11"
} ],
"resultId" : "o_12"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a/b"
} ],
"name" : "divide"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "i_8"
} ],
"resultId" : "o_13"
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
"value" : "i_9"
}, {
"key" : "b",
"value" : "o_13"
} ],
"resultId" : "o_14"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a/b"
} ],
"name" : "divide"
},
"id" : "op_8",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_4"
}, {
"key" : "b",
"value" : "i_2"
} ],
"resultId" : "o_15"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a+b"
} ],
"name" : "add"
},
"id" : "op_9",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_14"
}, {
"key" : "b",
"value" : "o_15"
} ],
"resultId" : "o_16"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a/b"
} ],
"name" : "divide"
},
"id" : "op_10",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigInteger"
},
"arguments" : [ {
"key" : "a",
"value" : "o_16"
}, {
"key" : "b",
"value" : "o_4"
} ],
"resultId" : "o_17"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_5, i_7, i_8, i_9]
- **Leaf variable IDs**: [o_6, o_17]
- **Variable IDs consumed as an argument by more than one operation**: [i_1, i_2, o_4, i_8]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
