## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Staking: interest accrual and distribution"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Staked principal"
},
"id" : "i_1",
"valueClass" : "java.math.BigInteger"
},
"value" : "100000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "period",
"value" : "41"
} ],
"name" : "Rate accumulator"
},
"id" : "i_2",
"valueClass" : "java.math.BigInteger"
},
"value" : "500"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "period",
"value" : "42"
} ],
"name" : "Interest accrued (bps)"
},
"id" : "i_3",
"valueClass" : "java.math.BigInteger"
},
"value" : "37"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Basis points denominator"
},
"id" : "i_4",
"valueClass" : "java.math.BigInteger"
},
"value" : "10000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "period",
"value" : "42"
} ],
"name" : "Rate accumulator"
},
"id" : "o_5",
"valueClass" : "java.math.BigInteger"
},
"value" : "537"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "period",
"value" : "42"
} ],
"name" : "Reward numerator"
},
"id" : "o_6",
"valueClass" : "java.math.BigInteger"
},
"value" : "50000000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "period",
"value" : "42"
} ],
"name" : "Distributed reward"
},
"id" : "o_7",
"valueClass" : "java.math.BigInteger"
},
"value" : "5000"
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
"value" : "i_2"
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
"value" : "i_2"
} ],
"resultId" : "o_6"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "a/b"
} ],
"name" : "divide"
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
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4]
- **Leaf variable IDs**: [o_5, o_7]
- **Variable IDs consumed as an argument by more than one operation**: [i_2]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: o_5 ("Rate accumulator") shares its name with: [i_2]

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
