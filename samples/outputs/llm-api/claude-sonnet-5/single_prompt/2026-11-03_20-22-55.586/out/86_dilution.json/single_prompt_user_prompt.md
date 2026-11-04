## CPG
<CPG>
{
"version" : "1.0",
"descriptor" : {
"meta" : [ ],
"name" : "Chemistry: serial dilution of NaCl stock"
},
"variables" : [ {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Computation precision (DECIMAL64)"
},
"id" : "i_1",
"valueClass" : "java.math.MathContext"
},
"value" : {
"precision" : 16,
"roundingMode" : "HALF_EVEN"
}
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "step",
"value" : "1"
} ],
"name" : "Stock concentration, C1 (mol/L)"
},
"id" : "i_2",
"valueClass" : "java.math.BigDecimal"
},
"value" : "2.00"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "step",
"value" : "1"
} ],
"name" : "Stock aliquot volume, V1 (L)"
},
"id" : "i_3",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.050"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "step",
"value" : "1"
} ],
"name" : "Step 1 final volume, V2 (L)"
},
"id" : "i_4",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.500"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "C1 × V1 (step 1)"
},
"id" : "o_5",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.10000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "step",
"value" : "1"
} ],
"name" : "Intermediate concentration (mol/L)"
},
"id" : "o_6",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.20"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "step",
"value" : "2"
} ],
"name" : "Intermediate aliquot volume, V1 (L)"
},
"id" : "i_7",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.100"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "step",
"value" : "2"
} ],
"name" : "Step 2 final volume, V2 (L)"
},
"id" : "i_8",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "C1 × V1 (step 2)"
},
"id" : "o_9",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.02000"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "step",
"value" : "2"
} ],
"name" : "Working (final) concentration (mol/L)"
},
"id" : "o_10",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.02"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Molar mass of NaCl (g/mol)"
},
"id" : "i_11",
"valueClass" : "java.math.BigDecimal"
},
"value" : "58.44"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Moles of solute in final volume (mol)"
},
"id" : "o_12",
"valueClass" : "java.math.BigDecimal"
},
"value" : "0.02000"
}, {
"track" : {
"descriptor" : {
"meta" : [ ],
"name" : "Mass of solute in final volume (g)"
},
"id" : "o_13",
"valueClass" : "java.math.BigDecimal"
},
"value" : "1.1688000"
} ],
"operations" : [ {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_1",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "i_2"
}, {
"key" : "b",
"value" : "i_3"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_5"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_2",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_5"
}, {
"key" : "b",
"value" : "i_4"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_6"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_3",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_6"
}, {
"key" : "b",
"value" : "i_7"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_9"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a/b)mc"
} ],
"name" : "divide"
},
"id" : "op_4",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_9"
}, {
"key" : "b",
"value" : "i_8"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_10"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_5",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_10"
}, {
"key" : "b",
"value" : "i_8"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_12"
}, {
"track" : {
"descriptor" : {
"meta" : [ {
"key" : "formula",
"value" : "(a*b)mc"
} ],
"name" : "multiply"
},
"id" : "op_6",
"wrapperClass" : "io.compprov.core.wrappers.WrappedBigDecimal"
},
"arguments" : [ {
"key" : "a",
"value" : "o_12"
}, {
"key" : "b",
"value" : "i_11"
}, {
"key" : "mc",
"value" : "i_1"
} ],
"resultId" : "o_13"
} ]
}

</CPG>

## STRUCTURAL REFERENCE DATA
- **Root variable IDs**: [i_1, i_2, i_3, i_4, i_7, i_8, i_11]
- **Leaf variable IDs**: [o_13]
- **Variable IDs consumed as an argument by more than one operation**: [i_8]
- **Operations that consume the same variable more than once**: [] (none detected)
- **Leaf variables that share their exact display name with another variable**: [] (none detected)

Audit this CPG against all three attack vectors defined in the system message and return the JSON report.
